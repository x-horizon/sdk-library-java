package cn.srd.library.java.orm.mybatis.flex.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

@Schema(description = "基础服务平台-用户中心-用户")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@Table(value = "uc_oe_user")
public class StudentPO extends BasePO implements Serializable {

    @Serial private static final long serialVersionUID = -7680901283684311918L;

    @Id(value = "student_id", keyType = KeyType.Generator)
    @Column("user_id")
    private Long id;

    @Schema(description = "头像图片id")
    @TableId(value = "avatar_file_id")
    private Long avatarFileId;

    @Schema(description = "用户名字")
    @TableField(value = "user_name")
    private String name;

    @Schema(description = "用户账号")
    @TableField(value = "account")
    private String account;

    @Schema(description = "用户编号")
    @TableField(value = "user_code")
    private String code;

    @Schema(description = "身份证号")
    @TableField(value = "id_card")
    private String idCard;

    @Schema(description = "性别")
    @TableField(value = "gender")
    private String gender;

    @Schema(description = "邮箱")
    @TableField(value = "email")
    private String email;

    @Schema(description = "手机号码")
    @TableField(value = "mobile")
    private String mobile;

    @Schema(description = "用户类型")
    @TableField(value = "user_type")
    private String type;

    @Schema(description = "用户状态")
    @TableField(value = "user_status")
    private String status;

}
