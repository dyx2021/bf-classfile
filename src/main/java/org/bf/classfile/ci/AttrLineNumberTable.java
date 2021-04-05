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
import java.util.List;

public class AttrLineNumberTable extends AttributeInfo {

    public AttrLineNumberTable(int attribute_name_index, int attribute_length) {
        super(attribute_name_index, attribute_length);
    }
    private int line_number_table_length;

    private List<LineNumberInfo> line_number_table = new ArrayList<>();

    @Override
    public int getLine_number_table_length() {
        return line_number_table_length;
    }

    @Override
    public void setLine_number_table_length(int line_number_table_length) {
        this.line_number_table_length = line_number_table_length;
    }

    public List<LineNumberInfo> getLine_number_table() {
        return line_number_table;
    }

    public void setLine_number_table(List<LineNumberInfo> line_number_table) {
        this.line_number_table = line_number_table;
    }

    @Override
    public String toString() {
        return "AttrLineNumberTable{" +
                "line_number_table_length=" + line_number_table_length +
                ", line_number_table=" + line_number_table +
                ", attribute_name_index=" + attribute_name_index +
                ", attribute_length=" + attribute_length +
                '}';
    }
}
