package cn.srd.library.java.tool.geometry.mybatis.plus.database.postgresql.support;

import com.baomidou.mybatisplus.annotation.TableName;
import org.locationtech.jts.geom.Geometry;

import java.lang.annotation.*;

/**
 * <pre>
 * 该注解可标记在与表关联的持久化模型的某个字段上，该持久化模型上必须标记了{@link TableName}，且该字段必须为 {@link Geometry} 的数据类型才有效，
 * 用于在执行该表相关的查询时，将 Postgresql 的 Geometry 转换为 Text，
 * 本质上是替换了查询 SQL，如：
 * SELECT id,location FROM geometry_test; 将被替换为 SELECT id,ST_ASTEXT(location) AS location FROM geometry_test;
 * </pre>
 *
 * @author wjm
 * @since 2023-03-14 15:39:11
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PostgresqlGeometryToTextFunctionOnSelectSqlWrapper {

}