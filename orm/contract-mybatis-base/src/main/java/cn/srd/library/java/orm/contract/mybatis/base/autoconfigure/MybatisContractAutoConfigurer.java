// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.contract.mybatis.base.autoconfigure;

import cn.srd.library.java.tool.enums.autowired.EnableEnumAutowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Orm Mybatis Contract
 *
 * @author wjm
 * @since 2023-11-08 17:07
 */
@AutoConfiguration
@EnableEnumAutowired(scanPackagePaths = "cn.srd.library.java.orm.mybatis")
public class MybatisContractAutoConfigurer {

}
