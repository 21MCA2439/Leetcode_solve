Given a string s, return the longest palindromic substring in s.

A string is called a palindrome string if the reverse of that string is the same as the original string.

 

Example 1:

Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.
Example 2:

Input: s = "cbbd"
Output: "bb"
 

Constraints:

1 <= s.length <= 1000
s consist of only digits and English letters.

Solve:-


class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][]dp = new boolean[n][n];
        int i = 0, j = 0;
        for(int gap=0;gap<n;gap++) {
            int row = 0;
            for(int col=gap;col<n;col++) {
                if(gap == 0) 
                    dp[row][col] = true;
                else if(gap == 1)
                    dp[row][col] = s.charAt(row) == s.charAt(col);
                else 
                    dp[row][col] = s.charAt(row) == s.charAt(col) && dp[row+1][col-1];
                
                if(dp[row][col]) {
                    i = row;
                    j = col;
                }
                row++;
            }
        }
        return s.substring(i, j+1);
    }
}