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
package at.gridtec.lambda4j.function.primitives.obj;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.ObjIntConsumer;
import java.util.function.ToIntFunction;

/**
 * Represents a function that accepts an object-valued and a {@code int}-valued argument, and produces a result. This is
 * the {@code (reference, int)} specialization of {@link BiFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #apply(Object, int)}.
 *
 * @param <T> The type of argument to the function
 * @param <R> The type of return value from the function
 * @see java.util.function.BiFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ObjIntFunction<T, R> {

    /**
     * Creates a {@link ObjIntFunction} which always returns a given value.
     *
     * @param <T> The type of argument to the function
     * @param <R> The type of return value from the function
     * @param r The return value for the constant
     * @return A {@code ObjIntFunction} which always returns a given value.
     */
    static <T, R> ObjIntFunction<T, R> constant(R r) {
        return (t, value) -> r;
    }

    /**
     * Creates a {@link ObjIntFunction} which uses the {@code first} parameter of this one as argument for the given {@link
     * Function}.
     *
     * @param <T> The type of argument to the function
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ObjIntFunction} which uses the {@code first} parameter of this one as argument for the given
     * {@code Function}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T, R> ObjIntFunction<T, R> onlyFirst(final Function<? super T, ? extends R> function) {
        Objects.requireNonNull(function);
        return (t, value) -> function.apply(t);
    }

    /**
     * Creates a {@link ObjIntFunction} which uses the {@code second} parameter of this one as argument for the given {@link
     * IntFunction}.
     *
     * @param <T> The type of argument to the function
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ObjIntFunction} which uses the {@code second} parameter of this one as argument for the given
     * {@code IntFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T, R> ObjIntFunction<T, R> onlySecond(final IntFunction<? extends R> function) {
        Objects.requireNonNull(function);
        return (t, value) -> function.apply(value);
    }

    /**
     * Performs this function to the given arguments.
     *
     * @param t The first argument to the function
     * @param value The second argument to the function
     * @return The return value from the function, which is its result.
     */
    R apply(T t, int value);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     */
    default int arity() {
        return 2;
    }

    /**
     * Returns a composed {@link ObjIntFunction} that first applies the {@code before} functions to its input, and then
     * applies this operation to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <U> The type of the argument to the first before operation
     * @param before1 The first {@code Function} to apply before this operation is applied
     * @param before2 The second {@code IntUnaryOperator} to apply before this operation is applied
     * @return A composed {@code ObjIntFunction} that first applies the {@code before} functions to its input, and then
     * applies this operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(Function)
     */
    default <U> ObjIntFunction<U, R> compose(final Function<? super U, ? extends T> before1,
            final IntUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (u, v) -> apply(before1.apply(u), before2.applyAsInt(v));
    }

    /**
     * Returns a composed {@link BiFunction} that applies the given {@code before} functions to its input, and then
     * applies this operation to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <U> The type of the argument to the first before operation
     * @param <V> The type of the argument to the second before operation
     * @param before1 The first before {@code Function} to apply before this operation is applied
     * @param before2 The second before {@code ToIntFunction} to apply before this operation is applied
     * @return A composed {@code BiFunction} that applies the given {@code before} functions to its input, and then
     * applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(Function)
     */
    default <U, V> BiFunction<U, V, R> compose(final Function<? super U, ? extends T> before1,
            final ToIntFunction<? super V> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (u, v) -> apply(before1.apply(u), before2.applyAsInt(v));
    }

    /**
     * Returns a composed {@link IntBinaryOperator} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code ToIntFunction} to apply after this operation is applied
     * @return A composed {@code IntBinaryOperator} that first applies this operation, and then applies the {@code
     * after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(Function, IntUnaryOperator)
     * @see #compose(Function, ToIntFunction)
     */
    default ToIntObjIntFunction<T> andThen(final ToIntFunction<? super R> after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsInt(apply(t, value));
    }

    /**
     * Returns a composed {@link ObjIntFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <S> The type of output of the {@code after} function, and of the composed function
     * @param after The {@code Function} to apply after this operation is applied
     * @return A composed {@code ObjIntFunction} that first applies this operation, and then applies the {@code after}
     * operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(Function, IntUnaryOperator)
     * @see #compose(Function, ToIntFunction)
     */
    default <S> ObjIntFunction<T, S> andThen(final Function<? super R, ? extends S> after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.apply(apply(t, value));
    }

    /**
     * Returns a composed {@link ObjIntConsumer} that fist applies this operation to its input, and then consumes the
     * result using the given {@code Consumer}. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param consumer The {@code Consumer} which consumes the result from this operation
     * @return A composed {@code ObjIntConsumer} that first applies this operation to its input, and then consumes the
     * result using the given {@code Consumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ObjIntConsumer<T> consume(Consumer<? super R> consumer) {
        Objects.requireNonNull(consumer);
        return (t, value) -> consumer.accept(apply(t, value));
    }

    /**
     * Returns a composed {@link BiFunction} which represents this {@link ObjIntFunction}. Thereby the primitive input
     * argument for this operation is autoboxed. This method is just convenience to provide the ability to use this
     * {@code ObjIntFunction} with JRE specific methods, only accepting {@code BiFunction}.
     *
     * @return A composed {@code BiFunction} which represents this {@code ObjIntFunction}.
     */
    default BiFunction<T, Integer, R> boxed() {
        return this::apply;
    }
}
