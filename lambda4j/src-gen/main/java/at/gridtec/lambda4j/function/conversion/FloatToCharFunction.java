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
import at.gridtec.lambda4j.consumer.CharConsumer;
import at.gridtec.lambda4j.consumer.FloatConsumer;
import at.gridtec.lambda4j.function.CharFunction;
import at.gridtec.lambda4j.function.FloatFunction;
import at.gridtec.lambda4j.function.Function2;
import at.gridtec.lambda4j.function.to.ToCharFunction;
import at.gridtec.lambda4j.function.to.ToFloatFunction;
import at.gridtec.lambda4j.operator.unary.CharUnaryOperator;
import at.gridtec.lambda4j.operator.unary.FloatUnaryOperator;
import at.gridtec.lambda4j.predicate.CharPredicate;
import at.gridtec.lambda4j.predicate.FloatPredicate;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Represents an operation that accepts one {@code float}-valued input argument and produces a
 * {@code char}-valued result.
 * This is a primitive specialization of {@link Function2}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsChar(float)}.
 *
 * @see Function2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface FloatToCharFunction extends Lambda {

    /**
     * Constructs a {@link FloatToCharFunction} based on a lambda expression or a method reference. Thereby the given
     * lambda expression or method reference is returned on an as-is basis to implicitly transform it to the desired
     * type. With this method, it is possible to ensure that correct type is used from lambda expression or method
     * reference.
     *
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code FloatToCharFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static FloatToCharFunction of(@Nullable final FloatToCharFunction expression) {
        return expression;
    }

    /**
     * Calls the given {@link FloatToCharFunction} with the given argument and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code FloatToCharFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static char call(@Nonnull final FloatToCharFunction function, float value) {
        Objects.requireNonNull(function);
        return function.applyAsChar(value);
    }

    /**
     * Creates a {@link FloatToCharFunction} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code FloatToCharFunction} which always returns a given value.
     */
    @Nonnull
    static FloatToCharFunction constant(char ret) {
        return (value) -> ret;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param value The argument to the function
     * @return The return value from the function, which is its result.
     */
    char applyAsChar(float value);

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
     * Returns a composed {@link ToCharFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     *
     * @param <A> The type of the argument to the given function, and of composed function
     * @param before The function to apply before this function is applied
     * @return A composed {@code ToCharFunction} that first applies the {@code before} function to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A> ToCharFunction<A> compose(@Nonnull final ToFloatFunction<? super A> before) {
        Objects.requireNonNull(before);
        return (a) -> applyAsChar(before.applyAsFloat(a));
    }

    /**
     * Returns a composed {@link BooleanToCharFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code boolean} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code BooleanToCharFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BooleanToCharFunction composeFromBoolean(@Nonnull final BooleanToFloatFunction before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsChar(before.applyAsFloat(value));
    }

    /**
     * Returns a composed {@link ByteToCharFunction} that first applies the {@code before} function to
     * its input, and then applies this function to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code byte} input,
     * before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code ByteToCharFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ByteToCharFunction composeFromByte(@Nonnull final ByteToFloatFunction before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsChar(before.applyAsFloat(value));
    }

    /**
     * Returns a composed {@link CharUnaryOperator} that first applies the {@code before} function to
     * its input, and then applies this function to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code char} input,
     * before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code CharUnaryOperator} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default CharUnaryOperator composeFromChar(@Nonnull final CharToFloatFunction before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsChar(before.applyAsFloat(value));
    }

    /**
     * Returns a composed {@link DoubleToCharFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code double} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code DoubleToCharFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default DoubleToCharFunction composeFromDouble(@Nonnull final DoubleToFloatFunction before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsChar(before.applyAsFloat(value));
    }

    /**
     * Returns a composed {@link FloatToCharFunction} that first applies the {@code before} operator to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code float} input, before this primitive function is executed.
     *
     * @param before The operator to apply before this function is applied
     * @return A composed {@code FloatToCharFunction} that first applies the {@code before} operator to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default FloatToCharFunction composeFromFloat(@Nonnull final FloatUnaryOperator before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsChar(before.applyAsFloat(value));
    }

    /**
     * Returns a composed {@link IntToCharFunction} that first applies the {@code before} function to
     * its input, and then applies this function to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code int} input,
     * before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code IntToCharFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default IntToCharFunction composeFromInt(@Nonnull final IntToFloatFunction before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsChar(before.applyAsFloat(value));
    }

    /**
     * Returns a composed {@link LongToCharFunction} that first applies the {@code before} function to
     * its input, and then applies this function to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code long} input,
     * before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code LongToCharFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default LongToCharFunction composeFromLong(@Nonnull final LongToFloatFunction before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsChar(before.applyAsFloat(value));
    }

    /**
     * Returns a composed {@link ShortToCharFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code ShortToCharFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ShortToCharFunction composeFromShort(@Nonnull final ShortToFloatFunction before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsChar(before.applyAsFloat(value));
    }

    /**
     * Returns a composed {@link FloatFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code FloatFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> FloatFunction<S> andThen(@Nonnull final CharFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (value) -> after.apply(applyAsChar(value));
    }

    /**
     * Returns a composed {@link FloatPredicate} that first applies this function to its input, and then applies the
     * {@code after} predicate to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this function is applied
     * @return A composed {@code FloatPredicate} that first applies this function to its input, and then applies the
     * {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default FloatPredicate andThenToBoolean(@Nonnull final CharPredicate after) {
        Objects.requireNonNull(after);
        return (value) -> after.test(applyAsChar(value));
    }

    /**
     * Returns a composed {@link FloatToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code FloatToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default FloatToByteFunction andThenToByte(@Nonnull final CharToByteFunction after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsByte(applyAsChar(value));
    }

    /**
     * Returns a composed {@link FloatToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code char}.
     *
     * @param after The operator to apply after this function is applied
     * @return A composed {@code FloatToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default FloatToCharFunction andThenToChar(@Nonnull final CharUnaryOperator after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsChar(applyAsChar(value));
    }

    /**
     * Returns a composed {@link FloatToDoubleFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code FloatToDoubleFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default FloatToDoubleFunction andThenToDouble(@Nonnull final CharToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsDouble(applyAsChar(value));
    }

    /**
     * Returns a composed {@link FloatUnaryOperator} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code FloatUnaryOperator} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default FloatUnaryOperator andThenToFloat(@Nonnull final CharToFloatFunction after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsFloat(applyAsChar(value));
    }

    /**
     * Returns a composed {@link FloatToIntFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code FloatToIntFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default FloatToIntFunction andThenToInt(@Nonnull final CharToIntFunction after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsInt(applyAsChar(value));
    }

    /**
     * Returns a composed {@link FloatToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code FloatToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default FloatToLongFunction andThenToLong(@Nonnull final CharToLongFunction after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsLong(applyAsChar(value));
    }

    /**
     * Returns a composed {@link FloatToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code FloatToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default FloatToShortFunction andThenToShort(@Nonnull final CharToShortFunction after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsShort(applyAsChar(value));
    }

    /**
     * Returns a composed {@link FloatConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link CharConsumer}. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code FloatConsumer} that first applies this function to its input, and then consumes the
     * result using the given {@code CharConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default FloatConsumer consume(@Nonnull final CharConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value) -> consumer.accept(applyAsChar(value));
    }

    /**
     * Returns a memoized (caching) version of this {@link FloatToCharFunction}. Whenever it is called, the mapping
     * between the input parameter and the return value is preserved in a cache, making subsequent calls returning the
     * memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code FloatToCharFunction}.
     * @implSpec This implementation does not allow the input parameter or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default FloatToCharFunction memoized() {
        if (isMemoized()) {
            return this;
        } else {
            final Map<Float, Character> cache = new ConcurrentHashMap<>();
            final Object lock = new Object();
            return (FloatToCharFunction & Memoized) (value) -> {
                final char returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(value, this::applyAsChar);
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link Function} which represents this {@link FloatToCharFunction}. Thereby the primitive
     * input argument for this function is autoboxed. This method is just convenience to provide the ability to use this
     * {@code FloatToCharFunction} with JDK specific methods, only accepting {@code Function}.
     *
     * @return A composed {@code Function} which represents this {@code FloatToCharFunction}.
     */
    @Nonnull
    default Function<Float, Character> boxed() {
        return this::applyAsChar;
    }

}