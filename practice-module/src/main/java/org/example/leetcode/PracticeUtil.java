package org.example.leetcode;

import org.example.model.ListNode;

import java.util.*;
import java.util.stream.Collectors;

public class PracticeUtil {
    /**
     * 350
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersection(int[] nums1, int[] nums2){
        // tmp 构成字典
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            if (map.containsKey(nums1[i])) {
                map.put(nums1[i], map.get(nums1[i])+1);
            } else {
                map.put(nums1[i], 1);
            }
        }
        List<Integer> tmpRes = new ArrayList();
        int len = 0;
        for (int i = 0; i < nums2.length; i++) {
            if(map.containsKey(nums2[i])&&map.get(nums2[i]) > 0){
                map.put(nums2[i], map.get(nums2[i])-1);
                tmpRes.add(nums2[i]);
                len++;
            }
        }
        int[] res = new int[len];
        for (int i = 0; i < tmpRes.size(); i++) {
            res[i] = tmpRes.get(i);
        }
        return res;
    }

    /**
     * 14. 最长公共前缀
     * @param strs
     * @return
     */
    public static String longestCommonPrefix(String[] strs) {
        if(strs.length == 0) return "";
        if(strs.length == 1) return strs[0];
        String s = strs[0];
//        int idx = s.length();
//        outLoop: for (int i = 0; i < s.length(); i++) {
//            char c = s.charAt(i);
//            for (int j = 1; j < strs.length; j++) {
//                if(strs[j].length() == i || strs[j].charAt(i) != c){
//                    idx = i;
//                    break outLoop;
//                }
//            }
//        }
        String tmp="";
        for (int i = s.length(); i > -1 ; i--) {
            int j = 0;
            tmp = s.substring(0, i);
            while (strs[j].startsWith(tmp)){
                if(++j == strs.length) return tmp;
            }
        }
        return tmp;
    }

    /**
     * 122. 买卖股票的最佳时机 II
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0) return 0;
        int idx = 0;
        return findProfit(prices, idx);
    }

    public static int findProfit(int[] prices, int idx){
        if(idx == prices.length - 1) return 0;
        int profit = prices[idx+1] - prices[idx];
        if(profit < 0) profit = 0;
        return findProfit(prices, ++idx) + profit;
    }

    /**
     * 189. 旋转数组
     * @param nums
     * @param k
     */
    public static void rotate(int[] nums, int k) {
        //时间复杂度O(1),空间复杂度O(n)
        int offset = 0;
        if(k > -1)offset = k%nums.length;
        else offset = nums.length + k% nums.length;
        int[] t = new int[nums.length];
        System.arraycopy(nums, nums.length-offset, t, 0, offset);
        System.arraycopy(nums, 0, t, offset, nums.length-offset);
        for (int i = 0; i < t.length; i++) {
            System.err.print(t[i]+";");
        }
    }

    /**
     * 27，移除元素
     * @param nums
     * @param val
     * @return
     */
    public static int removeElement(int[] nums, int val) {
//        int r = nums.length-1;
//        for (int i = 0; i < nums.length; i++) {
//            while (nums[r] == val && i < r){ r--; };
//            if(i >= r){
//                if(nums[r] == val) r--;
//                break;
//            }
//            if(nums[i]==val){
//                nums[i] = nums[r];
//                nums[r--] = val;
//            }
//        }
//        return r+1;

        int l = 0, r = nums.length;
        while (l < r){
            if(nums[l] != val){
                l++;
            } else {
                nums[l] = nums[--r];
            }
        }
        printArray(Arrays.stream(nums).boxed().toArray());
        return l;

    }

    /**
     * 66. 加一
     * @param digits
     * @return
     */
    public static int[] plusOne(int[] digits) {
        for (int i = digits.length-1; i > -1; i--) {
            if(digits[i] == 9){ digits[i] = 0;}
            else{ digits[i]+=1; break;}
        }
        if(digits[0] == 0) {
            int[] res = new int[digits.length+1];
            res[0] = 1;
            digits = res;
        }
        printArray(Arrays.stream(digits).boxed().toArray());
        return digits;
    }

