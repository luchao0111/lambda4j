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
package at.gridtec.lambda4j.predicate.tri;

import at.gridtec.lambda4j.Lambda;
import at.gridtec.lambda4j.consumer.ThrowableBooleanConsumer;
import at.gridtec.lambda4j.consumer.tri.ThrowableTriByteConsumer;
import at.gridtec.lambda4j.core.exception.ThrownByFunctionalInterfaceException;
import at.gridtec.lambda4j.core.util.ThrowableUtils;
import at.gridtec.lambda4j.function.ThrowableBooleanFunction;
import at.gridtec.lambda4j.function.ThrowableFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableBooleanToByteFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableBooleanToCharFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableBooleanToDoubleFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableBooleanToFloatFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableBooleanToIntFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableBooleanToLongFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableBooleanToShortFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableCharToByteFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableDoubleToByteFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableFloatToByteFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableIntToByteFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableLongToByteFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableShortToByteFunction;
import at.gridtec.lambda4j.function.to.ThrowableToByteFunction;
import at.gridtec.lambda4j.function.tri.ThrowableTriByteFunction;
import at.gridtec.lambda4j.function.tri.conversion.ThrowableTriByteToCharFunction;
import at.gridtec.lambda4j.function.tri.conversion.ThrowableTriByteToDoubleFunction;
import at.gridtec.lambda4j.function.tri.conversion.ThrowableTriByteToFloatFunction;
import at.gridtec.lambda4j.function.tri.conversion.ThrowableTriByteToIntFunction;
import at.gridtec.lambda4j.function.tri.conversion.ThrowableTriByteToLongFunction;
import at.gridtec.lambda4j.function.tri.conversion.ThrowableTriByteToShortFunction;
import at.gridtec.lambda4j.operator.ternary.ThrowableBooleanTernaryOperator;
import at.gridtec.lambda4j.operator.ternary.ThrowableByteTernaryOperator;
import at.gridtec.lambda4j.operator.unary.ThrowableBooleanUnaryOperator;
import at.gridtec.lambda4j.operator.unary.ThrowableByteUnaryOperator;
import at.gridtec.lambda4j.predicate.ThrowableBytePredicate;

