package cn.srd.sugar.tool.excel.alibaba.easy.excel.utils;

import cn.srd.itcp.sugar.tool.core.EnumsUtil;
import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.ReadConverterContext;

/**
 * Excel 属性类型转换器：Enum 属性值 =&gt; Enum
 *
 * @author wjm
 * @since 2022-08-15 23:42:11
 */
public class ExcelCapableToEnumConverter implements Converter<Enum<?>> {

    @SuppressWarnings("all")
    @Override
    public Enum<?> convertToJavaData(ReadConverterContext<?> context) {
        return EnumsUtil.capableToEnum(context.getReadCellData().getStringValue(), (Class<Enum>) context.getContentProperty().getField().getType());
    }

}
