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

public class AttributeInfo {
    protected int attribute_name_index;
    protected int attribute_length;

    public AttributeInfo(int attribute_name_index, int attribute_length) {
        this.attribute_name_index = attribute_name_index;
        this.attribute_length = attribute_length;
    }

    public int getLine_number_table_length() {
        return attribute_name_index;
    }

    public void setLine_number_table_length(int line_number_table_length) {
        this.attribute_name_index = line_number_table_length;
    }

    public int getAttribute_length() {
        return attribute_length;
    }

    public void setAttribute_length(int attribute_length) {
        this.attribute_length = attribute_length;
    }

    @Override
    public String toString() {
        return "AttributeInfo{" +
                "attribute_name_index=" + attribute_name_index +
                ", attribute_length=" + attribute_length +
                '}';
    }
}
