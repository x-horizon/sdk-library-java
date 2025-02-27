package org.horizon.sdk.library.java.orm.mybatis.flex.tdengine.model.bo;

import org.horizon.sdk.library.java.doc.knife4j.contract.constant.ApiDocConstant;
import org.horizon.sdk.library.java.orm.contract.mybatis.flex.model.bo.BaseWithVersionBO;
import org.horizon.sdk.library.java.orm.mybatis.flex.tdengine.model.po.PeoplePO;
import org.horizon.sdk.library.java.orm.mybatis.flex.tdengine.model.vo.PeopleVO;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;
import io.swagger.v3.oas.annotations.media.Schema;
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
@AutoMappers({@AutoMapper(target = PeoplePO.class), @AutoMapper(target = PeopleVO.class)})
public class PeopleBO extends BaseWithVersionBO {

    @Serial private static final long serialVersionUID = -2406926081842847637L;

    @Schema(description = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.NUMBER)
    @Column(value = "id")
    @Id
    private Long id;

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