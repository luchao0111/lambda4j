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
package at.gridtec.lambda4j.operator.binary;

import at.gridtec.lambda4j.Lambda;
import at.gridtec.lambda4j.consumer.bi.BiIntConsumer;
import at.gridtec.lambda4j.function.bi.BiIntFunction;
import at.gridtec.lambda4j.function.bi.conversion.BiBooleanToIntFunction;
import at.gridtec.lambda4j.function.bi.conversion.BiByteToIntFunction;
import at.gridtec.lambda4j.function.bi.conversion.BiCharToIntFunction;
import at.gridtec.lambda4j.function.bi.conversion.BiDoubleToIntFunction;
import at.gridtec.lambda4j.function.bi.conversion.BiFloatToIntFunction;
import at.gridtec.lambda4j.function.bi.conversion.BiIntToByteFunction;
import at.gridtec.lambda4j.function.bi.conversion.BiIntToCharFunction;
import at.gridtec.lambda4j.function.bi.conversion.BiIntToDoubleFunction;
import at.gridtec.lambda4j.function.bi.conversion.BiIntToFloatFunction;
import at.gridtec.lambda4j.function.bi.conversion.BiIntToLongFunction;
import at.gridtec.lambda4j.function.bi.conversion.BiIntToShortFunction;
import at.gridtec.lambda4j.function.bi.conversion.BiLongToIntFunction;
import at.gridtec.lambda4j.function.bi.conversion.BiShortToIntFunction;
import at.gridtec.lambda4j.function.bi.to.ToIntBiFunction2;
import at.gridtec.lambda4j.function.conversion.BooleanToIntFunction;
import at.gridtec.lambda4j.function.conversion.ByteToIntFunction;
import at.gridtec.lambda4j.function.conversion.CharToIntFunction;
import at.gridtec.lambda4j.function.conversion.FloatToIntFunction;
import at.gridtec.lambda4j.function.conversion.IntToByteFunction;
import at.gridtec.lambda4j.function.conversion.IntToCharFunction;
import at.gridtec.lambda4j.function.conversion.IntToFloatFunction;
import at.gridtec.lambda4j.function.conversion.IntToShortFunction;
import at.gridtec.lambda4j.function.conversion.ShortToIntFunction;
import at.gridtec.lambda4j.predicate.bi.BiIntPredicate;

import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BinaryOperator;
import java.util.function.DoubleToIntFunction;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.LongToIntFunction;
import java.util.function.ToIntFunction;

