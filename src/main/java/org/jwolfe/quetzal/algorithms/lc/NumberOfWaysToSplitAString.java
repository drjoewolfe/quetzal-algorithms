package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;

public class NumberOfWaysToSplitAString {
    class Solution {
        public int numWays(String s) {
            if(s == null || s.length() == 0) {
                return 0;
            }

            int mod = 1_000_000_007;
            int n = s.length();
            int onesCount = 0;
            List<Integer> onesIndices = new ArrayList<>();

            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if(c == '1') {
                    onesCount++;
                    onesIndices.add(i);
                }
            }

            if(onesCount == 0) {
                return (int) ((1l * (n - 2) * (n - 1) / 2) % mod);
            }

            if(onesCount % 3 != 0) {
                return 0;
            }

            int onesPerPartition = onesCount / 3;

            int p1End = onesIndices.get(onesPerPartition - 1);
            int p2Start = onesIndices.get(onesPerPartition);
            int p2End = onesIndices.get((2 * onesPerPartition) - 1);
            int p3Start = onesIndices.get(2 * onesPerPartition);

            int bay1 = (p2Start - p1End);
            int bay2 = (p3Start - p2End);

            // System.out.println(p1End + ", " + p2Start + ", " + p2End + ", " + p3Start + " = " + bay1 + ", " + bay2);

            return (int) ((1l * bay1 * bay2) % mod);
        }
    }
}

//    1573. Number of Ways to Split a String
//    Given a binary string s (a string consisting only of '0's and '1's), we can split s into 3 non-empty strings s1, s2, s3 (s1+ s2+ s3 = s).
//
//    Return the number of ways s can be split such that the number of characters '1' is the same in s1, s2, and s3.
//
//    Since the answer may be too large, return it modulo 10^9 + 7.
//
//
//
//    Example 1:
//
//    Input: s = "10101"
//    Output: 4
//    Explanation: There are four ways to split s in 3 parts where each part contain the same number of letters '1'.
//    "1|010|1"
//    "1|01|01"
//    "10|10|1"
//    "10|1|01"
//    Example 2:
//
//    Input: s = "1001"
//    Output: 0
//    Example 3:
//
//    Input: s = "0000"
//    Output: 3
//    Explanation: There are three ways to split s in 3 parts.
//    "0|0|00"
//    "0|00|0"
//    "00|0|0"
//    Example 4:
//
//    Input: s = "100100010100110"
//    Output: 12
//
//
//    Constraints:
//
//    3 <= s.length <= 10^5
//    s[i] is '0' or '1'.