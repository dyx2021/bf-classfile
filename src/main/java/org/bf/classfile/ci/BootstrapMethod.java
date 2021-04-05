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

import java.util.Arrays;

public class BootstrapMethod {
    private int bootstrap_method_ref;
    private int num_bootstrap_arguments;
    private int[] bootstrap_arguments;

    public int getBootstrap_method_ref() {
        return bootstrap_method_ref;
    }

    public void setBootstrap_method_ref(int bootstrap_method_ref) {
        this.bootstrap_method_ref = bootstrap_method_ref;
    }

    public int getNum_bootstrap_arguments() {
        return num_bootstrap_arguments;
    }

    public void setNum_bootstrap_arguments(int num_bootstrap_arguments) {
        this.num_bootstrap_arguments = num_bootstrap_arguments;
    }

    public int[] getBootstrap_arguments() {
        return bootstrap_arguments;
    }

    public void setBootstrap_arguments(int[] bootstrap_arguments) {
        this.bootstrap_arguments = bootstrap_arguments;
    }

    @Override
    public String toString() {
        return "BootstrapMethod{" +
                "bootstrap_method_ref=" + bootstrap_method_ref +
                ", num_bootstrap_arguments=" + num_bootstrap_arguments +
                ", bootstrap_arguments=" + Arrays.toString(bootstrap_arguments) +
                '}';
    }
}
