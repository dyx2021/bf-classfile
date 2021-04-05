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

import java.util.ArrayList;
import java.util.List;

public class OpTableSwitch extends Instruction {

    public OpTableSwitch(OpInfo opInfo) {
        super(opInfo);
    }

    private int pad;
    private int defOffset;
    private int low;
    private int high;
    private List<Integer> offsets = new ArrayList<>();

    public int getPad() {
        return pad;
    }

    public void setPad(int pad) {
        this.pad = pad;
    }

    public int getDefOffset() {
        return defOffset;
    }

    public void setDefOffset(int defOffset) {
        this.defOffset = defOffset;
    }

    public int getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public List<Integer> getOffsets() {
        return offsets;
    }

    public void setOffsets(List<Integer> offsets) {
        this.offsets = offsets;
    }

    @Override
    public String toSimpleName() {
        return getPrefix() + " default = " + defOffset + " low = " + low + " high=" + high + " offsets=" + offsets;
    }

    @Override
    public String toString() {
        return "OpTableSwitch{" +
                "pad=" + pad +
                ", defOffset=" + defOffset +
                ", low=" + low +
                ", high=" + high +
                ", offsets=" + offsets +
                ", opInfo=" + opInfo +
                ", pos=" + pos +
                '}';
    }
}
