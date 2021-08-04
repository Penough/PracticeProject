package org.example;

import org.example.model.ListNode;
import org.example.model.TreeNode;
import org.springframework.boot.web.embedded.tomcat.TomcatReactiveWebServerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        TreeNode root = TreePracticeUtil.generateTreeByArray(new Integer[]{120,
                70, 140,
                50,100,130,160,
                20,55,75,110,119,115,150,200});
        TreePracticeUtil.deleteNode(root, 140);
    }
}
