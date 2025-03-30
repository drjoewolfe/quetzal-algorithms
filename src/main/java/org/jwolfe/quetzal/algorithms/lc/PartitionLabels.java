package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class PartitionLabels {
    class Solution {
        public List<Integer> partitionLabels(String S) {
            List<Integer> partitionSizes = new ArrayList<>();
            if (S == null || S.length() == 0) {
                return partitionSizes;
            }

            int n = S.length();

            int[] lastOccurance = new int[26];
            for (int i = 0; i < n; i++) {
                char c = S.charAt(i);
                lastOccurance[c - 'a'] = i;
            }

            int partitionStart = 0;
            int partitionEnd = 0;

            for (int i = 0; i < n; i++) {
                char c = S.charAt(i);
                int index = c - 'a';

                partitionEnd = Math.max(partitionEnd, lastOccurance[index]);
                if (partitionEnd == i) {
                    int size = partitionEnd - partitionStart + 1;
                    partitionSizes.add(size);

                    partitionStart = i + 1;
                }
            }

            return partitionSizes;
        }
    }

    class Solution_Correct_1 {
        public List<Integer> partitionLabels(String S) {
            List<Integer> partitionSizes = new ArrayList<>();
            if (S == null || S.length() == 0) {
                return partitionSizes;
            }

            int[] rights = new int[26];
            Arrays.fill(rights, -1);
            for (int i = 0; i < S.length(); i++) {
                char c = S.charAt(i);
                rights[c - 'a'] = i;
            }

            int left = 0;
            int right = 0;
            for (int i = 0; i < S.length(); i++) {
                char c = S.charAt(i);
                right = Math.max(right, rights[c - 'a']);

                if (right == i) {
                    partitionSizes.add(right - left + 1);
                    left = i + 1;
                }
            }

            return partitionSizes;
        }

        private void print(int[] arr) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }

            System.out.println();
        }
    }

    class Solution_FirstAttempt {
        public List<Integer> partitionLabels(String S) {
            List<Integer> partitionSizes = new ArrayList<>();
            if (S == null || S.length() == 0) {
                return partitionSizes;
            }

            int[] leftIndices = new int[26];
            int[] rightIndices = new int[26];

            Arrays.fill(leftIndices, -1);
            Arrays.fill(rightIndices, -1);

            int n = S.length();
            for (int i = n - 1; i >= 0; i--) {
                char c = S.charAt(i);

                int index = c - 'a';
                leftIndices[index] = i;
                if (rightIndices[index] == -1) {
                    rightIndices[index] = i;
                }
            }

            PriorityQueue<CharacterPositions> minHeap = new PriorityQueue<>((cp1, cp2) -> cp1.leftIndex - cp2.leftIndex);
            for (int i = 0; i < 26; i++) {
                if (leftIndices[i] == -1) {
                    continue;
                }

                minHeap.offer(new CharacterPositions(i, leftIndices[i], rightIndices[i]));
            }

            CharacterPositions cp = minHeap.poll();
            int partitionStart = cp.leftIndex;
            int partitionEnd = cp.rightIndex;

            while (!minHeap.isEmpty()) {
                cp = minHeap.poll();

                if (partitionEnd > cp.rightIndex) {
                    continue;
                }

                if (partitionStart < cp.leftIndex
                        && partitionEnd < cp.leftIndex) {
                    partitionSizes.add(partitionEnd - partitionStart + 1);
                    partitionStart = cp.leftIndex;
                    partitionEnd = cp.rightIndex;
                } else {
                    partitionEnd = cp.rightIndex;
                }
            }

            partitionSizes.add(partitionEnd - partitionStart + 1);
            return partitionSizes;
        }

        class CharacterPositions {
            int c;
            int leftIndex;
            int rightIndex;

            public CharacterPositions(int c, int leftIndex, int rightIndex) {
                this.c = c;
                this.leftIndex = leftIndex;
                this.rightIndex = rightIndex;
            }
        }

        private void print(int[] arr) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }

            System.out.println();
        }
    }
}

//    763. Partition Labels
//    Medium
//    You are given a string s. We want to partition the string into as many parts as possible so that each letter appears in at most one part. For example, the string "ababcc" can be partitioned into ["abab", "cc"], but partitions such as ["aba", "bcc"] or ["ab", "ab", "cc"] are invalid.
//
//    Note that the partition is done so that after concatenating all the parts in order, the resultant string should be s.
//
//    Return a list of integers representing the size of these parts.
//
//
//
//    Example 1:
//
//    Input: s = "ababcbacadefegdehijhklij"
//    Output: [9,7,8]
//    Explanation:
//    The partition is "ababcbaca", "defegde", "hijhklij".
//    This is a partition so that each letter appears in at most one part.
//    A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits s into less parts.
//    Example 2:
//
//    Input: s = "eccbbbbdec"
//    Output: [10]
//
//
//    Constraints:
//
//    1 <= s.length <= 500
//    s consists of lowercase English letters.