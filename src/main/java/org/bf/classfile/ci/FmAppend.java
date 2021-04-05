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

public class FmAppend extends StackMapFrame {
    public FmAppend(int frame_type, FrameTypeEnum type) {
        super(frame_type, type);
    }

    private int offset_delta;
    private List<VerificationTypeInfo> locals = new ArrayList<>();

    public int getOffset_delta() {
        return offset_delta;
    }

    public void setOffset_delta(int offset_delta) {
        this.offset_delta = offset_delta;
    }

    public List<VerificationTypeInfo> getLocals() {
        return locals;
    }

    public void setLocals(List<VerificationTypeInfo> locals) {
        this.locals = locals;
    }

    @Override
    public String toString() {
        return "FmAppend{" +
                "offset_delta=" + offset_delta +
                ", locals=" + locals +
                ", type=" + type +
                ", frame_type=" + frame_type +
                '}';
    }
}
