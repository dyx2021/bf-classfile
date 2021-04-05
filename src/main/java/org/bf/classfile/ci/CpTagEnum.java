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

import java.util.HashMap;
import java.util.Map;

public enum CpTagEnum {
    CONSTANT_Class(7),
    CONSTANT_Fieldref(9),
    CONSTANT_Methodref(10),
    CONSTANT_InterfaceMethodref(11),
    CONSTANT_String(8),
    CONSTANT_Integer(3),
    CONSTANT_Float(4),
    CONSTANT_Long(5),
    CONSTANT_Double(6),
    CONSTANT_NameAndType(12),
    CONSTANT_Utf8(1),
    CONSTANT_MethodHandle(15),
    CONSTANT_MethodType(16),
    CONSTANT_InvokeDynamic(18);
    private int tag;
    private static final Map<Integer, CpTagEnum> map = new HashMap<>(23);

    static {
        for (CpTagEnum e : values()) {
            map.put(e.tag, e);
        }
    }

    CpTagEnum(int tag) {
        this.tag = tag;
    }

    public int getTag() {
        return tag;
    }

    public static CpTagEnum of(int tag) {
        return map.get(tag);
    }

    @Override
    public String toString() {
        return "CpTagEnum{" +
                "tag=" + tag +
                '}';
    }
}
