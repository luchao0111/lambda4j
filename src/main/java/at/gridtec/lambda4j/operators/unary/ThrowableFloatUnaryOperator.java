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
package at.gridtec.lambda4j.operators.unary;

import at.gridtec.lambda4j.util.ThrowableUtils;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * This functional interface implements a {@link FloatUnaryOperator} which is able to throw any {@link Exception}.
 * <p>
 * The thrown {@link Exception} is sneakily thrown unless its a {@link RuntimeException}. This means that there is no
 * need to catch the thrown exception, nor to declare that you throw it using the <em>throws</em> keyword. The exception
 * is still thrown, but the Java compiler stops warning about it.
 * <p>
 * However, when using this throwing lambda, be aware of the following consequences: <ol> <li>If the calling code is to
 * handle a thrown {@code Exception}, it MUST be declared in the methods <em>throws</em> clause which uses this lambda.
 * The compiler will not force you to add it.</li> <li>If the calling code already handles a thrown {@code Exception},
 * it needs to be declared in the methods <em>throws</em> clause which uses this lambda. If not the compiler prints an
 * error that the corresponding {@code try} block never throws the specific exception.</li> <li>In any case, there is no
 * way of explicitly catching the thrown {@code Exception} in the method which uses this lambda. If you try, the
 * compiler prints an error that the corresponding {@code try} block never throws the specific exception.</li> </ol>
 * <p>
 * When the calling code never throws the specific exception that it declares, you should omit it. For example: {@code
 * new String(byteArr, "UTF-8") throws UnsupportedEncodingException}, but UTF-8 is guaranteed by the Java specification
 * to be always present. The exception should therefore be omitted.
 * <p>
 * Moreover, if no checked exception should be used at all or its use is inappropriate for any reasons, omit the
 * declaration in the <em>throws</em> clause. The checked exception will behave just like a normal <b>unchecked</b>
 * exception due to sneaky throwing.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsFloatThrows(float)}.
 *
 * @apiNote This is a throwable JRE lambda
 * @see java.util.function.UnaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableFloatUnaryOperator extends FloatUnaryOperator {

    /**
     * Implicitly casts, and therefore wraps a given lambda as {@link ThrowableFloatUnaryOperator}. This is a
     * convenience method in case the given {@link ThrowableFloatUnaryOperator} is ambiguous for the compiler. This
     * might happen for overloaded methods accepting different functional interfaces. The given {@code
     * ThrowableFloatUnaryOperator} is returned as-is.
     *
     * @param lambda The {@code ThrowableFloatUnaryOperator} which should be returned as-is.
     * @return The given {@code ThrowableFloatUnaryOperator} as-is.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableFloatUnaryOperator wrap(final ThrowableFloatUnaryOperator lambda) {
        Objects.requireNonNull(lambda);
        return lambda;
    }

    /**
     * Creates a {@link ThrowableFloatUnaryOperator} from the given {@link FloatUnaryOperator}. This method is just
     * convenience to provide a mapping for the non-throwable/throwable instances of the corresponding functional
     * interface.
     *
     * @param lambda A {@code FloatUnaryOperator} which should be mapped to its throwable counterpart
     * @return A {@code ThrowableFloatUnaryOperator} from the given {@code FloatUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableFloatUnaryOperator from(final FloatUnaryOperator lambda) {
        Objects.requireNonNull(lambda);
        return lambda::applyAsFloat;
    }

    /**
     * Creates a {@link ThrowableFloatUnaryOperator} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code ThrowableFloatUnaryOperator} which always returns a given value.
     */
    static ThrowableFloatUnaryOperator constant(float ret) {
        return operand -> ret;
    }

    /**
     * The apply method for this {@link FloatUnaryOperator} which is able to throw any {@link Exception} type.
     *
     * @param operand The argument for the operator
     * @return The return value from the operator.
     * @throws Exception Any exception from this functions action
     */
    float applyAsFloatThrows(float operand) throws Exception;

    /**
     * Overrides the {@link FloatUnaryOperator#applyAsFloat(float)} method by using a redefinition as default method. It
     * calls the {@link #applyAsFloatThrows(float)} method of this interface and catches the thrown {@link Exception}s
     * from it. If it is of type {@link RuntimeException}, the exception is rethrown. Other throwable types are sneakily
     * thrown.
     *
     * @param operand The argument for the operator
     * @return The return value from the operator.
     * @see ThrowableUtils#sneakyThrow(Throwable)
     */
    @Override
    default float applyAsFloat(float operand) {
        try {
            return applyAsFloatThrows(operand);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw ThrowableUtils.sneakyThrow(e);
        }
    }

    /**
     * Returns a composed {@link ThrowableFloatUnaryOperator} that applies this {@code ThrowableFloatUnaryOperator} to
     * its input, and if an  error occurred, applies the given one. The exception from this {@code
     * ThrowableFloatUnaryOperator} is ignored.
     *
     * @param other A {@code ThrowableFloatUnaryOperator} to be applied if this one fails
     * @return A composed {@code ThrowableFloatUnaryOperator} that applies this {@code ThrowableFloatUnaryOperator}, and
     * if an error occurred, applies the given one.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ThrowableFloatUnaryOperator orElse(final ThrowableFloatUnaryOperator other) {
        Objects.requireNonNull(other);
        return operand -> {
            try {
                return applyAsFloatThrows(operand);
            } catch (Exception ignored) {
                return other.applyAsFloatThrows(operand);
            }
        };
    }

    /**
     * Returns a composed {@link ThrowableFloatUnaryOperator} that applies this {@code ThrowableFloatUnaryOperator} to
     * its input, and if an error occurred, throws the given {@link Exception}. The exception from this {@code
     * ThrowableFloatUnaryOperator} is added as suppressed to the given one.
     * <p>
     * The given exception must have a no arg constructor for reflection purposes. If not, then appropriate exception as
     * described in {@link Class#newInstance()} is thrown.
     *
     * @param <X> The type for the class extending {@code Exception}
     * @param clazz The exception class to throw if an error occurred
     * @return A composed {@code ThrowableFloatUnaryOperator} that applies this {@code ThrowableFloatUnaryOperator}, and
     * if an error occurred, throws the given {@code Exception}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default <X extends Exception> ThrowableFloatUnaryOperator orThrow(Class<X> clazz) {
        Objects.requireNonNull(clazz);
        return operand -> {
            try {
                return applyAsFloatThrows(operand);
            } catch (Exception e) {
                X ex = clazz.newInstance();
                ex.addSuppressed(e);
                throw ThrowableUtils.sneakyThrow(ex);
            }
        };
    }

    /**
     * Returns a composed {@link FloatUnaryOperator} that applies this {@link ThrowableFloatUnaryOperator} to its input,
     * and if an error occurred, applies the given {@code FloatUnaryOperator} representing a fallback. The exception
     * from this {@code ThrowableFloatUnaryOperator} is ignored.
     *
     * @param fallback A {@code FloatUnaryOperator} to be applied if this one fails
     * @return A composed {@code FloatUnaryOperator} that applies this {@code ThrowableFloatUnaryOperator}, and if an
     * error occurred, applies the given {@code UnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default FloatUnaryOperator fallbackTo(final FloatUnaryOperator fallback) {
        Objects.requireNonNull(fallback);
        return operand -> {
            try {
                return applyAsFloatThrows(operand);
            } catch (Exception ignored) {
                return fallback.applyAsFloat(operand);
            }
        };
    }

    /**
     * Returns a composed {@link FloatUnaryOperator} that applies this {@link ThrowableFloatUnaryOperator} to its input,
     * and if an error occurred, returns the given value. The exception from this {@code ThrowableFloatUnaryOperator} is
     * ignored.
     *
     * @param value The value to be returned if this {@code ThrowableFloatUnaryOperator} fails
     * @return A composed {@code FloatUnaryOperator} that applies this {@code ThrowableFloatUnaryOperator}, and if an
     * error occurred, returns the given value.
     */
    default FloatUnaryOperator orReturn(float value) {
        return operand -> {
            try {
                return applyAsFloatThrows(operand);
            } catch (Exception ignored) {
                return value;
            }
        };
    }

    /**
     * Returns a composed {@link FloatUnaryOperator} that applies this {@link ThrowableFloatUnaryOperator} to its input,
     * and if an error occurred, returns the supplied value from the given {@link Supplier}. The exception from this
     * {@code ThrowableFloatUnaryOperator} is ignored.
     *
     * @param supplier A {@code Supplier} to return a supplied value if this {@code ThrowableFloatUnaryOperator} fails
     * @return A composed {@code FloatUnaryOperator} that applies this {@code ThrowableFloatUnaryOperator}, and if an
     * error occurred, the supplied value from the given {@code Supplier}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default FloatUnaryOperator orReturn(final Supplier<? extends Float> supplier) {
        Objects.requireNonNull(supplier);
        return operand -> {
            try {
                return applyAsFloatThrows(operand);
            } catch (Exception ignored) {
                return supplier.get();
            }
        };
    }

    /**
     * Returns a composed {@link FloatUnaryOperator} that applies this {@link ThrowableFloatUnaryOperator} to its input,
     * and if an error occurred, returns its value. The exception from this {@code ThrowableFloatUnaryOperator} is
     * ignored.
     *
     * @return A composed {@code FloatUnaryOperator} that applies this {@code ThrowableFloatUnaryOperator}, and if an
     * error occurred, returns its value.
     */
    default FloatUnaryOperator orReturnSelf() {
        return operand -> {
            try {
                return applyAsFloatThrows(operand);
            } catch (Exception ignored) {
                return operand;
            }
        };
    }
}
