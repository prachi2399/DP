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

}
