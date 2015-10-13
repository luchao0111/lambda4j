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

package at.gridtec.lambda4j.consumer.primitives.tri;

import at.gridtec.lambda4j.consumer.TriConsumer;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.LongConsumer;
import java.util.function.LongUnaryOperator;
import java.util.function.ToLongFunction;

/**
 * Represents an operation that accepts three {@code long}-valued arguments and returns no result. This is the primitive
 * type specialization of {@link TriConsumer} for {@code long}. Unlike most other functional interfaces, {@code
 * LongTriConsumer} is expected to operate via side-effects.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #accept(long, long, long)}.
 *
 * @see TriConsumer
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface LongTriConsumer {

    /**
     * Creates a {@link LongTriConsumer} which uses the {@code first} parameter as argument for the given {@link
     * LongConsumer}.
     *
     * @param consumer The consumer which accepts the {@code first} parameter of this one
     * @return Creates a {@code LongTriConsumer} which uses the {@code first} parameter as argument for the given {@code
     * LongConsumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static LongTriConsumer onlyFirst(@Nonnull final LongConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.accept(value1);
    }

    /**
     * Creates a {@link LongTriConsumer} which uses the {@code second} parameter as argument for the given {@link
     * LongConsumer}.
     *
     * @param consumer The consumer which accepts the {@code second} parameter of this one
     * @return Creates a {@code LongTriConsumer} which uses the {@code second} parameter as argument for the given
     * {@code LongConsumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static LongTriConsumer onlySecond(@Nonnull final LongConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.accept(value2);
    }

    /**
     * Creates a {@link LongTriConsumer} which uses the {@code third} parameter as argument for the given {@link
     * LongConsumer}.
     *
     * @param consumer The consumer which accepts the {@code third} parameter of this one
     * @return Creates a {@code LongTriConsumer} which uses the {@code third} parameter as argument for the given {@code
     * LongConsumer}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static LongTriConsumer onlyThird(@Nonnull final LongConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.accept(value3);
    }

    /**
     * Performs this operation on the given arguments.
     *
     * @param value1 The first argument to the operation to be consumed
     * @param value2 The second argument to the operation to be consumed
     * @param value3 The third argument to the operation to be consumed
     */
    void accept(long value1, long value2, long value3);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     */
    @Nonnegative
    default int arity() {
        return 3;
    }

    /**
     * Returns a composed {@link LongTriConsumer} that applies the given {@code before} {@link LongUnaryOperator}s to
     * its input, and then applies this operation to the result. If evaluation of either of the given operations throws
     * an exception, it is relayed to the caller of the composed function.
     *
     * @param before1 The first before {@code LongUnaryOperator} to apply before this operation is applied
     * @param before2 The second before {@code LongUnaryOperator} to apply before this operation is applied
     * @param before3 The third before {@code LongUnaryOperator} to apply before this operation is applied
     * @return A composed {@code LongTriConsumer} that applies the given {@code before} {@code LongUnaryOperator}s to
     * its input, and then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(LongTriConsumer)
     */
    default LongTriConsumer compose(final LongUnaryOperator before1, final LongUnaryOperator before2,
            final LongUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> accept(before1.applyAsLong(value1), before2.applyAsLong(value2),
                                                  before3.applyAsLong(value3));
    }

    /**
     * Returns a composed {@link TriConsumer} that applies the given {@code before} {@link ToLongFunction}s to its
     * input, and then applies this operation to the result. If evaluation of either of the given operations throws an
     * exception, it is relayed to the caller of the composed function.
     *
     * @param <T> The type of the argument to the first before operation
     * @param <U> The type of the argument to the second before operation
     * @param <V> The type of the argument to the third before operation
     * @param before1 The first before {@code ToLongFunction} to apply before this operation is applied
     * @param before2 The second before {@code ToLongFunction} to apply before this operation is applied
     * @param before3 The third before {@code ToLongFunction} to apply before this operation is applied
     * @return A composed {@code TriConsumer} that applies the given {@code before} {@code ToLongFunction}s to its
     * input, and then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(LongTriConsumer)
     */
    default <T, U, V> TriConsumer<T, U, V> compose(final ToLongFunction<? super T> before1,
            final ToLongFunction<? super U> before2, final ToLongFunction<? super V> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> accept(before1.applyAsLong(value1), before2.applyAsLong(value2),
                                                  before3.applyAsLong(value3));
    }

    /**
     * Returns a composed {@link LongTriConsumer} that performs, in sequence, this operation followed by the {@code
     * after} operation. If evaluation of either operation throws an exception, it is relayed to the caller of the
     * composed function. If performing this operation throws an exception, the {@code after} operation will not be
     * performed.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@link LongTriConsumer} that performs, in sequence, this operation followed by the {@code
     * after} operation.
     * @throws NullPointerException If given after operation is {@code null}
     * @see #compose(LongUnaryOperator, LongUnaryOperator, LongUnaryOperator)
     * @see #compose(ToLongFunction, ToLongFunction, ToLongFunction)
     */
    default LongTriConsumer andThen(final LongTriConsumer after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> {
            accept(value1, value2, value3);
            after.accept(value1, value2, value3);
        };
    }

    /**
     * Returns a composed {@link TriConsumer} which represents this {@link LongTriConsumer}. Thereby the primitive input
     * argument for this consumer is autoboxed.
     *
     * @return A composed {@code TriConsumer} which represents this {@code LongTriConsumer}.
     */
    @Nonnull
    default TriConsumer<Long, Long, Long> boxed() {
        return this::accept;
    }
}
