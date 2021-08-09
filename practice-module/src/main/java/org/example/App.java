package org.example;

import org.example.sort.SortPracticeUtil;

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
//        TreeNode root = TreePracticeUtil.generateTreeByArray(new Integer[]{1,
//        2,3,null,null,4,5,6,7});
//        TreePracticeUtil.deleteNode(root, 140);
//        String s = "abcde";
//        String p = "ab?*de";
//        System.err.println(StrPracticeUtil.isMatch(s, p));

        int[] array = {6,4,2,1,6,2,45,7,2};
        SortPracticeUtil.quickSort(array);
        boolean a = 1==1;
    }
}
