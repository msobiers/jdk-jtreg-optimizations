/*
 * Copyright (c) 2022, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package jdk.internal.classfile.constantpool;

import java.lang.constant.ConstantDesc;
import java.lang.constant.DynamicCallSiteDesc;

import jdk.internal.classfile.impl.AbstractPoolEntry;
import jdk.internal.classfile.impl.Util;

/**
 * Models a constant pool entry for a dynamic call site.
 * @jvms 4.4.10 The CONSTANT_Dynamic_info and CONSTANT_InvokeDynamic_info Structures
 */
public sealed interface InvokeDynamicEntry
        extends DynamicConstantPoolEntry
        permits AbstractPoolEntry.InvokeDynamicEntryImpl {

    /**
     * {@return a symbolic descriptor for the dynamic call site}
     */
    default DynamicCallSiteDesc asSymbol() {
        return DynamicCallSiteDesc.of(bootstrap().bootstrapMethod().asSymbol(),
                                      name().stringValue(),
                                      Util.methodTypeSymbol(nameAndType()),
                                      bootstrap().arguments().stream()
                                                 .map(LoadableConstantEntry::constantValue)
                                                 .toArray(ConstantDesc[]::new));
    }
}
