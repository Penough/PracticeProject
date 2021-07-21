package org.example;

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
        TreeNode node1 = new TreeNode(15);
        TreeNode node2 = new TreeNode(7);
        TreeNode node3 = new TreeNode(20, node1, node2);
        TreeNode node4 = new TreeNode(9);
        TreeNode root = new TreeNode(3, node3, node4);

        System.err.println(TreePracticeUtil.levelOrder(root));
    }
}
