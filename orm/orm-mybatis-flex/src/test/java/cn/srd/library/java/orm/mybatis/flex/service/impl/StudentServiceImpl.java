package cn.srd.library.java.orm.mybatis.flex.service.impl;

import cn.srd.library.java.orm.mybatis.flex.dao.StudentDao;
import cn.srd.library.java.orm.mybatis.flex.model.po.StudentPO;
import cn.srd.library.java.orm.mybatis.flex.service.StudentService;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentDao, StudentPO> implements StudentService {

}
