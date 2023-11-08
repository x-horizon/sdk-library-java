// package cn.library.java.orm.mybatis.contract.base.handler;
//
// import cn.srd.library.java.contract.constant.text.SymbolConstant;
// import cn.srd.library.java.tool.lang.enums.Enums;
// import cn.srd.library.java.tool.lang.object.Nil;
// import cn.srd.library.java.tool.lang.text.Strings;
// import cn.srd.library.java.tool.spring.contract.Springs;
//
// import java.sql.ResultSet;
// import java.util.List;
//
// /**
//  * @param <E> the java enum data type
//  * @author wjm
//  * @since 2022-09-07 10:35:42
//  */
// public class JdbcStringMappingJavaListEnumIntegerTypeHandler<E extends Enum<E>> extends AbstractJdbcComplexTypeHandler<List<E>> {
//
//     @Override
//     protected Object toJdbcObject(List<E> javaObject) {
//         return Nil.isNull(javaObject) ? SymbolConstant.EMPTY : Enums.toStringByFieldDataType(javaObject, Integer.class);
//     }
//
//     @Override
//     protected List<E> toJavaObject(ResultSet resultSet, String columnName) {
//         Springs.getBean(ColumnJsonbMappingRelationCache.class).getMappingJavaTypes(columnName);
//         return Strings.splitToEnums(resultSet.getString(columnName), getTargetEnumClass());
//     }
//
// }
//
