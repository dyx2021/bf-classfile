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
package org.bf.classfile.op;

public class OpInvokeInterface extends Instruction {

    public OpInvokeInterface(OpInfo opInfo) {
        super(opInfo);
    }

    private int index;
    private int count;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toSimpleName() {
        return getPrefix() + " #" + index + " " + count;
    }

    @Override
    public String toString() {
        return "OpInvokeInterface{" +
                "index=" + index +
                ", count=" + count +
                ", opInfo=" + opInfo +
                ", pos=" + pos +
                '}';
    }
}
