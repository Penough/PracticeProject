package org.example;

import org.example.leetcode.StrPracticeUtil;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        ListNode lsn = new ListNode(1, new ListNode(2, new ListNode(3, null)));
//        System.err.println(JzPracticeUtil.Power(2,-3));\
//        TreeNode root = TreePracticeUtil.generateTreeByArray(new Integer[]{120,
//                70, 140,
//                50,100,130,160,
//                20,55,75,110,119,115,150,200});
//        TreePracticeUtil.deleteNode(root, 140);
        String s = "aba";
        String p = "a*a";
        System.err.println(StrPracticeUtil.isMatch(s, p));
    }
}
