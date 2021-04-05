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

public class AttrInnerClasses extends AttributeInfo {

    public AttrInnerClasses(int attribute_name_index, int attribute_length) {
        super(attribute_name_index, attribute_length);
    }
    private int number_of_classes;

    private List<InnerClassInfo> classes = new ArrayList<>();

    public int getNumber_of_classes() {
        return number_of_classes;
    }

    public void setNumber_of_classes(int number_of_classes) {
        this.number_of_classes = number_of_classes;
    }

    public List<InnerClassInfo> getClasses() {
        return classes;
    }

    public void setClasses(List<InnerClassInfo> classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return "AttrInnerClasses{" +
                "number_of_classes=" + number_of_classes +
                ", classes=" + classes +
                ", attribute_name_index=" + attribute_name_index +
                ", attribute_length=" + attribute_length +
                '}';
    }
}
