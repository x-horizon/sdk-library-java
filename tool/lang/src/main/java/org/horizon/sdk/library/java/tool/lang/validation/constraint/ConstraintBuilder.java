package org.horizon.sdk.library.java.tool.lang.validation.constraint;

import org.horizon.sdk.library.java.tool.lang.collection.Collections;
import org.horizon.sdk.library.java.tool.lang.functional.SerializableFunction;
import org.horizon.sdk.library.java.tool.lang.reflect.Reflects;
import org.horizon.sdk.library.java.tool.lang.validation.support.ValidationGroup;
import org.horizon.sdk.library.java.tool.lang.validation.support.ValidationSchema;
import org.horizon.sdk.library.java.tool.lang.validation.support.Validator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * @author wjm
 * @since 2025-04-18 15:37
 */
public class ConstraintBuilder<M> {

    private final Map<ConstraintConditionAdaptor<M>, List<ValidationSchema<M, ?>>> conditionValidationSchemaMap = Collections.newLinkedHashMap();

    private final ConstraintConditionAdaptor<M> defaultConstraintCondition = (ignore1, ignore2) -> true;

    public ConstraintBuilder<M> constraint(ByteFunction<M> fieldValueGetter, UnaryOperator<ByteConstraint> constraintOperator) {
        return this.constraint(fieldValueGetter, Reflects.getFieldComment(fieldValueGetter), constraintOperator);
    }

    public ConstraintBuilder<M> constraint(ShortFunction<M> fieldValueGetter, UnaryOperator<ShortConstraint> constraintOperator) {
        return this.constraint(fieldValueGetter, Reflects.getFieldComment(fieldValueGetter), constraintOperator);
    }

    public ConstraintBuilder<M> constraint(IntegerFunction<M> fieldValueGetter, UnaryOperator<IntegerConstraint> constraintOperator) {
        return this.constraint(fieldValueGetter, Reflects.getFieldComment(fieldValueGetter), constraintOperator);
    }

    public ConstraintBuilder<M> constraint(LongFunction<M> fieldValueGetter, UnaryOperator<LongConstraint> constraintOperator) {
        return this.constraint(fieldValueGetter, Reflects.getFieldComment(fieldValueGetter), constraintOperator);
    }

    public ConstraintBuilder<M> constraint(FloatFunction<M> fieldValueGetter, UnaryOperator<FloatConstraint> constraintOperator) {
        return this.constraint(fieldValueGetter, Reflects.getFieldComment(fieldValueGetter), constraintOperator);
    }

    public ConstraintBuilder<M> constraint(DoubleFunction<M> fieldValueGetter, UnaryOperator<DoubleConstraint> constraintOperator) {
        return this.constraint(fieldValueGetter, Reflects.getFieldComment(fieldValueGetter), constraintOperator);
    }

    public ConstraintBuilder<M> constraint(BigDecimalFunction<M> fieldValueGetter, UnaryOperator<BigDecimalConstraint> constraintOperator) {
        return this.constraint(fieldValueGetter, Reflects.getFieldComment(fieldValueGetter), constraintOperator);
    }

    public ConstraintBuilder<M> constraint(CharSequenceFunction<M> fieldValueGetter, UnaryOperator<CharSequenceConstraint> constraintOperator) {
        return this.constraint(fieldValueGetter, Reflects.getFieldComment(fieldValueGetter), constraintOperator);
    }

    public <V extends Enum<V>> ConstraintBuilder<M> constraint(EnumFunction<M, V> fieldValueGetter, UnaryOperator<EnumConstraint<V>> constraintOperator) {
        return this.constraint(fieldValueGetter, Reflects.getFieldComment(fieldValueGetter), constraintOperator);
    }

    public <V extends Iterable<E>, E> ConstraintBuilder<M> constraint(IterableFunction<M, V, E> fieldValueGetter, UnaryOperator<IterableConstraint<V, E>> constraintOperator) {
        return this.constraint(fieldValueGetter, Reflects.getFieldComment(fieldValueGetter), constraintOperator);
    }

    public <V extends Map<MapKey, MapValue>, MapKey, MapValue> ConstraintBuilder<M> constraint(MapFunction<M, V, MapKey, MapValue> fieldValueGetter, UnaryOperator<MapConstraint<V, MapKey, MapValue>> constraintOperator) {
        return this.constraint(fieldValueGetter, Reflects.getFieldComment(fieldValueGetter), constraintOperator);
    }

    public ConstraintBuilder<M> constraint(ByteFunction<M> fieldValueGetter, String fieldName, UnaryOperator<ByteConstraint> constraintOperator) {
        return this.constraint(fieldValueGetter, fieldName, constraintOperator, ByteConstraint::new);
    }

    public ConstraintBuilder<M> constraint(ShortFunction<M> fieldValueGetter, String fieldName, UnaryOperator<ShortConstraint> constraintOperator) {
        return this.constraint(fieldValueGetter, fieldName, constraintOperator, ShortConstraint::new);
    }

    public ConstraintBuilder<M> constraint(IntegerFunction<M> fieldValueGetter, String fieldName, UnaryOperator<IntegerConstraint> constraintOperator) {
        return this.constraint(fieldValueGetter, fieldName, constraintOperator, IntegerConstraint::new);
    }

