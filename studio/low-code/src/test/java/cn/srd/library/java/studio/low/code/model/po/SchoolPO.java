// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.studio.low.code.model.po;

import cn.srd.library.java.contract.model.base.PO;
import cn.srd.library.java.studio.low.code.model.bo.SchoolBO;
import cn.srd.library.java.studio.low.code.model.vo.SchoolVO;
import cn.srd.library.java.tool.convert.api.Converts;
import com.mybatisflex.annotation.Table;
import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

/**
 * 学校信息 model
 *
 * @author TODO 请填写作者名字
 * @since 2024-04-15 23:57
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@AutoMappers({@AutoMapper(target = SchoolBO.class), @AutoMapper(target = SchoolVO.class)})
@Table(value = "school")
public class SchoolPO extends SchoolBO implements PO {

    @Serial private static final long serialVersionUID = 1172231848506334518L;

    @Override
    public SchoolVO toVO() {
        return Converts.onMapstruct().toBean(this, SchoolVO.class);
    }

}