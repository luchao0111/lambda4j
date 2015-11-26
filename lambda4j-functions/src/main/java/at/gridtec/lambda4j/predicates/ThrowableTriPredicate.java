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
package at.gridtec.lambda4j.predicates;

import org.apache.commons.lang3.tuple.Triple;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

/**
 * Represents a predicate (boolean-valued function) of three arguments which is able to throw any {@link Throwable}.
 * This is the three-arity specialization of {@link ThrowablePredicate}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #testThrows(Object, Object, Object)}.
 *
 * @param <T> The type of the first argument to the predicate
 * @param <U> The type of the second argument to the predicate
 * @param <V> The type of the third argument to the predicate
 * @see ThrowableTriPredicate
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableTriPredicate<T, U, V> {

    /**
     * Calls the given {@link ThrowableTriPredicate} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param <V> The type of the third argument to the predicate
     * @param predicate The predicate to be called
     * @param t The first argument to the predicate
     * @param u The second argument to the predicate
     * @param v The third argument to the predicate
     * @return The result from the given {@code ThrowableTriPredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     * @throws Throwable Any throwable from the given predicates action
     */
    static <T, U, V> boolean call(@Nonnull final ThrowableTriPredicate<? super T, ? super U, ? super V> predicate, T t,
            U u, V v) throws Throwable {
        Objects.requireNonNull(predicate);
        return predicate.testThrows(t, u, v);
    }

    /**
     * Creates a {@link ThrowableTriPredicate} which uses the {@code first} parameter of this one as argument for the
     * given {@link ThrowablePredicate}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param <V> The type of the third argument to the predicate
     * @param predicate The predicate which accepts the {@code first} parameter of this one
     * @return Creates a {@code ThrowableTriPredicate} which uses the {@code first} parameter of this one as argument
     * for the given {@code ThrowablePredicate}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U, V> ThrowableTriPredicate<T, U, V> onlyFirst(@Nonnull final ThrowablePredicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        return (t, u, v) -> predicate.testThrows(t);
    }

    /**
     * Creates a {@link ThrowableTriPredicate} which uses the {@code second} parameter of this one as argument for the
     * given {@link ThrowablePredicate}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param <V> The type of the third argument to the predicate
     * @param predicate The predicate which accepts the {@code second} parameter of this one
     * @return Creates a {@code ThrowableTriPredicate} which uses the {@code second} parameter of this one as argument
     * for the given {@code ThrowablePredicate}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U, V> ThrowableTriPredicate<T, U, V> onlySecond(@Nonnull final ThrowablePredicate<? super U> predicate) {
        Objects.requireNonNull(predicate);
        return (t, u, v) -> predicate.testThrows(u);
    }

    /**
     * Creates a {@link ThrowableTriPredicate} which uses the {@code third} parameter of this one as argument for the
     * given {@link ThrowablePredicate}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param <V> The type of the third argument to the predicate
     * @param predicate The predicate which accepts the {@code third} parameter of this one
     * @return Creates a {@code ThrowableTriPredicate} which uses the {@code third} parameter of this one as argument
     * for the given {@code ThrowablePredicate}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U, V> ThrowableTriPredicate<T, U, V> onlyThird(@Nonnull final ThrowablePredicate<? super V> predicate) {
        Objects.requireNonNull(predicate);
        return (t, u, v) -> predicate.testThrows(v);
    }

    /**
     * Creates a {@link ThrowableTriPredicate} which always returns a given value.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param <V> The type of the third argument to the predicate
     * @param ret The return value for the constant
     * @return A {@code ThrowableTriPredicate} which always returns a given value.
     */
    @Nonnull
    static <T, U, V> ThrowableTriPredicate<T, U, V> constant(boolean ret) {
        return (t, v, u) -> ret;
    }

    /**
     * Returns a {@link ThrowableTriPredicate} that tests if the given arguments are equal to the ones of this predicate
     * according to {@link Objects#equals(Object)} method.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param <V> The type of the third argument to the predicate
     * @param targetRef1 The first object reference with which to compare for equality, which may be {@code null}
     * @param targetRef2 The second object reference with which to compare for equality, which may be {@code null}
     * @param targetRef3 The third object reference with which to compare for equality, which may be {@code null}
     * @return A {@code ThrowableTriPredicate} that tests if the given arguments are equal to the ones of this
     * predicate.
     * @see #isNotEqual(Object, Object, Object)
     */
    @Nonnull
    //@formatter:off
    static <T, U, V> ThrowableTriPredicate<T, U, V> isEqual(@Nullable Object targetRef1, @Nullable Object targetRef2,
            @Nullable Object targetRef3) {
        return (t, u, v) -> (t == null ? targetRef1 == null : t.equals(targetRef1))
                && (u == null ? targetRef2 == null : u.equals(targetRef2))
                && (v == null ? targetRef3 == null : v.equals(targetRef3));
    }
    //@formatter:on

    /**
     * Returns a {@link ThrowableTriPredicate} that tests if given arguments are not equal to the ones of this predicate
     * according to {@link Objects#equals(Object)} method.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param <V> The type of the third argument to the predicate
     * @param targetRef1 The first object reference with which to compare for equality, which may be {@code null}
     * @param targetRef2 The second object reference with which to compare for equality, which may be {@code null}
     * @param targetRef3 The third object reference with which to compare for equality, which may be {@code null}
     * @return A {@code ThrowableTriPredicate} that tests if the given arguments are not equal to the ones of this
     * predicate.
     * @see #isEqual(Object, Object, Object)
     */
    @Nonnull
    //@formatter:off
    static <T, U, V> ThrowableTriPredicate<T, U, V> isNotEqual(@Nullable Object targetRef1, @Nullable Object targetRef2,
            @Nullable Object targetRef3) {
        return (t, u, v) -> !(t == null ? targetRef1 == null : t.equals(targetRef1))
                || !(u == null ? targetRef2 == null : u.equals(targetRef2))
                || !(v == null ? targetRef3 == null : v.equals(targetRef3));
    }
    //@formatter:on

    /**
     * Returns a {@link ThrowableTriPredicate} that always returns {@code true}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param <V> The type of the third argument to the predicate
     * @return A {@link ThrowableTriPredicate} that always returns {@code true}.
     * @see #alwaysFalse()
     */
    @Nonnull
    static <T, U, V> ThrowableTriPredicate<T, U, V> alwaysTrue() {
        return (t, u, v) -> true;
    }

    /**
     * Returns a {@link ThrowableTriPredicate} the always returns {@code false}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param <V> The type of the third argument to the predicate
     * @return A {@link ThrowableTriPredicate} the always returns {@code false}.
     * @see #alwaysTrue()
     */
    @Nonnull
    static <T, U, V> ThrowableTriPredicate<T, U, V> alwaysFalse() {
        return (t, u, v) -> false;
    }

    /**
     * Evaluates this predicate on the given arguments. Thereby any {@link Throwable} is able to be thrown.
     *
     * @param t The first argument to the predicate
     * @param u The second argument to the predicate
     * @param v The third argument to the predicate
     * @return {@code true} if the input arguments match the predicate, otherwise {@code false}.
     * @throws Throwable Any throwable from this predicates action
     */
    boolean testThrows(T t, U u, V v) throws Throwable;

    /**
     * Evaluates this predicate on the given tuple. Thereby any {@link Throwable} is able to be thrown.
     *
     * @param tuple The tuple to be applied to the predicate
     * @return {@code true} if the input arguments match the predicate, otherwise {@code false}.
     * @throws NullPointerException If given argument is {@code null}
     * @throws Throwable Any throwable from this predicates action
     * @see org.apache.commons.lang3.tuple.Triple
     */
    default boolean testThrows(@Nonnull Triple<T, U, V> tuple) throws Throwable {
        Objects.requireNonNull(tuple);
        return testThrows(tuple.getLeft(), tuple.getMiddle(), tuple.getRight());
    }

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
     * Returns a {@link ThrowableTriPredicate} that represents the logical negation of this one.
     *
     * @return A {@code ThrowableTriPredicate} that represents the logical negation of this one.
     */
    @Nonnull
    default ThrowableTriPredicate<T, U, V> negate() {
        return (t, u, v) -> !testThrows(t, u, v);
    }

    /**
     * Returns a composed {@link ThrowableTriPredicate} that represents a short-circuiting logical AND of this predicate
     * and another.  When evaluating the composed predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code ThrowableTriPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code ThrowableTriPredicate} that will be logically-ANDed with this one
     * @return A composed {@code ThrowableTriPredicate} that represents the short-circuiting logical AND of this
     * predicate and the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #or(ThrowableTriPredicate)
     * @see #xor(ThrowableTriPredicate)
     */
    @Nonnull
    default ThrowableTriPredicate<T, U, V> and(
            @Nonnull final ThrowableTriPredicate<? super T, ? super U, ? super V> other) {
        Objects.requireNonNull(other);
        return (t, u, v) -> testThrows(t, u, v) && other.testThrows(t, u, v);
    }

    /**
     * Returns a composed {@link ThrowableTriPredicate} that represents a short-circuiting logical OR of this predicate
     * and another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code ThrowableTriPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code ThrowableTriPredicate} that will be logically-ORed with this one
     * @return A composed {@code ThrowableTriPredicate} that represents the short-circuiting logical OR of this
     * predicate and the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(ThrowableTriPredicate)
     * @see #xor(ThrowableTriPredicate)
     */
    @Nonnull
    default ThrowableTriPredicate<T, U, V> or(
            @Nonnull final ThrowableTriPredicate<? super T, ? super U, ? super V> other) {
        Objects.requireNonNull(other);
        return (t, u, v) -> testThrows(t, u, v) && other.testThrows(t, u, v);
    }

    /**
     * Returns a composed {@link ThrowableTriPredicate} that represents a short-circuiting logical XOR of this predicate
     * and another. Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation
     * of this {@code ThrowableTriPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code ThrowableTriPredicate} that will be logically-XORed with this one
     * @return A composed {@code ThrowableTriPredicate} that represents the short-circuiting logical XOR of this
     * predicate and the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(ThrowableTriPredicate)
     * @see #or(ThrowableTriPredicate)
     */
    @Nonnull
    default ThrowableTriPredicate<T, U, V> xor(
            @Nonnull final ThrowableTriPredicate<? super T, ? super U, ? super V> other) {
        Objects.requireNonNull(other);
        return (t, u, v) -> testThrows(t, u, v) ^ other.testThrows(t, u, v);
    }

    /**
     * Applies this predicate partially to one argument. The result is a predicate of arity {@code 2};
     *
     * @param t The argument to partially apply to the predicate
     * @return A partial application of this predicate.
     */
    @Nonnull
    default ThrowableBiPredicate<U, V> partial(T t) {
        return (u, v) -> testThrows(t, u, v);
    }

    /**
     * Applies this predicate partially to two arguments. The result is a predicate of arity {@code 1}.
     *
     * @param t The first argument to partially apply to the predicate
     * @param u The second argument to partially apply to the predicate
     * @return A partial application of this predicate.
     */
    @Nonnull
    default ThrowablePredicate<V> partial(T t, U u) {
        return v -> testThrows(t, u, v);
    }

    // TODO
    //    /**
    //     * Applies this predicate partially to three arguments. The result is an operation of arity {@code 0}.
    //     *
    //     * @param t The first argument to partially apply to the predicate
    //     * @param u The second argument to partially apply to the predicate
    //     * @param v The third argument to partially apply to the predicate
    //     * @return A partial application of this predicate.
    //     */
    //    @Nonnull
    //    default ThrowableBooleanSupplier partial(T t, U u, V v) {
    //        return () -> testThrows(t, u, v);
    //    }

    /**
     * Returns a tupled version of this predicate.
     *
     * @return A tupled version of this predicate.
     */
    @Nonnull
    default ThrowablePredicate<Triple<T, U, V>> tupled() {
        return this::testThrows;
    }

    /**
     * Returns a reversed version of this predicate. This may be useful in recursive context.
     *
     * @return A reversed version of this predicate.
     */
    @Nonnull
    default ThrowableTriPredicate<V, U, T> reversed() {
        return (v, u, t) -> testThrows(t, u, v);
    }
}