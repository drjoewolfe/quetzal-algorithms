package org.jwolfe.quetzal.algorithms.lc;

public class CountVowelStringsInRanges {
    class Solution {
        public int[] vowelStrings(String[] words, int[][] queries) {
            if (words == null || words.length == 0 || queries == null || queries.length == 0) {
                return new int[0];
            }

            int n = words.length;

            // At index i, stores number of words that start & end with a vowel, before index i.
            int[] oddCounts = new int[n + 1];
            for (int i = 0; i < n; i++) {
                String word = words[i];
                oddCounts[i + 1] = oddCounts[i];

                char sc = word.charAt(0);
                char ec = word.charAt(word.length() - 1);
                if (isVowel(sc) && isVowel(ec)) {
                    oddCounts[i + 1]++;
                }
            }

            int m = queries.length;
            int[] results = new int[m];
            for (int i = 0; i < m; i++) {
                int[] query = queries[i];
                int start = query[0];
                int end = query[1];

                results[i] = oddCounts[end + 1] - oddCounts[start];
            }

            return results;
        }

        private boolean isVowel(char c) {
            return (c == 'a')
                    || (c == 'e')
                    || (c == 'i')
                    || (c == 'o')
                    || (c == 'u');
        }
    }

    class Solution_Correct_1 {
        public int[] vowelStrings(String[] words, int[][] queries) {
            if (words == null || words.length == 0 || queries == null || queries.length == 0) {
                return new int[0];
            }

            int n = words.length;
            int[] prefixCounts = new int[n + 1];

            for (int i = 0; i < n; i++) {
                String word = words[i];
                char sc = word.charAt(0);
                char ec = word.charAt(word.length() - 1);

                prefixCounts[i + 1] = prefixCounts[i];
                if (isVowel(sc) && isVowel(ec)) {
                    prefixCounts[i + 1]++;
                }
            }

            int m = queries.length;
            int[] results = new int[m];
            for (int i = 0; i < m; i++) {
                int[] query = queries[i];

                int left = query[0];
                int right = query[1];

                results[i] = prefixCounts[right + 1] - prefixCounts[left];
            }

            return results;
        }

        private boolean isVowel(char c) {
            return c == 'a'
                    || c == 'e'
                    || c == 'i'
                    || c == 'o'
                    || c == 'u';
        }
    }
}

//    2559. Count Vowel Strings in Ranges
//    Medium
//    You are given a 0-indexed array of strings words and a 2D array of integers queries.
//
//    Each query queries[i] = [li, ri] asks us to find the number of strings present in the range li to ri (both inclusive) of words that start and end with a vowel.
//
//    Return an array ans of size queries.length, where ans[i] is the answer to the ith query.
//
//    Note that the vowel letters are 'a', 'e', 'i', 'o', and 'u'.
//
//
//
//    Example 1:
//
//    Input: words = ["aba","bcb","ece","aa","e"], queries = [[0,2],[1,4],[1,1]]
//    Output: [2,3,0]
//    Explanation: The strings starting and ending with a vowel are "aba", "ece", "aa" and "e".
//    The answer to the query [0,2] is 2 (strings "aba" and "ece").
//    to query [1,4] is 3 (strings "ece", "aa", "e").
//    to query [1,1] is 0.
//    We return [2,3,0].
//    Example 2:
//
//    Input: words = ["a","e","i"], queries = [[0,2],[0,1],[2,2]]
//    Output: [3,2,1]
//    Explanation: Every string satisfies the conditions, so we return [3,2,1].
//
//
//    Constraints:
//
//    1 <= words.length <= 105
//    1 <= words[i].length <= 40
//    words[i] consists only of lowercase English letters.
//    sum(words[i].length) <= 3 * 105
//    1 <= queries.length <= 105
//    0 <= li <= ri < words.length