    public ConstraintBuilder<M> constraint(LongFunction<M> fieldValueGetter, String fieldName, UnaryOperator<LongConstraint> constraintOperator) {
        return this.constraint(fieldValueGetter, fieldName, constraintOperator, LongConstraint::new);
    }

    public ConstraintBuilder<M> constraint(FloatFunction<M> fieldValueGetter, String fieldName, UnaryOperator<FloatConstraint> constraintOperator) {
        return this.constraint(fieldValueGetter, fieldName, constraintOperator, FloatConstraint::new);
    }

    public ConstraintBuilder<M> constraint(DoubleFunction<M> fieldValueGetter, String fieldName, UnaryOperator<DoubleConstraint> constraintOperator) {
        return this.constraint(fieldValueGetter, fieldName, constraintOperator, DoubleConstraint::new);
    }

    public ConstraintBuilder<M> constraint(BigDecimalFunction<M> fieldValueGetter, String fieldName, UnaryOperator<BigDecimalConstraint> constraintOperator) {
        return this.constraint(fieldValueGetter, fieldName, constraintOperator, BigDecimalConstraint::new);
    }

    public ConstraintBuilder<M> constraint(CharSequenceFunction<M> fieldValueGetter, String fieldName, UnaryOperator<CharSequenceConstraint> constraintOperator) {
        return this.constraint(fieldValueGetter, fieldName, constraintOperator, CharSequenceConstraint::new);
    }

    public <V extends Enum<V>> ConstraintBuilder<M> constraint(EnumFunction<M, V> fieldValueGetter, String fieldName, UnaryOperator<EnumConstraint<V>> constraintOperator) {
        return this.constraint(fieldValueGetter, fieldName, constraintOperator, EnumConstraint::new);
    }

    public <V extends Iterable<E>, E> ConstraintBuilder<M> constraint(IterableFunction<M, V, E> fieldValueGetter, String fieldName, UnaryOperator<IterableConstraint<V, E>> constraintOperator) {
        return this.constraint(fieldValueGetter, fieldName, constraintOperator, IterableConstraint::new);
    }

    public <V extends Map<MapKey, MapValue>, MapKey, MapValue> ConstraintBuilder<M> constraint(MapFunction<M, V, MapKey, MapValue> fieldValueGetter, String fieldName, UnaryOperator<MapConstraint<V, MapKey, MapValue>> constraintOperator) {
        return this.constraint(fieldValueGetter, fieldName, constraintOperator, MapConstraint::new);
    }

    public <V, C extends Constraint<V, C>> ConstraintBuilder<M> constraint(SerializableFunction<M, V> fieldValueGetter, String fieldName, UnaryOperator<C> constraintOperator, Supplier<C> constraintGetter) {
        this.conditionValidationSchemaMap
                .computeIfAbsent(defaultConstraintCondition, ignore -> Collections.newArrayList())
                .add(new ValidationSchema<>(fieldName, fieldValueGetter, constraintOperator.apply(constraintGetter.get()).validationRules));
        return this;
    }

    public ConstraintBuilder<M> constraintOnGroup(ValidationGroup validationGroup, Consumer<ConstraintBuilder<M>> validatorBuilderOperator) {
        return constraintOnConditionAdaptor(validatorBuilderOperator, (ignore, validationGroups) -> Collections.contains(validationGroups, validationGroup));
    }

    public ConstraintBuilder<M> constraintOnCondition(ConstraintCondition<M> constraintCondition, Consumer<ConstraintBuilder<M>> validatorBuilderOperator) {
        return constraintOnConditionAdaptor(validatorBuilderOperator, constraintCondition.toAdaptor());
    }

    private ConstraintBuilder<M> constraintOnConditionAdaptor(Consumer<ConstraintBuilder<M>> validatorBuilderOperator, ConstraintConditionAdaptor<M> constraintConditionAdaptor) {
        ConstraintBuilder<M> constraintBuilder = new ConstraintBuilder<>();
        validatorBuilderOperator.accept(constraintBuilder);
        this.conditionValidationSchemaMap
                .computeIfAbsent(constraintConditionAdaptor, ignore -> Collections.newArrayList())
                .addAll(constraintBuilder.conditionValidationSchemaMap.getOrDefault(defaultConstraintCondition, Collections.newArrayList()));
        return this;
    }

    public Validator<M> build() {
        return new Validator<>(this.conditionValidationSchemaMap);
    }

    public interface ByteFunction<M> extends SerializableFunction<M, Byte> {

    }

    public interface ShortFunction<M> extends SerializableFunction<M, Short> {

    }

    public interface IntegerFunction<M> extends SerializableFunction<M, Integer> {

    }

    public interface LongFunction<M> extends SerializableFunction<M, Long> {

    }

    public interface FloatFunction<M> extends SerializableFunction<M, Float> {

    }

    public interface DoubleFunction<M> extends SerializableFunction<M, Double> {

    }

    public interface BigDecimalFunction<M> extends SerializableFunction<M, BigDecimal> {

    }

    public interface CharSequenceFunction<M> extends SerializableFunction<M, CharSequence> {

    }

    public interface EnumFunction<M, V extends Enum<V>> extends SerializableFunction<M, V> {

    }

    public interface IterableFunction<M, V extends Iterable<E>, E> extends SerializableFunction<M, V> {

    }

    public interface MapFunction<M, V extends Map<MapKey, MapValue>, MapKey, MapValue> extends SerializableFunction<M, V> {

    }

}