import org.apache.commons.lang3.tuple.Triple;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Represents an predicate (boolean-valued function) of three {@code byte}-valued input arguments which is able to throw
 * any {@link Throwable}. This is a primitive specialization of {@link ThrowableTriPredicate}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #testThrows(byte, byte, byte)}.
 *
 * @param <X> The type of the throwable to be thrown by this predicate
 * @see ThrowableTriPredicate
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableTriBytePredicate<X extends Throwable> extends Lambda {

    /**
     * Constructs a {@link ThrowableTriBytePredicate} based on a lambda expression or a method reference. Thereby the
     * given lambda expression or method reference is returned on an as-is basis to implicitly transform it to the
     * desired type. With this method, it is possible to ensure that correct type is used from lambda expression or
     * method reference.
     *
     * @param <X> The type of the throwable to be thrown by this predicate
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ThrowableTriBytePredicate} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but if {@code null} given, {@code
     * null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @Nonnull
    static <X extends Throwable> ThrowableTriBytePredicate<X> of(
            @Nonnull final ThrowableTriBytePredicate<X> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ThrowableTriBytePredicate} with the given arguments and returns its result.
     *
     * @param <X> The type of the throwable to be thrown by this predicate
     * @param predicate The predicate to be called
     * @param value1 The first argument to the predicate
     * @param value2 The second argument to the predicate
     * @param value3 The third argument to the predicate
     * @return The result from the given {@code ThrowableTriBytePredicate}.
     * @throws NullPointerException If given argument is {@code null}
     * @throws X Any throwable from this predicates action
     */
    static <X extends Throwable> boolean call(@Nonnull final ThrowableTriBytePredicate<? extends X> predicate,
            byte value1, byte value2, byte value3) throws X {
        Objects.requireNonNull(predicate);
        return predicate.testThrows(value1, value2, value3);
    }

    /**
     * Creates a {@link ThrowableTriBytePredicate} which uses the {@code first} parameter of this one as argument for
     * the given {@link ThrowableBytePredicate}.
     *
     * @param <X> The type of the throwable to be thrown by this predicate
     * @param predicate The predicate which accepts the {@code first} parameter of this one
     * @return Creates a {@code ThrowableTriBytePredicate} which uses the {@code first} parameter of this one as
     * argument for the given {@code ThrowableBytePredicate}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <X extends Throwable> ThrowableTriBytePredicate<X> onlyFirst(
            @Nonnull final ThrowableBytePredicate<? extends X> predicate) {
        Objects.requireNonNull(predicate);
        return (value1, value2, value3) -> predicate.testThrows(value1);
    }

    /**
     * Creates a {@link ThrowableTriBytePredicate} which uses the {@code second} parameter of this one as argument for
     * the given {@link ThrowableBytePredicate}.
     *
     * @param <X> The type of the throwable to be thrown by this predicate
     * @param predicate The predicate which accepts the {@code second} parameter of this one
     * @return Creates a {@code ThrowableTriBytePredicate} which uses the {@code second} parameter of this one as
     * argument for the given {@code ThrowableBytePredicate}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <X extends Throwable> ThrowableTriBytePredicate<X> onlySecond(
            @Nonnull final ThrowableBytePredicate<? extends X> predicate) {
        Objects.requireNonNull(predicate);
        return (value1, value2, value3) -> predicate.testThrows(value2);
    }

    /**
     * Creates a {@link ThrowableTriBytePredicate} which uses the {@code third} parameter of this one as argument for
     * the given {@link ThrowableBytePredicate}.
     *
     * @param <X> The type of the throwable to be thrown by this predicate
     * @param predicate The predicate which accepts the {@code third} parameter of this one
     * @return Creates a {@code ThrowableTriBytePredicate} which uses the {@code third} parameter of this one as
     * argument for the given {@code ThrowableBytePredicate}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <X extends Throwable> ThrowableTriBytePredicate<X> onlyThird(
            @Nonnull final ThrowableBytePredicate<? extends X> predicate) {
        Objects.requireNonNull(predicate);
        return (value1, value2, value3) -> predicate.testThrows(value3);
    }

    /**
     * Creates a {@link ThrowableTriBytePredicate} which always returns a given value.
     *
     * @param <X> The type of the throwable to be thrown by this predicate
     * @param ret The return value for the constant
     * @return A {@code ThrowableTriBytePredicate} which always returns a given value.
     */
    @Nonnull
    static <X extends Throwable> ThrowableTriBytePredicate<X> constant(boolean ret) {
        return (value1, value2, value3) -> ret;
    }

    /**
     * Returns a {@link ThrowableTriBytePredicate} that always returns {@code true}.
     *
     * @param <X> The type of the throwable to be thrown by this predicate
     * @return A {@link ThrowableTriBytePredicate} that always returns {@code true}.
     * @see #alwaysFalse()
     */
    @Nonnull
    static <X extends Throwable> ThrowableTriBytePredicate<X> alwaysTrue() {
        return (value1, value2, value3) -> true;
    }

    /**
     * Returns a {@link ThrowableTriBytePredicate} that always returns {@code false}.
     *
     * @param <X> The type of the throwable to be thrown by this predicate
     * @return A {@link ThrowableTriBytePredicate} that always returns {@code false}.
     * @see #alwaysTrue()
     */
    @Nonnull
    static <X extends Throwable> ThrowableTriBytePredicate<X> alwaysFalse() {
        return (value1, value2, value3) -> false;
    }

    /**
     * Returns a {@link ThrowableTriBytePredicate} that tests if the given arguments are <b>equal</b> to the ones of
     * this predicate.
     *
     * @param <X> The type of the throwable to be thrown by this predicate
     * @param target1 The first reference with which to compare for equality, which may be {@code null}
     * @param target2 The second reference with which to compare for equality, which may be {@code null}
     * @param target3 The third reference with which to compare for equality, which may be {@code null}
     * @return A {@code ThrowableTriBytePredicate} that tests if the given arguments are <b>equal</b> to the ones of
     * this predicate.
     * @implNote This implementation checks equality according to {@link Objects#equals(Object)} operation for {@link
     * Object} references and {@code value == target} operation for primitive values.
     */
    @Nonnull
    static <X extends Throwable> ThrowableTriBytePredicate<X> isEqual(byte target1, byte target2, byte target3) {
        return (value1, value2, value3) -> (value1 == target1) && (value2 == target2) && (value3 == target3);
    }

    /**
     * Applies this predicate to the given arguments.
     *
     * @param value1 The first argument to the predicate
     * @param value2 The second argument to the predicate
     * @param value3 The third argument to the predicate
     * @return The return value from the predicate, which is its result.
     * @throws X Any throwable from this predicates action
     */
    boolean testThrows(byte value1, byte value2, byte value3) throws X;

    /**
     * Returns the number of arguments for this predicate.
     *
     * @return The number of arguments for this predicate.
     * @implSpec The default implementation always returns {@code 3}.
     */
    @Nonnegative
    default int arity() {
        return 3;
    }

    /**
     * Returns a composed {@link ThrowableTriPredicate} that first applies the {@code before} functions to its input,
     * and then applies this predicate to the result.
     *
     * @param <A> The type of the argument to the first given function, and of composed predicate
     * @param <B> The type of the argument to the second given function, and of composed predicate
     * @param <C> The type of the argument to the third given function, and of composed predicate
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @param before3 The third function to apply before this predicate is applied
     * @return A composed {@code ThrowableTriPredicate} that first applies the {@code before} functions to its input,
     * and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B, C> ThrowableTriPredicate<A, B, C, X> compose(
            @Nonnull final ThrowableToByteFunction<? super A, ? extends X> before1,
            @Nonnull final ThrowableToByteFunction<? super B, ? extends X> before2,
            @Nonnull final ThrowableToByteFunction<? super C, ? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> testThrows(before1.applyAsByteThrows(a), before2.applyAsByteThrows(b),
                                       before3.applyAsByteThrows(c));
    }

    /**
     * Returns a composed {@link ThrowableBooleanTernaryOperator} that first applies the {@code before} functions to its
     * input, and then applies this predicate to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code boolean} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @param before3 The third function to apply before this predicate is applied
     * @return A composed {@code ThrowableBooleanTernaryOperator} that first applies the {@code before} functions to its
     * input, and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableBooleanTernaryOperator<X> composeFromBoolean(
            @Nonnull final ThrowableBooleanToByteFunction<? extends X> before1,
            @Nonnull final ThrowableBooleanToByteFunction<? extends X> before2,
            @Nonnull final ThrowableBooleanToByteFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> testThrows(before1.applyAsByteThrows(value1),
                                                      before2.applyAsByteThrows(value2),
                                                      before3.applyAsByteThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriBytePredicate} that first applies the {@code before} operators to its
     * input, and then applies this predicate to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code byte} input, before this primitive predicate is executed.
     *
     * @param before1 The first operator to apply before this predicate is applied
     * @param before2 The second operator to apply before this predicate is applied
     * @param before3 The third operator to apply before this predicate is applied
     * @return A composed {@code ThrowableTriBytePredicate} that first applies the {@code before} operators to its
     * input, and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableTriBytePredicate<X> composeFromByte(@Nonnull final ThrowableByteUnaryOperator<? extends X> before1,
            @Nonnull final ThrowableByteUnaryOperator<? extends X> before2,
            @Nonnull final ThrowableByteUnaryOperator<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> testThrows(before1.applyAsByteThrows(value1),
                                                      before2.applyAsByteThrows(value2),
                                                      before3.applyAsByteThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriCharPredicate} that first applies the {@code before} functions to its
     * input, and then applies this predicate to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code char} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @param before3 The third function to apply before this predicate is applied
     * @return A composed {@code ThrowableTriCharPredicate} that first applies the {@code before} functions to its
     * input, and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableTriCharPredicate<X> composeFromChar(
            @Nonnull final ThrowableCharToByteFunction<? extends X> before1,
            @Nonnull final ThrowableCharToByteFunction<? extends X> before2,
            @Nonnull final ThrowableCharToByteFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> testThrows(before1.applyAsByteThrows(value1),
                                                      before2.applyAsByteThrows(value2),
                                                      before3.applyAsByteThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriDoublePredicate} that first applies the {@code before} functions to its
     * input, and then applies this predicate to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code double} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @param before3 The third function to apply before this predicate is applied
     * @return A composed {@code ThrowableTriDoublePredicate} that first applies the {@code before} functions to its
     * input, and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableTriDoublePredicate<X> composeFromDouble(
            @Nonnull final ThrowableDoubleToByteFunction<? extends X> before1,
            @Nonnull final ThrowableDoubleToByteFunction<? extends X> before2,
            @Nonnull final ThrowableDoubleToByteFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> testThrows(before1.applyAsByteThrows(value1),
                                                      before2.applyAsByteThrows(value2),
                                                      before3.applyAsByteThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriFloatPredicate} that first applies the {@code before} functions to its
     * input, and then applies this predicate to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code float} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @param before3 The third function to apply before this predicate is applied
     * @return A composed {@code ThrowableTriFloatPredicate} that first applies the {@code before} functions to its
     * input, and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableTriFloatPredicate<X> composeFromFloat(
            @Nonnull final ThrowableFloatToByteFunction<? extends X> before1,
            @Nonnull final ThrowableFloatToByteFunction<? extends X> before2,
            @Nonnull final ThrowableFloatToByteFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> testThrows(before1.applyAsByteThrows(value1),
                                                      before2.applyAsByteThrows(value2),
                                                      before3.applyAsByteThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriIntPredicate} that first applies the {@code before} functions to its input,
     * and then applies this predicate to the result. This method is just convenience, to provide the ability to execute
     * an operation which accepts {@code int} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @param before3 The third function to apply before this predicate is applied
     * @return A composed {@code ThrowableTriIntPredicate} that first applies the {@code before} functions to its input,
     * and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableTriIntPredicate<X> composeFromInt(@Nonnull final ThrowableIntToByteFunction<? extends X> before1,
            @Nonnull final ThrowableIntToByteFunction<? extends X> before2,
            @Nonnull final ThrowableIntToByteFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> testThrows(before1.applyAsByteThrows(value1),
                                                      before2.applyAsByteThrows(value2),
                                                      before3.applyAsByteThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriLongPredicate} that first applies the {@code before} functions to its
     * input, and then applies this predicate to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code long} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @param before3 The third function to apply before this predicate is applied
     * @return A composed {@code ThrowableTriLongPredicate} that first applies the {@code before} functions to its
     * input, and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableTriLongPredicate<X> composeFromLong(
            @Nonnull final ThrowableLongToByteFunction<? extends X> before1,
            @Nonnull final ThrowableLongToByteFunction<? extends X> before2,
            @Nonnull final ThrowableLongToByteFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> testThrows(before1.applyAsByteThrows(value1),
                                                      before2.applyAsByteThrows(value2),
                                                      before3.applyAsByteThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriShortPredicate} that first applies the {@code before} functions to its
     * input, and then applies this predicate to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code short} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @param before3 The third function to apply before this predicate is applied
     * @return A composed {@code ThrowableTriShortPredicate} that first applies the {@code before} functions to its
     * input, and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableTriShortPredicate<X> composeFromShort(
            @Nonnull final ThrowableShortToByteFunction<? extends X> before1,
            @Nonnull final ThrowableShortToByteFunction<? extends X> before2,
            @Nonnull final ThrowableShortToByteFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> testThrows(before1.applyAsByteThrows(value1),
                                                      before2.applyAsByteThrows(value2),
                                                      before3.applyAsByteThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriByteFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableTriByteFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ThrowableTriByteFunction<S, X> andThen(
            @Nonnull final ThrowableBooleanFunction<? extends S, ? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyThrows(testThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriBytePredicate} that first applies this predicate to its input, and then
     * applies the {@code after} operator to the result. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code boolean}.
     *
     * @param after The operator to apply after this predicate is applied
     * @return A composed {@code ThrowableTriBytePredicate} that first applies this predicate to its input, and then
     * applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableTriBytePredicate<X> andThenToBoolean(
            @Nonnull final ThrowableBooleanUnaryOperator<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsBooleanThrows(testThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableByteTernaryOperator} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code byte}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableByteTernaryOperator} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableByteTernaryOperator<X> andThenToByte(
            @Nonnull final ThrowableBooleanToByteFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsByteThrows(testThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriByteToCharFunction} that first applies this predicate to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code char}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableTriByteToCharFunction} that first applies this predicate to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableTriByteToCharFunction<X> andThenToChar(
            @Nonnull final ThrowableBooleanToCharFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsCharThrows(testThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriByteToDoubleFunction} that first applies this predicate to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code double}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableTriByteToDoubleFunction} that first applies this predicate to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableTriByteToDoubleFunction<X> andThenToDouble(
            @Nonnull final ThrowableBooleanToDoubleFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsDoubleThrows(testThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriByteToFloatFunction} that first applies this predicate to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code float}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableTriByteToFloatFunction} that first applies this predicate to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableTriByteToFloatFunction<X> andThenToFloat(
            @Nonnull final ThrowableBooleanToFloatFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsFloatThrows(testThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriByteToIntFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code int}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableTriByteToIntFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableTriByteToIntFunction<X> andThenToInt(
            @Nonnull final ThrowableBooleanToIntFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsIntThrows(testThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriByteToLongFunction} that first applies this predicate to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code long}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableTriByteToLongFunction} that first applies this predicate to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableTriByteToLongFunction<X> andThenToLong(
            @Nonnull final ThrowableBooleanToLongFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsLongThrows(testThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriByteToShortFunction} that first applies this predicate to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code short}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableTriByteToShortFunction} that first applies this predicate to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableTriByteToShortFunction<X> andThenToShort(
            @Nonnull final ThrowableBooleanToShortFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsShortThrows(testThrows(value1, value2, value3));
    }

    /**
     * Returns a composed {@link ThrowableTriByteConsumer} that fist applies this predicate to its input, and then
     * consumes the result using the given {@link ThrowableBooleanConsumer}.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ThrowableTriByteConsumer} that first applies this predicate to its input, and then
     * consumes the result using the given {@code ThrowableBooleanConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ThrowableTriByteConsumer<X> consume(@Nonnull final ThrowableBooleanConsumer<? extends X> consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.acceptThrows(testThrows(value1, value2, value3));
    }

    /**
     * Returns a {@link ThrowableTriBytePredicate} that represents the logical negation of this one.
     *
     * @return A {@code ThrowableTriBytePredicate} that represents the logical negation of this one.
     */
    @Nonnull
    default ThrowableTriBytePredicate<X> negate() {
        return (value1, value2, value3) -> !testThrows(value1, value2, value3);
    }

    /**
     * Returns a composed {@link ThrowableTriBytePredicate} that represents a short-circuiting logical AND of this
     * predicate and another. When evaluating the composed predicate, if this predicate is {@code false}, then the
     * {@code other} predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation of this
     * {@code ThrowableTriBytePredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code ThrowableTriBytePredicate} that will be logically-ANDed with this one
     * @return A composed {@code ThrowableTriBytePredicate} that represents the short-circuiting logical AND of this
     * predicate and the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #or(ThrowableTriBytePredicate)
     * @see #xor(ThrowableTriBytePredicate)
     */
    @Nonnull
    default ThrowableTriBytePredicate<X> and(@Nonnull final ThrowableTriBytePredicate<? extends X> other) {
        Objects.requireNonNull(other);
        return (value1, value2, value3) -> testThrows(value1, value2, value3) && other.testThrows(value1, value2,
                                                                                                  value3);
    }

    /**
     * Returns a composed {@link ThrowableTriBytePredicate} that represents a short-circuiting logical OR of this
     * predicate and another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code
     * other} predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation of this
     * {@code ThrowableTriBytePredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code ThrowableTriBytePredicate} that will be logically-ORed with this one
     * @return A composed {@code ThrowableTriBytePredicate} that represents the short-circuiting logical OR of this
     * predicate and the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #and(ThrowableTriBytePredicate)
     * @see #xor(ThrowableTriBytePredicate)
     */
    @Nonnull
    default ThrowableTriBytePredicate<X> or(@Nonnull final ThrowableTriBytePredicate<? extends X> other) {
        Objects.requireNonNull(other);
        return (value1, value2, value3) -> testThrows(value1, value2, value3) || other.testThrows(value1, value2,
                                                                                                  value3);
    }

    /**
     * Returns a composed {@link ThrowableTriBytePredicate} that represents a short-circuiting logical XOR of this
     * predicate and another. Any exceptions thrown during evaluation of either predicate is relayed to the caller; if
     * evaluation of this {@code ThrowableTriBytePredicate} throws an exception, the {@code other} predicate will not be
     * evaluated.
     *
     * @param other A {@code ThrowableTriBytePredicate} that will be logically-XORed with this one
     * @return A composed {@code ThrowableTriBytePredicate} that represents the short-circuiting logical XOR of this
     * predicate and the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #and(ThrowableTriBytePredicate)
     * @see #or(ThrowableTriBytePredicate)
     */
    @Nonnull
    default ThrowableTriBytePredicate<X> xor(@Nonnull final ThrowableTriBytePredicate<? extends X> other) {
        Objects.requireNonNull(other);
        return (value1, value2, value3) -> testThrows(value1, value2, value3) ^ other.testThrows(value1, value2,
                                                                                                 value3);
    }

    /**
     * Returns a memoized (caching) version of this {@link ThrowableTriBytePredicate}. Whenever it is called, the
     * mapping between the input parameters and the return value is preserved in a cache, making subsequent calls
     * returning the memoized value instead of computing the return value again.
     * <p>
     * Unless the predicate and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ThrowableTriBytePredicate}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized predicate, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized predicate can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ThrowableTriBytePredicate<X> memoized() {
        if (this instanceof Memoized) {
            return this;
        } else {
            final Map<Triple<Byte, Byte, Byte>, Boolean> cache = new ConcurrentHashMap<>();
            final Object lock = new Object();
            return (ThrowableTriBytePredicate<X> & Memoized) (value1, value2, value3) -> {
                final boolean returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Triple.of(value1, value2, value3), ThrowableFunction.of(
                            key -> testThrows(key.getLeft(), key.getMiddle(), key.getRight())));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link ThrowableTriPredicate} which represents this {@link ThrowableTriBytePredicate}. Thereby
     * the primitive input argument for this predicate is autoboxed.
     *
     * @return A composed {@code ThrowableTriPredicate} which represents this {@code ThrowableTriBytePredicate}.
     */
    @Nonnull
    default ThrowableTriPredicate<Byte, Byte, Byte, X> boxed() {
        return this::testThrows;
    }

    /**
     * Returns a composed {@link TriBytePredicate} that applies this predicate to its input and nests the thrown {@link
     * Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}. The throwable is nested
     * (wrapped) in a {@link ThrownByFunctionalInterfaceException}, which is constructed from the thrown throwables
     * message and the thrown throwable itself.
     *
     * @return A composed {@code TriBytePredicate} that applies this predicate to its input and nests the thrown {@code
     * Throwable} from it, unless it is of type {@code RuntimeException} or {@code Error}.
     */
    @Nonnull
    default TriBytePredicate nest() {
        return (value1, value2, value3) -> {
            try {
                return this.testThrows(value1, value2, value3);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw new ThrownByFunctionalInterfaceException(throwable.getMessage(), throwable);
            }
        };
    }

    /**
     * Returns a composed {@link TriBytePredicate} that applies this predicate to its input and sneakily throws the
     * thrown {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}. This means that
     * each throwable thrown from the returned composed predicate behaves exactly the same as an <em>unchecked</em>
     * throwable does. As a result, there is no need to handle the throwable of this predicate in the returned composed
     * predicate by either wrapping it in an <em>unchecked</em> throwable or to declare it in the {@code throws} clause,
     * as it would be done in a non sneaky throwing predicate.
     * <p>
     * What sneaky throwing simply does, is to fake out the compiler and thus it bypasses the principle of
     * <em>checked</em> throwables. On the JVM (class file) level, all throwables, checked or not, can be thrown
     * regardless of the {@code throws} clause of methods, which is why this works at all.
     * <p>
     * However, when using this method to get a sneaky throwing predicate variant of this throwable predicate, the
     * following advantages, disadvantages and limitations will apply:
     * <p>
     * If the calling-code is to handle the sneakily thrown throwable, it is required to add it to the {@code throws}
     * clause of the method that applies the returned composed predicate. The compiler will not force the declaration in
     * the {@code throws} clause anymore.
     * <p>
     * If the calling-code already handles the sneakily thrown throwable, the compiler requires it to be added to the
     * {@code throws} clause of the method that applies the returned composed predicate. If not added, the compiler will
     * error that the caught throwable is never thrown in the corresponding {@code try} block.
     * <p>
     * If the returned composed predicate is directly surrounded by a {@code try}-{@code catch} block to catch the
     * sneakily thrown throwable from it, the compiler will error that the caught throwable is never thrown in the
     * corresponding {@code try} block.
     * <p>
     * In any case, if the throwable is not added to the to the {@code throws} clause of the method that applies the
     * returned composed predicate, the calling-code won't be able to catch the throwable by name. It will bubble and
     * probably be caught in some {@code catch} statement, catching a base type such as {@code try { ... }
     * catch(RuntimeException e) { ... }} or {@code try { ... } catch(Exception e) { ... }}, but perhaps this is
     * intended.
     * <p>
     * When the called code never throws the specific throwable that it declares, it should obviously be omitted. For
     * example: {@code new String(byteArr, "UTF-8") throws UnsupportedEncodingException}, but {@code UTF-8} is
     * guaranteed by the Java specification to be always present. Here, the {@code throws} declaration is a nuisance and
     * any solution to silence it with minimal boilerplate is welcome. The throwable should therefore be omitted in the
     * {@code throws} clause of the method that applies the returned composed predicate.
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
     *         final Class<?> sneakyThrowingFunctionalInterface("some illegal class name");
     *     } catch(ClassNotFoundException e) {
     *         // ... do something with e ...
     *     }
     * }
     * }</pre>
     * In conclusion, this somewhat contentious ability should be used carefully, of course, with the advantages,
     * disadvantages and limitations described above kept in mind.
     *
     * @return A composed {@link TriBytePredicate} that applies this predicate to its input and sneakily throws the
     * thrown {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}.
     */
    @Nonnull
    default TriBytePredicate sneakyThrow() {
        return (value1, value2, value3) -> {
            try {
                return this.testThrows(value1, value2, value3);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw ThrowableUtils.sneakyThrow(throwable);
            }
        };
    }

}