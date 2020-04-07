package com.starry.leetcode;

import java.util.Stack;

/**
 * @Description 有效的括号
 * @Auther: https://github.com/starry-eyed-art
 * @Date: 2020/4/4 11:51 下午
 */
public class NO20 {

    public boolean isValid(String s) {
        return useStack(s);

    }

    // 暴力拆解
    private boolean notUseStack(String s) {
        // 边界处理
        if (s == null || s.trim().length() == 0) {
            return true;
        }

        while (s.contains("()") || s.contains("[]") || s.contains("{}")) {
            s = s.replaceAll("\\(\\)", "");
            s = s.replaceAll("\\[\\]", "");
            s = s.replaceAll("\\{\\}", "");
            if (s.trim().length() == 0) {
                return true;
            }
        }
        return false;
    }


    // 利用栈破解
    private boolean useStack(String s) {
        // 边界处理
        if (s == null || s.trim().length() == 0) {
            return true;
        }

        char[] array = s.toCharArray();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < array.length; i++) {
            char c = array[i];
            if (c == '{' || c == '(' || c == '[') {
                stack.push(c);
                continue;
            }
            if (c == '}' || c == ')' || c == ']') {
                if (stack.empty()) {
                    return false;
                }
                char pop = stack.pop();
                if (c == '}' && pop != '{') {
                    return false;
                }
                if (c == ']' && pop != '[') {
                    return false;
                }
                if (c == ')' && pop != '(') {
                    return false;
                }
            }
        }

        if (stack.empty()) {
            return true;
        }

        return false;
    }
}
