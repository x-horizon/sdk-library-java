package org.horizon.sdk.library.java.orm.mybatis.flex.postgresql.chain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.horizon.sdk.library.java.contract.constant.collection.CollectionConstant;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.support.ColumnNameGetter;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.support.MybatisFlexs;
import org.horizon.sdk.library.java.tool.lang.object.Nil;
import org.horizon.sdk.library.java.tool.lang.text.Strings;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author wjm
 * @since 2024-04-30 12:12
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonQuerySQLConnector {

    static String directConnect(String tableName, ColumnNameGetter<?>... columnNameGetters) {
        return Strings.format("({})", IntStream.range(CollectionConstant.INDEX_FIRST, columnNameGetters.length)
                .mapToObj(index -> {
                    String columnName = MybatisFlexs.getFieldName(columnNameGetters[index]);
                    if (CollectionConstant.INDEX_FIRST == index) {
                        return Strings.format("\"{}\".{}", tableName, columnName);
                    } else if (index == columnNameGetters.length - 1) {
                        return Strings.format(" ->> '{}'", columnName);
                    } else {
                        return Strings.format(" -> '{}'", columnName);
                    }
                })
                .collect(Collectors.joining())
        );
    }

    static String functionConnect(String tableName, ColumnNameGetter<?>... columnNameGetters) {
        if (Nil.isEmpty(columnNameGetters)) {
            return tableName;
        }
        return Strings.format("({})", IntStream.range(CollectionConstant.INDEX_FIRST, columnNameGetters.length)
                .mapToObj(index -> {
                    String columnName = MybatisFlexs.getFieldName(columnNameGetters[index]);
                    if (CollectionConstant.INDEX_FIRST == index) {
                        if (CollectionConstant.LENGTH_ONE == columnNameGetters.length) {
                            return Strings.format("{} ->> '{}'", tableName, columnName);
                        } else {
                            return Strings.format("{} -> '{}'", tableName, columnName);
                        }
                    } else if (index == columnNameGetters.length - 1) {
                        return Strings.format(" ->> '{}'", columnName);
                    } else {
                        return Strings.format(" -> '{}'", columnName);
                    }
                })
                .collect(Collectors.joining())
        );
    }

}