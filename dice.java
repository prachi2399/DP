public class dice{

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

    public static void diceThrow(){
        int n=10;
        int[] dp=new int[n+1];
        for(int i=0;i<n;i++){
            dp[i]=0;
        }
        System.out.print(diceThrow_Memo(0,n,dp));
        for(int i=0;i<=n;i++){
            System.out.println(dp[i]);
        }
    }

    public static void main(String[] args){
        diceThrow();
    }
}