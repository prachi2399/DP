import java.util.LinkedList;
public class l001{
 
    public static int fib_01(int n, int[] dp){
        if(n<=1) dp[n]=n;

        if(dp[n]!=-1) return dp[n];

        int ans=fib_01(n-1,dp)+fib_01(n-2,dp);
        return dp[n]=ans;
    }

    public static int fib_DP(int N, int[] dp){
        
        for(int n=0;n<=N;n++){
        if(n<=1) { 
            dp[n]=n;
            continue;
        }

        if(dp[n]!=-1) return dp[n];

        int ans=dp[n - 1] + dp[n - 2];//fib_01(n-1,dp)+fib_01(n-2,dp);
        
        dp[n] = ans;
        }
        return dp[N];
    }

    public static int fib_Twopointer(int N){
        int a=0,b=1;
        for(int i=0;i<N;i++){
           // System.out.println(a+" ");
            int sum=a+b;
            a=b;
            b=sum;
        }
        return a;
    }

    public static void fibo(){
        int n=5;
        int[] dp=new int[n+1];
        for(int i=0;i<=n;i++){
            dp[i]=-1;
        }

        System.out.println(fib_01(n,dp));
        System.out.println(fib_DP(n,dp));
        System.out.println(fib_Twopointer(n));
   
    }
    
    public static int mazePath_Memo(int sr, int sc, int er, int ec, int[][] dp){
        if(sr==er && sc==ec) return dp[sr][sc]=1;
        
        if(dp[sr][sc]!=0)
          return dp[sr][sc];

        int count=0;
        if(sc+1<=ec)
           count+=mazePath_Memo(sr,sc+1,er,ec,dp);

        if(sr+1<=er)
           count+=mazePath_Memo(sr+1,sc,er,ec,dp);
             
        if(sc+1<=ec&&sr+1<=er)
           count+=mazePath_Memo(sr+1,sc+1,er,ec,dp);
         
        return dp[sr][sc]=count;   
    }

    public static int mazePath_DP(int SR, int SC, int er, int ec, int[][] dp){
       for(int sr=er;sr>=0;sr--){
           for(int sc=ec;sc>=0;sc--){
               if(sr==er && sc==ec) {
                    dp[sr][sc]=1;
                    continue;
               }

                int count=0;
                if(sc+1<=ec)
                  count+=dp[sr][sc+1];//mazePath_Memo(sr,sc+1,er,ec,dp);

                if(sr+1<=er)
                  count+=dp[sr+1][sc];//mazePath_Memo(sr+1,sc,er,ec,dp);

               if(sc+1<=ec&&sr+1<=er)
                  count+=dp[sr+1][sc+1];//mazePath_Memo(sr+1,sc+1,er,ec,dp);
                
                dp[sr][sc]=count; 
           }
       }  
       return dp[SR][SC];
    }

    public static int mazePathInfi_Memo(int sr, int sc, int er, int ec, int[][] dp){ 
        if(sr==er && sc==ec) return dp[sr][sc]=1;
        
        if(dp[sr][sc]!=0)
          return dp[sr][sc];

        int count=0;
        for(int jump=1;sc+jump<=ec;jump++)
           count+=mazePath_Memo(sr,sc+jump,er,ec,dp);

        for(int jump=1;sr+jump<=er;jump++)
           count+=mazePath_Memo(sr+jump,sc,er,ec,dp);

        for(int jump=1;sc+jump<=ec&&sr+jump<=er;jump++)
           count+=mazePath_Memo(sr+jump,sc+jump,er,ec,dp);
         
        return dp[sr][sc]=count; 
    }

