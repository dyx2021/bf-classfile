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

import java.util.ArrayList;
import java.util.List;

public class FmSameLocals1StackItem extends StackMapFrame {
    public FmSameLocals1StackItem(int frame_type, FrameTypeEnum type) {
        super(frame_type, type);
    }
    private List<VerificationTypeInfo> stack = new ArrayList<>(1);

    public List<VerificationTypeInfo> getStack() {
        return stack;
    }

    public void setStack(List<VerificationTypeInfo> stack) {
        this.stack = stack;
    }

    @Override
    public String toString() {
        return "FmSameLocals1StackItem{" +
                "stack=" + stack +
                ", type=" + type +
                ", frame_type=" + frame_type +
                '}';
    }
}
