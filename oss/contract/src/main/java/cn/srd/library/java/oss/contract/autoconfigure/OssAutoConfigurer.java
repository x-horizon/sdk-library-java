// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.oss.contract.autoconfigure;

import cn.srd.library.java.tool.enums.autoconfigure.EnableEnumAutowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import static cn.srd.library.java.oss.contract.autoconfigure.OssBasePackagePathAutoConfigurer.BASE_CONTRACT_PACKAGE_PATH;
import static cn.srd.library.java.oss.contract.autoconfigure.OssBasePackagePathAutoConfigurer.BASE_PACKAGE_PATH;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Java Oss
 *
 * @author wjm
 * @since 2024-06-19 23:51
 */
@AutoConfiguration
@EnableEnumAutowired(scanPackagePaths = {BASE_PACKAGE_PATH, BASE_CONTRACT_PACKAGE_PATH})
public class OssAutoConfigurer {

}