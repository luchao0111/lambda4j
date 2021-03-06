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
package at.gridtec.lambda4j.function.bi;

import at.gridtec.lambda4j.Lambda;
import at.gridtec.lambda4j.consumer.bi.BiDoubleConsumer;
import at.gridtec.lambda4j.function.DoubleFunction2;
import at.gridtec.lambda4j.function.conversion.BooleanToDoubleFunction;
import at.gridtec.lambda4j.function.conversion.ByteToDoubleFunction;
import at.gridtec.lambda4j.function.conversion.CharToDoubleFunction;
import at.gridtec.lambda4j.function.conversion.FloatToDoubleFunction;
import at.gridtec.lambda4j.function.conversion.ShortToDoubleFunction;

import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.DoubleFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntToDoubleFunction;
import java.util.function.LongToDoubleFunction;
import java.util.function.ToDoubleFunction;

/**
 * Represents an operation that accepts two {@code double}-valued input arguments and produces a
 * result.
 * This is a primitive specialization of {@link BiFunction2}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #apply(double, double)}.
 *
 * @param <R> The type of return value from the function
 * @see BiFunction2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BiDoubleFunction<R> extends Lambda {

    /**
     * Constructs a {@link BiDoubleFunction} based on a lambda expression or a method reference. Thereby the given
     * lambda expression or method reference is returned on an as-is basis to implicitly transform it to the desired
     * type. With this method, it is possible to ensure that correct type is used from lambda expression or method
     * reference.
     *
     * @param <R> The type of return value from the function
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code BiDoubleFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static <R> BiDoubleFunction<R> of(@Nullable final BiDoubleFunction<R> expression) {
        return expression;
    }

    /**
     * Lifts a partial {@link BiDoubleFunction} into a total {@link BiDoubleFunction} that returns an {@link Optional}
     * result.
     *
     * @param <R> The type of return value from the function
     * @param partial A function that is only defined for some values in its domain
     * @return A partial {@code BiDoubleFunction} lifted into a total {@code BiDoubleFunction} that returns an {@code
     * Optional} result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <R> BiDoubleFunction<Optional<R>> lift(@Nonnull final BiDoubleFunction<? extends R> partial) {
        Objects.requireNonNull(partial);
        return (value1, value2) -> Optional.ofNullable(partial.apply(value1, value2));
    }

    /**
     * Calls the given {@link BiDoubleFunction} with the given arguments and returns its result.
     *
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code BiDoubleFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static <R> R call(@Nonnull final BiDoubleFunction<? extends R> function, double value1, double value2) {
        Objects.requireNonNull(function);
        return function.apply(value1, value2);
    }

    /**
     * Creates a {@link BiDoubleFunction} which uses the {@code first} parameter of this one as argument for the given
     * {@link DoubleFunction}.
     *
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code BiDoubleFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@code DoubleFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <R> BiDoubleFunction<R> onlyFirst(@Nonnull final DoubleFunction<? extends R> function) {
        Objects.requireNonNull(function);
        return (value1, value2) -> function.apply(value1);
    }

    /**
     * Creates a {@link BiDoubleFunction} which uses the {@code second} parameter of this one as argument for the given
     * {@link DoubleFunction}.
     *
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code BiDoubleFunction} which uses the {@code second} parameter of this one as argument for
     * the given {@code DoubleFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <R> BiDoubleFunction<R> onlySecond(@Nonnull final DoubleFunction<? extends R> function) {
        Objects.requireNonNull(function);
        return (value1, value2) -> function.apply(value2);
    }

    /**
     * Creates a {@link BiDoubleFunction} which always returns a given value.
     *
     * @param <R> The type of return value from the function
     * @param ret The return value for the constant
     * @return A {@code BiDoubleFunction} which always returns a given value.
     */
    @Nonnull
    static <R> BiDoubleFunction<R> constant(R ret) {
        return (value1, value2) -> ret;
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The return value from the function, which is its result.
     */
    R apply(double value1, double value2);

    /**
     * Applies this function partially to some arguments of this one, producing a {@link DoubleFunction2} as result.
     *
     * @param value1 The first argument to this function used to partially apply this function
     * @return A {@code DoubleFunction2} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default DoubleFunction2<R> papply(double value1) {
        return (value2) -> this.apply(value1, value2);
    }

    /**
     * Returns the number of arguments for this function.
     *
     * @return The number of arguments for this function.
     * @implSpec The default implementation always returns {@code 2}.
     */
    @Nonnegative
    default int arity() {
        return 2;
    }

    /**
     * Returns a composed {@link BiFunction2} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     *
     * @param <A> The type of the argument to the first given function, and of composed function
     * @param <B> The type of the argument to the second given function, and of composed function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiFunction2} that first applies the {@code before} functions to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B> BiFunction2<A, B, R> compose(@Nonnull final ToDoubleFunction<? super A> before1,
            @Nonnull final ToDoubleFunction<? super B> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (a, b) -> apply(before1.applyAsDouble(a), before2.applyAsDouble(b));
    }

    /**
     * Returns a composed {@link BiBooleanFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code boolean} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiBooleanFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BiBooleanFunction<R> composeFromBoolean(@Nonnull final BooleanToDoubleFunction before1,
            @Nonnull final BooleanToDoubleFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> apply(before1.applyAsDouble(value1), before2.applyAsDouble(value2));
    }

    /**
     * Returns a composed {@link BiByteFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code byte} input,
     * before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiByteFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default BiByteFunction<R> composeFromByte(@Nonnull final ByteToDoubleFunction before1,
            @Nonnull final ByteToDoubleFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> apply(before1.applyAsDouble(value1), before2.applyAsDouble(value2));
    }

    /**
     * Returns a composed {@link BiCharFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code char} input,
     * before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiCharFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default BiCharFunction<R> composeFromChar(@Nonnull final CharToDoubleFunction before1,
            @Nonnull final CharToDoubleFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> apply(before1.applyAsDouble(value1), before2.applyAsDouble(value2));
    }

    /**
     * Returns a composed {@link BiDoubleFunction} that first applies the {@code before} operators to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code double} input, before this primitive function is executed.
     *
     * @param before1 The first operator to apply before this function is applied
     * @param before2 The second operator to apply before this function is applied
     * @return A composed {@code BiDoubleFunction} that first applies the {@code before} operators to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default BiDoubleFunction<R> composeFromDouble(@Nonnull final DoubleUnaryOperator before1,
            @Nonnull final DoubleUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> apply(before1.applyAsDouble(value1), before2.applyAsDouble(value2));
    }

    /**
     * Returns a composed {@link BiFloatFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code float} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiFloatFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default BiFloatFunction<R> composeFromFloat(@Nonnull final FloatToDoubleFunction before1,
            @Nonnull final FloatToDoubleFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> apply(before1.applyAsDouble(value1), before2.applyAsDouble(value2));
    }

    /**
     * Returns a composed {@link BiIntFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code int} input,
     * before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiIntFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default BiIntFunction<R> composeFromInt(@Nonnull final IntToDoubleFunction before1,
            @Nonnull final IntToDoubleFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> apply(before1.applyAsDouble(value1), before2.applyAsDouble(value2));
    }

    /**
     * Returns a composed {@link BiLongFunction} that first applies the {@code before} functions to
     * its input, and then applies this function to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code long} input,
     * before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiLongFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default BiLongFunction<R> composeFromLong(@Nonnull final LongToDoubleFunction before1,
            @Nonnull final LongToDoubleFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> apply(before1.applyAsDouble(value1), before2.applyAsDouble(value2));
    }

    /**
     * Returns a composed {@link BiShortFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiShortFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default BiShortFunction<R> composeFromShort(@Nonnull final ShortToDoubleFunction before1,
            @Nonnull final ShortToDoubleFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> apply(before1.applyAsDouble(value1), before2.applyAsDouble(value2));
    }

    /**
     * Returns a composed {@link BiDoubleFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiDoubleFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> BiDoubleFunction<S> andThen(@Nonnull final Function<? super R, ? extends S> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.apply(apply(value1, value2));
    }

    /**
     * Returns a composed {@link BiDoubleConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link Consumer}. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code BiDoubleConsumer} that first applies this function to its input, and then consumes the
     * result using the given {@code Consumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default BiDoubleConsumer consume(@Nonnull final Consumer<? super R> consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> consumer.accept(apply(value1, value2));
    }

    /**
     * Returns a memoized (caching) version of this {@link BiDoubleFunction}. Whenever it is called, the mapping between
     * the input parameters and the return value is preserved in a cache, making subsequent calls returning the memoized
     * value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code BiDoubleFunction}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default BiDoubleFunction<R> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            final Map<Pair<Double, Double>, R> cache = new ConcurrentHashMap<>();
            final Object lock = new Object();
            return (BiDoubleFunction<R> & Memoized) (value1, value2) -> {
                final R returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Pair.of(value1, value2),
                                                        key -> apply(key.getLeft(), key.getRight()));
                }
                return returnValue;
            };
        }
    }

    /**
     * Converts this function to an equal function, which ensures that its result is not
     * {@code null} using {@link Optional}. This method mainly exists to avoid unnecessary {@code NullPointerException}s
     * through referencing {@code null} from this function.
     *
     * @return An equal function, which ensures that its result is not {@code null}.
     * @deprecated Use {@code lift} method for lifting this function.
     */
    @Deprecated
    @Nonnull
    default BiDoubleFunction<Optional<R>> nonNull() {
        return (value1, value2) -> Optional.ofNullable(apply(value1, value2));
    }

    /**
     * Returns a composed {@link BiFunction2} which represents this {@link BiDoubleFunction}. Thereby the primitive
     * input argument for this function is autoboxed. This method provides the possibility to use this
     * {@code BiDoubleFunction} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code BiFunction2} which represents this {@code BiDoubleFunction}.
     */
    @Nonnull
    default BiFunction2<Double, Double, R> boxed() {
        return this::apply;
    }

}