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

public class TpCatchTarget implements TypeInfo{
    private int exception_table_index;

    public int getException_table_index() {
        return exception_table_index;
    }

    public void setException_table_index(int exception_table_index) {
        this.exception_table_index = exception_table_index;
    }

    @Override
    public String toString() {
        return "TpCatchTarget{" +
                "exception_table_index=" + exception_table_index +
                '}';
    }
}
