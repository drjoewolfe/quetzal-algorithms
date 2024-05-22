package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {
    class Solution {
        public List<List<String>> partition(String s) {
            List<List<String>> palindromePartitions = new ArrayList<>();
            if (s == null || s.length() == 0) {
                return palindromePartitions;
            }

            int n = s.length();
            boolean[][] dp = new boolean[n][n];
            generatePalindromePartitions(s, 0, new ArrayList<>(), dp, palindromePartitions);

            return palindromePartitions;
        }

        private void generatePalindromePartitions(String s, int left, List<String> currentPartition, boolean[][] dp, List<List<String>> palindromePartitions) {
            if (left == s.length()) {
                palindromePartitions.add(new ArrayList<>(currentPartition));
                return;
            }

            char a = s.charAt(left);
            for (int right = left; right < s.length(); right++) {
                char b = s.charAt(right);

                if (a == b
                        && (right - left <= 2
                        || dp[left + 1][right - 1])) {
                    dp[left][right] = true;

                    String str = s.substring(left, right + 1);
                    currentPartition.add(str);
                    generatePalindromePartitions(s, right + 1, currentPartition, dp, palindromePartitions);
                    currentPartition.remove(currentPartition.size() - 1);
                }
            }
        }
    }

    class Solution_Correct_3 {
        public List<List<String>> partition(String s) {
            List<List<String>> palindromePartitions = new ArrayList<>();
            if (s == null || s.length() == 0) {
                return palindromePartitions;
            }

            int n = s.length();
            boolean[][] dp = new boolean[n][n];
            generatePalindromePartitions(s, 0, new ArrayList<>(), dp, palindromePartitions);

            return palindromePartitions;
        }

        private void generatePalindromePartitions(String s, int start, List<String> current, boolean[][] dp, List<List<String>> palindromePartitions) {
            if (start == s.length()) {
                // Completed partitioning
                palindromePartitions.add(new ArrayList<>(current));
                return;
            }

            char a = s.charAt(start);
            for (int end = start; end < s.length(); end++) {
                char b = s.charAt(end);

                if (a == b && (end - start <= 2 || dp[start + 1][end - 1])) {
                    dp[start][end] = true;

                    String str = s.substring(start, end + 1);
                    current.add(str);
                    generatePalindromePartitions(s, end + 1, current, dp, palindromePartitions);

                    current.remove(current.size() - 1);
                }
            }
        }
    }

    class Solution_Correct_2 {
        public List<List<String>> partition(String s) {
            List<List<String>> palindromePartitions = new ArrayList<>();
            if (s == null || s.length() == 0) {
                return palindromePartitions;
            }

            partition(s, 0, new ArrayList<>(), palindromePartitions);
            return palindromePartitions;
        }

        private void partition(String s, int index, List<String> currentPartition, List<List<String>> palindromePartitions) {
            if (index == s.length()) {
                // Successful palindrome partitioning
                List<String> partitioning = new ArrayList<>(currentPartition);
                palindromePartitions.add(partitioning);
                return;
            }

            for (int i = index; i < s.length(); i++) {
                String str = s.substring(index, i + 1);
                if (!isPalindrome(str)) {
                    continue;
                }

                currentPartition.add(str);
                partition(s, i + 1, currentPartition, palindromePartitions);
                currentPartition.remove(currentPartition.size() - 1);
            }
        }

        private boolean isPalindrome(String str) {
            int i = 0;
            int j = str.length() - 1;

            while (i < j) {
                if (str.charAt(i) != str.charAt(j)) {
                    return false;
                }

                i++;
                j--;
            }

            return true;
        }
    }

    class Solution_Correct_1 {
        public List<List<String>> partition(String s) {
            List<List<String>> allPalindromePartitions = new ArrayList<>();
            if (s == null || s.length() == 0) {
                return allPalindromePartitions;
            }

            constructPalindromicPartitions(s, 0, new ArrayList<>(), allPalindromePartitions);
            return allPalindromePartitions;
        }

        private void constructPalindromicPartitions(String s, int pivotIndex, List<String> currentPartitions, List<List<String>> allPalindromePartitions) {
            if (pivotIndex == s.length()) {
                List<String> partitions = new ArrayList<>(currentPartitions);
                allPalindromePartitions.add(partitions);
                return;
            }

            for (int i = pivotIndex; i < s.length(); i++) {
                String str = s.substring(pivotIndex, i + 1);
                if (!isPalindrome(str)) {
                    continue;
                }

                currentPartitions.add(str);
                constructPalindromicPartitions(s, i + 1, currentPartitions, allPalindromePartitions);
                currentPartitions.remove(currentPartitions.size() - 1);
            }
        }

        private boolean isPalindrome(String s) {
            int n = s.length();
            int l = 0;
            int r = n - 1;
            while (l < r) {
                if (s.charAt(l++) != s.charAt(r--)) {
                    return false;
                }
            }

            return true;
        }
    }
}

//    131. Palindrome Partitioning
//    Medium
//
//    5317
//
//    158
//
//    Add to List
//
//    Share
//    Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.
//
//    A palindrome string is a string that reads the same backward as forward.
//
//
//
//    Example 1:
//
//    Input: s = "aab"
//    Output: [["a","a","b"],["aa","b"]]
//    Example 2:
//
//    Input: s = "a"
//    Output: [["a"]]
//
//
//    Constraints:
//
//    1 <= s.length <= 16
//    s contains only lowercase English letters.