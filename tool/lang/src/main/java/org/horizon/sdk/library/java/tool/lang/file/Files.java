package org.horizon.sdk.library.java.tool.lang.file;

import com.vip.vjtools.vjkit.io.FilePathUtil;
import lombok.AccessLevel;
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.dromara.hutool.core.io.file.FileUtil;
import org.horizon.sdk.library.java.contract.constant.file.FileExtensionType;
import org.horizon.sdk.library.java.contract.constant.storage.StorageUnitType;
import org.horizon.sdk.library.java.contract.constant.text.SymbolConstant;
import org.horizon.sdk.library.java.tool.lang.collection.Collections;
import org.horizon.sdk.library.java.tool.lang.convert.Converts;
import org.horizon.sdk.library.java.tool.lang.object.Nil;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * toolkit for file
 *
 * @author wjm
 * @since 2020-04-02 17:52
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Files {

    /**
     * see {@link FileUtil#file(String)}
     *
     * @param path the absolute path or relative class path
     * @return {@link File}
     */
    public static File of(String path) {
        return FileUtil.file(path);
    }

    /**
     * return true if the specified file path exist
     *
     * @param path the specified file
     * @return return true if the specified file path exist
     */
    public static boolean exist(String path) {
        return FileUtil.exists(path);
    }

    /**
     * return true if the specified file path exist
     *
     * @param path the specified file
     * @return return true if the specified file path exist
     */
    public static boolean exist(Path path) {
        return java.nio.file.Files.exists(path);
    }

    /**
     * return true if the specified file exist
     *
     * @param input the specified file
     * @return return true if the specified file exist
     */
    public static boolean exist(File input) {
        return FileUtil.exists(input);
    }

    public static boolean isRegularFile(String path) {
        return isRegularFile(Path.of(path));
    }

    public static boolean isRegularFile(Path path) {
        return java.nio.file.Files.isRegularFile(path);
    }

    public static boolean isDirectory(String path) {
        return isDirectory(Path.of(path));
    }

    public static boolean isDirectory(Path path) {
        return java.nio.file.Files.isDirectory(path);
    }

    /**
     * return true if the specified file path not exist
     *
     * @param path the specified file
     * @return return true if the specified file path not exist
     */
    public static boolean notExist(String path) {
        return !exist(path);
    }

    /**
     * return true if the specified file path not exist
     *
     * @param path the specified file
     * @return return true if the specified file path not exist
     */
    public static boolean notExist(Path path) {
        return !exist(path);
    }

    /**
     * return true if the specified file not exist
     *
     * @param input the specified file
     * @return return true if the specified file not exist
     */
    public static boolean notExist(File input) {
        return !exist(input);
    }

    public static boolean isNotRegularFile(String path) {
        return !isRegularFile(path);
    }

    public static boolean isNotRegularFile(Path path) {
        return !isRegularFile(path);
    }

    public static boolean isNotDirectory(String path) {
        return !isDirectory(path);
    }

    public static boolean isNotDirectory(Path path) {
        return !isDirectory(path);
    }

    public static String getName(Path path) {
        return Nil.isNull(path) ? SymbolConstant.EMPTY : path.getFileName().toString();
    }

    public static String getNameWithoutExtension(String fileName) {
        return com.google.common.io.Files.getNameWithoutExtension(fileName);
    }

    /**
     * <p>get the file extension type based on the following rules:</p>
     *
     * <ol>
     *  <li>if input is {@code null} or blank → returns empty string</li>
     *  <li>if input contains no {@code "."} → returns empty string</li>
     *  <li>if input starts with {@code "."} → returns entire filename</li>
     *  <li>otherwise → returns substring after last {@code "."}</li>
     * </ol>
     *
     * <p>example results:</p>
     * <pre>{@code
     * getFileExtension(null)       → ""
     * getFileExtension("")         → ""
     * getFileExtension("file")     → ""
     * getFileExtension(".gitignore") → ".gitignore"
     * getFileExtension("file.txt") → "txt"
     * getFileExtension("file.tar.gz") → "gz"
     * }</pre>
     *
     * @param fileName the file name to analyze (may contain path information)
     * @return the file extension in lowercase, or empty string if no valid extension
     */
    public static String getExtension(String fileName) {
        return com.google.common.io.Files.getFileExtension(fileName);
    }

    public static long getSizeByte(String path) {
        return getSizeByte(Path.of(path));
    }

    @SneakyThrows
    public static long getSizeByte(Path path) {
        return java.nio.file.Files.size(path);
    }

    /**
     * see {@link FilePathUtil#concat(String, String...)}
     *
     * @param basePath    the base path
     * @param concatPaths the concat paths
     * @return the concat path
     */
    public static String concatPath(String basePath, String... concatPaths) {
        return FilePathUtil.concat(basePath, concatPaths);
    }

    /**
     * see {@link FileUtil#del(File)}
     *
     * @param input the specified file
     */
    public static void delete(File input) {
        FileUtil.del(input);
    }

    public static List<FileDTO> listFiles(String absolutePath) {
        return listFiles(absolutePath, StorageUnitType.BYTE);
    }

    @SneakyThrows
    public static List<FileDTO> listFiles(String absolutePath, StorageUnitType storageUnitType) {
        Path path = Path.of(absolutePath);
        if (isRegularFile(path)) {
            return Collections.ofArrayList(buildRegularFileDTO(path, storageUnitType));
        } else if (isDirectory(path)) {
            @Cleanup Stream<Path> childAbsolutePaths = java.nio.file.Files.list(path);
            return childAbsolutePaths
                    .filter(Files::exist)
                    .map(childAbsolutePath -> isDirectory(childAbsolutePath)
                            ? buildDirectoryFileDTO(childAbsolutePath, storageUnitType)
                            : buildRegularFileDTO(childAbsolutePath, storageUnitType)
                    )
                    .collect(Collectors.toList());
        }
        return Collections.newArrayList();
    }

    @SneakyThrows
    private static FileDTO buildRegularFileDTO(Path regularFilePath, StorageUnitType storageUnitType) {
        String regularFileNameWithExtension = getName(regularFilePath);
        return FileDTO.builder()
                .name(getNameWithoutExtension(regularFileNameWithExtension))
                .extensionType(Converts.toEnumByValue(getExtension(regularFileNameWithExtension), FileExtensionType.class))
                .size(storageUnitType.toSize(getSizeByte(regularFilePath)))
                .storageUnitType(storageUnitType)
                .children(Collections.newArrayList())
                .build();
    }

    @SneakyThrows
    private static FileDTO buildDirectoryFileDTO(Path directoryFilePath, StorageUnitType storageUnitType) {
        @Cleanup Stream<Path> childAbsolutePaths = java.nio.file.Files.list(directoryFilePath);
        List<FileDTO> childFileDTOs = childAbsolutePaths
                .filter(Files::exist)
                .map(path -> isDirectory(path)
                        ? buildDirectoryFileDTO(path, storageUnitType)
                        : buildRegularFileDTO(path, storageUnitType)
                )
                .collect(Collectors.toList());
        return FileDTO.builder()
                .name(getName(directoryFilePath))
                .extensionType(null)
                .size(storageUnitType.toSize(calculateTotalBytes(childFileDTOs)))
                .storageUnitType(storageUnitType)
                .children(childFileDTOs)
                .build();
    }

    private static long calculateTotalBytes(List<FileDTO> fileDTOs) {
        return fileDTOs.stream()
                .mapToLong(fileDTO -> Nil.isEmpty(fileDTO.getChildren()) ?
                        (long) (fileDTO.getSize() / fileDTO.getStorageUnitType().toSize(1)) :
                        calculateTotalBytes(fileDTO.getChildren()))
                .sum();
    }

}