/*
 * Copyright (c) 2016 Gridtec. All rights reserved.
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
package at.gridtec.lambda4j.function.bi.conversion;

import at.gridtec.lambda4j.Lambda;
import at.gridtec.lambda4j.consumer.ThrowableIntConsumer;
import at.gridtec.lambda4j.consumer.bi.ThrowableBiShortConsumer;
import at.gridtec.lambda4j.core.exception.ThrownByFunctionalInterfaceException;
import at.gridtec.lambda4j.core.util.ThrowableUtils;
import at.gridtec.lambda4j.function.ThrowableFunction;
import at.gridtec.lambda4j.function.ThrowableIntFunction;
import at.gridtec.lambda4j.function.bi.ThrowableBiFunction;
import at.gridtec.lambda4j.function.bi.ThrowableBiShortFunction;
import at.gridtec.lambda4j.function.bi.to.ThrowableToIntBiFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableBooleanToShortFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableByteToShortFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableCharToShortFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableDoubleToShortFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableFloatToShortFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableIntToByteFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableIntToCharFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableIntToDoubleFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableIntToFloatFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableIntToLongFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableIntToShortFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableLongToShortFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableShortToIntFunction;
import at.gridtec.lambda4j.function.to.ThrowableToShortFunction;
import at.gridtec.lambda4j.operator.binary.ThrowableIntBinaryOperator;
import at.gridtec.lambda4j.operator.binary.ThrowableShortBinaryOperator;
import at.gridtec.lambda4j.operator.unary.ThrowableIntUnaryOperator;
import at.gridtec.lambda4j.operator.unary.ThrowableShortUnaryOperator;
import at.gridtec.lambda4j.predicate.ThrowableIntPredicate;
import at.gridtec.lambda4j.predicate.bi.ThrowableBiShortPredicate;

import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Represents an operation that accepts two {@code short}-valued input arguments and produces a {@code int}-valued
 * result which is able to throw any {@link Throwable}. This is a primitive specialization of {@link
 * ThrowableBiFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsIntThrows(short, short)}.
 *
 * @param <X> The type of the throwable to be thrown by this function
 * @see ThrowableBiFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableBiShortToIntFunction<X extends Throwable> extends Lambda {

    /**
     * Constructs a {@link ThrowableBiShortToIntFunction} based on a lambda expression or a method reference. Thereby
     * the given lambda expression or method reference is returned on an as-is basis to implicitly transform it to the
     * desired type. With this method, it is possible to ensure that correct type is used from lambda expression or
     * method reference.
     *
     * @param <X> The type of the throwable to be thrown by this function
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ThrowableBiShortToIntFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static <X extends Throwable> ThrowableBiShortToIntFunction<X> of(
            @Nullable final ThrowableBiShortToIntFunction<X> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ThrowableBiShortToIntFunction} with the given arguments and returns its result.
     *
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code ThrowableBiShortToIntFunction}.
     * @throws NullPointerException If given argument is {@code null}
     * @throws X Any throwable from this functions action
     */
    static <X extends Throwable> int call(@Nonnull final ThrowableBiShortToIntFunction<? extends X> function,
            short value1, short value2) throws X {
        Objects.requireNonNull(function);
        return function.applyAsIntThrows(value1, value2);
    }

    /**
     * Creates a {@link ThrowableBiShortToIntFunction} which uses the {@code first} parameter of this one as argument
     * for the given {@link ThrowableShortToIntFunction}.
     *
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ThrowableBiShortToIntFunction} which uses the {@code first} parameter of this one as
     * argument for the given {@code ThrowableShortToIntFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <X extends Throwable> ThrowableBiShortToIntFunction<X> onlyFirst(
            @Nonnull final ThrowableShortToIntFunction<? extends X> function) {
        Objects.requireNonNull(function);
        return (value1, value2) -> function.applyAsIntThrows(value1);
    }

    /**
     * Creates a {@link ThrowableBiShortToIntFunction} which uses the {@code second} parameter of this one as argument
     * for the given {@link ThrowableShortToIntFunction}.
     *
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ThrowableBiShortToIntFunction} which uses the {@code second} parameter of this one as
     * argument for the given {@code ThrowableShortToIntFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <X extends Throwable> ThrowableBiShortToIntFunction<X> onlySecond(
            @Nonnull final ThrowableShortToIntFunction<? extends X> function) {
        Objects.requireNonNull(function);
        return (value1, value2) -> function.applyAsIntThrows(value2);
    }

    /**
     * Creates a {@link ThrowableBiShortToIntFunction} which always returns a given value.
     *
     * @param <X> The type of the throwable to be thrown by this function
     * @param ret The return value for the constant
     * @return A {@code ThrowableBiShortToIntFunction} which always returns a given value.
     */
    @Nonnull
    static <X extends Throwable> ThrowableBiShortToIntFunction<X> constant(int ret) {
        return (value1, value2) -> ret;
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The return value from the function, which is its result.
     * @throws X Any throwable from this functions action
     */
    int applyAsIntThrows(short value1, short value2) throws X;

    /**
     * Returns the number of arguments for this function.
     *
     * @return The number of arguments for this function.
     * @implSpec The default implementation always returns {@code 2}.
     */
    @Nonnegative
    default int arity() {
        return 2;
    }

    /**
     * Returns a composed {@link ThrowableToIntBiFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     *
     * @param <A> The type of the argument to the first given function, and of composed function
     * @param <B> The type of the argument to the second given function, and of composed function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code ThrowableToIntBiFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B> ThrowableToIntBiFunction<A, B, X> compose(
            @Nonnull final ThrowableToShortFunction<? super A, ? extends X> before1,
            @Nonnull final ThrowableToShortFunction<? super B, ? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (a, b) -> applyAsIntThrows(before1.applyAsShortThrows(a), before2.applyAsShortThrows(b));
    }

    /**
     * Returns a composed {@link ThrowableBiBooleanToIntFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code boolean} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code ThrowableBiBooleanToIntFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableBiBooleanToIntFunction<X> composeFromBoolean(
            @Nonnull final ThrowableBooleanToShortFunction<? extends X> before1,
            @Nonnull final ThrowableBooleanToShortFunction<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsIntThrows(before1.applyAsShortThrows(value1),
                                                    before2.applyAsShortThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableBiByteToIntFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code byte} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code ThrowableBiByteToIntFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableBiByteToIntFunction<X> composeFromByte(
            @Nonnull final ThrowableByteToShortFunction<? extends X> before1,
            @Nonnull final ThrowableByteToShortFunction<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsIntThrows(before1.applyAsShortThrows(value1),
                                                    before2.applyAsShortThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableBiCharToIntFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code char} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code ThrowableBiCharToIntFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableBiCharToIntFunction<X> composeFromChar(
            @Nonnull final ThrowableCharToShortFunction<? extends X> before1,
            @Nonnull final ThrowableCharToShortFunction<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsIntThrows(before1.applyAsShortThrows(value1),
                                                    before2.applyAsShortThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableBiDoubleToIntFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code double} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code ThrowableBiDoubleToIntFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableBiDoubleToIntFunction<X> composeFromDouble(
            @Nonnull final ThrowableDoubleToShortFunction<? extends X> before1,
            @Nonnull final ThrowableDoubleToShortFunction<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsIntThrows(before1.applyAsShortThrows(value1),
                                                    before2.applyAsShortThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableBiFloatToIntFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code float} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code ThrowableBiFloatToIntFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableBiFloatToIntFunction<X> composeFromFloat(
            @Nonnull final ThrowableFloatToShortFunction<? extends X> before1,
            @Nonnull final ThrowableFloatToShortFunction<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsIntThrows(before1.applyAsShortThrows(value1),
                                                    before2.applyAsShortThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableIntBinaryOperator} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code int} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code ThrowableIntBinaryOperator} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableIntBinaryOperator<X> composeFromInt(
            @Nonnull final ThrowableIntToShortFunction<? extends X> before1,
            @Nonnull final ThrowableIntToShortFunction<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsIntThrows(before1.applyAsShortThrows(value1),
                                                    before2.applyAsShortThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableBiLongToIntFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code long} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code ThrowableBiLongToIntFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableBiLongToIntFunction<X> composeFromLong(
            @Nonnull final ThrowableLongToShortFunction<? extends X> before1,
            @Nonnull final ThrowableLongToShortFunction<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsIntThrows(before1.applyAsShortThrows(value1),
                                                    before2.applyAsShortThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableBiShortToIntFunction} that first applies the {@code before} operators to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before1 The first operator to apply before this function is applied
     * @param before2 The second operator to apply before this function is applied
     * @return A composed {@code ThrowableBiShortToIntFunction} that first applies the {@code before} operators to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableBiShortToIntFunction<X> composeFromShort(
            @Nonnull final ThrowableShortUnaryOperator<? extends X> before1,
            @Nonnull final ThrowableShortUnaryOperator<? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsIntThrows(before1.applyAsShortThrows(value1),
                                                    before2.applyAsShortThrows(value2));
    }

    /**
     * Returns a composed {@link ThrowableBiShortFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableBiShortFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ThrowableBiShortFunction<S, X> andThen(
            @Nonnull final ThrowableIntFunction<? extends S, ? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyThrows(applyAsIntThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableBiShortPredicate} that first applies this function to its input, and then
     * applies the {@code after} predicate to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this function is applied
     * @return A composed {@code ThrowableBiShortPredicate} that first applies this function to its input, and then
     * applies the {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableBiShortPredicate<X> andThenToBoolean(@Nonnull final ThrowableIntPredicate<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.testThrows(applyAsIntThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableBiShortToByteFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableBiShortToByteFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableBiShortToByteFunction<X> andThenToByte(
            @Nonnull final ThrowableIntToByteFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsByteThrows(applyAsIntThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableBiShortToCharFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableBiShortToCharFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableBiShortToCharFunction<X> andThenToChar(
            @Nonnull final ThrowableIntToCharFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsCharThrows(applyAsIntThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableBiShortToDoubleFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableBiShortToDoubleFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableBiShortToDoubleFunction<X> andThenToDouble(
            @Nonnull final ThrowableIntToDoubleFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsDoubleThrows(applyAsIntThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableBiShortToFloatFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableBiShortToFloatFunction} that first applies this function to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableBiShortToFloatFunction<X> andThenToFloat(
            @Nonnull final ThrowableIntToFloatFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsFloatThrows(applyAsIntThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableBiShortToIntFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code int}.
     *
     * @param after The operator to apply after this function is applied
     * @return A composed {@code ThrowableBiShortToIntFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableBiShortToIntFunction<X> andThenToInt(@Nonnull final ThrowableIntUnaryOperator<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsIntThrows(applyAsIntThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableBiShortToLongFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableBiShortToLongFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableBiShortToLongFunction<X> andThenToLong(
            @Nonnull final ThrowableIntToLongFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsLongThrows(applyAsIntThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableShortBinaryOperator} that first applies this function to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableShortBinaryOperator} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableShortBinaryOperator<X> andThenToShort(
            @Nonnull final ThrowableIntToShortFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsShortThrows(applyAsIntThrows(value1, value2));
    }

    /**
     * Returns a composed {@link ThrowableBiShortConsumer} that fist applies this function to its input, and then
     * consumes the result using the given {@link ThrowableIntConsumer}.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ThrowableBiShortConsumer} that first applies this function to its input, and then
     * consumes the result using the given {@code ThrowableIntConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ThrowableBiShortConsumer<X> consume(@Nonnull final ThrowableIntConsumer<? extends X> consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> consumer.acceptThrows(applyAsIntThrows(value1, value2));
    }

    /**
     * Returns a memoized (caching) version of this {@link ThrowableBiShortToIntFunction}. Whenever it is called, the
     * mapping between the input parameters and the return value is preserved in a cache, making subsequent calls
     * returning the memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ThrowableBiShortToIntFunction}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ThrowableBiShortToIntFunction<X> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            final Map<Pair<Short, Short>, Integer> cache = new ConcurrentHashMap<>();
            final Object lock = new Object();
            return (ThrowableBiShortToIntFunction<X> & Memoized) (value1, value2) -> {
                final int returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Pair.of(value1, value2), ThrowableFunction.of(
                            key -> applyAsIntThrows(key.getLeft(), key.getRight())));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link ThrowableBiFunction} which represents this {@link ThrowableBiShortToIntFunction}.
     * Thereby the primitive input argument for this function is autoboxed.
     *
     * @return A composed {@code ThrowableBiFunction} which represents this {@code ThrowableBiShortToIntFunction}.
     */
    @Nonnull
    default ThrowableBiFunction<Short, Short, Integer, X> boxed() {
        return this::applyAsIntThrows;
    }

    /**
     * Returns a composed {@link BiShortToIntFunction} that applies this function to its input and nests the thrown
     * {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}. The throwable is
     * nested (wrapped) in a {@link ThrownByFunctionalInterfaceException}, which is constructed from the thrown
     * throwables message and the thrown throwable itself.
     *
     * @return A composed {@code BiShortToIntFunction} that applies this function to its input and nests the thrown
     * {@code Throwable} from it, unless it is of type {@code RuntimeException} or {@code Error}.
     */
    @Nonnull
    default BiShortToIntFunction nest() {
        return (value1, value2) -> {
            try {
                return this.applyAsIntThrows(value1, value2);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw new ThrownByFunctionalInterfaceException(throwable.getMessage(), throwable);
            }
        };
    }

    /**
     * Returns a composed {@link BiShortToIntFunction} that applies this function to its input and sneakily throws the
     * thrown {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}. This means that
     * each throwable thrown from the returned composed function behaves exactly the same as an <em>unchecked</em>
     * throwable does. As a result, there is no need to handle the throwable of this function in the returned composed
     * function by either wrapping it in an <em>unchecked</em> throwable or to declare it in the {@code throws} clause,
     * as it would be done in a non sneaky throwing function.
     * <p>
     * What sneaky throwing simply does, is to fake out the compiler and thus it bypasses the principle of
     * <em>checked</em> throwables. On the JVM (class file) level, all throwables, checked or not, can be thrown
     * regardless of the {@code throws} clause of methods, which is why this works at all.
     * <p>
     * However, when using this method to get a sneaky throwing function variant of this throwable function, the
     * following advantages, disadvantages and limitations will apply:
     * <p>
     * If the calling-code is to handle the sneakily thrown throwable, it is required to add it to the {@code throws}
     * clause of the method that applies the returned composed function. The compiler will not force the declaration in
     * the {@code throws} clause anymore.
     * <p>
     * If the calling-code already handles the sneakily thrown throwable, the compiler requires it to be added to the
     * {@code throws} clause of the method that applies the returned composed function. If not added, the compiler will
     * error that the caught throwable is never thrown in the corresponding {@code try} block.
     * <p>
     * If the returned composed function is directly surrounded by a {@code try}-{@code catch} block to catch the
     * sneakily thrown throwable from it, the compiler will error that the caught throwable is never thrown in the
     * corresponding {@code try} block.
     * <p>
     * In any case, if the throwable is not added to the to the {@code throws} clause of the method that applies the
     * returned composed function, the calling-code won't be able to catch the throwable by name. It will bubble and
     * probably be caught in some {@code catch} statement, catching a base type such as {@code try { ... }
     * catch(RuntimeException e) { ... }} or {@code try { ... } catch(Exception e) { ... }}, but perhaps this is
     * intended.
     * <p>
     * When the called code never throws the specific throwable that it declares, it should obviously be omitted. For
     * example: {@code new String(byteArr, "UTF-8") throws UnsupportedEncodingException}, but {@code UTF-8} is
     * guaranteed by the Java specification to be always present. Here, the {@code throws} declaration is a nuisance and
     * any solution to silence it with minimal boilerplate is welcome. The throwable should therefore be omitted in the
     * {@code throws} clause of the method that applies the returned composed function.
     * <p>
     * With all that mentioned, the following example will demonstrate this methods correct use:
     * <pre>{@code
     * // when called with illegal value ClassNotFoundException is thrown
     * public Class<?> sneakyThrowingFunctionalInterface(final String className) throws ClassNotFoundException {
     *     return ThrowableFunction.of(Class::forName) // create the correct throwable functional interface
     *                .sneakyThrow() // create a non-throwable variant which is able to sneaky throw (this method)
     *                .apply(className); // apply non-throwable variant -> may sneaky throw a throwable
     * }
     *
     * // call the the method which surround the sneaky throwing functional interface
     * public void callingMethod() {
     *     try {
     *         final Class<?> clazz = sneakyThrowingFunctionalInterface("some illegal class name");
     *         // ... do something with clazz ...
     *     } catch(ClassNotFoundException e) {
     *         // ... do something with e ...
     *     }
     * }
     * }</pre>
     * In conclusion, this somewhat contentious ability should be used carefully, of course, with the advantages,
     * disadvantages and limitations described above kept in mind.
     *
     * @return A composed {@link BiShortToIntFunction} that applies this function to its input and sneakily throws the
     * thrown {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}.
     */
    @Nonnull
    default BiShortToIntFunction sneakyThrow() {
        return (value1, value2) -> {
            try {
                return this.applyAsIntThrows(value1, value2);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw ThrowableUtils.sneakyThrow(throwable);
            }
        };
    }

}