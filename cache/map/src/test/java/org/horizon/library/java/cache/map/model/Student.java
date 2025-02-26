package org.horizon.library.java.cache.map.model;

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
public class Student implements Serializable {

    @Serial private static final long serialVersionUID = 2515268244821714169L;

    private Integer id;

    private String name;

}