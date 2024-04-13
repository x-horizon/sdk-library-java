package cn.srd.library.java.orm.mybatis.flex.postgresql.model.po;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@Table(value = "people")
public class PeoplePO extends BaseWithVersionPO {

    @Serial private static final long serialVersionUID = -2406926081842847637L;

    @Id
    @Column(value = "id")
    private Long id;

    @Column(value = "home_id")
    private Long homeId;

    @Column(value = "name1")
    private String name1;

    @Column(value = "name2")
    private String name2;

    @Column(value = "name3")
    private String name3;

    @Column(value = "name4")
    private String name4;

    @Column(value = "name5")
    private String name5;

    @Column(value = "name6")
    private String name6;

    @Column(value = "name7")
    private String name7;

    public PeoplePO setName(String name) {
        this.name1 = name;
        this.name2 = name;
        this.name3 = name;
        this.name4 = name;
        this.name5 = name;
        this.name6 = name;
        this.name7 = name;
        return this;
    }

}