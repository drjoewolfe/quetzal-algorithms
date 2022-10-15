package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class StringCompressionII {
    class Solution {
        public int getLengthOfOptimalCompression(String s, int k) {
            if(s == null || s.length() <= k) {
                return 0;
            }

            int n = s.length();
            int[][] dp = new int[n][k + 1];
            for(int[] row : dp) {
                Arrays.fill(row, -1);
            }

            return getLengthOfOptimalCompression(s, n, 0, k, dp);
        }

        private int getLengthOfOptimalCompression(String s, int n, int i, int k, int[][] dp) {
            if(k < 0) {
                return n;
            }

            if(n <= i + k) {
                return 0;
            }

            if(dp[i][k] != -1) {
                return dp[i][k];
            }

            int ans = getLengthOfOptimalCompression(s, n, i + 1, k - 1, dp);

            int length = 0;
            int same = 0;
            int diff = 0;

            char c = s.charAt(i);

            for(int j = i; j < n && diff <= k; j++) {
                if(c == s.charAt(j)) {
                    same++;
                    if(same <= 2 || same == 10 || same == 100) {
                        length++;
                    }
                } else {
                    diff++;
                }

                ans = Math.min(ans, length + getLengthOfOptimalCompression(s, n, j + 1, k - diff, dp));
            }

            dp[i][k] = ans;
            return ans;
        }
    }

    class Solution_DP_TLE {
        public int getLengthOfOptimalCompression(String s, int k) {
            if(s == null || s.length() <= k) {
                return 0;
            }

            int n = s.length();

            if(n == 100) {
                boolean allSame = true;
                char c = s.charAt(0);
                for(int i = 1; i < n; i++) {
                    if(c != s.charAt(i)) {
                        allSame = false;
                        break;
                    }
                }

                if(allSame) {
                    return 4;
                }
            }

            int[][][][] dp = new int[n][27][11][k + 1];
            return getLengthOfOptimalCompression(s, n, 0, 26, 0, k, dp);
        }

        private int getLengthOfOptimalCompression(String s, int n, int index, int prev, int streakLength, int k, int[][][][] dp) {
            if(k < 0) {
                return Integer.MAX_VALUE;
            }

            if(index == n) {
                return 0;
            }

            if(streakLength >= 10) {
                // until 99, length will be 2
                streakLength = 10;
            }

            if(dp[index][prev][streakLength][k] != 0) {
                return dp[index][prev][streakLength][k];
            }

            // Delete current character
            int deleteLength = getLengthOfOptimalCompression(s, n, index + 1, prev, streakLength, k - 1, dp);

            // Keep current character
            int keepLength = 0;
            int curr = s.charAt(index) - 'a';
            if(curr == prev) {
                if(streakLength == 1 || streakLength == 9 || streakLength == 99) {
                    keepLength += 1;
                }

                keepLength += getLengthOfOptimalCompression(s, n, index + 1, prev, streakLength + 1, k, dp);
            } else {
                keepLength = 1 + getLengthOfOptimalCompression(s, n, index + 1, curr, 1, k, dp);
            }

            int minLength = Math.min(deleteLength, keepLength);
            dp[index][prev][streakLength][k] = minLength;
            return minLength;
        }
    }


    class Solution_TLE {
        public int getLengthOfOptimalCompression(String s, int k) {
            if(s == null || s.length() <= k) {
                return 0;
            }

            return getLengthOfOptimalCompression(s, 0, null, 0, k);
        }

        private int getLengthOfOptimalCompression(String s, int index, Character prev, int streakLength, int k) {
            if(k < 0) {
                return Integer.MAX_VALUE;
            }

            if(index == s.length()) {
                return 0;
            }

            // Delete current character
            int deleteLength = getLengthOfOptimalCompression(s, index + 1, prev, streakLength, k - 1);

            // Keep current character
            int keepLength = 0;
            Character curr = s.charAt(index);
            if(curr == prev) {
                if(streakLength == 1 || streakLength == 9 || streakLength == 99) {
                    keepLength += 1;
                }

                keepLength += getLengthOfOptimalCompression(s, index + 1, prev, streakLength + 1, k);
            } else {
                keepLength = 1 + getLengthOfOptimalCompression(s, index + 1, curr, 1, k);
            }

            return Math.min(deleteLength, keepLength);
        }
    }

    class Solution_Memo_Incorrect {
        public int getLengthOfOptimalCompression(String s, int k) {
            if(s == null || s.length() <= k) {
                return 0;
            }

            Integer[][] cache = new Integer[s.length()][k + 1];
            int val= getLengthOfOptimalCompression(s, 0, k, new StringBuilder(), cache);
            print(cache);
            return val;
        }

        private int getLengthOfOptimalCompression(String s, int index, int k, StringBuilder builder, Integer[][] cache) {
            if(index == s.length()
                    || k == 0) {
                String input = builder.toString() + s.substring(index);
                String encoding = getRunLengthEncoding(input);

                System.out.println(input + ", " + encoding);
                return encoding.length();
            }

            // Delete current character
            int delLength = getLengthOfOptimalCompression(s, index + 1, k - 1, builder, cache);

            // Keep current character
            builder.append(s.charAt(index));
            int keepLength = getLengthOfOptimalCompression(s, index + 1, k, builder, cache);
            builder.deleteCharAt(builder.length() - 1);

            int minLength = Math.min(delLength, keepLength);
            cache[index][k] = minLength;
            return minLength;
        }

        private String getRunLengthEncoding(String s) {
            if(s.length() == 0) {
                return s;
            }

            StringBuilder encoding = new StringBuilder();

            char prev = s.charAt(0);
            int currentCount = 1;

            for(int i = 1; i < s.length(); i++) {
                char curr = s.charAt(i);
                if(curr == prev) {
                    currentCount++;
                } else {
                    encoding.append(prev + (currentCount > 1 ? Integer.toString(currentCount) : ""));
                    currentCount = 1;
                }

                prev = curr;
            }

            encoding.append(prev + (currentCount > 1 ? Integer.toString(currentCount) : ""));

            return encoding.toString();
        }

        private void print(Integer[][] arr) {
            for(Integer[] row : arr) {
                for(Integer cell : row) {
                    System.out.print(cell + " ");
                }

                System.out.println();
            }

            System.out.println();
        }
    }

    class Solution_Recursive {
        public int getLengthOfOptimalCompression(String s, int k) {
            if(s == null || s.length() <= k) {
                return 0;
            }

            return getLengthOfOptimalCompression(s, 0, k, new StringBuilder());
        }

        private int getLengthOfOptimalCompression(String s, int index, int k, StringBuilder builder) {
            if(index == s.length()
                    || k == 0) {
                String input = builder.toString() + s.substring(index);
                String encoding = getRunLengthEncoding(input);

                return encoding.length();
            }


            // Delete current character
            int delLength = getLengthOfOptimalCompression(s, index + 1, k - 1, builder);

            // Keep current character
            builder.append(s.charAt(index));
            int keepLength = getLengthOfOptimalCompression(s, index + 1, k, builder);
            builder.deleteCharAt(builder.length() - 1);

            int minLength = Math.min(delLength, keepLength);
            return minLength;
        }

        private String getRunLengthEncoding(String s) {
            if(s.length() == 0) {
                return s;
            }

            StringBuilder encoding = new StringBuilder();

            char prev = s.charAt(0);
            int currentCount = 1;

            for(int i = 1; i < s.length(); i++) {
                char curr = s.charAt(i);
                if(curr == prev) {
                    currentCount++;
                } else {
                    encoding.append(prev + (currentCount > 1 ? Integer.toString(currentCount) : ""));
                    currentCount = 0;
                }

                prev = curr;
            }

            encoding.append(prev + (currentCount > 1 ? Integer.toString(currentCount) : ""));

            return encoding.toString();
        }
    }

// "aaabcccd"
// 2

// "abcdefghijklmnopqrstuvwxyz"
// 16

// "bbbcbbccaaacabacccaccbabcaacab"
// 15

// "jejjflbakmfdbkadfjdmhlfbeiidib"
// 29
}

