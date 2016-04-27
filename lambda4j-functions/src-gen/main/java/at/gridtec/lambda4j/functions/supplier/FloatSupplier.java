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
package at.gridtec.lambda4j.functions.supplier;

import at.gridtec.lambda4j.functions.Lambda;
import at.gridtec.lambda4j.functions.consumer.Consumer2;
import at.gridtec.lambda4j.functions.consumer.FloatConsumer;
import at.gridtec.lambda4j.functions.function.FloatFunction;
import at.gridtec.lambda4j.functions.function.conversion.FloatToByteFunction;
import at.gridtec.lambda4j.functions.function.conversion.FloatToCharFunction;
import at.gridtec.lambda4j.functions.function.conversion.FloatToDoubleFunction;
import at.gridtec.lambda4j.functions.function.conversion.FloatToIntFunction;
import at.gridtec.lambda4j.functions.function.conversion.FloatToLongFunction;
import at.gridtec.lambda4j.functions.function.conversion.FloatToShortFunction;
import at.gridtec.lambda4j.functions.operator.unary.FloatUnaryOperator;
import at.gridtec.lambda4j.functions.predicate.FloatPredicate;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Represents a supplier of {@code float}-valued results. This is a primitive specialization of {@link Supplier2}.
 * <p>
 * There is no requirement that a distinct result be returned each time the supplier is invoked.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #getAsFloat()}.
 *
 * @see Supplier2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface FloatSupplier extends Lambda {

    /**
     * Constructs a {@link FloatSupplier} based on a lambda expression or a method reference. Thereby the given lambda
     * expression or method reference is returned on an as-is basis to implicitly transform it to the desired type. With
     * this method, it is possible to ensure that correct type is used from lambda expression or method reference.
     *
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code FloatSupplier} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but if {@code null} given, {@code
     * null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @Nonnull
    static FloatSupplier of(@Nonnull final FloatSupplier expression) {
        return expression;
    }

    /**
     * Calls the given {@link FloatSupplier} with the given argument and returns its result.
     *
     * @param supplier The supplier to be called
     * @return The result from the given {@code FloatSupplier}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static float call(@Nonnull final FloatSupplier supplier) {
        Objects.requireNonNull(supplier);
        return supplier.getAsFloat();
    }

    /**
     * Creates a {@link FloatSupplier} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code FloatSupplier} which always returns a given value.
     */
    @Nonnull
    static FloatSupplier constant(float ret) {
        return () -> ret;
    }

    /**
     * Applies this supplier to the given argument.
     *
     * @return The return value from the supplier, which is its result.
     */
    float getAsFloat();

    /**
     * Returns the number of arguments for this supplier.
     *
     * @return The number of arguments for this supplier.
     * @implSpec The default implementation always returns {@code 0}.
     */
    @Nonnegative
    default int arity() {
        return 0;
    }

    /**
     * Returns a composed {@link Supplier2} that first applies this supplier to its input, and then applies the {@code
     * after} function to the result. If evaluation of either operation throws an exception, it is relayed to the caller
     * of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed supplier
     * @param after The function to apply after this supplier is applied
     * @return A composed {@code Supplier2} that first applies this supplier to its input, and then applies the {@code
     * after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> Supplier2<S> andThen(@Nonnull final FloatFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return () -> after.apply(getAsFloat());
    }

    /**
     * Returns a composed {@link BooleanSupplier2} that first applies this supplier to its input, and then applies the
     * {@code after} predicate to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive supplier to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this supplier is applied
     * @return A composed {@code BooleanSupplier2} that first applies this supplier to its input, and then applies the
     * {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BooleanSupplier2 andThenToBoolean(@Nonnull final FloatPredicate after) {
        Objects.requireNonNull(after);
        return () -> after.test(getAsFloat());
    }

    /**
     * Returns a composed {@link ByteSupplier} that first applies this supplier to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive supplier to an operation returning {@code byte}.
     *
     * @param after The function to apply after this supplier is applied
     * @return A composed {@code ByteSupplier} that first applies this supplier to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ByteSupplier andThenToByte(@Nonnull final FloatToByteFunction after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsByte(getAsFloat());
    }

    /**
     * Returns a composed {@link CharSupplier} that first applies this supplier to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive supplier to an operation returning {@code char}.
     *
     * @param after The function to apply after this supplier is applied
     * @return A composed {@code CharSupplier} that first applies this supplier to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default CharSupplier andThenToChar(@Nonnull final FloatToCharFunction after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsChar(getAsFloat());
    }

    /**
     * Returns a composed {@link DoubleSupplier2} that first applies this supplier to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive supplier to an operation returning {@code double}.
     *
     * @param after The function to apply after this supplier is applied
     * @return A composed {@code DoubleSupplier2} that first applies this supplier to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default DoubleSupplier2 andThenToDouble(@Nonnull final FloatToDoubleFunction after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsDouble(getAsFloat());
    }

    /**
     * Returns a composed {@link FloatSupplier} that first applies this supplier to its input, and then applies the
     * {@code after} operator to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive supplier to an operation returning {@code float}.
     *
     * @param after The operator to apply after this supplier is applied
     * @return A composed {@code FloatSupplier} that first applies this supplier to its input, and then applies the
     * {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default FloatSupplier andThenToFloat(@Nonnull final FloatUnaryOperator after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsFloat(getAsFloat());
    }

    /**
     * Returns a composed {@link IntSupplier2} that first applies this supplier to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive supplier to an operation returning {@code int}.
     *
     * @param after The function to apply after this supplier is applied
     * @return A composed {@code IntSupplier2} that first applies this supplier to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default IntSupplier2 andThenToInt(@Nonnull final FloatToIntFunction after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsInt(getAsFloat());
    }

    /**
     * Returns a composed {@link LongSupplier2} that first applies this supplier to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive supplier to an operation returning {@code long}.
     *
     * @param after The function to apply after this supplier is applied
     * @return A composed {@code LongSupplier2} that first applies this supplier to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default LongSupplier2 andThenToLong(@Nonnull final FloatToLongFunction after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsLong(getAsFloat());
    }

    /**
     * Returns a composed {@link ShortSupplier} that first applies this supplier to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive supplier to an operation returning {@code short}.
     *
     * @param after The function to apply after this supplier is applied
     * @return A composed {@code ShortSupplier} that first applies this supplier to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ShortSupplier andThenToShort(@Nonnull final FloatToShortFunction after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsShort(getAsFloat());
    }

    /**
     * Returns a composed {@link Consumer2} that first gets the result from this supplier, and then consumes the result
     * using the given {@link FloatConsumer}. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code Consumer2} that first gets the result from this supplier, and then consumes the result
     * using the given {@code FloatConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote Due to the fact that a {@link FloatSupplier} receives no input, we do not need to pass an argument of a
     * particular type to the resulting {@code Consumer2}. As a result, this method returns a {@code Consumer2} of
     * {@link Void}, whose argument is ignored. Therefore, the input parameter will always be {@code null} when the
     * resulting consumer is called with {@code Consumer#accept(Object)}.
     */
    @Nonnull
    default Consumer2<Void> consume(@Nonnull final FloatConsumer consumer) {
        Objects.requireNonNull(consumer);
        return ignored -> consumer.accept(getAsFloat());
    }

    /**
     * Returns a composed {@link Supplier} which represents this {@link FloatSupplier}. Thereby the primitive input
     * argument for this supplier is autoboxed. This method is just convenience to provide the ability to use this
     * {@code FloatSupplier} with JDK specific methods, only accepting {@code Supplier}.
     *
     * @return A composed {@code Supplier} which represents this {@code FloatSupplier}.
     */
    @Nonnull
    default Supplier<Float> boxed() {
        return this::getAsFloat;
    }

}