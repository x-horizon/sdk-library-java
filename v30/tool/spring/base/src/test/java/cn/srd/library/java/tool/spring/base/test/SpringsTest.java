// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.spring.base.test;

import cn.srd.library.java.tool.spring.base.Springs;
import cn.srd.library.java.tool.spring.base.model.WithAutoConfigurationBean;
import cn.srd.library.java.tool.spring.base.model.WithComponentBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * test for {@link Springs}
 *
 * @author wjm
 * @since 2023-10-03 18:46
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringsTest {

    @Test
    public void testRegisterBean() {
        Springs.registerBean(WithComponentBean.class);
        Springs.registerBean(WithAutoConfigurationBean.class);
        Springs.getBean(WithComponentBean.class);
        Springs.getBean(WithAutoConfigurationBean.class);
    }

}
