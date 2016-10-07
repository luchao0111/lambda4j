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
package at.gridtec.lambda4j.function.conversion;

import at.gridtec.lambda4j.Lambda;
import at.gridtec.lambda4j.consumer.ShortConsumer;
import at.gridtec.lambda4j.function.Function2;
import at.gridtec.lambda4j.function.ShortFunction;
import at.gridtec.lambda4j.function.to.ToIntFunction2;
import at.gridtec.lambda4j.function.to.ToShortFunction;
import at.gridtec.lambda4j.operator.unary.IntUnaryOperator2;
import at.gridtec.lambda4j.operator.unary.ShortUnaryOperator;
import at.gridtec.lambda4j.predicate.ShortPredicate;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;

/**
 * Represents an operation that accepts one {@code short}-valued input argument and produces a
 * {@code int}-valued result.
 * This is a primitive specialization of {@link Function2}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsInt(short)}.
 *
 * @see Function2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ShortToIntFunction extends Lambda {

    /**
     * Constructs a {@link ShortToIntFunction} based on a lambda expression or a method reference. Thereby the given
     * lambda expression or method reference is returned on an as-is basis to implicitly transform it to the desired
     * type. With this method, it is possible to ensure that correct type is used from lambda expression or method
     * reference.
     *
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ShortToIntFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static ShortToIntFunction of(@Nullable final ShortToIntFunction expression) {
        return expression;
    }

    /**
     * Calls the given {@link ShortToIntFunction} with the given argument and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code ShortToIntFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static int call(@Nonnull final ShortToIntFunction function, short value) {
        Objects.requireNonNull(function);
        return function.applyAsInt(value);
    }

    /**
     * Creates a {@link ShortToIntFunction} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code ShortToIntFunction} which always returns a given value.
     */
    @Nonnull
    static ShortToIntFunction constant(int ret) {
        return (value) -> ret;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param value The argument to the function
     * @return The return value from the function, which is its result.
     */
    int applyAsInt(short value);

    /**
     * Returns the number of arguments for this function.
     *
     * @return The number of arguments for this function.
     * @implSpec The default implementation always returns {@code 1}.
     */
    @Nonnegative
    default int arity() {
        return 1;
    }

    /**
     * Returns a composed {@link ToIntFunction2} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     *
     * @param <A> The type of the argument to the given function, and of composed function
     * @param before The function to apply before this function is applied
     * @return A composed {@code ToIntFunction2} that first applies the {@code before} function to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A> ToIntFunction2<A> compose(@Nonnull final ToShortFunction<? super A> before) {
        Objects.requireNonNull(before);
        return (a) -> applyAsInt(before.applyAsShort(a));
    }

    /**
     * Returns a composed {@link BooleanToIntFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code boolean} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code BooleanToIntFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BooleanToIntFunction composeFromBoolean(@Nonnull final BooleanToShortFunction before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsInt(before.applyAsShort(value));
    }

    /**
     * Returns a composed {@link ByteToIntFunction} that first applies the {@code before} function to
     * its input, and then applies this function to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code byte} input,
     * before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code ByteToIntFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ByteToIntFunction composeFromByte(@Nonnull final ByteToShortFunction before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsInt(before.applyAsShort(value));
    }

    /**
     * Returns a composed {@link CharToIntFunction} that first applies the {@code before} function to
     * its input, and then applies this function to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code char} input,
     * before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code CharToIntFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default CharToIntFunction composeFromChar(@Nonnull final CharToShortFunction before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsInt(before.applyAsShort(value));
    }

    /**
     * Returns a composed {@link DoubleToIntFunction2} that first applies the {@code before} function to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code double} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code DoubleToIntFunction2} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default DoubleToIntFunction2 composeFromDouble(@Nonnull final DoubleToShortFunction before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsInt(before.applyAsShort(value));
    }

    /**
     * Returns a composed {@link FloatToIntFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code float} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code FloatToIntFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default FloatToIntFunction composeFromFloat(@Nonnull final FloatToShortFunction before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsInt(before.applyAsShort(value));
    }

    /**
     * Returns a composed {@link IntUnaryOperator2} that first applies the {@code before} function to
     * its input, and then applies this function to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code int} input,
     * before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code IntUnaryOperator2} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default IntUnaryOperator2 composeFromInt(@Nonnull final IntToShortFunction before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsInt(before.applyAsShort(value));
    }

    /**
     * Returns a composed {@link LongToIntFunction2} that first applies the {@code before} function to
     * its input, and then applies this function to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code long} input,
     * before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code LongToIntFunction2} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default LongToIntFunction2 composeFromLong(@Nonnull final LongToShortFunction before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsInt(before.applyAsShort(value));
    }

    /**
     * Returns a composed {@link ShortToIntFunction} that first applies the {@code before} operator to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before The operator to apply before this function is applied
     * @return A composed {@code ShortToIntFunction} that first applies the {@code before} operator to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ShortToIntFunction composeFromShort(@Nonnull final ShortUnaryOperator before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsInt(before.applyAsShort(value));
    }

    /**
     * Returns a composed {@link ShortFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code ShortFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ShortFunction<S> andThen(@Nonnull final IntFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (value) -> after.apply(applyAsInt(value));
    }

    /**
     * Returns a composed {@link ShortPredicate} that first applies this function to its input, and then applies the
     * {@code after} predicate to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this function is applied
     * @return A composed {@code ShortPredicate} that first applies this function to its input, and then applies the
     * {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ShortPredicate andThenToBoolean(@Nonnull final IntPredicate after) {
        Objects.requireNonNull(after);
        return (value) -> after.test(applyAsInt(value));
    }

    /**
     * Returns a composed {@link ShortToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ShortToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ShortToByteFunction andThenToByte(@Nonnull final IntToByteFunction after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsByte(applyAsInt(value));
    }

    /**
     * Returns a composed {@link ShortToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ShortToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ShortToCharFunction andThenToChar(@Nonnull final IntToCharFunction after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsChar(applyAsInt(value));
    }

    /**
     * Returns a composed {@link ShortToDoubleFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ShortToDoubleFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ShortToDoubleFunction andThenToDouble(@Nonnull final IntToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsDouble(applyAsInt(value));
    }

    /**
     * Returns a composed {@link ShortToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ShortToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ShortToFloatFunction andThenToFloat(@Nonnull final IntToFloatFunction after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsFloat(applyAsInt(value));
    }

    /**
     * Returns a composed {@link ShortToIntFunction} that first applies this function to its input, and then applies the
     * {@code after} operator to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code int}.
     *
     * @param after The operator to apply after this function is applied
     * @return A composed {@code ShortToIntFunction} that first applies this function to its input, and then applies the
     * {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ShortToIntFunction andThenToInt(@Nonnull final IntUnaryOperator after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsInt(applyAsInt(value));
    }

    /**
     * Returns a composed {@link ShortToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ShortToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ShortToLongFunction andThenToLong(@Nonnull final IntToLongFunction after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsLong(applyAsInt(value));
    }

    /**
     * Returns a composed {@link ShortUnaryOperator} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ShortUnaryOperator} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ShortUnaryOperator andThenToShort(@Nonnull final IntToShortFunction after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsShort(applyAsInt(value));
    }

    /**
     * Returns a composed {@link ShortConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link IntConsumer}. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ShortConsumer} that first applies this function to its input, and then consumes the
     * result using the given {@code IntConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ShortConsumer consume(@Nonnull final IntConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value) -> consumer.accept(applyAsInt(value));
    }

    /**
     * Returns a memoized (caching) version of this {@link ShortToIntFunction}. Whenever it is called, the mapping
     * between the input parameter and the return value is preserved in a cache, making subsequent calls returning the
     * memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ShortToIntFunction}.
     * @implSpec This implementation does not allow the input parameter or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ShortToIntFunction memoized() {
        if (isMemoized()) {
            return this;
        } else {
            final Map<Short, Integer> cache = new ConcurrentHashMap<>();
            final Object lock = new Object();
            return (ShortToIntFunction & Memoized) (value) -> {
                final int returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(value, this::applyAsInt);
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link Function} which represents this {@link ShortToIntFunction}. Thereby the primitive input
     * argument for this function is autoboxed. This method is just convenience to provide the ability to use this
     * {@code ShortToIntFunction} with JDK specific methods, only accepting {@code Function}.
     *
     * @return A composed {@code Function} which represents this {@code ShortToIntFunction}.
     */
    @Nonnull
    default Function<Short, Integer> boxed() {
        return this::applyAsInt;
    }

}