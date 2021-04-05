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

public class AttrBootstrapMethods extends AttributeInfo {

    public AttrBootstrapMethods(int attribute_name_index, int attribute_length) {
        super(attribute_name_index, attribute_length);
    }

    private int num_bootstrap_methods;

    private List<BootstrapMethod> bootstrap_methods = new ArrayList<>();

    public int getNum_bootstrap_methods() {
        return num_bootstrap_methods;
    }

    public void setNum_bootstrap_methods(int num_bootstrap_methods) {
        this.num_bootstrap_methods = num_bootstrap_methods;
    }

    public List<BootstrapMethod> getBootstrap_methods() {
        return bootstrap_methods;
    }

    public void setBootstrap_methods(List<BootstrapMethod> bootstrap_methods) {
        this.bootstrap_methods = bootstrap_methods;
    }

    @Override
    public String toString() {
        return "AttrBootstrapMethods{" +
                "num_bootstrap_methods=" + num_bootstrap_methods +
                ", bootstrap_methods=" + bootstrap_methods +
                ", attribute_name_index=" + attribute_name_index +
                ", attribute_length=" + attribute_length +
                '}';
    }
}
