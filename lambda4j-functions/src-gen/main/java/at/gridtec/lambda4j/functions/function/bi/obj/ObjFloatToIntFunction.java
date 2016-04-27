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
package at.gridtec.lambda4j.functions.function.bi.obj;

import at.gridtec.lambda4j.functions.Lambda;
import at.gridtec.lambda4j.functions.consumer.bi.obj.ObjFloatConsumer;
import at.gridtec.lambda4j.functions.function.BooleanFunction;
import at.gridtec.lambda4j.functions.function.ByteFunction;
import at.gridtec.lambda4j.functions.function.CharFunction;
import at.gridtec.lambda4j.functions.function.FloatFunction;
import at.gridtec.lambda4j.functions.function.ShortFunction;
import at.gridtec.lambda4j.functions.function.bi.BiFunction2;
import at.gridtec.lambda4j.functions.function.bi.conversion.BiBooleanToIntFunction;
import at.gridtec.lambda4j.functions.function.bi.conversion.BiByteToIntFunction;
import at.gridtec.lambda4j.functions.function.bi.conversion.BiCharToIntFunction;
import at.gridtec.lambda4j.functions.function.bi.conversion.BiDoubleToIntFunction;
import at.gridtec.lambda4j.functions.function.bi.conversion.BiFloatToIntFunction;
import at.gridtec.lambda4j.functions.function.bi.conversion.BiLongToIntFunction;
import at.gridtec.lambda4j.functions.function.bi.conversion.BiShortToIntFunction;
import at.gridtec.lambda4j.functions.function.bi.to.ToIntBiFunction2;
import at.gridtec.lambda4j.functions.function.conversion.BooleanToFloatFunction;
import at.gridtec.lambda4j.functions.function.conversion.ByteToFloatFunction;
import at.gridtec.lambda4j.functions.function.conversion.CharToFloatFunction;
import at.gridtec.lambda4j.functions.function.conversion.DoubleToFloatFunction;
import at.gridtec.lambda4j.functions.function.conversion.FloatToIntFunction;
import at.gridtec.lambda4j.functions.function.conversion.IntToByteFunction;
import at.gridtec.lambda4j.functions.function.conversion.IntToCharFunction;
import at.gridtec.lambda4j.functions.function.conversion.IntToFloatFunction;
import at.gridtec.lambda4j.functions.function.conversion.IntToShortFunction;
import at.gridtec.lambda4j.functions.function.conversion.LongToFloatFunction;
import at.gridtec.lambda4j.functions.function.conversion.ShortToFloatFunction;
import at.gridtec.lambda4j.functions.function.to.ToFloatFunction;
import at.gridtec.lambda4j.functions.operator.binary.IntBinaryOperator2;
import at.gridtec.lambda4j.functions.operator.unary.FloatUnaryOperator;
import at.gridtec.lambda4j.functions.predicate.bi.obj.ObjFloatPredicate;

import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.LongFunction;
import java.util.function.ToIntFunction;

