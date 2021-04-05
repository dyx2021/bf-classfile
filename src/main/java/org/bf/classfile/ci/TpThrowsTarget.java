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

public class TpThrowsTarget implements TypeInfo{
    private int throws_type_index;

    public int getThrows_type_index() {
        return throws_type_index;
    }

    public void setThrows_type_index(int throws_type_index) {
        this.throws_type_index = throws_type_index;
    }

    @Override
    public String toString() {
        return "TpThrowsTarget{" +
                "throws_type_index=" + throws_type_index +
                '}';
    }
}
