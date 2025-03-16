package org.horizon.sdk.library.java.tool.lang.file;

import com.vip.vjtools.vjkit.io.FilePathUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.dromara.hutool.core.io.file.FileUtil;

import java.io.File;

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
     * return true if the specified file exist
     *
     * @param input the specified file
     * @return return true if the specified file exist
     */
    public static boolean exist(File input) {
        return FileUtil.exists(input);
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

    /**
     * see {@link FileUtil#del(File)}
     *
     * @param input the specified file
     */
    public static void delete(File input) {
        FileUtil.del(input);
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

}