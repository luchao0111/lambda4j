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
package at.gridtec.lambda4j.predicates.primitives.tri;

import at.gridtec.lambda4j.predicates.TriPredicate;
import at.gridtec.lambda4j.predicates.primitives.bi.DoubleBiPredicate;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BooleanSupplier;
import java.util.function.DoublePredicate;

/**
 * Represents a predicate (boolean-valued function) of three {@code double}-valued argument. This is the {@code
 * double}-consuming primitive type specialization of {@link TriPredicate}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #test(double, double, double)}.
 *
 * @see TriPredicate
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface DoubleTriPredicate {

    /**
     * Calls the given {@link DoubleTriPredicate} with the given arguments and returns its result.
     *
     * @param predicate The predicate to be called
     * @param value1 The first argument to the predicate
     * @param value2 The second argument to the predicate
     * @param value3 The third argument to the predicate
     * @return The result from the given {@code DoubleTriPredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     */
    static boolean call(@Nonnull final DoubleTriPredicate predicate, double value1, double value2, double value3) {
        Objects.requireNonNull(predicate);
        return predicate.test(value1, value2, value3);
    }

    /**
     * Creates a {@link DoubleTriPredicate} which uses the {@code first} parameter of this one as argument for the given
     * {@link DoublePredicate}.
     *
     * @param predicate The predicate which accepts the {@code first} parameter of this one
     * @return Creates a {@code DoubleTriPredicate} which uses the {@code first} parameter of this one as argument for
     * the given {@code DoublePredicate}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static DoubleTriPredicate onlyFirst(@Nonnull final DoublePredicate predicate) {
        Objects.requireNonNull(predicate);
        return (value1, value2, value3) -> predicate.test(value1);
    }

    /**
     * Creates a {@link DoubleTriPredicate} which uses the {@code second} parameter of this one as argument for the
     * given {@link DoublePredicate}.
     *
     * @param predicate The predicate which accepts the {@code second} parameter of this one
     * @return Creates a {@code DoubleTriPredicate} which uses the {@code second} parameter of this one as argument for
     * the given {@code DoublePredicate}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static DoubleTriPredicate onlySecond(@Nonnull final DoublePredicate predicate) {
        Objects.requireNonNull(predicate);
        return (value1, value2, value3) -> predicate.test(value2);
    }

    /**
     * Creates a {@link DoubleTriPredicate} which uses the {@code third} parameter of this one as argument for the given
     * {@link DoublePredicate}.
     *
     * @param predicate The predicate which accepts the {@code third} parameter of this one
     * @return Creates a {@code DoubleTriPredicate} which uses the {@code third} parameter of this one as argument for
     * the given {@code DoublePredicate}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static DoubleTriPredicate onlyThird(@Nonnull final DoublePredicate predicate) {
        Objects.requireNonNull(predicate);
        return (value1, value2, value3) -> predicate.test(value3);
    }

    /**
     * Creates a {@link ShortTriPredicate} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code ShortTriPredicate} which always returns a given value.
     */
    @Nonnull
    static ShortTriPredicate constant(boolean ret) {
        return (value1, value2, value3) -> ret;
    }

    /**
     * Returns a {@link ShortTriPredicate} that tests if the given arguments are equal to the ones of this predicate
     * according to {@code value == target} method.
     *
     * @param target1 The first target value with which to compare for equality
     * @param target2 The second target value with which to compare for equality
     * @param target3 The third target value with which to compare for equality
     * @return A {@code ShortTriPredicate} that tests if the given arguments are equal to the ones of this predicate.
     * @see #isNotEqual(double, double, double)
     */
    @Nonnull
    static ShortTriPredicate isEqual(double target1, double target2, double target3) {
        return (value1, value2, value3) -> (value1 == target1) && (value2 == target2) && (value3 == target3);
    }

    /**
     * Returns a {@link ShortTriPredicate} that tests if the given arguments are not equal to the ones of this predicate
     * according to {@code value != target} method.
     *
     * @param target1 The first target value with which to compare for equality
     * @param target2 The second target value with which to compare for equality
     * @param target3 The third target value with which to compare for equality
     * @return A {@code ShortTriPredicate} that tests if the given arguments are not equal to the ones of this
     * predicate.
     * @see #isEqual(double, double, double)
     */
    @Nonnull
    static ShortTriPredicate isNotEqual(double target1, double target2, double target3) {
        return (value1, value2, value3) -> (value1 != target1) && (value2 != target2) && (value3 != target3);
    }

    /**
     * Returns a {@link ShortTriPredicate} that always returns {@code true}.
     *
     * @return A {@link ShortTriPredicate} that always returns {@code true}.
     * @see #alwaysFalse()
     */
    @Nonnull
    static ShortTriPredicate alwaysTrue() {
        return (value1, value2, value3) -> true;
    }

    /**
     * Returns a {@link ShortTriPredicate} the always returns {@code false}.
     *
     * @return A {@link ShortTriPredicate} the always returns {@code false}.
     * @see #alwaysTrue()
     */
    @Nonnull
    static ShortTriPredicate alwaysFalse() {
        return (value1, value2, value3) -> false;
    }

    /**
     * Evaluates this predicate on the given arguments.
     *
     * @param value1 The first argument to the predicate
     * @param value2 The second argument to the predicate
     * @param value3 The third argument to the predicate
     * @return {@code true} if the input arguments match the predicate, otherwise {@code false}.
     */
    boolean test(double value1, double value2, double value3);

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
     * Returns a {@link DoubleTriPredicate} that represents the logical negation of this one.
     *
     * @return A {@code DoubleTriPredicate} that represents the logical negation of this one.
     * @see TriPredicate#negate()
     */
    @Nonnull
    default DoubleTriPredicate negate() {
        return (value1, value2, value3) -> !test(value1, value2, value3);
    }

    /**
     * Returns a composed {@link DoubleTriPredicate} that represents a short-circuiting logical AND of this predicate
     * and another. When evaluating the composed predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code DoubleTriPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code DoubleTriPredicate} that will be logically-ANDed with this one
     * @return A composed {@code DoubleTriPredicate} that represents the short-circuiting logical AND of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #or(DoubleTriPredicate)
     * @see #xor(DoubleTriPredicate)
     * @see TriPredicate#and(TriPredicate)
     */
    @Nonnull
    default DoubleTriPredicate and(@Nonnull final DoubleTriPredicate other) {
        Objects.requireNonNull(other);
        return (value1, value2, value3) -> test(value1, value2, value3) && other.test(value1, value2, value3);
    }

    /**
     * Returns a composed {@link DoubleTriPredicate} that represents a short-circuiting logical OR of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code DoubleTriPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code DoubleTriPredicate} that will be logically-ORed with this one
     * @return A composed {@code DoubleTriPredicate} that represents the short-circuiting logical OR of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(DoubleTriPredicate)
     * @see #xor(DoubleTriPredicate)
     * @see TriPredicate#or(TriPredicate)
     */
    @Nonnull
    default DoubleTriPredicate or(@Nonnull final DoubleTriPredicate other) {
        Objects.requireNonNull(other);
        return (value1, value2, value3) -> test(value1, value2, value3) && other.test(value1, value2, value3);
    }

    /**
     * Returns a composed {@link DoubleTriPredicate} that represents a short-circuiting logical XOR of this predicate
     * and another. Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation
     * of this {@code DoubleTriPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code DoubleTriPredicate} that will be logically-XORed with this one
     * @return A composed {@code DoubleTriPredicate} that represents the short-circuiting logical XOR of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(DoubleTriPredicate)
     * @see #or(DoubleTriPredicate)
     * @see TriPredicate#xor(TriPredicate)
     */
    @Nonnull
    default DoubleTriPredicate xor(@Nonnull final DoubleTriPredicate other) {
        Objects.requireNonNull(other);
        return (value1, value2, value3) -> test(value1, value2, value3) ^ other.test(value1, value2, value3);
    }

    /**
     * Applies this predicate partially to one argument. The result is a predicate of arity {@code 2};
     *
     * @param value1 The argument to partially apply to the predicate
     * @return A partial application of this predicate.
     */
    @Nonnull
    default DoubleBiPredicate partial(double value1) {
        return (value2, value3) -> test(value1, value2, value3);
    }

    /**
     * Applies this predicate partially to two arguments. The result is a predicate of arity {@code 1}.
     *
     * @param value1 The first argument to partially apply to the predicate
     * @param value2 The second argument to partially apply to the predicate
     * @return A partial application of this predicate.
     */
    @Nonnull
    default DoublePredicate partial(double value1, double value2) {
        return value3 -> test(value1, value2, value3);
    }

    /**
     * Applies this predicate partially to three arguments. The result is an operation of arity {@code 0}.
     *
     * @param value1 The first argument to partially apply to the predicate
     * @param value2 The second argument to partially apply to the predicate
     * @param value3 The third argument to partially apply to the predicate
     * @return A partial application of this predicate.
     */
    @Nonnull
    default BooleanSupplier partial(double value1, double value2, double value3) {
        return () -> test(value1, value2, value3);
    }

    /**
     * Returns a composed {@link TriPredicate} which represents this {@link DoubleTriPredicate}. Thereby the primitive
     * input argument for this predicate is autoboxed.
     *
     * @return A composed {@code TriPredicate} which represents this {@code DoubleTriPredicate}.
     */
    @Nonnull
    default TriPredicate<Double, Double, Double> boxed() {
        return this::test;
    }
}