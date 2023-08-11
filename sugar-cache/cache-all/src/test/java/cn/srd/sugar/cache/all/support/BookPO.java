package cn.srd.sugar.cache.all.support;

import cn.srd.sugar.tool.lang.core.RandomsUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
public class BookPO implements Serializable {

    @Serial private static final long serialVersionUID = 5858155802780007051L;

    private Long id;

    private String name;

    private String author;

    public static BookPO build(Long id) {
        return BookPO.builder()
                .id(id)
                .name("bookName" + RandomsUtil.randomNumber())
                .build();
    }

}
