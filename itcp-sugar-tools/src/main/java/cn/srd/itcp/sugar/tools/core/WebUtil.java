package cn.srd.itcp.sugar.tools.core;

/**
 * Web 工具
 *
 * @author wjm
 * @since 2022-12-15 15:51:11
 */
public class WebUtil {

    /**
     * private block constructor
     */
    private WebUtil() {
    }

    // public static void main(String[] args) {
    //     String baseUrl = "http://demo:demo@127.0.0.1:8083";
    //     String uri = "/stream/8f1351db-c4bc-4132-a509-f167271eff7b/add";
    //     String url = "http://demo:demo@127.0.0.1:8083/stream/8f1351db-c4bc-4132-a509-f167271eff7b/add";
    //     String body = "{\"uuid\":\"8f1351db-c4bc-4132-a509-f167271eff7b\",\"name\":\"港湾大道十字路口东进口车流排队统计设备4\",\"channels\":{\"0\":{\"url\":\"rtsp://192.168.10.133:554/ch01/1\",\"debug\":true,\"on_demand\":false}}}";
    //     HTTP.builder().baseUrl(baseUrl).build().sync(uri).addBodyPara(JSON.parseObject(body)).post();
    //
    //     // new HttpRequest(UrlBuilder.of()).method(Method.POST);
    //     HttpUtil.post(url, body);
    //     HttpUtil.createPost(url);
    // }

}
