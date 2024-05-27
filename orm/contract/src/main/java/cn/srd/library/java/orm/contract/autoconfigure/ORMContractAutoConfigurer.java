// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.contract.autoconfigure;

import cn.srd.library.java.tool.enums.autowired.EnableEnumAutowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import static cn.srd.library.java.orm.contract.autoconfigure.ORMContractBasePackagePathAutoConfigurer.BASE_PACKAGE_PATH;

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