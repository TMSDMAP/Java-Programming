/*五一到了，ACM队组织大家去登山观光，队员们发现山上一共有N个景点，并且决定按照顺序来浏览这些景点，即每次所浏览景点的编号都要大于前一个浏览景点的编号。

同时队员们还有另一个登山习惯，就是不连续浏览海拔相同的两个景点，并且一旦开始下山，就不再向上走了。

队员们希望在满足上面条件的同时，尽可能多的浏览景点，你能帮他们找出最多可能浏览的景点数么？


输入

第一行包含整数N，表示景点数量。

第二行包含N个整数，表示每个景点的海拔。

数据范围

2≤N≤1000


输出

输出一个整数，表示最多能浏览的景点数。*/


import java.util.*;

public class MountainTour {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();  // 景点数量
        int[] altitudes = new int[N];  // 海拔数组

        for (int i = 0; i < N; i++) {
            altitudes[i] = scanner.nextInt();  // 输入每个景点的海拔
        }

        // 定义两个数组 up 和 down
        int[] up = new int[N];    // up[i] 表示从 i 开始的最长上升序列
        int[] down = new int[N];  // down[i] 表示从 i 开始的最长下降序列

        // 初始化 up 和 down 数组
        Arrays.fill(up, 1);
        Arrays.fill(down, 1);

        // 计算 up 数组
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (altitudes[j] < altitudes[i] && altitudes[j] != altitudes[i]) {
                    up[i] = Math.max(up[i], up[j] + 1);
                }
            }
        }

        // 计算 down 数组
        for (int i = N - 1; i >= 0; i--) {
            for (int j = N - 1; j > i; j--) {
                if (altitudes[j] < altitudes[i] && altitudes[j] != altitudes[i]) {
                    down[i] = Math.max(down[i], down[j] + 1);
                }
            }
        }

        // 计算最大浏览景点数
        int maxSpots = 0;
        for (int i = 0; i < N; i++) {
            maxSpots = Math.max(maxSpots, up[i] + down[i] - 1);
        }

        System.out.println(maxSpots);
    }
}
