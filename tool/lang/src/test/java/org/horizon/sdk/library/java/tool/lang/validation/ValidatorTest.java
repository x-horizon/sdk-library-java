package org.horizon.sdk.library.java.tool.lang.validation;

import org.horizon.sdk.library.java.tool.lang.object.Nil;
import org.horizon.sdk.library.java.tool.lang.validation.constraint.Constraint;
import org.horizon.sdk.library.java.tool.lang.validation.model.vo.UserVO;
import org.horizon.sdk.library.java.tool.lang.validation.support.ValidationGroup;
import org.horizon.sdk.library.java.tool.lang.validation.support.Validator;

import java.util.List;
import java.util.Map;

/**
 * @author wjm
 * @since 2025-04-18 23:50
 */
public class ValidatorTest {

    public static final Validator<UserVO> VALIDATOR = Validators.<UserVO>builder()
            .constraintOnGroup(ValidationGroup.CREATE, builder -> builder.constraint(UserVO::getId, Constraint::mustNull))
            .constraintOnGroup(ValidationGroup.UPDATE, builder -> builder.constraint(UserVO::getId, Constraint::mustNotNull))
            .constraintOnCondition(userVO -> Nil.isNull(userVO.getName()), builder -> builder.constraint(UserVO::getId, Constraint::mustNotNull))
            .constraintOnCondition(userVO -> Nil.isNull(userVO.getAvatarFileId()), builder -> builder.constraint(UserVO::getId, Constraint::mustNotNull))
            .constraintOnCondition(userVO -> Nil.isNull(userVO.getAvatarFileId()), builder -> builder.constraint(UserVO::getId, Constraint::mustNotNull))
            .constraintOnCondition(userVO -> Nil.isNull(userVO.getAvatarFileId()), builder -> builder.constraint(UserVO::getId, Constraint::mustNotNull))
            .constraintOnCondition(userVO -> Nil.isNull(userVO.getAvatarFileId()), builder -> builder.constraint(UserVO::getId, Constraint::mustNotNull))
            .constraint(UserVO::getName, constraint -> constraint
                    .skipBlank()
                    .mustLessThanOrEquals(64)
                    .mustValidUuid()
                    .mustValidHex()
                    .mustValidUrl()
                    .mustValidIpv4()
                    .mustValidIpv6()
                    .mustValidMacAddress()
                    .mustValidEmail()
                    .mustValidIdentityCard()
                    .mustValidUnifySocialCreditCode()
                    .mustValidPostcode()
                    .mustValidBirthday()
                    .mustValidMobileChina()
                    .mustValidMobileChinaHongKong()
                    .mustValidMobileChinaMacao()
                    .mustValidMobileChinaTaiwan()
                    .mustValidPhone()
                    .mustValidPlateNumberChina()
                    .mustValidCarVin()
                    .mustValidDriverLicence()
                    .mustValidMoney()
            )
            .constraint(UserVO::getId, constraint -> constraint.mustNotNull().mustEquals(64L))
            .constraint(UserVO::getPostIds, constraint -> constraint.mustEquals(List.of(1L)))
            .constraint(UserVO::getGenderType, constraint -> constraint.mustNotNull())
            .constraint(UserVO::getMap, constraint -> constraint
                    .mustEquals(Map.of("1L", 1L))
                    .mustGreaterThan(6)
                    .mustSizeEquals(2)
                    .mustSizeNotEquals(1)
                    .mustLessThan(1)
                    .mustGreaterThanOrEquals(2)
                    .mustLessThanOrEquals(0)
                    .mustEmpty()
                    .mustSizeEquals(null)
            )
            .build();

    public static void main(String[] args) {
        UserVO userVO = new UserVO();
        // userVO.setId(0L);
        userVO.setName(" ");
        // userVO.setIdentityCard("test");
        // userVO.setName("<UNK>");
        userVO.setMap(Map.of("1L", 2L));
        // VALIDATOR.validate(userVO, ValidationGroup.CREATE).throwsIfViolated();
        VALIDATOR.validate(userVO, ValidationGroup.UPDATE).throwsIfViolated();
    }

}