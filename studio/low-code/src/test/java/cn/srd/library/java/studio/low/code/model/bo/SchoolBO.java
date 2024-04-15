// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.studio.low.code.model.bo;

import cn.srd.library.java.orm.contract.mybatis.flex.model.bo.BaseVersionBO;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

/**
 * 学校信息
 *
 * @author TODO 请填写作者名字
 * @since 2024-04-15 23:57
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class SchoolBO extends BaseVersionBO {

    @Serial private static final long serialVersionUID = -9052089371242697920L;

    @Id
    @Column(value = "id")
    private Long id;

    @Column(value = "name")
    private String name;

    @Column(value = "address")
    private String address;

}