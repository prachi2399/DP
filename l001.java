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
    public static void main(String[] args){
        //fibo();
        mazePath();
    }
}