// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.studio.low.code.repository;

import cn.srd.library.java.orm.mybatis.flex.postgresql.repository.GenericRepository;
import cn.srd.library.java.studio.low.code.model.po.StudentPO;
import org.springframework.stereotype.Repository;

/**
 * 学生信息 repository
 *
 * @author TODO 请填写作者名字
 * @since 2024-04-15 23:57
 */
@Repository
public interface StudentRepository extends GenericRepository<StudentPO> {

}