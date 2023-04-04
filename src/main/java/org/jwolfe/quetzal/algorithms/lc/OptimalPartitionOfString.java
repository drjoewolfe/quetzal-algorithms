package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class OptimalPartitionOfString {
    class Solution {
        public int partitionString(String s) {
            if(s == null || s.length() == 0) {
                return 0;
            }

            int n = s.length();
            int partitionCount = 1;
            int[] seen = new int[26];

            for(int i = 0; i < n; i++) {
                char c = s.charAt(i);
                int index = c - 'a';

                if(seen[index] > 0) {
                    partitionCount++;
                    Arrays.fill(seen, 0);
                }

                seen[index]++;
            }

            return partitionCount;
        }
    }

    class Solution_Correct_1 {
        public int partitionString(String s) {
            if(s == null || s.length() == 0) {
                return 0;
            }

            int partitionCount = 1;
            Set<Character> currentPartition = new HashSet<>();
            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if(currentPartition.contains(c)) {
                    partitionCount++;
                    currentPartition.clear();
                }

                currentPartition.add(c);
            }

            return partitionCount;
        }
    }
}

//    2405. Optimal Partition of String
//    Medium
//    Given a string s, partition the string into one or more substrings such that the characters in each substring are unique. That is, no letter appears in a single substring more than once.
//
//    Return the minimum number of substrings in such a partition.
//
//    Note that each character should belong to exactly one substring in a partition.
//
//
//
//    Example 1:
//
//    Input: s = "abacaba"
//    Output: 4
//    Explanation:
//    Two possible partitions are ("a","ba","cab","a") and ("ab","a","ca","ba").
//    It can be shown that 4 is the minimum number of substrings needed.
//    Example 2:
//
//    Input: s = "ssssss"
//    Output: 6
//    Explanation:
//    The only valid partition is ("s","s","s","s","s","s").
//
//
//    Constraints:
//
//    1 <= s.length <= 105
//    s consists of only English lowercase letters.