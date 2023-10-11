// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.enums;

import cn.srd.library.java.tool.enums.autowired.EnableEnumAutowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @EnableEnumAutowired
@EnableEnumAutowired(scanPackagePaths = {"cn.srd.*.java"})
@SpringBootApplication
public class ToolEnumsApplication {

}
