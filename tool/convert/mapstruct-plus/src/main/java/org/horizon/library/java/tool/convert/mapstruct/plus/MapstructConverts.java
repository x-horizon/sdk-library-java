package org.horizon.library.java.tool.convert.mapstruct.plus;

import org.horizon.library.java.contract.constant.suppress.SuppressWarningConstant;
import org.horizon.library.java.tool.spring.contract.support.Springs;
import io.github.linpeilie.Converter;
import jakarta.annotation.PostConstruct;
import lombok.Getter;

import java.util.List;

/**
 * mapstruct converter
 *
 * @author wjm
 * @since 2021-05-01 14:13
 */
public class MapstructConverts {

    @Getter private static MapstructConverts instance = null;

    private static Converter internalConverter = null;

    @SuppressWarnings(SuppressWarningConstant.ALL)
    @PostConstruct
    public void initialize() {
        instance = this;
        internalConverter = Springs.getBean(Converter.class);
    }

    public <S, R> R toBean(S source, Class<R> targetClass) {
        return internalConverter.convert(source, targetClass);
    }

    public <S, R> List<R> toBeans(List<S> source, Class<R> targetClass) {
        return internalConverter.convert(source, targetClass);
    }

}