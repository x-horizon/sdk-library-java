package cn.srd.itcp.sugar.mybatis.plus.metadata.bean.convert;

import cn.srd.itcp.sugar.convert.mapstruct.core.BindMapstruct;
import cn.srd.itcp.sugar.convert.mapstruct.utils.MapstructMappingManager;
import cn.srd.itcp.sugar.mybatis.plus.metadata.bean.dto.PostgresqlTableDTO;
import cn.srd.itcp.sugar.mybatis.plus.metadata.bean.vo.PostgresqlTableVO;
import cn.srd.itcp.sugar.tools.page.PageResult;
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
@BindMapstruct
@Mapper(componentModel = "spring", uses = MapstructMappingManager.class)
public interface PostgresqlTableConverter {

    PostgresqlTableConverter INSTANCE = Mappers.getMapper(PostgresqlTableConverter.class);

    PostgresqlTableVO toVO1(PostgresqlTableDTO entity);

    @Named("toVOs1")
    @IterableMapping(elementTargetType = PostgresqlTableVO.class)
    List<PostgresqlTableVO> toVOs1(List<PostgresqlTableDTO> entities);

    @Mappings({
            @Mapping(source = "pages", target = "totalPages"),
            @Mapping(source = "current", target = "currentPage"),
            @Mapping(source = "size", target = "pageSize"),
            @Mapping(source = "records", target = "data", qualifiedByName = "toVOs1"),
            @Mapping(target = "datum", ignore = true),
    })
    PageResult<PostgresqlTableVO> toPageVO1(IPage<PostgresqlTableDTO> page);

}