package cn.library.java.orm.mybatis.flex.postgresql.service.impl;

import cn.library.java.orm.mybatis.flex.postgresql.dao.BookDao;
import cn.library.java.orm.mybatis.flex.postgresql.model.po.BookPO;
import cn.library.java.orm.mybatis.flex.postgresql.service.BookService;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl extends ServiceImpl<BookDao, BookPO> implements BookService {

}
