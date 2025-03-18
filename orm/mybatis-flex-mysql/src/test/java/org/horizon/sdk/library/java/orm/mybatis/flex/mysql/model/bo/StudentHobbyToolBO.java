package org.horizon.sdk.library.java.orm.mybatis.flex.mysql.model.bo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.horizon.sdk.library.java.contract.model.base.BO;
import org.horizon.sdk.library.java.doc.knife4j.contract.constant.ApiDocConstant;

import java.io.Serial;

@Schema(description = "学生爱好工具信息")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
public class StudentHobbyToolBO implements BO {

    @Serial private static final long serialVersionUID = -311631525432516351L;

    @Schema(description = "名字", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.STRING)
    private String name;

}