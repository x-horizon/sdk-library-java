package cn.srd.library.java.orm.mybatis.flex.demo.school.model.po;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.test.id.IdGenerateType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@Table(value = "school_book")
public class BookPO extends BasePO implements Serializable {

    @Serial private static final long serialVersionUID = -5049511887330150509L;

    @Id(keyType = KeyType.Generator, value = IdGenerateType.UUID_GENERATOR_NAME)
    @Column(value = "book_id")
    private String id;

    @Column(value = "student_id")
    private Long studentId;

    @Column(value = "book_name")
    private String name;

}
