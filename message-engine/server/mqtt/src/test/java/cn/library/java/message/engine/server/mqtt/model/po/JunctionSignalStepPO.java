package cn.library.java.message.engine.server.mqtt.model.po;

import cn.library.java.contract.model.base.PO;
import cn.library.java.contract.model.base.VO;
import cn.library.java.doc.knife4j.contract.constant.ApiDocConstant;
import cn.library.java.message.engine.server.mqtt.model.bo.JunctionSignalStepBO;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Table;
import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

/**
 * @author wjm
 * @since 2025-01-12 19:10
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@Table(value = "junction_signal_step")
@AutoMappers({@AutoMapper(target = JunctionSignalStepBO.class)})
public class JunctionSignalStepPO implements PO {

    @Serial private static final long serialVersionUID = -2273663977626557098L;

    @Override
    public VO toVO() {
        return null;
    }

    @Schema(description = "info", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.NUMBER)
    @Column(value = "info")
    private Long info;

}