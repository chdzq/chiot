package org.chdzq.common.core.utils;

import org.chdzq.common.core.constants.CharConstant;

/**
 * 文件工具类
 *
 * @author chdzq
 * @create 2025/3/7 10:13
 */
public class FileUtil {

    private FileUtil() {
    }

    private static final CharSequence[] SPECIAL_SUFFIX = {"tar.bz2", "tar.Z", "tar.gz", "tar.xz"};
    /**
     * 类Unix路径分隔符
     */
    public static final char UNIX_SEPARATOR = CharConstant.SLASH;
    /**
     * Windows路径分隔符
     */
    public static final char WINDOWS_SEPARATOR = CharConstant.BACKSLASH;

    /**
     * 获得文件的扩展名（后缀名），扩展名不带“.”
     *
     * @param fileName 文件名
     * @return 扩展名
     */
    public static String getSuffix(String fileName) {
        if (fileName == null) {
            return StringUtil.EMPTY;
        }
        final int index = fileName.lastIndexOf(CharConstant.DOT);
        if (index == -1) {
            return StringUtil.EMPTY;
        } else {
            // issue#I4W5FS@Gitee
            final int secondToLastIndex = fileName.substring(0, index).lastIndexOf(CharConstant.DOT);
            final String substr = fileName.substring(secondToLastIndex == -1 ? index : secondToLastIndex + 1);
            if (StringUtil.containsAny(substr, SPECIAL_SUFFIX)) {
                return substr;
            }

            final String ext = fileName.substring(index + 1);
            // 扩展名中不能包含路径相关的符号
            return StringUtil.containsAny(ext, UNIX_SEPARATOR, WINDOWS_SEPARATOR) ? StringUtil.EMPTY : ext;
        }
    }
}
