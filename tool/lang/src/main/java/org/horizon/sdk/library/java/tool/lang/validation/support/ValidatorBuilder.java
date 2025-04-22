package org.horizon.sdk.library.java.tool.lang.validation.support;

import org.horizon.sdk.library.java.contract.model.base.POJO;
import org.horizon.sdk.library.java.tool.lang.collection.Collections;
import org.horizon.sdk.library.java.tool.lang.functional.SerializableFunction;
import org.horizon.sdk.library.java.tool.lang.reflect.Reflects;
import org.horizon.sdk.library.java.tool.lang.validation.constraint.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * builder class for constructing validation constraints with fluent api.
 *
 * <p>this class provides type-safe constraint definitions for different data types, supporting conditional validation and group-based validation rules.</p>
 *
 * <p>typical usage:
 * <pre>{@code
 * // define VALIDATOR in UserVO.
 * public static final Validator<UserVO> VALIDATOR = Validators.<UserVO>builder()
 *             .constraintOnGroup(ValidationGroup.CREATE, builder -> builder.constraint(UserVO::getId, Constraint::mustNull).build())
 *             .constraintOnGroup(ValidationGroup.UPDATE, builder -> builder.constraint(UserVO::getId, Constraint::mustNotNull).build())
 *             .constraint(UserVO::getName, constraint -> constraint.mustNotBlank().mustLessThanOrEquals(64))
 *             .constraint(UserVO::getGenderType, Constraint::mustNotNull)
 *             .constraint(UserVO::getIdentityCard, constraint -> constraint.skipNull().mustValidIdentityCard())
 *             .constraint(UserVO::getEmail, constraint -> constraint.skipNull().mustValidEmail())
 *             .constraint(UserVO::getPhone, constraint -> constraint.skipNull().mustValidPhone())
 *             .build();
 *
 * // usage:
 * UserVO.VALIDATOR.validate(userVO, ValidationGroup.CREATE).throwsIfViolated();
 * UserVO.VALIDATOR.validate(userVO, ValidationGroup.UPDATE).throwsIfViolated();
 * }</pre>
 *
 * @author wjm
 * @see Validator
 * @see ValidationGroup
 * @see Constraint
 * @since 2025-04-18 15:37
 */
public class ValidatorBuilder<M> {

    /**
     * map storing validation schemas organized by condition adaptors.
     * <ul>
     *   <li>key: condition when the validation rules should apply</li>
     *   <li>value: list of validation schemas for the condition</li>
     * </ul>
     */
    final Map<ConstraintConditionAdaptor<M>, List<ValidationSchema<M, ?>>> conditionValidationSchemaMap = Collections.newLinkedHashMap();

    /**
     * default condition that always applies (no specific condition)
     */
    private final ConstraintConditionAdaptor<M> defaultConstraintCondition = (ignore1, ignore2) -> true;

    private static final String VALIDATION_RULE_FIELD_NAME = "validationRules";

    /**
     * adds byte type validation rules using reflection-based field name resolution.
     *
     * <p>example:
     * <pre>{@code
     * .constraint(UserVO::getAge, constraint -> constraint.mustGreaterThan((byte)0)
     * }</pre>
     *
     * @param fieldValueGetter   function to access the field value
     * @param constraintOperator constraint configuration lambda
     * @return current builder instance for chaining
     * @see ByteConstraint
     */
    public ValidatorBuilder<M> constraint(ByteFunction<M> fieldValueGetter, UnaryOperator<ByteConstraint> constraintOperator) {
        return this.constraint(fieldValueGetter, Reflects.getFieldComment(fieldValueGetter), constraintOperator);
    }

    /**
     * adds short type validation rules using reflection-based field name resolution.
     *
     * <p>example:
     * <pre>{@code
     * .constraint(UserVO::getAge, constraint -> constraint.mustGreaterThan((short)0)
     * }</pre>
     *
     * @param fieldValueGetter   function to access the field value
     * @param constraintOperator constraint configuration lambda
     * @return current builder instance for chaining
     * @see ShortConstraint
     */
    public ValidatorBuilder<M> constraint(ShortFunction<M> fieldValueGetter, UnaryOperator<ShortConstraint> constraintOperator) {
        return this.constraint(fieldValueGetter, Reflects.getFieldComment(fieldValueGetter), constraintOperator);
    }

