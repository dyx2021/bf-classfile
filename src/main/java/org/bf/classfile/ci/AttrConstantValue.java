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

public class AttrConstantValue extends AttributeInfo {
    private int constantvalue_index;

    public AttrConstantValue(int attribute_name_index, int attribute_length) {
        super(attribute_name_index, attribute_length);
    }

    public int getConstantvalue_index() {
        return constantvalue_index;
    }

    public void setConstantvalue_index(int constantvalue_index) {
        this.constantvalue_index = constantvalue_index;
    }

    @Override
    public String toString() {
        return "AttrConstantValue{" +
                "constantvalue_index=" + constantvalue_index +
                ", attribute_name_index=" + attribute_name_index +
                ", attribute_length=" + attribute_length +
                '}';
    }
}
