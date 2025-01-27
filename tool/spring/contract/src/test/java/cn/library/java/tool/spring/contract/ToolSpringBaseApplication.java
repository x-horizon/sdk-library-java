package cn.library.java.tool.spring.contract;

import cn.library.java.tool.spring.contract.model.WithAutoConfigurationBean;
import cn.library.java.tool.spring.contract.model.WithComponentBean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {WithComponentBean.class, WithAutoConfigurationBean.class}))
@SpringBootApplication
public class ToolSpringBaseApplication {

}