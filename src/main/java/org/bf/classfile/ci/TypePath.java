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

import java.util.ArrayList;
import java.util.List;

public class TypePath {
    private int path_length;
    private List<TypePathInfo> path = new ArrayList<>();

    public int getPath_length() {
        return path_length;
    }

    public void setPath_length(int path_length) {
        this.path_length = path_length;
    }

    public List<TypePathInfo> getPath() {
        return path;
    }

    public void setPath(List<TypePathInfo> path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "TypePath{" +
                "path_length=" + path_length +
                ", path=" + path +
                '}';
    }
}
