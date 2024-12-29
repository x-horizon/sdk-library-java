// package cn.srd.library.java.tool.convert.all.benchmark;
//
// import cn.hutool.core.bean.BeanUtil;
// import cn.hutool.core.util.ReflectUtil;
// import cn.srd.library.java.tool.convert.all.Converts;
// import cn.srd.library.java.tool.convert.all.ToolConvertAllApplication;
// import cn.srd.library.java.tool.convert.all.mapstruct.converter.StudentMapstructConverter;
// import cn.srd.library.java.tool.convert.all.mapstruct.converter.StudentMapstructConverterImpl;
// import cn.srd.library.java.tool.convert.all.mapstruct.model.domain.GradeDO;
// import cn.srd.library.java.tool.convert.all.mapstruct.model.domain.StudentDO;
// import cn.srd.library.java.tool.convert.all.mapstruct.model.vo.GradeVO;
// import cn.srd.library.java.tool.convert.all.mapstruct.model.vo.StudentVO;
// import com.esotericsoftware.reflectasm.MethodAccess;
// import lombok.SneakyThrows;
// import org.openjdk.jmh.annotations.*;
// import org.openjdk.jmh.runner.Runner;
// import org.openjdk.jmh.runner.RunnerException;
// import org.openjdk.jmh.runner.options.OptionsBuilder;
// import org.springframework.beans.BeanUtils;
// import org.springframework.boot.SpringApplication;
// import org.springframework.context.ConfigurableApplicationContext;
//
// import java.lang.reflect.Method;
// import java.util.List;
// import java.util.Map;
// import java.util.concurrent.ConcurrentHashMap;
// import java.util.concurrent.TimeUnit;
//
// @Fork(1)
// @State(Scope.Thread)
// @Warmup(iterations = 0)
// @Measurement(iterations = 3)
// @BenchmarkMode(Mode.SingleShotTime)
// @OutputTimeUnit(TimeUnit.MILLISECONDS)
// public class ConvertAllBenchmarkTest {
//
//     private static final Map<String, MethodAccess> MAPSTRUCT_CLASS_NAME_MAPPING_REFLECT_ASM_MAP = new ConcurrentHashMap<>();
//
//     private static final StudentMapstructConverterImpl STUDENT_MAPSTRUCT_CONVERTER = new StudentMapstructConverterImpl();
//
//     private static final StudentDO STUDENT_DO = StudentDO.newDO();
//
//     private static final GradeDO GRADE_DO1 = GradeDO.newDO();
//
//     private static final GradeDO GRADE_DO2 = GradeDO.newDO();
//
//     private static final List<StudentDO> STUDENT_DOS = StudentDO.newDOs();
//
//     private ConfigurableApplicationContext applicationContext;
//
//     @Param({"100000"})
//     // @Param({"1000", "10000", "100000", "1000000", "10000000"})
//     private int dataSize;
//
//     @Setup(Level.Trial)
//     public void initialize() {
//         applicationContext = SpringApplication.run(ToolConvertAllApplication.class);
//     }
//
//     @TearDown(Level.Trial)
//     public void close() {
//         applicationContext.close();
//     }
//
//     @Benchmark
//     public void testDirectConvertBean() {
//         for (int i = 0; i < dataSize; ++i) {
//             StudentMapstructConverter.INSTANCE.toVO(STUDENT_DO);
//         }
//     }
//
//     @Benchmark
//     public void testDirectConvertListBean() {
//         for (int i = 0; i < dataSize; ++i) {
//             StudentMapstructConverter.INSTANCE.toVOs(STUDENT_DOS);
//         }
//     }
//
//     @Benchmark
//     public void testASMConvertBean() {
//         for (int i = 0; i < dataSize; ++i) {
//             MethodAccess cache = MAPSTRUCT_CLASS_NAME_MAPPING_REFLECT_ASM_MAP.get(StudentMapstructConverterImpl.class.getName());
//             if (cache != null) {
//                 cache.invoke(STUDENT_MAPSTRUCT_CONVERTER, "toVO", STUDENT_DO);
//                 continue;
//             }
//             MethodAccess access = MethodAccess.get(StudentMapstructConverterImpl.class);
//             MAPSTRUCT_CLASS_NAME_MAPPING_REFLECT_ASM_MAP.put(StudentMapstructConverterImpl.class.getName(), access);
//             access.invoke(STUDENT_MAPSTRUCT_CONVERTER, "toVO", STUDENT_DO);
//         }
//     }
//
//     @Benchmark
//     public void testASMConvertListBean() {
//         for (int i = 0; i < dataSize; ++i) {
//             MethodAccess cache = MAPSTRUCT_CLASS_NAME_MAPPING_REFLECT_ASM_MAP.get(StudentMapstructConverterImpl.class.getName());
//             if (cache != null) {
//                 cache.invoke(STUDENT_MAPSTRUCT_CONVERTER, "toVOs", STUDENT_DOS);
//                 continue;
//             }
//             MethodAccess access = MethodAccess.get(StudentMapstructConverterImpl.class);
//             MAPSTRUCT_CLASS_NAME_MAPPING_REFLECT_ASM_MAP.put(StudentMapstructConverterImpl.class.getName(), access);
//             access.invoke(STUDENT_MAPSTRUCT_CONVERTER, "toVOs", STUDENT_DOS);
//         }
//     }
//
//     @Benchmark
//     @SneakyThrows
//     public void testReflectConvertBean() {
//         Method method = STUDENT_MAPSTRUCT_CONVERTER.getClass().getMethod("toVO", StudentDO.class);
//         for (int i = 0; i < dataSize; ++i) {
//             ReflectUtil.invokeWithCheck(STUDENT_MAPSTRUCT_CONVERTER, method, STUDENT_DO);
//         }
//     }
//
//     @Benchmark
//     @SneakyThrows
//     public void testReflectConvertListBean() {
//         Method method = STUDENT_MAPSTRUCT_CONVERTER.getClass().getMethod("toVOs", List.class);
//         for (int i = 0; i < dataSize; ++i) {
//             ReflectUtil.invokeWithCheck(STUDENT_MAPSTRUCT_CONVERTER, method, STUDENT_DOS);
//         }
//     }
//
//     @Benchmark
//     public void testConvertsConvertOneBean1() {
//         for (int i = 0; i < dataSize; ++i) {
//             Converts.withGenericMapstruct().toBean(GRADE_DO1, GradeVO.class);
//         }
//     }
//
//     @Benchmark
//     public void testConvertsConvertOneBean2() {
//         for (int i = 0; i < dataSize; ++i) {
//             Converts.withGenericMapstruct().toBean(STUDENT_DO, StudentVO.class);
//         }
//     }
//
//     @Benchmark
//     public void testConvertsConvertTwoBean() {
//         for (int i = 0; i < dataSize; ++i) {
//             Converts.withGenericMapstruct().toBean(STUDENT_DO, GRADE_DO1, StudentVO.class);
//         }
//     }
//
//     @Benchmark
//     public void testConvertsConvertThreeBean() {
//         for (int i = 0; i < dataSize; ++i) {
//             Converts.withGenericMapstruct().toBean(STUDENT_DO, GRADE_DO1, GRADE_DO2, StudentVO.class);
//         }
//     }
//
//     @Benchmark
//     public void testConvertsConvertListBean() {
//         for (int i = 0; i < dataSize; ++i) {
//             Converts.withGenericMapstruct().toBeans(STUDENT_DOS, StudentVO.class);
//         }
//     }
//
//     @Benchmark
//     public void testSpringBeanUtilsConvertBean() {
//         for (int i = 0; i < dataSize; ++i) {
//             BeanUtils.copyProperties(STUDENT_DO, StudentVO.class);
//         }
//     }
//
//     @Benchmark
//     public void testHutoolBeanUtilConvertBean() {
//         for (int i = 0; i < dataSize; ++i) {
//             BeanUtil.copyProperties(STUDENT_DO, StudentVO.class);
//         }
//     }
//
//     public static void main(String[] args) throws RunnerException {
//         new Runner(
//                 new OptionsBuilder()
//                         .include(ConvertAllBenchmarkTest.class.getSimpleName())
//                         .build()
//         ).run();
//     }
//
// }