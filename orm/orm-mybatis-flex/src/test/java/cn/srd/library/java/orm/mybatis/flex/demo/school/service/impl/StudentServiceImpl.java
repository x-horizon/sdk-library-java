package cn.srd.library.java.orm.mybatis.flex.demo.school.service.impl;

import cn.srd.library.java.orm.mybatis.flex.demo.school.dao.StudentDao;
import cn.srd.library.java.orm.mybatis.flex.demo.school.model.po.StudentPO;
import cn.srd.library.java.orm.mybatis.flex.demo.school.service.StudentService;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentDao, StudentPO> implements StudentService {

}
