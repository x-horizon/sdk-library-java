// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.spring.contract;

import cn.srd.library.java.tool.spring.contract.model.WithAutoConfigurationBean;
import cn.srd.library.java.tool.spring.contract.model.WithComponentBean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {WithComponentBean.class, WithAutoConfigurationBean.class}))
@SpringBootApplication
public class ToolSpringBaseApplication {

}