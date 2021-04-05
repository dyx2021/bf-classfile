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

public class OpBuilder {
    private OpInfo opInfo;

    public OpBuilder(OpInfo opInfo) {
        this.opInfo = opInfo;
    }

    public Instruction build() {
        String type = opInfo.getType();
        switch (type) {
            case "e": {
                return new OpEmpty(opInfo);
            }
            case "b_si": {
                return new Opb_si(opInfo);
            }
            case "2b_si": {
                return new Op2b_si(opInfo);
            }
            case "4b_si": {
                return new Op4b_si(opInfo);
            }
            case "b": {
                return new Opb(opInfo);
            }
            case "2b": {
                return new Op2b(opInfo);
            }
            case "b_sb": {
                return new Opb_sb(opInfo);
            }
            case "ts": {
                return new OpTableSwitch(opInfo);
            }
            case "ls": {
                return new OpLookupSwitch(opInfo);
            }
            case "ivi": {
                return new OpInvokeInterface(opInfo);
            }
            case "ivd": {
                return new OpInvokeDynamic(opInfo);
            }
            case "wide": {
                return new OpWide(opInfo);
            }
            case "multi": {
                return new OpMultiNewArray(opInfo);
            }
            case "r": {
                return new OpReserved(opInfo);
            }
            case "u": {
                return new OpUnknown(opInfo);
            }
            default:
                throw new RuntimeException("illegal op type : " + type);
        }
    }
}