    /**
     * adds integer type validation rules using reflection-based field name resolution.
     *
     * <p>example:
     * <pre>{@code
     * .constraint(UserVO::getAge, constraint -> constraint.mustGreaterThan(0)
     * }</pre>
     *
     * @param fieldValueGetter   function to access the field value
     * @param constraintOperator constraint configuration lambda
     * @return current builder instance for chaining
     * @see IntegerConstraint
     */
    public ValidatorBuilder<M> constraint(IntegerFunction<M> fieldValueGetter, UnaryOperator<IntegerConstraint> constraintOperator) {
        return this.constraint(fieldValueGetter, Reflects.getFieldComment(fieldValueGetter), constraintOperator);
    }

    /**
     * adds long type validation rules using reflection-based field name resolution.
     *
     * <p>example:
     * <pre>{@code
     * .constraint(UserVO::getAge, constraint -> constraint.mustGreaterThan(0L)
     * }</pre>
     *
     * @param fieldValueGetter   function to access the field value
     * @param constraintOperator constraint configuration lambda
     * @return current builder instance for chaining
     * @see LongConstraint
     */
    public ValidatorBuilder<M> constraint(LongFunction<M> fieldValueGetter, UnaryOperator<LongConstraint> constraintOperator) {
        return this.constraint(fieldValueGetter, Reflects.getFieldComment(fieldValueGetter), constraintOperator);
    }

    /**
     * adds float type validation rules using reflection-based field name resolution.
     *
     * <p>example:
     * <pre>{@code
     * .constraint(UserVO::getAge, constraint -> constraint.mustGreaterThan(0.0f)
     * }</pre>
     *
     * @param fieldValueGetter   function to access the field value
     * @param constraintOperator constraint configuration lambda
     * @return current builder instance for chaining
     * @see FloatConstraint
     */
    public ValidatorBuilder<M> constraint(FloatFunction<M> fieldValueGetter, UnaryOperator<FloatConstraint> constraintOperator) {
        return this.constraint(fieldValueGetter, Reflects.getFieldComment(fieldValueGetter), constraintOperator);
    }

    /**
     * adds double type validation rules using reflection-based field name resolution.
     *
     * <p>example:
     * <pre>{@code
     * .constraint(UserVO::getAge, constraint -> constraint.mustGreaterThan(0.00d)
     * }</pre>
     *
     * @param fieldValueGetter   function to access the field value
     * @param constraintOperator constraint configuration lambda
     * @return current builder instance for chaining
     * @see DoubleConstraint
     */
    public ValidatorBuilder<M> constraint(DoubleFunction<M> fieldValueGetter, UnaryOperator<DoubleConstraint> constraintOperator) {
        return this.constraint(fieldValueGetter, Reflects.getFieldComment(fieldValueGetter), constraintOperator);
    }

    /**
     * adds big decimal type validation rules using reflection-based field name resolution.
     *
     * <p>example:
     * <pre>{@code
     * .constraint(UserVO::getAge, constraint -> constraint.mustGreaterThan(new BigDecimal("0.00"))
     * }</pre>
     *
     * @param fieldValueGetter   function to access the field value
     * @param constraintOperator constraint configuration lambda
     * @return current builder instance for chaining
     * @see BigDecimalConstraint
     */
    public ValidatorBuilder<M> constraint(BigDecimalFunction<M> fieldValueGetter, UnaryOperator<BigDecimalConstraint> constraintOperator) {
        return this.constraint(fieldValueGetter, Reflects.getFieldComment(fieldValueGetter), constraintOperator);
    }

    /**
     * adds char sequence type validation rules using reflection-based field name resolution.
     *
     * <p>example:
     * <pre>{@code
     * .constraint(UserVO::getName, constraint -> constraint.mustNotBlank())
     * }</pre>
     *
     * @param fieldValueGetter   function to access the field value
     * @param constraintOperator constraint configuration lambda
     * @return current builder instance for chaining
     * @see CharSequenceConstraint
     */
    public ValidatorBuilder<M> constraint(CharSequenceFunction<M> fieldValueGetter, UnaryOperator<CharSequenceConstraint> constraintOperator) {
        return this.constraint(fieldValueGetter, Reflects.getFieldComment(fieldValueGetter), constraintOperator);
    }

