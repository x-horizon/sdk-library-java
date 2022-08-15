package cn.srd.itcp.sugar.excel.core;

import cn.srd.itcp.sugar.tools.constant.Charset;
import cn.srd.itcp.sugar.tools.constant.HttpInfo;
import cn.srd.itcp.sugar.tools.core.SpringsWebUtil;
import com.alibaba.excel.EasyExcel;
import lombok.SneakyThrows;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Excel 工具
 *
 * @author wjm
 * @since 2022-08-08 17:59:12
 */
public class Excels {

    private Excels() {
    }

    /**
     * 导出并下载 excel
     *
     * @param exportDataClass 导出的数据类型
     * @param exportData      导出的数据
     * @param fileName        下载的文件命名
     * @param sheetName       sheet 命名
     * @param <T>             导出的数据类型
     */
    @SneakyThrows
    public static <T> void export(Class<T> exportDataClass, List<T> exportData, String fileName, String sheetName) {
        HttpServletResponse httpServletResponse = SpringsWebUtil.getHttpServletResponse();
        httpServletResponse.setContentType(HttpInfo.CONTENT_TYPE_EXCEL);
        httpServletResponse.setCharacterEncoding(Charset.UTF8);
        httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(httpServletResponse.getOutputStream(), exportDataClass)
                .sheet(sheetName)
                .doWrite(exportData);
    }

}
