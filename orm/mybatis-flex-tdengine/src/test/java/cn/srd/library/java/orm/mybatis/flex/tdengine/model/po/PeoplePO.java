// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.tdengine.model.po;

import cn.srd.library.java.contract.model.base.PO;
import cn.srd.library.java.orm.mybatis.flex.tdengine.model.bo.PeopleBO;
import cn.srd.library.java.orm.mybatis.flex.tdengine.model.vo.PeopleVO;
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
 * @author wjm
 * @since 2024-04-15 18:44
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@Table(value = "people")
@AutoMappers({@AutoMapper(target = PeopleBO.class), @AutoMapper(target = PeopleVO.class)})
public class PeoplePO extends PeopleBO implements PO {

    @Serial private static final long serialVersionUID = 4292133966454196212L;

    @Override
    public PeoplePO setAllName(String name) {
        super.setAllName(name);
        return this;
    }

    @Override
    public PeopleVO toVO() {
        return Converts.onMapstruct().toBean(this, PeopleVO.class);
    }

}