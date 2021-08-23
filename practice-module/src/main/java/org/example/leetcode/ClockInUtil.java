package org.example.leetcode;

/**
 * 打卡题目
 */
public class ClockInUtil {
    /**
     * 1646. 获取生成数组中的最大值
     * @param n
     * @return
     */
    public int getMaximumGenerated(int n) {
        if(n==0) return 0;
        if(n<2) return n;
        int[] nums = new int[n+1];
        nums[0] = 0;
        nums[1] = 1;
        int res = 1;
        for(int i=1;i < nums.length; i++){
            int a = 2*i, b=2*i+1;
            if(a>=2&&a<=n) nums[2*i] = nums[i];
            if(b>=2&&b<=n) {
                int nr = nums[i] + nums[i + 1];
                nums[b] = nr;
                if(nr > res) res = nr;
            }
        }
        return res;
    }

    /**
     * 789. 逃脱阻碍者
     * @param ghosts
     * @param target
     * @return
     */
    public boolean escapeGhosts(int[][] ghosts, int[] target) {
        int[] pLoc = {0,0};
        int pDis = manhattanDistance(pLoc, target);
        for (int[] loc: ghosts) {
            int gDis = manhattanDistance(loc, target);
            if(gDis <= pDis) return false;
        }
        return true;
    }
    private int manhattanDistance(int[] loc, int[] target){
        return Math.abs(loc[0]-target[0])+Math.abs(loc[1]-target[1]);
    }
}
