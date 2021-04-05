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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AttrCode extends AttributeInfo {
    private int max_stack;
    private int max_locals;
    private int code_length;
    private int[] code;
    private int exception_table_length;
    private List<ExceptionTable> exception_table = new ArrayList<>();
    private int attributes_count;
    private List<AttributeInfo> attributes = new ArrayList<>();

    public AttrCode(int attribute_name_index, int attribute_length) {
        super(attribute_name_index, attribute_length);
    }

    public int getMax_stack() {
        return max_stack;
    }

    public void setMax_stack(int max_stack) {
        this.max_stack = max_stack;
    }

    public int getMax_locals() {
        return max_locals;
    }

    public void setMax_locals(int max_locals) {
        this.max_locals = max_locals;
    }

    public int[] getCode() {
        return code;
    }

    public void setCode(int[] code) {
        this.code = code;
    }

    public int getCode_length() {
        return code_length;
    }

    public void setCode_length(int code_length) {
        this.code_length = code_length;
    }

    public int getException_table_length() {
        return exception_table_length;
    }

    public void setException_table_length(int exception_table_length) {
        this.exception_table_length = exception_table_length;
    }

    public List<ExceptionTable> getException_table() {
        return exception_table;
    }

    public void addExceptionTable(ExceptionTable et) {
        this.exception_table.add(et);
    }

    public void setException_table(List<ExceptionTable> exception_table) {
        this.exception_table = exception_table;
    }

    public int getAttributes_count() {
        return attributes_count;
    }

    public void setAttributes_count(int attributes_count) {
        this.attributes_count = attributes_count;
    }

    public List<AttributeInfo> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AttributeInfo> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "AttrCode{" +
                "max_stack=" + max_stack +
                ", max_locals=" + max_locals +
                ", code_length=" + code_length +
                ", code=" + Arrays.toString(code) +
                ", exception_table_length=" + exception_table_length +
                ", exception_table=" + exception_table +
                ", attributes_count=" + attributes_count +
                ", attributes=" + attributes +
                ", attribute_name_index=" + attribute_name_index +
                ", attribute_length=" + attribute_length +
                '}';
    }
}
