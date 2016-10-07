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
import at.gridtec.lambda4j.consumer.ThrowableIntConsumer;
import at.gridtec.lambda4j.consumer.ThrowableShortConsumer;
import at.gridtec.lambda4j.core.exception.ThrownByFunctionalInterfaceException;
import at.gridtec.lambda4j.core.util.ThrowableUtils;
import at.gridtec.lambda4j.function.ThrowableFunction;
import at.gridtec.lambda4j.function.ThrowableIntFunction;
import at.gridtec.lambda4j.function.ThrowableShortFunction;
import at.gridtec.lambda4j.function.to.ThrowableToIntFunction;
import at.gridtec.lambda4j.function.to.ThrowableToShortFunction;
import at.gridtec.lambda4j.operator.unary.ThrowableIntUnaryOperator;
import at.gridtec.lambda4j.operator.unary.ThrowableShortUnaryOperator;
import at.gridtec.lambda4j.predicate.ThrowableIntPredicate;
import at.gridtec.lambda4j.predicate.ThrowableShortPredicate;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Represents an operation that accepts one {@code int}-valued input argument and produces a
 * {@code short}-valued result which is able to throw any {@link Throwable}.
 * This is a primitive specialization of {@link ThrowableFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsShortThrows(int)}.
 *
 * @param <X> The type of the throwable to be thrown by this function
 * @see ThrowableFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableIntToShortFunction<X extends Throwable> extends Lambda {

    /**
     * Constructs a {@link ThrowableIntToShortFunction} based on a lambda expression or a method reference. Thereby the
     * given lambda expression or method reference is returned on an as-is basis to implicitly transform it to the
     * desired type. With this method, it is possible to ensure that correct type is used from lambda expression or
     * method reference.
     *
     * @param <X> The type of the throwable to be thrown by this function
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ThrowableIntToShortFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static <X extends Throwable> ThrowableIntToShortFunction<X> of(
            @Nullable final ThrowableIntToShortFunction<X> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ThrowableIntToShortFunction} with the given argument and returns its result.
     *
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code ThrowableIntToShortFunction}.
     * @throws NullPointerException If given argument is {@code null}
     * @throws X Any throwable from this functions action
     */
    static <X extends Throwable> short call(@Nonnull final ThrowableIntToShortFunction<? extends X> function,
            int value) throws X {
        Objects.requireNonNull(function);
        return function.applyAsShortThrows(value);
    }

    /**
     * Creates a {@link ThrowableIntToShortFunction} which always returns a given value.
     *
     * @param <X> The type of the throwable to be thrown by this function
     * @param ret The return value for the constant
     * @return A {@code ThrowableIntToShortFunction} which always returns a given value.
     */
    @Nonnull
    static <X extends Throwable> ThrowableIntToShortFunction<X> constant(short ret) {
        return (value) -> ret;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param value The argument to the function
     * @return The return value from the function, which is its result.
     * @throws X Any throwable from this functions action
     */
    short applyAsShortThrows(int value) throws X;

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
     * Returns a composed {@link ThrowableToShortFunction} that first applies the {@code before} function to its input,
     * and then applies this function to the result.
     *
     * @param <A> The type of the argument to the given function, and of composed function
     * @param before The function to apply before this function is applied
     * @return A composed {@code ThrowableToShortFunction} that first applies the {@code before} function to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A> ThrowableToShortFunction<A, X> compose(
            @Nonnull final ThrowableToIntFunction<? super A, ? extends X> before) {
        Objects.requireNonNull(before);
        return (a) -> applyAsShortThrows(before.applyAsIntThrows(a));
    }

    /**
     * Returns a composed {@link ThrowableBooleanToShortFunction} that first applies the {@code before} function to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code boolean} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code ThrowableBooleanToShortFunction} that first applies the {@code before} function to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableBooleanToShortFunction<X> composeFromBoolean(
            @Nonnull final ThrowableBooleanToIntFunction<? extends X> before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsShortThrows(before.applyAsIntThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableByteToShortFunction} that first applies the {@code before} function to
     * its input, and then applies this function to the result.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code byte} input,
     * before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code ThrowableByteToShortFunction} that first applies the {@code before} function to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableByteToShortFunction<X> composeFromByte(
            @Nonnull final ThrowableByteToIntFunction<? extends X> before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsShortThrows(before.applyAsIntThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableCharToShortFunction} that first applies the {@code before} function to
     * its input, and then applies this function to the result.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code char} input,
     * before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code ThrowableCharToShortFunction} that first applies the {@code before} function to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableCharToShortFunction<X> composeFromChar(
            @Nonnull final ThrowableCharToIntFunction<? extends X> before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsShortThrows(before.applyAsIntThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableDoubleToShortFunction} that first applies the {@code before} function to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code double} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code ThrowableDoubleToShortFunction} that first applies the {@code before} function to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableDoubleToShortFunction<X> composeFromDouble(
            @Nonnull final ThrowableDoubleToIntFunction<? extends X> before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsShortThrows(before.applyAsIntThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableFloatToShortFunction} that first applies the {@code before} function to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code float} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code ThrowableFloatToShortFunction} that first applies the {@code before} function to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableFloatToShortFunction<X> composeFromFloat(
            @Nonnull final ThrowableFloatToIntFunction<? extends X> before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsShortThrows(before.applyAsIntThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableIntToShortFunction} that first applies the {@code before} operator to
     * its input, and then applies this function to the result.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code int} input,
     * before this primitive function is executed.
     *
     * @param before The operator to apply before this function is applied
     * @return A composed {@code ThrowableIntToShortFunction} that first applies the {@code before} operator to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableIntToShortFunction<X> composeFromInt(
            @Nonnull final ThrowableIntUnaryOperator<? extends X> before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsShortThrows(before.applyAsIntThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableLongToShortFunction} that first applies the {@code before} function to
     * its input, and then applies this function to the result.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code long} input,
     * before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code ThrowableLongToShortFunction} that first applies the {@code before} function to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableLongToShortFunction<X> composeFromLong(
            @Nonnull final ThrowableLongToIntFunction<? extends X> before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsShortThrows(before.applyAsIntThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableShortUnaryOperator} that first applies the {@code before} function to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code ThrowableShortUnaryOperator} that first applies the {@code before} function to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableShortUnaryOperator<X> composeFromShort(
            @Nonnull final ThrowableShortToIntFunction<? extends X> before) {
        Objects.requireNonNull(before);
        return (value) -> applyAsShortThrows(before.applyAsIntThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ThrowableIntFunction<S, X> andThen(
            @Nonnull final ThrowableShortFunction<? extends S, ? extends X> after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyThrows(applyAsShortThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableIntPredicate} that first applies this function to its input, and then applies
     * the {@code after} predicate to the result. This method is just convenience, to provide the ability to transform
     * this primitive function to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this function is applied
     * @return A composed {@code ThrowableIntPredicate} that first applies this function to its input, and then applies
     * the {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableIntPredicate<X> andThenToBoolean(@Nonnull final ThrowableShortPredicate<? extends X> after) {
        Objects.requireNonNull(after);
        return (value) -> after.testThrows(applyAsShortThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableIntToByteFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableIntToByteFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableIntToByteFunction<X> andThenToByte(
            @Nonnull final ThrowableShortToByteFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsByteThrows(applyAsShortThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableIntToCharFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableIntToCharFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableIntToCharFunction<X> andThenToChar(
            @Nonnull final ThrowableShortToCharFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsCharThrows(applyAsShortThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableIntToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableIntToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableIntToDoubleFunction<X> andThenToDouble(
            @Nonnull final ThrowableShortToDoubleFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsDoubleThrows(applyAsShortThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableIntToFloatFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableIntToFloatFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableIntToFloatFunction<X> andThenToFloat(
            @Nonnull final ThrowableShortToFloatFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsFloatThrows(applyAsShortThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableIntUnaryOperator} that first applies this function to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableIntUnaryOperator} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableIntUnaryOperator<X> andThenToInt(@Nonnull final ThrowableShortToIntFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsIntThrows(applyAsShortThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableIntToLongFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableIntToLongFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableIntToLongFunction<X> andThenToLong(
            @Nonnull final ThrowableShortToLongFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsLongThrows(applyAsShortThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableIntToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code short}.
     *
     * @param after The operator to apply after this function is applied
     * @return A composed {@code ThrowableIntToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableIntToShortFunction<X> andThenToShort(
            @Nonnull final ThrowableShortUnaryOperator<? extends X> after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyAsShortThrows(applyAsShortThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableIntConsumer} that fist applies this function to its input, and then consumes
     * the result using the given {@link ThrowableShortConsumer}.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ThrowableIntConsumer} that first applies this function to its input, and then consumes
     * the result using the given {@code ThrowableShortConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ThrowableIntConsumer<X> consume(@Nonnull final ThrowableShortConsumer<? extends X> consumer) {
        Objects.requireNonNull(consumer);
        return (value) -> consumer.acceptThrows(applyAsShortThrows(value));
    }

    /**
     * Returns a memoized (caching) version of this {@link ThrowableIntToShortFunction}. Whenever it is called, the
     * mapping between the input parameter and the return value is preserved in a cache, making subsequent calls
     * returning the memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ThrowableIntToShortFunction}.
     * @implSpec This implementation does not allow the input parameter or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ThrowableIntToShortFunction<X> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            final Map<Integer, Short> cache = new ConcurrentHashMap<>();
            final Object lock = new Object();
            return (ThrowableIntToShortFunction<X> & Memoized) (value) -> {
                final short returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(value, ThrowableFunction.of(this::applyAsShortThrows));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link ThrowableFunction} which represents this {@link ThrowableIntToShortFunction}. Thereby
     * the primitive input argument for this function is autoboxed.
     *
     * @return A composed {@code ThrowableFunction} which represents this {@code ThrowableIntToShortFunction}.
     */
    @Nonnull
    default ThrowableFunction<Integer, Short, X> boxed() {
        return this::applyAsShortThrows;
    }

    /**
     * Returns a composed {@link IntToShortFunction} that applies this function to its input and nests the thrown {@link
     * Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}. The throwable is nested
     * (wrapped) in a {@link ThrownByFunctionalInterfaceException}, which is constructed from the thrown throwables
     * message and the thrown throwable itself.
     *
     * @return A composed {@code IntToShortFunction} that applies this function to its input and nests the thrown {@code
     * Throwable} from it, unless it is of type {@code RuntimeException} or {@code Error}.
     */
    @Nonnull
    default IntToShortFunction nest() {
        return (value) -> {
            try {
                return this.applyAsShortThrows(value);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw new ThrownByFunctionalInterfaceException(throwable.getMessage(), throwable);
            }
        };
    }

    /**
     * Returns a composed {@link IntToShortFunction} that applies this function to its input and sneakily throws the
     * thrown {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}. This means that
     * each throwable thrown from the returned composed function behaves exactly the same as an <em>unchecked</em>
     * throwable does. As a result, there is no need to handle the throwable of this function in the returned composed
     * function by either wrapping it in an <em>unchecked</em> throwable or to declare it in the {@code throws} clause,
     * as it would be done in a non sneaky throwing function.
     * <p>
     * What sneaky throwing simply does, is to fake out the compiler and thus it bypasses the principle of
     * <em>checked</em> throwables. On the JVM (class file) level, all throwables, checked or not, can be thrown
     * regardless of the {@code throws} clause of methods, which is why this works at all.
     * <p>
     * However, when using this method to get a sneaky throwing function variant of this throwable function, the
     * following advantages, disadvantages and limitations will apply:
     * <p>
     * If the calling-code is to handle the sneakily thrown throwable, it is required to add it to the {@code throws}
     * clause of the method that applies the returned composed function. The compiler will not force the declaration in
     * the {@code throws} clause anymore.
     * <p>
     * If the calling-code already handles the sneakily thrown throwable, the compiler requires it to be added to the
     * {@code throws} clause of the method that applies the returned composed function. If not added, the compiler will
     * error that the caught throwable is never thrown in the corresponding {@code try} block.
     * <p>
     * If the returned composed function is directly surrounded by a {@code try}-{@code catch} block to catch the
     * sneakily thrown throwable from it, the compiler will error that the caught throwable is never thrown in the
     * corresponding {@code try} block.
     * <p>
     * In any case, if the throwable is not added to the to the {@code throws} clause of the method that applies the
     * returned composed function, the calling-code won't be able to catch the throwable by name. It will bubble and
     * probably be caught in some {@code catch} statement, catching a base type such as {@code try { ... }
     * catch(RuntimeException e) { ... }} or {@code try { ... } catch(Exception e) { ... }}, but perhaps this is
     * intended.
     * <p>
     * When the called code never throws the specific throwable that it declares, it should obviously be omitted. For
     * example: {@code new String(byteArr, "UTF-8") throws UnsupportedEncodingException}, but {@code UTF-8} is
     * guaranteed by the Java specification to be always present. Here, the {@code throws} declaration is a nuisance and
     * any solution to silence it with minimal boilerplate is welcome. The throwable should therefore be omitted in the
     * {@code throws} clause of the method that applies the returned composed function.
     * <p>
     * With all that mentioned, the following example will demonstrate this methods correct use:
     * <pre>{@code
     * // when called with illegal value ClassNotFoundException is thrown
     * public Class<?> sneakyThrowingFunctionalInterface(final String className) throws ClassNotFoundException {
     *     return ThrowableFunction.of(Class::forName) // create the correct throwable functional interface
     *                .sneakyThrow() // create a non-throwable variant which is able to sneaky throw (this method)
     *                .apply(className); // apply non-throwable variant -> may sneaky throw a throwable
     * }
     *
     * // call the the method which surround the sneaky throwing functional interface
     * public void callingMethod() {
     *     try {
     *         final Class<?> clazz = sneakyThrowingFunctionalInterface("some illegal class name");
     *         // ... do something with clazz ...
     *     } catch(ClassNotFoundException e) {
     *         // ... do something with e ...
     *     }
     * }
     * }</pre>
     * In conclusion, this somewhat contentious ability should be used carefully, of course, with the advantages,
     * disadvantages and limitations described above kept in mind.
     *
     * @return A composed {@link IntToShortFunction} that applies this function to its input and sneakily throws the
     * thrown {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}.
     */
    @Nonnull
    default IntToShortFunction sneakyThrow() {
        return (value) -> {
            try {
                return this.applyAsShortThrows(value);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw ThrowableUtils.sneakyThrow(throwable);
            }
        };
    }

    /**
     * Returns a composed {@link IntToShortFunction} that first applies this function to its input, and then applies the
     * {@code recover} operation if a {@link Throwable} is thrown from this one. The {@code recover} operation is
     * represented by a curried operation which is called with throwable information and same argument of this
     * function.
     *
     * @param recover The operation to apply if this function throws a {@code Throwable}
     * @return A composed {@link IntToShortFunction} that first applies this function to its input, and then applies the
     * {@code recover} operation if a {@link Throwable} is thrown from this one.
     * @throws NullPointerException If given argument or the returned enclosing function is {@code null}
     * @implNote The implementation checks that the returned enclosing function from {@code recover} operation is not
     * {@code null}. If it is, then a {@link NullPointerException} with appropriate message is thrown.
     */
    @Nonnull
    default IntToShortFunction recover(
            @Nonnull final Function<? super Throwable, ? extends IntToShortFunction> recover) {
        Objects.requireNonNull(recover);
        return (value) -> {
            try {
                return this.applyAsShortThrows(value);
            } catch (Throwable throwable) {
                final IntToShortFunction function = recover.apply(throwable);
                Objects.requireNonNull(function, () -> "recover returned null for " + throwable.getClass() + ": "
                        + throwable.getMessage());
                return function.applyAsShort(value);
            }
        };
    }

}