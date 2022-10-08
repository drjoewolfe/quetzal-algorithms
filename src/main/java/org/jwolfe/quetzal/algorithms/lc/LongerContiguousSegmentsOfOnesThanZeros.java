package org.jwolfe.quetzal.algorithms.lc;

public class LongerContiguousSegmentsOfOnesThanZeros {
    class Solution {
        public boolean checkZeroOnes(String s) {
            if(s == null || s.length() == 0) {
                return false;
            }

            int lcsOne = 0;
            int lcsZero = 0;

            char prev = s.charAt(0);
            int streak = 1;
            for(int i = 1; i < s.length(); i++) {
                char curr = s.charAt(i);

                if(curr == prev) {
                    streak++;
                } else {
                    if(prev == '1') {
                        lcsOne = Math.max(lcsOne, streak);
                    } else {
                        lcsZero = Math.max(lcsZero, streak);
                    }

                    prev = curr;
                    streak = 1;
                }
            }

            if(prev == '1') {
                lcsOne = Math.max(lcsOne, streak);
            } else {
                lcsZero = Math.max(lcsZero, streak);
            }

            return lcsOne > lcsZero;
        }
    }
}

//    1869. Longer Contiguous Segments of Ones than Zeros
//    Easy
//    Given a binary string s, return true if the longest contiguous segment of 1's is strictly longer than the longest contiguous segment of 0's in s, or return false otherwise.
//
//    For example, in s = "110100010" the longest continuous segment of 1s has length 2, and the longest continuous segment of 0s has length 3.
//    Note that if there are no 0's, then the longest continuous segment of 0's is considered to have a length 0. The same applies if there is no 1's.
//
//
//
//    Example 1:
//
//    Input: s = "1101"
//    Output: true
//    Explanation:
//    The longest contiguous segment of 1s has length 2: "1101"
//    The longest contiguous segment of 0s has length 1: "1101"
//    The segment of 1s is longer, so return true.
//    Example 2:
//
//    Input: s = "111000"
//    Output: false
//    Explanation:
//    The longest contiguous segment of 1s has length 3: "111000"
//    The longest contiguous segment of 0s has length 3: "111000"
//    The segment of 1s is not longer, so return false.
//    Example 3:
//
//    Input: s = "110100010"
//    Output: false
//    Explanation:
//    The longest contiguous segment of 1s has length 2: "110100010"
//    The longest contiguous segment of 0s has length 3: "110100010"
//    The segment of 1s is not longer, so return false.
//
//
//    Constraints:
//
//    1 <= s.length <= 100
//    s[i] is either '0' or '1'.
