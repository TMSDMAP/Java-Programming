/*
一个数的序列 𝑏𝑖，当 𝑏1<𝑏2<…<𝑏𝑆 的时候，我们称这个序列是上升的。

对于给定的一个序列(𝑎1,𝑎2,…,𝑎𝑁)，我们可以得到一些上升的子序列(𝑎𝑖1,𝑎𝑖2,…,𝑎𝑖𝐾)，这里1≤𝑖1<𝑖2<…<𝑖𝐾≤𝑁。

比如，对于序列(1,7,3,5,9,4,8)，有它的一些上升子序列，如(1,7),(3,4,8)等等。

这些子序列中和最大为18，为子序列(1,3,5,9)的和。

你的任务，就是对于给定的序列，求出最大上升子序列和。

注意，最长的上升子序列的和不一定是最大的，比如序列(100,1,2,3)的最大上升子序列和为100，而最长上升子序列为(1,2,3)。


输入

输入的第一行是序列的长度N。

第二行给出序列中的N个整数，这些整数的取值范围都在0到10000（可能重复）。

数据范围

1 ≤ N ≤ 1000


输出

输出一个整数，表示最大上升子序列和。*/

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 输入序列的长度
        int N = scanner.nextInt();
        int[] a = new int[N];
        
        // 输入序列的元素
        for (int i = 0; i < N; i++) {
            a[i] = scanner.nextInt();
        }

        // 动态规划数组
        int[] dp = new int[N];
        
        // 初始化，dp[i] 等于序列元素本身
        for (int i = 0; i < N; i++) {
            dp[i] = a[i];
        }

        // 动态规划求解最大上升子序列和
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (a[j] < a[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + a[i]);
                }
            }
        }

        // 输出最大上升子序列和
        int result = 0;
        for (int i = 0; i < N; i++) {
            result = Math.max(result, dp[i]);
        }
        
        System.out.println(result);
    }
}
