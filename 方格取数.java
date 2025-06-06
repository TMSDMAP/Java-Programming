/*设有N×N的方格图，我们在其中的某些方格中填入正整数，而其它的方格中则放入数字0。

2.gif

某人从图中的左上角A出发，可以向下行走，也可以向右行走，直到到达右下角的B点。

在走过的路上，他可以取走方格中的数（取走后的方格中将变为数字0）。

此人从A点到B点共走了两次，试找出两条这样的路径，使得取得的数字和为最大。


输入

第一行为一个整数N，表示N×N的方格图。

接下来的每行有三个整数，第一个为行号数，第二个为列号数，第三个为在该行、该列上所放的数。

行和列编号从1开始。

一行“0 0 0”表示结束。


输出

输出一个整数，表示两条路径上取得的最大的和。

数据范围

N≤10*/
//从一个网格的左上角到右下角找到两条路径，使得路径上的数字总和最大
import java.util.*;

public class Main {
    static int N;
    static int[][] grid = new int[11][11];
    static int[][][] dp = new int[11][11][11];  //x1和y1表示第一条路径当前位置的坐标,x2表示第二条路径的横坐标，纵坐标y2由x1 + y1 - x2计算得出（因为两条路径的步数相同）
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();


        while(true){  // 读取网格数据，直到输入0 0 0为止
            int x = sc.nextInt();
            int y = sc.nextInt();
            int val = sc.nextInt();
            if(x==0&&y==0&&val==0) break;
            grid[x][y] = val;
        }
        dp[1][1][1] = grid[1][1];

        for(int x1 = 1;x1<=N;x1++){
            for(int y1 = 1;y1<=N;y1++){
                for(int x2 = 1;x2<=N;x2++) {
                    int y2 = x1 + y1 - x2;  // 计算第二条路径的y坐标
                    if (y2 < 1 || y2 > N) continue;  // 确保y2在有效范围内

                    int val = grid[x1][y1];   // 确保y2在有效范围内
                    if (x1 != x2 || y1 != y2) val += grid[x2][y2];   // 如果两条路径不在同一位置，加上第二条路径当前位置的值
                    int maxPrev = Math.max(Math.max(dp[x1 - 1][y1][x2 - 1], dp[x1][y1 - 1][x2 - 1]),
                            Math.max(dp[x1 - 1][y1][x2], dp[x1][y1 - 1][x2]));   // 计算所有可能的前一个状态中的最大值
                    dp[x1][y1][x2] = Math.max(dp[x1][y1][x2], maxPrev + val);
                }
            }
        }
        System.out.println(dp[N][N][N]);
    }
}
