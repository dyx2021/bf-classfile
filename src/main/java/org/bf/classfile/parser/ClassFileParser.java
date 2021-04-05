/*
 * Copyright 2021 The BF Project
 *
 * The BF Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package org.bf.classfile.parser;

import org.bf.classfile.ci.*;
import org.bf.classfile.util.BytesReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClassFileParser {
    private static int[] MAGIC_BYTES = {0xCA, 0xFE, 0xBA, 0xBE};

    public ClassFile from(byte bs[]) {
        ClassFile rst = new ClassFile();
        BytesReader in = new BytesReader(bs);
        rst.setMagic(in.readBytes(4));
        if (!Arrays.equals(rst.getMagic(), MAGIC_BYTES)) {
            throw new RuntimeException("illegal java magic header");
        }
        rst.setMinor_version(in.readShort());
        rst.setMajor_version(in.readShort());
        fromConstantPool(in, rst);
        rst.setAccess_flags(in.readShort());
        rst.setThis_class(in.readShort());
        rst.setSuper_class(in.readShort());
        rst.setInterfaces_count(in.readShort());
        for (int i = 0; i < rst.getInterfaces_count(); i++) {
            rst.addInterface(in.readShort());
        }
        fromFields(in, rst);
        fromMethods(in, rst);
        rst.setAttributes(fromAttributes(in, rst));
        rst.setAttributes_count(rst.getAttributes().size());
        return rst;
    }

    private void fromMethods(BytesReader in, ClassFile rst) {
        rst.setMethods_count(in.readShort());
        for (int i = 0; i < rst.getMethods_count(); i++) {
            MethodInfo info = new MethodInfo();
            info.setAccess_flags(in.readShort());
            info.setName_index(in.readShort());
            info.setDescriptor_index(in.readShort());
            info.setAttributes(fromAttributes(in, rst));
            info.setAttributes_count(info.getAttributes().size());
            rst.addMethod(info);
        }
    }

    private String getUtf8(ClassFile cf, int i) {
        CpUtf8Info info = cf.getConstant(i);
        if (info == null) {
            throw new RuntimeException("illegal utf8 index " + i);
        }
        return info.getValue();
    }

    private List<AttributeInfo> fromAttributes(BytesReader in, ClassFile cf) {
        int count = in.readShort();
        List<AttributeInfo> rst = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            int attribute_name_index = in.readShort();
            int attribute_length = in.readInt();
            switch (getUtf8(cf, attribute_name_index)) {
                case "ConstantValue": {
                    AttrConstantValue attr = new AttrConstantValue(attribute_name_index, attribute_length);
                    rst.add(attr);
                    attr.setConstantvalue_index(in.readShort());
                    break;
                }
                case "Code": {
                    AttrCode attr = new AttrCode(attribute_name_index, attribute_length);
                    rst.add(attr);
                    attr.setMax_stack(in.readShort());
                    attr.setMax_locals(in.readShort());
                    attr.setCode_length(in.readInt());
                    attr.setCode(in.readBytes(attr.getCode_length()));
                    attr.setException_table_length(in.readShort());
                    for (int j = 0; j < attr.getException_table_length(); j++) {
                        ExceptionTable et = new ExceptionTable();
                        et.setStart_pc(in.readShort());
                        et.setEnd_pc(in.readShort());
                        et.setHandler_pc(in.readShort());
                        et.setCatch_type(in.readShort());
                        attr.addExceptionTable(et);
                    }
                    attr.setAttributes(fromAttributes(in, cf));
                    attr.setAttributes_count(attr.getAttributes().size());
                    break;
                }
                case "StackMapTable": {
                    AttrStackMapTable attr = new AttrStackMapTable(attribute_name_index, attribute_length);
                    rst.add(attr);
                    int number_of_entries = in.readShort();
                    attr.setNumber_of_entries(number_of_entries);
                    for (int j = 0; j < number_of_entries; j++) {
                        int frame_type = in.read();
                        FrameTypeEnum type = FrameTypeEnum.of(frame_type);
                        if (type == null) {
                            throw new RuntimeException("illegal frame type " + frame_type);
                        }
                        switch (type) {
                            case SAME: {
                                FmSame fm = new FmSame(frame_type, type);
                                attr.addEntry(fm);
                                break;
                            }
                            case SAME_LOCALS_1_STACK_ITEM: {
                                FmSameLocals1StackItem fm = new FmSameLocals1StackItem(frame_type, type);
                                attr.addEntry(fm);
                                fm.getStack().addAll(fromVerificationTypeInfo(in, 1));
                                break;
                            }
                            case SAME_LOCALS_1_STACK_ITEM_EXTENDED: {
                                FmSameLocals1StackItemExtended fm = new FmSameLocals1StackItemExtended(frame_type, type);
                                attr.addEntry(fm);
                                fm.setOffset_delta(in.readShort());
                                fm.getStack().addAll(fromVerificationTypeInfo(in, 1));
                                break;
                            }
                            case CHOP: {
                                FmChop fm = new FmChop(frame_type, type);
                                attr.addEntry(fm);
                                fm.setOffset_delta(in.readShort());
                                break;
                            }
                            case SAME_FRAME_EXTENDED: {
                                FmSameExtended fm = new FmSameExtended(frame_type, type);
                                attr.addEntry(fm);
                                fm.setOffset_delta(in.readShort());
                                break;
                            }
                            case APPEND: {
                                FmAppend fm = new FmAppend(frame_type, type);
                                attr.addEntry(fm);
                                fm.setOffset_delta(in.readShort());
                                fm.getLocals().addAll(fromVerificationTypeInfo(in, fm.getFrame_type() - 251));
                                break;
                            }
                            case FULL_FRAME: {
                                FmFull fm = new FmFull(frame_type, type);
                                attr.addEntry(fm);
                                fm.setOffset_delta(in.readShort());
                                fm.setNumber_of_locals(in.readShort());
                                fm.getLocals().addAll(fromVerificationTypeInfo(in, fm.getNumber_of_locals()));
                                fm.setNumber_of_stack_items(in.readShort());

                                fm.getStack().addAll(fromVerificationTypeInfo(in, fm.getNumber_of_stack_items()));
                                break;
                            }
                            default:
                                throw new RuntimeException("illegal StackMapTable frame type " + frame_type);
                        }
                    }
                    break;
                }
                case "Exceptions": {
                    AttrExceptions attr = new AttrExceptions(attribute_name_index, attribute_length);
                    rst.add(attr);
                    attr.setNumber_of_exceptions(in.readShort());
                    attr.setException_index_table(in.readBytes(attr.getNumber_of_exceptions()));
                    break;
                }
                case "InnerClasses": {
                    AttrInnerClasses attr = new AttrInnerClasses(attribute_name_index, attribute_length);
                    rst.add(attr);
                    attr.setNumber_of_classes(in.readShort());
                    for (int j = 0; j < attr.getNumber_of_classes(); j++) {
                        InnerClassInfo innerClassInfo = new InnerClassInfo();
                        attr.getClasses().add(innerClassInfo);
                        innerClassInfo.setInner_class_info_index(in.readShort());
                        innerClassInfo.setOuter_class_info_index(in.readShort());
                        innerClassInfo.setInner_name_index(in.readShort());
                        innerClassInfo.setInner_class_access_flags(in.readShort());
                    }
                    break;
                }
                case "EnclosingMethod": {
                    AttrEnclosingMethod attr = new AttrEnclosingMethod(attribute_name_index, attribute_length);
                    rst.add(attr);
                    attr.setClass_index(in.readShort());
                    attr.setMethod_index(in.readShort());
                    break;
                }
                case "Synthetic": {
                    AttrSynthetic attr = new AttrSynthetic(attribute_name_index, attribute_length);
                    rst.add(attr);
                    break;
                }
                case "Signature": {
                    AttrSignature attr = new AttrSignature(attribute_name_index, attribute_length);
                    rst.add(attr);
                    attr.setSignature_index(in.readShort());
                    break;
                }
                case "SourceFile": {
                    AttrSourceFile attr = new AttrSourceFile(attribute_name_index, attribute_length);
                    rst.add(attr);
                    attr.setSourcefile_index(in.readShort());
                    break;
                }
                case "SourceDebugExtension": {
                    AttrSourceDebugExtension attr = new AttrSourceDebugExtension(attribute_name_index, attribute_length);
                    rst.add(attr);
                    attr.setDebug_extension(in.readJvmModifiedUtf8(attr.getAttribute_length()));
                    break;
                }
                case "LineNumberTable": {
                    AttrLineNumberTable attr = new AttrLineNumberTable(attribute_name_index, attribute_length);
                    rst.add(attr);
                    attr.setLine_number_table_length(in.readShort());
                    for (int j = 0; j < attr.getLine_number_table_length(); j++) {
                        LineNumberInfo lineNumberInfo = new LineNumberInfo();
                        lineNumberInfo.setStart_pc(in.readShort());
                        lineNumberInfo.setLine_number(in.readShort());
                        attr.getLine_number_table().add(lineNumberInfo);
                    }
                    break;
                }
                case "LocalVariableTable": {
                    AttrLocalVariableTable attr = new AttrLocalVariableTable(attribute_name_index, attribute_length);
                    rst.add(attr);
                    attr.setLocal_variable_table_length(in.readShort());
                    for (int j = 0; j < attr.getLocal_variable_table_length(); j++) {
                        LocalVariableTableInfo info = new LocalVariableTableInfo();
                        info.setStart_pc(in.readShort());
                        info.setLength(in.readShort());
                        info.setName_index(in.readShort());
                        info.setDescriptor_index(in.readShort());
                        info.setIndex(in.readShort());
                        attr.getLocal_variable_table().add(info);
                    }
                    break;
                }
                case "LocalVariableTypeTable": {
                    AttrLocalVariableTypeTable attr = new AttrLocalVariableTypeTable(attribute_name_index, attribute_length);
                    rst.add(attr);
                    attr.setLocal_variable_type_table_length(in.readShort());
                    for (int j = 0; j < attr.getLocal_variable_type_table_length(); j++) {
                        LocalVariableTypeTableInfo info = new LocalVariableTypeTableInfo();
                        info.setStart_pc(in.readShort());
                        info.setLength(in.readShort());
                        info.setName_index(in.readShort());
                        info.setSignature_index(in.readShort());
                        info.setIndex(in.readShort());
                        attr.getLocal_variable_type_table().add(info);
                    }
                    break;
                }
                case "Deprecated": {
                    AttrDeprecated attr = new AttrDeprecated(attribute_name_index, attribute_length);
                    rst.add(attr);
                    break;
                }
                case "RuntimeVisibleAnnotations": {
                    AttrRuntimeVisibleAnnotations attr = new AttrRuntimeVisibleAnnotations(attribute_name_index, attribute_length);
                    rst.add(attr);
                    attr.setNum_annotations(in.readShort());
                    for (int j = 0; j < attr.getNum_annotations(); j++) {
                        attr.getAnnotations().add(fromAnnotation(in));
                    }
                    break;
                }
                case "RuntimeInvisibleAnnotations": {
                    AttrRuntimeInvisible attr = new AttrRuntimeInvisible(attribute_name_index, attribute_length);
                    rst.add(attr);
                    attr.setNum_annotations(in.readShort());
                    for (int j = 0; j < attr.getNum_annotations(); j++) {
                        attr.getAnnotations().add(fromAnnotation(in));
                    }
                    break;
                }
                case "RuntimeVisibleParameterAnnotations": {
                    AttrRuntimeVisibleParameter attr = new AttrRuntimeVisibleParameter(attribute_name_index, attribute_length);
                    rst.add(attr);
                    attr.setNum_parameters(in.read());
                    for (int j = 0; j < attr.getNum_parameters(); j++) {
                        EvParameterAnnotation p = new EvParameterAnnotation();
                        p.setNum_annotations(in.readShort());
                        for (int k = 0; k < p.getNum_annotations(); k++) {
                            p.getAnnotations().add(fromAnnotation(in));
                        }
                        attr.getParameter_annotations().add(p);
                    }
                    break;
                }
                case "RuntimeInvisibleParameterAnnotations": {
                    AttrRuntimeInvisibleParameter attr = new AttrRuntimeInvisibleParameter(attribute_name_index, attribute_length);
                    rst.add(attr);
                    attr.setNum_parameters(in.read());
                    for (int j = 0; j < attr.getNum_parameters(); j++) {
                        EvParameterAnnotation p = new EvParameterAnnotation();
                        p.setNum_annotations(in.readShort());
                        for (int k = 0; k < p.getNum_annotations(); k++) {
                            p.getAnnotations().add(fromAnnotation(in));
                        }
                        attr.getParameter_annotations().add(p);
                    }
                    break;
                }
                case "RuntimeVisibleTypeAnnotations": {
                    AttrRuntimeVisibleTypeAnnotations attr = new AttrRuntimeVisibleTypeAnnotations(attribute_name_index, attribute_length);
                    rst.add(attr);
                    attr.setNum_annotations(in.read());
                    for (int j = 0; j < attr.getNum_annotations(); j++) {
                        attr.getAnnotations().add(fromTypeAnnotation(in));
                    }
                    break;
                }
                case "AnnotationDefault": {
                    AttrAnnotationDefault attr = new AttrAnnotationDefault(attribute_name_index, attribute_length);
                    rst.add(attr);
                    attr.setDefault_value(fromElementValueInfo(in));
                    break;
                }
                case "BootstrapMethods": {
                    AttrBootstrapMethods attr = new AttrBootstrapMethods(attribute_name_index, attribute_length);
                    rst.add(attr);
                    attr.setNum_bootstrap_methods(in.readShort());
                    for (int j = 0; j < attr.getNum_bootstrap_methods(); j++) {
                        BootstrapMethod method = new BootstrapMethod();
                        attr.getBootstrap_methods().add(method);
                        method.setBootstrap_method_ref(in.readShort());
                        method.setNum_bootstrap_arguments(in.readShort());
                        method.setBootstrap_arguments(in.readBytes(method.getNum_bootstrap_arguments()));
                    }
                    break;
                }
                case "MethodParameters": {
                    AttrMethodParameters attr = new AttrMethodParameters(attribute_name_index, attribute_length);
                    rst.add(attr);
                    attr.setParameters_count(in.read());
                    for (int j = 0; j < attr.getParameters_count(); j++) {
                        ParameterInfo info = new ParameterInfo();
                        attr.getParameters().add(info);
                        info.setAccess_flags(in.readShort());
                        info.setName_index(in.readShort());
                    }
                    break;
                }
                default: {
                    in.skip(attribute_length);
                    break;
                }
            }
        }
        return rst;
    }

    private TypeAnnotation fromTypeAnnotation(BytesReader in) {
        TypeAnnotation rst = new TypeAnnotation();
        int targetType = in.read();
        rst.setTarget_type(targetType);
        switch (targetType) {
            case 0x00:
            case 0x01: {
                TpTypeParameterTarget target = new TpTypeParameterTarget();
                rst.setTarget_info(target);
                target.setType_parameter_index(in.read());
                break;
            }
            case 0x10: {
                TpSupertypeTarget target = new TpSupertypeTarget();
                rst.setTarget_info(target);
                target.setSupertype_index(in.readShort());
                break;
            }
            case 0x11:
            case 0x12: {
                TpTypeParameterBoundTarget target = new TpTypeParameterBoundTarget();
                rst.setTarget_info(target);
                target.setType_parameter_index(in.read());
                target.setBound_index(in.read());
                break;
            }
            case 0x13:
            case 0x14:
            case 0x15: {
                TpEmptyTarget target = new TpEmptyTarget();
                rst.setTarget_info(target);
                break;
            }
            case 0x16: {
                TpFormalParameterTarget target = new TpFormalParameterTarget();
                rst.setTarget_info(target);
                target.setFormal_parameter_index(in.read());
                break;
            }
            case 0x17: {
                TpThrowsTarget target = new TpThrowsTarget();
                rst.setTarget_info(target);
                target.setThrows_type_index(in.readShort());
                break;
            }
            case 0x40:
            case 0x41: {
                TpLocalVarTarget target = new TpLocalVarTarget();
                rst.setTarget_info(target);
                target.setTable_length(in.readShort());
                for (int i = 0; i < target.getTable_length(); i++) {
                    TpTableInfo info = new TpTableInfo();
                    info.setStart_pc(in.readShort());
                    info.setLength(in.readShort());
                    info.setIndex(in.readShort());
                    target.getTable().add(info);
                }
                break;
            }
            case 0x42: {
                TpCatchTarget target = new TpCatchTarget();
                rst.setTarget_info(target);
                target.setException_table_index(in.readShort());
            }
            case 0x43:
            case 0x44:
            case 0x45:
            case 0x46: {
                TpOffsetTarget target = new TpOffsetTarget();
                rst.setTarget_info(target);
                target.setOffset(in.readShort());
            }
            case 0x47:
            case 0x48:
            case 0x49:
            case 0x4A:
            case 0x4B: {
                TpTypeArgumentTarget target = new TpTypeArgumentTarget();
                rst.setTarget_info(target);
                target.setOffset(in.readShort());
                target.setType_argument_index(in.read());
            }
            default:
                throw new RuntimeException("illegal type annotation " + targetType);
        }
        return rst;
    }

    private EvAnnotation fromAnnotation(BytesReader in) {
        EvAnnotation rst = new EvAnnotation();
        rst.setType_index(in.readShort());
        rst.setNum_element_value_pairs(in.readShort());
        for (int i = 0; i < rst.getNum_element_value_pairs(); i++) {
            ElementValuePairsInfo pairsInfo = new ElementValuePairsInfo();
            pairsInfo.setElement_name_index(in.readShort());
            pairsInfo.setValue(fromElementValueInfo(in));
            rst.getElement_value_pairs().add(pairsInfo);
        }
        return rst;
    }

    private ElementValueInfo fromElementValueInfo(BytesReader in) {
        ElementValueInfo info = new ElementValueInfo();
        char tag = (char) in.read();
        info.setTag(tag);
        switch (tag) {
            case 'B':
            case 'C':
            case 'D':
            case 'F':
            case 'I':
            case 'J':
            case 'S':
            case 'Z':
            case 's': {
                EvConstValue value = new EvConstValue();
                info.setValue(value);
                value.setConst_value_index(in.readShort());
                break;
            }
            case 'e': {
                EvEnumConstValue value = new EvEnumConstValue();
                info.setValue(value);
                value.setType_name_index(in.readShort());
                value.setConst_name_index(in.readShort());
                break;
            }
            case 'c': {
                EvClassInfo value = new EvClassInfo();
                info.setValue(value);
                value.setClass_info_index(in.readShort());
                break;
            }
            case '@': {
                EvAnnotation value = fromAnnotation(in);
                info.setValue(value);
                break;
            }
            case '[': {
                EvArrayValue value = new EvArrayValue();
                info.setValue(value);
                value.setNum_values(in.readShort());
                for (int i = 0; i < value.getNum_values(); i++) {
                    value.getValues().add(fromElementValueInfo(in));
                }
                break;
            }
            default:
                throw new RuntimeException("illegal Element Value :" + tag);
        }
        return info;
    }

    private List<VerificationTypeInfo> fromVerificationTypeInfo(BytesReader in, int n) {
        List<VerificationTypeInfo> rst = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            int tagNo = in.read();
            VariableTagEnum tag = VariableTagEnum.of(tagNo);
            switch (tag) {
                case ITEM_Top: {
                    VarInfoTop var = new VarInfoTop(tag);
                    rst.add(var);
                    break;
                }
                case ITEM_Integer: {
                    VarInfoInteger var = new VarInfoInteger(tag);
                    rst.add(var);
                    break;
                }
                case ITEM_Float: {
                    VarInfoFloat var = new VarInfoFloat(tag);
                    rst.add(var);
                    break;
                }
                case ITEM_Double: {
                    VarInfoDouble var = new VarInfoDouble(tag);
                    rst.add(var);
                    break;
                }
                case ITEM_Long: {
                    VarInfoLong var = new VarInfoLong(tag);
                    rst.add(var);
                    break;
                }
                case ITEM_Null: {
                    VarInfoNull var = new VarInfoNull(tag);
                    rst.add(var);
                    break;
                }
                case ITEM_UninitializedThis: {
                    VarInfoUninitializedThis var = new VarInfoUninitializedThis(tag);
                    rst.add(var);
                    break;
                }
                case ITEM_Object: {
                    VarInfoObject var = new VarInfoObject(tag);
                    rst.add(var);
                    var.setCpool_index(in.readShort());
                    break;
                }
                case ITEM_Uninitialized: {
                    VarInfoUninitialized var = new VarInfoUninitialized(tag);
                    rst.add(var);
                    var.setOffset(in.readShort());
                    break;
                }
                default: {
                    throw new RuntimeException("illegal variable tag " + tagNo);
                }
            }
        }
        return rst;
    }

    private void fromFields(BytesReader in, ClassFile rst) {
        rst.setFields_count(in.readShort());
        for (int i = 0; i < rst.getFields_count(); i++) {
            FieldInfo info = new FieldInfo();
            info.setAccess_flags(in.readShort());
            info.setName_index(in.readShort());
            info.setDescriptor_index(in.readShort());
            info.setAttributes(fromAttributes(in, rst));
            info.setAttributes_count(info.getAttributes().size());
            rst.addField(info);
        }
    }

    private void fromConstantPool(BytesReader in, ClassFile rst) {
        rst.setConstant_pool_count(in.readShort());
        for (int i = 1; i < rst.getConstant_pool_count(); ) {
            CpTagEnum tag = CpTagEnum.of(in.read());
            if (tag == null) {
                throw new RuntimeException("tag is empty " + i + "," + in.getPos());
            }
            switch (tag) {
                case CONSTANT_Class: {
                    CpClass info = new CpClass();
                    info.setTag(tag);
                    info.setName_index(in.readShort());
                    rst.putConstant(i++, info);
                    break;
                }
                case CONSTANT_Fieldref: {
                    CpFieldRefInfo info = new CpFieldRefInfo();
                    info.setTag(tag);
                    info.setClass_index(in.readShort());
                    info.setName_and_type_index(in.readShort());
                    rst.putConstant(i++, info);
                    break;
                }
                case CONSTANT_Methodref: {
                    CpMethodRefInfo info = new CpMethodRefInfo();
                    info.setTag(tag);
                    info.setClass_index(in.readShort());
                    info.setName_and_type_index(in.readShort());
                    rst.putConstant(i++, info);
                    break;
                }
                case CONSTANT_InterfaceMethodref: {
                    CpInterfaceMethodRefInfo info = new CpInterfaceMethodRefInfo();
                    info.setTag(tag);
                    info.setClass_index(in.readShort());
                    info.setName_and_type_index(in.readShort());
                    rst.putConstant(i++, info);
                    break;
                }
                case CONSTANT_String: {
                    CpStringInfo info = new CpStringInfo();
                    info.setTag(tag);
                    info.setString_index(in.readShort());
                    rst.putConstant(i++, info);
                    break;
                }
                case CONSTANT_Integer: {
                    CpInteger info = new CpInteger();
                    info.setTag(tag);
                    info.setValue(in.readInt());
                    rst.putConstant(i++, info);
                    break;
                }
                case CONSTANT_Float: {
                    CpFloat info = new CpFloat();
                    info.setTag(tag);
                    info.setValue(in.readFloat());
                    rst.putConstant(i++, info);
                    break;
                }
                case CONSTANT_Long: {
                    CpLong info = new CpLong();
                    info.setTag(tag);
                    info.setValue(in.readLong());
                    rst.putConstant(i++, info);
                    i++;
                    break;
                }
                case CONSTANT_Double: {
                    CpDouble info = new CpDouble();
                    info.setTag(tag);
                    info.setValue(in.readDouble());
                    rst.putConstant(i++, info);
                    i++;
                    break;
                }
                case CONSTANT_NameAndType: {
                    CpNameAndTypeInfo info = new CpNameAndTypeInfo();
                    info.setTag(tag);
                    info.setName_index(in.readShort());
                    info.setDescriptor_index(in.readShort());
                    rst.putConstant(i++, info);
                    break;
                }
                case CONSTANT_Utf8: {
                    CpUtf8Info info = new CpUtf8Info();
                    info.setTag(tag);
                    info.setLength(in.readShort());
                    info.setValue(in.readJvmModifiedUtf8(info.getLength()));
                    rst.putConstant(i++, info);
                    break;
                }
                case CONSTANT_MethodHandle: {
                    CpMethodHandleInfo info = new CpMethodHandleInfo();
                    info.setTag(tag);
                    info.setReference_kind(in.read());
                    info.setReference_index(in.readShort());
                    rst.putConstant(i++, info);
                    break;
                }
                case CONSTANT_MethodType: {
                    CpMethodTypeInfo info = new CpMethodTypeInfo();
                    info.setTag(tag);
                    info.setDescriptor_index(in.readShort());
                    rst.putConstant(i++, info);
                    break;
                }
                case CONSTANT_InvokeDynamic: {
                    CpInvokeDynamicInfo info = new CpInvokeDynamicInfo();
                    info.setTag(tag);
                    info.setBootstrap_method_attr_index(in.readShort());
                    info.setName_and_type_index(in.readShort());
                    rst.putConstant(i++, info);
                    break;
                }
                default: {
                    throw new RuntimeException("illegal constant tag:" + tag);
                }
            }
        }
    }
}
