package org.horizon.sdk.library.java.orm.contract.autoconfigure;

import org.horizon.sdk.library.java.tool.enums.autoconfigure.EnableEnumAutowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import static org.horizon.sdk.library.java.orm.contract.autoconfigure.ORMContractBasePackagePathAutoConfigurer.BASE_PACKAGE_PATH;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Java ORM Mybatis Contract
 *
 * @author wjm
 * @since 2023-11-08 17:07
 */
@AutoConfiguration
@EnableEnumAutowired(scanPackagePaths = BASE_PACKAGE_PATH)
public class ORMContractAutoConfigurer {

}