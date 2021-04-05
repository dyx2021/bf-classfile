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

public class OpWide extends Instruction {

    public OpWide(OpInfo opInfo) {
        super(opInfo);
    }

    private Instruction code;
    private int index;
    private Integer constant;

    public Instruction getCode() {
        return code;
    }

    public void setCode(Instruction code) {
        this.code = code;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Integer getConstant() {
        return constant;
    }

    public void setConstant(Integer constant) {
        this.constant = constant;
    }

    @Override
    public String toSimpleName() {
        return getPrefix() + " #" + index + (constant == null ? "" : " " + constant);
    }

    @Override
    public String toString() {
        return "OpWide{" +
                "code=" + code +
                ", index=" + index +
                ", constant=" + constant +
                ", opInfo=" + opInfo +
                ", pos=" + pos +
                '}';
    }
}
