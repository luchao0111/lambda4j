/*
 * Copyright (c) 2015 Gridtec. All rights reserved.
 *
 * This program is proprietary software; all information contained herein is, and
 * remains, the property of Gridtec and its suppliers, if any. The intellectual
 * and technical concepts contained herein are proprietary to Gridtec and its suppliers
 * and may be covered by Austrian and Foreign Patents, patents in process, and are
 * protected by trade secret or copyright law.
 *
 * Dissemination of this information or reproduction of this material is strictly
 * forbidden unless prior written permission is obtained from Gridtec company.
 *
 * This software consists of voluntary contributions made by individuals on behalf
 * of Gridtec. For more information on Gridtec, please refer to www.gridtec.at homepage.
 */
package at.gridtec.lambda4j.operators.ternary;

import at.gridtec.lambda4j.function.TriFunction;
import at.gridtec.lambda4j.operators.unary.ThrowableLongUnaryOperator;
import at.gridtec.lambda4j.util.ThrowableUtils;

import java.util.Objects;
import java.util.function.LongSupplier;

/**
 * This functional interface implements a {@link LongTernaryOperator} which is able to throw any {@link Exception}.
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
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsLong(long, long, long)}.
 *
 * @see TriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableLongTernaryOperator extends LongTernaryOperator {

    /**
     * Implicitly casts, and therefore wraps a given lambda as {@link ThrowableLongTernaryOperator}. This is a
     * convenience method in case the given {@link ThrowableLongTernaryOperator} is ambiguous for the compiler. This
     * might happen for overloaded methods accepting different functional interfaces. The given {@code
     * ThrowableLongTernaryOperator} is returned as-is.
     *
     * @param lambda The {@code ThrowableLongTernaryOperator} which should be returned as-is.
     * @return The given {@code ThrowableLongTernaryOperator} as-is.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableLongTernaryOperator wrap(final ThrowableLongTernaryOperator lambda) {
        Objects.requireNonNull(lambda);
        return lambda;
    }

    /**
     * Creates a {@link ThrowableLongTernaryOperator} from the given {@link LongTernaryOperator}. This method is just
     * convenience to provide a mapping for the non-throwable/throwable instances of the corresponding functional
     * interface.
     *
     * @param lambda A {@code LongTernaryOperator} which should be mapped to its throwable counterpart
     * @return A {@code ThrowableLongTernaryOperator} from the given {@code LongTernaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableLongTernaryOperator from(final LongTernaryOperator lambda) {
        Objects.requireNonNull(lambda);
        return lambda::applyAsLong;
    }

    /**
     * Creates a {@link ThrowableLongTernaryOperator} which always returns a given value.
     *
     * @param r The return value for the constant
     * @return A {@code ThrowableLongTernaryOperator} which always returns a given value.
     */
    static ThrowableLongTernaryOperator constant(long r) {
        return (left, middle, right) -> r;
    }

    /**
     * Creates a {@link ThrowableLongTernaryOperator} which uses the left parameter as argument for the given {@link
     * ThrowableLongUnaryOperator}.
     *
     * @return Creates a {@code ThrowableLongTernaryOperator} which uses the left parameter as argument for the given
     * {@code ThrowableLongUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableLongTernaryOperator useLeft(final ThrowableLongUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsLong(left);
    }

    /**
     * Creates a {@link ThrowableLongTernaryOperator} which uses the middle parameter as argument for the given {@link
     * ThrowableLongUnaryOperator}.
     *
     * @return Creates a {@code ThrowableLongTernaryOperator} which uses the middle parameter as argument for the given
     * {@code ThrowableLongUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableLongTernaryOperator useMiddle(final ThrowableLongUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsLong(middle);
    }

    /**
     * Creates a {@link ThrowableLongTernaryOperator} which uses the right parameter as argument for the given {@link
     * ThrowableLongUnaryOperator}.
     *
     * @return Creates a {@code ThrowableLongTernaryOperator} which uses the right parameter as argument for the given
     * {@code ThrowableLongUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableLongTernaryOperator useRight(final ThrowableLongUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsLong(right);
    }

    /**
     * The applyAsLong method for this {@link LongTernaryOperator} which is able to throw any {@link Exception} type.
     *
     * @param left The first argument to the operator (left input)
     * @param middle The second argument to the operator (middle input)
     * @param right The third argument to the operator (right input)
     * @return The return value from the operator.
     * @throws Exception Any exception from this functions action
     */
    long applyThrows(long left, long middle, long right) throws Exception;

    /**
     * Overrides the {@link LongTernaryOperator#applyAsLong(long, long, long)} method by using a redefinition as default
     * method. It calls the {@link #applyThrows(long, long, long)} method of this interface and catches the thrown
     * {@link Exception}s from it. If it is of type {@link RuntimeException}, the exception is rethrown. Other throwable
     * types are sneakily thrown.
     *
     * @param left The first argument for the operator
     * @param middle The second argument for the operator
     * @param right The third argument for the operator
     * @return The return value from the operator.
     * @see ThrowableUtils#sneakyThrow(Throwable)
     */
    @Override
    default long applyAsLong(long left, long middle, long right) {
        try {
            return applyThrows(left, middle, right);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw ThrowableUtils.sneakyThrow(e);
        }
    }

    /**
     * Returns a composed {@link ThrowableLongTernaryOperator} that applies this {@code ThrowableLongTernaryOperator} to
     * its input, and if an  error occurred, applies the given one. The exception from this {@code
     * ThrowableLongTernaryOperator} is ignored.
     *
     * @param other A {@code ThrowableLongTernaryOperator} to be applied if this one fails
     * @return A composed {@code ThrowableLongTernaryOperator} that applies this {@code ThrowableLongTernaryOperator},
     * and if an error occurred, applies the given one.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ThrowableLongTernaryOperator orElse(final ThrowableLongTernaryOperator other) {
        Objects.requireNonNull(other);
        return (left, middle, right) -> {
            try {
                return applyThrows(left, middle, right);
            } catch (Exception ignored) {
                return other.applyThrows(left, middle, right);
            }
        };
    }

    /**
     * Returns a composed {@link ThrowableLongTernaryOperator} that applies this {@code ThrowableLongTernaryOperator} to
     * its input, and if an error occurred, throws the given {@link Exception}. The exception from this {@code
     * ThrowableLongTernaryOperator} is added as suppressed to the given one.
     * <p>
     * The given exception must have a no arg constructor for reflection purposes. If not, then appropriate exception as
     * described in {@link Class#newInstance()} is thrown.
     *
     * @param <X> The type for the class extending {@code Exception}
     * @param clazz The exception class to throw if an error occurred
     * @return A composed {@code ThrowableLongTernaryOperator} that applies this {@code ThrowableLongTernaryOperator},
     * and if an error occurred, throws the given {@code Exception}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default <X extends Exception> ThrowableLongTernaryOperator orThrow(Class<X> clazz) {
        Objects.requireNonNull(clazz);
        return (left, middle, right) -> {
            try {
                return applyThrows(left, middle, right);
            } catch (Exception e) {
                X ex = clazz.newInstance();
                ex.addSuppressed(e);
                throw ThrowableUtils.sneakyThrow(ex);
            }
        };
    }

    /**
     * Returns a composed {@link LongTernaryOperator} that applies this {@link ThrowableLongTernaryOperator} to its
     * input, and if an error occurred, applies the given {@code LongTernaryOperator} representing a fallback. The
     * exception from this {@code ThrowableLongTernaryOperator} is ignored.
     *
     * @param fallback A {@code LongTernaryOperator} to be applied if this one fails
     * @return A composed {@code LongTernaryOperator} that applies this {@code ThrowableLongTernaryOperator}, and if an
     * error occurred, applies the given {@code LongTernaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default LongTernaryOperator fallbackTo(final LongTernaryOperator fallback) {
        Objects.requireNonNull(fallback);
        return (left, middle, right) -> {
            try {
                return applyThrows(left, middle, right);
            } catch (Exception ignored) {
                return fallback.applyAsLong(left, middle, right);
            }
        };
    }

    /**
     * Returns a composed {@link LongTernaryOperator} that applies this {@link ThrowableLongTernaryOperator} to its
     * input, and if an error occurred, returns the given value. The exception from this {@code
     * ThrowableLongTernaryOperator} is ignored.
     *
     * @param value The value to be returned if this {@code ThrowableLongTernaryOperator} fails
     * @return A composed {@code LongTernaryOperator} that applies this {@code ThrowableLongTernaryOperator}, and if an
     * error occurred, returns the given value.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default LongTernaryOperator orReturn(long value) {
        Objects.requireNonNull(value);
        return (left, middle, right) -> {
            try {
                return applyThrows(left, middle, right);
            } catch (Exception ignored) {
                return value;
            }
        };
    }

    /**
     * Returns a composed {@link LongTernaryOperator} that applies this {@link ThrowableLongTernaryOperator} to its
     * input, and if an error occurred, returns the supplied value from the given {@link LongSupplier}. The exception
     * from this {@code ThrowableLongTernaryOperator} is ignored.
     *
     * @param supplier A {@code LongSupplier} to return a supplied value if this {@code ThrowableLongTernaryOperator}
     * fails
     * @return A composed {@code LongTernaryOperator} that applies this {@code ThrowableLongTernaryOperator}, and if an
     * error occurred, the supplied value from the given {@code LongSupplier}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default LongTernaryOperator orReturn(final LongSupplier supplier) {
        Objects.requireNonNull(supplier);
        return (left, middle, right) -> {
            try {
                return applyThrows(left, middle, right);
            } catch (Exception ignored) {
                return supplier.getAsLong();
            }
        };
    }

    /**
     * Returns a composed {@link LongTernaryOperator} that applies this {@link ThrowableLongTernaryOperator} to its
     * input, and if an error occurred, returns the left value from this operator. The exception from this {@code
     * ThrowableLongTernaryOperator} is ignored.
     *
     * @return A composed {@code LongTernaryOperator} that applies this {@code ThrowableLongTernaryOperator}, and if an
     * error occurred, returns the left value from this operator.
     */
    default LongTernaryOperator orReturnLeft() {
        return (left, middle, right) -> {
            try {
                return applyThrows(left, middle, right);
            } catch (Exception ignored) {
                return left;
            }
        };
    }

    /**
     * Returns a composed {@link LongTernaryOperator} that applies this {@link ThrowableLongTernaryOperator} to its
     * input, and if an error occurred, returns the right value from this operator. The exception from this {@code
     * ThrowableLongTernaryOperator} is ignored.
     *
     * @return A composed {@code LongTernaryOperator} that applies this {@code ThrowableLongTernaryOperator}, and if an
     * error occurred, returns the right value from this operator.
     */
    default LongTernaryOperator orReturnMiddle() {
        return (left, middle, right) -> {
            try {
                return applyThrows(left, middle, right);
            } catch (Exception ignored) {
                return middle;
            }
        };
    }

    /**
     * Returns a composed {@link LongTernaryOperator} that applies this {@link ThrowableLongTernaryOperator} to its
     * input, and if an error occurred, returns the right value from this operator. The exception from this {@code
     * ThrowableLongTernaryOperator} is ignored.
     *
     * @return A composed {@code LongTernaryOperator} that applies this {@code ThrowableLongTernaryOperator}, and if an
     * error occurred, returns the right value from this operator.
     */
    default LongTernaryOperator orReturnRight() {
        return (left, middle, right) -> {
            try {
                return applyThrows(left, middle, right);
            } catch (Exception ignored) {
                return right;
            }
        };
    }
}