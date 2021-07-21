package org.example;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StrPracticeUtil {
    /**
     * 344. 反转字符串
     * @param s
     */
    public static void reverseString(char[] s) {
        // 空间复杂度O（1)反转
        for (int i = 0; i < s.length/2; i++) {
            char tmp = s[i];
            s[i] = s[s.length-1-i];
            s[s.length-1-i] = tmp;
        }
        return;
    }

    /**
     * 387. 字符串中的第一个唯一字符
     * @param s
     * @return
     */
    public static int firstUniqChar(String s) {
        // 暴力循环
//        for (int i = 0; i < s.length(); i++) {
//            char c = s.charAt(i);
//            boolean flag = true;
//            for (int j = 0; j < s.length(); j++) {
//                if(c==s.charAt(j)&&i!=j){ flag=false; break;}
//            }
//            if (flag) return i;
//        }
//        return -1;

        // 备忘录处理
//        Set set = new HashSet();
//        for (int i = 0; i < s.length(); i++) {
//            char c = s.charAt(i);
//            if(set.contains(c)) continue;
//            boolean flag = true;
//            for (int j = 0; j < s.length(); j++) {
//                if(c==s.charAt(j)&&i!=j){ flag=false; break;}
//            }
//            if (flag) return i;
//            else set.add(c);
//        }
//        return -1;

        // hash表处理
        Map<Character, Integer> map = new HashMap();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(!map.containsKey(c)) map.put(c, 1);
            else map.put(c, map.get(c)+1);
        }
        for (int i = 0; i < s.length(); i++) {
            if(map.get(s.charAt(i)) == 1) return i;
        }
        return -1;
    }

    /**
     * 58. 最后一个单词的长度
     * @param s
     * @return
     */
    public static int lengthOfLastWord(String s) {
        int len = 0;
        boolean flag = true;
        for (int i = s.length()-1; i > -1; i--) {
            if(s.charAt(i)== ' ' && flag) continue;
            if(s.charAt(i)!= ' ') flag = false;
            if(s.charAt(i)==' ') return len;
            len++;
        }
        return len;
    }

    /**
     * kmp匹配
     * @param target 目标串
     * @param sub 子串
     * @return
     */
    public static int kmp(String target, String sub){
        int l1 = target.length();
        int l2 = sub.length();
        int i = 0, j = 0;
        while (i<l1 && j<l2){
            if(j==-1||target.charAt(i)==sub.charAt(j)){
                i++;j++;
            } else {
                j++;
            }
        }
        if(j==l2){
            return i-j;
        }
        return -1;
    }

}
