You are given an array of strings arr. A string s is formed by the concatenation of a subsequence of arr that has unique characters.

Return the maximum possible length of s.

A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.

 

Example 1:

Input: arr = ["un","iq","ue"]
Output: 4
Explanation: All the valid concatenations are:
- ""
- "un"
- "iq"
- "ue"
- "uniq" ("un" + "iq")
- "ique" ("iq" + "ue")
Maximum length is 4.
Example 2:

Input: arr = ["cha","r","act","ers"]
Output: 6
Explanation: Possible longest valid concatenations are "chaers" ("cha" + "ers") and "acters" ("act" + "ers").
Example 3:

Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
Output: 26
Explanation: The only string in arr has all 26 characters.
 

Constraints:

1 <= arr.length <= 16
1 <= arr[i].length <= 26
arr[i] contains only lowercase English letters.



Solve:-


class Solution {
    public int maxLength(List<String> arr) {
      Set<String> set = arr.stream().filter(this::unique).collect(Collectors.toSet());
        backtrack(arr, set, 0, new StringBuilder());
        int max = 0;
        for (String word : set) max = Math.max(max, word.length());
        return max;
    }

    private void backtrack(List<String> arr, Set<String> set, int i, StringBuilder sb) {
        if (i == arr.size()) {
            String word = sb.toString();
            if (unique(word)) set.add(word);
            return;
        }

        int len = sb.length();
        sb.append(arr.get(i));
        backtrack(arr, set, i + 1, sb);
        sb.setLength(len);

        backtrack(arr, set, i + 1, sb);
    }

    private boolean unique(String word) {
        Set<Character> set = new HashSet<>();
        for (char c : word.toCharArray()) set.add(c);
        return set.size() == word.length();
  }
}