    public static int mazePathInfi_DP(int SR, int SC, int er, int ec, int[][] dp){
        for(int sr=er;sr>=0;sr--){
            for(int sc=ec;sc>=0;sc--){ 
            if(sr==er && sc==ec) 
                {
                    dp[sr][sc]=1;
                    continue;
                }
        
            if(dp[sr][sc]!=0)
                return dp[sr][sc];

            int count=0;
            for(int jump=1;sc+jump<=ec;jump++)
                count+=dp[sr][sc+jump];//mazePath_Memo(sr,sc+jump,er,ec,dp);
            
            for(int jump=1;sr+jump<=er;jump++)
                count+=dp[sr+jump][sc];//mazePath_Memo(sr+jump,sc,er,ec,dp);

            for(int jump=1;sc+jump<=ec&&sr+jump<=er;jump++)
                count+=dp[sr+jump][sc+jump];//mazePath_Memo(sr+jump,sc+jump,er,ec,dp);
         
            dp[sr][sc]=count;   
            }   
        }
       return dp[SR][SC]; 
    }
    
    public static void mazePath(){
        int n=5;
        int m=5;
        int[][] dp=new int[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                dp[i][j]=0;
            }
        }
        System.out.print(mazePathInfi_DP(0,0,n-1,m-1,dp));
    }

    public static int diceThrow_Memo(int sr, int N, int[] dp){
        if(sr==N) 
            return dp[sr]=1;

        if(dp[sr]!=0) 
            return dp[sr]; 

       int count=0;
       for(int dice=1;dice<=6&&sr+dice<=N;dice++){
           count+=diceThrow_Memo(sr+dice,N,dp);
       }
       return dp[sr]=count;
    }

    public static int diceThrow_DP(int SR, int N, int[] dp){
        for(int sr=N;sr>=0;sr--){
        if(sr==N){
            dp[sr]=1;
            continue;
        }

        int count=0;
        for(int dice=1;dice<=6&&sr+dice<=N;dice++){
           count+=dp[sr+dice];//diceThrow_DP(sr+dice,N,dp);
        }
            dp[sr]=count;
        }
        return dp[SR];
    }

    public static int diceThrow_Opti(int sr, int N){
        LinkedList<Integer> list=new LinkedList<>();

        list.addFirst(1);
        list.addFirst(1);
        for(int sp=N;sp>=0;sp--){
            if(list.size()<=6){
                list.addFirst(list.getFirst()*2);
            }
            else list.addFirst(list.getFirst()*2-list.removeLast());
        }
        return list.getFirst();
    }
    
    public static void diceThrow(){
        int n=10;
        int[] dp=new int[n+1];
        for(int i=0;i<n;i++){
            dp[i]=0;
        }
        System.out.print(diceThrow_Memo(0,n,dp));
        for(int i=0;i<n;i++){
            System.out.println(dp[i]);
        }
    }

    public int climbStairs_Memo(int n, int[] dp){
        if(n<=1) return dp[n]=1;

        if(dp[n]!=0) return dp[n];
        int count=climbStairs_Memo(n-1,dp)+climbStairs_Memo(n-2,dp);
        return dp[n]=count;
    }

    public static int climbStairs_DP(int N, int [] dp){
        for(int n=0;n<=N;n++){
            if(n<=1) {
                dp[n]=1;
                continue;
            }
            dp[n]=dp[n-1]+dp[n-2];//climbStairs_Memo(n-1,dp)+climbStairs_Memo(n-2,dp);
        }
        return dp[N];
    }

    public int climbStairs(int n){
        int[] dp=new int[n+1];
        //Arrays.fill(dp,0);
        return climbStairs_Memo(n,dp);
    }


    public int minCost_Memo(int n, int[] cost, int[] dp){
        if(n<=1) return dp[n]=1;
        
        if(dp[n]!=0) return dp[n];
        
        int mcost=Math.min(minCost(n-1,cost,dp), minCost(n-1,cost,dp));
        
        return dp[n]=mcost+(n==cost.length?0:cost[n]);
    }

    public int minCostClimbingStairs_DP(int[] cost) {
        int[] dp=new int[cost.length+1];
        //Arrays.fill(dp,-1);
        return minCostClimbingStairs(cost.length, cost, dp);
    }

    public int minCostClimbingStairs(int N, int[] cost,int[] dp) {
        for(int n=0;n<=N;n++){
        if(n<=1) {
             dp[n]=cost[n];
             continue;
        }

        int minCost=Math.min(dp[n-1],dp[n-2]);//minCostClimbingStairs(n-1,cost,dp), minCostClimbingStairs(n-2,cost,dp));
        dp[n]=minCost+(n==cost.length?0:cost[n]);
        }
        return dp[N];
    }

    public int minCostClimbingStairs(int N, int[] cost) {
        int n = cost.length;
        int a=cost[0];
        int b=cost[1];

        for(int i=2;i<n;i++){
            int minCost=Math.min(a,b)+cost[i];
            a=b;
            b=minCost;
        }
        return Math.min(a,b);
    }

    static long mod=(int)1e9+7;

    public static long countFriendsPairings_Memo(int n, long[] dp) 
    { 
       if(n<=1) return dp[n]=1;
       
       if(dp[n]!=0) return dp[n];
       
       long single=countFriendsPairings_Memo(n-1,dp);
       long pairUp=countFriendsPairings_Memo(n-2,dp)*(n-1);
       
       return dp[n]=(single%mod+pairUp%mod)%mod;
    }
    
    public long countFriendsPairings_DP(int N, long[] dp) 
    { 
    for(int n=0;n<=N;n++){    
       if(n<=1) {
           dp[n]=1;
           continue;
       }
       
       long single=dp[n-1];//countFriendsPairings_Memo(n-1,dp);
       long pairUp=dp[n-2]*(n-1);//countFriendsPairings_Memo(n-2,dp)*(n-1);
       
        dp[n]=(single%mod+pairUp%mod)%mod;
    }
    return dp[N];
    }
    
    public static long printFriendsPairing(String str, String ans){
        if(str.length()==0){
            System.out.println(ans);
            return 1;
        }

        char ch=str.charAt(1);
        long count=0;
        count+=printFriendsPairing(str.substring(1),ans+ch+" ");

        for(int i=1;i<str.length();i++){
            String rstr=str.substring(1,i)+str.substring(i+1);
            count+=printFriendsPairing(rstr,ans+ch+str.charAt(i)+" ");
        }
        return count;
    }

    public static long countFriendsPairings(int n) 
    { 
       long[] dp=new long[n+1];
       return countFriendsPairings_Memo(n,dp);
      // System.out.println(printFriendsPairing("ABCDE",""));
   
    }

    public static int goldMine_memo(int r, int c, int[][]mat, int[][] dp, int[][] dir){
        if(c == mat[0].length-1){
            return dp[r][c]=mat[r][c];
        }

        if(dp[r][c]!=-1) return dp[r][c];

        int maxGold=0;
        for(int d=0;d<3;d++){
            int x=r+dir[d][0];
            int y=c+dir[d][1];
            if(x>=0&&x<mat.length)
            maxGold=Math.max(maxGold,goldMine_memo(x,y,mat,dp,dir));
        }
        return dp[r][c]=maxGold+mat[r][c];
    }

    public static int goldMine_DP(int[][]mat, int[][] dp, int[][] dir){
        int n=mat.length;
        int m=mat[0].length;

        for(int c=m-1;c>=0;c--){
            for(int r=n-1;r>=0;r--){
                if(c==mat[0].length-1) {
                    dp[r][c]=mat[r][c];
                    continue;
                }
                 
                int maxGold=0;
                for(int d=0;d<3;d++){
                    int x=r+dir[d][0];
                    int y=c+dir[d][1];
                    if(x>=0&&x<mat.length)
                       maxGold=Math.max(maxGold,dp[x][y]);//goldMine_memo(x,y,mat,dp,dir));
                }
                dp[r][c]=maxGold+mat[r][c];
            }
        }
        int maxGold=0;
        for(int i=0;i<mat.length;i++){
            maxGold=Math.max(maxGold,dp[i][0]);
        }
        return maxGold;
    }
    public static void goldMine() {
        int[][] mat = { { 1, 3, 1, 5 }, { 2, 2, 4, 1 }, { 5, 0, 2, 3 }, { 0, 6, 1, 2 } };

        int[][] dp = new int[mat.length][mat[0].length];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        int[][] dir = new int[3][2];
        dir = { { -1, 1 }, { 0, 1 }, { 1, 1 } };

        int maxGold = 0;
        for (int i = 0; i < mat.length; i++) {
            maxGold = Math.max(maxGold, goldMine_memo(i, 0, mat, dp, dir));
        }

        System.out.println(goldMine_dp(mat, dp, dir));
        print2D(dp);
        System.out.println(maxGold);
    }

    public int numDecodings(String s) {
        
        int[] dp=new int[s.length() + 1];
        
        //Arrays.fill(dp, -1);
        
        return numDecodings_DP(s,0,dp);
    }

    public int numDecodings_MEMO(String s, int i, int[] dp) {
        if(i==s.length()) {
            dp[i]=1;
            return 1;
        }
        
        if (dp[i] != -1)
            return dp[i];
        
        char ch1=s.charAt(i);
        if(ch1=='0') return 0;
         
        int count=0;
        count+=numDecodings_MEMO(s,i+1,dp);
        
        if(i<s.length()-1) {
            char ch2=s.charAt(i+1);
            int num=(ch1-'0')*10+(ch2-'0');
            if(num<=26) count+=numDecodings_MEMO(s,i+2,dp);
        }
        return dp[i]=count;
    }

    public int numDecodings_DP(String s, int I, int[] dp) {
        
        for(int i=s.length();i>=0;i--){
            if(i==s.length()) {
            dp[i]=1;
            continue;
        }
        
        char ch1=s.charAt(i);
        if(ch1=='0') {
            dp[i]=0;
            continue;
        } 
         
        int count=0;
        count+=dp[i+1];//numDecodings(s,i+1,dp);
        
        if(i<s.length()-1) {
            char ch2=s.charAt(i+1);
            int num=(ch1-'0')*10+(ch2-'0');
            
            if(num<=26) {
                count +=dp[i+2];//numDecodings(s,i+2,dp);
            }
        }
            
        dp[i]=count;
    }
        
    return dp[I];
    }

    public int numDecodings_Opti(String s) {
        int a=1, b=0;
        for(int i=s.length()-1;i>=0;i--){        
         
        int count=0;
        char ch1=s.charAt(i);
        
        if(ch1!='0'){
            count+=a;
        if(i<s.length()-1) {
            char ch2=s.charAt(i+1);
            int num=(ch1-'0')*10+(ch2-'0');
            
            if(num<=26) {
                count +=b;
            }
        }
    }
    b=a;
    a=count;
}
    return a;
}

    // numdecoding 2
    public int numDecodings1(String s) {
        if(s.length()==0) return 0;
        long[] dp=new long[s.length()+1];
        //Arrays.fill(dp,-1);
        long ans=numDecodings_DP(s,0,dp);
        return (int)ans;
    }
    //int mod=(int)1e9+7;
    
    public long numDecoding2_Memo(String s, int i, long[] dp){
        if(i==s.length()) {
           return  dp[i]=1;
        }
        
        if(dp[i]!=-1) return dp[i];
        
        long count=0;
        
        if(s.charAt(i)=='0') return 0;
        
        char ch1=s.charAt(i);
        
        if(ch1=='*'){
            count = (count+9*numDecoding2_Memo(s,i+1,dp))%mod;
            if(i<s.length()-1){
                char ch2=s.charAt(i+1);
                if(ch2=='*'){
                    count = (count+15*numDecoding2_Memo(s,i+2,dp))%mod;
                }
                else if(ch2>='0'&&ch2<='6'){
                    count = (count+2*numDecoding2_Memo(s,i+2,dp))%mod;
                }
                else if(ch2>'6'){
                    count = (count+1*numDecoding2_Memo(s,i+2,dp))%mod;
                }
            }
            
        }
        else{
            count = (count+numDecoding2_Memo(s,i+1,dp))%mod;
            if(i<s.length()-1){
                char ch2=s.charAt(i+1);
                if(ch2!='*'){
                int num=(ch1-'0')*10+(ch2-'0');
            
               if(num<=26) {
                count = (count+numDecoding2_Memo(s,i+2,dp))%mod;
            }
            }
            else {
                if(ch1=='1'){
                    count = (count+9*numDecoding2_Memo(s,i+2,dp))%mod;
                }else if(ch1=='2'){
                    count = (count+6*numDecoding2_Memo(s,i+2,dp))%mod;
                }
            }
        }
    }
        return dp[i]=count;
}

