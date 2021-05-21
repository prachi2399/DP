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

    // scramble string 
    HashMap<String,Boolean> map = new HashMap<>();
    public boolean isScramble(String s, String t) {
        if(s.length()!=t.length()||s.length()<1) return false;
        if(s.length()==1||s.equals(t)){
            if (s.equals(t))
                return true;
      }  
        StringBuilder sb = new StringBuilder();
        sb.append(s);
        sb.append(t);
        
        if(map.containsKey(sb.toString())) return map.get(sb.toString());
        
        int n = s.length();
        for(int cut =1; cut<n;cut++){
            if(isScramble(s.substring(0,cut),t.substring(0,cut)) && isScramble(s.substring(cut),t.substring(cut))) {
                map.put(sb.toString(), true);
                return true;
            }
           if(isScramble(s.substring(0,cut),t.substring(n-cut))&&isScramble(s.substring(cut),t.substring(0,n-cut))){
                map.put(sb.toString(), true);
                return true;
           }
      }
        
        map.put(sb.toString(), false);
        return false;
    }

    // leetcode 139 wordbreak
    public boolean wordBreak(String s, List<String> wordDict) {
        
        HashMap<String,Boolean> map = new HashMap<>();
        
        boolean res = wordBreak_(s,new HashSet<>(wordDict),0,map);
    
        return res;
    }
    public boolean wordBreak_(String s, HashSet<String> wordDict, int si,  HashMap<String,Boolean> map) {
        if(si==s.length()) {
            return true;
        }
        
        if(map.containsKey(s.substring(0,si))) return map.get(s.substring(0,si));
                                                           
        for(int cut = si+1;cut<=s.length();cut++){
            String str = s.substring(si,cut);
            if(wordDict.contains(str)&&wordBreak_(s,wordDict,cut,map)) {
                map.put(str,true);
                return true;
            }
        }
        map.put(s.substring(0,si),false);
        return false;
    }

    // maximum segemnt from cutting rod gfg
    public int maximize(int n, int x, int y, int z, int[] dp){
        if(n==0) return dp[n] = 0;
        if(n<0) return -(int)1e8;
        if(dp[n]!=-1) return dp[n];
        
        int maxX = maximize(n-x,x,y,z,dp)+1;
        int maxY = maximize(n-y,x,y,z,dp)+1;
        int maxZ = maximize(n-z,x,y,z,dp)+1;
        
        return dp[n] = max(maxX,maxY,maxZ);
    }
    public int maximizeCuts(int n, int x, int y, int z)
    {
       int[] dp = new int[n+1];
        Arrays.fill(dp,-1);
        int ans = maximize(n,x,y,z,dp);
        return ans>=0?ans:0;
    }

    // russian dolls 
    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        Arrays.sort(envelopes,(a,b)->{
            if(a[0]==b[0]) return b[1]-a[1];
            return a[0]-b[0];
        });
        
        int maxEnvelopes=0;
        int maxAns=0;
        int[] dp = new int[n];
        for(int i=0;i<n;i++){
            dp[i]=1;
            for(int j=i-1;j>=0;j--){
                if(envelopes[j][1]<envelopes[i][1]){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }
            }
            maxEnvelopes = Math.max(maxEnvelopes,dp[i]);
        }
        return maxEnvelopes;
    }
    //minimum cost path
    public void minimumCostPath(int[][] arr, int n, int m){
        int[][] dp = new int[n][m];
      
      for(int i=n-1;i>=0;i--){
          for(int j=m-1;j>=0;j--){
              if(i==n-1&&j==m-1){
                  dp[i][j]=arr[i][j];
              }
              else if(i==n-1){
                  dp[i][j]=dp[i][j+1]+arr[i][j];
              }
              else if(j==m-1){
                  dp[i][j]=dp[i+1][j]+arr[i][j];
              }
              else
                  dp[i][j]= Math.min(dp[i][j+1],dp[i+1][j])+arr[i][j];
          }
      }
      
      System.out.println(dp[0][0]);
      String ans = "";
      LinkedList<Pair> queue = new LinkedList<>();
      queue.addFirst(new Pair("",0,0));
      
      while(queue.size()>0){
          Pair rem = queue.removeFirst();
          if(rem.i==n-1&&rem.j==m-1){
              System.out.println(rem.psf);
          }else if(rem.i==n-1){
              queue.addFirst(new Pair(rem.psf+"H",rem.i,rem.j+1));
          }
          else if(rem.j==m-1){
              queue.addFirst(new Pair(rem.psf+"V",rem.i+1,rem.j));
          }
          else{
              if(dp[rem.i][rem.j+1]<dp[rem.i+1][rem.j]){
                  queue.addFirst(new Pair(rem.psf+"H",rem.i,rem.j+1));
              }
              else if(dp[rem.i+1][rem.j]<dp[rem.i][rem.j+1]){
                  queue.addFirst(new Pair(rem.psf+"V",rem.i+1,rem.j));
              }
              else {
                  queue.addFirst(new Pair(rem.psf+"H",rem.i,rem.j+1));
              }
          }
      }
    }


    // print goldmine path
    public static void printGoldMinePath(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        int[][] dp = new int[arr.length][arr[0].length];
      
      for(int j = arr[0].length - 1; j >= 0; j--){
          for(int i = 0; i < arr.length; i++){
              if(j == arr[0].length - 1){
                  dp[i][j] = arr[i][j];
              } else if(i == 0){
                  dp[i][j] = arr[i][j] + Math.max(dp[i][j + 1], dp[i + 1][j + 1]);
              } else if(i == arr.length - 1){
                  dp[i][j] = arr[i][j] + Math.max(dp[i][j + 1], dp[i - 1][j + 1]);
              } else {
                  dp[i][j] = arr[i][j] + Math.max(dp[i][j + 1], Math.max(dp[i - 1][j + 1], dp[i + 1][j + 1]));
              }
          }
      }
      
      int max = Integer.MIN_VALUE;
      for(int i = 0; i < dp.length; i++){
          if(dp[i][0] > max){
              max = dp[i][0];
          }
      }
      
      System.out.println(max);
      
      ArrayDeque<Pair> que = new ArrayDeque<>();
      
      for(int i = 0; i < dp.length; i++){
          if(dp[i][0] == max){
              que.add(new Pair(i + " ", i, 0));
          }
      }
      
      while(que.size() > 0){
          Pair rem = que.removeFirst();
          
          if(rem.j == arr[0].length - 1){
              System.out.println(rem.psf);
          } else if(rem.i == 0){
              int g = Math.max(dp[rem.i][rem.j + 1], dp[rem.i + 1][rem.j + 1]);
              
              
              if(g == dp[rem.i][rem.j + 1]){
                  que.add(new Pair(rem.psf + "d2 ", rem.i, rem.j + 1));
              }
              
              if(g == dp[rem.i + 1][rem.j + 1]){
                  que.add(new Pair(rem.psf + "d3 ", rem.i + 1, rem.j + 1));
              }
          } else if(rem.i == arr.length - 1){
              int g = Math.max(dp[rem.i][rem.j + 1], dp[rem.i - 1][rem.j + 1]);
              
              
              if(g == dp[rem.i - 1][rem.j + 1]){
                  que.add(new Pair(rem.psf + "d1 ", rem.i - 1, rem.j + 1));
              }
              
              if(g == dp[rem.i][rem.j + 1]){
                  que.add(new Pair(rem.psf + "d2 ", rem.i, rem.j + 1));
              }
          } else {
              int g = Math.max(dp[rem.i][rem.j + 1], Math.max(dp[rem.i - 1][rem.j + 1], dp[rem.i + 1][rem.j + 1]));
              
              if(g == dp[rem.i - 1][rem.j + 1]){
                  que.add(new Pair(rem.psf + "d1 ", rem.i - 1, rem.j + 1));
              }
              
              if(g == dp[rem.i][rem.j + 1]){
                  que.add(new Pair(rem.psf + "d2 ", rem.i, rem.j + 1));
              }
              
              if(g == dp[rem.i + 1][rem.j + 1]){
                  que.add(new Pair(rem.psf + "d3 ", rem.i + 1, rem.j + 1));
              }
          }   
      }
    }

    public static void printPathMinimumJumps(int[] arr){
        int n = arr.length;
        Integer[] dp = new Integer[n];
        dp[n-1]=0;
        for(int i = n-2;i>=0;i--){
            int steps = arr[i];
            int nMin = (int)1e8; 
            for(int j=1;j<=steps&&i+j<n;j++){
                if(dp[i+j]!=null&&dp[i+j]<nMin){
                    nMin = dp[i+j];
                }
            }
            if(nMin!=(int)1e8){
                dp[i]=nMin+1;
            }
            //System.out.print(dp[i]);
        }
        
        System.out.println(dp[0]);
        ArrayDeque<Pair> que = new ArrayDeque<>();
        que.add(new Pair(0,arr[0],dp[0],0+""));
        while(que.size()!=0){
            Pair rem = que.removeFirst();
            if(rem.i==n){
                System.out.println(rem.str+".");
            }
            for(int j=1;j<=rem.s&&rem.i+j<n;j++){
                int ci = rem.i+j;
                if(dp[ci]!=null&&dp[ci]==rem.j-1){
                    que.add(new Pair(ci,arr[ci],dp[ci],rem.str+" -> "+ci));
                }
            }
        }   
    }

    // paint fence
    int mod = (int)1e9+7;
    
    long countWays(int n,int k)
    {
        if(n==0) return 0;
        if(n==1) return k;
         long same=k%mod;
        long diff=(k*(k-1))%mod;
        long total=(same+diff)%mod;
        for(int i=3;i<=n;i++){
            same=(diff)%mod;
            diff=(total*(k-1))%mod;
            total=(same+diff)%mod;
        }
        return (total)%mod;
    }

    // catalan number
    public static int findCatalan(int n)
    {
        int[] dp = new int[n+1];
        for(int i=0;i<=n;i++){
            if(i==0){
                dp[i]=1;
                continue;
            }
            for(int j=i-1,k=0;j>=0;j--,k++){
                dp[i]+=(dp[j]*dp[k]);
            }
        }
        return dp[n];
        
    }
    // Longest Reapeating Subsequence
    public int LongestRepeatingSubsequence(String str)
    {
        int n = str.length();
        int[][] dp = new int[n+1][n+1];
        for(int i=n-1;i>=0;i--){
            for(int j=n-1;j>=0;j--){
               if(str.charAt(i)==str.charAt(j)&&i!=j){
                    dp[i][j]=dp[i+1][j+1]+1;
                }
                else dp[i][j]=Math.max(dp[i][j+1],dp[i+1][j]);
                }
        }
        
        return dp[0][0];
    }
    // lcs 3 string
    int LCSof3(String A, String B, String C, int n, int m, int o) 
    { 
        int[][][] dp = new int[n+1][m+1][o+1];
        
        for(int i=0;i<=n;i++){
            for(int j=0;j<=m;j++){
                for(int k=0;k<=o;k++){
                    if(i==0||j==0||k==0){
                        dp[i][j][k]=0;
                        continue;
                    }
                    
                    if(A.charAt(i-1)==B.charAt(j-1)&&A.charAt(i-1)==C.charAt(k-1)){
                        dp[i][j][k]=1+dp[i-1][j-1][k-1];
                    }
                    else {
                        dp[i][j][k]=Math.max(dp[i-1][j][k],Math.max(dp[i][j-1][k],dp[i][j][k-1]));
                    }
                }
            }
        }
        return dp[n][m][o];
    } 

    // Longest subsequence such that difference between adjacents is one
    static int longestSubsequence(int n, int arr[])
    {
        int[] dp = new int[n];
        
        int maxAns=0;
        for(int i=0;i<n;i++){
            dp[i]=1;
            for(int j=i-1;j>=0;j--){
                if(Math.abs(arr[i]-arr[j])==1){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }
            }
            maxAns=Math.max(maxAns,dp[i]);
        }
        return maxAns;
    }

    //Maximum subsequence sum such that no three are consecutive
    public static int maxSubNothreeConsec(int[]arr, int n){
        int[] dp = new int[n];

        dp[0]=arr[0];
        dp[1]=arr[0]+arr[1];
        dp[2]=Math.max(dp[1],Math.max(arr[0]+arr[2],arr[1]+arr[2]));
        
        for(int i=3;i<n;i++){
            dp[i]=Math.max(Math.max(dp[i-1],dp[i-2]+arr[i]),dp[i-3]+arr[i]+arr[i-1]);        
            }
        return dp[n-1];
    }
    
    public static void maxSubNothreeConsec() {
        int[] arr = {3000,2000,1000,3,10};
        int n =5;
        System.out.println(maxSubNothreeConsec(arr,n));
    }

    // egg drop problem
    public static int eggDrop(int n, int k){
		//write your code here
        int[][] dp = new int[n+1][k+1];

        for(int i=1;i<=n;i++){
            for(int j=1;j<=k;j++){
                if(i==1) dp[i][j]=j;
                else if(j==1) dp[i][j]=1;

                else{
                    int min=(int)1e8;
                    for(int mj = j-1,pj=0;mj>=0;mj--,pj++){
                        int v1 = dp[i][mj];//egg survives
                        int v2 = dp[i-1][pj];//egg breaks;

                        int val = Math.max(v1,v2);
                        min= Math.min(val,min);
                    }
                    dp[i][j]=min+1;
                } 
            }
        }
		return dp[n][k];
	} 

    //optimal game strategy
    public static void optimalStrategy(int[] arr) {
	    int n = arr.length;
        int[][] dp = new int[n][n];

        for(int gap=0;gap<n;gap++){
            for(int i=0,j=gap;j<n;j++,i++){
                if(gap==0) dp[i][j]=arr[i];
                else if(gap==1) dp[i][j]=Math.max(arr[i],arr[j]);
                else{
                    int val1 = arr[i]+Math.min(dp[i+2][j],dp[i+1][j-1]);
                    int val2 = arr[j]+Math.min(dp[i+1][j-1],dp[i][j-2]);
                    int max = Math.max(val1,val2);
                    dp[i][j]=max;
                }
            }
        }
		System.out.println(dp[0][n-1]);
	}

    // Max length chain
    int maxChainLength(Pair arr[], int n)
    {
        Arrays.sort(arr,(a,b)->{
            return a.x-b.x;
        });
        
        int[] dp = new int[n];
        int maxLen=0;
        for(int i=0;i<n;i++){
            dp[i]=1;
            for(int j=i-1;j>=0;j--){
                if(arr[i].x>arr[j].y){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }
            }
            maxLen=Math.max(maxLen,dp[i]);
        }
        return maxLen;
    }

    // maximum submatrix square of 1
    public static int submatrixSqaure(int[][] arr) {
        int[][] dp = new int[arr.length][arr[0].length];
        int ans = 0;

        for(int i=dp.length-1;i>=0;i--){
            for(int j=dp[0].length-1;j>=0;j--){
                if(i==dp.length-1&&j==dp[0].length-1){
                    dp[i][j]=arr[i][j];
                    continue;
                }
                else if(i==dp.length-1){
                    dp[i][j]=arr[i][j];
                    continue;
                }
                else if(j==dp[0].length-1){
                    dp[i][j]=arr[i][j];
                    continue;
                }
                else {
                    if(arr[i][j]==0){
                    dp[i][j]=0;
                    continue;
                    }
                     
                        dp[i][j]=Math.min(Math.min(dp[i][j+1],dp[i+1][j]),dp[i+1][j+1])+1;
                        
                        ans=Math.max(dp[i][j],ans);
                }
            }
        }
        return ans;
	}

    //max difference bw 0 & 1;
    public static int (String str) {
        int ans = 0;
        int cSum=0;
        for(int i=0;i<str.length();i++){
            int val =0;
            if(str.charAt(i)==1){
                val=-1;
            }
            else val=1;

            if(cSum>0){
                cSum+=val;
            }else{
                cSum=val;
            }
            if(ans<cSum){
                ans=cSum;
            }
        }
        return ans==0?-1:ans;
    }

    // min jumps to reach end
    static int minJumps(int[] arr){
        // your code here
        int n = arr.length;
        Integer[] dp = new Integer[n + 1];
        dp[n] = 0;
        for(int i=n-1;i>=0;i--){
            if(arr[i]>0){
                int min=(int)1e8;
                for(int j=1;j<=arr[i]&&i+j<dp.length;j++){
                if(dp[i+j]!=null) 
                    { min=Math.min(min,dp[i+j]);}
                }
                if(min!=(int)1e8){
                    dp[i]=min+1;
                }
            }
        }
        //System.out.println(dp[0]);
        return dp[0];
    }

    // binomial coeffient memo
    static int mod = (int)1e9+7;
    static int nCr(int n, int k, int[][] dp){
        if(n<k) return dp[n][k]=0;
        if(k==0||n==k) return dp[n][k]=1;
        if(dp[n][k]!=-1) return dp[n][k];
        return dp[n][k]=(nCr(n-1,k-1,dp)%mod+nCr(n-1,k,dp)%mod)%mod;
    }
    
    static int nCr(int n, int k){
        int[][] dp = new int[n+1][k+1];
        for(int[] d:dp) Arrays.fill(d,-1);
        return nCr(n,k,dp);
    }

    // binomial coeff dp
    static int mod = (int)1e9+7;
    static int nCr(int N, int K, int[][] dp){
        for(int n=0;n<=N;n++){
            for(int k=0;k<=K;k++){
                if(n<k)  {
                    dp[n][k]=0;
                    continue;
                }
                if(k==0||n==k) {
                    dp[n][k]=1;
                    continue;
                }
             dp[n][k]=(dp[n-1][k-1]%mod+dp[n-1][k]%mod)%mod;
            }
        }
        return dp[N][K];
    }
    
    static int nCr(int n, int k){
        int[][] dp = new int[n+1][k+1];
        //for(int[] d:dp) Arrays.fill(d,-1);
        return nCr(n,k,dp);
    }

    // permutation coeffient memo
    static int mod = (int)1e9+7;
    static int nPr(int n, int k, int[][] dp){
        if(k==0) return dp[n][k]=1;
        if(dp[n][k]!=-1) return dp[n][k];
        return dp[n][k]=(k*nPr(n-1,k-1,dp)%mod+nPr(n-1,k,dp)%mod)%mod;
    }
    
    static int nPr(int n, int k){
        int[][] dp = new int[n+1][k+1];
        for(int[] d:dp) Arrays.fill(d,-1);
        return nPr(n,k,dp);
    }

    // permutation coeff dp
    static int mod = (int)1e9+7;
    static int nPr(int N, int K, int[][] dp){
        for(int n=0;n<=N;n++){
            for(int k=0;k<=K;k++){
                if(k==0) {
                    dp[n][k]=1;
                    continue;
                }
             dp[n][k]=(k*dp[n-1][k-1]%mod+dp[n-1][k]%mod)%mod;
            }
        }
        return dp[N][K];
    }
    
    static int nPr(int n, int k){
        int[][] dp = new int[n+1][k+1];
        //for(int[] d:dp) Arrays.fill(d,-1);
        return nPr(n,k,dp);
    }

    ///count of balance tree of height h
    public int balanceTree(int h){
        if(h==0||h==1) return 1;

        return balanceTree(h-1)*(2*balanceTree(h-2)+balanceTree(h-1));
    }

    public int balanceTreeDP(int h){
        int[] dp=new int[n+1];
        dp[0]=1;
        dp[1]=1;

        for(int h=2;h=H;h++){
            dp[h]+=dp[h-1]*(2dp[h-2]+dp[h-1]);
        }
        return dp[H]; 
    }

    // Count all subsequences having product less than K
    public static int countSubsequence(int[] arr, int k) {
        int n= arr.length;
        int[][] arr = new int[k+1][n+1];

        for(int i=1;i<=k;i++){
            for(int j=1;j<=n;j++){
                dp[i][j]=dp[i][j-1];

                if(arr[j-1]>=0&&arr[j-1]<=i){
                    dp[i][j]+=dp[i/arr[j-1]]+1;
                }
            }
        }
        return dp[n][k];
    }

    // Maximum sum of pairs with specific difference
    public static int maxSumPairWithDifferenceLessThanK(int arr[], int N, int K) 
    {
        // Your code goes here 
        Arrays.sort(arr);
        int[] dp = new int[N];
        for(int i=1;i<N;i++){
            dp[i]=dp[i-1];
            if(arr[i]-arr[i-1]<K){
                if(i>=2) dp[i]=Math.max(dp[i],dp[i-2]+arr[i]+arr[i-1]);
            
                else dp[i]=Math.max(dp[i],arr[i]+arr[i-1]);
            }
        }
        return dp[N-1];
    }

    //
    int minCost=-1;
    public int knapSack(int n, int W, int val[], int wt[])
    {
        int[][] dp = new int[n+1][W+1];
        // for (int i = 0; i <= W; i++)
        //     min_cost[0][i] = Integer.MAX_VALUE;
      
        // // fill 0'th column with 0
        
        for(int[] d:dp) Arrays.fill(d,(int)1e8);
        for (int i = 1; i <= n; i++)
            dp[i][0] = 0;
            
        for(int i=1;i<=n;i++){
            for(int j=1;j<=W;j++){
                if (wt[i-1] > j)
                    dp[i][j] = dp[i-1][j];
      
                else
                    dp[i][j] = Math.min(dp[i-1][j],dp[i][j-wt[i-1]] + val[i-1]);
            }
        }
        return dp[n][W]!=(int)1e8?dp[n][W]:-1;
    }
    
	public int minimumCost(int cost[], int N,int W)
	{

		int count=0;
		for(int ele:cost) {
		    if(ele==-1) count++;
		}
		int n = N-count;
		int[] Wt= new int[N-count];
		int[] val=new int[N-count];
		int k=0;
		for(int i=0;i<N;i++){
		    if(cost[i]==-1) continue;
		    else {
		        val[k]=cost[i];
		        Wt[k]=i+1;
		        k++;
		    }
		}
		
		return knapSack(n,W,val,Wt);
	}

    // largest independent set gfg
    public static class TreeNode{
        int data=0,liss=0;
        TreeNode left=null;
        TreeNode right=null;

        TreeNode(int data, int liss, TreeNode left, TreeNode right){
            this.data=data;
            this.liss=liss;
            this.left=left;
            this.right=right;
        }
    }
    public static int Lis(TreeNode root) {
        if(root==null) return 0;
        if(root.left==null&&root.right==null) return root.liss=1;
        if(root.liss!=0) return root.liss;

        int root_excl=Lis(root.left)+Lis(root.right);

        int root_incl=1;
        if(root.left!=null){
            root_incl+=Lis(root.left.left)+Lis(root.left.right);
        }
        if(root.right!=null){
            root_incl+=Lis(root.right.left)+Lis(root.right.right);
        }
        return Math.max(root_excl,root_incl);
    }

    // minimum removal to make max-min<=k
    public static int minRemoval(int[] arr, int k) {
        int n = arr.length;
        int[][] dp = new int[n][n];
        for(int[] d:dp) Arrays.fill(dp,-1);
        Arrays.sort(arr);
        return minRemoval(arr,k,0,n-1,dp);
    }
    public static int minRemoval(int[] arr, int i, int j, int[][] dp) {
        if(i>=j) return 0;
        if(arr[i]-arr[j]<=K) return dp[i][j]=0;
        if(dp[i][j]!=-1) return dp[i][j];

        else dp[i][j]=Math.min(minRemoval(arr,i+1,j,dp),minRemoval(arr,i,j-1,dp))+1;
        return dp[i][j];
    }
    // longest common substring
    public static int LongestCommonSubstring(String s1, String s2){
		//write your code here
		int[][] dp = new int[s1.length()+1][s2.length()+1];
		int maxLen=0;
		for(int i=1;i<=s1.length();i++){
		    for(int j=1;j<=s2.length();j++){
		        if(s1.charAt(i-1)==s2.charAt(j-1)){
		            dp[i][j]=dp[i-1][j-1]+1;
		        }
		        maxLen=Math.max(maxLen,dp[i][j]);
		    }
		}
		return maxLen;
	}

    // Reach a given score 
    public long count(int n) {
        long[] dp = new long[(int)n+1];
        Arrays.fill(dp, 0);
        dp[0] = 1;
        
        // Add your code here.
        for(int i=3;i<=n;i++){
            dp[i]+=dp[i-3];
        }
        for(int i=5;i<=n;i++){
            dp[i]+=dp[i-5];
        }
        for(int i=10;i<=n;i++){
            dp[i]+=dp[i-10];
        }
        return dp[n];
    }
    // 646. Maximum Length of Pair Chain
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs,(a,b)->{
            return a[0]-b[0];
        });
          
          int n = pairs.length;
          int[] dp = new int[n];
          int maxLen=1;
          for(int i=0;i<n;i++){
              dp[i]=1;
              for(int j=i-1;j>=0;j--){
                  if(pairs[i][0]>pairs[j][1]){
                      dp[i]=Math.max(dp[i],dp[j]+1);
                  }
                  maxLen=Math.max(dp[i],maxLen);
              }
          }
          return maxLen;
    }
    //longest alernating sequence
    public int AlternatingaMaxLength(int[] arr)
    {
        // code here
        int n = arr.length;
        //O(n^2)
        // int[][] dp = new int[n][2];
        // int maxLen=1;
        // for(int i=0;i<n;i++){
        //     dp[i][0]=dp[i][1]=1;
        //     for(int j=i-1;j>=0;j--){
        //         if(arr[j]<arr[i]&&dp[i][0]<dp[j][1]+1){
        //             dp[i][0]=dp[j][1]+1;
        //             maxLen=Math.max(dp[i][0],maxLen);
        //         }else if(arr[j]>arr[i]&&dp[i][1]<dp[j][0]+1){
        //             dp[i][1]=dp[j][0]+1;
        //             maxLen=Math.max(dp[i][1],maxLen);
        //         }
                
        //     }
        // }
        // return maxLen;

        //O(1)
        int inc=1,dec=1;
        for(int i=1;i<n;i++){
            if(arr[i]>arr[i-1]){
                inc=dec+1;
            }else if(arr[i]<arr[i-1]){
                dec=inc+1;
            }
        }
        return  Math.max(inc,dec);
    }

    // ugly numbers2
    public int nthUglyNumber(int n) {
        int uglyNo=1;
        int m_2=2,m_3=3,m_5=5;
        int i_2=0,i_3=0,i_5=0;
        int[] dp = new int[n];
        for(int i=1;i<n;i++){
            uglyNo=Math.min(m_2,Math.min(m_3,m_5));
            dp[i]=uglyNo;
            if(uglyNo==m_2){
                i_2++;
                m_2=dp[i_2]*2;
            }
            if(uglyNo==m_3){
                i_3++;
                m_3=dp[i_3]*3;
            }
            if(uglyNo==m_5){
                i_5++;
                m_5=dp[i_5]*5;
            }
        }
        return uglyNo;
    }
    // form palindrome
    public static int minCharsAdded(String s){
        int n = s.length();
        int[][] dp = new int[n][n];
        int len=0;
        for(int gap=1;gap<n;gap++){
            for(int i=0,j=gap;j<n;i++,j++){
               if(s.charAt(i)!=s.charAt(j)) dp[i][j]=Math.min(dp[i][j-1],dp[i+1][j])+1;
               else if(s.charAt(i)==s.charAt(j)){
                   dp[i][j]=dp[i+1][j-1];
               }
            }
        }
        return dp[0][n-1];
    }
    static int countMin(String str)
    {
        // code here
        int n=str.length();
        return minCharsAdded(str);
        
    }
    
}


