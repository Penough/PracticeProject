package org.example;

import org.example.model.ListNode;
import org.example.model.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
//        String biv = Integer.toBinaryString(n);
//        return (int)biv.chars().filter(c -> c=='1').count();
        // 移位法
//        int count = 0;
//        int m = 1;
//        while(m != 0){
//            if((m&n)!=0) ++count;
//            m<<=1;
//        }
//        return count;

        // -1与
        int count=0;
        while (n!=0){
            ++count;
            n = n & (n-1);
        }
        return count;
    }

    /**
     * JZ12 数值的整数次方
     * @param base
     * @param exponent
     * @return
     */
    public static double Power(double base, int exponent) {
        // 递归处理
//        if(exponent==0) return 1;
//        else if(exponent > 0) return base * Power(base, exponent-1);
//        else return (1/base) * Power(base, exponent+1);

        // 循环求解
        if(exponent==0) return 1;
        if(base==0) return 0;
        if(exponent < 0) {
            base = 1/base;
            exponent = -exponent;
        }
        double res = base;
        for (int i = 1; i < exponent; i++) {
            res *= base;
        }
        return res;
    }

    /**
     * JZ13 调整数组顺序使奇数位于偶数前面
     * [1,2,3,4,5]
     * [1,3,5,2,4]
     * @param array
     * @return
     */
    public int[] reOrderArray (int[] array) {
        // 双遍历取数法 时间复杂度O(2n),额外空间复杂度O(n)
        int[] res = new int[array.length];
        int cur = 0;
        for (int i = 0; i < array.length; i++) {
            if(array[i]%2==1) res[cur++] = array[i];
        }
        for (int i = 0; i < array.length; i++) {
            if(array[i]%2==0) res[cur++] = array[i];
        }
        return res;
    }

    /**
     * JZ14 链表中倒数最后k个结点
     * @param pHead
     * @param k
     * @return
     */
    public ListNode FindKthToTail (ListNode pHead, int k) {
        // 链表处理
//        if(pHead==null||k==0) return null;
//        List<ListNode> ls = new ArrayList<>();
//        while (pHead!=null){
//            ls.add(pHead);
//            pHead = pHead.next;
//        }
//        return ls.size()-k >= 0 ? ls.get(ls.size()-k):null;

        // 双指针
        if(pHead==null||k==0) return null;
        ListNode pre = pHead;
        int d = 0;
        while (pHead!=null){
            pHead = pHead.next;
            if(d<k) ++d;
            else pre = pre.next;
        }
        if(d < k) return null;
        return pre;
    }

    /**
     * JZ15 反转链表
     * @param head
     * @return
     */
    public ListNode ReverseList(ListNode head) {
        return reverseListInt(head, null);
    }

    private ListNode reverseListInt(ListNode head, ListNode newHead) {
        if (head == null)
            return newHead;
        ListNode next = head.next;
        head.next = newHead;
        return reverseListInt(next, head);
    }

    /**
     * JZ16 合并两个排序的链表
     * @param list1
     * @param list2
     * @return
     */
    public ListNode Merge(ListNode list1,ListNode list2) {
        // 递归
//        if(list1 == null) return list2;
//        else if(list2 == null) return  list1;
//        else if(list1.val < list2.val) {
//            list1.next = Merge(list1.next, list2);
//            return list1;
//        } else {
//            list2.next = Merge(list1, list2.next);
//            return list2;
//        }

        // 单链表
        List<ListNode> ls = new ArrayList<>();
        while (list1 == null && list2 == null){
            if(list1.val < list2.val) {
                ls.add(list1);
                list1 = list1.next;
            } else {
                ls.add(list2);
                list2 = list2.next;
            }
        }
        for (int i = 0; i < ls.size()-1; i++) {
            ls.get(i).next = ls.get(i+1);
        }
        return ls.get(0);
    }

    /**
     * JZ17 树的子结构
     * @param root1
     * @param root2
     * @return
     */
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        if(root1==null||root2==null) return false;
        return check(root1, root2)||HasSubtree(root1.left, root2)||HasSubtree(root1.right, root2);
    }

    private boolean check(TreeNode root1,TreeNode root2){
        if(root2==null) return true;
        if(root1==null || root1.val != root2.val) return false;
        return check(root1.left, root2.left) && check(root1.right, root2.right);
    }

    /**
     * JZ18 二叉树的镜像
     * @param pRoot
     * @return
     */
    public TreeNode Mirror (TreeNode pRoot) {
        // write code here
        if(pRoot == null) return pRoot;
        if(pRoot.left==null && pRoot.right==null) return pRoot;
        TreeNode tnode = pRoot.left;
        pRoot.left = pRoot.right;
        pRoot.right = tnode;
        Mirror(pRoot.left);
        Mirror(pRoot.right);
        return pRoot;
    }
}