public long numDecodin2_Dp(String s, int I, long[] dp){
    for(int i=s.length();i>=0;i--) 
    {if(i==s.length()) {
        dp[i]=1;
        continue;
     }
     
    if(s.charAt(i)=='0') {
         dp[i]=0;
         continue;
    } 
     
    long count=0;
     
    char ch1=s.charAt(i);
     
    if(ch1=='*'){
        count = (count+9*dp[i+1])%mod;
        if(i<s.length()-1){
            char ch2=s.charAt(i+1);
            if(ch2=='*'){
                count = (count+15*dp[i+2])%mod;
            }
            else if(ch2>='0'&&ch2<='6'){
                count = (count+2*dp[i+2])%mod;
            }
            else if(ch2>'6'){
                count = (count+dp[i+2])%mod;
            }
        } 
    }
    else{
        count = (count+dp[i+1]) %mod;
        if(i<s.length()-1){
            char ch2=s.charAt(i+1);
            if(ch2!='*'){
            int num=(ch1-'0')*10+(ch2-'0');
         
            if(num<=26) {
            count = (count+dp[i+2])%mod;
        }
    }
        else {
            if(ch1=='1'){
                count = (count+9*dp[i+2])%mod;
            } else if(ch1=='2'){
                count = (count+6*dp[i+2])%mod;
            }
        }
    }
}
dp[i]=count;
}
 return dp[I];
}

    //int mod=(int)1e9+7;
    public int numDecodings2_Opti(String s) {
        long a=1,b=0;
       for(int i=s.length()-1;i>=0;i--) {     
           long count=0;
     
           char ch1=s.charAt(i);
           if(ch1=='0'){
               count=0;
            }
            else if(ch1=='*'){
                count = (count+9*a)%mod;
                if(i<s.length()-1){
                    char ch2=s.charAt(i+1);
                    if(ch2=='*'){
                        count = (count+15*b)%mod;
                    }
                    else if(ch2>='0'&&ch2<='6'){
                        count = (count+2*b)%mod;
                    }
                    else if(ch2>'6'){
                    count = (count+b)%mod;
                }
            } 
        }
        else{
            count = (count+a) %mod;
            if(i<s.length()-1){
                char ch2=s.charAt(i+1);
                if(ch2!='*'){
                    int num=(ch1-'0')*10+(ch2-'0');
         
                    if(num<=26) {
                        count = (count+b)%mod;
                    }
                }
                else {
                    if(ch1=='1'){
                        count = (count+9*b)%mod;
                    }    
                    else if(ch1=='2'){
                        count = (count+6*b)%mod;
                    }
                }
            }
        }
    }
    b=a;
    a=count;
    return (int)a;
}

    public int numWaysToDivideinK(int n, int k, int[][] dp){
        if(k==1||n==k){
            return dp[n][k]=1;
        }
        int uniqueGroup=numWaysToDivideinK(n-1,k-1,dp);
        int partOfGroup=numWaysToDivideinK(n-1,k,dp)*k;

        return dp[n][k] = uniqueGroup + partOfGroup;
        
    }

    public int numWaysToDivideinK(int n, int k){
        int[][] dp=new int[n][k];
        return numWaysToDivideinK(n,k,dp);
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


    public static void main(String[] args) {
        // fibo();
        // mazePath();
        // boardPath();
        // climbStairs(10);
        // countFriendsPairings(10);
        printFriendsPairing("ABCDE","");
        //goldMine();
    }

}