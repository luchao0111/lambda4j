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
package at.gridtec.lambda4j.function.primitives.conversion.tri;

import at.gridtec.lambda4j.consumer.primitives.ByteConsumer;
import at.gridtec.lambda4j.consumer.primitives.tri.CharTriConsumer;
import at.gridtec.lambda4j.function.TriFunction;
import at.gridtec.lambda4j.function.primitives.ByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ByteToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ByteToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ByteToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ByteToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ByteToLongFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ByteToShortFunction;
import at.gridtec.lambda4j.function.primitives.conversion.CharToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiCharToByteFunction;
import at.gridtec.lambda4j.function.primitives.to.ToCharFunction;
import at.gridtec.lambda4j.function.primitives.to.tri.ToByteTriFunction;
import at.gridtec.lambda4j.function.primitives.tri.CharTriFunction;
import at.gridtec.lambda4j.operators.ternary.CharTernaryOperator;
import at.gridtec.lambda4j.operators.unary.ByteUnaryOperator;
import at.gridtec.lambda4j.operators.unary.CharUnaryOperator;
import at.gridtec.lambda4j.predicates.primitives.BytePredicate;
import at.gridtec.lambda4j.predicates.primitives.tri.CharTriPredicate;
import at.gridtec.lambda4j.supplier.ByteSupplier;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.UnaryOperator;

