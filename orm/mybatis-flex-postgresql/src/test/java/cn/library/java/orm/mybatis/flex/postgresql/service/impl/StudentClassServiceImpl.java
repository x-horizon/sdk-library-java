package cn.library.java.orm.mybatis.flex.postgresql.service.impl;

import cn.library.java.orm.mybatis.flex.postgresql.dao.StudentClassDao;
import cn.library.java.orm.mybatis.flex.postgresql.model.po.StudentClassPO;
import cn.library.java.orm.mybatis.flex.postgresql.service.StudentClassService;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class StudentClassServiceImpl extends ServiceImpl<StudentClassDao, StudentClassPO> implements StudentClassService {

}
