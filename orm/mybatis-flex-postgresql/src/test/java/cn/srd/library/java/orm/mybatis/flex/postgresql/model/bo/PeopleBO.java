package cn.srd.library.java.orm.mybatis.flex.postgresql.model.bo;

import cn.srd.library.java.orm.contract.mybatis.flex.model.bo.BaseVersionBO;
import com.mybatisflex.annotation.Column;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

/**
 * @author wjm
 * @since 2023-11-04 00:19
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class PeopleBO extends BaseVersionBO {

    @Serial private static final long serialVersionUID = -2406926081842847637L;

    @Column(value = "home_id")
    private Long homeId;

    @Column(value = "name1")
    private String name1;

    @Column(value = "name2")
    private String name2;

    @Column(value = "name3")
    private String name3;

    @Column(value = "name4")
    private String name4;

    @Column(value = "name5")
    private String name5;

    @Column(value = "name6")
    private String name6;

    @Column(value = "name7")
    private String name7;

    public PeopleBO setAllName(String name) {
        this.setName1(name)
                .setName2(name)
                .setName3(name)
                .setName4(name)
                .setName5(name)
                .setName6(name)
                .setName7(name);
        return this;
    }

}