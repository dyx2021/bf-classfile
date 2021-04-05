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

public class EvAnnotation implements ElementValue {
    private int type_index;
    private int num_element_value_pairs;
    private List<ElementValuePairsInfo> element_value_pairs = new ArrayList<>();

    public int getType_index() {
        return type_index;
    }

    public void setType_index(int type_index) {
        this.type_index = type_index;
    }

    public int getNum_element_value_pairs() {
        return num_element_value_pairs;
    }

    public void setNum_element_value_pairs(int num_element_value_pairs) {
        this.num_element_value_pairs = num_element_value_pairs;
    }

    public List<ElementValuePairsInfo> getElement_value_pairs() {
        return element_value_pairs;
    }

    public void setElement_value_pairs(List<ElementValuePairsInfo> element_value_pairs) {
        this.element_value_pairs = element_value_pairs;
    }

    @Override
    public String toString() {
        return "EvAnnotation{" +
                "type_index=" + type_index +
                ", num_element_value_pairs=" + num_element_value_pairs +
                ", element_value_pairs=" + element_value_pairs +
                '}';
    }
}
