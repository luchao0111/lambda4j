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
package at.gridtec.lambda4j.operators.ternary;

import at.gridtec.lambda4j.operators.unary.ByteUnaryOperator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * Represents an operation on a two {@code byte}-valued operands and producing a {@code byte}-valued result. This is the
 * primitive type specialization of {@link TernaryOperator} for {@code byte}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsByte(byte, byte, byte)}.
 *
 * @see TernaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ByteTernaryOperator {

    /**
     * Creates a {@link ByteTernaryOperator} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code ByteTernaryOperator} which always returns a given value.
     */
    @Nonnull
    static ByteTernaryOperator constant(byte ret) {
        return (left, middle, right) -> ret;
    }

    /**
     * Creates a {@link ByteTernaryOperator} which uses the {@code left} parameter as argument for the given {@link
     * ByteUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code left} parameter of this one
     * @return Creates a {@code ByteTernaryOperator} which uses the {@code left} parameter as argument for the given
     * {@code ByteUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static ByteTernaryOperator onlyLeft(@Nonnull final ByteUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsByte(left);
    }

    /**
     * Creates a {@link ByteTernaryOperator} which uses the {@code middle} parameter as argument for the given {@link
     * ByteUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code middle} parameter of this one
     * @return Creates a {@code ByteTernaryOperator} which uses the {@code middle} parameter as argument for the given
     * {@code ByteUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static ByteTernaryOperator onlyMiddle(@Nonnull final ByteUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsByte(middle);
    }

    /**
     * Creates a {@link ByteTernaryOperator} which uses the {@code right} parameter as argument for the given {@link
     * ByteUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code right} parameter of this one
     * @return Creates a {@code ByteTernaryOperator} which uses the {@code right} parameter as argument for the given
     * {@code ByteUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static ByteTernaryOperator onlyRight(@Nonnull final ByteUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsByte(right);
    }

    /**
     * Applies this operator to the given arguments.
     *
     * @param left The first argument to the operator (left input)
     * @param middle The second argument to the operator (middle input)
     * @param right The third argument to the operator (right input)
     * @return The return value from the operator.
     */
    byte applyAsByte(byte left, byte middle, byte right);

    /**
     * Returns the number of arguments for this operation.
     *
     * @return The number of arguments for this operation.
     */
    @Nonnegative
    default int arity() {
        return 3;
    }
    /**
     * Returns a composed {@link ByteTernaryOperator} that first applies the given {@code before} operators to its
     * input, and then applies this operator to the result. If evaluation of either operator throws an exception, it is
     * relayed to the caller of the composed operator.
     *
     * @param before1 The first {@code ByteUnaryOperator} to apply before this operator is applied
     * @param before2 The second {@code ByteUnaryOperator} to apply before this operator is applied
     * @param before3 The third {@code ByteUnaryOperator} to apply before this operator is applied
     * @return A composed {@code ByteTernaryOperator} that first applies the given {@code before} operators and then
     * applies this operator.
     * @throws NullPointerException If one of the given operators are {@code null}
     * @see #andThen(ByteUnaryOperator)
     */
    default ByteTernaryOperator compose(final ByteUnaryOperator before1, final ByteUnaryOperator before2,
            final ByteUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (left, middle, right) -> applyAsByte(before1.applyAsByte(left), before2.applyAsByte(middle),
                                                    before3.applyAsByte(right));
    }

    /**
     * Returns a composed {@link ByteTernaryOperator} that first applies this operator to its input, and then applies
     * the {@code after} operator to the result. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operator.
     *
     * @param after The {@code ByteUnaryOperator} to apply after this operator is applied
     * @return A composed {@code ByteTernaryOperator} that first applies this operator and then applies the {@code
     * after} operator.
     * @throws NullPointerException If one of the given operators are {@code null}
     * @see #compose(ByteUnaryOperator, ByteUnaryOperator, ByteUnaryOperator)
     */
    default ByteTernaryOperator andThen(ByteUnaryOperator after) {
        Objects.requireNonNull(after);
        return (left, middle, right) -> after.applyAsByte(applyAsByte(left, middle, right));
    }

    /**
     * Returns a composed {@link TernaryOperator} which represents this {@link ByteTernaryOperator}. Thereby the
     * primitive input argument for this operator is autoboxed.
     *
     * @return A composed {@code TernaryOperator} which represents this {@code ByteTernaryOperator}.
     */
    @Nonnull
    default TernaryOperator<Byte> boxed() {
        return this::applyAsByte;
    }
}