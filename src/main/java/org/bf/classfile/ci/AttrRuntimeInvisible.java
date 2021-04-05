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

public class AttrRuntimeInvisible extends AttributeInfo {

    public AttrRuntimeInvisible(int attribute_name_index, int attribute_length) {
        super(attribute_name_index, attribute_length);
    }

    private int num_annotations;
    private List<EvAnnotation> annotations = new ArrayList<>();

    public int getNum_annotations() {
        return num_annotations;
    }

    public void setNum_annotations(int num_annotations) {
        this.num_annotations = num_annotations;
    }

    public List<EvAnnotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<EvAnnotation> annotations) {
        this.annotations = annotations;
    }

    @Override
    public String toString() {
        return "AttrRuntimeInvisible{" +
                "num_annotations=" + num_annotations +
                ", annotations=" + annotations +
                ", attribute_name_index=" + attribute_name_index +
                ", attribute_length=" + attribute_length +
                '}';
    }
}
