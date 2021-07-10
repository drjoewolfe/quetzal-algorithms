package org.jwolfe.quetzal.algorithms.lc;

public class DecodeWaysII {
    class Solution {
        private int MOD = 1_000_000_007;

        public int numDecodings(String s) {
            if(s == null || s.length() == 0) {
                return 0;
            }

            int n = s.length();
            Long[] memo = new Long[n];
            return (int) (numDecodings(s, n - 1, memo) % MOD);
        }

        private long numDecodings(String s, int index, Long[] memo) {
            if(index < 0) {
                return 1;
            }

            if(memo[index] != null) {
                return memo[index];
            }

            long ways = 0;
            char c = s.charAt(index);
            if(c == '*') {
                ways = (9 * numDecodings(s, index - 1, memo)) % MOD;

                if(index > 0) {
                    long prevWays = (numDecodings(s, index - 2, memo)) % MOD;

                    char p = s.charAt(index - 1);
                    if(p == '1') {
                        ways += (9 * prevWays);
                    } else if(p == '2') {
                        ways += (6 * prevWays);
                    } else if(p == '*') {
                        ways += (15 * prevWays);
                    }
                }
            } else {
                ways = (c == '0') ? 0 : numDecodings(s, index - 1, memo);

                if(index > 0) {
                    long prevWays = numDecodings(s, index - 2, memo);
                    char p = s.charAt(index - 1);

                    if(p == '1') {
                        ways += prevWays;
                    } else if(p == '2' && c <= '6') {
                        ways += prevWays;
                    } else if(p == '*') {
                        ways += ((c <= '6' ? 2 : 1) * prevWays);
                    }
                }
            }

            ways %= MOD;
            memo[index] = ways;
            return ways;
        }
    }

    class Solution_Incorrect {
        public int numDecodings(String s) {
            if(s == null || s.length() == 0 || s.charAt(0) == '0') {
                return 0;
            }

            int n = s.length();
            Integer[] memo = new Integer[n];
            int result = numDecodings(s, n - 1, memo);
            print(memo);
            return result;
        }

        private int numDecodings(String s, int index, Integer[] memo) {
            if(index < 0) {
                return 0;
            }

            if(memo[index] != null) {
                return memo[index];
            }

            int ways = 0;

            ways += numDecodingsSingle(s, index, memo);
            if(index > 0) {
                ways += numDecodingsDouble(s, index, memo);
            }

            memo[index] = ways;
            return ways;
        }

        private int numDecodingsSingle(String s, int index, Integer[] memo) {
            char c = s.charAt(index);

            int prevWays = numDecodings(s, index - 1, memo);
            int minWays = (prevWays == 0) ? 1 : prevWays;
            int ways = 0;
            if(c == '*') {
                ways = 9 * minWays;
            } else if(c > '0' && c <= '9') {
                if(prevWays == 0 && index == 0) {
                    ways = minWays;
                } else if(prevWays != 0 && index > 0) {
                    ways = prevWays;
                }
            }

            return ways;
        }

        private int numDecodingsDouble(String s, int index, Integer[] memo) {
            char c = s.charAt(index);
            char p = s.charAt(index - 1);

            int prevWays = numDecodings(s, index - 2, memo);
            int minWays = (prevWays == 0) ? 1 : prevWays;
            int ways = 0;
            if(p == '1') {
                if(c == '*') {
                    ways = 9 * minWays;
                } else {
                    ways = minWays;
                }
            } else if(p == '2') {
                if(c == '*') {
                    ways = 6 * minWays;
                } else if(c >= '0' && c <= '6') {
                    ways = minWays;
                }
            } else if(p == '*') {
                if(c == '*') {
                    ways = 15 * minWays;
                } else if(c <= '6') {
                    ways = 2 * minWays;
                } else {
                    ways = minWays;
                }
            }

            return ways;
        }

        private void print(Integer[] memo) {
            for(int a : memo) {
                System.out.print(a + " ");
            }

            System.out.println();
        }

//     private int numDecodingsDouble(String s, int index) {
//         char c = s.charAt(index);
//         char p = s.charAt(index - 1);

//         int ways = numDecodings(s, index - 2);
//         if(p == '1' && c == '*') {
//             ways *= 9;
//         } else if(p == '2' && c == '*') {
//             ways *= 6;
//         } else if(p == '*' && c == '*') {
//             ways *= 15;
//         } else {
//             ways -= 1;
//         }

//         System.out.println("Double: " + ways);
//         return ways;
//     }
    }

// "*"
// "11"
// "3*"
// "**"
// "*6"
// "*1*1*0"
// "*1*3*3*334**"
// "*1"
// "0*1*8"
// "50926"
// "*********"
}

//    639. Decode Ways II
//    Hard
//    A message containing letters from A-Z can be encoded into numbers using the following mapping:
//
//    'A' -> "1"
//    'B' -> "2"
//    ...
//    'Z' -> "26"
//    To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the mapping above (there may be multiple ways). For example, "11106" can be mapped into:
//
//    "AAJF" with the grouping (1 1 10 6)
//    "KJF" with the grouping (11 10 6)
//    Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".
//
//    In addition to the mapping above, an encoded message may contain the '*' character, which can represent any digit from '1' to '9' ('0' is excluded). For example, the encoded message "1*" may represent any of the encoded messages "11", "12", "13", "14", "15", "16", "17", "18", or "19". Decoding "1*" is equivalent to decoding any of the encoded messages it can represent.
//
//    Given a string s containing digits and the '*' character, return the number of ways to decode it.
//
//    Since the answer may be very large, return it modulo 109 + 7.
//
//
//
//    Example 1:
//
//    Input: s = "*"
//    Output: 9
//    Explanation: The encoded message can represent any of the encoded messages "1", "2", "3", "4", "5", "6", "7", "8", or "9".
//    Each of these can be decoded to the strings "A", "B", "C", "D", "E", "F", "G", "H", and "I" respectively.
//    Hence, there are a total of 9 ways to decode "*".
//    Example 2:
//
//    Input: s = "1*"
//    Output: 18
//    Explanation: The encoded message can represent any of the encoded messages "11", "12", "13", "14", "15", "16", "17", "18", or "19".
//    Each of these encoded messages have 2 ways to be decoded (e.g. "11" can be decoded to "AA" or "K").
//    Hence, there are a total of 9 * 2 = 18 ways to decode "1*".
//    Example 3:
//
//    Input: s = "2*"
//    Output: 15
//    Explanation: The encoded message can represent any of the encoded messages "21", "22", "23", "24", "25", "26", "27", "28", or "29".
//    "21", "22", "23", "24", "25", and "26" have 2 ways of being decoded, but "27", "28", and "29" only have 1 way.
//    Hence, there are a total of (6 * 2) + (3 * 1) = 12 + 3 = 15 ways to decode "2*".
//
//
//    Constraints:
//
//    1 <= s.length <= 105
//    s[i] is a digit or '*'.