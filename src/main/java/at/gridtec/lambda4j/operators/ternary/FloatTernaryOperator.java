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

import at.gridtec.lambda4j.operators.unary.FloatUnaryOperator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * Represents an operation on a two {@code float}-valued operands and producing a {@code float}-valued result. This is
 * the primitive type specialization of {@link TernaryOperator} for {@code float}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsFloat(float, float, float)}.
 *
 * @see TernaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface FloatTernaryOperator {

    /**
     * Creates a {@link FloatTernaryOperator} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code FloatTernaryOperator} which always returns a given value.
     */
    @Nonnull
    static FloatTernaryOperator constant(float ret) {
        return (left, middle, right) -> ret;
    }

    /**
     * Creates a {@link FloatTernaryOperator} which uses the {@code left} parameter as argument for the given {@link
     * FloatUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code left} parameter of this one
     * @return Creates a {@code FloatTernaryOperator} which uses the {@code left} parameter as argument for the given
     * {@code FloatUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static FloatTernaryOperator onlyLeft(@Nonnull final FloatUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsFloat(left);
    }

    /**
     * Creates a {@link FloatTernaryOperator} which uses the {@code middle} parameter as argument for the given {@link
     * FloatUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code middle} parameter of this one
     * @return Creates a {@code FloatTernaryOperator} which uses the {@code middle} parameter as argument for the given
     * {@code FloatUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static FloatTernaryOperator onlyMiddle(@Nonnull final FloatUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsFloat(middle);
    }

    /**
     * Creates a {@link FloatTernaryOperator} which uses the {@code right} parameter as argument for the given {@link
     * FloatUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code right} parameter of this one
     * @return Creates a {@code FloatTernaryOperator} which uses the {@code right} parameter as argument for the given
     * {@code FloatUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static FloatTernaryOperator onlyRight(@Nonnull final FloatUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsFloat(right);
    }

    /**
     * Applies this operator to the given arguments.
     *
     * @param left The first argument to the operator (left input)
     * @param middle The second argument to the operator (middle input)
     * @param right The third argument to the operator (right input)
     * @return The return value from the operator.
     */
    float applyAsFloat(float left, float middle, float right);

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
     * Returns a composed {@link FloatTernaryOperator} that first applies the given {@code before} operators to its
     * input, and then applies this operator to the result. If evaluation of either operator throws an exception, it is
     * relayed to the caller of the composed operator.
     *
     * @param before1 The first {@code FloatUnaryOperator} to apply before this operator is applied
     * @param before2 The second {@code FloatUnaryOperator} to apply before this operator is applied
     * @param before3 The third {@code FloatUnaryOperator} to apply before this operator is applied
     * @return A composed {@code FloatTernaryOperator} that first applies the given {@code before} operators and then
     * applies this operator.
     * @throws NullPointerException If one of the given operators are {@code null}
     * @see #andThen(FloatUnaryOperator)
     */
    default FloatTernaryOperator compose(final FloatUnaryOperator before1, final FloatUnaryOperator before2,
            final FloatUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (left, middle, right) -> applyAsFloat(before1.applyAsFloat(left), before2.applyAsFloat(middle),
                                                     before3.applyAsFloat(right));
    }

    /**
     * Returns a composed {@link FloatTernaryOperator} that first applies this operator to its input, and then applies
     * the {@code after} operator to the result. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operator.
     *
     * @param after The {@code FloatUnaryOperator} to apply after this operator is applied
     * @return A composed {@code FloatTernaryOperator} that first applies this operator and then applies the {@code
     * after} operator.
     * @throws NullPointerException If one of the given operators are {@code null}
     * @see #compose(FloatUnaryOperator, FloatUnaryOperator, FloatUnaryOperator)
     */
    default FloatTernaryOperator andThen(FloatUnaryOperator after) {
        Objects.requireNonNull(after);
        return (left, middle, right) -> after.applyAsFloat(applyAsFloat(left, middle, right));
    }

    /**
     * Returns a composed {@link TernaryOperator} which represents this {@link FloatTernaryOperator}. Thereby the
     * primitive input argument for this operator is autoboxed.
     *
     * @return A composed {@code TernaryOperator} which represents this {@code FloatTernaryOperator}.
     */
    @Nonnull
    default TernaryOperator<Float> boxed() {
        return this::applyAsFloat;
    }
}