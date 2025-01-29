package cn.library.java.tool.lang.file;

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
     * <pre>
     * get the file extension type.
     * example:
     *    1. the input is null or blank, return empty string.
     *    2. the input has no ".", return empty string.
     *    3. the input first char is ".", return the file name.
     *    4. return remain char after the last ".".
     * </pre>
     *
     * @param fileName the file name
     * @return the file extension type
     */

    public static String getExtension(String fileName) {
        return com.google.common.io.Files.getFileExtension(fileName);
    }

}