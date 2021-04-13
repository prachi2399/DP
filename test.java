import java.util.Arrays;
public class test{

    public static void print2d(int[][] dp){
        for(int i=0;i<dp.length;i++){
            for(int j=0;j<dp[0].length;j++){
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static int goldMine_memo(int r, int c, int[][] mat, int[][] dp, int[][] dir){
            if(c == mat.length-1){
                return dp[r][c]= mat[r][c];
            }

            if(dp[r][c]!=-1) return dp[r][c];
            int maxGold = 0;
            for(int d=0;d<3;d++){
                int x = r + dir[d][0];
                int y = c + dir[d][1];
                if(x>=0 && x < mat.length){
                   maxGold=Math.max(maxGold,goldMine_memo(x, y, mat, dp, dir)); 
                }
            }
            return dp[r][c] = maxGold+mat[r][c];
    }

    public static int goldMine_dp(int[][] mat, int[][] dp, int[][] dir){
        int n=mat.length;
        int m=mat[0].length;

        for(int c = m-1;c>=0;c--){
            for(int r = n-1;r>=0;r--){
                if(c == m-1){
                    dp[r][c]= mat[r][c];
                    continue;
                }
                int maxGold = 0;
                for(int d=0;d<3;d++){
                    int x = r + dir[d][0];
                    int y = c + dir[d][1];
                    if(x>=0 && x < mat.length){
                       maxGold=Math.max(maxGold,dp[x][y]); 
                    }
                }
                dp[r][c] = maxGold+mat[r][c];
            }
        }

        int maxGold = 0;
        for(int r = 0; r<mat.length; r++){
            maxGold=Math.max(maxGold,dp[r][0]);
        }
        return maxGold;
}

    public static void goldMine() {
        int[][] mat = { { 1, 3, 1, 5 }, { 2, 2, 4, 1 }, { 5, 0, 2, 3 }, { 0, 6, 1, 2 } };

        int n = mat.length;
        int m = mat[0].length;

        int[][] dp = new int[n][m];

        int[][] dir = {{1,1}, {0,1}, {-1,1}};
        // for(int[] d:dp) Arrays.fill(d,-1);

        // int maxGold = 0;
        // for(int r=0;r<n;r++){
        //     maxGold=Math.max(maxGold,goldMine_memo(r,0,mat,dp,dir));
        // }
        System.out.println(goldMine_dp(mat,dp,dir));
        print2d(dp);
    }

    public static void main(String[] args){
        goldMine();
    }
}