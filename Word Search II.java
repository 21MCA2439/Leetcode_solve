Given an m x n board of characters and a list of strings words, return all words on the board.

Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

 

Example 1:


Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
Output: ["eat","oath"]
Example 2:


Input: board = [["a","b"],["c","d"]], words = ["abcb"]
Output: []
 

Constraints:

m == board.length
n == board[i].length
1 <= m, n <= 12
board[i][j] is a lowercase English letter.
1 <= words.length <= 3 * 104
1 <= words[i].length <= 10
words[i] consists of lowercase English letters.
All the strings of words are unique.


Solve:-


class TrieNode {
  public TrieNode[] children = new TrieNode[26];
  public String word;
}

class Solution {
    public List<String> findWords(char[][] board, String[] words) {
    for (final String word : words)
      insert(word);

    List<String> ans = new ArrayList<>();

    for (int i = 0; i < board.length; ++i)
      for (int j = 0; j < board[0].length; ++j)
        dfs(board, i, j, root, ans);

    return ans;
  }

  private TrieNode root = new TrieNode();

  private void insert(final String word) {
    TrieNode node = root;
    for (final char c : word.toCharArray()) {
      final int i = c - 'a';
      if (node.children[i] == null)
        node.children[i] = new TrieNode();
      node = node.children[i];
    }
    node.word = word;
  }

  private void dfs(char[][] board, int i, int j, TrieNode node, List<String> ans) {
    if (i < 0 || i == board.length || j < 0 || j == board[0].length)
      return;
    if (board[i][j] == '*')
      return;

    final char c = board[i][j];
    TrieNode child = node.children[c - 'a'];
    if (child == null)
      return;
    if (child.word != null) {
      ans.add(child.word);
      child.word = null;
    }

    board[i][j] = '*';
    dfs(board, i + 1, j, child, ans);
    dfs(board, i - 1, j, child, ans);
    dfs(board, i, j + 1, child, ans);
    dfs(board, i, j - 1, child, ans);
    board[i][j] = c;    
    }
}