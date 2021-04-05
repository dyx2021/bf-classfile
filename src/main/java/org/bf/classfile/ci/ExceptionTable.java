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

public class ExceptionTable {
    private int start_pc;
    private int end_pc;
    private int handler_pc;
    private int catch_type;

    public int getStart_pc() {
        return start_pc;
    }

    public void setStart_pc(int start_pc) {
        this.start_pc = start_pc;
    }

    public int getEnd_pc() {
        return end_pc;
    }

    public void setEnd_pc(int end_pc) {
        this.end_pc = end_pc;
    }

    public int getHandler_pc() {
        return handler_pc;
    }

    public void setHandler_pc(int handler_pc) {
        this.handler_pc = handler_pc;
    }

    public int getCatch_type() {
        return catch_type;
    }

    public void setCatch_type(int catch_type) {
        this.catch_type = catch_type;
    }

    @Override
    public String toString() {
        return "ExceptionTable{" +
                "start_pc=" + start_pc +
                ", end_pc=" + end_pc +
                ", handler_pc=" + handler_pc +
                ", catch_type=" + catch_type +
                '}';
    }
}
