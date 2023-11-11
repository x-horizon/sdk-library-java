package cn.library.java.orm.mybatis.flex.postgresql.service.impl;

import cn.library.java.orm.mybatis.flex.postgresql.dao.StudentTestIdSQLDao;
import cn.library.java.orm.mybatis.flex.postgresql.model.po.StudentTestIdSQLPO;
import cn.library.java.orm.mybatis.flex.postgresql.service.StudentTestIdSQLService;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class StudentTestIdSQLServiceImpl extends ServiceImpl<StudentTestIdSQLDao, StudentTestIdSQLPO> implements StudentTestIdSQLService {

}
