package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;

public class CountBinarySubstrings {
    class Solution {
        public int countBinarySubstrings(String s) {
            if (s == null || s.length() < 2) {
                return 0;
            }

            int n = s.length();

            int[] groups = new int[n];
            int groupIndex = 0;
            groups[groupIndex] = 1;
            for (int i = 1; i < n; i++) {
                if (s.charAt(i - 1) != s.charAt(i)) {
                    groupIndex++;
                }

                groups[groupIndex]++;
            }

            int count = 0;
            for (int i = 1; i < n; i++) {
                count += Math.min(groups[i - 1], groups[i]);
            }

            return count;
        }
    }

    class Solution_Correct_1 {
        public int countBinarySubstrings(String s) {
            if (s == null || s.length() < 2) {
                return 0;
            }

            int count = 0;
            int previousFrequency = 0;
            int currentFrequency = 1;
            for (int i = 1; i < s.length(); i++) {
                char c1 = s.charAt(i - 1);
                char c2 = s.charAt(i);

                if (c1 == c2) {
                    currentFrequency++;
                } else {
                    count += Math.min(previousFrequency, currentFrequency);
                    previousFrequency = currentFrequency;
                    currentFrequency = 1;
                }
            }

            count += Math.min(previousFrequency, currentFrequency);
            return count;
        }
    }

    class Solution_Standard {
        public int countBinarySubstrings(String s) {
            if (s == null || s.length() < 2) {
                return 0;
            }

            List<Integer> groups = new ArrayList<>();
            int frequency = 1;
            for (int i = 1; i < s.length(); i++) {
                char c = s.charAt(i);
                char pc = s.charAt(i - 1);
                if (c == pc) {
                    frequency++;
                } else {
                    groups.add(frequency);
                    frequency = 1;
                }
            }

            groups.add(frequency);

            int count = 0;
            for (int i = 1; i < groups.size(); i++) {
                int f1 = groups.get(i - 1);
                int f2 = groups.get(i);

                count += Math.min(f1, f2);
            }

            return count;
        }
    }
}

//    696. Count Binary Substrings
//    Easy
//    Give a string s, count the number of non-empty (contiguous) substrings that have the same number of 0's and 1's, and all the 0's and all the 1's in these substrings are grouped consecutively.
//
//    Substrings that occur multiple times are counted the number of times they occur.
//
//    Example 1:
//    Input: "00110011"
//    Output: 6
//    Explanation: There are 6 substrings that have equal number of consecutive 1's and 0's: "0011", "01", "1100", "10", "0011", and "01".
//
//    Notice that some of these substrings repeat and are counted the number of times they occur.
//
//    Also, "00110011" is not a valid substring because all the 0's (and 1's) are not grouped together.
//    Example 2:
//    Input: "10101"
//    Output: 4
//    Explanation: There are 4 substrings: "10", "01", "10", "01" that have equal number of consecutive 1's and 0's.
//    Note:
//
//    s.length will be between 1 and 50,000.
//    s will only consist of "0" or "1" characters.