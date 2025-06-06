/*历经动态规划的试炼，成功掌握前缀和之后，小华决定助力小鲁实现进一步的提升。

此前，小华已引导小鲁学习了最长上升子序列，接着是最长公共子序列，当下迎来了这一综合题目：最长公共上升子序列。

小华讲解道：对于两个数列A和B，倘若它们各自都涵盖一段位置并非必然连续、但数值严格递增的数，那么这一段数便被称作两个数列的公共上升子序列。

而在所有的公共上升子序列当中，长度最长的那个即为最长公共上升子序列。

数列A和B的长度均不超过 3000。


输入

第一行包含一个整数N，用于表示数列A与B的长度。

第二行包含N个整数，代表数列A的元素。

第三行包含N个整数，代表数列B的元素。


输出

输出一个整数，代表最长公共上升子序列的长度。

数据范围1≤N≤3000序列中的数字均不超过2^31 - 1*/


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] A = new int[N];
        int[] B = new int[N];
        
        for (int i = 0; i < N; i++) {
            A[i] = scanner.nextInt();
        }
        for (int i = 0; i < N; i++) {
            B[i] = scanner.nextInt();
        }
        
        int result = LCIS(A, B);
        System.out.println(result);
    }
    
    public static int LCIS(int[] A, int[] B) {
        int N = A.length;
        int[] dp = new int[N];
        int max = 0;
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
              // 当A[i]和B[j]相等时，可能形成公共子序列的一部分
                if (A[i] == B[j]) {
                    dp[i] = 1; // 初始化以A[i]结尾的LCIS长度为1
                    for (int k = 0; k < i; k++) {   // 检查A[i]之前的元素，寻找可能的递增子序列
                        if (A[k] < A[i] && dp[k] > dp[i] - 1) {  // 如果A[k] < A[i]且能形成更长的递增序列
                            dp[i] = dp[k] + 1;
                        }
                    }
                    max = Math.max(max, dp[i]);
                }
            }
        }
        return max;
    }
}
