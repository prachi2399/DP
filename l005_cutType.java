public class l005_cutType{

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
    
    public static int mcm_memo(int[] arr, int si, int ei, int[][] dp){
        if(si+1==ei){
            return dp[si][ei]=0;
        }

        if(dp[si][ei]!=0) return dp[si][ei];

        int minAns=(int)1e9;
        for(int cut=si+1;cut<ei;cut++){
            int lans=mcm_memo(arr,si,cut,dp);
            int rans=mcm_memo(arr,cut,ei,dp);

            minAns=Math.min(minAns,lans+arr[si]*arr[cut]*arr[ei]+rans);
        }
        return dp[si][ei]=minAns;
    }

    public static int mcm_dp(int[] arr, int SI, int EI, int[][] dp){

        for(int gap=0;gap<arr.length;gap++){
            for(int si=0;ei=gap;ei<arr.length;si++,ei++){
                if(si+1==ei){
                    dp[si][ei]=0;
                    continue;
                }
        
                int minAns=(int)1e9;
                for(int cut=si+1;cut<ei;cut++){
                    int lans=dp[si][cut];//mcm_memo(arr,si,cut,dp);
                    int rans=dp[cut][ei];//mcm_memo(arr,cut,ei,dp);
        
                    minAns=Math.min(minAns,lans+arr[si]*arr[cut]*arr[ei]+rans);
                }
                dp[si][ei]=minAns;
            }
        }
        return dp[SI][EI];
    }

     // cost of one multiplication is 3$ and cost of 1 addition is 5$.
     public static int mcm_dp2(int[] arr, int SI, int EI, int[][] dp) {

        int n = arr.length;
        for (int gap = 1; gap < n; gap++) {
            for (int si = 0, ei = gap; ei < n; si++, ei++) {
                if (si + 1 == ei) {
                    dp[si][ei] = 0;
                    continue;
                }

                int minAns = (int) 1e9;
                for (int cut = si + 1; cut < ei; cut++) {
                    int lans = dp[si][cut];// mcm_memo(arr, si, cut, dp);
                    int rans = dp[cut][ei];// mcm_memo(arr, cut, ei, dp);

                    minAns = Math.min(minAns, lans + arr[si] * (3 * arr[cut] + 5 * (arr[cut] - 1)) * arr[ei] + rans);
                }

                dp[si][ei] = minAns;
            }
        }

        return dp[SI][EI];
    }

    public static class pair{
        int minValue=(int)1e9;

        String minExpression="";

        pair(){

        }

        pair(int minValue, String minExpression){
            this.minValue=minValue;

            this.minExpression=minExpression;
        }
    }

    public static pair mcm_memoString(int[] arr,String [] mat, int si, int ei, pair[][] dp){
        if(si+1==ei){
            return dp[si][ei] = new pair(0,mat[si]+"");
        }

        if(dp[si][ei]!=null) return dp[si][ei];

        pair minAns=new pair();
        for(int cut=si+1;cut<ei;cut++){
            pair lans=mcm_memoString(arr,mat,si,cut,dp);
            pair rans=mcm_memoString(arr,mat,cut,ei,dp);

            minAns.minValue=Math.min(minAns.minValue,lans.minValue+arr[si]*arr[cut]*arr[ei]+rans.minValue);
            minAns.minExpression="("+lans.minExpression+" "+mat[cut]+" "+rans.minExpression+")"; 
        }
        return dp[si][ei]=minAns;
    }

    public static void matrixMultiplication()
    {
        int[] arr = { 40, 20, 30, 10, 30 };
        String [] mat={"A","B","C","D","E"};
        int n = arr.length;
        //int[][] dp = new int[n][n];
        pair[][] dp=new pair[n][n];
        //System.out.println(mcm_dp(arr, 0, n - 1, dp));
        System.out.println(mcm_memoString(arr, mat, 0, n - 1, dp));
        //print2D(dp);
    }
    // min max value of expression
    public static class Pair{
        int minValue=(int)1e9;
        int maxValue=-(int)1e9;

        String minExpression="";
        String maxExpression="";

        Pair(){

        }

        Pair(int minValue, int maxValue, String minExpression, String maxExpression){
            this.minValue=minValue;
            this.maxValue=maxValue;
            this.minExpression=minExpression;
            this.maxExpression=maxExpression;
        }
    }

    public static int evaluate(int a, int b, char ch){
        if(ch=='+') return a+b;
        else return a*b;
    }

    public static Pair minMaxEvaluation(String str, int si, int ei, Pair[][] dp){
        if(si==ei){
            int val=str.charAt(si)-'0';
            return new Pair(val,val,val+"",val+"");
        }

        if(dp[si][ei]!=null) return dp[si][ei];

        Pair res=new Pair();
        for(int cut=si+1;cut<ei;cut+=2){
            Pair lans = minMaxEvaluation(str,si,cut-1,dp);
            Pair rans = minMaxEvaluation(str,cut+1,ei,dp);

            int minValue=evaluate(lans.minValue,rans.minValue,str.charAt(cut));
            int maxValue=evaluate(lans.maxValue,rans.maxValue,str.charAt(cut));

            //res.minValue=Math.max(res.minValue,minValue);
            //res.maxValue=Math.max(res.maxValue,maxValue);

            if(minValue<res.minValue){
                res.minValue=minValue;
                res.minExpression="("+lans.minExpression+" "+str.charAt(cut)+" "+rans.minExpression+")";   
            }

            if (maxValue > res.maxValue) {
                res.maxValue = maxValue;
                res.maxExpression = "(" + lans.maxExpression + " " + str.charAt(cut) + " " + rans.maxExpression
                        + ")";
            }
        }
        return dp[si][ei]=res;
    }
    public static void minMaxEvaluation(){
        String str="1+2*3+4*5";
        int n=str.length();
        Pair[][] dp=new Pair[n][n];

        Pair res=minMaxEvaluation(str,0,n-1,dp);

        System.out.println("Min Value: " + res.minValue + "\nMin Expression: " + res.minExpression);
        System.out.println("Max Value: " + res.maxValue + "\nMax Expression: " + res.maxExpression);
    }

    public static String longestPalindromic(String str){
        int n=str.length();
        int[][] dp=new int[n][n];
        int len=0;
        int si=0;
        int count=0;
        for(int gap=0;gap<n;gap++){
            for(int i=0,j=gap;j<n;i++,j++){
                if(gap==0) dp[i][j]=1;
                else if(gap==1) dp[i][j]=str.charAt(i)==str.charAt(j)?2:0;

                else
                    dp[i][j]=str.charAt(i)==str.charAt(j) && dp[i+1][j-1]!=0? dp[i+1][j-1] + 2 : 0;
                
                if(dp[i][j]>len){
                    len=dp[i][j];
                    si=i;
                }
                if(dp[i][j]!=0) count++;
            }
        }
        return str.substring(si,si+len);
    }

    public int minCut_memo(String str, int si, int ei, boolean[][] isPalindrome, int[] dp){
        if(isPalindrome[si][n-1]) return dp[si]=0

        if(dp[si]!=-1) return dp[si];

        int minAns=(int)1e9;
        for(int cut=si;cut<n;cut++){
            if(isPalindrome[si][cut]){
                minAns=Math.min(minAns,minCut_memo(str,cut+1,n,isPalindrome,dp)+1);
            }
        }
        return dp[si]=minAns;
    }
    public int minCut(String str){
        int n=str.length();
        boolean[][] isPalindrome=new boolean[n][n];
        for(int gap=0;gap<n;gap++){
            for(int i=0,j=gap;j<n;i++,j++){
                if(gap==0) isPalindrome[i][j]=true;
                else if(gap==1) dp[i][j]=str.charAt(i)==str.charAt(j);

                else
                isPalindrome[i][j]=str.charAt(i)==str.charAt(j) && isPalindrome[i+1][j-1];
            }
        }

        int[] dp = new int[n];
        Arrays.fill(dp,-1);
        return minCut_memo(str, 0, n, isPalindrome, dp);
    }

    public int maxCoins(int[] nums, int si, int ei, int[][] dp) {
        if(dp[si][ei]!=-1) return dp[si][ei];

        int lval=si-1==-1?1:nums[si-1];
        int rval=ei+1==nums.length?1:nums[ei+1];

        for(int cut=si;sut<=ei;cut++){
            int lans=maxCoins(nums,si,cut-1,dp);
            int rans=maxCoins(nums,cut+1,ei,dp);

            maxAns=Math.max(maxAns,lans+lval*nums[cut]*rval+rans);
        }
        return dp[si][ei]=maxAns;
    }
    public int maxCoins(int[] nums) {
        int n = nums.length;
        if(n==0) return 0;
        int[][]dp=new int[n][n];
        for(int[] d:dp)  Arrays.fill(d,-1);
        
        return maxCoins(nums,0,n-1,dp);
        
    }

    //1039
    public int minScoreTriangulation(int[] values, int si, int ei, int[][]dp) {
        if(ei-si<=1) return dp[si][ei]=0;
        if(dp[si][ei]!=-1) return dp[si][ei];
        
        int minAns=(int)1e9;
        for(int cut=si+1;cut<ei;cut++){
            int lans = minScoreTriangulation(values,si,cut,dp);
            int rans = minScoreTriangulation(values,cut,ei,dp);
            
            minAns=Math.min(minAns,lans+values[si]*values[cut]*values[ei]+rans);
        }
        return dp[si][ei]=minAns;
    }
    
    public int minScoreTriangulation(int[] values) {
        int n = values.length;
        if(n==0) return 0;
        int[][]dp=new int[n][n];
        for(int[] d:dp)  Arrays.fill(d,-1);
        
        return minScoreTriangulation(values,0,n-1,dp);
    }
    
    public static void main(String[] args){
        matrixMultiplication();
        //minMaxEvaluation();
    }
}