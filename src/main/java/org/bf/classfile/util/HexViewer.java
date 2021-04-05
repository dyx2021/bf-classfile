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

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class HexViewer {
    private BytesReader in = null;

    private boolean loaded = false;

    public void load(String file) {
        try {
            in = new BytesReader(Files.readAllBytes(Paths.get(file)));
            loaded = true;
        } catch (Exception e) {
            e.printStackTrace();
            loaded = false;
            throw new RuntimeException("read file failed " + file);
        }
    }

    private boolean checkNotLoaded() {
        if (!loaded) {
            println("please load a file first");
        }
        return !loaded;
    }

    private void println(Object s) {
        System.out.println(s);
    }

    private void println() {
        System.out.println();
    }

    private void print(Object s) {
        System.out.print(s);
    }

    private int parseInt(String args[], int i, int def) {
        if (args == null || args.length <= i) {
            return def;
        }
        try {
            return Integer.parseInt(args[i]);
        } catch (Exception e) {
            throw new RuntimeException("parse int failed", e);
        }
    }

    private String parseString(String args[], int i, String def) {
        if (args == null || args.length <= i) {
            return def;
        }
        return args[i];
    }

    public static final int MAX_INTS[] = {9, 99, 999, 9999, 99999, 999999, 9999999, 99999999, 999999999, Integer.MAX_VALUE};

    public int calcIntWidth(int n) {
        int i = 0;
        while (n > MAX_INTS[i]) {
            i++;
        }
        return i + 1;
    }

    public String toHex(int i) {
        return align(Integer.toHexString(i).toUpperCase(), 2, '0', true);
    }

    public String align(String s, int paddingLen, char padding, boolean isLeft) {
        if (s == null || s.length() >= paddingLen) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < paddingLen - s.length(); i++) {
            sb.append(padding);
        }
        if (isLeft) {
            return sb.toString() + s;
        } else {
            return s + sb.toString();
        }
    }

    public void showString(String args[]) {
        int p = 0;
        int from = parseInt(args, p++, 0);
        int len = parseInt(args, p++, 16);
        String charset = parseString(args, p++, null);
        if (charset == null || charset.isEmpty()) {
            println(in.pos(from).readString(len));
        } else {
            println(in.pos(from).readString(len, charset));
        }
    }

    public void showInt(String args[]) {
        if (checkNotLoaded()) {
            return;
        }
        int p = 0;
        int from = parseInt(args, p++, 0);
        int len = parseInt(args, p++, 4);
        println(in.pos(from).readInt(len));
    }

    public void showUtf8(String args[]) {
        if (checkNotLoaded()) {
            return;
        }
        int p = 0;
        int from = parseInt(args, p++, 0);
        int len = parseInt(args, p++, 4);
        println(in.pos(from).readJvmModifiedUtf8(len));
    }

    public void showHex(String args[]) {
        if (checkNotLoaded()) {
            return;
        }
        int p = 0;
        int from = parseInt(args, p++, 0);
        int len = parseInt(args, p++, 16);
        final int LINE_WIDTH = 16;
        int bs[] = in.pos(from).readBytes(len);
        int lineCount = len / LINE_WIDTH;
        int lineCountAppend = (len % LINE_WIDTH > 0) ? 1 : 0;
        int lineAlignWidth = calcIntWidth(lineCount + lineCountAppend);
        int lineNo = 0;
        for (; lineNo < lineCount; lineNo++) {
            print(align(String.valueOf(lineNo + 1), lineAlignWidth, ' ', false));
            print(" | ");
            int i = 0;
            for (; i < LINE_WIDTH / 2; i++) {
                print(toHex(bs[lineNo * LINE_WIDTH + i]));
                print(" ");
            }
            print(" ");
            for (; i < LINE_WIDTH; i++) {
                print(toHex(bs[lineNo * LINE_WIDTH + i]));
                print(" ");
            }
            println();
        }
        if (lineCountAppend > 0) {
            print(align(String.valueOf(lineNo + 1), lineAlignWidth, ' ', false));
            print(" | ");
            int base = lineCount * LINE_WIDTH;
            int i = base;
            for (; i < base + LINE_WIDTH / 2 && i < len; i++) {
                print(toHex(bs[i]));
                print(" ");
            }
            print(" ");
            for (; i < base + LINE_WIDTH && i < len; i++) {
                print(toHex(bs[i]));
                print(" ");
            }
            println();
        }
    }

    public static void main(String[] args) {
        //D:\src\jp\out\production\jp\com\dyx\jp\BytesReader.class
        Scanner scanner = new Scanner(System.in);
        String line = null;
        HexViewer viewer = new HexViewer();
        viewer.load("D:\\src\\jp\\out\\production\\jp\\com\\dyx\\jp\\BytesReader.class");
        //viewer.load("C:\\Users\\xk\\Desktop\\CPU.txt");
        while ((line = scanner.nextLine()) != null) {
            if (line.equals("exit")) {
                viewer.println("bye");
                break;
            } else {
                String cmdArr[] = line.split(" ", 2);
                if (cmdArr == null || cmdArr.length != 2) {
                    viewer.println("please input legal command");
                    continue;
                }
                String cmd = cmdArr[0];
                String[] as = cmdArr[1].split(" ");
                if (cmd.equals("load")) {
                    viewer.load(cmdArr[1]);
                } else if (cmd.equals("h")) {
                    viewer.showHex(as);
                } else if (cmd.equals("s")) {
                    viewer.showString(as);
                } else if (cmd.equals("u")) {
                    viewer.showUtf8(as);
                } else if (cmd.equals("i")) {
                    viewer.showInt(as);
                } else {
                    viewer.println("please input legal command");
                }
            }
        }
    }

}
