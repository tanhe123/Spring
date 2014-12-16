package net.xiayule.spring.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

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

    /**
     * 给定的字符串 Tokenize 成一个字符数组
     * 会自动进行trim并且会忽略结果中空的字符串
     * @param str 给定的字符串
     * @param delimiters 分割字符，是一个字符串，即多个分隔符的组合，每个分隔符都会起作用
     * @return 被分割后的字符串数组， 如果传入的String为 {@code null}, 则返回 {@code null}
     * @see #tokenizeToStringArray(String, String, boolean, boolean)
     */
    public static String[] tokenizeToStringArray(String str, String delimiters) {
        return tokenizeToStringArray(str, delimiters, true, true);
    }

    /**
     * 给定的字符串 Tokenize 成一个字符数组
     * @param str 给定的字符串
     * @param delimiters 分割字符，是一个字符串，即多个分隔符的组合，每个分隔符都会起作用
     * @param trimTokens trim 字符串
     * @param ignoreEmptyTokens 如果被分割的字符串是空的，则忽略{如果开启了 trimtokens, 则会先进行 trim, 然后判断是否为空}
     *                          空的含义是字符串长度为0
     * @return 被分割后的字符串数组， 如果传入的String为 {@code null}, 则返回 {@code null}
     * @see java.util.StringTokenizer
     * @see String#trim()
     *
     */
    public static String[] tokenizeToStringArray(
            String str, String delimiters, boolean trimTokens, boolean ignoreEmptyTokens) {

        if (str == null) {
            return null;
        }
        StringTokenizer st = new StringTokenizer(str, delimiters);
        List<String> tokens = new ArrayList<String>();
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (trimTokens) {
                token = token.trim();
            }
            if (!ignoreEmptyTokens || token.length() > 0) {
                tokens.add(token);
            }
        }
        return toStringArray(tokens);
    }

    /**
     * 将给定的集合元素复制到数组
     * 集合元素中的元素只能为String类型
     * @param collection 要复制的集合 (可为 @{code null})
     * @return 字符数组，如果集合为 {@code null} 返回 {@code null}
     */
    public static String[] toStringArray(Collection<String> collection) {
        if (collection == null) {
            return null;
        }
        return collection.toArray(new String[collection.size()]);
    }
}
