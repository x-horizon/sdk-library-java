package org.horizon.library.java.tool.convert.api.benchmark;

import org.horizon.library.java.tool.convert.api.Converts;
import org.horizon.library.java.tool.convert.api.mapstruct.model.domain.GradeDO;
import org.horizon.library.java.tool.convert.api.mapstruct.model.domain.StudentDO;
import org.horizon.library.java.tool.convert.jackson.Jacksons;
import com.google.common.collect.ImmutableMap;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 基准性能测试
 * <pre>
 * &#064;BenchmarkMode
 *       JMH 进行 Benchmark 时所使用的模式，可以用在类 / 方法上；
 *          Throughput: 吞吐量，统计单位时间内可以对方法测试多少次，例如 “1秒内可以执行多少次调用”，单位：操作数/时间；
 *          AverageTime: 调用的平均响应时间，例如 “每次调用平均耗时 xxx 毫秒”，单位：时间/操作数；
 *          SampleTime: 随机取样，最后输出取样结果的分布，统计每个响应时间范围内的响应次数，比如 “0-1ms，3次；1-2ms，5次；99% 的调用在 xxx 毫秒以内，99.99% 的调用在 xxx 毫秒以内”；
 *          SingleShotTime: 跳过预热阶段，只进行一次测试（以上三种模式都不只有一次），一般与 @WarmUp(0) 共用，用于测试冷启动时的性能；
 *
 * &#064;Iteration：JMH 进行测试的最小单位，与 @BenchmarkMode 的 Throughput、AverageTime、SampleTime 配合使用，一次 iteration 代表的是一秒；
 *
 * &#064;Benchmark：表示该方法是需要进行 benchmark 的对象，可以用在方法上；
 *
 * &#064;Fork：例如 1，表示开启一个线程进行测试；
 *
 * &#064;Measurement：例如 5，表示进行 5 次测试，可以用在类 / 方法上；
 *
 * &#064;WarmUp：例如 3，表示测试前进行三次预热执行，可以用在类 / 方法上；
 *
 * &#064;OutputTimeUnit：输出的时间单位；
 *
 * &#064;Setup：执行所有 @Benchmark 前执行，主要用于初始化，可以用在方法上；
 *
 * &#064;TearDown：执行完所有 @Benchmark 后执行，主要用于资源的回收等，可以用在方法上；
 *
 * &#064;Setup/@TearDown 注解使用 Level 参数来指定何时调用，
 *          Level.Trial：默认level，执行所有 @Benchmark 前 / 后 执行
 *          Level.Iteration：一次迭代前 / 后
 *          Level.Invocation：每个方法调用前 / 后
 *
 * &#064;State
 *          Scope.Thread：默认的 State，每个测试线程分配一个实例；
 *          Scope.Benchmark：所有测试线程共享一个实例，用于测试有状态实例在多线程共享下的性能；
 *          Scope.Group：每个线程组共享一个实例；
 *
 * &#064;Param：
 *        可以用于指定某项参数的多种情况，适合用于测试一个函数在不同的参数输入的情况下的性能，可以用在成员变量上；
 *        多个 @Param 注解的成员之间是乘积关系，比如有两个用 @Param 注解的字段，第一个字段有 5 个值，第二个字段有 2 个值，则每个测试方法会跑 5*2 = 10 次；
 * </pre>
 *
 * @author wjm
 * @since 2021-05-01 14:13
 */
@Fork(1)
@State(Scope.Thread)
@Warmup(iterations = 0)
@Measurement(iterations = 3)
@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class JacksonConvertsBenchmarkTest {

    @Param({"1000000"})
    // @Param({"1000", "10000", "100000", "1000000"})
    private int dataSize;

    private static final StudentDO STUDENT_DO = StudentDO.newDO();

    private static final List<StudentDO> STUDENT_DOS = StudentDO.newDOs();

    private static final GradeDO GRADE_DO = GradeDO.newDO();

    private static final List<GradeDO> GRADE_DOS = GradeDO.newDOs();

    private static final Map<String, GradeDO> STRING_MAPPING_GRADE_DO_MAP = ImmutableMap.of("Grade", GRADE_DO);

    private static final Map<String, List<GradeDO>> STRING_MAPPING_GRADE_DOS_MAP = ImmutableMap.of("Grade", GRADE_DOS);

    private static final String GRADE_DO_JSON = Converts.onJackson().toString(GRADE_DO);

    private static final String GRADE_DOS_JSON_ARRAY = Converts.onJackson().toString(GRADE_DOS);

    private static final String STRING_MAPPING_GRADE_DO_MAP_JSON = Converts.onJackson().toString(STRING_MAPPING_GRADE_DO_MAP);

    private static final String STRING_MAPPING_GRADE_DOS_MAP_JSON = Converts.onJackson().toString(STRING_MAPPING_GRADE_DOS_MAP);

    @Benchmark
    public void testStringToBeanWithToBean() {
        for (int i = 1; i <= dataSize; ++i) {
            Converts.onJackson().toBean(GRADE_DO_JSON, GradeDO.class);
        }
    }

    @Benchmark
    public void testStringToBeanWithToAnything() {
        for (int i = 1; i <= dataSize; ++i) {
            Converts.onJackson().toAnything(GRADE_DO_JSON, Jacksons.<GradeDO>newTypeReference());
        }
    }

    @Benchmark
    public void testStringToBeansWithToBean() {
        for (int i = 1; i <= dataSize; ++i) {
            Converts.onJackson().toBeans(GRADE_DOS_JSON_ARRAY, GradeDO.class);
        }
    }

    @Benchmark
    public void testStringToBeansWithToAnything() {
        for (int i = 1; i <= dataSize; ++i) {
            Converts.onJackson().toAnything(GRADE_DOS_JSON_ARRAY, Jacksons.<List<GradeDO>>newTypeReference());
        }
    }

    @Benchmark
    public void testStringToMapWithToBean() {
        for (int i = 1; i <= dataSize; ++i) {
            Converts.onJackson().toMap(STRING_MAPPING_GRADE_DO_MAP_JSON, GradeDO.class);
        }
    }

    @Benchmark
    public void testStringToMapWithToAnything() {
        for (int i = 1; i <= dataSize; ++i) {
            Converts.onJackson().toAnything(STRING_MAPPING_GRADE_DO_MAP_JSON, Jacksons.<Map<String, GradeDO>>newTypeReference());
        }
    }

    @Benchmark
    public void testStringToMapsWithToBean() {
        for (int i = 1; i <= dataSize; ++i) {
            Converts.onJackson().toMaps(STRING_MAPPING_GRADE_DOS_MAP_JSON, GradeDO.class);
        }
    }

    @Benchmark
    public void testStringToMapsWithToAnything() {
        for (int i = 1; i <= dataSize; ++i) {
            Converts.onJackson().toAnything(STRING_MAPPING_GRADE_DOS_MAP_JSON, Jacksons.<Map<String, List<GradeDO>>>newTypeReference());
        }
    }

    public static void main(String[] args) throws RunnerException {
        new Runner(
                new OptionsBuilder()
                        .include(JacksonConvertsBenchmarkTest.class.getSimpleName())
                        .build()
        ).run();
    }

}