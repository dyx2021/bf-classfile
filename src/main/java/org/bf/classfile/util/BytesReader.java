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
package org.bf.classfile.util;

import java.util.ArrayList;
import java.util.List;

public class BytesReader {
    private byte[] bs;
    private int pos;

    public BytesReader(byte[] bs) {
        this.bs = bs;
        this.pos = 0;
    }

    public BytesReader pos(int pos) {
        this.pos = pos;
        return this;
    }

    public String readString(int len) {
        return readString(len, null);
    }

    public String readString(int len, String charset) {
        try {
            String rst = charset == null ? new String(bs, pos, len) : new String(bs, pos, len, charset);
            pos += len;
            return rst;
        } catch (Exception e) {
            throw new RuntimeException("illegal charset " + charset);
        }
    }

    public void skip(int len) {
        pos += len;
    }

    public int read() {
        if (pos >= bs.length) {
            throw new RuntimeException(pos + " pos exceed bounds");
        }
        return 0xff & bs[pos++];
    }

    public int[] readBytes(int len) {
        int[] rst = new int[len];
        for (int i = 0; i < len; i++) {
            rst[i] = read();
        }
        return rst;
    }

    private static final int INT_BITS[] = {0, 8, 16, 24, 32, 40, 48, 56};

    public int readShort() {
        return readInt(2);
    }

    public int readInt() {
        return readInt(4);
    }

    public int readInt(int len) {
        int rst = 0;
        for (int i = 0; i < len; i++) {
            rst += read() << INT_BITS[len - i - 1];
        }
        return rst;
    }

    public long readLong() {
        int high_bytes = readInt();
        int low_bytes = readInt();
        return ((long) high_bytes << 32) + low_bytes;
    }

    public float readFloat() {
        int bits = readInt(4);
        if (bits == 0x7f800000) {
            return Float.POSITIVE_INFINITY;
        } else if (bits == 0xff800000) {
            return Float.NEGATIVE_INFINITY;
        } else if ((bits >= 0x7f800001 && bits <= 0x7fffffff) || (bits >= 0xff800001 && bits <= 0xffffffff)) {
            return Float.NaN;
        } else {
            int s = ((bits >> 31) == 0) ? 1 : -1;
            int e = ((bits >> 23) & 0xff);
            int m = (e == 0) ?
                    (bits & 0x7fffff) << 1 :
                    (bits & 0x7fffff) | 0x800000;
            return (float) (s * m * Math.pow(2 * e, -150));
        }
    }

    public double readDouble() {
        long bits = readLong();
        if (bits == 0x7ff0000000000000L) {
            return Double.POSITIVE_INFINITY;
        } else if (bits == 0xfff0000000000000L) {
            return Double.NEGATIVE_INFINITY;
        } else if ((bits >= 0x7ff0000000000001L && bits <= 0x7fffffffffffffffL) || (bits >= 0xfff0000000000001L && bits <= 0xffffffffffffffffL)) {
            return Double.NaN;
        } else {
            int s = ((bits >> 63) == 0) ? 1 : -1;
            int e = (int) ((bits >> 52) & 0x7ffL);
            long m = (e == 0) ?
                    (bits & 0xfffffffffffffL) << 1 :
                    (bits & 0xfffffffffffffL) | 0x10000000000000L;
            return s * m * Math.pow(2 * e, -150);
        }
    }

    public String readJvmModifiedUtf8(int len) {
        List<Integer> codePoints = new ArrayList<>(len / 2);
        for (int cur = this.pos; this.pos - cur < len; ) {
            int x = read();
            if (x >>> 7 == 0) {
            } else if (x >>> 5 == 6) {
                int y = read();
                x = ((x & 0x1f) << 6) + (y & 0x3f);
            } else if (x >>> 4 == 14) {
                if (x == 237) {
                    int v = read();
                    int w = read();
                    read();
                    int y = read();
                    int z = read();
                    x = 0x10000 + ((v & 0x0f) << 16) + ((w & 0x3f) << 10) +
                            ((y & 0x0f) << 6) + (z & 0x3f);
                } else {
                    int y = read();
                    int z = read();
                    x = ((x & 0xf) << 12) + ((y & 0x3f) << 6) + (z & 0x3f);
                }
            } else {
                throw new RuntimeException("illegal utf8 string");
            }
            codePoints.add(x);
        }
        int arr[] = new int[codePoints.size()];
        for (int i = 0; i < codePoints.size(); i++) {
            arr[i] = codePoints.get(i);
        }
        return new String(arr, 0, arr.length);
    }

    public int getPos() {
        return pos;
    }
}
