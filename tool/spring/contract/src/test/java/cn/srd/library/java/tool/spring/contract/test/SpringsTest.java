package cn.srd.library.java.tool.spring.contract.test;

import cn.srd.library.java.tool.spring.contract.model.WithAutoConfigurationBean;
import cn.srd.library.java.tool.spring.contract.model.WithComponentBean;
import cn.srd.library.java.tool.spring.contract.support.Springs;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * test for {@link Springs}
 *
 * @author wjm
 * @since 2023-10-03 18:46
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class SpringsTest {

    @Test
    void testRegisterBean() {
        Springs.registerBean(WithComponentBean.class);
        Springs.registerBean(WithAutoConfigurationBean.class);
        Springs.getBean(WithComponentBean.class);
        Springs.getBean(WithAutoConfigurationBean.class);
    }

}