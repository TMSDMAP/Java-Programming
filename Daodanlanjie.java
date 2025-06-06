/*描述

某国为了防御敌国的导弹袭击，发展出一种导弹拦截系统。

但是这种导弹拦截系统有一个缺陷：虽然它的第一发炮弹能够到达任意的高度，但是以后每一发炮弹都不能高于前一发的高度。

某天，雷达捕捉到敌国的导弹来袭。由于该系统还在试用阶段，所以只有一套系统，因此有可能不能拦截所有的导弹。

输入导弹依次飞来的高度（雷达给出的高度数据是不大于30000的正整数），计算这套系统最多能拦截多少导弹，如果要拦截所有导弹最少要配备多少套这种导弹拦截系统。


输入

一行，为导弹依次飞来的高度


输出

两行，分别是最多能拦截的导弹数与要拦截所有导弹最少要配备的系统数
 */

import java.util.*;

public class Daodanlanjie {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        // 读取输入的导弹高度序列
        String input = scanner.nextLine();
        String[] heights = input.split(" ");

        int N = heights.length;
        int[] missiles = new int[N];

        // 将输入字符串转换为整数数组
        for(int i = 0; i < N; i++){
            missiles[i] = Integer.parseInt(heights[i]);
        }
        // ===== 第一部分：计算一套系统最多能拦截的导弹数 =====
        // dp[i] 表示以第i个导弹结尾的最长不上升子序列长度
        int[] dp = new int[N];
        Arrays.fill(dp, 1);
        // 动态规划计算最长不上升子序列
        for(int i = 1;i<N;i++){
            for(int j = 0;j<i;j++){
                // 如果当前导弹高度不超过前一个导弹，则可以拦截
                if(missiles[i]<=missiles[j]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }
        // 找出最长不上升子序列的长度
        int maxIntercept = 0;
        for(int i = 0; i < N; i++){
            maxIntercept = Math.max(maxIntercept, dp[i]);
        }
        System.out.println(maxIntercept);

        // ===== 第二部分：计算最少需要的系统数量 =====
        // 每个防御系统的当前最低拦截高度
        List<Integer> systems = new ArrayList<>();
        // 贪心算法：为每个导弹分配最合适的防御系统
        for(int missile : missiles){
            // 二分查找第一个大于等于当前导弹高度的系统
            int idx = binarySearch(systems,missile);
            // 如果没有找到合适的系统，则需要新增一个
            if(idx == systems.size()){
                systems.add(missile);
            } else {
                // 否则更新该系统的最低拦截高度为当前导弹高度
                systems.set(idx,missile);
            }
        }
        // 系统数量即为最少需要的防御系统数
        System.out.println(systems.size());
    } //对于每个新导弹，找到第一个能装下它的桶（即桶的最低高度 ≥ 导弹高度），然后把这个桶的最低高度更新为当前导弹高度（因为更小的高度能兼容更多后续导弹）。
    // 二分查找：找到列表中第一个大于等于target的位置
    // 如果所有元素都小于target，则返回列表长度
    private static int binarySearch(List<Integer> systems,int missile){
        int left = 0,right = systems.size()-1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(systems.get(mid) < missile){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
