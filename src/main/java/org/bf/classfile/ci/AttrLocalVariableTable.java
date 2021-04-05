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

public class AttrLocalVariableTable extends AttributeInfo {

    public AttrLocalVariableTable(int attribute_name_index, int attribute_length) {
        super(attribute_name_index, attribute_length);
    }
    private int local_variable_table_length;
    private List<LocalVariableTableInfo> local_variable_table = new ArrayList<>();

    public int getLocal_variable_table_length() {
        return local_variable_table_length;
    }

    public void setLocal_variable_table_length(int local_variable_table_length) {
        this.local_variable_table_length = local_variable_table_length;
    }

    public List<LocalVariableTableInfo> getLocal_variable_table() {
        return local_variable_table;
    }

    public void setLocal_variable_table(List<LocalVariableTableInfo> local_variable_table) {
        this.local_variable_table = local_variable_table;
    }

    @Override
    public String toString() {
        return "AttrLocalVariableTable{" +
                "local_variable_table_length=" + local_variable_table_length +
                ", local_variable_table=" + local_variable_table +
                ", attribute_name_index=" + attribute_name_index +
                ", attribute_length=" + attribute_length +
                '}';
    }
}
