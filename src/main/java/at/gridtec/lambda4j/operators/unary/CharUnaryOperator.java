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
package at.gridtec.lambda4j.operators.unary;

import at.gridtec.lambda4j.consumer.primitives.CharConsumer;
import at.gridtec.lambda4j.function.primitives.CharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.CharToBooleanFunction;
import at.gridtec.lambda4j.function.primitives.conversion.CharToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.CharToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.CharToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.CharToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.CharToLongFunction;
import at.gridtec.lambda4j.function.primitives.conversion.CharToShortFunction;
import at.gridtec.lambda4j.function.primitives.to.ToCharFunction;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * Represents an operation on a single {@code char}-valued operand that produces a {@code char}-valued result. This is
 * the primitive type specialization of {@link UnaryOperator} for {@code char}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsChar(char)}.
 *
 * @see java.util.function.UnaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface CharUnaryOperator {

    /**
     * Creates a {@link CharUnaryOperator} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code CharUnaryOperator} which always returns a given value.
     */
    @Nonnull
    static CharUnaryOperator constant(char ret) {
        return operand -> ret;
    }

    /**
     * Returns a {@link CharUnaryOperator} that always returns its input argument.
     *
     * @return A {@code CharUnaryOperator} that always returns its input argument
     */
    @Nonnull
    static CharUnaryOperator identity() {
        return operand -> operand;
    }

    /**
     * Applies this operator to the given argument.
     *
     * @param operand The argument to the operator
     * @return The result of this operator.
     */
    char applyAsChar(char operand);

    /**
     * Returns the number of arguments for this operation.
     *
     * @return The number of arguments for this operation.
     * @implSpec The default implementation always returns {@code 1}.
     */
    @Nonnegative
    default int arity() {
        return 1;
    }

    /**
     * Returns a composed {@link CharUnaryOperator} that first applies the {@code before} operator to its input, and
     * then applies this operator to the result. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operator.
     *
     * @param before The operator to apply before this operator is applied
     * @return A composed {@link CharUnaryOperator} that first applies the {@code before} operator to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is the primitive specialization of {@link UnaryOperator}. Therefore
     * the given operation handles primitive types. In this case this is {@code char}.
     * @see #andThen(CharUnaryOperator)
     * @see #andThen(CharFunction)
     */
    @Nonnull
    default CharUnaryOperator compose(@Nonnull final CharUnaryOperator before) {
        Objects.requireNonNull(before);
        return operand -> applyAsChar(before.applyAsChar(operand));
    }

    /**
     * Returns a composed {@link ToCharFunction} that first applies the {@code before} operation to its input, and then
     * applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <T> The type of the argument to the before operation
     * @param before The operation to apply before this operator is applied
     * @return A composed {@link ToCharFunction} that first applies the {@code before} operation to its input, and then
     * applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to handle every type.
     * @see #andThen(CharUnaryOperator)
     * @see #andThen(CharFunction)
     */
    @Nonnull
    default <T> ToCharFunction<T> compose(@Nonnull final ToCharFunction<? super T> before) {
        Objects.requireNonNull(before);
        return t -> applyAsChar(before.applyAsChar(t));
    }

    /**
     * Returns a composed {@link CharUnaryOperator} that first applies this operator to its input, and then applies the
     * {@code after} operator to the result. If evaluation of either operator throws an exception, it is relayed to the
     * caller of the composed operator.
     *
     * @param after The operator to apply after this operator is applied
     * @return A composed {@code CharUnaryOperator} that first applies this operator to its input, and then applies the
     * {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The result of this method is the primitive specialization of {@link UnaryOperator}. Therefore the
     * returned operation handles primitive types. In this case this is {@code char}.
     * @see #compose(CharUnaryOperator)
     * @see #compose(ToCharFunction)
     */
    @Nonnull
    default CharUnaryOperator andThen(@Nonnull final CharUnaryOperator after) {
        Objects.requireNonNull(after);
        return operand -> after.applyAsChar(applyAsChar(operand));
    }

    /**
     * Returns a composed {@link CharFunction} that first applies this operator to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <R> The type of return value from the {@code after} operation, and of the composed operation
     * @param after The operation to apply after this operator is applied
     * @return A composed {@code CharFunction} that first applies this operator to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The returned operation is able to handle every type.
     * @see #compose(CharUnaryOperator)
     * @see #compose(ToCharFunction)
     */
    @Nonnull
    default <R> CharFunction<R> andThen(@Nonnull final CharFunction<? extends R> after) {
        Objects.requireNonNull(after);
        return value -> after.apply(applyAsChar(value));
    }

    /**
     * Returns a composed {@link CharToBooleanFunction} that first applies this operator to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation. This method is just convenience, to provide the ability to transform
     * this operator to {@code boolean}, using the {@code char}-to-{@code boolean} primitive specialization of {@link
     * Function}.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@code CharToBooleanFunction} that first gets the result from this operation, and then applies
     * the {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default CharToBooleanFunction toBoolean(@Nonnull final CharToBooleanFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsBoolean(applyAsChar(value));
    }

    /**
     * Returns a composed {@link CharToByteFunction} that first applies this operator to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * operator to {@code byte}, using the {@code char}-to-{@code byte} primitive specialization of {@link Function}.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@code CharToByteFunction} that first gets the result from this operation, and then applies
     * the {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default CharToByteFunction toByte(@Nonnull final CharToByteFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsByte(applyAsChar(value));
    }

    /**
     * Returns a composed {@link CharToDoubleFunction} that first applies this operator to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation. This method is just convenience, to provide the ability to transform
     * this operator to {@code double}, using the {@code char}-to-{@code double} primitive specialization of {@link
     * Function}.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@code CharToDoubleFunction} that first gets the result from this operation, and then applies
     * the {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default CharToDoubleFunction toDouble(@Nonnull final CharToDoubleFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsDouble(applyAsChar(value));
    }

    /**
     * Returns a composed {@link CharToFloatFunction} that first applies this operator to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation. This method is just convenience, to provide the ability to transform
     * this operator to {@code float}, using the {@code char}-to-{@code float} primitive specialization of {@link
     * Function}.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@code CharToFloatFunction} that first gets the result from this operation, and then applies
     * the {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default CharToFloatFunction toFloat(@Nonnull final CharToFloatFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsFloat(applyAsChar(value));
    }

    /**
     * Returns a composed {@link CharToIntFunction} that first applies this operator to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * operator to {@code int}, using the {@code char}-to-{@code int} primitive specialization of {@link Function}.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@code CharToIntFunction} that first gets the result from this operation, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default CharToIntFunction toInt(@Nonnull final CharToIntFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsInt(applyAsChar(value));
    }

    /**
     * Returns a composed {@link CharToLongFunction} that first applies this operator to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * operator to {@code long}, using the {@code char}-to-{@code long} primitive specialization of {@link Function}.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@code CharToLongFunction} that first gets the result from this operation, and then applies
     * the {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default CharToLongFunction toLong(@Nonnull final CharToLongFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsLong(applyAsChar(value));
    }

    /**
     * Returns a composed {@link CharToShortFunction} that first applies this operator to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation. This method is just convenience, to provide the ability to transform
     * this operator to {@code short}, using the {@code char}-to-{@code short} primitive specialization of {@link
     * Function}.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@code CharToShortFunction} that first gets the result from this operation, and then applies
     * the {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default CharToShortFunction toShort(@Nonnull final CharToShortFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsShort(applyAsChar(value));
    }

    /**
     * Returns a composed {@link CharConsumer} that fist applies this operator to its input, and then consumes the
     * result using the given {@code CharConsumer}. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code CharConsumer} that first applies this operation to its input, and then consumes the
     * result using the given {@code CharConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default CharConsumer consume(@Nonnull final CharConsumer consumer) {
        Objects.requireNonNull(consumer);
        return value -> consumer.accept(applyAsChar(value));
    }

    /**
     * Returns a composed {@link UnaryOperator} which represents this {@link CharUnaryOperator}. Thereby the primitive
     * input argument for this operator is autoboxed. This method is just convenience to provide the ability to use this
     * {@code CharUnaryOperator} with JRE specific methods, only accepting {@code UnaryOperator}.
     *
     * @return A composed {@code UnaryOperator} which represents this {@code CharUnaryOperator}.
     */
    @Nonnull
    default UnaryOperator<Character> boxed() {
        return this::applyAsChar;
    }
}