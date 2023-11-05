package cn.srd.library.java.orm.mybatis.flex.service.impl;

import cn.srd.library.java.orm.mybatis.flex.dao.BookDao;
import cn.srd.library.java.orm.mybatis.flex.model.po.BookPO;
import cn.srd.library.java.orm.mybatis.flex.service.BookService;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl extends ServiceImpl<BookDao, BookPO> implements BookService {

}
