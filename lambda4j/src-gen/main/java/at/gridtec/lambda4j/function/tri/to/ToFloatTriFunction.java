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
package at.gridtec.lambda4j.function.tri.to;

import at.gridtec.lambda4j.Lambda;
import at.gridtec.lambda4j.consumer.FloatConsumer;
import at.gridtec.lambda4j.consumer.tri.TriConsumer;
import at.gridtec.lambda4j.function.FloatFunction;
import at.gridtec.lambda4j.function.bi.to.ToFloatBiFunction;
import at.gridtec.lambda4j.function.conversion.FloatToByteFunction;
import at.gridtec.lambda4j.function.conversion.FloatToCharFunction;
import at.gridtec.lambda4j.function.conversion.FloatToDoubleFunction;
import at.gridtec.lambda4j.function.conversion.FloatToIntFunction;
import at.gridtec.lambda4j.function.conversion.FloatToLongFunction;
import at.gridtec.lambda4j.function.conversion.FloatToShortFunction;
import at.gridtec.lambda4j.function.to.ToFloatFunction;
import at.gridtec.lambda4j.function.tri.TriFunction;
import at.gridtec.lambda4j.operator.unary.FloatUnaryOperator;
import at.gridtec.lambda4j.predicate.FloatPredicate;
import at.gridtec.lambda4j.predicate.tri.TriPredicate;

