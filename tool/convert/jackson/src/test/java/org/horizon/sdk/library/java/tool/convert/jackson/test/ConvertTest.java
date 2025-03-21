package org.horizon.sdk.library.java.tool.convert.jackson.test;

import org.horizon.sdk.library.java.tool.convert.jackson.JacksonConverts;
import org.horizon.sdk.library.java.tool.convert.jackson.model.FoolVO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ConvertTest {

    @Test
    void test() {
        FoolVO foolVO = FoolVO.builder().build();
        String foolVOString = JacksonConverts.getInstance().toString(foolVO);
        FoolVO foolVO2 = JacksonConverts.getInstance().toBean(foolVOString, FoolVO.class);
        System.out.println(foolVOString);
    }

}