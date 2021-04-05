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

public class FmFull extends StackMapFrame {
    public FmFull(int frame_type, FrameTypeEnum type) {
        super(frame_type, type);
    }

    private int offset_delta;
    private int number_of_locals;
    private List<VerificationTypeInfo> locals = new ArrayList<>();
    private int number_of_stack_items;
    private List<VerificationTypeInfo> stack = new ArrayList<>();

    public int getOffset_delta() {
        return offset_delta;
    }

    public void setOffset_delta(int offset_delta) {
        this.offset_delta = offset_delta;
    }

    public int getNumber_of_locals() {
        return number_of_locals;
    }

    public void setNumber_of_locals(int number_of_locals) {
        this.number_of_locals = number_of_locals;
    }

    public List<VerificationTypeInfo> getLocals() {
        return locals;
    }

    public void setLocals(List<VerificationTypeInfo> locals) {
        this.locals = locals;
    }

    public int getNumber_of_stack_items() {
        return number_of_stack_items;
    }

    public void setNumber_of_stack_items(int number_of_stack_items) {
        this.number_of_stack_items = number_of_stack_items;
    }

    public List<VerificationTypeInfo> getStack() {
        return stack;
    }

    public void setStack(List<VerificationTypeInfo> stack) {
        this.stack = stack;
    }

    @Override
    public String toString() {
        return "FmFull{" +
                "offset_delta=" + offset_delta +
                ", number_of_locals=" + number_of_locals +
                ", locals=" + locals +
                ", number_of_stack_items=" + number_of_stack_items +
                ", stack=" + stack +
                ", type=" + type +
                ", frame_type=" + frame_type +
                '}';
    }
}