/**
 * Represents an operation that accepts two {@code int}-valued input arguments and produces a
 * {@code int}-valued result.
 * This is a primitive specialization of {@link BinaryOperator2}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsInt(int, int)}.
 *
 * @apiNote This is a JDK lambda.
 * @see BinaryOperator2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface IntBinaryOperator2 extends Lambda, IntBinaryOperator {

    /**
     * Constructs a {@link IntBinaryOperator2} based on a lambda expression or a method reference. Thereby the given
     * lambda expression or method reference is returned on an as-is basis to implicitly transform it to the desired
     * type. With this method, it is possible to ensure that correct type is used from lambda expression or method
     * reference.
     *
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code IntBinaryOperator2} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static IntBinaryOperator2 of(@Nullable final IntBinaryOperator2 expression) {
        return expression;
    }

    /**
     * Calls the given {@link IntBinaryOperator} with the given arguments and returns its result.
     *
     * @param operator The operator to be called
     * @param value1 The first argument to the operator
     * @param value2 The second argument to the operator
     * @return The result from the given {@code IntBinaryOperator2}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static int call(@Nonnull final IntBinaryOperator operator, int value1, int value2) {
        Objects.requireNonNull(operator);
        return operator.applyAsInt(value1, value2);
    }

    /**
     * Creates a {@link IntBinaryOperator2} which uses the {@code first} parameter of this one as argument for the given
     * {@link IntUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code first} parameter of this one
     * @return Creates a {@code IntBinaryOperator2} which uses the {@code first} parameter of this one as argument for
     * the given {@code IntUnaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static IntBinaryOperator2 onlyFirst(@Nonnull final IntUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (value1, value2) -> operator.applyAsInt(value1);
    }

    /**
     * Creates a {@link IntBinaryOperator2} which uses the {@code second} parameter of this one as argument for the
     * given {@link IntUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code second} parameter of this one
     * @return Creates a {@code IntBinaryOperator2} which uses the {@code second} parameter of this one as argument for
     * the given {@code IntUnaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static IntBinaryOperator2 onlySecond(@Nonnull final IntUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (value1, value2) -> operator.applyAsInt(value2);
    }

    /**
     * Creates a {@link IntBinaryOperator2} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code IntBinaryOperator2} which always returns a given value.
     */
    @Nonnull
    static IntBinaryOperator2 constant(int ret) {
        return (value1, value2) -> ret;
    }

    /**
     * Returns a {@link IntBinaryOperator2} which returns the lesser of two elements according to the specified {@code
     * Comparator}.
     *
     * @param comparator A {@code Comparator} for comparing the two values
     * @return A {@code IntBinaryOperator2} which returns the lesser of its operands, according to the supplied {@code
     * Comparator}.
     * @throws NullPointerException If given argument is {@code null}
     * @see BinaryOperator#minBy(Comparator)
     */
    @Nonnull
    static IntBinaryOperator2 minBy(@Nonnull final Comparator<Integer> comparator) {
        Objects.requireNonNull(comparator);
        return (value1, value2) -> comparator.compare(value1, value2) <= 0 ? value1 : value2;
    }

    /**
     * Returns a {@link IntBinaryOperator2} which returns the greater of two elements according to the specified {@code
     * Comparator}.
     *
     * @param comparator A {@code Comparator} for comparing the two values
     * @return A {@code IntBinaryOperator2} which returns the greater of its operands, according to the supplied {@code
     * Comparator}.
     * @throws NullPointerException If given argument is {@code null}
     * @see BinaryOperator#maxBy(Comparator)
     */
    @Nonnull
    static IntBinaryOperator2 maxBy(@Nonnull final Comparator<Integer> comparator) {
        Objects.requireNonNull(comparator);
        return (value1, value2) -> comparator.compare(value1, value2) >= 0 ? value1 : value2;
    }

    /**
     * Applies this operator to the given arguments.
     *
     * @param value1 The first argument to the operator
     * @param value2 The second argument to the operator
     * @return The return value from the operator, which is its result.
     */
    int applyAsInt(int value1, int value2);

    /**
     * Returns the number of arguments for this operator.
     *
     * @return The number of arguments for this operator.
     * @implSpec The default implementation always returns {@code 2}.
     */
    @Nonnegative
    default int arity() {
        return 2;
    }

    /**
     * Returns a composed {@link ToIntBiFunction2} that first applies the {@code before} functions to its input, and
     * then applies this operator to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     *
     * @param <A> The type of the argument to the first given function, and of composed function
     * @param <B> The type of the argument to the second given function, and of composed function
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @return A composed {@code ToIntBiFunction2} that first applies the {@code before} functions to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B> ToIntBiFunction2<A, B> compose(@Nonnull final ToIntFunction<? super A> before1,
            @Nonnull final ToIntFunction<? super B> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (a, b) -> applyAsInt(before1.applyAsInt(a), before2.applyAsInt(b));
    }

    /**
     * Returns a composed {@link BiBooleanToIntFunction} that first applies the {@code before} functions to its input,
     * and then applies this operator to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code boolean} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @return A composed {@code BiBooleanToIntFunction} that first applies the {@code before} functions to its input,
     * and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BiBooleanToIntFunction composeFromBoolean(@Nonnull final BooleanToIntFunction before1,
            @Nonnull final BooleanToIntFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsInt(before1.applyAsInt(value1), before2.applyAsInt(value2));
    }

    /**
     * Returns a composed {@link BiByteToIntFunction} that first applies the {@code before} functions to
     * its input, and then applies this operator to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code byte} input,
     * before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @return A composed {@code BiByteToIntFunction} that first applies the {@code before} functions to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default BiByteToIntFunction composeFromByte(@Nonnull final ByteToIntFunction before1,
            @Nonnull final ByteToIntFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsInt(before1.applyAsInt(value1), before2.applyAsInt(value2));
    }

    /**
     * Returns a composed {@link BiCharToIntFunction} that first applies the {@code before} functions to
     * its input, and then applies this operator to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code char} input,
     * before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @return A composed {@code BiCharToIntFunction} that first applies the {@code before} functions to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default BiCharToIntFunction composeFromChar(@Nonnull final CharToIntFunction before1,
            @Nonnull final CharToIntFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsInt(before1.applyAsInt(value1), before2.applyAsInt(value2));
    }

    /**
     * Returns a composed {@link BiDoubleToIntFunction} that first applies the {@code before} functions to its input,
     * and then applies this operator to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code double} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @return A composed {@code BiDoubleToIntFunction} that first applies the {@code before} functions to its input,
     * and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default BiDoubleToIntFunction composeFromDouble(@Nonnull final DoubleToIntFunction before1,
            @Nonnull final DoubleToIntFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsInt(before1.applyAsInt(value1), before2.applyAsInt(value2));
    }

    /**
     * Returns a composed {@link BiFloatToIntFunction} that first applies the {@code before} functions to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code float} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @return A composed {@code BiFloatToIntFunction} that first applies the {@code before} functions to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default BiFloatToIntFunction composeFromFloat(@Nonnull final FloatToIntFunction before1,
            @Nonnull final FloatToIntFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsInt(before1.applyAsInt(value1), before2.applyAsInt(value2));
    }

    /**
     * Returns a composed {@link IntBinaryOperator2} that first applies the {@code before} operators to
     * its input, and then applies this operator to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code int} input,
     * before this primitive operator is executed.
     *
     * @param before1 The first operator to apply before this operator is applied
     * @param before2 The second operator to apply before this operator is applied
     * @return A composed {@code IntBinaryOperator2} that first applies the {@code before} operators to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default IntBinaryOperator2 composeFromInt(@Nonnull final IntUnaryOperator before1,
            @Nonnull final IntUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsInt(before1.applyAsInt(value1), before2.applyAsInt(value2));
    }

    /**
     * Returns a composed {@link BiLongToIntFunction} that first applies the {@code before} functions to
     * its input, and then applies this operator to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code long} input,
     * before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @return A composed {@code BiLongToIntFunction} that first applies the {@code before} functions to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default BiLongToIntFunction composeFromLong(@Nonnull final LongToIntFunction before1,
            @Nonnull final LongToIntFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsInt(before1.applyAsInt(value1), before2.applyAsInt(value2));
    }

    /**
     * Returns a composed {@link BiShortToIntFunction} that first applies the {@code before} functions to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code short} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @return A composed {@code BiShortToIntFunction} that first applies the {@code before} functions to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default BiShortToIntFunction composeFromShort(@Nonnull final ShortToIntFunction before1,
            @Nonnull final ShortToIntFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsInt(before1.applyAsInt(value1), before2.applyAsInt(value2));
    }

    /**
     * Returns a composed {@link BiIntFunction} that first applies this operator to its input, and then applies the
     * {@code after} function to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this operator is applied
     * @return A composed {@code BiIntFunction} that first applies this operator to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> BiIntFunction<S> andThen(@Nonnull final IntFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.apply(applyAsInt(value1, value2));
    }

    /**
     * Returns a composed {@link BiIntPredicate} that first applies this operator to its input, and then applies the
     * {@code after} predicate to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this operator is applied
     * @return A composed {@code BiIntPredicate} that first applies this operator to its input, and then applies the
     * {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BiIntPredicate andThenToBoolean(@Nonnull final IntPredicate after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.test(applyAsInt(value1, value2));
    }

    /**
     * Returns a composed {@link BiIntToByteFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code byte}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code BiIntToByteFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default BiIntToByteFunction andThenToByte(@Nonnull final IntToByteFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsByte(applyAsInt(value1, value2));
    }

    /**
     * Returns a composed {@link BiIntToCharFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code char}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code BiIntToCharFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default BiIntToCharFunction andThenToChar(@Nonnull final IntToCharFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsChar(applyAsInt(value1, value2));
    }

    /**
     * Returns a composed {@link BiIntToDoubleFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code double}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code BiIntToDoubleFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default BiIntToDoubleFunction andThenToDouble(@Nonnull final IntToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsDouble(applyAsInt(value1, value2));
    }

    /**
     * Returns a composed {@link BiIntToFloatFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code float}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code BiIntToFloatFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default BiIntToFloatFunction andThenToFloat(@Nonnull final IntToFloatFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsFloat(applyAsInt(value1, value2));
    }

    /**
     * Returns a composed {@link IntBinaryOperator2} that first applies this operator to its input, and then applies the
     * {@code after} operator to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code int}.
     *
     * @param after The operator to apply after this operator is applied
     * @return A composed {@code IntBinaryOperator2} that first applies this operator to its input, and then applies the
     * {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default IntBinaryOperator2 andThenToInt(@Nonnull final IntUnaryOperator after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsInt(applyAsInt(value1, value2));
    }

    /**
     * Returns a composed {@link BiIntToLongFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code long}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code BiIntToLongFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default BiIntToLongFunction andThenToLong(@Nonnull final IntToLongFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsLong(applyAsInt(value1, value2));
    }

    /**
     * Returns a composed {@link BiIntToShortFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code short}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code BiIntToShortFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default BiIntToShortFunction andThenToShort(@Nonnull final IntToShortFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsShort(applyAsInt(value1, value2));
    }

    /**
     * Returns a composed {@link BiIntConsumer} that fist applies this operator to its input, and then consumes the
     * result using the given {@link IntConsumer}. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code BiIntConsumer} that first applies this operator to its input, and then consumes the
     * result using the given {@code IntConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default BiIntConsumer consume(@Nonnull final IntConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> consumer.accept(applyAsInt(value1, value2));
    }

    /**
     * Returns a memoized (caching) version of this {@link IntBinaryOperator2}. Whenever it is called, the mapping
     * between the input parameters and the return value is preserved in a cache, making subsequent calls returning the
     * memoized value instead of computing the return value again.
     * <p>
     * Unless the operator and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code IntBinaryOperator2}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized operator, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized operator can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default IntBinaryOperator2 memoized() {
        if (isMemoized()) {
            return this;
        } else {
            final Map<Pair<Integer, Integer>, Integer> cache = new ConcurrentHashMap<>();
            final Object lock = new Object();
            return (IntBinaryOperator2 & Memoized) (value1, value2) -> {
                final int returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Pair.of(value1, value2),
                                                        key -> applyAsInt(key.getLeft(), key.getRight()));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link BinaryOperator} which represents this {@link IntBinaryOperator2}. Thereby the primitive
     * input argument for this operator is autoboxed. This method is just convenience to provide the ability to use this
     * {@code IntBinaryOperator2} with JDK specific methods, only accepting {@code BinaryOperator}.
     *
     * @return A composed {@code BinaryOperator} which represents this {@code IntBinaryOperator2}.
     */
    @Nonnull
    default BinaryOperator<Integer> boxed() {
        return this::applyAsInt;
    }

}