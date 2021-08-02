package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class WordBreak {
    class Solution {
        public boolean wordBreak(String s, List<String> wordDict) {
            if(s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
                return false;
            }

            int n = s.length();
            boolean[] dp = new boolean[n + 1];
            dp[0] = true;

            Set<String> dict = new HashSet<>(wordDict);
            for(int i = 1; i <= n; i++) {
                for(int j = 0; j < i; j++) {
                    String str = s.substring(j, i);
                    if(dp[j] && dict.contains(str)) {
                        dp[i] = true;
                        break;
                    }

                }
            }

            return dp[n];
        }
    }

    class Solution_Memoized {
        public boolean wordBreak(String s, List<String> wordDict) {
            if(s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
                return false;
            }

            return wordBreak(s, new HashSet(wordDict), 0, s.length(), new HashMap<>());
        }

        private boolean wordBreak(String s, Set<String> wordDict, int start, int end, Map<Integer, Boolean> memo) {
            if(start == end) {
                return true;
            }

            if(memo.containsKey(start)) {
                return memo.get(start);
            }

            boolean rv = false;
            StringBuilder builder = new StringBuilder();
            for(int i = start; i < end; i++) {
                char c = s.charAt(i);
                builder.append(c);

                String word = builder.toString();
                if(wordDict.contains(word)) {
                    if(wordBreak(s, wordDict, i + 1, end, memo)) {
                        rv = true;
                        break;
                    }
                }
            }

            memo.put(start, rv);
            return rv;
        }
    }

    class Solution_Classic {
        public boolean wordBreak(String s, List<String> wordDict) {
            if(s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
                return false;
            }

            Set<String> set = new HashSet(wordDict);
            return wordBreak(s, set, 0, s.length());
        }

        private boolean wordBreak(String s, Set<String> wordDict, int start, int end) {
            if(start == end) {
                return true;
            }

            StringBuilder builder = new StringBuilder();
            for(int i = start; i < end; i++) {
                char c = s.charAt(i);
                builder.append(c);

                String word = builder.toString();
                if(wordDict.contains(word)) {

                    boolean rv = wordBreak(s, wordDict, i + 1, end);
                    if(rv) {
                        return true;
                    }
                }
            }

            return false;
        }
    }
}

//    139. Word Break
//    Medium
//    Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.
//
//    Note that the same word in the dictionary may be reused multiple times in the segmentation.
//
//
//
//    Example 1:
//
//    Input: s = "leetcode", wordDict = ["leet","code"]
//    Output: true
//    Explanation: Return true because "leetcode" can be segmented as "leet code".
//    Example 2:
//
//    Input: s = "applepenapple", wordDict = ["apple","pen"]
//    Output: true
//    Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
//    Note that you are allowed to reuse a dictionary word.
//    Example 3:
//
//    Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
//    Output: false
//
//
//    Constraints:
//
//    1 <= s.length <= 300
//    1 <= wordDict.length <= 1000
//    1 <= wordDict[i].length <= 20
//    s and wordDict[i] consist of only lowercase English letters.
//    All the strings of wordDict are unique.
