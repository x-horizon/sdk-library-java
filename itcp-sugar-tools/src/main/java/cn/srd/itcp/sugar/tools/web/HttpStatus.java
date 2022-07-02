package cn.srd.itcp.sugar.tools.web;

/**
 * 响应码 + 响应码对应的描述
 *
 * @author wjm
 * @date 2020/6/29 14:25
 */
public interface HttpStatus {

    /**
     * 响应码
     *
     * @return
     */
    int getCode();

    /**
     * 响应码对应的描述
     *
     * @return
     */
    String getDescription();

}
