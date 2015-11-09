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
import at.gridtec.lambda4j.predicates.primitives.FloatPredicate;
import at.gridtec.lambda4j.predicates.primitives.bi.FloatBiPredicate;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BooleanSupplier;

/**
 * Represents a predicate (boolean-valued function) of three {@code float}-valued argument. This is the {@code
 * float}-consuming primitive type specialization of {@link TriPredicate}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #test(float, float, float)}.
 *
 * @see TriPredicate
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface FloatTriPredicate {

    /**
     * Calls the given {@link FloatTriPredicate} with the given arguments and returns its result.
     *
     * @param predicate The predicate to be called
     * @param value1 The first argument to the predicate
     * @param value2 The second argument to the predicate
     * @param value3 The third argument to the predicate
     * @return The result from the given {@code FloatTriPredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     */
    static boolean call(@Nonnull final FloatTriPredicate predicate, float value1, float value2, float value3) {
        Objects.requireNonNull(predicate);
        return predicate.test(value1, value2, value3);
    }

    /**
     * Creates a {@link FloatTriPredicate} which uses the {@code first} parameter of this one as argument for the given
     * {@link FloatPredicate}.
     *
     * @param predicate The predicate which accepts the {@code first} parameter of this one
     * @return Creates a {@code FloatTriPredicate} which uses the {@code first} parameter of this one as argument for
     * the given {@code FloatPredicate}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static FloatTriPredicate onlyFirst(@Nonnull final FloatPredicate predicate) {
        Objects.requireNonNull(predicate);
        return (value1, value2, value3) -> predicate.test(value1);
    }

    /**
     * Creates a {@link FloatTriPredicate} which uses the {@code second} parameter of this one as argument for the given
     * {@link FloatPredicate}.
     *
     * @param predicate The predicate which accepts the {@code second} parameter of this one
     * @return Creates a {@code FloatTriPredicate} which uses the {@code second} parameter of this one as argument for
     * the given {@code FloatPredicate}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static FloatTriPredicate onlySecond(@Nonnull final FloatPredicate predicate) {
        Objects.requireNonNull(predicate);
        return (value1, value2, value3) -> predicate.test(value2);
    }

    /**
     * Creates a {@link FloatTriPredicate} which uses the {@code third} parameter of this one as argument for the given
     * {@link FloatPredicate}.
     *
     * @param predicate The predicate which accepts the {@code third} parameter of this one
     * @return Creates a {@code FloatTriPredicate} which uses the {@code third} parameter of this one as argument for
     * the given {@code FloatPredicate}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static FloatTriPredicate onlyThird(@Nonnull final FloatPredicate predicate) {
        Objects.requireNonNull(predicate);
        return (value1, value2, value3) -> predicate.test(value3);
    }

    /**
     * Creates a {@link FloatTriPredicate} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code FloatTriPredicate} which always returns a given value.
     */
    @Nonnull
    static FloatTriPredicate constant(boolean ret) {
        return (value1, value2, value3) -> ret;
    }

    /**
     * Returns a {@link FloatTriPredicate} that tests if the given arguments are equal to the ones of this predicate
     * according to {@code value == target} method.
     *
     * @param target1 The first target value with which to compare for equality
     * @param target2 The second target value with which to compare for equality
     * @param target3 The third target value with which to compare for equality
     * @return A {@code FloatTriPredicate} that tests if the given arguments are equal to the ones of this predicate.
     * @see #isNotEqual(float, float, float)
     */
    @Nonnull
    static FloatTriPredicate isEqual(float target1, float target2, float target3) {
        return (value1, value2, value3) -> (value1 == target1) && (value2 == target2) && (value3 == target3);
    }

    /**
     * Returns a {@link FloatTriPredicate} that tests if the given arguments are not equal to the ones of this predicate
     * according to {@code value != target} method.
     *
     * @param target1 The first target value with which to compare for equality
     * @param target2 The second target value with which to compare for equality
     * @param target3 The third target value with which to compare for equality
     * @return A {@code FloatTriPredicate} that tests if the given arguments are not equal to the ones of this
     * predicate.
     * @see #isEqual(float, float, float)
     */
    @Nonnull
    static FloatTriPredicate isNotEqual(float target1, float target2, float target3) {
        return (value1, value2, value3) -> (value1 != target1) && (value2 != target2) && (value3 != target3);
    }

    /**
     * Returns a {@link FloatTriPredicate} that always returns {@code true}.
     *
     * @return A {@link FloatTriPredicate} that always returns {@code true}.
     * @see #alwaysFalse()
     */
    @Nonnull
    static FloatTriPredicate alwaysTrue() {
        return (value1, value2, value3) -> true;
    }

    /**
     * Returns a {@link FloatTriPredicate} the always returns {@code false}.
     *
     * @return A {@link FloatTriPredicate} the always returns {@code false}.
     * @see #alwaysTrue()
     */
    @Nonnull
    static FloatTriPredicate alwaysFalse() {
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
    boolean test(float value1, float value2, float value3);

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
     * Returns a {@link FloatTriPredicate} that represents the logical negation of this one.
     *
     * @return A {@code FloatTriPredicate} that represents the logical negation of this one.
     * @see TriPredicate#negate()
     */
    @Nonnull
    default FloatTriPredicate negate() {
        return (value1, value2, value3) -> !test(value1, value2, value3);
    }

    /**
     * Returns a composed {@link FloatTriPredicate} that represents a short-circuiting logical AND of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code FloatTriPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code FloatTriPredicate} that will be logically-ANDed with this one
     * @return A composed {@code FloatTriPredicate} that represents the short-circuiting logical AND of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #or(FloatTriPredicate)
     * @see #xor(FloatTriPredicate)
     * @see TriPredicate#and(TriPredicate)
     */
    @Nonnull
    default FloatTriPredicate and(@Nonnull final FloatTriPredicate other) {
        Objects.requireNonNull(other);
        return (value1, value2, value3) -> test(value1, value2, value3) && other.test(value1, value2, value3);
    }

    /**
     * Returns a composed {@link FloatTriPredicate} that represents a short-circuiting logical OR of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code FloatTriPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code FloatTriPredicate} that will be logically-ORed with this one
     * @return A composed {@code FloatTriPredicate} that represents the short-circuiting logical OR of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(FloatTriPredicate)
     * @see #xor(FloatTriPredicate)
     * @see TriPredicate#or(TriPredicate)
     */
    @Nonnull
    default FloatTriPredicate or(@Nonnull final FloatTriPredicate other) {
        Objects.requireNonNull(other);
        return (value1, value2, value3) -> test(value1, value2, value3) && other.test(value1, value2, value3);
    }

    /**
     * Returns a composed {@link FloatTriPredicate} that represents a short-circuiting logical XOR of this predicate and
     * another. Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of
     * this {@code FloatTriPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code FloatTriPredicate} that will be logically-XORed with this one
     * @return A composed {@code FloatTriPredicate} that represents the short-circuiting logical XOR of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(FloatTriPredicate)
     * @see #or(FloatTriPredicate)
     * @see TriPredicate#xor(TriPredicate)
     */
    @Nonnull
    default FloatTriPredicate xor(@Nonnull final FloatTriPredicate other) {
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
    default FloatBiPredicate partial(float value1) {
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
    default FloatPredicate partial(float value1, float value2) {
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
    default BooleanSupplier partial(float value1, float value2, float value3) {
        return () -> test(value1, value2, value3);
    }

    /**
     * Returns a composed {@link TriPredicate} which represents this {@link FloatTriPredicate}. Thereby the primitive
     * input argument for this predicate is autoboxed.
     *
     * @return A composed {@code TriPredicate} which represents this {@code FloatTriPredicate}.
     */
    @Nonnull
    default TriPredicate<Float, Float, Float> boxed() {
        return this::test;
    }
}
