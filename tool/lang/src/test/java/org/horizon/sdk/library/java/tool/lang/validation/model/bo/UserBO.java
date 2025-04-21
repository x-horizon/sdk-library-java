package org.horizon.sdk.library.java.tool.lang.validation.model.bo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.horizon.sdk.library.java.contract.model.base.BO;

import java.io.Serial;
import java.util.List;
import java.util.Map;

@Schema(name = "用户信息")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
public class UserBO implements BO {

    @Serial private static final long serialVersionUID = 664477221454981L;

    @Schema(description = "用户id")
    private Long id;

    @Schema(description = "头像id")
    private Long avatarFileId;

    @Schema(description = "部门id")
    private List<Long> departmentIds;

    @Schema(description = "岗位id")
    private List<Long> postIds;

    @Schema(description = "角色id")
    private List<Long> roleIds;

    @Schema(description = "用户名字")
    private String name;

    @Schema(description = "身份证")
    private String identityCard;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "手机号码")
    private String phone;

    @Schema(description = "映射关系")
    private Map<String, Object> map;

    @Schema(description = "是否启用")
    private Boolean enabledIs;

}