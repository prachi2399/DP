public class l003_LIS{

    public int LIS_LR(int[] arr, int i, int[] dp){
        if(dp[i]!=0) 
            return dp[i];

        int longestLen=1;
        for(int j=i-1;j>=0;j--){
            if(arr[j]<arr[i]){
                int len=LIS_LR(arr,j,dp);
                longestLen=Math.max(longestLen,len+1);
            }
        }
        return dp[i]=longestLen;
    }

    public int LIS(int[] arr){
        int n=arr.length;
        int[] dp=new int[n];
        int len=0;
        for(int i=0;i<n;i++){
            len=Math.max(len,LIS_LR(nums,i,dp));
        }
        return len;
    }

    public int lengthOfLIS(int[] arr) {
        int n=arr.length;
        
        int[] dp=new int[n];
        int len=0;
        for(int i=0;i<n;i++){
            dp[i]=1;
            for(int j=i-1;j>=0;j--){
                if(arr[j]<arr[i]){
                    dp[i]=Math.max(dp[i],dp[j]+1);
            }
        }
        len=Math.max(len,dp[i]);
        }
        return len;
    }

    // minimum deletion needed to sort an array range of ele -1e7<=ele<=1e7
    public int minDeletion(int[] arr){
        int n=arr.length;
        int[] dp=new int[n];
        int len=0;

        for(int i=0;i<n;i++){
            dp[i]=1;
            for(int j=i-1;j>=0;j--){
                if(arr[i]<arr[j]){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }
            }
            len=Math.max(len,dp[i]);
        }
        return n - len;
    }

    // max sum increasing sub
    public int maxSumIS(int arr[], int n)  
	{  
	    //int n=arr.length;
        int[] dp=new int[n];
        int len=0,sum=0;
        
        for(int i=0;i<n;i++){
            dp[i]=arr[i];
            for(int j=i-1;j>=0;j--){
                if(arr[j]<arr[i]){
                    dp[i]=Math.max(dp[i],dp[j]+arr[i]);
                }
            }
            len=Math.max(len,dp[i]);
        }
        return len;
	} 

    public int[] LIS_LR(int[] arr){
        int n=arr.length;
        int[] dp=new int[n];
        int len=0;
        for(int i=0;i<n;i++){
            dp[i]=1;
            for(int j=i-1;j>=0;j--){
                if(arr[j]<arr[i]){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }
                len=Math.max(len,dp[i]);
            }
        }
        return dp;
    }

    public int[] LIS_RL(int[] arr){
        int n=arr.length;
        int[] dp=new int[n];
        int len=0;
        for(int i=n-1;i>=0;i--){
            dp[i]=1;
            for(int j=i+1;j<n;j++){
                if(arr[j]<arr[i]){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }
                len=Math.max(len,dp[i]);
            }
        }
        return dp;
    }

    public int LBS_UpHill(int[] arr){
        int[] LIS=LIS_LR(arr); 
        int[] LDS=LIS_RL(arr);

        int len=0;
        for(int i=0;i<n;i++){
            len=Math.max(len,LIS[i]+LDS[i]-1);
        }
        return len;
    }
    
    // LDS _ LR
    public int[] LDS_LR(int[] arr){
        int n=arr.length;
        int[] dp=new int[n];
        int len=0;
        for(int i=0;i<n;i++){
            dp[i]=1;
            for(int j=i-1;j>=0;j--){
                if(arr[j]>arr[i]){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }
                len=Math.max(len,dp[i]);
            }
        }
        return dp;
    }

    //LDS_RL
    public int[] LDS_RL(int[] arr){
        int n=arr.length;
        int[] dp=new int[n];
        int len=0;
        for(int i=n-1;i>=0;i--){
            dp[i]=1;
            for(int j=i+1;j<n;j++){
                if(arr[j]>arr[i]){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }
                len=Math.max(len,dp[i]);
            }
        }
        return dp;
    }

    public int LBS_DownHill(int[] arr){
        int[] LDS_LR=LDS_LR(arr); 
        int[] LDS_RL=LDS_RL(arr);

        int len=0;
        for(int i=0;i<n;i++){
            len=Math.max(len,LDS_LR[i]+LDS_RL[i]-1);
        }
        return len;
    }
    
    // 673
    public int findNumberOfLIS(int[] arr) {
        
        int n=arr.length;
        int[] len=new int[n];
        int[] count=new int[n];
        
        int maxLen=0;
        int maxCount=0;
        
        for(int i=0;i<n;i++){
            len[i]=1;
            count[i]=1;
            for(int j=i-1;j>=0;j--){
                if(arr[j]<arr[i]){
                    if(len[i]<len[j]+1){
                        len[i]=len[j]+1;
                        count[i]=count[j];
                    }
                    else if(len[j]+1==len[i]){
                         count[i]+=count[j];
                    }
                }
            }
            
            if(maxLen<len[i]){
                maxLen=len[i];
                maxCount=count[i];
            } else if(maxLen==len[i]){
                maxCount+=count[i];
            }
        }
        return maxCount;
    }

    // all LIS of maxx Len
    public void allLIS(ArrayList<ArrayList<Integer>> mapping, int[] arr, int idx, int len, String ans){
        if(len == 1) {
            System.out.println(ans + arr[idx]);
            return;
        }

        for (Integer i : mapping.get(len - 1)) {
            if (i < idx && arr[i] < arr[idx]) {
                allLIS(mapping, arr, i, len - 1, ans + arr[idx] + ", ");
            }
        }
    }
    public void findAllLIS(int[] arr) {
      int n=arr.length;
        int[] dp=new int[n];
        int len=0;
        for(int i=0;i<n;i++){
            dp[i]=1;
            for(int j=i-1;j>=0;j--){
                if(arr[j]<arr[i]){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }
                len=Math.max(len,dp[i]);
            }
        }
        ArrayList<ArrayList<Integer>> mapping = new ArrayList<>();
        for (int i = 0; i <= len; i++)
            mapping.add(new ArrayList<>());

        for (int i = 0; i < n; i++) {
            mapping.get(dp[i]).add(i);
        }

        for (Integer i : mapping.get(len)) {
            allLIS(mapping, arr, i, len, "");
        }
    }

    // building  bridges
    public int buildingBridges(int[][] arr){
        Arrays.sort(arr,(a,b)->{
            return a[0]-b[0];
        });

        int n=arr.length;
        int[] dp=new int[n];
        int len=0;

        for(int i=0;i<n;i++){
            dp[i]=1;
            for(int j=i-1;j>=0;j--){
                if(arr[j][0]<arr[i][0]&&arr[j][1]<arr[i][1]){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }
            }
            len=Math.max(len,dp[i]);
        }
        return dp[i];
    }
    public void main(String[] args){
        LIS();
    }
}