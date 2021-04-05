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

public class EvClassInfo implements ElementValue {
    private int class_info_index;

    public int getClass_info_index() {
        return class_info_index;
    }

    public void setClass_info_index(int class_info_index) {
        this.class_info_index = class_info_index;
    }

    @Override
    public String toString() {
        return "EvClassInfo{" +
                "class_info_index=" + class_info_index +
                '}';
    }
}
