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

public class CpMethodHandleInfo extends CpInfo {
    private int reference_kind; // 1~9
    private int reference_index; // constant_pool entry

    public int getReference_kind() {
        return reference_kind;
    }

    public void setReference_kind(int reference_kind) {
        this.reference_kind = reference_kind;
    }

    public int getReference_index() {
        return reference_index;
    }

    public void setReference_index(int reference_index) {
        this.reference_index = reference_index;
    }

    @Override
    public String toString() {
        return "CpMethodHandleInfo{" +
                "reference_kind=" + reference_kind +
                ", reference_index=" + reference_index +
                ", tag=" + tag +
                '}';
    }
}