    /**
     * adds enum type validation rules using reflection-based field name resolution.
     *
     * <p>example:
     * <pre>{@code
     * .constraint(UserVO::getStatus, constraint -> constraint.mustNotNull())
     * }</pre>
     *
     * @param fieldValueGetter   function to access the field value
     * @param constraintOperator constraint configuration lambda
     * @return current builder instance for chaining
     * @see EnumConstraint
     */
    public <V extends Enum<V>> ValidatorBuilder<M> constraint(EnumFunction<M, V> fieldValueGetter, UnaryOperator<EnumConstraint<V>> constraintOperator) {
        return this.constraint(fieldValueGetter, Reflects.getFieldComment(fieldValueGetter), constraintOperator);
    }

    /**
     * adds iterable type validation rules using reflection-based field name resolution.
     *
     * <p>example:
     * <pre>{@code
     * .constraint(UserVO::getListFields, constraint -> constraint.mustNotEmpty())
     * }</pre>
     *
     * @param fieldValueGetter   function to access the field value
     * @param constraintOperator constraint configuration lambda
     * @return current builder instance for chaining
     * @see IterableConstraint
     */
    public <V extends Iterable<E>, E> ValidatorBuilder<M> constraint(IterableFunction<M, V, E> fieldValueGetter, UnaryOperator<IterableConstraint<V, E>> constraintOperator) {
        return this.constraint(fieldValueGetter, Reflects.getFieldComment(fieldValueGetter), constraintOperator);
    }

    /**
     * adds map type validation rules using reflection-based field name resolution.
     *
     * <p>example:
     * <pre>{@code
     * .constraint(UserVO::getMapField, constraint -> constraint.mustNotEmpty())
     * }</pre>
     *
     * @param fieldValueGetter   function to access the field value
     * @param constraintOperator constraint configuration lambda
     * @return current builder instance for chaining
     * @see MapConstraint
     */
    public <V extends Map<MapKey, MapValue>, MapKey, MapValue> ValidatorBuilder<M> constraint(MapFunction<M, V, MapKey, MapValue> fieldValueGetter, UnaryOperator<MapConstraint<V, MapKey, MapValue>> constraintOperator) {
        return this.constraint(fieldValueGetter, Reflects.getFieldComment(fieldValueGetter), constraintOperator);
    }

    /**
     * adds pojo type validation rules using reflection-based field name resolution.
     *
     * <p>example:
     * <pre>{@code
     * .constraint(UserVO::getAccountVO, "accountInfo", constraint -> constraint.mustNotNull())
     * }</pre>
     *
     * @param fieldValueGetter   function to access the field value
     * @param constraintOperator constraint configuration lambda
     * @return current builder instance for chaining
     * @see PojoConstraint
     */
    public <V extends POJO> ValidatorBuilder<M> constraint(PojoFunction<M, V> fieldValueGetter, UnaryOperator<PojoConstraint<V>> constraintOperator) {
        return this.constraint(fieldValueGetter, Reflects.getFieldComment(fieldValueGetter), constraintOperator);
    }

    /**
     * adds byte type validation rules with explicit field name specification.
     *
     * <p>example:
     * <pre>{@code
     * .constraint(UserVO::getAge, "age", constraint -> constraint.mustGreaterThan((byte)0)
     * }</pre>
     *
     * @param fieldValueGetter   function to access the field value
     * @param fieldName          display name for a validation message
     * @param constraintOperator constraint configuration lambda
     * @return current builder instance for chaining
     * @see ByteConstraint
     */
    public ValidatorBuilder<M> constraint(ByteFunction<M> fieldValueGetter, String fieldName, UnaryOperator<ByteConstraint> constraintOperator) {
        return this.constraint(fieldValueGetter, fieldName, constraintOperator, ByteConstraint::new);
    }

