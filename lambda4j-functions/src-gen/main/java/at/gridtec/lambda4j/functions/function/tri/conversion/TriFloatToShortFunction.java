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
package at.gridtec.lambda4j.functions.function.tri.conversion;

import at.gridtec.lambda4j.functions.Lambda;
import at.gridtec.lambda4j.functions.consumer.ShortConsumer;
import at.gridtec.lambda4j.functions.consumer.tri.TriFloatConsumer;
import at.gridtec.lambda4j.functions.function.ShortFunction;
import at.gridtec.lambda4j.functions.function.conversion.BooleanToFloatFunction;
import at.gridtec.lambda4j.functions.function.conversion.ByteToFloatFunction;
import at.gridtec.lambda4j.functions.function.conversion.CharToFloatFunction;
import at.gridtec.lambda4j.functions.function.conversion.DoubleToFloatFunction;
import at.gridtec.lambda4j.functions.function.conversion.FloatToShortFunction;
import at.gridtec.lambda4j.functions.function.conversion.IntToFloatFunction;
import at.gridtec.lambda4j.functions.function.conversion.LongToFloatFunction;
import at.gridtec.lambda4j.functions.function.conversion.ShortToByteFunction;
import at.gridtec.lambda4j.functions.function.conversion.ShortToCharFunction;
import at.gridtec.lambda4j.functions.function.conversion.ShortToDoubleFunction;
import at.gridtec.lambda4j.functions.function.conversion.ShortToFloatFunction;
import at.gridtec.lambda4j.functions.function.conversion.ShortToIntFunction;
import at.gridtec.lambda4j.functions.function.conversion.ShortToLongFunction;
import at.gridtec.lambda4j.functions.function.to.ToFloatFunction;
import at.gridtec.lambda4j.functions.function.tri.TriFloatFunction;
import at.gridtec.lambda4j.functions.function.tri.TriFunction;
import at.gridtec.lambda4j.functions.function.tri.to.ToShortTriFunction;
import at.gridtec.lambda4j.functions.operator.ternary.FloatTernaryOperator;
import at.gridtec.lambda4j.functions.operator.ternary.ShortTernaryOperator;
import at.gridtec.lambda4j.functions.operator.unary.FloatUnaryOperator;
import at.gridtec.lambda4j.functions.operator.unary.ShortUnaryOperator;
import at.gridtec.lambda4j.functions.predicate.ShortPredicate;
import at.gridtec.lambda4j.functions.predicate.tri.TriFloatPredicate;

