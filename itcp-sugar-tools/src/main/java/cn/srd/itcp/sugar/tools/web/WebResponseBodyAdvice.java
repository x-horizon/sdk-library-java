// package cn.commons.tools.web;
//
// import org.springframework.core.MethodParameter;
// import org.springframework.http.MediaType;
// import org.springframework.http.server.ServerHttpRequest;
// import org.springframework.http.server.ServerHttpResponse;
// import org.springframework.web.bind.annotation.ControllerAdvice;
// import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
//
// /**
//  * @author wjm
//  * @date 2020/6/13 20:05
//  */
// @ControllerAdvice
// public class WebResponseBodyAdvice implements ResponseBodyAdvice<Object> {
//
//     /**
//      * 统一格式化响应信息
//      *
//      * @param body                  Controller返回结果
//      * @param returnType
//      * @param selectedContentType
//      * @param selectedConverterType
//      * @param request
//      * @param response
//      * @return
//      */
//     @Override
//     public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
//         return body;
//     }
//
//     /**
//      * 只拦截返回结果为 {@link WebResponse} 类型
//      *
//      * @param returnType
//      * @param converterType
//      * @return
//      */
//     @Override
//     public boolean supports(MethodParameter returnType, Class converterType) {
//         if (returnType.getMethod() == null) {
//             return false;
//         }
//         return returnType.getMethod().getReturnType() == WebResponse.class;
//     }
//
// }