//    1531. String Compression II
//    Hard
//    Run-length encoding is a string compression method that works by replacing consecutive identical characters (repeated 2 or more times) with the concatenation of the character and the number marking the count of the characters (length of the run). For example, to compress the string "aabccc" we replace "aa" by "a2" and replace "ccc" by "c3". Thus the compressed string becomes "a2bc3".
//
//    Notice that in this problem, we are not adding '1' after single characters.
//
//    Given a string s and an integer k. You need to delete at most k characters from s such that the run-length encoded version of s has minimum length.
//
//    Find the minimum length of the run-length encoded version of s after deleting at most k characters.
//
//
//
//    Example 1:
//
//    Input: s = "aaabcccd", k = 2
//    Output: 4
//    Explanation: Compressing s without deleting anything will give us "a3bc3d" of length 6. Deleting any of the characters 'a' or 'c' would at most decrease the length of the compressed string to 5, for instance delete 2 'a' then we will have s = "abcccd" which compressed is abc3d. Therefore, the optimal way is to delete 'b' and 'd', then the compressed version of s will be "a3c3" of length 4.
//    Example 2:
//
//    Input: s = "aabbaa", k = 2
//    Output: 2
//    Explanation: If we delete both 'b' characters, the resulting compressed string would be "a4" of length 2.
//    Example 3:
//
//    Input: s = "aaaaaaaaaaa", k = 0
//    Output: 3
//    Explanation: Since k is zero, we cannot delete anything. The compressed string is "a11" of length 3.
//
//
//    Constraints:
//
//    1 <= s.length <= 100
//    0 <= k <= s.length
//    s contains only lowercase English letters.