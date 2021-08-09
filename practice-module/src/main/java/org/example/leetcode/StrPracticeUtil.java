package org.example.leetcode;

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

    /**
     * 44. 通配符匹配
     * 字符串通配匹配
     * *任意字符串
     * ？单个字符
     * @param s
     * @param p
     * @return
     */
    public static boolean isMatch(String s, String p){
        // DP，状态转移矩阵
//        final int m = s.length(),n = p.length();
//        boolean[][] dp = new boolean[m+1][n+1];
//        dp[0][0] = true;
//        for (int i = 1; i <= n; i++) {
//            if(p.charAt(i-1)=='*') dp[0][i] = true;
//            else break;
//        }
//        for (int i = 1; i < dp.length; i++) {
//            for (int j = 1; j < dp[i].length; j++) {
//                if (p.charAt(j - 1) == '*') {
//                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
//                } else if (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1)) {
//                    dp[i][j] = dp[i - 1][j - 1];
//                }
//            }
//        }
//        return dp[m][n];

        // 贪心算法
        // 先从尾部匹配，剩下的模式串就是以*结尾，或者没有*了
        // 没有*直接匹配即可，如果前串*结尾，则使用从头匹配到right
        int sRight = s.length(), pRight = p.length();
        while (sRight > 0 && pRight > 0 && p.charAt(pRight - 1) != '*') {
            if (charMatch(s.charAt(sRight - 1), p.charAt(pRight - 1))) {
                --sRight;
                --pRight;
            } else {
                return false;
            }
        }
        // 如果魔偶是串最终到0，则都归0处理
        if (pRight == 0) {
            return sRight == 0;
        }
        // 匹配到idx
        int sIndex = 0, pIndex = 0;
        // 匹配到*时记录
        int sRecord = -1, pRecord = -1;

        while (sIndex < sRight && pIndex < pRight) {
            if (p.charAt(pIndex) == '*') {
                // 匹配到*模式串，跳过，记录后续位置
                ++pIndex;
                sRecord = sIndex;
                pRecord = pIndex;
            } else if (charMatch(s.charAt(sIndex), p.charAt(pIndex))) {
                // 正常匹配，匹配位置后移
                ++sIndex;
                ++pIndex;
            } else if (sRecord != -1 && sRecord + 1 < sRight) {
                // 如果记录位置没到未匹配尾部，同时记录过sRecord，即匹配到过*
                // 且不匹配，那么srecord往后移，重新记录si和pi
                ++sRecord;
                sIndex = sRecord;
                pIndex = pRecord;
            } else {
                return false;
            }
        }

        return allStars(p, pIndex, pRight);
    }
    public static boolean allStars(String str, int left, int right) {
        for (int i = left; i < right; ++i) {
            if (str.charAt(i) != '*') {
                return false;
            }
        }
        return true;
    }

    public static boolean charMatch(char u, char v) {
        return u == v || v == '?';
    }

    /**
     * 5. 最长回文子串
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        // dp,状态转移矩阵
//        final int len = s.length();
//        if(len<2) return s;
//        int maxLen = 1;
//        int begin = 0;
//        boolean[][] dp = new boolean[len][len];
//        for (int i = 0; i < len; i++)  dp[i][i] = true;
//        char[] charArray = s.toCharArray();
//        for (int l = 2; l < len; l++) {
//            for (int i = 0; i < len; i++) {
//                int j = l + i - 1;
//                if(j >= len) break;
//                if(charArray[i]!=charArray[j]){
//                    dp[i][j] = false;
//                } else {
//                    if(charArray[i]==charArray[j]) dp[i][j] = dp[i+1][j-1];
//                    else if(j - i <3) dp[i][j] = true;
//                }
//                if (dp[i][j] && j - i + 1 > maxLen) {
//                    maxLen = j - i + 1;
//                    begin = i;
//                }
//            }
//        }
//        return s.substring(begin, begin + maxLen);

        // 中心扩散
        if(s==null&&s.length()<2) return s;
        char[] cAry = s.toCharArray();
        int len = cAry.length;
        int maxLen = 1,cur=0, mid = 0;
        while(cur+maxLen/2<len){
            int len1 = expand(cAry, cur, cur);
            int len2 = expand(cAry, cur, cur+1);
            int max = Math.max(len1, len2);
            if(max > maxLen){
                maxLen = max;
                mid = cur;
            }
            cur++;
        }
        int start = mid - (maxLen - 1) / 2;
        int end = mid + maxLen / 2;
        return s.substring(start, end+1);
    }

    public static int expand(char[] cAry, int left, int right){
        while(left>=0&&right<cAry.length&&cAry[left]==cAry[right]){
            left--;
            right++;
        }
        return right-left-1;
    }
}
