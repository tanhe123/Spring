package net.xiayule.spring.util;

/**
 * Created by tan on 14-12-16.
 */
public class StringUtils {

    /**
     * 检测给定的字符串是否包含文本内容（空白字符不算)
     * @param str 要检测的字符串
     * @return 只有当该字符串不为空、长度大于0且包含至少一个非空白字符时才会返回 {@code true}
     * @see #hasText(CharSequence)
     */
    public static boolean hasText(String str) {
        return hasText((CharSequence) str);
    }

    /**
     * 检测给定的字符串是否包含文本内容（空白字符不算)
     * <p><pre class="code">
     * StringUtils.hasText(null) = false
     * StringUtils.hasText("") = false
     * StringUtils.hasText(" ") = false
     * StringUtils.hasText("12345") = true
     * StringUtils.hasText(" 12345 ") = true
     * </pre>
     * @param str 要检查的 CharSequence (可能为 {@code null})
     * @return 只有当该字符串不为空、长度大于0且包含至少一个非空白字符时才会返回 {@code true}
     * @see java.lang.Character#isWhitespace(char)
     */
    public static boolean hasText(CharSequence str) {
        if (!hasLength(str)) {
            return false;
        }
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检测给定的字符串是否是 {@code null} 或者 长度为 0
     * 即使该字符串全为空白字符，也会返回 {@code true}
     * <p><pre class="code">
     * StringUtils.hasLength(null) = false
     * StringUtils.hasLength("") = false
     * StringUtils.hasLength(" ") = true
     * StringUtils.hasLength("Hello") = true
     * </pre>
     * @param str 需要检测的 CharSequence (可能为 {@code null} )
     * @return 如果非空且有长度，将会返回 {@code true}
     * @see #hasText(String)
     */
    public static boolean hasLength(CharSequence str) {
        return (str != null && str.length() > 0);
    }

    /**
     * 检测给定的字符串是否是 {@code null} 或者 长度为 0
     * 即使该字符串全为空白字符，也会返回 {@code true}
     * @param str 需要检测的 String (可能为 {@code null} )
     * @return 如果非空且有长度，将会返回 {@code true}
     * @see #hasLength(String)
     */
    public static boolean hasLength(String str) {
        return hasLength((CharSequence) str);
    }
}
