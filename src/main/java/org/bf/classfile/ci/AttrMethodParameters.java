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

public class AttrMethodParameters extends AttributeInfo {

    public AttrMethodParameters(int attribute_name_index, int attribute_length) {
        super(attribute_name_index, attribute_length);
    }
    private int parameters_count;
    private List<ParameterInfo> parameters = new ArrayList<>();

    public int getParameters_count() {
        return parameters_count;
    }

    public void setParameters_count(int parameters_count) {
        this.parameters_count = parameters_count;
    }

    public List<ParameterInfo> getParameters() {
        return parameters;
    }

    public void setParameters(List<ParameterInfo> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "AttrMethodParameters{" +
                "parameters_count=" + parameters_count +
                ", parameters=" + parameters +
                ", attribute_name_index=" + attribute_name_index +
                ", attribute_length=" + attribute_length +
                '}';
    }
}
