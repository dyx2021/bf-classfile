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

public class TpSupertypeTarget implements TypeInfo{
    private int supertype_index;

    public int getSupertype_index() {
        return supertype_index;
    }

    public void setSupertype_index(int supertype_index) {
        this.supertype_index = supertype_index;
    }

    @Override
    public String toString() {
        return "TpSupertypeTarget{" +
                "supertype_index=" + supertype_index +
                '}';
    }
}
