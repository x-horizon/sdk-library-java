package cn.library.java.orm.mybatis.flex.postgresql.service.impl;

import cn.library.java.orm.mybatis.flex.postgresql.dao.StudentTestIdSnowflakeDao;
import cn.library.java.orm.mybatis.flex.postgresql.model.po.StudentTestIdSnowflakePO;
import cn.library.java.orm.mybatis.flex.postgresql.service.StudentTestIdSnowflakeService;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class StudentTestIdSnowflakeServiceImpl extends ServiceImpl<StudentTestIdSnowflakeDao, StudentTestIdSnowflakePO> implements StudentTestIdSnowflakeService {

}
