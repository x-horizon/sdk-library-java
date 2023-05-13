package cn.srd.itcp.sugar.orm.mybatis.plus.common.database.postgresql.metadata.bean.convert;

import cn.srd.itcp.sugar.component.convert.mapstruct.utils.IgnoreUnmappedMapperConfigurator;
import cn.srd.itcp.sugar.component.convert.mapstruct.utils.MapstructMappingManager;
import cn.srd.itcp.sugar.doc.knife4j.model.PageResult;
import cn.srd.itcp.sugar.orm.mybatis.plus.common.database.postgresql.metadata.bean.dto.PostgresqlTableDTO;
import cn.srd.itcp.sugar.orm.mybatis.plus.common.database.postgresql.metadata.bean.vo.PostgresqlTableVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * postgresql table 相关信息 模型转换
 *
 * @author wjm
 * @since 2022-07-18 17:59:54
 */
@Mapper(uses = MapstructMappingManager.class, config = IgnoreUnmappedMapperConfigurator.class)
public interface PostgresqlTableConverter {

    /**
     * 获取实例
     */
    PostgresqlTableConverter INSTANCE = Mappers.getMapper(PostgresqlTableConverter.class);

    /**
     * PostgresqlTableDTO =&gt; PostgresqlTableVO
     *
     * @param entity 待转换对象
     * @return 转换结果
     */
    PostgresqlTableVO toPostgresqlTableVO(PostgresqlTableDTO entity);

    /**
     * List&lt;PostgresqlTableDTO&gt; =&gt; List&lt;PostgresqlTableVO&gt;
     *
     * @param entities 待转换对象
     * @return 转换结果
     */
    @Named("toVOs1")
    @IterableMapping(elementTargetType = PostgresqlTableVO.class)
    List<PostgresqlTableVO> toPostgresqlTableVOs(List<PostgresqlTableDTO> entities);

    /**
     * IPage&lt;PostgresqlTableDTO&gt; =&gt; PageResult&lt;PostgresqlTableVO&gt;
     *
     * @param page 待转换对象
     * @return 转换结果
     */
    @Mappings({
            @Mapping(source = "pages", target = "totalPages"),
            @Mapping(source = "current", target = "currentPage"),
            @Mapping(source = "size", target = "pageSize"),
            @Mapping(source = "records", target = "data", qualifiedByName = "toVOs1"),
            @Mapping(target = "datum", ignore = true),
    })
    PageResult<PostgresqlTableVO> toPostgresqlTablePageResultVO(IPage<PostgresqlTableDTO> page);

}