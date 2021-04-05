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

public enum FrameTypeEnum {
    SAME, SAME_LOCALS_1_STACK_ITEM, SAME_LOCALS_1_STACK_ITEM_EXTENDED,
    CHOP, SAME_FRAME_EXTENDED, APPEND, FULL_FRAME;

    private static final int[] FRAME_ARR = {0, 63, 64, 127, 247, 247, 248, 250, 251, 251, 252, 254, 255, 255};

    public static FrameTypeEnum of(int n) {
        for (int i = 0; i < FRAME_ARR.length / 2; i++) {
            if (n >= FRAME_ARR[i * 2] && n <= FRAME_ARR[i * 2 + 1]) {
                return values()[i];
            }
        }
        return null;
    }
}
