/*一个商人穿过一个N×N的正方形的网格，去参加一个非常重要的商务活动。

        他要从网格的左上角进，右下角出。

        每穿越中间1个小方格，都要花费1个单位时间。

        商人必须在(2N - 1)个单位时间穿越出去。

        而在经过中间的每个小方格时，都需要缴纳一定的费用。

        这个商人期望在规定时间内用最少费用穿越出去。

        请问至少需要多少费用？

        注意：不能对角穿越各个小方格（即，只能向上下左右四个方向移动且不能离开网格）。


        输入

        第一行是一个整数，表示正方形的宽度N。

        后面N行，每行N个不大于100的正整数，为网格上每个小方格的费用。

        数据范围

        1 ≤ N ≤ 100


        输出

        输出一个整数，表示至少需要的费用。

        样例解释

        样例中，最小值为109 = 1 + 2 + 5 + 7 + 9 + 12 + 19 + 21 + 33。*/

//典型的向下、向右移动的最短路径问题，直接用dp即可
import java.util.Scanner;

public class ZuiDiTongXingFei {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[][] cost = new int[N][N];
        for(int i = 0;i<N;i++){
            for(int j = 0;j<N;j++){
                cost[i][j] = sc.nextInt();
            }
        }
        int[][] dp = new int[N][N];
        dp[0][0] = cost[0][0];

        for(int i = 1;i<N;i++){
            dp[i][0] = dp[i-1][0] + cost[i][0];
        }

        for(int j = 1;j<N;j++){
            dp[0][j] = dp[0][j-1] + cost[0][j];
        }

        for(int i = 1;i<N;i++){
            for(int j = 1;j<N;j++){
                dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1]) + cost[i][j];
            }
        }
        System.out.println(dp[N-1][N-1]);
    }
}
