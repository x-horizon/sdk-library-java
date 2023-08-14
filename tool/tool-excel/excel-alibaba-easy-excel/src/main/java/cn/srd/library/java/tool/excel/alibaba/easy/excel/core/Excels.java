package cn.srd.library.java.tool.excel.alibaba.easy.excel.core;

import cn.srd.library.java.tool.constant.core.Charset;
import cn.srd.library.java.tool.constant.core.HttpInfo;
import cn.srd.library.java.tool.spring.webmvc.core.SpringsWebMVCUtil;
import com.alibaba.excel.EasyExcel;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

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
    public static <T> void exportData(Class<T> exportDataClass, List<T> exportData, String fileName, String sheetName) {
        HttpServletResponse httpServletResponse = SpringsWebMVCUtil.getHttpServletResponse();
        httpServletResponse.setContentType(HttpInfo.CONTENT_TYPE_EXCEL);
        httpServletResponse.setCharacterEncoding(Charset.UTF8);
        httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(httpServletResponse.getOutputStream(), exportDataClass)
                .sheet(sheetName)
                .doWrite(exportData);
    }

}
