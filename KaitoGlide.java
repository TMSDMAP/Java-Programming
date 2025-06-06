/*怪盗基德是一个充满传奇色彩的怪盗，专门以珠宝为目标的超级盗窃犯。

而他最为突出的地方，就是他每次都能逃脱中村警部的重重围堵，而这也很大程度上是多亏了他随身携带的便于操作的滑翔翼。

有一天，怪盗基德像往常一样偷走了一颗珍贵的钻石，不料却被柯南小朋友识破了伪装，而他的滑翔翼的动力装置也被柯南踢出的足球破坏了。

不得已，怪盗基德只能操作受损的滑翔翼逃脱。

假设城市中一共有N幢建筑排成一条线，每幢建筑的高度各不相同。

初始时，怪盗基德可以在任何一幢建筑的顶端。

他可以选择一个方向逃跑，但是不能中途改变方向（因为中森警部会在后面追击）。

因为滑翔翼动力装置受损，他只能往下滑行（即：只能从较高的建筑滑翔到较低的建筑）。

他希望尽可能多地经过不同建筑的顶部，这样可以减缓下降时的冲击力，减少受伤的可能性。

请问，他最多可以经过多少幢不同建筑的顶部(包含初始时的建筑)？


输入

输入数据第一行是一个平行的整数K，代表有K组测试数据。

每组测试数据包含两行：第一行是一个整数N，代表有N幢建筑。第二行包含N个不同的整数，每一个对应一幢建筑的高度h，按照建筑的排列顺序给出。

数据范围

1≤K≤100

1≤N≤100

0<h<10000


输出

对于每一组测试数据，输出一行，包含一个整数，代表怪盗基德最多可以经过的建筑数量。*/

//找到从任意点出发的最长连续下降或上升路径
import java.util.Scanner;
import java.util.Stack;

public class KaitoGlide {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        while(K-->0){
            int N = sc.nextInt();
            int[] heights = new int[N];
            for(int i = 0;i<N;i++){
                heights[i] = sc.nextInt();
            }
            // 计算从左到右的最长严格下降子序列
            int max1 = longestDescending(heights);
            // 反转数组，以便计算从右到左的最长严格下降子序列
            reverse(heights);
            int max2 = longestDescending(heights);
            // 输出两种方向中的最大值
            System.out.println(Math.max(max1, max2));
        }

    }
    // 计算数组中的最长严格下降子序列长度
    static int longestDescending(int[] arr){
        int N = arr.length;
        int[] dp = new int[N]; // dp[i] 表示以 arr[i] 结尾的最长严格下降子序列长度
        int max = 1; // 记录全局最长长度

        for(int i = 0;i<N;i++){
            dp[i] = 1;  // 初始化以当前元素结尾的最长子序列长度为1
            for(int j = 0;j<i;j++){   // 检查当前元素之前的所有元素
                if(arr[i]<arr[j]){  // 如果之前的元素比当前元素大，可以构成下降序列
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }  // 更新当前元素结尾的最长子序列长度
            }
            max = Math.max(max, dp[i]);  // 更新全局最长长度
        }
        return max;
    }

    static void reverse(int[] arr){   // 反转数组元素的顺序
        int l = 0,r = arr.length-1;  // 左右指针分别指向数组首尾
        while(l<r){    // 交换左右指针所指元素，向中间移动指针直到相遇
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            l++;
            r--;
        }
    }
}