    /**
     * adds short type validation rules with explicit field name specification.
     *
     * <p>example:
     * <pre>{@code
     * .constraint(UserVO::getAge, "age", constraint -> constraint.mustGreaterThan((short)0)
     * }</pre>
     *
     * @param fieldValueGetter   function to access the field value
     * @param fieldName          display name for a validation message
     * @param constraintOperator constraint configuration lambda
     * @return current builder instance for chaining
     * @see ShortConstraint
     */
    public ValidatorBuilder<M> constraint(ShortFunction<M> fieldValueGetter, String fieldName, UnaryOperator<ShortConstraint> constraintOperator) {
        return this.constraint(fieldValueGetter, fieldName, constraintOperator, ShortConstraint::new);
    }

    /**
     * adds integer type validation rules with explicit field name specification.
     *
     * <p>example:
     * <pre>{@code
     * .constraint(UserVO::getAge, "age", constraint -> constraint.mustGreaterThan(0)
     * }</pre>
     *
     * @param fieldValueGetter   function to access the field value
     * @param fieldName          display name for a validation message
     * @param constraintOperator constraint configuration lambda
     * @return current builder instance for chaining
     * @see IntegerConstraint
     */
    public ValidatorBuilder<M> constraint(IntegerFunction<M> fieldValueGetter, String fieldName, UnaryOperator<IntegerConstraint> constraintOperator) {
        return this.constraint(fieldValueGetter, fieldName, constraintOperator, IntegerConstraint::new);
    }

    /**
     * adds long type validation rules with explicit field name specification.
     *
     * <p>example:
     * <pre>{@code
     * .constraint(UserVO::getAge, "age", constraint -> constraint.mustGreaterThan(0L)
     * }</pre>
     *
     * @param fieldValueGetter   function to access the field value
     * @param fieldName          display name for a validation message
     * @param constraintOperator constraint configuration lambda
     * @return current builder instance for chaining
     * @see LongConstraint
     */
    public ValidatorBuilder<M> constraint(LongFunction<M> fieldValueGetter, String fieldName, UnaryOperator<LongConstraint> constraintOperator) {
        return this.constraint(fieldValueGetter, fieldName, constraintOperator, LongConstraint::new);
    }

    /**
     * adds float type validation rules with explicit field name specification.
     *
     * <p>example:
     * <pre>{@code
     * .constraint(UserVO::getAge, "age", constraint -> constraint.mustGreaterThan(0.0f)
     * }</pre>
     *
     * @param fieldValueGetter   function to access the field value
     * @param fieldName          display name for a validation message
     * @param constraintOperator constraint configuration lambda
     * @return current builder instance for chaining
     * @see FloatConstraint
     */
    public ValidatorBuilder<M> constraint(FloatFunction<M> fieldValueGetter, String fieldName, UnaryOperator<FloatConstraint> constraintOperator) {
        return this.constraint(fieldValueGetter, fieldName, constraintOperator, FloatConstraint::new);
    }

    /**
     * adds double type validation rules with explicit field name specification.
     *
     * <p>example:
     * <pre>{@code
     * .constraint(UserVO::getAge, "age", constraint -> constraint.mustGreaterThan(0.00d)
     * }</pre>
     *
     * @param fieldValueGetter   function to access the field value
     * @param fieldName          display name for a validation message
     * @param constraintOperator constraint configuration lambda
     * @return current builder instance for chaining
     * @see DoubleConstraint
     */
    public ValidatorBuilder<M> constraint(DoubleFunction<M> fieldValueGetter, String fieldName, UnaryOperator<DoubleConstraint> constraintOperator) {
        return this.constraint(fieldValueGetter, fieldName, constraintOperator, DoubleConstraint::new);
    }

    /**
     * adds big decimal type validation rules with explicit field name specification.
     *
     * <p>example:
     * <pre>{@code
     * .constraint(UserVO::getAge, "age", constraint -> constraint.mustGreaterThan(new BigDecimal("0.00"))
     * }</pre>
     *
     * @param fieldValueGetter   function to access the field value
     * @param fieldName          display name for a validation message
     * @param constraintOperator constraint configuration lambda
     * @return current builder instance for chaining
     * @see BigDecimalConstraint
     */
    public ValidatorBuilder<M> constraint(BigDecimalFunction<M> fieldValueGetter, String fieldName, UnaryOperator<BigDecimalConstraint> constraintOperator) {
        return this.constraint(fieldValueGetter, fieldName, constraintOperator, BigDecimalConstraint::new);
    }

