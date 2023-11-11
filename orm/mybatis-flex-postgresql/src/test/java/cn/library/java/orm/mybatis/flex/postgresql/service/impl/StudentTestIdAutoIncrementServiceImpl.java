package cn.library.java.orm.mybatis.flex.postgresql.service.impl;

import cn.library.java.orm.mybatis.flex.postgresql.dao.StudentTestIdAutoIncrementDao;
import cn.library.java.orm.mybatis.flex.postgresql.model.po.StudentTestIdAutoIncrementPO;
import cn.library.java.orm.mybatis.flex.postgresql.service.StudentTestIdAutoIncrementService;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class StudentTestIdAutoIncrementServiceImpl extends ServiceImpl<StudentTestIdAutoIncrementDao, StudentTestIdAutoIncrementPO> implements StudentTestIdAutoIncrementService {

}
