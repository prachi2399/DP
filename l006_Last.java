public class l006_Last{
    public int numTrees(int n) {
        int[] ans = new int[n+1];
        ans[0]=1;
        for(int i=1;i<=n;i++){
            for(int j=i-1,k=0;j>=0;j--,k++){
                ans[i]+=ans[j]*ans[k];
            }
        }
        return ans[n];
    }

    // leetcode 940
    public int distinctSubseqII(String S) {
        S='$'+S;
        int n = S.length(); 
        int[] dp = new int[n];
        int[] lastOccurence = new int[26];
        int mod = (int)1e9+7;
        dp[0]=1;
        for(int i=1;i<n;i++){
            char ch = S.charAt(i);
            int idx = ch-'a';
            dp[i]=(dp[i-1]*2)%mod;
            if(lastOccurence[idx]!=0){
                dp[i]=(dp[i]-dp[lastOccurence[idx]-1]+mod)%mod;
            }
            lastOccurence[idx]=i;
        }
        return dp[n-1]-1;
    }


    //
    public int[][] minChanges(String s, int k){
        int n = s.length();
        int[][] minChangesDP = new int[n][n];
        for(int gap =1;gap<n;gap++){
            for(int i=0,j=gap;j<n;i++,j++){
                if(gap==1) minChangesDP[i][j] = s.charAt(i)==s.charAt(j)?0:1;
                else minChangesDP[i][j] = s.charAt(i)==s.charAt(j)?minChangesDP[i+1][j-1]:minChangesDP[i+1][j-1]+1;
            }
        }
        return minChangesDP;
    }
    public int palindromePartition(String s, int k, int si, int[][] minChangesDP, int[][] dp){
        int n = s.length();
        if(n-si<=k) return dp[si][k] = n-si==k?0:(int)1e9;

        if(k==1) return dp[si][k] = minChangesDP[si][n-1];

        if(dp[si][k]!=-1) return dp[si][k];

        int minAns = (int) 1e9;
        for(int cut = si; cut<n-1;cut++){
            int minChanges = minChangesDP[si][cut];
            int minChangesRec = palindromePartition(s,k-1,cut+1,minChangesDP,dp);
            if(minChangesRec!=(int)1e9){
                minAns= Math.min(minAns,minChanges+minChangesRec);
            }
        }
        return dp[si][k]=minAns;
    }
    public int palindromePartition(String s, int k) {
        int n = s.length();
         int[][] minChangesDP = minChanges(s,k);
         int[][] dp = new int[n][k+1];
         for(int[] d:dp) Arrays.fill(d,-1);
         return palindromePartition(s,k,0,minChangesDP,dp);
    }
}