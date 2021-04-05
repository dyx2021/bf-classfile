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

public class TpTypeParameterBoundTarget implements TypeInfo{
    private int type_parameter_index;
    private int bound_index;

    public int getType_parameter_index() {
        return type_parameter_index;
    }

    public void setType_parameter_index(int type_parameter_index) {
        this.type_parameter_index = type_parameter_index;
    }

    public int getBound_index() {
        return bound_index;
    }

    public void setBound_index(int bound_index) {
        this.bound_index = bound_index;
    }

    @Override
    public String toString() {
        return "TpTypeParameterBoundTarget{" +
                "type_parameter_index=" + type_parameter_index +
                ", bound_index=" + bound_index +
                '}';
    }
}
