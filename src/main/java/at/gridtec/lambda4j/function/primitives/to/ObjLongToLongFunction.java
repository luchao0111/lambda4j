/*
 * Copyright (c) 2015 Gridtec. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package at.gridtec.lambda4j.function.primitives.to;

import java.util.function.BiFunction;

/**
 * Represents a function that accepts an object-valued and a {@code long}-valued argument, and produces a {@code
 * long}-valued result. This is the {@code (reference, long)}, {@code long}-producing primitive specialization for
 * {@link BiFunction}.
 * <p/>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsInt(Object, long)}.
 *
 * @param <T> The type of argument to the function
 * @see java.util.function.BiFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ObjLongToLongFunction<T> {

    /**
     * Applies this {@link ObjLongToLongFunction} to the given arguments.
     *
     * @param t The first argument to the function
     * @param value The second argument to the function
     * @return The return value from the function, which is its result.
     */
    long applyAsInt(T t, long value);
}
