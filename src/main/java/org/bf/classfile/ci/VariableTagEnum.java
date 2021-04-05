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

public enum VariableTagEnum {
    ITEM_Top, ITEM_Integer, ITEM_Float, ITEM_Double, ITEM_Long,
    ITEM_Null, ITEM_UninitializedThis, ITEM_Object, ITEM_Uninitialized;

    public static VariableTagEnum of(int n) {
        return values()[n];
    }
}
