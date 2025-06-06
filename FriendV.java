/*在海拉鲁大陆上，林克从沉睡中苏醒，他发现这片土地充满了各种挑战和谜题。

海拉鲁王国曾经繁荣昌盛，但被灾厄盖侬袭击后，许多地方都变得荒芜和危险。林克肩负着拯救海拉鲁的使命，他需要探索各个角落，恢复王国的生机。

在他的冒险过程中，他发现了一条横贯东西的大河，河有笔直的南北两岸，岸上各有位置各不相同的一些城市。

北岸的每个城市有且仅有一个友好的对应城市在南岸，而且不同据点的友好城市不相同。这些友好城市之间如果能建立起安全的连接通道，将对林克的冒险和海拉鲁的恢复有很大帮助。

但是由于河上存在着各种危险，比如怪物的袭击和神秘力量的干扰。林克需要帮助当地的管理者做出一些决定，来确定哪些连接通道可以被批准建立。使得在保证任意两条通道不相交的情况下，被批准的通道尽量多，以更好地促进各个城市之间的交流和资源共享，助力海拉鲁的重建。


输入

第1行，一个整数N，表示城市数。

第2行到第n+1行，每行两个整数，中间用1个空格隔开，分别表示南岸和北岸的一对友好城市的坐标。

数据范围1≤N≤5000


输出

仅一行，输出一个整数，表示政府所能批准的最多申请数。*/
//经典的 "不交叉连接点对" 问题,使用贪心 + 二分查找方法计算最长递增子序列 (LIS)
/*
先按北城排好顺序
在对应的南城位置中找最长递增子序列
用贪心和二分查找快速找到答案
 */

import java.util.*;

public class FriendV {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);;
        int N = scanner.nextInt();  // 读取城市对数
        int[][]  pairs = new int[N][2];
        // 存储每对城市的南北坐标
        for(int i = 0;i<N;i++){
            pairs[i][0] = scanner.nextInt(); // 北城市坐标
            pairs[i][1] = scanner.nextInt(); // 南城市坐标
        }
        // 按北城市坐标升序排序
        Arrays.sort(pairs,(a,b)->Integer.compare(a[0],b[0]));
        // 提取排序后的南城市坐标数组
        int[] southCites = new int[N];
        for(int i = 0;i<N;i++){
            southCites[i] = pairs[i][1];
        }
        // 使用贪心+二分查找实现的LIS算法
        ArrayList<Integer> lis = new ArrayList<>();
        // 计算最长递增子序列长度
        for(int i = 0;i<N;i++){
            int pos = binarySearch(lis,southCites[i]);
            if(pos<lis.size()){
                lis.set(pos,southCites[i]); // 替换已有元素,每次都用尽可能小的值替换当前的尾元素，以此来保持子序列的 "潜力"，让后续元素有更大可能扩展序列长度
            }
            else lis.add(southCites[i]);  // 扩展序列长度
        }
        // 输出最长递增子序列长度，即最多不交叉的道路数目
        System.out.println(lis.size());
    }
    // 二分查找：找到第一个大于等于value的位置
    private static int binarySearch(ArrayList<Integer> lis,int value){
        int left = 0,right = lis.size()-1;
        while(left<=right){
            int mid = left+(right-left)/2;
            if(lis.get(mid)<value){
                left = mid+1;
            }
            else{
                right = mid-1;
            }
        }
        return left;
    }
}
