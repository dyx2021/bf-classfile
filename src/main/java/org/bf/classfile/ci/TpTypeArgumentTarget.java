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

public class TpTypeArgumentTarget implements TypeInfo{
    private int offset;
    private int type_argument_index;

    public int getType_argument_index() {
        return type_argument_index;
    }

    public void setType_argument_index(int type_argument_index) {
        this.type_argument_index = type_argument_index;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "TpTypeArgumentTarget{" +
                "offset=" + offset +
                ", type_argument_index=" + type_argument_index +
                '}';
    }
}
