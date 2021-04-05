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

public class EvConstValue implements ElementValue {
    private int const_value_index;

    public int getConst_value_index() {
        return const_value_index;
    }

    public void setConst_value_index(int const_value_index) {
        this.const_value_index = const_value_index;
    }

    @Override
    public String toString() {
        return "EvConstValue{" +
                "const_value_index=" + const_value_index +
                '}';
    }
}
