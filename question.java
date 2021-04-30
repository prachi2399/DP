public class question{
    //leetcode 647
    public int countSubstrings(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        
        int count=0;
        
        for(int gap=0;gap<n;gap++){
            for(int i=0,j=gap;j<n;i++,j++){
                if(gap==0) dp[i][j]=1;
                else if(gap==1) dp[i][j]= s.charAt(i)==s.charAt(j)?2:0;
                else if(s.charAt(i)==s.charAt(j)){
                    dp[i][j]=dp[i+1][j-1]!=0?dp[i+1][j-1]+2:0;
                }
                if(dp[i][j]!=0) count++;
            }
        }
        return count; 
    }

    // palindrome partition
    public List<List<String>> partition(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        
        int count=0;
        
        for(int gap=0;gap<n;gap++){
            for(int i=0,j=gap;j<n;i++,j++){
                if(gap==0) dp[i][j]=1;
                else if(gap==1) dp[i][j]= s.charAt(i)==s.charAt(j)?2:0;
                else if(s.charAt(i)==s.charAt(j)){
                    dp[i][j]=dp[i+1][j-1]!=0?dp[i+1][j-1]+2:0;
                }
                if(dp[i][j]!=0) count++;
            }
        }
        List<List<String>> ans = new ArrayList<>();
        partition(s,0,n,dp,ans,new ArrayList<>());
        
        return ans;
    }
    
    
    public void partition(String s, int si, int n, int[][] dp, List<List<String>> ans,List<String> temp) {
        
        if(si==n) {
            ans.add(new ArrayList<>(temp));
            return;
        }
        List<String> str = new ArrayList<>();
        for(int i = si; i<n;i++){
            if(dp[si][i]!=0){
                temp.add(s.substring(si,i+1)); 
                partition(s,i+1,n,dp,ans,temp);
                temp.remove(temp.size()-1);
            }
        }
    }

    //leetcode 1745
    public boolean checkPartitioning(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        
        int count=0;
        
        for(int gap=0;gap<n;gap++){
            for(int i=0,j=gap;j<n;i++,j++){
                if(gap==0) dp[i][j]=1;
                else if(gap==1) dp[i][j]= s.charAt(i)==s.charAt(j)?2:0;
                else if(s.charAt(i)==s.charAt(j)){
                    dp[i][j]=dp[i+1][j-1]!=0?dp[i+1][j-1]+2:0;
                }
                if(dp[i][j]!=0) count++;
            }
        }
     
        for(int i=0;i<n-2;i++){
            for(int j=i+1;j<n-1;j++){
            if(dp[0][i]!=0&&dp[i+1][j]!=0&&dp[j+1][n-1]!=0) 
                return true;
            }
        }
        return false;
    }

}