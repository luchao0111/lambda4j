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
package at.gridtec.lambda4j.function.primitives.conversion.bi;

import at.gridtec.lambda4j.consumer.primitives.bi.IntBiConsumer;
import at.gridtec.lambda4j.function.primitives.bi.IntBiFunction;
import at.gridtec.lambda4j.function.primitives.conversion.LongToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.LongToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.LongToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.LongToShortFunction;
import at.gridtec.lambda4j.predicates.primitives.bi.IntBiPredicate;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.IntBinaryOperator;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongSupplier;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.ToIntFunction;
import java.util.function.ToLongBiFunction;
import java.util.function.UnaryOperator;

/**
 * Represents a function that accepts two int-valued arguments and produces a long-valued result. This is the {@code
 * int}-to-{@code long} primitive specialization for {@link BiFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsLong(int, int)}.
 *
 * @see BiFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BiIntToLongFunction {

    /**
     * Calls the given {@link BiIntToLongFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiIntToLongFunction}.
     * @throws NullPointerException If the given function is {@code null}
     */
    static long call(@Nonnull final BiIntToLongFunction function, int value1, int value2) {
        Objects.requireNonNull(function);
        return function.applyAsLong(value1, value2);
    }

    /**
     * Creates a {@link BiIntToLongFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@link IntToLongFunction}.
     *
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code BiIntToLongFunction} which uses the {@code first} parameter of this one as argument for
     * the given {@code IntToLongFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static BiIntToLongFunction onlyFirst(@Nonnull final IntToLongFunction function) {
        Objects.requireNonNull(function);
        return (value1, value2) -> function.applyAsLong(value1);
    }

    /**
     * Creates a {@link BiIntToLongFunction} which uses the {@code second} parameter of this one as argument for the
     * given {@link IntToLongFunction}.
     *
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code BiIntToLongFunction} which uses the {@code second} parameter of this one as argument for
     * the given {@code IntToLongFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static BiIntToLongFunction onlySecond(@Nonnull final IntToLongFunction function) {
        Objects.requireNonNull(function);
        return (value1, value2) -> function.applyAsLong(value2);
    }

    /**
     * Creates a {@link BiIntToLongFunction} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code BiIntToLongFunction} which always returns a given value.
     */
    @Nonnull
    static BiIntToLongFunction constant(long ret) {
        return (value1, value2) -> ret;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The return value from the function, which is its result.
     */
    long applyAsLong(int value1, int value2);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     * @implSpec The default implementation always returns {@code 2}.
     */
    @Nonnegative
    default int arity() {
        return 2;
    }

    /**
     * Returns a composed {@link BiIntToLongFunction} that first applies the {@code before} operations to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param before1 The first operation to apply before this function is applied
     * @param before2 The second operation to apply before this function is applied
     * @return A composed {@code BiIntToLongFunction} that first applies the {@code before} operations to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If one of the given arguments is {@code null}
     * @implNote The input arguments of this method are primitive specializations of {@link UnaryOperator}. Therefore
     * the given operations handle primitive types. In this case this is {@code int}.
     * @see #andThen(LongFunction)
     */
    @Nonnull
    default BiIntToLongFunction compose(@Nonnull final IntUnaryOperator before1,
            @Nonnull final IntUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsLong(before1.applyAsInt(value1), before2.applyAsInt(value2));
    }

    /**
     * Returns a composed {@link ToLongBiFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <T> The type of the argument to the first before function
     * @param <U> The type of the argument to the second before function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code ToLongBiFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If one of the given arguments is {@code null}
     * @implNote The input arguments of this method are able to handle every type.
     * @see #andThen(LongFunction)
     */
    @Nonnull
    default <T, U> ToLongBiFunction<T, U> compose(@Nonnull final ToIntFunction<? super T> before1,
            @Nonnull final ToIntFunction<? super U> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (t, u) -> applyAsLong(before1.applyAsInt(t), before2.applyAsInt(u));
    }

    /**
     * Returns a composed {@link IntBiFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <R> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code IntBiFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(IntUnaryOperator, IntUnaryOperator)
     * @see #compose(ToIntFunction, ToIntFunction)
     */
    @Nonnull
    default <R> IntBiFunction<R> andThen(@Nonnull final LongFunction<? extends R> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.apply(applyAsLong(value1, value2));
    }

    /**
     * Returns a composed {@link IntBiPredicate} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code boolean}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code IntBiPredicate} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(LongFunction)
     */
    @Nonnull
    default IntBiPredicate andThenToBoolean(@Nonnull final LongPredicate after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.test(applyAsLong(value1, value2));
    }

    /**
     * Returns a composed {@link BiIntToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiIntToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(LongFunction)
     */
    @Nonnull
    default BiIntToByteFunction andThenToByte(@Nonnull final LongToByteFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsByte(applyAsLong(value1, value2));
    }

    /**
     * Returns a composed {@link BiIntToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiIntToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(LongFunction)
     */
    @Nonnull
    default BiIntToCharFunction andThenToChar(@Nonnull final LongToCharFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsChar(applyAsLong(value1, value2));
    }

    /**
     * Returns a composed {@link BiIntToDoubleFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiIntToDoubleFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(LongFunction)
     */
    @Nonnull
    default BiIntToDoubleFunction andThenToDouble(@Nonnull final LongToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsDouble(applyAsLong(value1, value2));
    }

    /**
     * Returns a composed {@link BiIntToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiIntToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(LongFunction)
     */
    @Nonnull
    default BiIntToFloatFunction andThenToFloat(@Nonnull final LongToFloatFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsFloat(applyAsLong(value1, value2));
    }

    /**
     * Returns a composed {@link IntBinaryOperator} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code IntBinaryOperator} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(LongFunction)
     */
    @Nonnull
    default IntBinaryOperator andThenToInt(@Nonnull final LongToIntFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsInt(applyAsLong(value1, value2));
    }

    /**
     * Returns a composed {@link BiIntToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiIntToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(LongFunction)
     */
    @Nonnull
    default BiIntToLongFunction andThenToLong(@Nonnull final LongUnaryOperator after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsLong(applyAsLong(value1, value2));
    }

    /**
     * Returns a composed {@link BiIntToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiIntToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(LongFunction)
     */
    @Nonnull
    default BiIntToShortFunction andThenToShort(@Nonnull final LongToShortFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsShort(applyAsLong(value1, value2));
    }

    /**
     * Returns a composed {@link IntBiConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link LongConsumer}. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code IntBiConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@code LongConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default IntBiConsumer consume(@Nonnull final LongConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> consumer.accept(applyAsLong(value1, value2));
    }

    /**
     * Applies this function partially to one argument. The result is a function of arity {@code 1};
     *
     * @param value1 The argument to partially apply to the function
     * @return A partial application of this function.
     */
    @Nonnull
    default IntToLongFunction partial(int value1) {
        return value2 -> applyAsLong(value1, value2);
    }

    /**
     * Applies this function partially to two arguments. The result is an operation of arity {@code 0}.
     *
     * @param value1 The first argument to partially apply to the function
     * @param value2 The second argument to partially apply to the function
     * @return A partial application of this function.
     */
    @Nonnull
    default LongSupplier partial(int value1, int value2) {
        return () -> applyAsLong(value1, value2);
    }

    /**
     * Returns a composed {@link BiFunction} which represents this {@link BiIntToLongFunction}. Thereby the primitive
     * input argument for this function is autoboxed. This method is just convenience to provide the ability to use this
     * {@code BiIntToLongFunction} with JRE specific methods, only accepting {@code BiFunction}.
     *
     * @return A composed {@code BiFunction} which represents this {@code BiIntToLongFunction}.
     */
    @Nonnull
    default BiFunction<Integer, Integer, Long> boxed() {
        return this::applyAsLong;
    }
}