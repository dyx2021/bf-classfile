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

public class AttrEnclosingMethod extends AttributeInfo {

    public AttrEnclosingMethod(int attribute_name_index, int attribute_length) {
        super(attribute_name_index, attribute_length);
    }
    private int class_index;
    private int method_index;

    public int getClass_index() {
        return class_index;
    }

    public void setClass_index(int class_index) {
        this.class_index = class_index;
    }

    public int getMethod_index() {
        return method_index;
    }

    @Override
    public String toString() {
        return "AttrEnclosingMethod{" +
                "class_index=" + class_index +
                ", method_index=" + method_index +
                ", attribute_name_index=" + attribute_name_index +
                ", attribute_length=" + attribute_length +
                '}';
    }

    public void setMethod_index(int method_index) {
        this.method_index = method_index;
    }
}
