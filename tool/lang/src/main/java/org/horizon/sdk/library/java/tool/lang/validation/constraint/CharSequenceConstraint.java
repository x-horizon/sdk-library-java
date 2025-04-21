package org.horizon.sdk.library.java.tool.lang.validation.constraint;

import org.horizon.sdk.library.java.tool.lang.object.Nil;
import org.horizon.sdk.library.java.tool.lang.text.Patterns;
import org.horizon.sdk.library.java.tool.lang.validation.support.ValidationRule;
import org.horizon.sdk.library.java.tool.lang.validation.violation.ViolationMessageType;

/**
 * @author wjm
 * @since 2025-04-18 17:01
 */
public class CharSequenceConstraint extends ContainerConstraint<CharSequence, Integer, CharSequenceConstraint> {

    public CharSequenceConstraint mustBlank() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.BLANK, Nil::isBlank));
        return this;
    }

    public CharSequenceConstraint mustNotBlank() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.NOT_BLANK, Nil::isNotBlank));
        return this;
    }

    public CharSequenceConstraint mustValidUuid() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.STRING_INVALID_UUID, Patterns::isUuid));
        return this;
    }

    public CharSequenceConstraint mustValidHex() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.STRING_INVALID_HEX, Patterns::isHex));
        return this;
    }

    public CharSequenceConstraint mustValidUrl() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.STRING_INVALID_URL, Patterns::isUrl));
        return this;
    }

    public CharSequenceConstraint mustValidIpv4() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.STRING_INVALID_IPV4, Patterns::isIpv4));
        return this;
    }

    public CharSequenceConstraint mustValidIpv6() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.STRING_INVALID_IPV6, Patterns::isIpv6));
        return this;
    }

    public CharSequenceConstraint mustValidMacAddress() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.STRING_INVALID_MAC_ADDRESS, Patterns::isMacAddress));
        return this;
    }

    public CharSequenceConstraint mustValidEmail() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.STRING_INVALID_EMAIL, Patterns::isEmail));
        return this;
    }

    public CharSequenceConstraint mustValidIdentityCard() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.STRING_INVALID_IDENTITY_CARD, Patterns::isIdentityCard));
        return this;
    }

    public CharSequenceConstraint mustValidUnifySocialCreditCode() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.STRING_INVALID_UNIFY_SOCIAL_CREDIT_CODE, Patterns::isUnifySocialCreditCode));
        return this;
    }

    public CharSequenceConstraint mustValidPostcode() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.STRING_INVALID_POSTCODE, Patterns::isPostcode));
        return this;
    }

    public CharSequenceConstraint mustValidBirthday() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.STRING_INVALID_BIRTHDAY, Patterns::isBirthday));
        return this;
    }

    public CharSequenceConstraint mustValidMobileChina() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.STRING_INVALID_MOBILE_CHINA, Patterns::isMobileChina));
        return this;
    }

    public CharSequenceConstraint mustValidMobileChinaHongKong() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.STRING_INVALID_MOBILE_CHINA_HONG_KONG, Patterns::isMobileChinaHongKong));
        return this;
    }

    public CharSequenceConstraint mustValidMobileChinaMacao() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.STRING_INVALID_MOBILE_CHINA_MACAO, Patterns::isMobileChinaMacao));
        return this;
    }

    public CharSequenceConstraint mustValidMobileChinaTaiwan() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.STRING_INVALID_MOBILE_CHINA_TAIWAN, Patterns::isMobileChinaTaiwan));
        return this;
    }

    public CharSequenceConstraint mustValidPhone() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.STRING_INVALID_PHONE, Patterns::isPhone));
        return this;
    }

    public CharSequenceConstraint mustValidPlateNumberChina() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.STRING_INVALID_PLATE_NUMBER_CHINA, Patterns::isPlateNumberChina));
        return this;
    }

    public CharSequenceConstraint mustValidCarVin() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.STRING_INVALID_CAR_VIN, Patterns::isCarVin));
        return this;
    }

    public CharSequenceConstraint mustValidDriverLicence() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.STRING_INVALID_DRIVER_LICENCE, Patterns::isDriverLicence));
        return this;
    }

    public CharSequenceConstraint mustValidMoney() {
        this.validationRules.add(new ValidationRule<>(null, ViolationMessageType.STRING_INVALID_MONEY, Patterns::isMoney));
        return this;
    }

    @Override
    protected Integer getComparedFieldValue(CharSequence fieldValue) {
        return Nil.zeroValueIfNull(fieldValue).length();
    }

    @Override
    protected ViolationMessageType getMustSizeEqualsViolationMessageType() {
        return ViolationMessageType.CHAR_SEQUENCE_LENGTH_EQUALS;
    }

    @Override
    protected ViolationMessageType getMustSizeNotEqualsViolationMessageType() {
        return ViolationMessageType.CHAR_SEQUENCE_LENGTH_NOT_EQUALS;
    }

    @Override
    protected ViolationMessageType getMustGreaterThanViolationMessageType() {
        return ViolationMessageType.CHAR_SEQUENCE_LENGTH_GREATER_THAN;
    }

    @Override
    protected ViolationMessageType getMustGreaterThanOrEqualsViolationMessageType() {
        return ViolationMessageType.CHAR_SEQUENCE_LENGTH_GREATER_THAN_OR_EQUALS;
    }

    @Override
    protected ViolationMessageType getMustLessThanViolationMessageType() {
        return ViolationMessageType.CHAR_SEQUENCE_LENGTH_LESS_THAN;
    }

    @Override
    protected ViolationMessageType getMustLessThanOrEqualsViolationMessageType() {
        return ViolationMessageType.CHAR_SEQUENCE_LENGTH_LESS_THAN_OR_EQUALS;
    }

    @Override
    protected CharSequenceConstraint toThis() {
        return this;
    }

}