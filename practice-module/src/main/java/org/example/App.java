package org.example;

import org.example.leetcode.StrPracticeUtil;
import org.example.leetcode.TreePracticeUtil;
import org.example.model.TreeNode;

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
        TreeNode root = TreePracticeUtil.generateTreeByArray(new Integer[]{1,
        2,3,null,null,4,5,6,7});
//        TreePracticeUtil.deleteNode(root, 140);
//        String s = "aba";
//        String p = "a*a";
//        System.err.println(StrPracticeUtil.isMatch(s, p));
    }
}
