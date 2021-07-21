package org.example;

import org.example.model.TreeNode;
import sun.awt.image.ImageWatched;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class TreePracticeUtil {

    /**
     * 104. 二叉树的最大深度
     * @param root
     * @return
     */
    public static int maxDepth(TreeNode root) {
        // 递归深度遍历
//        if(root==null) return 0;
//        if(root.left == null && root.right == null) return 1;
//        else if(root.left!=null && root.right!=null) return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
//        else return 1 + (root.left==null? maxDepth(root.right):maxDepth(root.left));
        // 化简
        if(root==null) return 0;
        else return 1 + Math.max(maxDepth(root.right),maxDepth(root.left));
    }

    /**
     * 102. 二叉树的层序遍历
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        // 常规层循环处理
//        if (root == null) return new LinkedList<>();
//        List<List<Integer>> res = new LinkedList<>();
//        List<TreeNode> layer = new LinkedList<>();
//        layer.add(root);
//        TreeNode cur;
//        List<Integer> layVals = new LinkedList<>();
//        layVals.add(root.val);
//        res.add(layVals);
//        List<TreeNode> tmpLayer;
//        while(true){
//            tmpLayer = new LinkedList<>();
//            layVals = new LinkedList<>();
//            for (int i = 0; i < layer.size(); i++) {
//                cur = layer.get(i);
//                if(cur.left!=null) {
//                    layVals.add(cur.left.val);
//                    tmpLayer.add(cur.left);
//                }
//                if(cur.right!=null){
//                    layVals.add(cur.right.val);
//                    tmpLayer.add(cur.right);
//                }
//            }
//            if(layVals.size()==0) break;
//            res.add(layVals);
//            layer = tmpLayer;
//        }
//        return res;

        // 队列代码处理
        if (root == null) return new LinkedList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> vals = new LinkedList<>();
        TreeNode cur;
        int idx = queue.size();
        while ( (cur=queue.poll())!=null){
            vals.add(cur.val);
            if(cur.left!=null) queue.add(cur.left);
            if(cur.right!=null) queue.add(cur.right);
            if(--idx == 0) {
                idx = queue.size();
                res.add(vals);
                vals = new LinkedList<>();
            }
        }
        return res;
    }
}
