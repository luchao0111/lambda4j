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

import at.gridtec.lambda4j.consumer.primitives.obj.ObjShortConsumer;
import at.gridtec.lambda4j.function.primitives.ShortFunction;
import at.gridtec.lambda4j.function.primitives.to.ToShortFunction;
import at.gridtec.lambda4j.operators.binary.ShortBinaryOperator;
import at.gridtec.lambda4j.operators.unary.ShortUnaryOperator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Represents a function that accepts an object-valued and a {@code short}-valued argument, and produces a result. This
 * is the {@code (reference, short)} specialization of {@link BiFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #apply(Object, short)}.
 *
 * @param <T> The type of argument to the function
 * @param <R> The type of return value from the function
 * @see java.util.function.BiFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ObjShortFunction<T, R> {

    /**
     * Creates a {@link ObjShortFunction} which always returns a given value.
     *
     * @param <T> The type of argument to the function
     * @param <R> The type of return value from the function
     * @param r The return value for the constant
     * @return A {@code ObjShortFunction} which always returns a given value.
     */
    @Nonnull
    static <T, R> ObjShortFunction<T, R> constant(R r) {
        return (t, value) -> r;
    }

    /**
     * Creates a {@link ObjShortFunction} which uses the {@code first} parameter of this one as argument for the given
     * {@link Function}.
     *
     * @param <T> The type of argument to the function
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ObjShortFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@code Function}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, R> ObjShortFunction<T, R> onlyFirst(@Nonnull final Function<? super T, ? extends R> function) {
        Objects.requireNonNull(function);
        return (t, value) -> function.apply(t);
    }

    /**
     * Creates a {@link ObjShortFunction} which uses the {@code second} parameter of this one as argument for the given
     * {@link ShortFunction}.
     *
     * @param <T> The type of argument to the function
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ObjShortFunction} which uses the {@code second} parameter of this one as argument for
     * the given {@code ShortFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, R> ObjShortFunction<T, R> onlySecond(@Nonnull final ShortFunction<? extends R> function) {
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
    R apply(T t, short value);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     */
    @Nonnegative
    default int arity() {
        return 2;
    }

    /**
     * Returns a composed {@link ObjShortFunction} that first applies the {@code before} functions to its input, and
     * then applies this operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed function.
     *
     * @param <U> The type of the argument to the first before operation
     * @param before1 The first {@code Function} to apply before this operation is applied
     * @param before2 The second {@code ShortUnaryOperator} to apply before this operation is applied
     * @return A composed {@code ObjShortFunction} that first applies the {@code before} functions to its input, and
     * then applies this operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(Function)
     */
    default <U> ObjShortFunction<U, R> compose(final Function<? super U, ? extends T> before1,
            final ShortUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (u, v) -> apply(before1.apply(u), before2.applyAsShort(v));
    }

    /**
     * Returns a composed {@link BiFunction} that applies the given {@code before} functions to its input, and then
     * applies this operation to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <U> The type of the argument to the first before operation
     * @param <V> The type of the argument to the second before operation
     * @param before1 The first before {@code Function} to apply before this operation is applied
     * @param before2 The second before {@code ToShortFunction} to apply before this operation is applied
     * @return A composed {@code BiFunction} that applies the given {@code before} functions to its input, and then
     * applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(Function)
     */
    default <U, V> BiFunction<U, V, R> compose(final Function<? super U, ? extends T> before1,
            final ToShortFunction<? super V> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (u, v) -> apply(before1.apply(u), before2.applyAsShort(v));
    }

    /**
     * Returns a composed {@link ShortBinaryOperator} that first applies this operation to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param after The {@code ToShortFunction} to apply after this operation is applied
     * @return A composed {@code ShortBinaryOperator} that first applies this operation, and then applies the {@code
     * after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(Function, ShortUnaryOperator)
     * @see #compose(Function, ToShortFunction)
     */
    default ToShortObjShortFunction<T> andThen(final ToShortFunction<? super R> after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsShort(apply(t, value));
    }

    /**
     * Returns a composed {@link ObjShortFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <S> The type of output of the {@code after} function, and of the composed function
     * @param after The {@code Function} to apply after this operation is applied
     * @return A composed {@code ObjShortFunction} that first applies this operation, and then applies the {@code after}
     * operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(Function, ShortUnaryOperator)
     * @see #compose(Function, ToShortFunction)
     */
    default <S> ObjShortFunction<T, S> andThen(final Function<? super R, ? extends S> after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.apply(apply(t, value));
    }

    /**
     * Returns a composed {@link ObjShortConsumer} that fist applies this operation to its input, and then consumes the
     * result using the given {@code Consumer}. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param consumer The {@code Consumer} which consumes the result from this operation
     * @return A composed {@code ObjShortConsumer} that first applies this operation to its input, and then consumes the
     * result using the given {@code Consumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ObjShortConsumer<T> consume(Consumer<? super R> consumer) {
        Objects.requireNonNull(consumer);
        return (t, value) -> consumer.accept(apply(t, value));
    }

    /**
     * Returns a composed {@link BiFunction} which represents this {@link ObjShortFunction}. Thereby the primitive input
     * argument for this function is autoboxed. This method is just convenience to provide the ability to use this
     * {@code ObjShortFunction} with JRE specific methods, only accepting {@code BiFunction}.
     *
     * @return A composed {@code BiFunction} which represents this {@code ObjShortFunction}.
     */
    @Nonnull
    default BiFunction<T, Short, R> boxed() {
        return this::apply;
    }
}
