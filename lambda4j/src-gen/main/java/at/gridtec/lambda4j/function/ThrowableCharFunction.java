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
package at.gridtec.lambda4j.function;

import at.gridtec.lambda4j.Lambda;
import at.gridtec.lambda4j.consumer.ThrowableCharConsumer;
import at.gridtec.lambda4j.consumer.ThrowableConsumer;
import at.gridtec.lambda4j.core.exception.ThrownByFunctionalInterfaceException;
import at.gridtec.lambda4j.core.util.ThrowableUtils;
import at.gridtec.lambda4j.function.conversion.ThrowableBooleanToCharFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableByteToCharFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableDoubleToCharFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableFloatToCharFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableIntToCharFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableLongToCharFunction;
import at.gridtec.lambda4j.function.conversion.ThrowableShortToCharFunction;
import at.gridtec.lambda4j.function.to.ThrowableToCharFunction;
import at.gridtec.lambda4j.operator.unary.ThrowableCharUnaryOperator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Represents an operation that accepts one {@code char}-valued input argument and produces a result which is able to
 * throw any {@link Throwable}. This is a primitive specialization of {@link ThrowableFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyThrows(char)}.
 *
 * @param <R> The type of return value from the function
 * @param <X> The type of the throwable to be thrown by this function
 * @see ThrowableFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableCharFunction<R, X extends Throwable> extends Lambda {

    /**
     * Constructs a {@link ThrowableCharFunction} based on a lambda expression or a method reference. Thereby the given
     * lambda expression or method reference is returned on an as-is basis to implicitly transform it to the desired
     * type. With this method, it is possible to ensure that correct type is used from lambda expression or method
     * reference.
     *
     * @param <R> The type of return value from the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ThrowableCharFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but if {@code null} given, {@code
     * null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @Nonnull
    static <R, X extends Throwable> ThrowableCharFunction<R, X> of(
            @Nonnull final ThrowableCharFunction<R, X> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ThrowableCharFunction} with the given argument and returns its result.
     *
     * @param <R> The type of return value from the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code ThrowableCharFunction}.
     * @throws NullPointerException If given argument is {@code null}
     * @throws X Any throwable from this functions action
     */
    static <R, X extends Throwable> R call(@Nonnull final ThrowableCharFunction<? extends R, ? extends X> function,
            char value) throws X {
        Objects.requireNonNull(function);
        return function.applyThrows(value);
    }

    /**
     * Creates a {@link ThrowableCharFunction} which always returns a given value.
     *
     * @param <R> The type of return value from the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param ret The return value for the constant
     * @return A {@code ThrowableCharFunction} which always returns a given value.
     */
    @Nonnull
    static <R, X extends Throwable> ThrowableCharFunction<R, X> constant(R ret) {
        return (value) -> ret;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param value The argument to the function
     * @return The return value from the function, which is its result.
     * @throws X Any throwable from this functions action
     */
    R applyThrows(char value) throws X;

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
     * Returns a composed {@link ThrowableFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     *
     * @param <A> The type of the argument to the given function, and of composed function
     * @param before The function to apply before this function is applied
     * @return A composed {@code ThrowableFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A> ThrowableFunction<A, R, X> compose(
            @Nonnull final ThrowableToCharFunction<? super A, ? extends X> before) {
        Objects.requireNonNull(before);
        return (a) -> applyThrows(before.applyAsCharThrows(a));
    }

    /**
     * Returns a composed {@link ThrowableBooleanFunction} that first applies the {@code before} function to its input,
     * and then applies this function to the result. This method is just convenience, to provide the ability to execute
     * an operation which accepts {@code boolean} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code ThrowableBooleanFunction} that first applies the {@code before} function to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableBooleanFunction<R, X> composeFromBoolean(
            @Nonnull final ThrowableBooleanToCharFunction<? extends X> before) {
        Objects.requireNonNull(before);
        return (value) -> applyThrows(before.applyAsCharThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableByteFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code byte} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code ThrowableByteFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableByteFunction<R, X> composeFromByte(
            @Nonnull final ThrowableByteToCharFunction<? extends X> before) {
        Objects.requireNonNull(before);
        return (value) -> applyThrows(before.applyAsCharThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableCharFunction} that first applies the {@code before} operator to its input, and
     * then applies this function to the result. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code char} input, before this primitive function is executed.
     *
     * @param before The operator to apply before this function is applied
     * @return A composed {@code ThrowableCharFunction} that first applies the {@code before} operator to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableCharFunction<R, X> composeFromChar(@Nonnull final ThrowableCharUnaryOperator<? extends X> before) {
        Objects.requireNonNull(before);
        return (value) -> applyThrows(before.applyAsCharThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableDoubleFunction} that first applies the {@code before} function to its input,
     * and then applies this function to the result. This method is just convenience, to provide the ability to execute
     * an operation which accepts {@code double} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code ThrowableDoubleFunction} that first applies the {@code before} function to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableDoubleFunction<R, X> composeFromDouble(
            @Nonnull final ThrowableDoubleToCharFunction<? extends X> before) {
        Objects.requireNonNull(before);
        return (value) -> applyThrows(before.applyAsCharThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableFloatFunction} that first applies the {@code before} function to its input,
     * and then applies this function to the result. This method is just convenience, to provide the ability to execute
     * an operation which accepts {@code float} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code ThrowableFloatFunction} that first applies the {@code before} function to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableFloatFunction<R, X> composeFromFloat(
            @Nonnull final ThrowableFloatToCharFunction<? extends X> before) {
        Objects.requireNonNull(before);
        return (value) -> applyThrows(before.applyAsCharThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableIntFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code int} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code ThrowableIntFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableIntFunction<R, X> composeFromInt(@Nonnull final ThrowableIntToCharFunction<? extends X> before) {
        Objects.requireNonNull(before);
        return (value) -> applyThrows(before.applyAsCharThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableLongFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code long} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code ThrowableLongFunction} that first applies the {@code before} function to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableLongFunction<R, X> composeFromLong(
            @Nonnull final ThrowableLongToCharFunction<? extends X> before) {
        Objects.requireNonNull(before);
        return (value) -> applyThrows(before.applyAsCharThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableShortFunction} that first applies the {@code before} function to its input,
     * and then applies this function to the result. This method is just convenience, to provide the ability to execute
     * an operation which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code ThrowableShortFunction} that first applies the {@code before} function to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableShortFunction<R, X> composeFromShort(
            @Nonnull final ThrowableShortToCharFunction<? extends X> before) {
        Objects.requireNonNull(before);
        return (value) -> applyThrows(before.applyAsCharThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ThrowableCharFunction<S, X> andThen(
            @Nonnull final ThrowableFunction<? super R, ? extends S, ? extends X> after) {
        Objects.requireNonNull(after);
        return (value) -> after.applyThrows(applyThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableCharConsumer} that fist applies this function to its input, and then consumes
     * the result using the given {@link ThrowableConsumer}.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ThrowableCharConsumer} that first applies this function to its input, and then consumes
     * the result using the given {@code ThrowableConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ThrowableCharConsumer<X> consume(@Nonnull final ThrowableConsumer<? super R, ? extends X> consumer) {
        Objects.requireNonNull(consumer);
        return (value) -> consumer.acceptThrows(applyThrows(value));
    }

    /**
     * Returns a memoized (caching) version of this {@link ThrowableCharFunction}. Whenever it is called, the mapping
     * between the input parameter and the return value is preserved in a cache, making subsequent calls returning the
     * memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ThrowableCharFunction}.
     * @implSpec This implementation does not allow the input parameter or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ThrowableCharFunction<R, X> memoized() {
        if (this instanceof Memoized) {
            return this;
        } else {
            final Map<Character, R> cache = new ConcurrentHashMap<>();
            final Object lock = new Object();
            return (ThrowableCharFunction<R, X> & Memoized) (value) -> {
                final R returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(value, ThrowableFunction.of(this::applyThrows));
                }
                return returnValue;
            };
        }
    }

    /**
     * Converts this function to an equal function, which ensures that its result is not {@code null} using {@link
     * Optional}. This method mainly exists to avoid unnecessary {@code NullPointerException}s through referencing
     * {@code null} from this function.
     *
     * @return An equal function, which ensures that its result is not {@code null}.
     */
    @Nonnull
    default ThrowableCharFunction<Optional<R>, X> nonNull() {
        return (value) -> Optional.ofNullable(applyThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableFunction} which represents this {@link ThrowableCharFunction}. Thereby the
     * primitive input argument for this function is autoboxed.
     *
     * @return A composed {@code ThrowableFunction} which represents this {@code ThrowableCharFunction}.
     */
    @Nonnull
    default ThrowableFunction<Character, R, X> boxed() {
        return this::applyThrows;
    }

    /**
     * Returns a composed {@link CharFunction} that applies this function to its input and nests the thrown {@link
     * Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}. The throwable is nested
     * (wrapped) in a {@link ThrownByFunctionalInterfaceException}, which is constructed from the thrown throwables
     * message and the thrown throwable itself.
     *
     * @return A composed {@code CharFunction} that applies this function to its input and nests the thrown {@code
     * Throwable} from it, unless it is of type {@code RuntimeException} or {@code Error}.
     */
    @Nonnull
    default CharFunction<R> nest() {
        return (value) -> {
            try {
                return this.applyThrows(value);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw new ThrownByFunctionalInterfaceException(throwable.getMessage(), throwable);
            }
        };
    }

    /**
     * Returns a composed {@link CharFunction} that applies this function to its input and sneakily throws the thrown
     * {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}. This means that each
     * throwable thrown from the returned composed function behaves exactly the same as an <em>unchecked</em> throwable
     * does. As a result, there is no need to handle the throwable of this function in the returned composed function by
     * either wrapping it in an <em>unchecked</em> throwable or to declare it in the {@code throws} clause, as it would
     * be done in a non sneaky throwing function.
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
     *         final Class<?> sneakyThrowingFunctionalInterface("some illegal class name");
     *     } catch(ClassNotFoundException e) {
     *         // ... do something with e ...
     *     }
     * }
     * }</pre>
     * In conclusion, this somewhat contentious ability should be used carefully, of course, with the advantages,
     * disadvantages and limitations described above kept in mind.
     *
     * @return A composed {@link CharFunction} that applies this function to its input and sneakily throws the thrown
     * {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}.
     */
    @Nonnull
    default CharFunction<R> sneakyThrow() {
        return (value) -> {
            try {
                return this.applyThrows(value);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw ThrowableUtils.sneakyThrow(throwable);
            }
        };
    }

}