    /**
     * adds char sequence type validation rules with explicit field name specification.
     *
     * <p>example:
     * <pre>{@code
     * .constraint(UserVO::getName, "name", constraint -> constraint.mustNotBlank())
     * }</pre>
     *
     * @param fieldValueGetter   function to access the field value
     * @param fieldName          display name for a validation message
     * @param constraintOperator constraint configuration lambda
     * @return current builder instance for chaining
     * @see CharSequenceConstraint
     */
    public ValidatorBuilder<M> constraint(CharSequenceFunction<M> fieldValueGetter, String fieldName, UnaryOperator<CharSequenceConstraint> constraintOperator) {
        return this.constraint(fieldValueGetter, fieldName, constraintOperator, CharSequenceConstraint::new);
    }

    /**
     * adds enum type validation rules with explicit field name specification.
     *
     * <p>example:
     * <pre>{@code
     * .constraint(UserVO::getStatus, "status", constraint -> constraint.mustNotNull())
     * }</pre>
     *
     * @param fieldValueGetter   function to access the field value
     * @param fieldName          display name for a validation message
     * @param constraintOperator constraint configuration lambda
     * @return current builder instance for chaining
     * @see EnumConstraint
     */
    public <V extends Enum<V>> ValidatorBuilder<M> constraint(EnumFunction<M, V> fieldValueGetter, String fieldName, UnaryOperator<EnumConstraint<V>> constraintOperator) {
        return this.constraint(fieldValueGetter, fieldName, constraintOperator, EnumConstraint::new);
    }

    /**
     * adds iterable type validation rules with explicit field name specification.
     *
     * <p>example:
     * <pre>{@code
     * .constraint(UserVO::getListFields, "listFields", constraint -> constraint.mustNotEmpty())
     * }</pre>
     *
     * @param fieldValueGetter   function to access the field value
     * @param fieldName          display name for a validation message
     * @param constraintOperator constraint configuration lambda
     * @return current builder instance for chaining
     * @see IterableConstraint
     */
    public <V extends Iterable<E>, E> ValidatorBuilder<M> constraint(IterableFunction<M, V, E> fieldValueGetter, String fieldName, UnaryOperator<IterableConstraint<V, E>> constraintOperator) {
        return this.constraint(fieldValueGetter, fieldName, constraintOperator, IterableConstraint::new);
    }

    /**
     * adds map type validation rules with explicit field name specification.
     *
     * <p>example:
     * <pre>{@code
     * .constraint(UserVO::getMapField, "mapField", constraint -> constraint.mustNotEmpty())
     * }</pre>
     *
     * @param fieldValueGetter   function to access the field value
     * @param fieldName          display name for a validation message
     * @param constraintOperator constraint configuration lambda
     * @return current builder instance for chaining
     * @see MapConstraint
     */
    public <V extends Map<MapKey, MapValue>, MapKey, MapValue> ValidatorBuilder<M> constraint(MapFunction<M, V, MapKey, MapValue> fieldValueGetter, String fieldName, UnaryOperator<MapConstraint<V, MapKey, MapValue>> constraintOperator) {
        return this.constraint(fieldValueGetter, fieldName, constraintOperator, MapConstraint::new);
    }

    /**
     * adds pojo type validation rules with explicit field name specification.
     *
     * <p>example:
     * <pre>{@code
     * .constraint(UserVO::getAccountVO, "accountInfo", constraint -> constraint.mustNotNull())
     * }</pre>
     *
     * @param fieldValueGetter   function to access the field value
     * @param fieldName          display name for a validation message
     * @param constraintOperator constraint configuration lambda
     * @return current builder instance for chaining
     * @see PojoConstraint
     */
    public <V extends POJO> ValidatorBuilder<M> constraint(PojoFunction<M, V> fieldValueGetter, String fieldName, UnaryOperator<PojoConstraint<V>> constraintOperator) {
        return this.constraint(fieldValueGetter, fieldName, constraintOperator, PojoConstraint::new);
    }

