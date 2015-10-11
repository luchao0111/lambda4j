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
package at.gridtec.lambda4j.function.primitives.tri;

import at.gridtec.lambda4j.consumer.primitives.tri.CharTriConsumer;
import at.gridtec.lambda4j.function.TriFunction;
import at.gridtec.lambda4j.function.primitives.CharFunction;
import at.gridtec.lambda4j.function.primitives.to.ToCharFunction;
import at.gridtec.lambda4j.operators.ternary.CharTernaryOperator;
import at.gridtec.lambda4j.operators.unary.CharUnaryOperator;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Represents a function that accepts three char-valued argument and produces a result. This is the {@code
 * char}-consuming primitive specialization for {@link TriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #apply(char, char, char)}.
 *
 * @param <R> The type of return value from the function
 * @see TriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface CharTriFunction<R> {

    /**
     * Creates a {@link CharTriFunction} which always returns a given value.
     *
     * @param <R> The type of return value from the function
     * @param r The return value for the constant
     * @return A {@code CharTriFunction} which always returns a given value.
     */
    static <R> CharTriFunction<R> constant(R r) {
        return (value1, value2, value3) -> r;
    }

    /**
     * Creates a {@link CharTriFunction} which uses the {@code first} parameter of this one as argument for the given
     * {@link CharFunction}.
     *
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code CharTriFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@code CharFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <R> CharTriFunction<R> onlyFirst(final CharFunction<? extends R> function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.apply(value1);
    }

    /**
     * Creates a {@link CharTriFunction} which uses the {@code second} parameter of this one as argument for the given
     * {@link CharFunction}.
     *
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code CharTriFunction} which uses the {@code second} parameter of this one as argument for the
     * given {@code CharFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <R> CharTriFunction<R> onlySecond(final CharFunction<? extends R> function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.apply(value2);
    }

    /**
     * Creates a {@link CharTriFunction} which uses the {@code third} parameter of this one as argument for the given
     * {@link CharFunction}.
     *
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code third} parameter of this one
     * @return Creates a {@code CharTriFunction} which uses the {@code third} parameter of this one as argument for the
     * given {@code CharFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <R> CharTriFunction<R> onlyThird(final CharFunction<? extends R> function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.apply(value3);
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The return value from the function, which is its result.
     */
    R apply(char value1, char value2, char value3);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     */
    default int arity() {
        return 3;
    }

    /**
     * Returns a composed {@link CharTriFunction} that first applies the {@code before} {@link CharUnaryOperator}s to
     * its input, and then applies this operation to the result. If evaluation of either operation throws an exception,
     * it is relayed to the caller of the composed function.
     *
     * @param before1 The first {@code CharUnaryOperator} to apply before this operation is applied
     * @param before2 The second {@code CharUnaryOperator} to apply before this operation is applied
     * @param before3 The third {@code CharUnaryOperator} to apply before this operation is applied
     * @return A composed {@code CharTriFunction} that first applies the {@code before} {@code CharUnaryOperator}s to
     * its input, and then applies this operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(Function)
     */
    default CharTriFunction<R> compose(final CharUnaryOperator before1, final CharUnaryOperator before2,
            final CharUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2, value3) -> apply(before1.applyAsChar(value1), before2.applyAsChar(value2),
                                                 before3.applyAsChar(value3));
    }

    /**
     * Returns a composed {@link TriFunction} that applies the given {@code before} {@link ToCharFunction}s to its
     * input, and then applies this operation to the result. If evaluation of either operation throws an exception, it
     * is relayed to the caller of the composed function.
     *
     * @param <T> The type of the argument to the first before operation
     * @param <U> The type of the argument to the second before operation
     * @param <V> The type of the argument to the third before operation
     * @param before1 The first before {@code ToCharFunction} to apply before this operation is applied
     * @param before2 The second before {@code ToCharFunction} to apply before this operation is applied
     * @param before3 The third before {@code ToCharFunction} to apply before this operation is applied
     * @return A composed {@code TriFunction} that applies the given {@code before} {@code ToCharFunction}s to its
     * input, and then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(Function)
     */
    default <T, U, V> TriFunction<T, U, V, R> compose(final ToCharFunction<? super T> before1,
            final ToCharFunction<? super U> before2, final ToCharFunction<? super V> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2, value3) -> apply(before1.applyAsChar(value1), before2.applyAsChar(value2),
                                                 before3.applyAsChar(value3));
    }

    /**
     * Returns a composed {@link CharTernaryOperator} that first applies this operation to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param after The {@code ToCharFunction} to apply after this operation is applied
     * @return A composed {@code CharTernaryOperator} that first applies this operation, and then applies the {@code
     * after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(CharUnaryOperator, CharUnaryOperator, CharUnaryOperator)
     * @see #compose(ToCharFunction, ToCharFunction, ToCharFunction)
     */
    default CharTernaryOperator andThen(final ToCharFunction<? super R> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsChar(apply(value1, value2, value3));
    }

    /**
     * Returns a composed {@link CharTriFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <S> The type of output of the {@code after} function, and of the composed function
     * @param after The {@code CharTriFunction} to apply after this operation is applied
     * @return A composed {@code CharTriFunction} that first applies this operation, and then applies the {@code after}
     * operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(CharUnaryOperator, CharUnaryOperator, CharUnaryOperator)
     * @see #compose(ToCharFunction, ToCharFunction, ToCharFunction)
     */
    default <S> CharTriFunction<S> andThen(final Function<? super R, ? extends S> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.apply(apply(value1, value2, value3));
    }

    /**
     * Returns a composed {@link CharTriConsumer} that fist applies this operation to its input, and then consumes the
     * result using the given {@code Consumer}. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param consumer The {@code Consumer} which consumes the result from this operation
     * @return A composed {@code CharTriConsumer} that first applies this operation to its input, and then consumes the
     * result using the given {@code Consumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    default CharTriConsumer consume(Consumer<? super R> consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.accept(this.apply(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriFunction} which represents this {@link CharTriFunction}. Thereby the primitive input
     * argument for this operation is autoboxed.
     *
     * @return A composed {@code TriFunction} which represents this {@code CharTriFunction}.
     */
    default TriFunction<Character, Character, Character, R> boxed() {
        return this::apply;
    }
}
