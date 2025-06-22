// Java program to find the longest
// palindromic substring.


class LongestPalindromeSubstring {

    // Function to check if a substring 
    // s[low..high] is a palindrome
    static boolean checkPal(String s, int low, int high) {
        while (low < high) {
            if (s.charAt(low) != s.charAt(high))
                return false;
            low++;
            high--;
        }
        return true;
    }

    // Function to find the longest palindrome substring
    static String longestPalindrome(String s) {

        // Get length of input string
        int n = s.length();

        // All substrings of length 1 are palindromes
        int maxLen = 1, start = 0;

        // Nested loop to mark start and end index
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {

                // Check if the current substring is a palindrome
                if ((j - i + 1) > maxLen && checkPal(s, i, j))  {
                    start = i;
                    maxLen = j - i + 1;
                }
            }
        }

        return s.substring(start, start + maxLen);
    }


    // Function to find the longest palindrome substring
    static String longestPalindromeOrderN(String s) {
        int n = s.length();
        if (n == 0) return "";

        int start = 0, maxLen = 1;

        // Traverse the input string
        for (int i = 0; i < n; i++) {

            // THIS RUNS TWO TIMES 
            // for both odd and even length
            // palindromes. j = 0 means odd
            // and j = 1 means even length
            for (int j = 0; j <= 1; j++) {
                int low = i;
                int high = i + j; 

                // Expand substring while it is a palindrome
                // and in bounds
                while (low >= 0 && high < n && s.charAt(low) == s.charAt(high)) {
                    int currLen = high - low + 1;
                    if (currLen > maxLen) {
                        start = low;
                        maxLen = currLen;
                    }
                    low--;
                    high++;
                }
            }
        }

        return s.substring(start, start + maxLen);
    }

    public static void main(String[] args) {
        String s = "forgeeksskeegfor";
        System.out.println(longestPalindrome(s));
    }
    
}
