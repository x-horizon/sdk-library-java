package org.horizon.sdk.library.java.tool.lang.file;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.horizon.sdk.library.java.contract.constant.file.FileExtensionType;
import org.horizon.sdk.library.java.contract.constant.storage.StorageUnitType;
import org.horizon.sdk.library.java.contract.model.base.POJO;

import java.io.Serial;
import java.util.List;

/**
 * file model
 *
 * @author wjm
 * @since 2025-05-30 22:15
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
public class FileDTO implements POJO {

    @Serial private static final long serialVersionUID = 2213812979019287491L;

    private String name;

    private FileExtensionType extensionType;

    private double size;

    private StorageUnitType storageUnitType;

    private List<FileDTO> children;

}