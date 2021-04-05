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

public class EvEnumConstValue implements ElementValue {
    private int type_name_index;
    private int const_name_index;

    public int getType_name_index() {
        return type_name_index;
    }

    public void setType_name_index(int type_name_index) {
        this.type_name_index = type_name_index;
    }

    public int getConst_name_index() {
        return const_name_index;
    }

    public void setConst_name_index(int const_name_index) {
        this.const_name_index = const_name_index;
    }

    @Override
    public String toString() {
        return "EvEnumConstValue{" +
                "type_name_index=" + type_name_index +
                ", const_name_index=" + const_name_index +
                '}';
    }
}
