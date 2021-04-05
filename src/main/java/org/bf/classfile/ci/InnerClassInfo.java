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

public class InnerClassInfo {
    private int inner_class_info_index;
    private int outer_class_info_index;
    private int inner_name_index;
    private int inner_class_access_flags;

    public int getInner_class_info_index() {
        return inner_class_info_index;
    }

    public void setInner_class_info_index(int inner_class_info_index) {
        this.inner_class_info_index = inner_class_info_index;
    }

    public int getOuter_class_info_index() {
        return outer_class_info_index;
    }

    public void setOuter_class_info_index(int outer_class_info_index) {
        this.outer_class_info_index = outer_class_info_index;
    }

    public int getInner_name_index() {
        return inner_name_index;
    }

    public void setInner_name_index(int inner_name_index) {
        this.inner_name_index = inner_name_index;
    }

    public int getInner_class_access_flags() {
        return inner_class_access_flags;
    }

    public void setInner_class_access_flags(int inner_class_access_flags) {
        this.inner_class_access_flags = inner_class_access_flags;
    }

    @Override
    public String toString() {
        return "InnerClassInfo{" +
                "inner_class_info_index=" + inner_class_info_index +
                ", outer_class_info_index=" + outer_class_info_index +
                ", inner_name_index=" + inner_name_index +
                ", inner_class_access_flags=" + inner_class_access_flags +
                '}';
    }
}
