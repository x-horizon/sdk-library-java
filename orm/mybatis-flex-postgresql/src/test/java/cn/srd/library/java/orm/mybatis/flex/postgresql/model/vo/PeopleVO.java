// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.postgresql.model.vo;

import cn.srd.library.java.orm.contract.model.base.VO;
import cn.srd.library.java.orm.mybatis.flex.postgresql.model.bo.PeopleBO;
import cn.srd.library.java.orm.mybatis.flex.postgresql.model.po.PeoplePO;
import cn.srd.library.java.tool.convert.all.Converts;
import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

/**
 * @author wjm
 * @since 2024-04-15 18:44
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@AutoMappers({@AutoMapper(target = PeopleBO.class), @AutoMapper(target = PeoplePO.class)})
public class PeopleVO extends PeopleBO implements VO {

    @Serial private static final long serialVersionUID = -2621402998719572653L;

    public PeopleVO setAllName(String name) {
        super.setAllName(name);
        return this;
    }

    @Override
    public PeoplePO toPO() {
        return Converts.withMapstruct().toBean(this, PeoplePO.class);
    }

}