    /**
     * adding validation constraints with custom configuration.
     *
     * <p>handle the following:
     * <ul>
     *   <li>field value access through getter function</li>
     *   <li>constraint configuration via operator</li>
     *   <li>storage in validation schema map</li>
     * </ul>
     *
     * @param fieldValueGetter   function to access the field value
     * @param fieldName          display name for a validation message
     * @param constraintOperator constraint configuration lambda
     * @param constraintGetter   supplier for constraint instance
     * @return current builder instance
     */
    private <V, C extends Constraint<V, C>> ValidatorBuilder<M> constraint(SerializableFunction<M, V> fieldValueGetter, String fieldName, UnaryOperator<C> constraintOperator, Supplier<C> constraintGetter) {
        this.conditionValidationSchemaMap
                .computeIfAbsent(defaultConstraintCondition, ignore -> Collections.newArrayList())
                .add(new ValidationSchema<>(fieldName, fieldValueGetter, Reflects.getFieldValue(constraintOperator.apply(constraintGetter.get()), VALIDATION_RULE_FIELD_NAME)));
        return this;
    }

    /**
     * adds group-specific validation constraints.
     *
     * <p>example:
     * <pre>{@code
     * .constraintOnGroup(ValidationGroup.CREATE, builder -> builder.constraint(UserVO::getId, Constraint::mustNull).build())
     * .constraintOnGroup(ValidationGroup.UPDATE, builder -> builder.constraint(UserVO::getId, Constraint::mustNotNull).build())
     * }</pre>
     *
     * @param validationGroup          target validation group
     * @param validatorBuilderOperator constraint configuration for the group
     * @return current builder instance
     * @see ValidationGroup
     */
    public ValidatorBuilder<M> constraintOnGroup(ValidationGroup validationGroup, Consumer<ValidatorBuilder<M>> validatorBuilderOperator) {
        return constraintOnConditionAdaptor(validatorBuilderOperator, (ignore, validationGroups) -> Collections.contains(validationGroups, validationGroup));
    }

    /**
     * adds condition validation constraints.
     *
     * <p>example:
     * <pre>{@code
     * // the constraint on id will only be activated if the name is not null.
     * .constraintOnCondition(userVO -> Nil.isNull(userVO.getName()), builder -> builder.constraint(UserVO::getId, Constraint::mustNotNull))
     * }</pre>
     *
     * @param constraintCondition      target validation condition
     * @param validatorBuilderOperator constraint configuration for the condition
     * @return current builder instance
     */
    public ValidatorBuilder<M> constraintOnCondition(ConstraintCondition<M> constraintCondition, Consumer<ValidatorBuilder<M>> validatorBuilderOperator) {
        return constraintOnConditionAdaptor(validatorBuilderOperator, constraintCondition.toAdaptor());
    }

    /**
     * adapt constraint condition
     *
     * @param validatorBuilderOperator   constraint configuration for the condition
     * @param constraintConditionAdaptor condition adaptor
     * @return current builder instance
     * @see #constraintOnGroup(ValidationGroup, Consumer)
     * @see #constraintOnCondition(ConstraintCondition, Consumer)
     */
    private ValidatorBuilder<M> constraintOnConditionAdaptor(Consumer<ValidatorBuilder<M>> validatorBuilderOperator, ConstraintConditionAdaptor<M> constraintConditionAdaptor) {
        ValidatorBuilder<M> validatorBuilder = new ValidatorBuilder<>();
        validatorBuilderOperator.accept(validatorBuilder);
        this.conditionValidationSchemaMap
                .computeIfAbsent(constraintConditionAdaptor, ignore -> Collections.newArrayList())
                .addAll(validatorBuilder.conditionValidationSchemaMap.getOrDefault(defaultConstraintCondition, Collections.newArrayList()));
        return this;
    }

    /**
     * finalizes the builder and creates the validator instance.
     *
     * <p>example:
     * <pre>{@code
     * Validator<UserVO> validator = Validators.<UserVO>builder()
     *     .constraint(...)
     *     .build();
     * }</pre>
     *
     * @return configured validator instance
     * @see Validator
     */
    public Validator<M> build() {
        return new Validator<>(this);
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

    public interface PojoFunction<M, V extends POJO> extends SerializableFunction<M, V> {

    }

}