package org.example;

import org.example.model.ListNode;
import org.example.model.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * （牛客）剑指Offer练习题
 */
public class JzPracticeUtil {

    /**
     * JZ3 从尾到头打印链表
     * @param listNode
     * @return
     */
    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        // 利用stack特性
//        if(listNode==null) return new ArrayList();
//        ArrayList<Integer> arrays = new ArrayList();
//        Stack<Integer> stack = new Stack<>();
//        stack.push(listNode.val);
//        while(listNode.next!=null){
//            listNode = listNode.next;
//            stack.push(listNode.val);
//        }
//        while (!stack.empty()){
//            arrays.add(stack.pop());
//        }
//        return arrays;

        // 直接一遍遍历，重复置换
//        if(listNode==null) return new ArrayList();
//        ArrayList<Integer> arrays = new ArrayList();
//        arrays.add(listNode.val);
//        while(listNode.next!=null){
//            listNode = listNode.next;
//            arrays.add(listNode.val);
//        }
//        int as = arrays.size();
//        for (int i = 0; i < as/2; i++) {
//            int tmp = arrays.get(i);
//            arrays.set(i, arrays.get(as-i-1));
//            arrays.set(as-i-1, tmp);
//        }
//        return arrays;

        //数组处理
//        if(listNode==null) return new ArrayList();
//        ListNode head = listNode;
//        int lsLength = 0;
//        while(listNode!=null){
//            lsLength++;
//            listNode = listNode.next;
//        }
//        int[] as = new int[lsLength];
//        for (int i = as.length-1; i > -1; i--) {
//            as[i] = head.val;
//            head = head.next;
//        }
//        return (ArrayList)Arrays.stream(as).boxed().collect(Collectors.toList());

        // 递归
        if(listNode==null) return new ArrayList<Integer>();
        ArrayList<Integer> as;
        if(listNode.next==null){
            as = new ArrayList<>();
            as.add(listNode.val);
        } else {
            as = printListFromTailToHead(listNode.next);
            as.add(listNode.val);
        }
        return as;
    }


    /**
     * JZ4 重建二叉树
     * 输入：
     * 先序，中序
     * [1,2,4,7,3,5,6,8],[4,7,2,1,5,3,8,6]
     * @param pre
     * @param in
     * @return
     */
    public static TreeNode reConstructBinaryTree(int [] pre, int [] in) {
        if (pre.length == 0 || in.length == 0) {
            return null;
        }
        // 获取根节点
        TreeNode root = new TreeNode(pre[0]);
        for (int i = 0; i < in.length; i++) {
            if(in[i]==pre[0]) {
//                root.left = reConstructBinaryTree(Arrays.stream(pre).skip(1).limit(i+1).toArray(),
//                        Arrays.stream(in).limit(i+1).toArray());
//                root.right = reConstructBinaryTree(Arrays.stream(pre).skip(i+1).limit(pre.length).toArray(),
//                        Arrays.stream(in).skip(i+1).limit(in.length).toArray());
                // 左子树，注意 copyOfRange 函数，左闭右开
                root.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, i + 1), Arrays.copyOfRange(in, 0, i));
                // 右子树，注意 copyOfRange 函数，左闭右开
                root.right = reConstructBinaryTree(Arrays.copyOfRange(pre, i + 1, pre.length), Arrays.copyOfRange(in, i + 1, in.length));
                break;
            }
        }
        return root;
    }


    /**
     * JZ5 用两个栈实现队列
     */
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    public void push(int node) {
        stack2.push(node);
    }

    public int pop() {
//         if(stack1.isEmpty()&&stack2.isEmpty()) return null;
        if(stack1.isEmpty()) {
            while(!stack2.isEmpty()){
                stack1.push(stack2.pop());
            }
        }
        return stack1.pop();
    }

    /**
     * JZ6 旋转数组的最小数字
     * @param array
     * @return
     */
    public int minNumberInRotateArray(int [] array) {
        if(array.length==0) return 0;
        if(array.length==1) return array[0];
        int left = 0;
        int right = array.length-1;
        while (left < right){
            if(array[left] < array[right]) return array[left];
            int mid = (left+right)/2;
            if(array[mid] > array[right])left = mid+1;
            else if(array[mid] < array[right]) right = mid;
            else --right;
        }
        return array[right];
    }


    /**
     * JZ7 斐波那契数列
     * @param n
     * @return
     */
    public static int Fibonacci(int n) {
        // 循环累加
//        if(n < 2) return n;
//        int at = 1;
//        int ao = 1;
//        int res = 1;
//        for (int i = 2; i < n; i++) {
//            res = at + ao;
//            at = ao;
//            ao = res;
//        }
//        return res;

        //递归
        if(n < 2) return n;
        return Fibonacci(n-1)+Fibonacci(n-2);
    }

    /**
     * JZ8 跳台阶
     * @param target
     * @return
     */
    public int jumpFloor(int target) {
        // 递归
//        if(target < 3) return target;
//        return jumpFloor(target-1) + jumpFloor(target-2);
        // 动态规划
        int p = 0, q = 0, r = 1;
        for (int i = 1; i <= target; ++i) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }

    /**
     * JZ9 跳台阶扩展问题
     * @param target
     * @return
     */
    public int jumpFloorII(int target) {
        if(target < 2) return target;
        return (int)Math.pow(2, target-1);
    }

    /**
     * JZ10 矩形覆盖
     * @param target
     * @return
     */
    public int rectCover(int target) {
        if(target < 3) return target;
        return rectCover(target-1) +rectCover(target-2);
    }

    /**
     * JZ11 二进制中1的个数
     * @param n
     * @return
     */
    public static int NumberOf1(int n) {
        // 转字符串做法
        String biv = Integer.toBinaryString(n);
        return (int)biv.chars().filter(c -> c=='1').count();
    }
}
