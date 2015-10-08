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
package at.gridtec.lambda4j.function.primitives.bi;

import at.gridtec.lambda4j.consumer.primitives.bi.DoubleBiConsumer;

import java.util.Objects;
import java.util.function.*;

/**
 * Represents a function that accepts two double-valued arguments and produces a result. This is the {@code
 * double}-consuming primitive specialization for {@link BiFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #apply(double, double)}.
 *
 * @param <R> The type of return value from the function
 * @see java.util.function.BiFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface DoubleBiFunction<R> {

    /**
     * Creates a {@link DoubleBiFunction} which always returns a given value.
     *
     * @param <R> The type of return value from the function
     * @param r The return value for the constant
     * @return A {@code DoubleBiFunction} which always returns a given value.
     */
    static <R> DoubleBiFunction<R> constant(R r) {
        return (value1, value2) -> r;
    }

    /**
     * Creates a {@link DoubleBiFunction} which uses the first parameter of this one as argument for the given {@link
     * DoubleFunction}.
     *
     * @param <R> The return value from the operation
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code DoubleBiFunction} which uses the first parameter of this one as argument for the given
     * {@code DoubleFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <R> DoubleBiFunction<R> onlyFirst(final DoubleFunction<R> function) {
        Objects.requireNonNull(function);
        return (value1, value2) -> function.apply(value1);
    }

    /**
     * Creates a {@link DoubleBiFunction} which uses the second parameter of this one as argument for the given {@link
     * DoubleFunction}.
     *
     * @param <R> The return value from the operation
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code DoubleBiFunction} which uses the second parameter of this one as argument for the given
     * {@code DoubleFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <R> DoubleBiFunction<R> onlySecond(final DoubleFunction<R> function) {
        Objects.requireNonNull(function);
        return (value1, value2) -> function.apply(value2);
    }

    /**
     * Applies this {@link DoubleBiFunction} to the given argument.
     *
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The return value from the function, which is its result.
     */
    R apply(double value1, double value2);

    /**
     * Returns a composed {@link DoubleBiFunction} that first applies the {@code before} {@link DoubleUnaryOperator}s to
     * its input, and then applies this operation to the result. If evaluation of either operation throws an exception,
     * it is relayed to the caller of the composed function.
     *
     * @param before1 The first {@code DoubleUnaryOperator} to apply before this operation is applied
     * @param before2 The second {@code DoubleUnaryOperator} to apply before this operation is applied
     * @return A composed {@code DoubleBiFunction} that first applies the {@code before} {@code DoubleBiUnaryOperator}s
     * to its input, and then applies this operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(Function)
     */
    default DoubleBiFunction<R> compose(final DoubleUnaryOperator before1, final DoubleUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> apply(before1.applyAsDouble(value1), before2.applyAsDouble(value2));
    }

    /**
     * Returns a composed {@link BiFunction} that applies the given {@code before} {@link ToDoubleFunction}s to its
     * input, and then applies this operation to the result. If evaluation of either operation throws an exception, it
     * is relayed to the caller of the composed function.
     *
     * @param <T> The type of the argument to the first before operation
     * @param <V> The type of the argument to the second before operation
     * @param before1 The first before {@code ToDoubleFunction} to apply before this operation is applied
     * @param before2 The second before {@code ToDoubleFunction} to apply before this operation is applied
     * @return A composed {@code BiFunction} that applies the given {@code before} {@code ToDoubleFunction}s to its
     * input, and then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(Function)
     */
    default <T, V> BiFunction<T, V, R> compose(final ToDoubleFunction<? super T> before1,
            final ToDoubleFunction<? super V> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> apply(before1.applyAsDouble(value1), before2.applyAsDouble(value2));
    }

    /**
     * Returns a composed {@link DoubleBiFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <S> The type of output of the {@code after} function, and of the composed function
     * @param after The {@code DoubleBiFunction} to apply after this operation is applied
     * @return A composed {@code DoubleBiFunction} that first applies this operation, and then applies the {@code after}
     * operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(DoubleUnaryOperator, DoubleUnaryOperator)
     * @see #compose(ToDoubleFunction, ToDoubleFunction)
     */
    default <S> DoubleBiFunction<S> andThen(final Function<? super R, ? extends S> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.apply(apply(value1, value2));
    }

    /**
     * Returns a composed {@link DoubleBiConsumer} that fist applies this operation to its input, and then consumes the
     * result using the given {@code Consumer}. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param consumer The {@code Consumer} which consumes the result from this operation
     * @return A composed {@code DoubleBiConsumer} that first applies this operation to its input, and then consumes the
     * result using the given {@code Consumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    default DoubleBiConsumer consume(Consumer<? super R> consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> consumer.accept(this.apply(value1, value2));
    }

    /**
     * Returns a composed {@link BiFunction} which represents this {@link DoubleBiFunction}. Thereby the primitive input
     * argument for this operation is autoboxed. This method is just convenience to provide the ability to use this
     * {@code DoubleBiFunction} with JRE specific methods, only accepting {@code BiFunction}.
     *
     * @return A composed {@code BiFunction} which represents this {@code DoubleBiFunction}.
     */
    default BiFunction<Double, Double, R> boxed() {
        return this::apply;
    }
}