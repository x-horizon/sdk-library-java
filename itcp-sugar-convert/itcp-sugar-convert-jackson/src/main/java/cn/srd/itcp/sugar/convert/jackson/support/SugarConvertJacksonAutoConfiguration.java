// package cn.srd.itcp.sugar.convert.jackson.support;
//
// import cn.srd.itcp.sugar.convert.jackson.core.EnableJacksonCapableToEnumDeserializer;
// import cn.srd.itcp.sugar.tools.core.Objects;
// import cn.srd.itcp.sugar.tools.core.SpringsUtil;
// import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
// import org.springframework.context.annotation.Bean;
//
// /**
//  * {@link EnableAutoConfiguration AutoConfiguration} for Sugar Convert Jackson
//  *
//  * @author wjm
//  * @date 2022-07-19 20:25:11
//  */
// public class SugarConvertJacksonAutoConfiguration {
//
//     @SuppressWarnings("unchecked")
//     @Bean
//     public <E extends Enum<E>> JacksonCapableToEnumDeserializer<E> jacksonCapableToEnumDeserializer() {
//         if (Objects.isNotEmpty(SpringsUtil.scanPackageByAnnotation(EnableJacksonCapableToEnumDeserializer.class))) {
//             return SpringsUtil.registerCapableBean(JacksonCapableToEnumDeserializer.class);
//         }
//         return null;
//     }
//
// }
