package org.horizon.sdk.library.java.tool.lang.validation.constraint;

import org.horizon.sdk.library.java.tool.lang.object.Nil;
import org.horizon.sdk.library.java.tool.lang.text.Patterns;
import org.horizon.sdk.library.java.tool.lang.validation.support.ValidationRule;
import org.horizon.sdk.library.java.tool.lang.validation.violation.ViolationMessageType;

/**
 * constraint implementation for {@link CharSequence} validation with string-specific rules.
 *
 * <p>extends {@link ContainerConstraint} to provide comprehensive string validation capabilities including
 * format checks, length constraints, and regional pattern validations. supports validation for various
 * common string patterns like emails, IP addresses, IDs, and region-specific formats.</p>
 *
 * <p>usage example:
 * <pre>{@code
 * CharSequenceConstraint constraint = new CharSequenceConstraint()
 *     .mustNotBlank()
 *     .mustValidEmail()
 *     .mustSizeLessThanOrEquals(100);
 * }</pre></p>
 *
 * @author wjm
 * @see Patterns
 * @since 2025-04-18 17:01
 */
public class CharSequenceConstraint extends ContainerConstraint<CharSequence, Integer, CharSequenceConstraint> {

    /**
     * requires the string to contain only whitespace (blank).
     *
     * <p>uses {@link Nil#isBlank} for validation check and generates {@link ViolationMessageType#BLANK} message when violated.</p>
     *
     * @return current constraint instance
     * @see Nil#isBlank
     */
    public CharSequenceConstraint mustBlank() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.BLANK, Nil::isBlank));
        return this;
    }

    /**
     * requires the string to contain non-whitespace content.
     *
     * <p>uses {@link Nil#isNotBlank} for validation check and generates {@link ViolationMessageType#NOT_BLANK} message when violated.</p>
     *
     * @return current constraint instance
     * @see Nil#isNotBlank
     */
    public CharSequenceConstraint mustNotBlank() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.NOT_BLANK, Nil::isNotBlank));
        return this;
    }

    /**
     * validates the string as a UUID format.
     *
     * <p>uses {@link Patterns#isUuid} for validation check and generates {@link ViolationMessageType#STRING_INVALID_UUID} message when violated.</p>
     *
     * @return current constraint instance
     * @see Patterns#isUuid
     */
    public CharSequenceConstraint mustValidUuid() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.STRING_INVALID_UUID, Patterns::isUuid));
        return this;
    }

    /**
     * validates the string as hexadecimal format.
     *
     * <p>uses {@link Patterns#isHex} for validation check and generates {@link ViolationMessageType#STRING_INVALID_HEX} message when violated.</p>
     *
     * @return current constraint instance
     * @see Patterns#isHex
     */
    public CharSequenceConstraint mustValidHex() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.STRING_INVALID_HEX, Patterns::isHex));
        return this;
    }

    /**
     * validates the string as URL format.
     *
     * <p>uses {@link Patterns#isUrl} for validation check and generates {@link ViolationMessageType#STRING_INVALID_URL} message when violated.</p>
     *
     * @return current constraint instance
     * @see Patterns#isUrl
     */
    public CharSequenceConstraint mustValidUrl() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.STRING_INVALID_URL, Patterns::isUrl));
        return this;
    }

    /**
     * validates the string as IPv4 address format.
     *
     * <p>uses {@link Patterns#isIpv4} for validation check and generates {@link ViolationMessageType#STRING_INVALID_IPV4} message when violated.</p>
     *
     * @return current constraint instance
     * @see Patterns#isIpv4
     */
    public CharSequenceConstraint mustValidIpv4() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.STRING_INVALID_IPV4, Patterns::isIpv4));
        return this;
    }

    /**
     * validates the string as IPv6 address format.
     *
     * <p>uses {@link Patterns#isIpv6} for validation check and generates {@link ViolationMessageType#STRING_INVALID_IPV6} message when violated.</p>
     *
     * @return current constraint instance
     * @see Patterns#isIpv6
     */
    public CharSequenceConstraint mustValidIpv6() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.STRING_INVALID_IPV6, Patterns::isIpv6));
        return this;
    }

    /**
     * validates the string as MAC address format.
     *
     * <p>uses {@link Patterns#isMacAddress} for validation check and generates {@link ViolationMessageType#STRING_INVALID_MAC_ADDRESS} message when violated.</p>
     *
     * @return current constraint instance
     * @see Patterns#isMacAddress
     */
    public CharSequenceConstraint mustValidMacAddress() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.STRING_INVALID_MAC_ADDRESS, Patterns::isMacAddress));
        return this;
    }

    /**
     * validates the string as email address format.
     *
     * <p>uses {@link Patterns#isEmail} for validation check and generates {@link ViolationMessageType#STRING_INVALID_EMAIL} message when violated.</p>
     *
     * @return current constraint instance
     * @see Patterns#isEmail
     */
    public CharSequenceConstraint mustValidEmail() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.STRING_INVALID_EMAIL, Patterns::isEmail));
        return this;
    }

    /**
     * validates the string as identity card number format.
     *
     * <p>uses {@link Patterns#isIdentityCard} for validation check and generates {@link ViolationMessageType#STRING_INVALID_IDENTITY_CARD} message when violated.</p>
     *
     * @return current constraint instance
     * @see Patterns#isIdentityCard
     */
    public CharSequenceConstraint mustValidIdentityCard() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.STRING_INVALID_IDENTITY_CARD, Patterns::isIdentityCard));
        return this;
    }

    /**
     * validates the string as Chinese unified social credit code format.
     *
     * <p>uses {@link Patterns#isUnifySocialCreditCode} for validation check and generates {@link ViolationMessageType#STRING_INVALID_UNIFY_SOCIAL_CREDIT_CODE} message when violated.</p>
     *
     * @return current constraint instance
     * @see Patterns#isUnifySocialCreditCode
     */
    public CharSequenceConstraint mustValidUnifySocialCreditCode() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.STRING_INVALID_UNIFY_SOCIAL_CREDIT_CODE, Patterns::isUnifySocialCreditCode));
        return this;
    }

    /**
     * validates the string as postal code format.
     *
     * <p>uses {@link Patterns#isPostcode} for validation check and generates {@link ViolationMessageType#STRING_INVALID_POSTCODE} message when violated.</p>
     *
     * @return current constraint instance
     * @see Patterns#isPostcode
     */
    public CharSequenceConstraint mustValidPostcode() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.STRING_INVALID_POSTCODE, Patterns::isPostcode));
        return this;
    }

    /**
     * validates the string as birthday date format (yyyy-MM-dd).
     *
     * <p>uses {@link Patterns#isBirthday} for validation check and generates {@link ViolationMessageType#STRING_INVALID_BIRTHDAY} message when violated.</p>
     *
     * @return current constraint instance
     * @see Patterns#isBirthday
     */
    public CharSequenceConstraint mustValidBirthday() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.STRING_INVALID_BIRTHDAY, Patterns::isBirthday));
        return this;
    }

    /**
     * validates the string as Chinese mainland mobile number format.
     *
     * <p>uses {@link Patterns#isMobileChina} for validation check and generates {@link ViolationMessageType#STRING_INVALID_MOBILE_CHINA} message when violated.</p>
     *
     * @return current constraint instance
     * @see Patterns#isMobileChina
     */
    public CharSequenceConstraint mustValidMobileChina() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.STRING_INVALID_MOBILE_CHINA, Patterns::isMobileChina));
        return this;
    }

    /**
     * validates the string as Hong Kong mobile number format.
     *
     * <p>uses {@link Patterns#isMobileChinaHongKong} for validation check and generates {@link ViolationMessageType#STRING_INVALID_MOBILE_CHINA_HONG_KONG} message when violated.</p>
     *
     * @return current constraint instance
     * @see Patterns#isMobileChinaHongKong
     */
    public CharSequenceConstraint mustValidMobileChinaHongKong() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.STRING_INVALID_MOBILE_CHINA_HONG_KONG, Patterns::isMobileChinaHongKong));
        return this;
    }

    /**
     * validates the string as Macao mobile number format.
     *
     * <p>uses {@link Patterns#isMobileChinaMacao} for validation check and generates {@link ViolationMessageType#STRING_INVALID_MOBILE_CHINA_MACAO} message when violated.</p>
     *
     * @return current constraint instance
     * @see Patterns#isMobileChinaMacao
     */
    public CharSequenceConstraint mustValidMobileChinaMacao() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.STRING_INVALID_MOBILE_CHINA_MACAO, Patterns::isMobileChinaMacao));
        return this;
    }

    /**
     * validates the string as Taiwan mobile number format.
     *
     * <p>uses {@link Patterns#isMobileChinaTaiwan} for validation check and generates {@link ViolationMessageType#STRING_INVALID_MOBILE_CHINA_TAIWAN} message when violated.</p>
     *
     * @return current constraint instance
     * @see Patterns#isMobileChinaTaiwan
     */
    public CharSequenceConstraint mustValidMobileChinaTaiwan() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.STRING_INVALID_MOBILE_CHINA_TAIWAN, Patterns::isMobileChinaTaiwan));
        return this;
    }

    /**
     * validates the string as general phone number format.
     *
     * <p>uses {@link Patterns#isPhone} for validation check and generates {@link ViolationMessageType#STRING_INVALID_PHONE} message when violated.</p>
     *
     * @return current constraint instance
     * @see Patterns#isPhone
     */
    public CharSequenceConstraint mustValidPhone() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.STRING_INVALID_PHONE, Patterns::isPhone));
        return this;
    }

    /**
     * validates the string as Chinese vehicle plate number format.
     *
     * <p>uses {@link Patterns#isPlateNumberChina} for validation check and generates {@link ViolationMessageType#STRING_INVALID_PLATE_NUMBER_CHINA} message when violated.</p>
     *
     * @return current constraint instance
     * @see Patterns#isPlateNumberChina
     */
    public CharSequenceConstraint mustValidPlateNumberChina() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.STRING_INVALID_PLATE_NUMBER_CHINA, Patterns::isPlateNumberChina));
        return this;
    }

    /**
     * validates the string as vehicle identification number (VIN) format.
     *
     * <p>uses {@link Patterns#isCarVin} for validation check and generates {@link ViolationMessageType#STRING_INVALID_CAR_VIN} message when violated.</p>
     *
     * @return current constraint instance
     * @see Patterns#isCarVin
     */
    public CharSequenceConstraint mustValidCarVin() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.STRING_INVALID_CAR_VIN, Patterns::isCarVin));
        return this;
    }

    /**
     * validates the string as driver's license number format.
     *
     * <p>uses {@link Patterns#isDriverLicence} for validation check and generates {@link ViolationMessageType#STRING_INVALID_DRIVER_LICENCE} message when violated.</p>
     *
     * @return current constraint instance
     * @see Patterns#isDriverLicence
     */
    public CharSequenceConstraint mustValidDriverLicence() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.STRING_INVALID_DRIVER_LICENCE, Patterns::isDriverLicence));
        return this;
    }

    /**
     * validates the string as currency amount format.
     *
     * <p>uses {@link Patterns#isMoney} for validation check and generates {@link ViolationMessageType#STRING_INVALID_MONEY} message when violated.</p>
     *
     * @return current constraint instance
     * @see Patterns#isMoney
     */
    public CharSequenceConstraint mustValidMoney() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.STRING_INVALID_MONEY, Patterns::isMoney));
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * <p>implementation returns the length of the {@link CharSequence} for size comparison, returns 0 for null values using {@link Nil#zeroValueIfNull}.</p>
     *
     * @param fieldValue the input char sequence
     * @return length of the char sequence (0 for null)
     */
    @Override
    protected Integer getComparedFieldValue(CharSequence fieldValue) {
        return Nil.zeroValueIfNull(fieldValue).length();
    }

    /**
     * {@inheritDoc}
     *
     * @return {@link ViolationMessageType#CHAR_SEQUENCE_LENGTH_EQUALS} for exact length matches
     */
    @Override
    protected ViolationMessageType getMustSizeEqualsViolationMessageType() {
        return ViolationMessageType.CHAR_SEQUENCE_LENGTH_EQUALS;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@link ViolationMessageType#CHAR_SEQUENCE_LENGTH_NOT_EQUALS} for length inequality
     */
    @Override
    protected ViolationMessageType getMustSizeNotEqualsViolationMessageType() {
        return ViolationMessageType.CHAR_SEQUENCE_LENGTH_NOT_EQUALS;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@link ViolationMessageType#CHAR_SEQUENCE_LENGTH_GREATER_THAN} for length comparisons
     */
    @Override
    protected ViolationMessageType getMustGreaterThanViolationMessageType() {
        return ViolationMessageType.CHAR_SEQUENCE_LENGTH_GREATER_THAN;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@link ViolationMessageType#CHAR_SEQUENCE_LENGTH_GREATER_THAN_OR_EQUALS} for length comparisons
     */
    @Override
    protected ViolationMessageType getMustGreaterThanOrEqualsViolationMessageType() {
        return ViolationMessageType.CHAR_SEQUENCE_LENGTH_GREATER_THAN_OR_EQUALS;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@link ViolationMessageType#CHAR_SEQUENCE_LENGTH_LESS_THAN} for length comparisons
     */
    @Override
    protected ViolationMessageType getMustLessThanViolationMessageType() {
        return ViolationMessageType.CHAR_SEQUENCE_LENGTH_LESS_THAN;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@link ViolationMessageType#CHAR_SEQUENCE_LENGTH_LESS_THAN_OR_EQUALS} for length comparisons
     */
    @Override
    protected ViolationMessageType getMustLessThanOrEqualsViolationMessageType() {
        return ViolationMessageType.CHAR_SEQUENCE_LENGTH_LESS_THAN_OR_EQUALS;
    }

    /**
     * {@inheritDoc}
     *
     * <p>returns {@code this} instance to support method chaining</p>
     */
    @Override
    protected CharSequenceConstraint toThis() {
        return this;
    }

}