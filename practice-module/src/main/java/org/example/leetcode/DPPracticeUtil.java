package org.example.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 动态规划练习
 */
public class DPPracticeUtil {
    /**
     * 509. 斐波那契数
     * @param n
     * @return
     */
    public int fib(int n) {
        // 递归
//        if(n<2) return n;
//        return fib(n-1)+fib(n-2);
        // DP
        if(n<2) return n;
        int pre=0,res=1,tmp;
        for (int i = 1; i < n; i++) {
            tmp = res;
            res += pre;
            pre = tmp;
        }
        return res;
    }
    /**
     * 70. 爬楼梯
     * @param n
     * @return
     */
    public static int climbStairs(int n) {
        // 反向递归
//        if(n == 1) return 1;
//        if(n == 2) return 2;
//        return climbStairs(n-1) + climbStairs(n-2);

        // 正向循环
        int p = 0, q = 0, r = 1;
        for (int i = 1; i <= n; ++i) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }

    /**
     * 53. 最大子序和
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        // 暴力解
//        if(nums.length == 1) return nums[0];
//        int max = nums[0];
//        for (int i = 1; i < nums.length+1; i++) {
//            for (int j = 0; j < nums.length - i+1; j++) {
//                int sum = Arrays.stream(nums).skip(j).limit(i).sum();
//                if(max < sum) max = sum;
//            }
//        }
//        return max;

        // DP
        int res = nums[0];
        int sum = 0;
        for (int num:nums) {
            if(sum > 0){
                sum += num;
            }else{
                sum = num;
            }
            if(sum > res) res = sum;
        }
        return res;
    }

    /**
     * 300. 最长递增子序列
     * @param nums
     * @return
     */
    public static int lengthOfLIS(int[] nums) {
        // DP解
//        int[] dp = new int[nums.length];
//        int max = 1;
//        dp[0] = 1;
//        for (int i = 1; i < nums.length; i++) {
//            dp[i] = 1;
//            for (int j = 0; j < i; j++) {
//                if(nums[j] < nums[i]) dp[i] = Math.max(dp[i], dp[j] + 1);//dp[i] = dp[j]+1;
//            }
//            max = Math.max(max, dp[i]);
//        }
//        return max;

        // 二分法+贪心
        int[] tails = new int[nums.length];
        int res = 0;
        for(int num : nums) {
            int i = 0, j = res;
            while(i < j) {
                int m = (i + j) / 2;
                if(tails[m] < num) i = m + 1;
                else j = m;
            }
            tails[i] = num;
            if(res == j) res++;
        }
        return res;
    }

    /**
     * 120. 三角形最小路径和
     * @param triangle
     * @return
     */
    public static int minimumTotal(List<List<Integer>> triangle) {
        if(triangle == null || triangle.size() == 0) return 0;
        // 自顶向下累加，比较最后一层大小
//        List<Integer> layer = triangle.get(0);
//        List<Integer> preLayer;
//        for (int i = 1; i < triangle.size(); i++) {
//            preLayer = triangle.get(i-1);
//            layer = triangle.get(i);
//            for (int j = 0; j < layer.size(); j++) {
//                int cur = layer.get(j);
//                if(j == 0) layer.set(j, cur + preLayer.get(0));
//                else if(j == layer.size() - 1) layer.set(j, cur + preLayer.get(preLayer.size()-1));
//                else {
//                    layer.set(j, Math.min(cur+preLayer.get(j), cur+preLayer.get(j-1)));
//                }
//            }
//        }
//        int res = layer.stream().min(Integer::compare).get();
//        return res;

        // 自底向上累加，不需要比较大小
        List<Integer> preLayer;
        List<Integer> layer = triangle.get(triangle.size()-1);
        for (int i = triangle.size() - 2; i > -1; i--) {
            preLayer = triangle.get(i+1);
            layer = triangle.get(i);
            for (int j = 0; j < layer.size(); j++) {
                int cur = layer.get(j);
                layer.set(j, Math.min(cur + preLayer.get(j), cur + preLayer.get(j+1)));
            }
        }
        return layer.get(0);
    }

    /**
     * 64. 最小路径和
     * @param grid
     * @return
     */
    public static int minPathSum(int[][] grid) {
        // 先递归做一下
//        int m = grid.length;
//        int n = grid[0].length;
//        return shortestPathTo(grid, m-1, n-1);

        // 递归思路有了，DP左上起
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(i==0&&i==j)continue;
                int cur = grid[i][j];
                if(i==0&&j!=0) grid[i][j] = cur+grid[i][j-1];
                else if(i!=0&&j==0) grid[i][j] = cur+grid[i-1][j];
                else grid[i][j] = Math.min(grid[i-1][j]+cur, grid[i][j-1]+cur);
            }
        }
        return grid[grid.length-1][grid[0].length-1];
    }

    public static int shortestPathTo(int[][] grid, int x, int y){
        if(x==0&&y!=0) {
            return grid[x][y] + shortestPathTo(grid, x, y-1);
        } else if (x!=0&&y==0){
            return grid[x][y] + shortestPathTo(grid, x-1, y);
        } else if (x!=0&&y!=0){
            return Math.min(grid[x][y] + shortestPathTo(grid, x-1, y), grid[x][y] + shortestPathTo(grid, x, y-1));
        } else {
            return grid[0][0];
        }
    }

    /**
     * 198. 打家劫舍
     * @param nums
     * @return
     */
    public static int rob(int[] nums) {
        // 递归做法，量大超时
//        return robFront(nums, nums.length - 1);

        // DP处理
        if(nums.length == 1) return nums[0];
        else if(nums.length == 2) return Math.max(nums[0], nums[1]);
        nums[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            nums[i] = Math.max(nums[i-2]+nums[i], nums[i-1]);
        }
        return nums[nums.length-1];
    }

    public static int robFront(int[] nums, int idx){
        if(idx==0) return nums[idx];
        else if(idx==1) return Math.max(nums[0], nums[idx]);
        else return Math.max(robFront(nums, idx-2)+nums[idx], robFront(nums, idx-1));
    }
}
