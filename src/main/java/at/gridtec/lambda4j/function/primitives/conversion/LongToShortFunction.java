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
package at.gridtec.lambda4j.function.primitives.conversion;

import at.gridtec.lambda4j.consumer.primitives.ShortConsumer;
import at.gridtec.lambda4j.function.primitives.ShortFunction;
import at.gridtec.lambda4j.function.primitives.to.ToShortFunction;
import at.gridtec.lambda4j.operators.unary.ShortUnaryOperator;
import at.gridtec.lambda4j.supplier.ShortSupplier;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.ToLongFunction;
import java.util.function.UnaryOperator;

/**
 * Represents a function that accepts a long-valued argument and produces a short-valued result. This is the {@code
 * long}-to-{@code short} primitive specialization for {@link Function}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsShort(long)}.
 *
 * @see java.util.function.Function
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface LongToShortFunction {

    /**
     * Calls the given {@link LongToShortFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code LongToShortFunction}.
     * @throws NullPointerException If the given function is {@code null}
     */
    static short call(@Nonnull final LongToShortFunction function, long value) {
        Objects.requireNonNull(function);
        return function.applyAsShort(value);
    }

    /**
     * Creates a {@link LongToShortFunction} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code LongToShortFunction} which always returns a given value.
     */
    @Nonnull
    static LongToShortFunction constant(short ret) {
        return value -> ret;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param value The argument to the function
     * @return The result from this function, which is its result.
     */
    short applyAsShort(long value);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     * @implSpec The default implementation always returns {@code 1}.
     */
    @Nonnegative
    default int arity() {
        return 1;
    }

    /**
     * Returns a composed {@link LongToShortFunction} that first applies the {@code before} operation to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param before The operation to apply before this function is applied
     * @return A composed {@code LongToShortFunction} that first applies the {@code before} operation to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a primitive specialization of {@link UnaryOperator}. Therefore the
     * given operation handles primitive types. In this case this is {@code byte}.
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default LongToShortFunction compose(@Nonnull final LongUnaryOperator before) {
        Objects.requireNonNull(before);
        return value -> applyAsShort(before.applyAsLong(value));
    }

    /**
     * Returns a composed {@link ToShortFunction} that first applies the {@code before} function to its input, and then
     * applies this function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <T> The type of the argument to the before function
     * @param before The function to apply before this function is applied
     * @return A composed {@code ToShortFunction} that first applies the {@code before} function to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to handle every type.
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default <T> ToShortFunction<T> compose(@Nonnull final ToLongFunction<? super T> before) {
        Objects.requireNonNull(before);
        return t -> applyAsShort(before.applyAsLong(t));
    }

    /**
     * Returns a composed {@link LongFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <R> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code LongFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(LongUnaryOperator)
     * @see #compose(ToLongFunction)
     */
    @Nonnull
    default <R> LongFunction<R> andThen(@Nonnull final ShortFunction<? extends R> after) {
        Objects.requireNonNull(after);
        return value -> after.apply(applyAsShort(value));
    }

    /**
     * Returns a composed {@link LongToBooleanFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code boolean}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code LongToBooleanFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default LongToBooleanFunction andThenToBoolean(@Nonnull final ShortToBooleanFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsBoolean(applyAsShort(value));
    }

    /**
     * Returns a composed {@link LongToByteFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code LongToByteFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default LongToByteFunction andThenToByte(@Nonnull final ShortToByteFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsByte(applyAsShort(value));
    }

    /**
     * Returns a composed {@link LongToCharFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code LongToCharFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default LongToCharFunction andThenToChar(@Nonnull final ShortToCharFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsChar(applyAsShort(value));
    }

    /**
     * Returns a composed {@link LongToDoubleFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code LongToDoubleFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default LongToDoubleFunction andThenToDouble(@Nonnull final ShortToDoubleFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsDouble(applyAsShort(value));
    }

    /**
     * Returns a composed {@link LongToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code LongToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default LongToFloatFunction andThenToFloat(@Nonnull final ShortToFloatFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsFloat(applyAsShort(value));
    }

    /**
     * Returns a composed {@link LongToIntFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code LongToIntFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default LongToIntFunction andThenToInt(@Nonnull final ShortToIntFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsInt(applyAsShort(value));
    }

    /**
     * Returns a composed {@link LongUnaryOperator} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code LongUnaryOperator} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default LongUnaryOperator andThenToLong(@Nonnull final ShortToLongFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsLong(applyAsShort(value));
    }

    /**
     * Returns a composed {@link LongToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code LongToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ShortFunction)
     */
    @Nonnull
    default LongToShortFunction andThenToShort(@Nonnull final ShortUnaryOperator after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsShort(applyAsShort(value));
    }

    /**
     * Returns a composed {@link LongConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link ShortConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code LongConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@code ShortConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default LongConsumer consume(@Nonnull final ShortConsumer consumer) {
        Objects.requireNonNull(consumer);
        return value -> consumer.accept(applyAsShort(value));
    }

    /**
     * Applies this function partially to one argument. The result is an operation of arity {@code 0}.
     *
     * @param value The argument to partially apply to the function
     * @return A partial application of this function.
     */
    @Nonnull
    default ShortSupplier partial(long value) {
        return () -> applyAsShort(value);
    }

    /**
     * Returns a composed {@link Function} which represents this {@link LongToShortFunction}. Thereby the primitive
     * input argument for this function is autoboxed. This method is just convenience to provide the ability to use this
     * {@code LongToShortFunction} with JRE specific methods, only accepting {@code Function}.
     *
     * @return A composed {@code Function} which represents this {@code LongToShortFunction}.
     */
    @Nonnull
    default Function<Long, Short> boxed() {
        return this::applyAsShort;
    }
}
