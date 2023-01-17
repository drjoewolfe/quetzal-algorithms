package org.jwolfe.quetzal.algorithms.lc;

public class FlipStringToMonotoneIncreasing {
    class Solution {
        public int minFlipsMonoIncr(String s) {
            if(s == null || s.length() < 2) {
                return 0;
            }

            int zeros = 0;
            for(int i = 0; i < s.length(); i++) {
                if(s.charAt(i) == '0') {
                    zeros++;
                }
            }

            int leftOnes = 0;
            int rightZeros = zeros;
            int flips = rightZeros;
            for(int i = 0; i < s.length(); i++) {
                if(s.charAt(i) == '0') {
                    rightZeros--;
                } else {
                    leftOnes++;
                }

                flips = Math.min(flips, leftOnes + rightZeros);
            }

            return flips;
        }
    }

    class Solution_Correct_3 {
        public int minFlipsMonoIncr(String s) {
            if(s == null || s.length() < 2) {
                return 0;
            }

            int n = s.length();
            int[] prefixSums = new int[n + 1];
            for(int i = 0; i < n; i++) {
                prefixSums[i + 1] = prefixSums[i] + (s.charAt(i) == '1' ? 1 : 0);
            }

            int minFlips = Integer.MAX_VALUE;
            for(int i = 0; i <= n; i++) {
                int flips = prefixSums[i] + ((n - i) - (prefixSums[n] - prefixSums[i]));
                minFlips = Math.min(minFlips, flips);
            }

            return minFlips;
        }
    }

    class Solution_Memoized {
        public int minFlipsMonoIncr(String s) {
            if(s == null || s.length() < 2) {
                return 0;
            }

            Integer[][] memo = new Integer[s.length()][2];
            return Math.min(minFlipsMonoIncr(s, 0, false, memo),
                    minFlipsMonoIncr(s, 0, true, memo));
        }

        private int minFlipsMonoIncr(String s, int index, boolean ones, Integer[][] memo) {
            char c = s.charAt(index);
            if(index == s.length() - 1) {
                if(c == '0') {
                    return ones ? 1 : 0;
                } else {
                    return ones ? 0 : 1;
                }
            }

            if(memo[index][ones ? 1 : 0] != null) {
                return memo[index][ones ? 1 : 0];
            }

            int minFlips = 0;
            if(!ones) {
                // continue as zero
                int zflips = minFlipsMonoIncr(s, index + 1, ones, memo) + ((c == '0') ? 0 : 1);

                // try with switching to ones
                int oflips = minFlipsMonoIncr(s, index + 1, !ones, memo) + ((c == '0') ? 0 : 1);

                minFlips = Math.min(zflips, oflips);
            } else {
                // continue as one
                minFlips = minFlipsMonoIncr(s, index + 1, ones, memo) + ((c == '0') ? 1 : 0);
            }

            memo[index][ones ? 1 : 0] = minFlips;
            return minFlips;
        }
    }

    class Solution_Recursive {
        public int minFlipsMonoIncr(String s) {
            if(s == null || s.length() < 2) {
                return 0;
            }

            return Math.min(minFlipsMonoIncr(s, 0, false),
                    minFlipsMonoIncr(s, 0, true));
        }

        private int minFlipsMonoIncr(String s, int index, boolean ones) {
            char c = s.charAt(index);
            if(index == s.length() - 1) {
                if(c == '0') {
                    return ones ? 1 : 0;
                } else {
                    return ones ? 0 : 1;
                }
            }

            int minFlips = 0;
            if(!ones) {
                // continue as zero
                int zflips = minFlipsMonoIncr(s, index + 1, ones) + ((c == '0') ? 0 : 1);

                // try with switching to ones
                int oflips = minFlipsMonoIncr(s, index + 1, !ones) + ((c == '0') ? 0 : 1);

                minFlips = Math.min(zflips, oflips);
            } else {
                // continue as one
                minFlips = minFlipsMonoIncr(s, index + 1, ones) + ((c == '0') ? 1 : 0);
            }

            return minFlips;
        }
    }

    class Solution_Recursive_1 {
        public int minFlipsMonoIncr(String s) {
            if(s == null || s.length() < 2) {
                return 0;
            }

            return minFlipsMonoIncr(s, 0, 0, false);
        }

        private int minFlipsMonoIncr(String s, int index, int flips, boolean ones) {
            if(index == s.length()) {
                return flips;
            }

            char c = s.charAt(index);
            int minFlips = Integer.MAX_VALUE;
            if(!ones) {
                // continue as zero
                if(c == '0') {
                    minFlips = Math.min(minFlips,
                            minFlipsMonoIncr(s, index + 1, flips, ones));
                } else {
                    minFlips = Math.min(minFlips,
                            minFlipsMonoIncr(s, index + 1, flips + 1, ones));
                }

                // try with switching to ones
                if(c == '0') {
                    minFlips = Math.min(minFlips,
                            minFlipsMonoIncr(s, index + 1, flips + 1, !ones));
                } else {
                    minFlips = Math.min(minFlips,
                            minFlipsMonoIncr(s, index + 1, flips, !ones));
                }
            } else {
                // continue as one
                if(c == '0') {
                    minFlips = Math.min(minFlips,
                            minFlipsMonoIncr(s, index + 1, flips + 1, ones));
                } else {
                    minFlips = Math.min(minFlips,
                            minFlipsMonoIncr(s, index + 1, flips, ones));
                }
            }

            return minFlips;
        }
    }

    class Solution_Correct_2 {
        public int minFlipsMonoIncr(String s) {
            if(s == null || s.length() < 2) {
                return 0;
            }

            int n = s.length();
            int[] prefixSums = new int[n + 1];
            for(int i = 0; i < n; i++) {
                prefixSums[i + 1] = prefixSums[i] + (s.charAt(i) == '1' ? 1 : 0);
            }

            int minFlips = n;
            for(int i = 0; i <= n; i++) {
                int flips = prefixSums[i] + ((n - i) - (prefixSums[n] - prefixSums[i]));
                minFlips = Math.min(minFlips, flips);
            }

            return minFlips;
        }
    }

// "00110"
// "010110"
// "00011000"
// "00000000000000000000000000000000000000000000000000000000000000000010100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001"
}

//    926. Flip String to Monotone Increasing
//    Medium
//
//    1031
//
//    29
//
//    Add to List
//
//    Share
//    A binary string is monotone increasing if it consists of some number of 0's (possibly none), followed by some number of 1's (also possibly none).
//
//    You are given a binary string s. You can flip s[i] changing it from 0 to 1 or from 1 to 0.
//
//    Return the minimum number of flips to make s monotone increasing.
//
//
//
//    Example 1:
//
//    Input: s = "00110"
//    Output: 1
//    Explanation: We flip the last digit to get 00111.
//    Example 2:
//
//    Input: s = "010110"
//    Output: 2
//    Explanation: We flip to get 011111, or alternatively 000111.
//    Example 3:
//
//    Input: s = "00011000"
//    Output: 2
//    Explanation: We flip to get 00000000.
//
//
//    Constraints:
//
//    1 <= s.length <= 105
//    s[i] is either '0' or '1'.
