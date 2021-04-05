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
package org.bf.classfile.ci;

import java.util.*;

public class ClassFile {
    private int[] magic;
    private int minor_version;
    private int major_version;
    private int constant_pool_count;
    private Map<Integer, CpInfo> constant_pool = new HashMap<>();
    private int access_flags;
    private int this_class;
    private int super_class;
    private int interfaces_count;
    private List<Integer> interfaces = new ArrayList<>();
    private int fields_count;
    private List<FieldInfo> fields = new ArrayList<>();
    private int methods_count;
    private List<MethodInfo> methods = new ArrayList<>();
    private int attributes_count;
    private List<AttributeInfo> attributes = new ArrayList<>();

    public int[] getMagic() {
        return magic;
    }

    public void setMagic(int[] magic) {
        this.magic = magic;
    }

    public int getMinor_version() {
        return minor_version;
    }

    public void setMinor_version(int minor_version) {
        this.minor_version = minor_version;
    }

    public int getMajor_version() {
        return major_version;
    }

    public void setMajor_version(int major_version) {
        this.major_version = major_version;
    }

    public int getConstant_pool_count() {
        return constant_pool_count;
    }

    public void setConstant_pool_count(int constant_pool_count) {
        this.constant_pool_count = constant_pool_count;
    }

    public int getAccess_flags() {
        return access_flags;
    }

    public void setAccess_flags(int access_flags) {
        this.access_flags = access_flags;
    }

    public int getThis_class() {
        return this_class;
    }

    public void setThis_class(int this_class) {
        this.this_class = this_class;
    }

    public int getSuper_class() {
        return super_class;
    }

    public void setSuper_class(int super_class) {
        this.super_class = super_class;
    }

    public int getInterfaces_count() {
        return interfaces_count;
    }

    public void setInterfaces_count(int interfaces_count) {
        this.interfaces_count = interfaces_count;
    }

    public void addInterface(int n) {
        this.interfaces.add(n);
    }

    public int getFields_count() {
        return fields_count;
    }

    public void setFields_count(int fields_count) {
        this.fields_count = fields_count;
    }

    public int getMethods_count() {
        return methods_count;
    }

    public void setMethods_count(int methods_count) {
        this.methods_count = methods_count;
    }

    public int getAttributes_count() {
        return attributes_count;
    }

    public void setAttributes_count(int attributes_count) {
        this.attributes_count = attributes_count;
    }

    public Map<Integer, CpInfo> getConstant_pool() {
        return constant_pool;
    }

    public <T extends CpInfo> T getConstant(int n) {
        return (T)this.constant_pool.get(n);
    }

    public void setConstant_pool(Map<Integer, CpInfo> constant_pool) {
        this.constant_pool = constant_pool;
    }

    public void putConstant(int i, CpInfo info) {
        this.constant_pool.put(i, info);
    }

    public List<Integer> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(List<Integer> interfaces) {
        this.interfaces = interfaces;
    }

    public List<FieldInfo> getFields() {
        return fields;
    }

    public void setFields(List<FieldInfo> fields) {
        this.fields = fields;
    }

    public void addField(FieldInfo info) {
        fields.add(info);
    }

    public List<MethodInfo> getMethods() {
        return methods;
    }

    public void setMethods(List<MethodInfo> methods) {
        this.methods = methods;
    }

    public void addMethod(MethodInfo info) {
        methods.add(info);
    }

    public List<AttributeInfo> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AttributeInfo> attributes) {
        this.attributes = attributes;
    }

    public void addAttribute(AttributeInfo info) {
        attributes.add(info);
    }

    @Override
    public String toString() {
        return "ClassFile{" +
                "magic=" + Arrays.toString(magic) +
                ", minor_version=" + minor_version +
                ", major_version=" + major_version +
                ", constant_pool_count=" + constant_pool_count +
                ", constant_pool=" + constant_pool +
                ", access_flags=" + access_flags +
                ", this_class=" + this_class +
                ", super_class=" + super_class +
                ", interfaces_count=" + interfaces_count +
                ", interfaces=" + interfaces +
                ", fields_count=" + fields_count +
                ", fields=" + fields +
                ", methods_count=" + methods_count +
                ", methods=" + methods +
                ", attributes_count=" + attributes_count +
                ", attributes=" + attributes +
                '}';
    }
}
