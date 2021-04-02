public class l002_String{

    public int longestPalindrominSubseq(String s, int i, int j , int[][] dp){
        if(i>j) return dp[i][j]=(i==j)?1:0;

        if(dp[i][j]!=-1) return dp[i][j];

        if(s.charAt(i)==s.charAt(j)) return dp[i][j]=longestPalindrominSubseq(s,i+1,j-1,dp);
        else return dp[i][j]=Math.max(longestPalindrominSubseq(s,i+1,j,dp),longestPalindrominSubseq(s,i,j-1,dp));
    }

    public int longestPalindrominSubseq_DP(String s, int I, int J , int[][] dp){
        int n=s.length(); 
        for(int gap=0;gap<n;gap++){
            for(int i=0,j=gap;j<n;i++,j++){
                if(i>j) {
                    dp[i][j]=(i==j)?1:0;
                    continue;
                }

                if(s.charAt(i)==s.charAt(j)) dp[i][j]=dp[i+1][j-1];
                else dp[i][j]=Math.max(dp[i][j-1],dp[i+1][j]);
            }
        }
        return dp[I][J];
    }

    public String longestPalindrominSubseq_String(String s, int I, int J , String[][] dp){
        int n=s.length(); 
        for(int gap=0;gap<n;gap++){
            for(int i=0,j=gap;j<n;i++,j++){
                if(i==j){
                    dp[i][j]=s.charAt(i)+"";
                    continue;
                }
                if(s.charAt(i)==s.charAt(j)) dp[i][j]=s.charAt(i)+dp[i+1][j-1]+s.charAt(i);
                else dp[i][j]=dp[i][j-1].length()>dp[i+1][j].length()?dp[i][j-1]:dp[i+1][j];
            }
        }
        return dp[I][J];
    }

    public int longestPalindrominSubseq(String s){
        int n=s.length();
        //int[][] dp=new int[n][n];
        //for(int[] d:dp) Arrays.fill(d,-1);
        //return longestPalindrominSubseq(s,0,n-1,dp);
        //return longestPalindrominSubseq_DP(s,0,n-1,dp);
        String[][] dp=new String[n][n];
        for(String[] d:dp) Arrays.fill(d,"");
        String ans = longestPalindrominSubseq_DP(s,0,n-1,dp); 
        System.out.println(ans);
        return ans.length();
    } 

    public int numDistinct_Memo(String s, String t, int n, int m, int[][] dp){
        if(m==0) return dp[n][m]=1;

        if(n<m) return dp[n][m]=0;

        if(dp[n][m]!=-1) return dp[n][m];

        if(s.charAt(s)==t.charAt(j)){
            dp[i][j]=numDistinct_Memo(s,t,n-1,m-1,dp)+numDistinct_Memo(s,t,n-1,m,dp);
        }
        else dp[i][j]=numDistinct_Memo(s,t,n-1,m,dp);

        return dp[n][m];
    }

    public int numDistinct_DP(String s, String t, int N, int M, int[][] dp){
    
        for(int n=0;n<=N;n++){
            for(int m=0;m<=M;m++){
                if(m==0) {
                    dp[n][m]=1;
                    continue;
                }
                
                if(n==0) continue;
                
                if(s.charAt(n-1)==t.charAt(m-1)){
                    dp[n][m]=dp[n-1][m-1]+dp[n-1][m];
                }
                else dp[n][m]=dp[n-1][m];
            }
        }
        return dp[N][M];
}

    public int maxUncrossedLines(int[] A, int[] B) {
    int N=A.length;
    int M=B.length;
    
    int[][] dp=new int[N+1][M+1];
    
    for(int n=0;n<=N;n++){
        for(int m=0;m<=M;m++){
           
            if(n==0||m==0){
                dp[n][m]=0;
                continue;
            }
            
            if(A[n-1]==B[m-1]) dp[n][m]=dp[n-1][m-1]+1;
            else dp[n][m]=Math.max(dp[n-1][m],dp[n][m-1]);
        }
    }
    return dp[N][M];
    }

    public int numDistinct(String s, String t) {
     int n=s.length();
     int m=t.length();
     
     int[][] dp=new int[n+1][m+1];
     //for(int[] d: dp) Arrays.fill(d,-1);
     //return numDistinct_Memo(s,t,n,m,dp);
        return numDistinct_DP(s,t,n,m,dp);
    }

    // leetode 1458
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n=nums1.length;
        int m=nums2.length;
        int[][] dp=new int[n+1][m+1];
        
        for(int[] d:dp) Arrays.fill(d,-(int)1e7);
        
        return maxDotProduct_Memo(nums1, nums2, n, m, dp);
        
    }
    
    public int maxDotProduct_Memo(int[] nums1, int[] nums2, int n, int m, int[][] dp){
        
        if(n==0||m==0){
            return dp[n][m]=-(int)1e7;
        }
                
        if(dp[n][m]!=-(int)1e7) return dp[n][m];
        int val=nums1[n-1]*nums2[m-1];
        int acceptBothNo=maxDotProduct_Memo(nums1, nums2,n-1,m-1,dp)+val;

        int a=maxDotProduct_Memo(nums1, nums2,n-1,m,dp);
        int b=maxDotProduct_Memo(nums1, nums2,n,m-1,dp);
                
        return dp[n][m]=Math.max(Math.max(acceptBothNo,val),Math.max(a,b));
    }

    // edit distance
    public int minDistance(String word1, String word2) {
        int n=word1.length();
        int m=word2.length();
         
        int[][] dp=new int[n+1][m+1];
        for(int[] d:dp) Arrays.fill(d,-1);
        return minDistance_Memo(word1,word2,n,m,dp);
    }
     
    public int minDistance_Memo(String word1, String word2, int n, int m, int[][] dp){
        if(n==0||m==0){
            return dp[n][m]=(n!=0)?n:m;
        }
         
        if(dp[n][m]!=-1) return dp[n][m];
         
        int insert=minDistance_Memo(word1,word2,n,m-1,dp);
        int delete=minDistance_Memo(word1,word2,n-1,m,dp);
        int replace=minDistance_Memo(word1,word2,n-1,m-1,dp);
         
        if(word1.charAt(n-1)==word2.charAt(m-1))
            return dp[n][m]=replace;
        else 
            return dp[n][m]=Math.min(Math.min(insert,delete),replace)+1;
    }

    //follow up with cost of each operstion given 
    public int minDistance_Memo(String word1, String word2, int n, int m, int[] cost, int[][] dp){
        if(n==0||m==0){
            return dp[n][m]=(n!=0)?n*cost[1]:m*cost[0];
        }
         
        if(dp[n][m]!=-1) return dp[n][m];
         
        int insert=minDistance_Memo(word1,word2,n,m-1,dp);
        int delete=minDistance_Memo(word1,word2,n-1,m,dp);
        int replace=minDistance_Memo(word1,word2,n-1,m-1,dp);
         
        if(word1.charAt(n-1)==word2.charAt(m-1))
            return dp[n][m]=replace;
        else 
            return dp[n][m]=Math.min(Math.min(insert+cost[0],delete+cost[1]),replace+cost[2])+1;
    }

    // count palindromic subsequence gfg
    int mod=(int)1e9+7;
    long countPS(String str, int i, int j, long[][] dp)
    {
       if(i>=j){
           return dp[i][j]=(i==j)?1:0;
       }
       
       if(dp[i][j]!=-1) return dp[i][j];
       
       long x=countPS(str,i+1,j,dp);
       long y=countPS(str,i,j-1,dp);
       long z=countPS(str,i+1,j-1,dp);
       
       return dp[i][j]=str.charAt(i)!=str.charAt(j)?(x+y-z+mod)%mod:(x+y+1+mod)%mod;
    }
    
    long countPS(String str)
    {
        int n=str.length();
        long[][] dp=new long[n][n];
        for(long[] d:dp) Arrays.fill(d,-1);
        return countPS(str, 0, n-1, dp);
    }

    public void main(String[] args){
        int ans=longestPalindrominSubseq("aeggab");
        System.out.println(ans);
    }

}