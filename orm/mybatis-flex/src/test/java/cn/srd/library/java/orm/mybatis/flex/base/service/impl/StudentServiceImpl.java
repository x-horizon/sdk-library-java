package cn.srd.library.java.orm.mybatis.flex.base.service.impl;

import cn.srd.library.java.orm.mybatis.flex.base.dao.StudentDao;
import cn.srd.library.java.orm.mybatis.flex.base.model.po.StudentPO;
import cn.srd.library.java.orm.mybatis.flex.base.service.StudentService;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentDao, StudentPO> implements StudentService {

}
