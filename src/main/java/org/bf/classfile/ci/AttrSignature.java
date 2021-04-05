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

public class AttrSignature extends AttributeInfo {

    public AttrSignature(int attribute_name_index, int attribute_length) {
        super(attribute_name_index, attribute_length);
    }

    private int signature_index;

    public int getSignature_index() {
        return signature_index;
    }

    public void setSignature_index(int signature_index) {
        this.signature_index = signature_index;
    }

    @Override
    public String toString() {
        return "AttrSignature{" +
                "signature_index=" + signature_index +
                ", attribute_name_index=" + attribute_name_index +
                ", attribute_length=" + attribute_length +
                '}';
    }
}