import org.apache.commons.lang3.tuple.Triple;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Represents an operation that accepts three input arguments and produces a
 * {@code float}-valued result.
 * This is a primitive specialization of {@link TriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsFloat(Object, Object, Object)}.
 *
 * @param <T> The type of the first argument to the function
 * @param <U> The type of the second argument to the function
 * @param <V> The type of the third argument to the function
 * @see TriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ToFloatTriFunction<T, U, V> extends Lambda {

    /**
     * Constructs a {@link ToFloatTriFunction} based on a lambda expression or a method reference. Thereby the given
     * lambda expression or method reference is returned on an as-is basis to implicitly transform it to the desired
     * type. With this method, it is possible to ensure that correct type is used from lambda expression or method
     * reference.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ToFloatTriFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static <T, U, V> ToFloatTriFunction<T, U, V> of(@Nullable final ToFloatTriFunction<T, U, V> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ToFloatTriFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param v The third argument to the function
     * @return The result from the given {@code ToFloatTriFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static <T, U, V> float call(@Nonnull final ToFloatTriFunction<? super T, ? super U, ? super V> function, T t, U u,
            V v) {
        Objects.requireNonNull(function);
        return function.applyAsFloat(t, u, v);
    }

    /**
     * Creates a {@link ToFloatTriFunction} which uses the {@code first} parameter of this one as argument for the given
     * {@link ToFloatFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ToFloatTriFunction} which uses the {@code first} parameter of this one as argument for
     * the given {@code ToFloatFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U, V> ToFloatTriFunction<T, U, V> onlyFirst(@Nonnull final ToFloatFunction<? super T> function) {
        Objects.requireNonNull(function);
        return (t, u, v) -> function.applyAsFloat(t);
    }

    /**
     * Creates a {@link ToFloatTriFunction} which uses the {@code second} parameter of this one as argument for the
     * given {@link ToFloatFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ToFloatTriFunction} which uses the {@code second} parameter of this one as argument for
     * the given {@code ToFloatFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U, V> ToFloatTriFunction<T, U, V> onlySecond(@Nonnull final ToFloatFunction<? super U> function) {
        Objects.requireNonNull(function);
        return (t, u, v) -> function.applyAsFloat(u);
    }

    /**
     * Creates a {@link ToFloatTriFunction} which uses the {@code third} parameter of this one as argument for the given
     * {@link ToFloatFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param function The function which accepts the {@code third} parameter of this one
     * @return Creates a {@code ToFloatTriFunction} which uses the {@code third} parameter of this one as argument for
     * the given {@code ToFloatFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U, V> ToFloatTriFunction<T, U, V> onlyThird(@Nonnull final ToFloatFunction<? super V> function) {
        Objects.requireNonNull(function);
        return (t, u, v) -> function.applyAsFloat(v);
    }

    /**
     * Creates a {@link ToFloatTriFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <V> The type of the third argument to the function
     * @param ret The return value for the constant
     * @return A {@code ToFloatTriFunction} which always returns a given value.
     */
    @Nonnull
    static <T, U, V> ToFloatTriFunction<T, U, V> constant(float ret) {
        return (t, u, v) -> ret;
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param v The third argument to the function
     * @return The return value from the function, which is its result.
     */
    float applyAsFloat(T t, U u, V v);

    /**
     * Applies this function to the given tuple.
     *
     * @param tuple The tuple to be applied to the function
     * @return The return value from the function, which is its result.
     * @throws NullPointerException If given argument is {@code null}
     * @see org.apache.commons.lang3.tuple.Triple
     */
    default float applyAsFloat(@Nonnull Triple<T, U, V> tuple) {
        Objects.requireNonNull(tuple);
        return applyAsFloat(tuple.getLeft(), tuple.getMiddle(), tuple.getRight());
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link ToFloatBiFunction} as result.
     *
     * @param t The first argument to this function used to partially apply this function
     * @return A {@code ToFloatBiFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default ToFloatBiFunction<U, V> papplyAsFloat(T t) {
        return (u, v) -> this.applyAsFloat(t, u, v);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link ToFloatFunction} as result.
     *
     * @param t The first argument to this function used to partially apply this function
     * @param u The second argument to this function used to partially apply this function
     * @return A {@code ToFloatFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default ToFloatFunction<V> papplyAsFloat(T t, U u) {
        return (v) -> this.applyAsFloat(t, u, v);
    }

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
     * Returns a composed {@link ToFloatTriFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     *
     * @param <A> The type of the argument to the first given function, and of composed function
     * @param <B> The type of the argument to the second given function, and of composed function
     * @param <C> The type of the argument to the third given function, and of composed function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ToFloatTriFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B, C> ToFloatTriFunction<A, B, C> compose(@Nonnull final Function<? super A, ? extends T> before1,
            @Nonnull final Function<? super B, ? extends U> before2,
            @Nonnull final Function<? super C, ? extends V> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> applyAsFloat(before1.apply(a), before2.apply(b), before3.apply(c));
    }

    /**
     * Returns a composed {@link TriFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriFunction} that first applies this function to its input, and then applies the {@code
     * after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> TriFunction<T, U, V, S> andThen(@Nonnull final FloatFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.apply(applyAsFloat(t, u, v));
    }

    /**
     * Returns a composed {@link TriPredicate} that first applies this function to its input, and then applies the
     * {@code after} predicate to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this function is applied
     * @return A composed {@code TriPredicate} that first applies this function to its input, and then applies the
     * {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default TriPredicate<T, U, V> andThenToBoolean(@Nonnull final FloatPredicate after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.test(applyAsFloat(t, u, v));
    }

    /**
     * Returns a composed {@link ToByteTriFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ToByteTriFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ToByteTriFunction<T, U, V> andThenToByte(@Nonnull final FloatToByteFunction after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsByte(applyAsFloat(t, u, v));
    }

    /**
     * Returns a composed {@link ToCharTriFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ToCharTriFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ToCharTriFunction<T, U, V> andThenToChar(@Nonnull final FloatToCharFunction after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsChar(applyAsFloat(t, u, v));
    }

    /**
     * Returns a composed {@link ToDoubleTriFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ToDoubleTriFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ToDoubleTriFunction<T, U, V> andThenToDouble(@Nonnull final FloatToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsDouble(applyAsFloat(t, u, v));
    }

    /**
     * Returns a composed {@link ToFloatTriFunction} that first applies this function to its input, and then applies the
     * {@code after} operator to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code float}.
     *
     * @param after The operator to apply after this function is applied
     * @return A composed {@code ToFloatTriFunction} that first applies this function to its input, and then applies the
     * {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ToFloatTriFunction<T, U, V> andThenToFloat(@Nonnull final FloatUnaryOperator after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsFloat(applyAsFloat(t, u, v));
    }

    /**
     * Returns a composed {@link ToIntTriFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ToIntTriFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ToIntTriFunction<T, U, V> andThenToInt(@Nonnull final FloatToIntFunction after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsInt(applyAsFloat(t, u, v));
    }

    /**
     * Returns a composed {@link ToLongTriFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ToLongTriFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ToLongTriFunction<T, U, V> andThenToLong(@Nonnull final FloatToLongFunction after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsLong(applyAsFloat(t, u, v));
    }

    /**
     * Returns a composed {@link ToShortTriFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ToShortTriFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ToShortTriFunction<T, U, V> andThenToShort(@Nonnull final FloatToShortFunction after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.applyAsShort(applyAsFloat(t, u, v));
    }

    /**
     * Returns a composed {@link TriConsumer} that fist applies this function to its input, and then consumes the result
     * using the given {@link FloatConsumer}.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code TriConsumer} that first applies this function to its input, and then consumes the
     * result using the given {@code FloatConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default TriConsumer<T, U, V> consume(@Nonnull final FloatConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, v) -> consumer.accept(applyAsFloat(t, u, v));
    }

    /**
     * Returns a tupled version of this function.
     *
     * @return A tupled version of this function.
     */
    @Nonnull
    default ToFloatFunction<Triple<T, U, V>> tupled() {
        return this::applyAsFloat;
    }

    /**
     * Returns a reversed version of this function. This may be useful in recursive context.
     *
     * @return A reversed version of this function.
     */
    @Nonnull
    default ToFloatTriFunction<V, U, T> reversed() {
        return (v, u, t) -> applyAsFloat(t, u, v);
    }

    /**
     * Returns a memoized (caching) version of this {@link ToFloatTriFunction}. Whenever it is called, the mapping
     * between the input parameters and the return value is preserved in a cache, making subsequent calls returning the
     * memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ToFloatTriFunction}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ToFloatTriFunction<T, U, V> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            final Map<Triple<T, U, V>, Float> cache = new ConcurrentHashMap<>();
            final Object lock = new Object();
            return (ToFloatTriFunction<T, U, V> & Memoized) (t, u, v) -> {
                final float returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Triple.of(t, u, v),
                                                        key -> applyAsFloat(key.getLeft(), key.getMiddle(),
                                                                            key.getRight()));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link TriFunction} which represents this {@link ToFloatTriFunction}. Thereby the primitive
     * input argument for this function is autoboxed. This method provides the possibility to use this
     * {@code ToFloatTriFunction} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code TriFunction} which represents this {@code ToFloatTriFunction}.
     */
    @Nonnull
    default TriFunction<T, U, V, Float> boxed() {
        return this::applyAsFloat;
    }

}