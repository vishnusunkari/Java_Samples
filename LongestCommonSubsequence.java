public class LongestCommonSubsequence {
    public static void main(String[] args) {
        String sub = "abc";
        String full = "ahbgdc";
        System.out.println(longestCommonSubsequence(sub, full));
        System.out.println(longestCommonSubsequenceUsingDP(sub, full));
    }

    //DP approach O(m*n)
    public static int longestCommonSubsequenceUsingDP(String sub, String full) {
        int[][] dp = new int[sub.length() + 1][full.length() + 1];
        for (int i = 1; i <= sub.length(); i++) {
            for (int j = 1; j <= full.length(); j++) {
                if (sub.charAt(i - 1) == full.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[sub.length()][full.length()];
    }

    //Easy approach but O(n^3)
    public static int longestCommonSubsequence(String sub, String full) {
        int maxLength = 0;
        // Iterate over all substrings of sub
        for (int start = 0; start < sub.length(); start++) {
            for (int end = start + 1; end <= sub.length(); end++) {
                String substring = sub.substring(start, end);
                if (isSubsequence(substring, full)) {
                    maxLength = Math.max(maxLength, substring.length());
                }
            }
        }
        return maxLength;
    }

    public static boolean isSubsequence(String sub, String full) {
        int p1 = 0, p2 = 0;
        while(p1<sub.length() && p2<full.length()){
            if(sub.charAt(p1) == full.charAt(p2)){
                p1++;
            } 
            p2++;            
        }
        return p1 == sub.length();
    }
}
