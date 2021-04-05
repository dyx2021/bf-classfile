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

public class Opb_sb extends Instruction {

    public Opb_sb(OpInfo opInfo) {
        super(opInfo);
    }

    private int index;

    private int constValue;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getConstValue() {
        return constValue;
    }

    public void setConstValue(int constValue) {
        this.constValue = constValue;
    }

    @Override
    public String toSimpleName() {
        return getPrefix() + " #" + index + " " + constValue;
    }

    @Override
    public String toString() {
        return "Opb_sb{" +
                "index=" + index +
                ", constValue=" + constValue +
                ", opInfo=" + opInfo +
                ", pos=" + pos +
                '}';
    }
}
