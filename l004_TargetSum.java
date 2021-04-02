public class l004_TargetSum{

    public static void print1D(int[] arr) {
        for (int ele : arr) {
            System.out.print(ele + " ");
        }
        System.out.println();
    }

    public static void print2D(int[][] arr) {
        for (int[] ar : arr) {
            print1D(ar);
        }
    }

    public static int coinChangePermutation(int[] arr, int tar, int[] dp){
        if(tar==0) return dp[tar]=1;

        if(dp[tar]!=-1) return dp[tar];

        int count=0;
        for(int ele:arr){
            if(tar-ele>=0){
            count+=coinChangePermutation(arr,tar-ele,dp);
            }
        }
        return dp[tar]=count;
    }

    public static int coinChangePermutation_DP(int[] arr, int Tar, int[] dp){
        dp[0]=1;
        for(int tar=0;tar<=Tar;tar++){
            for(int ele:arr){
                if(tar-ele>=0){
                    dp[tar]+=dp[tar-ele];//coinChangePermutation(arr,tar-ele,dp);
                }
            }
        }
        return dp[Tar];
    }

    public static int coinChangeCombination_DP(int[] arr, int Tar, int[] dp){
        dp[0]=1;
        
        for(int ele:arr){
            for(int tar=0;tar<=Tar;tar++){
                if(tar-ele>=0){
                    dp[tar]+=dp[tar-ele];//coinChangePermutation(arr,tar-ele,dp);
                }
            }
        }
        return dp[Tar];
    }
    public static int coinChangePermutation(int[] arr, int tar){
      int n=arr.length;
      int[] dp=new int[n+1];
    return coinChangePermutation(arr,tar,dp);  
    }

    public static void coinChnage() {
        int[] arr = { 2, 3, 5, 7 };
        int tar = 10;

        int[] dp = new int[tar + 1];
        System.out.println(coinChangeCombination_DP(arr, tar, dp));

        print1D(dp);
    }


    // leetcode 518
    public int change(int Tar, int[] arr) {
        int[] dp = new int[Tar + 1];
        
        dp[0]=1;
        
        for(int ele:arr){
            for(int tar=0;tar<=Tar;tar++){
                if(tar-ele>=0){
                    dp[tar]+=dp[tar-ele];//coinChangePermutation(arr,tar-ele,dp);
                }
            }
        }
        return dp[Tar];
    }

    //leetcode 377
    public int combinationSum4(int[] arr, int Tar) {
        int[] dp = new int[Tar + 1];
        dp[0] = 1;
        for (int tar = 0; tar <= Tar; tar++) {
            for (int ele : arr) {
                if (tar - ele >= 0) {
                    dp[tar] += dp[tar - ele];
                }
            }
        }

        return dp[Tar];
    }

    // leetcode 322
    public int coinChange(int[] coins, int target, int[] dp){
        if(target==0) return 0;
        
        if(dp[target]!=(int)1e9) return dp[target];
        
        int minCoins=(int)1e8;
        for(int ele:coins){
            if(target-ele>=0){
                minCoins=Math.min(minCoins,coinChange(coins,target-ele,dp)+1);
            }
        }
        return dp[target]=minCoins;
    }
    public int coinChange(int[] coins, int amount) {
        int n=coins.length;
        int[] dp=new int[amount+1];
        //Arrays.fill(dp,(int)1e9);
        
        int ans=coinChange(coins, amount, dp);
        
        return ans!=(int)1e8?ans:-1;
    }
    //

    public static int numberOfSolution(int[] arr, int Tar){
        int[] dp=new int[Tar+1];

        for(int tar=0;tar<=Tar;tar++){
            for(int ele:arr){
                if(tar-ele>=0){
                    dp[tar]+=dp[tar-ele];
                }
            }
        }
        return dp[Tar];
    }

    public static int numberOfSolution(int[]arr, int Tar, int oTar, int idx, int[] coeff){
        if(Tar==0){
            for(int i=0;i<arr.length;i++){
                System.out.print(arr[i]+"("+coeff[i]+")");
                if(i!=arr.length-1){
                    System.out.print("+");
                }
            }
            System.out.println("="+oTar);
            return 1;
        }

        int count=0;
        for(int i=idx;i<arr.length;i++){
            if(Tar-arr[i]>=0){
                coeff[i]++;
                count+=numberOfSolution(arr,Tar-arr[i],oTar,i,coeff);
                coeff[i]--;
            }
        }
        return count;
    }
    public static void numberOfSolution(){
        int[] arr={2,3,5,7};
        int tar=10;
        System.out.println(numberOfSolution(arr,tar,tar,0,new int[arr.length]));
    }

    // https://www.geeksforgeeks.org/subset-sum-problem-dp-25/

    public static int targetSum(int[] arr, int n, int tar, int[][] dp) {
        if (n == 0 || tar == 0) {
            return dp[n][tar] = (tar == 0) ? 1 : 0;
        }

        if (dp[n][tar] != -1)
            return dp[n][tar];

        boolean res = false;
        if (tar - arr[n - 1] >= 0)
            res = res || (targetSum(arr, n - 1, tar - arr[n - 1], dp) == 1);

        res = res || (targetSum(arr, n - 1, tar, dp) == 1);

        return dp[n][tar] = res ? 1 : 0;
    }

    public static boolean targetSum_DP(int[] arr, int Tar) {
        int N = arr.length;
        boolean[][] dp = new boolean[N + 1][Tar + 1];

        for (int n = 0; n <= N; n++) {
            for (int tar = 0; tar <= Tar; tar++) {
                if (n == 0 || tar == 0) {
                    dp[n][tar] = (tar == 0);
                    continue;
                }

                if (tar - arr[n - 1] >= 0)
                    dp[n][tar] = dp[n][tar] || dp[n - 1][tar - arr[n - 1]];
                dp[n][tar] = dp[n][tar] || dp[n - 1][tar];
            }
        }

        return dp[N][Tar];
    }

    public static int targetSumTotalWays_DP(int[] arr, int Tar) {
        int N = arr.length;
        int[][] dp = new int[N + 1][Tar + 1];

        for (int n = 0; n <= N; n++) {
            for (int tar = 0; tar <= Tar; tar++) {
                if (n == 0 || tar == 0) {
                    dp[n][tar] = (tar == 0) ? 1 : 0;
                    continue;
                }

                if (tar - arr[n - 1] >= 0)
                    dp[n][tar] += dp[n - 1][tar - arr[n - 1]];
                dp[n][tar] += dp[n - 1][tar];
            }
        }
        print2D(dp);
        return dp[N][Tar];
    }

    public static void targetSum(int[] arr, int tar) {
        int n = arr.length;
        int[][] dp = new int[n + 1][tar + 1];
        for (int i=0;i<=n;i++)
            for(int j=0;j<=tar;j++){
                dp[i][j]=-1;
            }
        //boolean res = targetSum(arr, n, tar, dp) == 1;
        int res = targetSumTotalWays_DP(arr,tar);
        System.out.println(res);
    }

    //leetcode 416
    public boolean canPartition(int[] arr) {
        int sum=0;
        for(int ele:arr) sum+=ele;
        
        if(sum%2!=0) return false;
        int Tar=sum/2;
        
         int N = arr.length;
        boolean[][] dp = new boolean[N + 1][Tar + 1];

        for (int n = 0; n <= N; n++) {
            for (int tar = 0; tar <= Tar; tar++) {
                if (n == 0 || tar == 0) {
                    dp[n][tar] = (tar == 0);
                    continue;
                }

                if (tar - arr[n - 1] >= 0)
                    dp[n][tar] = dp[n][tar] || dp[n - 1][tar - arr[n - 1]];
                dp[n][tar] = dp[n][tar] || dp[n - 1][tar];
            }
        }

        return dp[N][Tar];
    }  
    
    public static void main(String[] args){
        //coinChnage();
        //numberOfSolution();
        
        targetSum(new int[] { 2, 3, 5, 7 }, 10);
    }

}