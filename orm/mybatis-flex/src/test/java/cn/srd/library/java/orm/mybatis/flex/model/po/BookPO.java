package cn.srd.library.java.orm.mybatis.flex.model.po;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
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

    @Id
    // @Id(keyType = KeyType.Generator, value = KeyGenerators.uuid)
    // @Id(keyType = KeyType.Auto)
    @Column(value = "book_id")
    private String id;

    @Column(value = "student_id")
    private Long studentId;

    @Column(value = "book_name")
    private String name;

}
