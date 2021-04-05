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

import java.util.Arrays;

public class AttrExceptions extends AttributeInfo {

    public AttrExceptions(int attribute_name_index, int attribute_length) {
        super(attribute_name_index, attribute_length);
    }
    private int attribute_name_index;
    private int attribute_length;
    private int number_of_exceptions;
    private int[] exception_index_table;

    @Override
    public int getLine_number_table_length() {
        return attribute_name_index;
    }

    @Override
    public void setLine_number_table_length(int line_number_table_length) {
        this.attribute_name_index = line_number_table_length;
    }

    @Override
    public int getAttribute_length() {
        return attribute_length;
    }

    @Override
    public void setAttribute_length(int attribute_length) {
        this.attribute_length = attribute_length;
    }

    public int getNumber_of_exceptions() {
        return number_of_exceptions;
    }

    public void setNumber_of_exceptions(int number_of_exceptions) {
        this.number_of_exceptions = number_of_exceptions;
    }

    public int[] getException_index_table() {
        return exception_index_table;
    }

    public void setException_index_table(int[] exception_index_table) {
        this.exception_index_table = exception_index_table;
    }

    @Override
    public String toString() {
        return "AttrExceptions{" +
                "attribute_name_index=" + attribute_name_index +
                ", attribute_length=" + attribute_length +
                ", number_of_exceptions=" + number_of_exceptions +
                ", exception_index_table=" + Arrays.toString(exception_index_table) +
                ", attribute_name_index=" + attribute_name_index +
                ", attribute_length=" + attribute_length +
                '}';
    }
}
