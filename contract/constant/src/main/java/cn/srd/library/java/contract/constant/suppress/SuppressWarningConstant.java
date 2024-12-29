package cn.srd.library.java.contract.constant.suppress;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * {@link SuppressWarnings} value constant
 *
 * @author wjm
 * @since 2023-09-21 19:35
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SuppressWarningConstant {

    /**
     * ignore all warning: {@code "all"}
     */
    public static final String ALL = "all";

    /**
     * ignore using {@link Deprecated} warning: {@code "deprecation"}
     */
    public static final String DEPRECATED = "deprecation";

    /**
     * ignore duplicated code: {@code "DuplicatedCode"}
     */
    public static final String DUPLICATED_CODE = "DuplicatedCode";

    /**
     * ignore unchecked warning: {@code "unchecked"}
     */
    public static final String UNCHECKED = "unchecked";

    /**
     * ignore null pointer exception warning: {@code "null"}
     */
    public static final String NULL = "null";

    /**
     * ignore without generic warning: {@code "rawtypes"}
     */
    public static final String RAW_TYPE = "rawtypes";

    /**
     * ignore without serial version UID warning: {@code "serial"}
     */
    public static final String SERIAL = "serial";

    /**
     * ignore unused warning: {@code "unused"}
     */
    public static final String UNUSED = "unused";

    /**
     * ignore varargs warning: {@code "varargs"}, the same as {@link SafeVarargs}
     */
    public static final String VARARGS = "varargs";

    /**
     * ignore removal warning: {@code "removal"}
     */
    public static final String REMOVAL = "removal";

    /**
     * ignore preview warning: {@code "preview"}
     */
    public static final String PREVIEW = "preview";

    /**
     * ignore spring ioc component weak known: {@code "SpringJavaInjectionPointsAutowiringInspection"}
     */
    public static final String SPRING_JAVA_INJECTION_POINTS_AUTOWIRING_INSPECTION = "SpringJavaInjectionPointsAutowiringInspection";

    // @SuppressWarnings({"MagicConstant", "ConstantConditions"})
    // @SuppressWarnings("NullableProblems")
    // @SuppressWarnings({"PointlessBitwiseExpression", "FieldCanBeLocal"})
    // @SuppressWarnings("UnnecessaryLocalVariable")
    // @SuppressWarnings({"unchecked", "SuspiciousSystemArraycopy"})
    // @SuppressWarnings("UnnecessaryUnicodeEscape")
    // @SuppressWarnings("SameParameterValue")
    // @SuppressWarnings("serial")
    // @SuppressWarnings({"deprecation", "unchecked"})
    // @SuppressWarnings("null")

}