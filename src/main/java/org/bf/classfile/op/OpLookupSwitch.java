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

public class OpLookupSwitch extends Instruction {

    public OpLookupSwitch(OpInfo opInfo) {
        super(opInfo);
    }

    private int pad;
    private int defOffset;
    private int nPairs;
    private List<Integer> pairs = new ArrayList<>();

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

    public int getnPairs() {
        return nPairs;
    }

    public void setnPairs(int nPairs) {
        this.nPairs = nPairs;
    }

    public List<Integer> getPairs() {
        return pairs;
    }

    public void setPairs(List<Integer> pairs) {
        this.pairs = pairs;
    }

    @Override
    public String toSimpleName() {
        return getPrefix() + " default = " + defOffset + " nPairs = " + nPairs + " pairs = " + pairs;
    }

    @Override
    public String toString() {
        return "OpLookupSwitch{" +
                "pad=" + pad +
                ", defOffset=" + defOffset +
                ", nPairs=" + nPairs +
                ", pairs=" + pairs +
                ", opInfo=" + opInfo +
                ", pos=" + pos +
                '}';
    }
}