/**
 * Represents a function that accepts three char-valued arguments and produces a byte-valued result. This is the {@code
 * char}-to-{@code byte} primitive specialization for {@link TriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsByte(char, char, char)}.
 *
 * @see TriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface TriCharToByteFunction {

    /**
     * Calls the given {@link TriCharToByteFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriCharToByteFunction}.
     * @throws NullPointerException If the given function is {@code null}
     */
    static byte call(@Nonnull final TriCharToByteFunction function, char value1, char value2, char value3) {
        Objects.requireNonNull(function);
        return function.applyAsByte(value1, value2, value3);
    }

    /**
     * Creates a {@link TriCharToByteFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@link CharToByteFunction}.
     *
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code TriCharToByteFunction} which uses the {@code first} parameter of this one as argument
     * for the given {@code CharToByteFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static TriCharToByteFunction onlyFirst(@Nonnull final CharToByteFunction function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.applyAsByte(value1);
    }

    /**
     * Creates a {@link TriCharToByteFunction} which uses the {@code second} parameter of this one as argument for the
     * given {@link CharToByteFunction}.
     *
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code TriCharToByteFunction} which uses the {@code second} parameter of this one as argument
     * for the given {@code CharToByteFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static TriCharToByteFunction onlySecond(@Nonnull final CharToByteFunction function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.applyAsByte(value2);
    }

    /**
     * Creates a {@link TriCharToByteFunction} which uses the {@code third} parameter of this one as argument for the
     * given {@link CharToByteFunction}.
     *
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code TriCharToByteFunction} which uses the {@code third} parameter of this one as argument
     * for the given {@code CharToByteFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static TriCharToByteFunction onlyThird(@Nonnull final CharToByteFunction function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.applyAsByte(value3);
    }

    /**
     * Creates a {@link TriCharToByteFunction} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code TriCharToByteFunction} which always returns a given value.
     */
    @Nonnull
    static TriCharToByteFunction constant(byte ret) {
        return (value1, value2, value3) -> ret;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The return value from the function, which is its result.
     */
    byte applyAsByte(char value1, char value2, char value3);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     * @implSpec The default implementation always returns {@code 3}.
     */
    @Nonnegative
    default int arity() {
        return 3;
    }

    /**
     * Returns a composed {@link TriCharToByteFunction} that first applies the {@code before} operations to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param before1 The first operation to apply before this function is applied
     * @param before2 The second operation to apply before this function is applied
     * @param before3 The third operation to apply before this function is applied
     * @return A composed {@code TriCharToByteFunction} that first applies the {@code before} operations to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If one of the given arguments is {@code null}
     * @implNote The input arguments of this method are primitive specializations of {@link UnaryOperator}. Therefore
     * the given operations handle primitive types. In this case this is {@code char}.
     * @see #andThen(ByteFunction)
     */
    @Nonnull
    default TriCharToByteFunction compose(@Nonnull final CharUnaryOperator before1,
            @Nonnull final CharUnaryOperator before2, @Nonnull final CharUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsByte(before1.applyAsChar(value1), before2.applyAsChar(value2),
                                                       before3.applyAsChar(value3));
    }

    /**
     * Returns a composed {@link ToByteTriFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <T> The type of the argument to the first before function
     * @param <U> The type of the argument to the second before function
     * @param <V> The type of the argument to the third before function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ToByteTriFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If one of the given arguments is {@code null}
     * @implNote The input arguments of this method are able to handle every type.
     * @see #andThen(ByteFunction)
     */
    @Nonnull
    default <T, U, V> ToByteTriFunction<T, U, V> compose(@Nonnull final ToCharFunction<? super T> before1,
            @Nonnull final ToCharFunction<? super U> before2, @Nonnull final ToCharFunction<? super V> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (t, u, v) -> applyAsByte(before1.applyAsChar(t), before2.applyAsChar(u), before3.applyAsChar(v));
    }

    /**
     * Returns a composed {@link CharTriFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <R> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code CharTriFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(CharUnaryOperator, CharUnaryOperator, CharUnaryOperator)
     * @see #compose(ToCharFunction, ToCharFunction, ToCharFunction)
     */
    @Nonnull
    default <R> CharTriFunction<R> andThen(@Nonnull final ByteFunction<? extends R> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.apply(applyAsByte(value1, value2, value3));
    }

    /**
     * Returns a composed {@link CharTriPredicate} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code boolean}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code CharTriPredicate} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ByteFunction)
     */
    @Nonnull
    default CharTriPredicate andThenToBoolean(@Nonnull final BytePredicate after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.test(applyAsByte(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriCharToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriCharToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ByteFunction)
     */
    @Nonnull
    default TriCharToByteFunction andThenToByte(@Nonnull final ByteUnaryOperator after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsByte(applyAsByte(value1, value2, value3));
    }

    /**
     * Returns a composed {@link CharTernaryOperator} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code CharTernaryOperator} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ByteFunction)
     */
    @Nonnull
    default CharTernaryOperator andThenToChar(@Nonnull final ByteToCharFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsChar(applyAsByte(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriCharToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either function throws an exception, it is
     * relayed to the caller of the composed function. This method is just convenience, to provide the ability to
     * transform this operation to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriCharToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ByteFunction)
     */
    @Nonnull
    default TriCharToDoubleFunction andThenToDouble(@Nonnull final ByteToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsDouble(applyAsByte(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriCharToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriCharToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ByteFunction)
     */
    @Nonnull
    default TriCharToFloatFunction andThenToFloat(@Nonnull final ByteToFloatFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsFloat(applyAsByte(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriCharToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriCharToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ByteFunction)
     */
    @Nonnull
    default TriCharToIntFunction andThenToInt(@Nonnull final ByteToIntFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsInt(applyAsByte(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriCharToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriCharToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ByteFunction)
     */
    @Nonnull
    default TriCharToLongFunction andThenToLong(@Nonnull final ByteToLongFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsLong(applyAsByte(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriCharToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriCharToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ByteFunction)
     */
    @Nonnull
    default TriCharToShortFunction andThenToShort(@Nonnull final ByteToShortFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsShort(applyAsByte(value1, value2, value3));
    }

    /**
     * Returns a composed {@link CharTriConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link ByteConsumer}. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code CharTriConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@code ByteConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default CharTriConsumer consume(@Nonnull final ByteConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.accept(applyAsByte(value1, value2, value3));
    }

    /**
     * Applies this function partially to one argument. The result is a function of arity {@code 2};
     *
     * @param value1 The argument to partially apply to the function
     * @return A partial application of this function.
     */
    @Nonnull
    default BiCharToByteFunction partial(char value1) {
        return (value2, value3) -> applyAsByte(value1, value2, value3);
    }

    /**
     * Applies this function partially to two arguments. The result is a function of arity {@code 1}.
     *
     * @param value1 The first argument to partially apply to the function
     * @param value2 The second argument to partially apply to the function
     * @return A partial application of this function.
     */
    @Nonnull
    default CharToByteFunction partial(char value1, char value2) {
        return value3 -> applyAsByte(value1, value2, value3);
    }

    /**
     * Applies this function partially to three arguments. The result is an operation of arity {@code 0}.
     *
     * @param value1 The first argument to partially apply to the function
     * @param value2 The second argument to partially apply to the function
     * @param value3 The third argument to partially apply to the function
     * @return A partial application of this function.
     */
    @Nonnull
    default ByteSupplier partial(char value1, char value2, char value3) {
        return () -> applyAsByte(value1, value2, value3);
    }

    /**
     * Returns a composed {@link TriFunction} which represents this {@link TriCharToByteFunction}. Thereby the primitive
     * input argument for this function is autoboxed.
     *
     * @return A composed {@code TriFunction} which represents this {@code TriCharToByteFunction}.
     */
    @Nonnull
    default TriFunction<Character, Character, Character, Byte> boxed() {
        return this::applyAsByte;
    }
}