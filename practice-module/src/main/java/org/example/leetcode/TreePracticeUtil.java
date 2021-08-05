package org.example.leetcode;

import org.example.model.TreeNode;
import sun.awt.image.ImageWatched;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class TreePracticeUtil {

    /**
     * 层级遍历生成树
     * @param vals
     * @return
     */
    public static TreeNode generateTreeByArray(Integer[] vals){
        if(vals==null || vals.length==0) return null;
        TreeNode[] ts = new TreeNode[vals.length];
        ts[0] = new TreeNode(vals[0]);
        TreeNode tmp;
        for (int i = 1; i < vals.length; i++) {
            if(vals[i] != null) tmp = new TreeNode(vals[i]);
            else tmp = null;
            ts[i] = tmp;
            if(i%2==1){
                ts[i/2].left=vals[i]==null?null:tmp;
            } else {
                ts[i/2-1].right=vals[i]==null?null:tmp;
            }
        }
        return ts[0];
    }

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

    /**
     * 98. 验证二叉搜索树
     * @param root
     * @return
     */
    Integer curNum;
    public boolean isValidBST(TreeNode root) {
        // 中序遍历，比大小
        if(root==null) return true;
        if(!isValidBST(root.left)) return false;
        if(curNum!=null&&curNum >= root.val) return false;
        curNum = root.val;
        return isValidBST(root.right);
    }

    // 中序遍历
    public static LinkedList tns = new LinkedList();
    public static void ldr(TreeNode root){
        if(root==null) return;
        ldr(root.left);
        tns.add(root);
        ldr(root.right);
    }

    /**
     * 700. 二叉搜索树中的搜索
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBST(TreeNode root, int val) {
        // 全遍历搜索，没有利用搜索树的属性
//        if(root==null) return null;
//        if(root.val == val) return root;
//        TreeNode node = searchBST(root.left, val);
//        if(node==null) return searchBST(root.right, val);
//        return node;
        // 优化处理
        if(root==null) return null;
        if(root.val == val) return root;
        if(val < root.val) return searchBST(root.left, val);
        else return searchBST(root.right, val);
    }

    /**
     * 450. 删除二叉搜索树中的节点
     * @param root
     * @param key
     * @return
     */
    public static TreeNode deleteNode(TreeNode root, int key) {
        // 左旋，和题目用例不符，应该也是ok的
//        TreeNode cur = root;
//        TreeNode pre = null;
//        boolean isLeft = true;
//        while (cur!=null){
//            if(key > cur.val) {
//                pre = cur;
//                cur = cur.right;
//                isLeft = false;
//            } else if(key < cur.val) {
//                pre = cur;
//                cur = cur.left;
//                isLeft = true;
//            } else {
//                if(pre==null){
//                    root = turnLeft(cur);
//                    break;
//                }
//                if(isLeft){
//                    pre.left = turnLeft(cur);
//                } else {
//                    pre.right = turnLeft(cur);
//                }
//                break;
//            }
//        }
//        return root;

        // 升级处理
        TreeNode cur = root;
        TreeNode pre = null;
        boolean isLeft = true;
        while (cur!=null){
            if(key > cur.val) {
                pre = cur;
                cur = cur.right;
                isLeft = false;
            } else if(key < cur.val) {
                pre = cur;
                cur = cur.left;
                isLeft = true;
            } else {
                if(pre==null){
                    root = upGrade(cur);
                    break;
                }
                if(isLeft){
                    pre.left = upGrade(cur);
                } else {
                    pre.right = upGrade(cur);
                }
                break;
            }
        }
        return root;
    }
    // 左旋转处理
    public static TreeNode turnLeft(TreeNode delNode){
        TreeNode root;
        if(delNode.right!=null) {
            root = delNode.right;
            if(delNode.left!=null){
                TreeNode cur = root;
                while (cur.left!=null){
                    cur = cur.left;
                }
                cur.left = delNode.left;
            }
        } else {
            root = delNode.left;
        }
        return root;
    }
    // 顺位升级，将叶子节点晋升为根节点，符合题目用例要求
    public static TreeNode upGrade(TreeNode delNode){
        TreeNode root;
        if(delNode.right!=null) {
            TreeNode cur = delNode.right;
            TreeNode pre = null;
            while (cur.left!=null) {
                pre = cur;
                cur = cur.left;
            }
            if(pre==null) {
                cur.left = delNode.left;
                root = cur;
            } else {
                pre.left = upGrade(cur);
                cur.right = delNode.right;
                cur.left = delNode.left;
                root = cur;
            }
        } else {
            root = delNode.left;
        }
        return root;
    }

    /**
     * 110. 平衡二叉树
     * @param root
     * @return
     */
    public static boolean isBalanced(TreeNode root) {
        // 自顶向下遍历高度
//        if(root==null) return true;
//        return Math.abs(height(root.left)-height(root.right)) <= 1&&isBalanced(root.left)&&isBalanced(root.right);
        return height(root) >= 0;
    }

    public static int height(TreeNode root){
        // 自顶向下求高
//        if(root==null) return 0;
//        else return 1 + Math.max(height(root.left), height(root.right));
        // 自底向上处理
        if(root == null) return 0;
        int leftHeight = height(root.left);
        int rightHeight=height(root.right);
        if(leftHeight==-1||rightHeight==-1||Math.abs(leftHeight-rightHeight)>1){
            return -1;
        }
        return 1+Math.max(leftHeight, rightHeight);
    }

    /**
     * 222. 完全二叉树的节点个数
     * @param root
     * @return
     */
    public static int countNodes(TreeNode root) {
        // 遍历数节点，未使用到完全二叉树性质
//        if(root==null) return 0;
//        return 1 + countNodes(root.left) + countNodes(root.right);
        // 利用完全二叉树性质，对递归进行优化
        if(root==null) return 0;
        int leftHeight = countLeftHeight(root.left);
        int rightHeight = countLeftHeight(root.right);
        if(leftHeight==rightHeight){
            return (1 << leftHeight) + countNodes(root.right);
        } else {
            return (1 << rightHeight) + countNodes(root.left);
        }
    }

    public static int countLeftHeight(TreeNode root){
        int count = 0;
        if(root == null) return count;
        while (root!=null){
            ++count;
            root = root.left;
        }
        return count;
    }

    /**
     * 814. 二叉树剪枝
     * @param root
     * @return
     */
    public TreeNode pruneTree(TreeNode root) {
        if(root==null) return null;
        if(root.left!=null) root.left = pruneTree(root.left);
        if(root.right!=null) root.right = pruneTree(root.right);
        if(root.val==0&&root.left==null&&root.right==null) return null;
        return root;
    }
}
