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
import at.gridtec.lambda4j.predicates.primitives.bi.IntBiPredicate;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BooleanSupplier;
import java.util.function.IntPredicate;

/**
 * Represents a predicate (boolean-valued function) of three {@code int}-valued argument. This is the {@code
 * int}-consuming primitive type specialization of {@link TriPredicate}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #test(int, int, int)}.
 *
 * @see TriPredicate
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface IntTriPredicate {

    /**
     * Calls the given {@link IntTriPredicate} with the given arguments and returns its result.
     *
     * @param predicate The predicate to be called
     * @param value1 The first argument to the predicate
     * @param value2 The second argument to the predicate
     * @param value3 The third argument to the predicate
     * @return The result from the given {@code IntTriPredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     */
    static boolean call(@Nonnull final IntTriPredicate predicate, int value1, int value2, int value3) {
        Objects.requireNonNull(predicate);
        return predicate.test(value1, value2, value3);
    }

    /**
     * Creates a {@link IntTriPredicate} which uses the {@code first} parameter of this one as argument for the given
     * {@link IntPredicate}.
     *
     * @param predicate The predicate which accepts the {@code first} parameter of this one
     * @return Creates a {@code IntTriPredicate} which uses the {@code first} parameter of this one as argument for the
     * given {@code IntPredicate}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static IntTriPredicate onlyFirst(@Nonnull final IntPredicate predicate) {
        Objects.requireNonNull(predicate);
        return (value1, value2, value3) -> predicate.test(value1);
    }

    /**
     * Creates a {@link IntTriPredicate} which uses the {@code second} parameter of this one as argument for the given
     * {@link IntPredicate}.
     *
     * @param predicate The predicate which accepts the {@code second} parameter of this one
     * @return Creates a {@code IntTriPredicate} which uses the {@code second} parameter of this one as argument for the
     * given {@code IntPredicate}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static IntTriPredicate onlySecond(@Nonnull final IntPredicate predicate) {
        Objects.requireNonNull(predicate);
        return (value1, value2, value3) -> predicate.test(value2);
    }

    /**
     * Creates a {@link IntTriPredicate} which uses the {@code third} parameter of this one as argument for the given
     * {@link IntPredicate}.
     *
     * @param predicate The predicate which accepts the {@code third} parameter of this one
     * @return Creates a {@code IntTriPredicate} which uses the {@code third} parameter of this one as argument for the
     * given {@code IntPredicate}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static IntTriPredicate onlyThird(@Nonnull final IntPredicate predicate) {
        Objects.requireNonNull(predicate);
        return (value1, value2, value3) -> predicate.test(value3);
    }

    /**
     * Creates a {@link IntTriPredicate} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code IntTriPredicate} which always returns a given value.
     */
    @Nonnull
    static IntTriPredicate constant(boolean ret) {
        return (value1, value2, value3) -> ret;
    }

    /**
     * Returns a {@link IntTriPredicate} that tests if the given arguments are equal to the ones of this predicate
     * according to {@code value == target} method.
     *
     * @param target1 The first target value with which to compare for equality
     * @param target2 The second target value with which to compare for equality
     * @param target3 The third target value with which to compare for equality
     * @return A {@code IntTriPredicate} that tests if the given arguments are equal to the ones of this predicate.
     * @see #isNotEqual(int, int, int)
     */
    @Nonnull
    static IntTriPredicate isEqual(int target1, int target2, int target3) {
        return (value1, value2, value3) -> (value1 == target1) && (value2 == target2) && (value3 == target3);
    }

    /**
     * Returns a {@link IntTriPredicate} that tests if the given arguments are not equal to the ones of this predicate
     * according to {@code value != target} method.
     *
     * @param target1 The first target value with which to compare for equality
     * @param target2 The second target value with which to compare for equality
     * @param target3 The third target value with which to compare for equality
     * @return A {@code IntTriPredicate} that tests if the given arguments are not equal to the ones of this predicate.
     * @see #isEqual(int, int, int)
     */
    @Nonnull
    static IntTriPredicate isNotEqual(int target1, int target2, int target3) {
        return (value1, value2, value3) -> (value1 != target1) && (value2 != target2) && (value3 != target3);
    }

    /**
     * Returns a {@link IntTriPredicate} that always returns {@code true}.
     *
     * @return A {@link IntTriPredicate} that always returns {@code true}.
     * @see #alwaysFalse()
     */
    @Nonnull
    static IntTriPredicate alwaysTrue() {
        return (value1, value2, value3) -> true;
    }

    /**
     * Returns a {@link IntTriPredicate} the always returns {@code false}.
     *
     * @return A {@link IntTriPredicate} the always returns {@code false}.
     * @see #alwaysTrue()
     */
    @Nonnull
    static IntTriPredicate alwaysFalse() {
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
    boolean test(int value1, int value2, int value3);

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
     * Returns an {@link IntTriPredicate} that represents the logical negation of this one.
     *
     * @return An {@code IntTriPredicate} that represents the logical negation of this one.
     * @see TriPredicate#negate()
     */
    @Nonnull
    default IntTriPredicate negate() {
        return (value1, value2, value3) -> !test(value1, value2, value3);
    }

    /**
     * Returns a composed {@link IntTriPredicate} that represents a short-circuiting logical AND of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code IntTriPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code IntTriPredicate} that will be logically-ANDed with this one
     * @return A composed {@code IntTriPredicate} that represents the short-circuiting logical AND of this predicate and
     * the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #or(IntTriPredicate)
     * @see #xor(IntTriPredicate)
     * @see TriPredicate#and(TriPredicate)
     */
    @Nonnull
    default IntTriPredicate and(@Nonnull final IntTriPredicate other) {
        Objects.requireNonNull(other);
        return (value1, value2, value3) -> test(value1, value2, value3) && other.test(value1, value2, value3);
    }

    /**
     * Returns a composed {@link IntTriPredicate} that represents a short-circuiting logical OR of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code IntTriPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code IntTriPredicate} that will be logically-ORed with this one
     * @return A composed {@code IntTriPredicate} that represents the short-circuiting logical OR of this predicate and
     * the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(IntTriPredicate)
     * @see #xor(IntTriPredicate)
     * @see TriPredicate#or(TriPredicate)
     */
    @Nonnull
    default IntTriPredicate or(@Nonnull final IntTriPredicate other) {
        Objects.requireNonNull(other);
        return (value1, value2, value3) -> test(value1, value2, value3) && other.test(value1, value2, value3);
    }

    /**
     * Returns a composed {@link IntTriPredicate} that represents a short-circuiting logical XOR of this predicate and
     * another. Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of
     * this {@code IntTriPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code IntTriPredicate} that will be logically-XORed with this one
     * @return A composed {@code IntTriPredicate} that represents the short-circuiting logical XOR of this predicate and
     * the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(IntTriPredicate)
     * @see #or(IntTriPredicate)
     * @see TriPredicate#xor(TriPredicate)
     */
    @Nonnull
    default IntTriPredicate xor(@Nonnull final IntTriPredicate other) {
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
    default IntBiPredicate partial(int value1) {
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
    default IntPredicate partial(int value1, int value2) {
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
    default BooleanSupplier partial(int value1, int value2, int value3) {
        return () -> test(value1, value2, value3);
    }

    /**
     * Returns a composed {@link TriPredicate} which represents this {@link IntTriPredicate}. Thereby the primitive
     * input argument for this predicate is autoboxed.
     *
     * @return A composed {@code TriPredicate} which represents this {@code IntTriPredicate}.
     */
    @Nonnull
    default TriPredicate<Integer, Integer, Integer> boxed() {
        return this::test;
    }
}
