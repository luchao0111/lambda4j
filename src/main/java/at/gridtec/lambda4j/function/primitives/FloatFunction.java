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
package at.gridtec.lambda4j.function.primitives;

import at.gridtec.lambda4j.consumer.primitives.FloatConsumer;
import at.gridtec.lambda4j.function.primitives.conversion.FloatToBooleanFunction;
import at.gridtec.lambda4j.function.primitives.conversion.FloatToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.FloatToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.FloatToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.FloatToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.FloatToLongFunction;
import at.gridtec.lambda4j.function.primitives.conversion.FloatToShortFunction;
import at.gridtec.lambda4j.function.primitives.to.ToByteFunction;
import at.gridtec.lambda4j.function.primitives.to.ToCharFunction;
import at.gridtec.lambda4j.function.primitives.to.ToFloatFunction;
import at.gridtec.lambda4j.function.primitives.to.ToShortFunction;
import at.gridtec.lambda4j.operators.unary.FloatUnaryOperator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/**
 * Represents a function that accepts a float-valued argument and produces a result. This is the {@code float}-consuming
 * primitive specialization for {@link Function}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #apply(float)}.
 *
 * @param <R> The type of return value from the function
 * @see java.util.function.Function
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface FloatFunction<R> {

    /**
     * Creates a {@link FloatFunction} which always returns a given value.
     *
     * @param <R> The type of return value from the function
     * @param r The return value for the constant
     * @return A {@code FloatFunction} which always returns a given value.
     */
    @Nonnull
    static <R> FloatFunction<R> constant(R r) {
        return value -> r;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param value The argument to the function
     * @return The return value from the function, which is its result.
     */
    R apply(float value);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     */
    @Nonnegative
    default int arity() {
        return 1;
    }

    /**
     * Returns a composed {@link FloatFunction} that first applies the {@code before} {@link FloatUnaryOperator} to its
     * input, and then applies this operation to the result. If evaluation of either operation throws an exception, it
     * is relayed to the caller of the composed function.
     *
     * @param before The {@code FloatUnaryOperator} to apply before this operation is applied
     * @return A composed {@code FloatFunction} that first applies the {@code before} {@code FloatUnaryOperator} to its
     * input, and then applies this operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(Function)
     */
    default FloatFunction<R> compose(final FloatUnaryOperator before) {
        Objects.requireNonNull(before);
        return value -> apply(before.applyAsFloat(value));
    }

    /**
     * Returns a composed {@link Function} that applies the given {@code before} {@link ToFloatFunction} to its input,
     * and then applies this operation to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed function.
     *
     * @param <T> The type of the argument to the before operation
     * @param before The before {@code ToFloatFunction} to apply before this operation is applied
     * @return A composed {@code Function} that applies the given {@code before} {@code ToFloatFunction} to its input,
     * and then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(Function)
     */
    default <T> Function<T, R> compose(final ToFloatFunction<? super T> before) {
        Objects.requireNonNull(before);
        return value -> apply(before.applyAsFloat(value));
    }

    /**
     * Returns a composed {@link FloatUnaryOperator} that first applies this operation to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param after The {@code ToFloatFunction} to apply after this operation is applied
     * @return A composed {@code FloatUnaryOperator} that first applies this operation, and then applies the {@code
     * after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(FloatUnaryOperator)
     * @see #compose(ToFloatFunction)
     */
    default FloatUnaryOperator andThen(final ToFloatFunction<? super R> after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsFloat(apply(value));
    }

    /**
     * Returns a composed {@link FloatFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <S> The type of output of the {@code after} function, and of the composed function
     * @param after The {@code FloatFunction} to apply after this operation is applied
     * @return A composed {@code FloatFunction} that first applies this operation, and then applies the {@code after}
     * operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(FloatUnaryOperator)
     * @see #compose(ToFloatFunction)
     */
    default <S> FloatFunction<S> andThen(final Function<? super R, ? extends S> after) {
        Objects.requireNonNull(after);
        return value -> after.apply(apply(value));
    }

    /**
     * Returns a composed {@link FloatToBooleanFunction} that first applies this operation to its input, and then
     * applies the {@code after} operation to the result. If evaluation of either operations throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param after The {@code Predicate} to apply after this operation is applied
     * @return A composed {@code FloatToBooleanFunction} that first applies this operation to its input, and then
     * applies the {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default FloatToBooleanFunction toBoolean(final Predicate<? super R> after) {
        Objects.requireNonNull(after);
        return value -> after.test(apply(value));
    }

    /**
     * Returns a composed {@link FloatToByteFunction} that first applies this operation to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param after The {@code ToByteFunction} to apply after this operation is applied
     * @return A composed {@code FloatToByteFunction} that first applies this operation to its input, and then applies
     * the {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default FloatToByteFunction toByte(final ToByteFunction<? super R> after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsByte(apply(value));
    }

    /**
     * Returns a composed {@link FloatToCharFunction} that first applies this operation to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param after The {@code ToCharFunction} to apply after this operation is applied
     * @return A composed {@code FloatToCharFunction} that first applies this operation to its input, and then applies
     * the {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default FloatToCharFunction toChar(final ToCharFunction<? super R> after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsChar(apply(value));
    }

    /**
     * Returns a composed {@link FloatToDoubleFunction} that first applies this operation to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param after The {@code ToDoubleFunction} to apply after this operation is applied
     * @return A composed {@code FloatToDoubleFunction} that first applies this operation to its input, and then applies
     * the {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default FloatToDoubleFunction toDouble(final ToDoubleFunction<? super R> after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsDouble(apply(value));
    }

    /**
     * Returns a composed {@link FloatToIntFunction} that first applies this operation to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param after The {@code ToIntFunction} to apply after this operation is applied
     * @return A composed {@code FloatToIntFunction} that first applies this operation to its input, and then applies
     * the {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default FloatToIntFunction toInt(final ToIntFunction<? super R> after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsInt(apply(value));
    }

    /**
     * Returns a composed {@link FloatToLongFunction} that first applies this operation to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param after The {@code ToLongFunction} to apply after this operation is applied
     * @return A composed {@code FloatToLongFunction} that first applies this operation to its input, and then applies
     * the {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default FloatToLongFunction toLong(final ToLongFunction<? super R> after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsLong(apply(value));
    }

    /**
     * Returns a composed {@link FloatToShortFunction} that first applies this operation to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param after The {@code ToShortFunction} to apply after this operation is applied
     * @return A composed {@code FloatToShortFunction} that first applies this operation to its input, and then applies
     * the {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default FloatToShortFunction toShort(final ToShortFunction<? super R> after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsShort(apply(value));
    }

    /**
     * Returns a composed {@link FloatConsumer} that fist applies this operation to its input, and then consumes the
     * result using the given {@link Consumer}. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code FloatConsumer} that first applies this operation to its input, and then consumes the
     * result using the given {@code Consumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default FloatConsumer consume(@Nonnull final Consumer<? super R> consumer) {
        Objects.requireNonNull(consumer);
        return value -> consumer.accept(this.apply(value));
    }

    /**
     * Returns a composed {@link Function} which represents this {@link FloatFunction}. Thereby the primitive input
     * argument for this function is autoboxed. This method is just convenience to provide the ability to use this
     * {@code FloatFunction} with JRE specific methods, only accepting {@code Function}.
     *
     * @return A composed {@code Function} which represents this {@code FloatFunction}.
     */
    @Nonnull
    default Function<Float, R> boxed() {
        return this::apply;
    }
}