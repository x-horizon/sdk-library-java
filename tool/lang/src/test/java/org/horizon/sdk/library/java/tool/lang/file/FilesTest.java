package org.horizon.sdk.library.java.tool.lang.file;

import org.horizon.sdk.library.java.contract.constant.storage.StorageUnitType;
import org.horizon.sdk.library.java.tool.lang.object.Nil;

import java.util.List;

/**
 * @author wjm
 * @since 2025-05-31 02:01
 */
public class FilesTest {

    public static void main(String[] args) {
        System.out.println(formatFileTree(Files.listFiles("/myPath", StorageUnitType.MB), StorageUnitType.MB));
    }

    public static String formatFileTree(List<FileDTO> fileDTOs, StorageUnitType storageUnitType) {
        StringBuilder stringBuilder = new StringBuilder();
        fileDTOs.forEach(file -> buildTreeOutput(stringBuilder, file, storageUnitType, 0));
        return stringBuilder.toString();
    }

    private static void buildTreeOutput(StringBuilder stringBuilder, FileDTO fileDTO, StorageUnitType storageUnitType, int depth) {
        if (Nil.isNull(fileDTO.getExtensionType())) {
            // Directory
            String indent = "    ".repeat(depth);
            stringBuilder.append(indent)
                    .append(fileDTO.getName())
                    .append(" | ")
                    .append(fileDTO.getSize() * (fileDTO.getStorageUnitType().toSize(1) / storageUnitType.toSize(1)))
                    .append(" ")
                    .append(storageUnitType)
                    .append("\n");
            fileDTO.getChildren().forEach(child -> buildTreeOutput(stringBuilder, child, storageUnitType, depth + 1));
        } else {
            // File
            String indent = "    ".repeat(depth + 1);
            stringBuilder.append(indent)
                    .append(fileDTO.getName())
                    .append(".")
                    .append(fileDTO.getExtensionType().getValue())
                    .append(" | ")
                    .append(fileDTO.getSize() * (fileDTO.getStorageUnitType().toSize(1) / storageUnitType.toSize(1)))
                    .append(" ")
                    .append(storageUnitType)
                    .append("\n");
        }
    }

}