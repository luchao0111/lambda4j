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
package at.gridtec.lambda4j.function.primitives.tri;

import at.gridtec.lambda4j.consumer.primitives.tri.BooleanTriConsumer;
import at.gridtec.lambda4j.function.TriFunction;
import at.gridtec.lambda4j.function.primitives.BooleanFunction;
import at.gridtec.lambda4j.operators.ternary.BooleanTernaryOperator;
import at.gridtec.lambda4j.operators.unary.BooleanUnaryOperator;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Represents a function that accepts three boolean-valued argument and produces a result. This is the {@code
 * boolean}-consuming primitive specialization for {@link TriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #apply(boolean, boolean, boolean)}.
 *
 * @param <R> The type of return value from the function
 * @see TriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BooleanTriFunction<R> {

    /**
     * Creates a {@link BooleanTriFunction} which always returns a given value.
     *
     * @param <R> The type of return value from the function
     * @param r The return value for the constant
     * @return A {@code BooleanTriFunction} which always returns a given value.
     */
    static <R> BooleanTriFunction<R> constant(R r) {
        return (value1, value2, value3) -> r;
    }

    /**
     * Creates a {@link BooleanTriFunction} which uses the {@code first} parameter of this one as argument for the given {@link
     * BooleanFunction}.
     *
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code BooleanTriFunction} which uses the {@code first} parameter of this one as argument for the given
     * {@code BooleanFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <R> BooleanTriFunction<R> onlyFirst(final BooleanFunction<? extends R> function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.apply(value1);
    }

    /**
     * Creates a {@link BooleanTriFunction} which uses the {@code second} parameter of this one as argument for the given {@link
     * BooleanFunction}.
     *
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code BooleanTriFunction} which uses the {@code second} parameter of this one as argument for the
     * given {@code BooleanFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <R> BooleanTriFunction<R> onlySecond(final BooleanFunction<? extends R> function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.apply(value2);
    }

    /**
     * Creates a {@link BooleanTriFunction} which uses the {@code third} parameter of this one as argument for the given {@link
     * BooleanFunction}.
     *
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code third} parameter of this one
     * @return Creates a {@code BooleanTriFunction} which uses the {@code third} parameter of this one as argument for the given
     * {@code BooleanFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <R> BooleanTriFunction<R> onlyThird(final BooleanFunction<? extends R> function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.apply(value3);
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The return value from the function, which is its result.
     */
    R apply(boolean value1, boolean value2, boolean value3);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     */
    default int arity() {
        return 3;
    }

    /**
     * Returns a composed {@link BooleanTriFunction} that first applies the {@code before} {@link BooleanUnaryOperator}s
     * to its input, and then applies this operation to the result. If evaluation of either operation throws an
     * exception, it is relayed to the caller of the composed function.
     *
     * @param before1 The first {@code BooleanUnaryOperator} to apply before this operation is applied
     * @param before2 The second {@code BooleanUnaryOperator} to apply before this operation is applied
     * @param before3 The third {@code BooleanUnaryOperator} to apply before this operation is applied
     * @return A composed {@code BooleanTriFunction} that first applies the {@code before} {@code BooleanUnaryOperator}s
     * to its input, and then applies this operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(Function)
     */
    default BooleanTriFunction<R> compose(final BooleanUnaryOperator before1, final BooleanUnaryOperator before2,
            final BooleanUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2, value3) -> apply(before1.applyAsBoolean(value1), before2.applyAsBoolean(value2),
                                                 before3.applyAsBoolean(value3));
    }

    /**
     * Returns a composed {@link TriFunction} that applies the given {@code before} {@link Predicate}s to its input, and
     * then applies this operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed function.
     *
     * @param <T> The type of the argument to the first before operation
     * @param <U> The type of the argument to the second before operation
     * @param <V> The type of the argument to the third before operation
     * @param before1 The first before {@code Predicate} to apply before this operation is applied
     * @param before2 The second before {@code Predicate} to apply before this operation is applied
     * @param before3 The third before {@code Predicate} to apply before this operation is applied
     * @return A composed {@code TriFunction} that applies the given {@code before} {@code Predicate}s to its input, and
     * then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(Function)
     */
    default <T, U, V> TriFunction<T, U, V, R> compose(final Predicate<? super T> before1,
            final Predicate<? super U> before2, final Predicate<? super V> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2, value3) -> apply(before1.test(value1), before2.test(value2), before3.test(value3));
    }

    /**
     * Returns a composed {@link BooleanTernaryOperator} that first applies this operation to its input, and then
     * applies the {@code after} operation to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param after The {@code ToBooleanFunction} to apply after this operation is applied
     * @return A composed {@code BooleanTernaryOperator} that first applies this operation, and then applies the {@code
     * after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(BooleanUnaryOperator, BooleanUnaryOperator, BooleanUnaryOperator)
     * @see #compose(Predicate, Predicate, Predicate)
     */
    default BooleanTernaryOperator andThen(final Predicate<? super R> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.test(apply(value1, value2, value3));
    }

    /**
     * Returns a composed {@link BooleanTriFunction} that first applies this operation to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param <S> The type of output of the {@code after} function, and of the composed function
     * @param after The {@code BooleanTriFunction} to apply after this operation is applied
     * @return A composed {@code BooleanTriFunction} that first applies this operation, and then applies the {@code
     * after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(BooleanUnaryOperator, BooleanUnaryOperator, BooleanUnaryOperator)
     * @see #compose(Predicate, Predicate, Predicate)
     */
    default <S> BooleanTriFunction<S> andThen(final Function<? super R, ? extends S> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.apply(apply(value1, value2, value3));
    }

    /**
     * Returns a composed {@link BooleanTriConsumer} that fist applies this operation to its input, and then consumes
     * the result using the given {@code Consumer}. If evaluation of either operator throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The {@code Consumer} which consumes the result from this operation
     * @return A composed {@code BooleanTriConsumer} that first applies this operation to its input, and then consumes
     * the result using the given {@code Consumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    default BooleanTriConsumer consume(Consumer<? super R> consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.accept(this.apply(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriFunction} which represents this {@link BooleanTriFunction}. Thereby the primitive
     * input argument for this operation is autoboxed.
     *
     * @return A composed {@code TriFunction} which represents this {@code BooleanTriFunction}.
     */
    default TriFunction<Boolean, Boolean, Boolean, R> boxed() {
        return this::apply;
    }
}
