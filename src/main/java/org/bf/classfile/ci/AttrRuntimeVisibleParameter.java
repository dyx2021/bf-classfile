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

public class AttrRuntimeVisibleParameter extends AttributeInfo {

    public AttrRuntimeVisibleParameter(int attribute_name_index, int attribute_length) {
        super(attribute_name_index, attribute_length);
    }

    private int num_parameters;
    private List<EvParameterAnnotation> parameter_annotations = new ArrayList<>();

    public int getNum_parameters() {
        return num_parameters;
    }

    public void setNum_parameters(int num_parameters) {
        this.num_parameters = num_parameters;
    }

    public List<EvParameterAnnotation> getParameter_annotations() {
        return parameter_annotations;
    }

    public void setParameter_annotations(List<EvParameterAnnotation> parameter_annotations) {
        this.parameter_annotations = parameter_annotations;
    }

    @Override
    public String toString() {
        return "AttrRuntimeVisibleParameter{" +
                "num_parameters=" + num_parameters +
                ", parameter_annotations=" + parameter_annotations +
                ", attribute_name_index=" + attribute_name_index +
                ", attribute_length=" + attribute_length +
                '}';
    }
}
