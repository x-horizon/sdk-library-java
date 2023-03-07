package cn.srd.itcp.sugar.tool.web;

/**
 * 响应码 + 响应码对应的描述
 *
 * @author wjm
 * @since 2020/6/29 14:25
 */
public interface HttpStatus {

    /**
     * 响应码
     *
     * @return 响应码
     */
    int getCode();

    /**
     * 响应码对应的描述
     *
     * @return 响应码对应的描述
     */
    String getDescription();

}
