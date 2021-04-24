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
}