// // Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// // Use of this source code is governed by SRD.
// // license that can be found in the LICENSE file.
//
// package cn.srd.library.java.orm.mybatis.flex.postgresql.chain;
//
// import cn.srd.library.java.contract.constant.booleans.BooleanConstant;
// import cn.srd.library.java.contract.constant.module.ModuleView;
// import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
// import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
// import cn.srd.library.java.orm.contract.model.base.PO;
// import cn.srd.library.java.orm.contract.model.base.POJO;
// import cn.srd.library.java.orm.mybatis.flex.base.chain.BaseQueryChainer;
// import cn.srd.library.java.orm.mybatis.flex.base.chain.QueryChain;
// import cn.srd.library.java.orm.mybatis.flex.base.tool.ColumnNameGetter;
// import cn.srd.library.java.orm.mybatis.flex.base.tool.MybatisFlexs;
// import cn.srd.library.java.orm.mybatis.flex.postgresql.cache.ColumnJsonbMappingAliasCache;
// import cn.srd.library.java.orm.mybatis.flex.postgresql.function.PostgresqlFunction;
// import cn.srd.library.java.tool.lang.collection.Collections;
// import cn.srd.library.java.tool.lang.functional.Assert;
// import cn.srd.library.java.tool.lang.object.Nil;
// import cn.srd.library.java.tool.lang.reflect.Reflects;
// import cn.srd.library.java.tool.lang.text.Strings;
// import com.google.errorprone.annotations.CanIgnoreReturnValue;
// import com.mybatisflex.core.constant.SqlConnector;
// import com.mybatisflex.core.query.RawQueryTable;
// import lombok.AccessLevel;
// import lombok.Getter;
// import lombok.Setter;
//
// import java.util.List;
//
// /**
//  * @author wjm
//  * @since 2024-04-23 19:48
//  */
// @Getter(AccessLevel.PROTECTED)
// @Setter(AccessLevel.PRIVATE)
// @CanIgnoreReturnValue
// public class JsonbQueryChainer<PJ extends POJO, P extends PO> extends BaseQueryChainer<PJ> {
//
//     private final NormalQueryChainer<P, PJ> normalQueryChainer;
//
//     private final QueryChain<PJ> nativeQueryChain;
//
//     private final Class<P> poClass;
//
//     private String jsonbFunctionSQL;
//
//     private String theLastJsonKeyName;
//
//     private ColumnNameGetter<PJ> theLastJsonKeyNameGetter;
//
//     public JsonbQueryChainer(NormalQueryChainer<P, PJ> normalQueryChainer, Class<P> poClass) {
//         this.normalQueryChainer = normalQueryChainer;
//         this.nativeQueryChain = Reflects.getFieldValue(normalQueryChainer, "nativeQueryChain");
//         this.poClass = poClass;
//     }
//
//     public <PJ1 extends POJO, PJ2 extends POJO> JsonbQueryCaster<PJ, PJ1, PJ2, P> where(ColumnNameGetter<PJ1> columnNameGetter) {
//         return and(columnNameGetter);
//     }
//
//     public <PJ1 extends POJO, PJ2 extends POJO> JsonbQueryCaster<PJ, PJ1, PJ2, P> where(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter) {
//         return and(columnNameGetter, jsonKeyGetter);
//     }
//
//     public <PJ1 extends POJO, PJ2 extends POJO> JsonbQueryCaster<PJ, PJ1, PJ2, P> and(ColumnNameGetter<PJ1> columnNameGetter) {
//         return new JsonbQueryCaster<>(SqlConnector.AND, this, getNativeQueryChain(), columnNameGetter);
//     }
//
//     public <PJ1 extends POJO, PJ2 extends POJO> JsonbQueryCaster<PJ, PJ1, PJ2, P> and(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter) {
//         return new JsonbQueryCaster<>(SqlConnector.AND, this, getNativeQueryChain(), columnNameGetter, jsonKeyGetter);
//     }
//
//     public <PJ1 extends POJO, PJ2 extends POJO> JsonbQueryCaster<PJ, PJ1, PJ2, P> or(ColumnNameGetter<PJ1> columnNameGetter) {
//         return new JsonbQueryCaster<>(SqlConnector.OR, this, getNativeQueryChain(), columnNameGetter);
//     }
//
//     public <PJ1 extends POJO, PJ2 extends POJO> JsonbQueryCaster<PJ, PJ1, PJ2, P> or(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter) {
//         return new JsonbQueryCaster<>(SqlConnector.OR, this, getNativeQueryChain(), columnNameGetter, jsonKeyGetter);
//     }
//
//     @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
//     public <PJ1, PJ2 extends POJO> JsonbQueryChainer<PJ, P> functionObjectExtract(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter) {
//         setTheLastJsonKeyNameGetter((ColumnNameGetter<PJ>) jsonKeyGetter);
//         ColumnJsonbMappingAliasCache.computeToCache(jsonKeyGetter);
//         return functionObjectExtract(columnNameGetter, List.of(MybatisFlexs.getFieldName(jsonKeyGetter)));
//     }
//
//     @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
//     public <PJ1, PJ2, PJ3 extends POJO> JsonbQueryChainer<PJ, P> functionObjectExtract(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter1, ColumnNameGetter<PJ3> jsonKeyGetter2) {
//         setTheLastJsonKeyNameGetter((ColumnNameGetter<PJ>) jsonKeyGetter2);
//         ColumnJsonbMappingAliasCache.computeToCache(jsonKeyGetter2);
//         return functionObjectExtract(columnNameGetter, List.of(
//                 MybatisFlexs.getFieldName(jsonKeyGetter1),
//                 MybatisFlexs.getFieldName(jsonKeyGetter2)
//         ));
//     }
//
//     @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
//     public <PJ1, PJ2, PJ3, PJ4 extends POJO> JsonbQueryChainer<PJ, P> functionObjectExtract(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter1, ColumnNameGetter<PJ3> jsonKeyGetter2, ColumnNameGetter<PJ4> jsonKeyGetter3) {
//         setTheLastJsonKeyNameGetter((ColumnNameGetter<PJ>) jsonKeyGetter3);
//         ColumnJsonbMappingAliasCache.computeToCache(jsonKeyGetter3);
//         return functionObjectExtract(columnNameGetter, List.of(
//                 MybatisFlexs.getFieldName(jsonKeyGetter1),
//                 MybatisFlexs.getFieldName(jsonKeyGetter2),
//                 MybatisFlexs.getFieldName(jsonKeyGetter3)
//         ));
//     }
//
//     @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
//     public <PJ1, PJ2, PJ3, PJ4, PJ5 extends POJO> JsonbQueryChainer<PJ, P> functionObjectExtract(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter1, ColumnNameGetter<PJ3> jsonKeyGetter2, ColumnNameGetter<PJ4> jsonKeyGetter3, ColumnNameGetter<PJ5> jsonKeyGetter4) {
//         setTheLastJsonKeyNameGetter((ColumnNameGetter<PJ>) jsonKeyGetter4);
//         ColumnJsonbMappingAliasCache.computeToCache(jsonKeyGetter4);
//         return functionObjectExtract(columnNameGetter, List.of(
//                 MybatisFlexs.getFieldName(jsonKeyGetter1),
//                 MybatisFlexs.getFieldName(jsonKeyGetter2),
//                 MybatisFlexs.getFieldName(jsonKeyGetter3),
//                 MybatisFlexs.getFieldName(jsonKeyGetter4)
//         ));
//     }
//
//     @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
//     public <PJ1, PJ2, PJ3, PJ4, PJ5, PJ6 extends POJO> JsonbQueryChainer<PJ, P> functionObjectExtract(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter1, ColumnNameGetter<PJ3> jsonKeyGetter2, ColumnNameGetter<PJ4> jsonKeyGetter3, ColumnNameGetter<PJ5> jsonKeyGetter4, ColumnNameGetter<PJ6> jsonKeyGetter5) {
//         setTheLastJsonKeyNameGetter((ColumnNameGetter<PJ>) jsonKeyGetter5);
//         ColumnJsonbMappingAliasCache.computeToCache(jsonKeyGetter5);
//         return functionObjectExtract(columnNameGetter, List.of(
//                 MybatisFlexs.getFieldName(jsonKeyGetter1),
//                 MybatisFlexs.getFieldName(jsonKeyGetter2),
//                 MybatisFlexs.getFieldName(jsonKeyGetter3),
//                 MybatisFlexs.getFieldName(jsonKeyGetter4),
//                 MybatisFlexs.getFieldName(jsonKeyGetter5)
//         ));
//     }
//
//     @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
//     public <PJ1, PJ2, PJ3, PJ4, PJ5, PJ6, PJ7 extends POJO> JsonbQueryChainer<PJ, P> functionObjectExtract(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter1, ColumnNameGetter<PJ3> jsonKeyGetter2, ColumnNameGetter<PJ4> jsonKeyGetter3, ColumnNameGetter<PJ5> jsonKeyGetter4, ColumnNameGetter<PJ6> jsonKeyGetter5, ColumnNameGetter<PJ7> jsonKeyGetter6) {
//         setTheLastJsonKeyNameGetter((ColumnNameGetter<PJ>) jsonKeyGetter6);
//         ColumnJsonbMappingAliasCache.computeToCache(jsonKeyGetter6);
//         return functionObjectExtract(columnNameGetter, List.of(
//                 MybatisFlexs.getFieldName(jsonKeyGetter1),
//                 MybatisFlexs.getFieldName(jsonKeyGetter2),
//                 MybatisFlexs.getFieldName(jsonKeyGetter3),
//                 MybatisFlexs.getFieldName(jsonKeyGetter4),
//                 MybatisFlexs.getFieldName(jsonKeyGetter5),
//                 MybatisFlexs.getFieldName(jsonKeyGetter6)
//         ));
//     }
//
//     @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
//     public <PJ1, PJ2, PJ3, PJ4, PJ5, PJ6, PJ7, PJ8 extends POJO> JsonbQueryChainer<PJ, P> functionObjectExtract(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter1, ColumnNameGetter<PJ3> jsonKeyGetter2, ColumnNameGetter<PJ4> jsonKeyGetter3, ColumnNameGetter<PJ5> jsonKeyGetter4, ColumnNameGetter<PJ6> jsonKeyGetter5, ColumnNameGetter<PJ7> jsonKeyGetter6, ColumnNameGetter<PJ8> jsonKeyGetter7) {
//         setTheLastJsonKeyNameGetter((ColumnNameGetter<PJ>) jsonKeyGetter7);
//         ColumnJsonbMappingAliasCache.computeToCache(jsonKeyGetter7);
//         return functionObjectExtract(columnNameGetter, List.of(
//                 MybatisFlexs.getFieldName(jsonKeyGetter1),
//                 MybatisFlexs.getFieldName(jsonKeyGetter2),
//                 MybatisFlexs.getFieldName(jsonKeyGetter3),
//                 MybatisFlexs.getFieldName(jsonKeyGetter4),
//                 MybatisFlexs.getFieldName(jsonKeyGetter5),
//                 MybatisFlexs.getFieldName(jsonKeyGetter6),
//                 MybatisFlexs.getFieldName(jsonKeyGetter7)
//         ));
//     }
//
//     @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
//     public <PJ1, PJ2, PJ3, PJ4, PJ5, PJ6, PJ7, PJ8, PJ9 extends POJO> JsonbQueryChainer<PJ, P> functionObjectExtract(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter1, ColumnNameGetter<PJ3> jsonKeyGetter2, ColumnNameGetter<PJ4> jsonKeyGetter3, ColumnNameGetter<PJ5> jsonKeyGetter4, ColumnNameGetter<PJ6> jsonKeyGetter5, ColumnNameGetter<PJ7> jsonKeyGetter6, ColumnNameGetter<PJ8> jsonKeyGetter7, ColumnNameGetter<PJ9> jsonKeyGetter8) {
//         setTheLastJsonKeyNameGetter((ColumnNameGetter<PJ>) jsonKeyGetter8);
//         ColumnJsonbMappingAliasCache.computeToCache(jsonKeyGetter8);
//         return functionObjectExtract(columnNameGetter, List.of(
//                 MybatisFlexs.getFieldName(jsonKeyGetter1),
//                 MybatisFlexs.getFieldName(jsonKeyGetter2),
//                 MybatisFlexs.getFieldName(jsonKeyGetter3),
//                 MybatisFlexs.getFieldName(jsonKeyGetter4),
//                 MybatisFlexs.getFieldName(jsonKeyGetter5),
//                 MybatisFlexs.getFieldName(jsonKeyGetter6),
//                 MybatisFlexs.getFieldName(jsonKeyGetter7),
//                 MybatisFlexs.getFieldName(jsonKeyGetter8)
//         ));
//     }
//
//     @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
//     public <PJ1, PJ2, PJ3, PJ4, PJ5, PJ6, PJ7, PJ8, PJ9, PJ10 extends POJO> JsonbQueryChainer<PJ, P> functionObjectExtract(ColumnNameGetter<PJ1> columnNameGetter, ColumnNameGetter<PJ2> jsonKeyGetter1, ColumnNameGetter<PJ3> jsonKeyGetter2, ColumnNameGetter<PJ4> jsonKeyGetter3, ColumnNameGetter<PJ5> jsonKeyGetter4, ColumnNameGetter<PJ6> jsonKeyGetter5, ColumnNameGetter<PJ7> jsonKeyGetter6, ColumnNameGetter<PJ8> jsonKeyGetter7, ColumnNameGetter<PJ9> jsonKeyGetter8, ColumnNameGetter<PJ10> jsonKeyGetter9) {
//         setTheLastJsonKeyNameGetter((ColumnNameGetter<PJ>) jsonKeyGetter9);
//         ColumnJsonbMappingAliasCache.computeToCache(jsonKeyGetter9);
//         return functionObjectExtract(columnNameGetter, List.of(
//                 MybatisFlexs.getFieldName(jsonKeyGetter1),
//                 MybatisFlexs.getFieldName(jsonKeyGetter2),
//                 MybatisFlexs.getFieldName(jsonKeyGetter3),
//                 MybatisFlexs.getFieldName(jsonKeyGetter4),
//                 MybatisFlexs.getFieldName(jsonKeyGetter5),
//                 MybatisFlexs.getFieldName(jsonKeyGetter6),
//                 MybatisFlexs.getFieldName(jsonKeyGetter7),
//                 MybatisFlexs.getFieldName(jsonKeyGetter8),
//                 MybatisFlexs.getFieldName(jsonKeyGetter9)
//         ));
//     }
//
//     @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
//     private <C> JsonbQueryChainer<PJ, P> functionObjectExtract(ColumnNameGetter<C> columnNameGetter, List<String> jsonKeys) {
//         String jsonColumnName = MybatisFlexs.getFieldName(columnNameGetter);
//         String jsonViewAlias = ColumnJsonbMappingAliasCache.get((ColumnNameGetter<PJ>) columnNameGetter, jsonColumnName);
//         if (Nil.isNotNull(jsonViewAlias)) {
//             jsonColumnName = jsonViewAlias;
//         }
//         setTheLastJsonKeyName(Collections.getLast(jsonKeys).orElseThrow());
//         setJsonbFunctionSQL(Strings.format(PostgresqlFunction.JSONB_OBJECT_EXTRACT_APPENDER, jsonColumnName, Strings.joinWithSingleQuoteAndComma(jsonKeys)));
//         return this;
//     }
//
//     public JsonbQueryChainer<PJ, P> functionArrayUnnest() {
//         ColumnJsonbMappingAliasCache.computeToCache(getJsonbFunctionSQL(), getPoClass().getSimpleName());
//         setJsonbFunctionSQL(Strings.format(PostgresqlFunction.JSONB_ARRAY_UNNEST_APPENDER, getJsonbFunctionSQL()));
//         return this;
//     }
//
//     @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
//     public <PJ1 extends POJO> JsonbQueryChainer<PJ, P> functionArrayUnnest(ColumnNameGetter<PJ1> columnNameGetter) {
//         ColumnJsonbMappingAliasCache.computeToUnderlineCaseCache(columnNameGetter);
//         String jsonColumnName = Strings.underlineCase(MybatisFlexs.getFieldName(columnNameGetter));
//         setTheLastJsonKeyName(jsonColumnName);
//         setTheLastJsonKeyNameGetter((ColumnNameGetter<PJ>) columnNameGetter);
//         setJsonbFunctionSQL(Strings.format(PostgresqlFunction.JSONB_ARRAY_UNNEST_APPENDER, jsonColumnName));
//         return this;
//     }
//
//     @SuppressWarnings(SuppressWarningConstant.DEPRECATED)
//     public JsonbQueryChainer<PJ, P> innerJoin() {
//         String jsonViewAlias = ColumnJsonbMappingAliasCache.get(getTheLastJsonKeyNameGetter(), getTheLastJsonKeyName());
//         Assert.of().setMessage("{}could not find the json query alias name by [{}], please check!", ModuleView.ORM_MYBATIS_SYSTEM, getTheLastJsonKeyName())
//                 .setThrowable(LibraryJavaInternalException.class)
//                 .throwsIfNull(jsonViewAlias);
//         getNativeQueryChain()
//                 .innerJoin(new RawQueryTable(getJsonbFunctionSQL()))
//                 .as(Strings.removeHeadTailDoubleQuote(jsonViewAlias))
//                 .on(BooleanConstant.TRUE_STRING_LOWER_CASE);
//         return new JsonbQueryChainer<>(getNormalQueryChainer(), getPoClass());
//     }
//
//     public NormalQueryChainer<P, PJ> switchToNormalQuery() {
//         return getNormalQueryChainer();
//     }
//
// }