package cn.library.java.orm.mybatis.flex.postgresql.service.impl;

import cn.library.java.orm.mybatis.flex.postgresql.dao.StudentDao;
import cn.library.java.orm.mybatis.flex.postgresql.model.po.StudentPO;
import cn.library.java.orm.mybatis.flex.postgresql.service.StudentService;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentDao, StudentPO> implements StudentService {

}
