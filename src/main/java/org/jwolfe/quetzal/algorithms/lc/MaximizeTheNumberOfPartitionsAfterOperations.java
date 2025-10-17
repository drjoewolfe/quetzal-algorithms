package org.jwolfe.quetzal.algorithms.lc;

public class MaximizeTheNumberOfPartitionsAfterOperations {
    class Solution {
        public int maxPartitionsAfterOperations(String s, int k) {
            if (s == null || s.length() == 0 || k < 1) {
                return 0;
            }

            int n = s.length();

            int[][] left = new int[n][3];
            int[][] right = new int[n][3];

            int segmentCount = 0;
            int currentSegmentMask = 0;
            int currentSegmentDistinctCharacterCount = 0;

            for (int i = 0; i < n - 1; i++) {
                char c = s.charAt(i);
                int val = c - 'a';
                int binary = 1 << val;

                if ((currentSegmentMask & binary) == 0) {
                    // New character
                    currentSegmentDistinctCharacterCount++;

                    if (currentSegmentDistinctCharacterCount <= k) {
                        // Current segment can accomodate
                        currentSegmentMask |= binary;
                    } else {
                        // Current segment exceeed allowed length
                        segmentCount++;
                        currentSegmentDistinctCharacterCount = 1;
                        currentSegmentMask = binary;
                    }
                }

                left[i + 1][0] = segmentCount;
                left[i + 1][1] = currentSegmentMask;
                left[i + 1][2] = currentSegmentDistinctCharacterCount;
            }

            segmentCount = 0;
            currentSegmentMask = 0;
            currentSegmentDistinctCharacterCount = 0;

            for (int i = n - 1; i > 0; i--) {
                char c = s.charAt(i);
                int val = c - 'a';
                int binary = 1 << val;

                if ((currentSegmentMask & binary) == 0) {
                    // New character
                    currentSegmentDistinctCharacterCount++;

                    if (currentSegmentDistinctCharacterCount <= k) {
                        // Current segment can accomodate
                        currentSegmentMask |= binary;
                    } else {
                        // Current segment exceeed allowed length
                        segmentCount++;
                        currentSegmentDistinctCharacterCount = 1;
                        currentSegmentMask = binary;
                    }
                }

                right[i - 1][0] = segmentCount;
                right[i - 1][1] = currentSegmentMask;
                right[i - 1][2] = currentSegmentDistinctCharacterCount;
            }

            int maxSegments = 0;
            for (int i = 0; i < n; i++) {
                int totalSegments = left[i][0] + right[i][0] + 2;
                int totalMask = left[i][1] | right[i][1];
                int totalDistinctCharacters = Integer.bitCount(totalMask);

                if (left[i][2] == k && right[i][2] == k && totalDistinctCharacters < 26) {
                    totalSegments++;
                } else if (Math.min(totalDistinctCharacters + 1, 26) <= k) {
                    totalSegments--;
                }

                maxSegments = Math.max(maxSegments, totalSegments);
            }

            return maxSegments;
        }
    }
}

//    3003. Maximize the Number of Partitions After Operations
//    Hard
//    You are given a string s and an integer k.
//
//    First, you are allowed to change at most one index in s to another lowercase English letter.
//
//    After that, do the following partitioning operation until s is empty:
//
//    Choose the longest prefix of s containing at most k distinct characters.
//    Delete the prefix from s and increase the number of partitions by one. The remaining characters (if any) in s maintain their initial order.
//    Return an integer denoting the maximum number of resulting partitions after the operations by optimally choosing at most one index to change.
//
//
//
//    Example 1:
//
//    Input: s = "accca", k = 2
//
//    Output: 3
//
//    Explanation:
//
//    The optimal way is to change s[2] to something other than a and c, for example, b. then it becomes "acbca".
//
//    Then we perform the operations:
//
//    The longest prefix containing at most 2 distinct characters is "ac", we remove it and s becomes "bca".
//    Now The longest prefix containing at most 2 distinct characters is "bc", so we remove it and s becomes "a".
//    Finally, we remove "a" and s becomes empty, so the procedure ends.
//    Doing the operations, the string is divided into 3 partitions, so the answer is 3.
//
//    Example 2:
//
//    Input: s = "aabaab", k = 3
//
//    Output: 1
//
//    Explanation:
//
//    Initially s contains 2 distinct characters, so whichever character we change, it will contain at most 3 distinct characters, so the longest prefix with at most 3 distinct characters would always be all of it, therefore the answer is 1.
//
//    Example 3:
//
//    Input: s = "xxyz", k = 1
//
//    Output: 4
//
//    Explanation:
//
//    The optimal way is to change s[0] or s[1] to something other than characters in s, for example, to change s[0] to w.
//
//    Then s becomes "wxyz", which consists of 4 distinct characters, so as k is 1, it will divide into 4 partitions.
//
//
//
//    Constraints:
//
//    1 <= s.length <= 104
//    s consists only of lowercase English letters.
//    1 <= k <= 26