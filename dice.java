public class dice{

    public static long printFriendsPairing(String str, String ans){
        if(str.length()==0){
            System.out.println(ans);
            return 1;
        }

        int count=0;
        char ch = str.charAt(0);
        count+=printFriendsPairing(str.substring(1),ans+ch+" ");

        for(int i=1;i<str.length();i++){
            String rstr = str.substring(1,i)+str.substring(i+1);
            count+=printFriendsPairing(rstr, ans+ch+str.charAt(i)+" ");
        }
        return count;
    }

    public static void main(String[] args){
        System.out.println(printFriendsPairing("ABCDE",""));
    }
}