/**
 * Represents an operation that accepts one object-valued and one {@code float}-valued input argument and produces a
 * {@code int}-valued result. This is a (reference, float) specialization of {@link BiFunction2}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsInt(Object, float)}.
 *
 * @param <T> The type of the first argument to the function
 * @see BiFunction2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ObjFloatToIntFunction<T> extends Lambda {

    /**
     * Constructs a {@link ObjFloatToIntFunction} based on a lambda expression or a method reference. Thereby the given
     * lambda expression or method reference is returned on an as-is basis to implicitly transform it to the desired
     * type. With this method, it is possible to ensure that correct type is used from lambda expression or method
     * reference.
     *
     * @param <T> The type of the first argument to the function
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ObjFloatToIntFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but if {@code null} given, {@code
     * null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @Nonnull
    static <T> ObjFloatToIntFunction<T> of(@Nonnull final ObjFloatToIntFunction<T> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ObjFloatToIntFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param value The second argument to the function
     * @return The result from the given {@code ObjFloatToIntFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static <T> int call(@Nonnull final ObjFloatToIntFunction<? super T> function, T t, float value) {
        Objects.requireNonNull(function);
        return function.applyAsInt(t, value);
    }

    /**
     * Creates a {@link ObjFloatToIntFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@link ToIntFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ObjFloatToIntFunction} which uses the {@code first} parameter of this one as argument
     * for the given {@code ToIntFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T> ObjFloatToIntFunction<T> onlyFirst(@Nonnull final ToIntFunction<? super T> function) {
        Objects.requireNonNull(function);
        return (t, value) -> function.applyAsInt(t);
    }

    /**
     * Creates a {@link ObjFloatToIntFunction} which uses the {@code second} parameter of this one as argument for the
     * given {@link FloatToIntFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ObjFloatToIntFunction} which uses the {@code second} parameter of this one as argument
     * for the given {@code FloatToIntFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T> ObjFloatToIntFunction<T> onlySecond(@Nonnull final FloatToIntFunction function) {
        Objects.requireNonNull(function);
        return (t, value) -> function.applyAsInt(value);
    }

    /**
     * Creates a {@link ObjFloatToIntFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param ret The return value for the constant
     * @return A {@code ObjFloatToIntFunction} which always returns a given value.
     */
    @Nonnull
    static <T> ObjFloatToIntFunction<T> constant(int ret) {
        return (t, value) -> ret;
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param t The first argument to the function
     * @param value The second argument to the function
     * @return The return value from the function, which is its result.
     */
    int applyAsInt(T t, float value);

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
     * Returns a composed {@link ToIntBiFunction2} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <A> The type of the argument to the first given function, and of composed function
     * @param <B> The type of the argument to the second given function, and of composed function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code ToIntBiFunction2} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B> ToIntBiFunction2<A, B> compose(@Nonnull final Function<? super A, ? extends T> before1,
            @Nonnull final ToFloatFunction<? super B> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (a, b) -> applyAsInt(before1.apply(a), before2.applyAsFloat(b));
    }

    /**
     * Returns a composed {@link BiBooleanToIntFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code boolean} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiBooleanToIntFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BiBooleanToIntFunction composeFromBoolean(@Nonnull final BooleanFunction<? extends T> before1,
            @Nonnull final BooleanToFloatFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsInt(before1.apply(value1), before2.applyAsFloat(value2));
    }

    /**
     * Returns a composed {@link BiByteToIntFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code byte} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiByteToIntFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default BiByteToIntFunction composeFromByte(@Nonnull final ByteFunction<? extends T> before1,
            @Nonnull final ByteToFloatFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsInt(before1.apply(value1), before2.applyAsFloat(value2));
    }

    /**
     * Returns a composed {@link BiCharToIntFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code char} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiCharToIntFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default BiCharToIntFunction composeFromChar(@Nonnull final CharFunction<? extends T> before1,
            @Nonnull final CharToFloatFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsInt(before1.apply(value1), before2.applyAsFloat(value2));
    }

    /**
     * Returns a composed {@link BiDoubleToIntFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code double} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiDoubleToIntFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default BiDoubleToIntFunction composeFromDouble(@Nonnull final DoubleFunction<? extends T> before1,
            @Nonnull final DoubleToFloatFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsInt(before1.apply(value1), before2.applyAsFloat(value2));
    }

    /**
     * Returns a composed {@link BiFloatToIntFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code float} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second operator to apply before this function is applied
     * @return A composed {@code BiFloatToIntFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default BiFloatToIntFunction composeFromFloat(@Nonnull final FloatFunction<? extends T> before1,
            @Nonnull final FloatUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsInt(before1.apply(value1), before2.applyAsFloat(value2));
    }

    /**
     * Returns a composed {@link IntBinaryOperator2} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code int} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code IntBinaryOperator2} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default IntBinaryOperator2 composeFromInt(@Nonnull final IntFunction<? extends T> before1,
            @Nonnull final IntToFloatFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsInt(before1.apply(value1), before2.applyAsFloat(value2));
    }

    /**
     * Returns a composed {@link BiLongToIntFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code long} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiLongToIntFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default BiLongToIntFunction composeFromLong(@Nonnull final LongFunction<? extends T> before1,
            @Nonnull final LongToFloatFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsInt(before1.apply(value1), before2.applyAsFloat(value2));
    }

    /**
     * Returns a composed {@link BiShortToIntFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiShortToIntFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default BiShortToIntFunction composeFromShort(@Nonnull final ShortFunction<? extends T> before1,
            @Nonnull final ShortToFloatFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsInt(before1.apply(value1), before2.applyAsFloat(value2));
    }

    /**
     * Returns a composed {@link ObjFloatFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjFloatFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ObjFloatFunction<T, S> andThen(@Nonnull final IntFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.apply(applyAsInt(t, value));
    }

    /**
     * Returns a composed {@link ObjFloatPredicate} that first applies this function to its input, and then applies the
     * {@code after} predicate to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this function is applied
     * @return A composed {@code ObjFloatPredicate} that first applies this function to its input, and then applies the
     * {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ObjFloatPredicate<T> andThenToBoolean(@Nonnull final IntPredicate after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.test(applyAsInt(t, value));
    }

    /**
     * Returns a composed {@link ObjFloatToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjFloatToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ObjFloatToByteFunction<T> andThenToByte(@Nonnull final IntToByteFunction after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsByte(applyAsInt(t, value));
    }

    /**
     * Returns a composed {@link ObjFloatToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjFloatToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ObjFloatToCharFunction<T> andThenToChar(@Nonnull final IntToCharFunction after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsChar(applyAsInt(t, value));
    }

    /**
     * Returns a composed {@link ObjFloatToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjFloatToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ObjFloatToDoubleFunction<T> andThenToDouble(@Nonnull final IntToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsDouble(applyAsInt(t, value));
    }

    /**
     * Returns a composed {@link ObjFloatToFloatFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjFloatToFloatFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ObjFloatToFloatFunction<T> andThenToFloat(@Nonnull final IntToFloatFunction after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsFloat(applyAsInt(t, value));
    }

    /**
     * Returns a composed {@link ObjFloatToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code int}.
     *
     * @param after The operator to apply after this function is applied
     * @return A composed {@code ObjFloatToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ObjFloatToIntFunction<T> andThenToInt(@Nonnull final IntUnaryOperator after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsInt(applyAsInt(t, value));
    }

    /**
     * Returns a composed {@link ObjFloatToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjFloatToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ObjFloatToLongFunction<T> andThenToLong(@Nonnull final IntToLongFunction after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsLong(applyAsInt(t, value));
    }

    /**
     * Returns a composed {@link ObjFloatToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjFloatToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ObjFloatToShortFunction<T> andThenToShort(@Nonnull final IntToShortFunction after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsShort(applyAsInt(t, value));
    }

    /**
     * Returns a composed {@link ObjFloatConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link IntConsumer}. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ObjFloatConsumer} that first applies this function to its input, and then consumes the
     * result using the given {@code IntConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ObjFloatConsumer<T> consume(@Nonnull final IntConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, value) -> consumer.accept(applyAsInt(t, value));
    }

    /**
     * Returns a memoized (caching) version of this {@link ObjFloatToIntFunction}. Whenever it is called, the mapping
     * between the input parameters and the return value is preserved in a cache, making subsequent calls returning the
     * memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ObjFloatToIntFunction}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ObjFloatToIntFunction<T> memoized() {
        if (this instanceof Memoized) {
            return this;
        } else {
            final Map<Pair<T, Float>, Integer> cache = new ConcurrentHashMap<>();
            final Object lock = new Object();
            return (ObjFloatToIntFunction<T> & Memoized) (t, value) -> {
                final int returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Pair.of(t, value),
                                                        key -> applyAsInt(key.getLeft(), key.getRight()));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link BiFunction} which represents this {@link ObjFloatToIntFunction}. Thereby the primitive
     * input argument for this function is autoboxed. This method is just convenience to provide the ability to use this
     * {@code ObjFloatToIntFunction} with JDK specific methods, only accepting {@code BiFunction}.
     *
     * @return A composed {@code BiFunction} which represents this {@code ObjFloatToIntFunction}.
     */
    @Nonnull
    default BiFunction<T, Float, Integer> boxed() {
        return this::applyAsInt;
    }

}