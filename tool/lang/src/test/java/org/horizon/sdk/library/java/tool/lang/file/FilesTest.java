package org.horizon.sdk.library.java.tool.lang.file;

import org.horizon.sdk.library.java.contract.constant.storage.StorageUnitType;
import org.horizon.sdk.library.java.tool.lang.object.Nil;
import org.horizon.sdk.library.java.tool.lang.text.Strings;

import java.util.Comparator;
import java.util.List;

/**
 * @author wjm
 * @since 2025-05-31 02:01
 */
public class FilesTest {

    private static final String[] FILTER_FILE_NAMES = {};

    public static void main(String[] args) {
        List<FileDTO> fileDTOs = Files.listFiles("/myPath", StorageUnitType.MB);
        filterAndSortFileByName(fileDTOs, FILTER_FILE_NAMES);
        System.out.println(formatFileTree(fileDTOs, StorageUnitType.MB));
    }

    public static void filterAndSortFileByName(List<FileDTO> fileDTOs, String... filterNames) {
        fileDTOs.removeIf(fileDTO -> {
            if (Nil.isNotEmpty(fileDTO.getChildren())) {
                fileDTO.getChildren().sort(Comparator.comparing(FileDTO::getName));
                filterAndSortFileByName(fileDTO.getChildren(), filterNames);
            }
            String fullFileName = Nil.isNull(fileDTO.getExtensionType()) ? fileDTO.getName() : fileDTO.getName() + "." + fileDTO.getExtensionType().getValue();
            return Strings.containsAny(fullFileName, filterNames);
        });
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