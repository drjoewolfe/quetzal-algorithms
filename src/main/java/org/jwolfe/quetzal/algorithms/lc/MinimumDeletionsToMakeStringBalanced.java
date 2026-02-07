package org.jwolfe.quetzal.algorithms.lc;

public class MinimumDeletionsToMakeStringBalanced {
    class Solution {
        public int minimumDeletions(String s) {
            if (s == null || s.length() < 2) {
                return 0;
            }

            int n = s.length();

            int[] prefixBCounts = new int[n];
            int bCount = 0;
            for (int i = 0; i < n; i++) {
                prefixBCounts[i] = bCount;

                if (s.charAt(i) == 'b') {
                    bCount++;
                }
            }

            int[] suffixACounts = new int[n];
            int aCount = 0;
            for (int i = n - 1; i >= 0; i--) {
                suffixACounts[i] = aCount;
                if (s.charAt(i) == 'a') {
                    aCount++;
                }
            }

            int minDeletions = n;
            for (int i = 0; i < n; i++) {
                minDeletions = Math.min(minDeletions, suffixACounts[i] + prefixBCounts[i]);
            }

            return minDeletions;
        }
    }

    class Solution_Correct_2 {
        public int minimumDeletions(String s) {
            if (s == null || s.length() < 2) {
                return 0;
            }

            int n = s.length();

            int[] beforeBs = new int[n];
            int[] afterAs = new int[n];

            int bCount = 0;
            int aCount = 0;

            for (int i = 0; i < n; i++) {
                int j = n - i - 1;

                beforeBs[i] = bCount;
                afterAs[j] = aCount;

                char c1 = s.charAt(i);
                if (c1 == 'b') {
                    bCount++;
                }

                char c2 = s.charAt(j);
                if (c2 == 'a') {
                    aCount++;
                }
            }

            int minDeletions = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                int deletions = beforeBs[i] + afterAs[i];
                minDeletions = Math.min(minDeletions, deletions);
            }

            return minDeletions;
        }
    }

    class Solution_Correct_1 {
        public int minimumDeletions(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }

            int n = s.length();
            int[] afterAs = new int[n];
            int[] beforeBs = new int[n];

            int aCount = 0;
            int bCount = 0;
            for (int i = 0; i < n; i++) {
                beforeBs[i] = bCount;
                afterAs[n - i - 1] = aCount;

                if (s.charAt(i) == 'b') {
                    bCount++;
                }

                if (s.charAt(n - i - 1) == 'a') {
                    aCount++;
                }
            }

            int minDeletions = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                minDeletions = Math.min(minDeletions,
                        beforeBs[i] + afterAs[i]);
            }

            return minDeletions;
        }
    }
}

//    1653. Minimum Deletions to Make String Balanced
//    Medium
//    You are given a string s consisting only of characters 'a' and 'b'​​​​.
//
//    You can delete any number of characters in s to make s balanced. s is balanced if there is no pair of indices (i,j) such that i < j and s[i] = 'b' and s[j]= 'a'.
//
//    Return the minimum number of deletions needed to make s balanced.
//
//
//
//    Example 1:
//
//    Input: s = "aababbab"
//    Output: 2
//    Explanation: You can either:
//    Delete the characters at 0-indexed positions 2 and 6 ("aababbab" -> "aaabbb"), or
//    Delete the characters at 0-indexed positions 3 and 6 ("aababbab" -> "aabbbb").
//    Example 2:
//
//    Input: s = "bbaaaaabb"
//    Output: 2
//    Explanation: The only solution is to delete the first two characters.
//
//
//    Constraints:
//
//    1 <= s.length <= 105
//    s[i] is 'a' or 'b'​​.