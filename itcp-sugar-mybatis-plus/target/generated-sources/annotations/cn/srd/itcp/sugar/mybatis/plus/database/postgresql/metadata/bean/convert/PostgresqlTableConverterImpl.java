package cn.srd.itcp.sugar.mybatis.plus.database.postgresql.metadata.bean.convert;

import cn.srd.itcp.sugar.mybatis.plus.database.postgresql.metadata.bean.dto.PostgresqlTableDTO;
import cn.srd.itcp.sugar.mybatis.plus.database.postgresql.metadata.bean.vo.PostgresqlTableVO;
import cn.srd.itcp.sugar.tools.page.PageResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-11T20:20:58+0800",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.15 (Amazon.com Inc.)"
)
public class PostgresqlTableConverterImpl implements PostgresqlTableConverter {

    @Override
    public PostgresqlTableVO toPostgresqlTableVO(PostgresqlTableDTO entity) {
        if ( entity == null ) {
            return null;
        }

        PostgresqlTableVO postgresqlTableVO = new PostgresqlTableVO();

        postgresqlTableVO.setTableName( entity.getTableName() );
        postgresqlTableVO.setTableComment( entity.getTableComment() );

        return postgresqlTableVO;
    }

    @Override
    public List<PostgresqlTableVO> toPostgresqlTableVOs(List<PostgresqlTableDTO> entities) {
        if ( entities == null ) {
            return null;
        }

        List<PostgresqlTableVO> list = new ArrayList<PostgresqlTableVO>( entities.size() );
        for ( PostgresqlTableDTO postgresqlTableDTO : entities ) {
            list.add( toPostgresqlTableVO( postgresqlTableDTO ) );
        }

        return list;
    }

    @Override
    public PageResult<PostgresqlTableVO> toPostgresqlTablePageResultVO(IPage<PostgresqlTableDTO> page) {
        if ( page == null ) {
            return null;
        }

        PageResult<PostgresqlTableVO> pageResult = new PageResult<PostgresqlTableVO>();

        pageResult.setTotalPages( page.getPages() );
        pageResult.setCurrentPage( page.getCurrent() );
        pageResult.setPageSize( page.getSize() );
        pageResult.setData( toPostgresqlTableVOs( page.getRecords() ) );
        pageResult.setTotal( page.getTotal() );

        return pageResult;
    }
}
