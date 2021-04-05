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
package org.bf.classfile.parser;

import org.bf.classfile.op.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CodeParser {
    private int[] codes;
    private List<Instruction> rst;
    private int pos;

    public CodeParser(int[] codes) {
        this.codes = codes;
        this.rst = new ArrayList<>();
        this.pos = 0;
        init();
    }

    private int readByte() {
        if (pos > codes.length - 1) {
            throw new RuntimeException("pos exceed code bound " + pos);
        }
        return 0xff & codes[pos++];
    }

    private int readSByte() {
        int byte1 = readByte();
        byte n = (byte) byte1;
        return n;
    }

    private void skip(int n) {
        pos += n;
    }

    private int readS2Byte() {
        short byte1 = (short) readByte();
        short byte2 = (short) readByte();
        short n = (short) (byte1 << 8);
        n += byte2;
        return n;
    }

    private int read2Byte() {
        int byte1 = readByte();
        int byte2 = readByte();
        return (byte1 << 8) | byte2;
    }

    private int read4Byte() {
        int byte1 = readByte();
        int byte2 = readByte();
        int byte3 = readByte();
        int byte4 = readByte();
        return (byte1 << 24) | (byte2 << 16) | (byte3 << 8) | byte4;
    }

    private <T extends Instruction> T toInst(Instruction a) {
        return (T) a;
    }

    private Instruction parseOp() {
        int opCode = readByte();
        Instruction op = InstructionSet.getInstruction(opCode);
        op.setPos(this.pos - 1);
        if (op instanceof OpEmpty) {
        } else if (op instanceof OpReserved) {
        } else if (op instanceof OpUnknown) {
        } else if (op instanceof Opb_si) {
            Opb_si tmp = toInst(op);
            tmp.setValue(readSByte());
        } else if (op instanceof Op2b_si) {
            Op2b_si tmp = toInst(op);
            tmp.setValue(readS2Byte());
        } else if (op instanceof Op4b_si) {
            Op4b_si tmp = toInst(op);
            tmp.setValue(read4Byte());
        } else if (op instanceof Opb) {
            Opb tmp = toInst(op);
            tmp.setIndex(readByte());
        } else if (op instanceof Op2b) {
            Op2b tmp = toInst(op);
            tmp.setIndex(read2Byte());
        } else if (op instanceof Opb_sb) {
            Opb_sb tmp = toInst(op);
            tmp.setIndex(readByte());
            tmp.setConstValue(readSByte());
        } else if (op instanceof OpTableSwitch) {
            OpTableSwitch tmp = toInst(op);
            readTableSwitch(tmp);
        } else if (op instanceof OpLookupSwitch) {
            OpLookupSwitch tmp = toInst(op);
            readLookupSwitch(tmp);
        } else if (op instanceof OpInvokeInterface) {
            OpInvokeInterface tmp = toInst(op);
            tmp.setIndex(read2Byte());
            tmp.setCount(readByte());
            if (readByte() != 0) {
                throw new RuntimeException("illegal opCode, InvokeInterface not ends with 0");
            }
        } else if (op instanceof OpInvokeDynamic) {
            OpInvokeDynamic tmp = toInst(op);
            tmp.setIndex(read2Byte());
            for (int j = 0; j < 2; j++) {
                if (readByte() != 0) {
                    throw new RuntimeException("illegal opCode, InvokeDynamic not ends with 0");
                }
            }
        } else if (op instanceof OpWide) {
            OpWide tmp = toInst(op);
            readWide(tmp);
        } else if (op instanceof OpMultiNewArray) {
            OpMultiNewArray tmp = toInst(op);
            tmp.setIndex(read2Byte());
            tmp.setDimensions(readByte());
        } else {
            throw new RuntimeException("illegal opCode : " + opCode);
        }
        return op;
    }

    private void init() {
        int len = codes.length;
        while (pos < len) {
            this.rst.add(parseOp());
        }
    }

    private int calcPad() {
        return 4 - this.pos % 4;
    }

    private void readTableSwitch(OpTableSwitch tmp) {
        int pad = calcPad();
        tmp.setPad(pad);
        skip(pad);

        tmp.setDefOffset(read4Byte());
        tmp.setLow(read4Byte());
        tmp.setHigh(read4Byte());
        int len = tmp.getHigh() - tmp.getLow() + 1;
        for (int i = 0; i < len; i++) {
            tmp.getOffsets().add(read4Byte());
        }
    }

    private void readLookupSwitch(OpLookupSwitch tmp) {
        int pad = calcPad();
        tmp.setPad(pad);
        skip(pad);

        tmp.setDefOffset(read4Byte());
        tmp.setnPairs(read4Byte());
        int len = tmp.getnPairs();
        for (int i = 0; i < len; i++) {
            tmp.getPairs().add(read4Byte());
        }
    }

    private final static List<String> WIDE_NAMES;

    static {
        String arr[] = {"iload", "fload", "aload", "lload", "dload", "istore", "fstore", "astore", "lstore", "dstore", "ret"};
        WIDE_NAMES = Arrays.asList(arr);
    }

    private void readWide(OpWide tmp) {
        Instruction op = parseOp();
        String name = op.getPrefix();
        tmp.setCode(op);
        if (name.equals("iinc")) {
            tmp.setIndex(read2Byte());
            tmp.setConstant(readS2Byte());
        } else if (WIDE_NAMES.contains(name)) {
            tmp.setIndex(read2Byte());
        } else {
            throw new RuntimeException("wide opcode not ends with legal instruction, but " + op.getPrefix());
        }
    }

    public List<Instruction> getCodes() {
        return rst;
    }
}
