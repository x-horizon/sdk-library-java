package cn.srd.library.java.orm.mybatis.flex.demo.school.service.impl;

import cn.srd.library.java.orm.mybatis.flex.demo.school.dao.BookDao;
import cn.srd.library.java.orm.mybatis.flex.demo.school.model.po.BookPO;
import cn.srd.library.java.orm.mybatis.flex.demo.school.service.BookService;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl extends ServiceImpl<BookDao, BookPO> implements BookService {

}
