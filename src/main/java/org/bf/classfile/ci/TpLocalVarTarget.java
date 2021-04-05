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

public class TpLocalVarTarget implements TypeInfo{
    private int table_length;
    private List<TpTableInfo> table = new ArrayList<>();

    public List<TpTableInfo> getTable() {
        return table;
    }

    public void setTable(List<TpTableInfo> table) {
        this.table = table;
    }

    public int getTable_length() {
        return table_length;
    }

    @Override
    public String toString() {
        return "TpLocalVarTarget{" +
                "table_length=" + table_length +
                ", table=" + table +
                '}';
    }

    public void setTable_length(int table_length) {
        this.table_length = table_length;
    }
}