import org.apache.commons.lang3.tuple.Triple;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Represents an operation that accepts three {@code float}-valued input arguments and produces a {@code short}-valued
 * result. This is a primitive specialization of {@link TriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsShort(float, float, float)}.
 *
 * @see TriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface TriFloatToShortFunction extends Lambda {

    /**
     * Constructs a {@link TriFloatToShortFunction} based on a lambda expression or a method reference. Thereby the
     * given lambda expression or method reference is returned on an as-is basis to implicitly transform it to the
     * desired type. With this method, it is possible to ensure that correct type is used from lambda expression or
     * method reference.
     *
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code TriFloatToShortFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but if {@code null} given, {@code
     * null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @Nonnull
    static TriFloatToShortFunction of(@Nonnull final TriFloatToShortFunction expression) {
        return expression;
    }

    /**
     * Calls the given {@link TriFloatToShortFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriFloatToShortFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static short call(@Nonnull final TriFloatToShortFunction function, float value1, float value2, float value3) {
        Objects.requireNonNull(function);
        return function.applyAsShort(value1, value2, value3);
    }

    /**
     * Creates a {@link TriFloatToShortFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@link FloatToShortFunction}.
     *
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code TriFloatToShortFunction} which uses the {@code first} parameter of this one as argument
     * for the given {@code FloatToShortFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static TriFloatToShortFunction onlyFirst(@Nonnull final FloatToShortFunction function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.applyAsShort(value1);
    }

    /**
     * Creates a {@link TriFloatToShortFunction} which uses the {@code second} parameter of this one as argument for the
     * given {@link FloatToShortFunction}.
     *
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code TriFloatToShortFunction} which uses the {@code second} parameter of this one as argument
     * for the given {@code FloatToShortFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static TriFloatToShortFunction onlySecond(@Nonnull final FloatToShortFunction function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.applyAsShort(value2);
    }

    /**
     * Creates a {@link TriFloatToShortFunction} which uses the {@code third} parameter of this one as argument for the
     * given {@link FloatToShortFunction}.
     *
     * @param function The function which accepts the {@code third} parameter of this one
     * @return Creates a {@code TriFloatToShortFunction} which uses the {@code third} parameter of this one as argument
     * for the given {@code FloatToShortFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static TriFloatToShortFunction onlyThird(@Nonnull final FloatToShortFunction function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.applyAsShort(value3);
    }

    /**
     * Creates a {@link TriFloatToShortFunction} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code TriFloatToShortFunction} which always returns a given value.
     */
    @Nonnull
    static TriFloatToShortFunction constant(short ret) {
        return (value1, value2, value3) -> ret;
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The return value from the function, which is its result.
     */
    short applyAsShort(float value1, float value2, float value3);

    /**
     * Returns the number of arguments for this function.
     *
     * @return The number of arguments for this function.
     * @implSpec The default implementation always returns {@code 3}.
     */
    @Nonnegative
    default int arity() {
        return 3;
    }

    /**
     * Returns a composed {@link ToShortTriFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <A> The type of the argument to the first given function, and of composed function
     * @param <B> The type of the argument to the second given function, and of composed function
     * @param <C> The type of the argument to the third given function, and of composed function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ToShortTriFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B, C> ToShortTriFunction<A, B, C> compose(@Nonnull final ToFloatFunction<? super A> before1,
            @Nonnull final ToFloatFunction<? super B> before2, @Nonnull final ToFloatFunction<? super C> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> applyAsShort(before1.applyAsFloat(a), before2.applyAsFloat(b), before3.applyAsFloat(c));
    }

    /**
     * Returns a composed {@link TriBooleanToShortFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code boolean} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriBooleanToShortFunction} that first applies the {@code before} functions to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default TriBooleanToShortFunction composeFromBoolean(@Nonnull final BooleanToFloatFunction before1,
            @Nonnull final BooleanToFloatFunction before2, @Nonnull final BooleanToFloatFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsShort(before1.applyAsFloat(value1), before2.applyAsFloat(value2),
                                                        before3.applyAsFloat(value3));
    }

    /**
     * Returns a composed {@link TriByteToShortFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code byte} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriByteToShortFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default TriByteToShortFunction composeFromByte(@Nonnull final ByteToFloatFunction before1,
            @Nonnull final ByteToFloatFunction before2, @Nonnull final ByteToFloatFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsShort(before1.applyAsFloat(value1), before2.applyAsFloat(value2),
                                                        before3.applyAsFloat(value3));
    }

    /**
     * Returns a composed {@link TriCharToShortFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code char} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriCharToShortFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default TriCharToShortFunction composeFromChar(@Nonnull final CharToFloatFunction before1,
            @Nonnull final CharToFloatFunction before2, @Nonnull final CharToFloatFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsShort(before1.applyAsFloat(value1), before2.applyAsFloat(value2),
                                                        before3.applyAsFloat(value3));
    }

    /**
     * Returns a composed {@link TriDoubleToShortFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code double} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriDoubleToShortFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default TriDoubleToShortFunction composeFromDouble(@Nonnull final DoubleToFloatFunction before1,
            @Nonnull final DoubleToFloatFunction before2, @Nonnull final DoubleToFloatFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsShort(before1.applyAsFloat(value1), before2.applyAsFloat(value2),
                                                        before3.applyAsFloat(value3));
    }

    /**
     * Returns a composed {@link TriFloatToShortFunction} that first applies the {@code before} operators to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code float} input, before this primitive function is executed.
     *
     * @param before1 The first operator to apply before this function is applied
     * @param before2 The second operator to apply before this function is applied
     * @param before3 The third operator to apply before this function is applied
     * @return A composed {@code TriFloatToShortFunction} that first applies the {@code before} operators to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default TriFloatToShortFunction composeFromFloat(@Nonnull final FloatUnaryOperator before1,
            @Nonnull final FloatUnaryOperator before2, @Nonnull final FloatUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsShort(before1.applyAsFloat(value1), before2.applyAsFloat(value2),
                                                        before3.applyAsFloat(value3));
    }

    /**
     * Returns a composed {@link TriIntToShortFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code int} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriIntToShortFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default TriIntToShortFunction composeFromInt(@Nonnull final IntToFloatFunction before1,
            @Nonnull final IntToFloatFunction before2, @Nonnull final IntToFloatFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsShort(before1.applyAsFloat(value1), before2.applyAsFloat(value2),
                                                        before3.applyAsFloat(value3));
    }

    /**
     * Returns a composed {@link TriLongToShortFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code long} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriLongToShortFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default TriLongToShortFunction composeFromLong(@Nonnull final LongToFloatFunction before1,
            @Nonnull final LongToFloatFunction before2, @Nonnull final LongToFloatFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsShort(before1.applyAsFloat(value1), before2.applyAsFloat(value2),
                                                        before3.applyAsFloat(value3));
    }

    /**
     * Returns a composed {@link ShortTernaryOperator} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ShortTernaryOperator} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ShortTernaryOperator composeFromShort(@Nonnull final ShortToFloatFunction before1,
            @Nonnull final ShortToFloatFunction before2, @Nonnull final ShortToFloatFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsShort(before1.applyAsFloat(value1), before2.applyAsFloat(value2),
                                                        before3.applyAsFloat(value3));
    }

    /**
     * Returns a composed {@link TriFloatFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriFloatFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> TriFloatFunction<S> andThen(@Nonnull final ShortFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.apply(applyAsShort(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriFloatPredicate} that first applies this function to its input, and then applies the
     * {@code after} predicate to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this function is applied
     * @return A composed {@code TriFloatPredicate} that first applies this function to its input, and then applies the
     * {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default TriFloatPredicate andThenToBoolean(@Nonnull final ShortPredicate after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.test(applyAsShort(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriFloatToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriFloatToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default TriFloatToByteFunction andThenToByte(@Nonnull final ShortToByteFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsByte(applyAsShort(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriFloatToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriFloatToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default TriFloatToCharFunction andThenToChar(@Nonnull final ShortToCharFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsChar(applyAsShort(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriFloatToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriFloatToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default TriFloatToDoubleFunction andThenToDouble(@Nonnull final ShortToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsDouble(applyAsShort(value1, value2, value3));
    }

    /**
     * Returns a composed {@link FloatTernaryOperator} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code FloatTernaryOperator} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default FloatTernaryOperator andThenToFloat(@Nonnull final ShortToFloatFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsFloat(applyAsShort(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriFloatToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriFloatToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default TriFloatToIntFunction andThenToInt(@Nonnull final ShortToIntFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsInt(applyAsShort(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriFloatToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriFloatToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default TriFloatToLongFunction andThenToLong(@Nonnull final ShortToLongFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsLong(applyAsShort(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriFloatToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code short}.
     *
     * @param after The operator to apply after this function is applied
     * @return A composed {@code TriFloatToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default TriFloatToShortFunction andThenToShort(@Nonnull final ShortUnaryOperator after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsShort(applyAsShort(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriFloatConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link ShortConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code TriFloatConsumer} that first applies this function to its input, and then consumes the
     * result using the given {@code ShortConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default TriFloatConsumer consume(@Nonnull final ShortConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.accept(applyAsShort(value1, value2, value3));
    }

    /**
     * Returns a memoized (caching) version of this {@link TriFloatToShortFunction}. Whenever it is called, the mapping
     * between the input parameters and the return value is preserved in a cache, making subsequent calls returning the
     * memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code TriFloatToShortFunction}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default TriFloatToShortFunction memoized() {
        if (this instanceof Memoized) {
            return this;
        } else {
            final Map<Triple<Float, Float, Float>, Short> cache = new ConcurrentHashMap<>();
            final Object lock = new Object();
            return (TriFloatToShortFunction & Memoized) (value1, value2, value3) -> {
                final short returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Triple.of(value1, value2, value3),
                                                        key -> applyAsShort(key.getLeft(), key.getMiddle(),
                                                                            key.getRight()));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link TriFunction} which represents this {@link TriFloatToShortFunction}. Thereby the
     * primitive input argument for this function is autoboxed. This method is just convenience to provide the ability
     * to use this {@code TriFloatToShortFunction} with JDK specific methods, only accepting {@code TriFunction}.
     *
     * @return A composed {@code TriFunction} which represents this {@code TriFloatToShortFunction}.
     */
    @Nonnull
    default TriFunction<Float, Float, Float, Short> boxed() {
        return this::applyAsShort;
    }

}