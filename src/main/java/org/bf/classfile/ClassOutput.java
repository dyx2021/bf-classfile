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
package org.bf.classfile;

import org.bf.classfile.ci.*;
import org.bf.classfile.parser.CodeParser;
import org.bf.classfile.op.Instruction;
import org.bf.classfile.parser.ClassFileParser;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ClassOutput {
    private ClassFile cf;

    public ClassOutput(String file) {
        ClassFileParser parser = new ClassFileParser();
        try {
            cf = parser.from(Files.readAllBytes(Paths.get(file)));
        } catch (Exception e) {
            throw new RuntimeException("parse file failed", e);
        }
    }

    public void printAll() {
        CpClass cpClass = cf.getConstant(cf.getThis_class());
        CpUtf8Info className = cf.getConstant(cpClass.getName_index());
        System.out.println(className.getValue() + " has " + cf.getFields_count() + " fields, has " + cf.getMethods_count() + " methods.");
        System.out.println();
        System.out.println("fields:");
        int i = 1;
        for (FieldInfo fi : cf.getFields()) {
            CpUtf8Info nameInfo = cf.getConstant(fi.getName_index());
            CpUtf8Info descInfo = cf.getConstant(fi.getDescriptor_index());
            System.out.println(i + " " + nameInfo.getValue() + " " + descInfo.getValue());
            System.out.println();
            i++;
        }
        System.out.println("methods:");
        i = 1;
        for (MethodInfo methodInfo : cf.getMethods()) {
            CpUtf8Info nameInfo = cf.getConstant(methodInfo.getName_index());
            CpUtf8Info descInfo = cf.getConstant(methodInfo.getDescriptor_index());
            System.out.println(i + " " + nameInfo.getValue() + " " + descInfo.getValue());
            for (AttributeInfo ai : methodInfo.getAttributes()) {
                if (ai instanceof AttrCode) {
                    AttrCode ac = (AttrCode) ai;
                    printCode(ac);
                }
            }
            System.out.println();
            i++;
        }
    }

    private void printCode(AttrCode ac) {
        List<Instruction> is = new CodeParser(ac.getCode()).getCodes();
        System.out.println(is.size() + " codes");
        for (int i = 0; i < is.size(); i++) {
            System.out.println("\t" + is.get(i).toSimpleName());
        }
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println();
            System.out.println("please input java class file path");
            return;
        }
        ClassOutput out = new ClassOutput(args[0].trim());
        out.printAll();
    }
}