    /**
     * 15. 三数之和
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        int[] sortedLs = Arrays.stream(nums).sorted().toArray();
        if(nums == null || nums.length == 0 || sortedLs[0] > 0 || sortedLs[sortedLs.length-1] < 0) return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < sortedLs.length; i++) {
            // 正数后正数相加不会等于0，排除
            if(sortedLs[i] > 0) break;
            if(i > 0 && sortedLs[i] ==  sortedLs[i-1]) continue;
            int l = i+1;
            int r = sortedLs.length-1;
            // 最小加最大如果小于选定值的绝对值，则说明最小值过小，左>>,反之右<<
            while ( l < r ) {
                if (sortedLs[l] + sortedLs[r] + sortedLs[i] < 0 ) {
                    l++;
                } else if (sortedLs[l] + sortedLs[r] + sortedLs[i] > 0) {
                    r--;
                } else {
                    res.add(Arrays.asList(sortedLs[i], sortedLs[l], sortedLs[r]));
                    while (l < r && sortedLs[l] == sortedLs[l + 1]) l++;
                    while (l < r && sortedLs[r] == sortedLs[r - 1]) r--;
                    l++;r--;
                }
            }
        }
        System.err.println(res);
        return res;
    }

    /**
     * 6. Z 字形变换
     * @param s
     * @param numRows
     * @return
     */
    public static String convert(String s, int numRows) {
        // 暴力解
//        if(s.length() < numRows || numRows == 1) return s;
//        int num = s.length()/(2*numRows-2) + 1;
//        int numColumns = (numRows-1) * num;
//        char[][] rec= new char[numRows][numColumns];
//        int cur = 0;
//        outLoop:
//        for (int i = 0; i < numColumns; i++) {
//            for (int j = 0; j < numRows; j++) {
//                if(cur >= s.length()) break outLoop;
//                if (i%(numRows-1)==0) {
//                    rec[j][i] = s.charAt(cur++);
//                } else {
//                    if((i+j) % (numRows-1)==0) {
//                        rec[j][i] =s.charAt(cur++);
//                        break;
//                    }
//                }
//            }
//        }
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < rec.length; i++) {
//            for (int j = 0; j < rec[i].length; j++) {
//                if((int)rec[i][j]!=0) sb.append(rec[i][j]);
//            }
//        }
//        return  sb.toString();

        // 字符迭代方法
        if(numRows == 1) return s;
        StringBuilder[] sbs = new StringBuilder[numRows];
        for (int i = 0; i < sbs.length; i++) {
            sbs[i] = new StringBuilder();
        }
        int sbsCur = 0;
        boolean goDown = false;
        for (int i = 0; i < s.length(); i++) {
            sbs[sbsCur].append(s.charAt(i));
            if(sbsCur==0 || numRows-1==sbsCur) goDown = !goDown;
            sbsCur += goDown? 1:-1;
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < sbs.length; i++) {
            res.append(sbs[i]);
        }
        return res.toString();
    }

    /**
     * 19. 删除链表的倒数第 N 个结点
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        // 暴力解法
//        List<ListNode> lnl = new ArrayList();
//        ListNode tmp = head;
//        while(tmp.next != null){
//            lnl.add(tmp);
//            tmp = tmp.next;
//        }
//        lnl.add(tmp);
//        int delIdx = lnl.size()-n;
//        if(delIdx == 0){
//            if(lnl.size()==1) return null;
//            return lnl.get(1);
//        } else if (delIdx == lnl.size() - 1) {
//            ListNode preNode = lnl.get(delIdx-1);
//            preNode.next = null;
//            return lnl.get(0);
//        }else {
//            ListNode preNode = lnl.get(delIdx-1);
//            preNode.next = lnl.get(delIdx+1);
//            return lnl.get(0);
//        }

        // 指针解法
        // 扫描小尾巴
        int tail = 1;
        // 哨兵节点,用于首项删除
        ListNode sen = new ListNode(0, head);
        ListNode pre = sen;
        ListNode cur = head;
        while (head.next != null){
            if(tail >= n){
                pre = cur;
                cur = cur.next;
            }
            head = head.next;
            tail++;
        }
        pre.next = pre.next.next;
        return sen.next;
    }

    /**
     * 21. 合并两个有序链表
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null){
            return l2;
        } else if(l2 == null){
            return l1;
        } else if(l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    /**
     * 141. 环形链表
     * @param head
     * @return
     */
    public static boolean hasCycle(ListNode head) {
        // 空间复杂度O(n)
//        if(head==null) return false;
//        Map<ListNode, Integer> vcMap = new LinkedHashMap();
//        while (head.next != null){
//            if(vcMap.containsKey(head)){
//                return true;
//            } else {
//                vcMap.put(head, 1);
//            }
//            head = head.next;
//        }

        // 快慢指针
        if(head==null) return false;
        ListNode slow = head;
        ListNode fast = head;
        while(true){
            try {
                slow = slow.next;
                fast = fast.next.next;
            } catch (NullPointerException e){
                return false;
            }
            if(slow == fast) return true;
        }
    }

    private static void printArray(Object[] ls){
        for (int i = 0; i < ls.length; i++) {
            System.err.print(ls[i]+";");
        }
        System.err.println();
    }

    /**
     * 16. 最接近的三数之和
     * @param nums
     * @param target
     * @return
     */
    public static int threeSumClosest(int[] nums, int target) {
        // 排序+双指针
        Arrays.sort(nums);
        int cur=0;
        int res=nums[cur]+nums[1]+nums[2];
        while(cur < nums.length-2){
            int left=cur+1, right=nums.length-1;
            while(left < right) {
                int sum = nums[cur] + nums[left] + nums[right];
                if (Math.abs(target - sum) <= Math.abs(target - res)) res = sum;
                if (sum < target) left++;
                else if (sum > target) right--;
                else return res;
            }
            cur++;
        }
        return res;
    }

    /**
     * 11. 盛最多水的容器
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
        // 穷举
//        int cap = 0;
//        for (int i = 0; i < height.length-1; i++) {
//            for (int j = i+1; j < height.length; j++) {
//                int tmp = Math.min(height[i], height[j]) * (j-i);
//                if(tmp > cap) cap = tmp;
//            }
//        }
//        return cap;

        // 双指针
        int left = 0, right = height.length-1;
        int cap = 0;
        while(left < right){
            int tmp = Math.min(height[left], height[right]) * (right-left);
            if(tmp>cap) cap = tmp;
            if(height[left] < height[right]) left++;
            else right--;
        }
        return cap;
    }
}
