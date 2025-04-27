package org.horizon.sdk.library.java.tool.lang.text;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.dromara.hutool.core.data.PhoneUtil;
import org.dromara.hutool.core.lang.Validator;
import org.dromara.hutool.core.regex.ReUtil;
import org.horizon.sdk.library.java.tool.lang.object.Nil;

/**
 * @author wjm
 * @since 2025-04-21 18:57
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Patterns {

    private static final String EMAIL_QQ_REGEX = "^[a-zA-Z0-9_.%+-]+@(qq|foxmail)\\.com$";

    public static boolean isUuid(CharSequence value) {
        return Validator.isUUID(value);
    }

    public static boolean isHex(CharSequence value) {
        return Validator.isHex(value);
    }

    public static boolean isUrl(CharSequence value) {
        return Validator.isUrl(value);
    }

    public static boolean isIpv4(CharSequence value) {
        return Validator.isIpv4(value);
    }

    public static boolean isIpv6(CharSequence value) {
        return Validator.isIpv6(value);
    }

    public static boolean isMacAddress(CharSequence value) {
        return Validator.isMac(value);
    }

    public static boolean isEmail(CharSequence value) {
        return Validator.isEmail(value);
    }

    public static boolean isEmailQQ(CharSequence value) {
        return ReUtil.isMatch(EMAIL_QQ_REGEX, value);
    }

    public static boolean isIdentityCard(CharSequence value) {
        return Validator.isCitizenId(value);
    }

    public static boolean isUnifySocialCreditCode(CharSequence value) {
        return Validator.isCreditCode(value);
    }

    public static boolean isPostcode(CharSequence value) {
        return Validator.isZipCode(value);
    }

    public static boolean isBirthday(CharSequence value) {
        if (Nil.isNull(value)) {
            return false;
        }
        return Validator.isBirthday(value);
    }

    public static boolean isMobileChina(CharSequence value) {
        return PhoneUtil.isMobile(value);
    }

    public static boolean isMobileChinaHongKong(CharSequence value) {
        return PhoneUtil.isMobileHk(value);
    }

    public static boolean isMobileChinaMacao(CharSequence value) {
        return PhoneUtil.isMobileMo(value);
    }

    public static boolean isMobileChinaTaiwan(CharSequence value) {
        return PhoneUtil.isMobileTw(value);
    }

    public static boolean isPhone(CharSequence value) {
        return PhoneUtil.isPhone(value);
    }

    public static boolean isPlateNumberChina(CharSequence value) {
        return Validator.isPlateNumber(value);
    }

    public static boolean isCarVin(CharSequence value) {
        return Validator.isCarVin(value);
    }

    public static boolean isDriverLicence(CharSequence value) {
        return Validator.isCarDrivingLicence(value);
    }

    public static boolean isMoney(CharSequence value) {
        return Validator.isMoney(value);
    }

}