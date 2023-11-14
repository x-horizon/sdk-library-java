// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.property;

import com.mybatisflex.spring.boot.MybatisFlexProperties;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * the global property config for {@link MybatisFlexProperties}
 *
 * @author wjm
 * @since 2023-11-14 16:49
 */
@Target({})
@Retention(RetentionPolicy.RUNTIME)
public @interface PropertyConfig {

    /**
     * the class paths where mybatis mapper xml files stored, like: "classpath*:cn/test/dao/impl/*.xml".
     * <ol>
     *   <li>
     *       if not specified this field value or set the value of key "mybatis-flex.mapper-locations" in spring configuration file,<br/>
     *       the default mybatis mapper xml files stored class paths will be set to {@link MybatisFlexProperties#getMapperLocations()}.
     *   </li>
     *   <li>
     *       if both this field value and the value of key "mybatis-flex.mapper-locations" in spring configuration file are specified,<br/>
     *       the value of key "mybatis-flex.mapper-locations" in spring configuration file will be selected.
     *       for example:
     *       <ul>
     *         <li>in spring configuration file application.yaml:
     * <pre>
     * {@code
     *   mybatis-flex:
     *     mapper-locations:
     *       - classpath*:cn/test1/dao/impl/*.xml
     * }
     * </pre>
     *         </li>
     *         <li>specified this field value:
     * <pre>
     * {@code
     *  @PropertyConfig(xmlMapperClassPaths = {"classpath*:cn/test2/dao/impl/*.xml"})
     * }
     * </pre>
     *         </li>
     *       </ul>
     *       finally the class path "classpath*:cn/test1/dao/impl/*.xml" in spring configuration file will be selected.
     *   </li>
     *   <li>
     *       if just specified this field value, then using this field value as the class paths where mybatis mapper xml files stored.
     *       for example:
     *       <ul>
     *         <li>specified this field value:
     * <pre>
     * {@code
     *  @PropertyConfig(xmlMapperClassPaths = {"classpath*:cn/test/dao/impl/*.xml"})
     * }
     * </pre>
     *         </li>
     *       </ul>
     *       finally the class path "classpath*:cn/test/dao/impl/*.xml" will be selected.
     *   </li>
     * </ol>
     *
     * @return the class paths where mybatis mapper xml files stored
     */
    String[] xmlMapperClassPaths() default {};

    /**
     * the ant style package paths to register an alias instead of using the fully class name in mybatis mapper xml file.
     * <ol>
     *   <li>
     *       if both this field value and the value of key "mybatis-flex.type-aliases-package" in spring configuration file are specified,<br/>
     *       the value of key "mybatis-flex.type-aliases-package" in spring configuration file will be selected.
     *       for example:
     *       <ul>
     *         <li>in spring configuration file application.yaml:
     * <pre>
     * {@code
     *   mybatis-flex:
     *     type-aliases-package: cn.test.model.student1.po
     * }
     * </pre>
     *         </li>
     *         <li>specified this field value:
     * <pre>
     * {@code
     *  @PropertyConfig(xmlMapperEntityPackageAliasPackagePaths = {"cn.test.model.student2.po"})
     * }
     * </pre>
     *         </li>
     *       </ul>
     *       finally the package path "cn.test.model.student1.po" in spring configuration file will be selected.
     *   </li>
     *   <li>
     *       if just specified this field value, then using this field value as the package paths to register an alias instead of using the fully class name in mybatis mapper xml file.
     *       for example:
     *       <ul>
     *         <li>specified this field value:
     *             <pre>
     * {@code
     *  @PropertyConfig(xmlMapperEntityPackageAliasPackagePaths = {"cn.test.model.student.po"})
     * }
     *             </pre>
     *         </li>
     *       </ul>
     *       finally the package path "cn.test.model.student.po" will be selected.
     *   </li>
     *   <li>
     *       the package paths specified in spring configuration file does not support ant style:
     *       <ul>
     *         <li>this is not supported: "cn.test.model.*.po"</li>
     *         <li>multiple package paths can be specified like this: "cn.test.model.student1.po,cn.test.model.student2.po,cn.test.model.student3.po"</li>
     *       </ul>
     *   </li>
     *   <li>
     *       the package paths specified in this field supports ant style, you can set it like this:
     *       <ul>
     *         <li>{@code @PropertyConfig(xmlMapperEntityPackageAliasPackagePaths = {"cn.test.model.*.po"})}</li>
     *         <li>{@code @PropertyConfig(xmlMapperEntityPackageAliasPackagePaths = {"cn.test.model.**.po"})}</li>
     *         <li>{@code @PropertyConfig(xmlMapperEntityPackageAliasPackagePaths = {"cn.test.model.student.po"})}</li>
     *       </ul>
     *   </li>
     *   <li>
     *       if both this field value and the value of key "mybatis-flex.type-aliases-package" in spring configuration file are not specified,
     *       you need to provide a full class name in mybatis xml file like this:<br/>
     *       {@code <resultMap id="studentResultMap" type="cn.test.model.student.po.StudentPO">}
     *       <br/><br/>
     *       you can provide a simple class name in mybatis xml file after specify this field value or the value of key "mybatis-flex.type-aliases-package" in spring configuration file:<br/>
     *       {@code <resultMap id="studentResultMap" type="StudentPO">}
     *   </li>
     * </ol>
     *
     * @return the ant style package paths to register an alias instead of using the fully class name in mybatis mapper xml file
     */
    String[] xmlMapperEntityPackageAliasPackagePaths() default {};

    String[] mapperScanBasePackagePaths() default {};

}
