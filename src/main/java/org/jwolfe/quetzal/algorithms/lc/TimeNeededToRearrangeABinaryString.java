package org.jwolfe.quetzal.algorithms.lc;

public class TimeNeededToRearrangeABinaryString {
    class Solution {
        public int secondsToRemoveOccurrences(String s) {
            if(s == null || s.length() < 2) {
                return 0;
            }

            int n = s.length();
            int indexOfLastOne = s.lastIndexOf('1');
            int zeroCount = 0;
            int waitingTimeForOnes = 0;

            for(int i = 0; i <= indexOfLastOne; i++) {
                char c = s.charAt(i);
                if(c == '0') {
                    zeroCount++;

                    if(i > 0
                            && waitingTimeForOnes > 0
                            && s.charAt(i - 1) == '0') {
                        waitingTimeForOnes--;
                    }
                } else {
                    if(i > 0
                            && zeroCount > 0
                            && s.charAt(i - 1) == '1') {
                        // consecutive ones
                        waitingTimeForOnes++;
                    }
                }
            }

            return zeroCount + waitingTimeForOnes;
        }
    }

    class Solution_Correct_2 {
        public int secondsToRemoveOccurrences(String s) {
            if(s == null || s.length() < 2) {
                return 0;
            }

            int n = s.length();
            StringBuilder builder = new StringBuilder(s);

            int seconds = 0;
            while(true) {
                boolean changed = false;
                for(int i = 0; i < n - 1; i++) {
                    char c = builder.charAt(i);
                    if(c == '0') {
                        char nc = builder.charAt(i + 1);
                        if(nc == '1') {
                            changed = true;
                            builder.setCharAt(i, '1');
                            builder.setCharAt(i + 1, '0');
                            i++;
                        }
                    }
                }

                if(!changed) {
                    break;
                }

                seconds++;
            }

            return seconds;
        }
    }

    class Solution_Correct_1 {
        public int secondsToRemoveOccurrences(String s) {
            if(s == null || s.length() < 2) {
                return 0;
            }

            int n = s.length();

            int seconds = 0;
            while(true) {
                StringBuilder builder = new StringBuilder();
                boolean changed = false;
                for(int i = 0; i < n - 1; i++) {
                    char c = s.charAt(i);
                    if(c == '0') {
                        char nc = s.charAt(i + 1);
                        if(nc == '1') {
                            changed = true;
                            builder.append("10");
                            i++;
                        } else {
                            builder.append(c);
                        }
                    } else {
                        builder.append(c);
                    }
                }

                if(builder.length() < n) {
                    builder.append(s.charAt(n - 1));
                }

                s = builder.toString();
                if(!changed) {
                    break;
                }

                seconds++;
            }

            return seconds;
        }
    }

// "0110101"
// "11100"
// "1001111111110001011001110000000110101"
}

//    2380. Time Needed to Rearrange a Binary String
//    Medium
//    You are given a binary string s. In one second, all occurrences of "01" are simultaneously replaced with "10". This process repeats until no occurrences of "01" exist.
//
//    Return the number of seconds needed to complete this process.
//
//
//
//    Example 1:
//
//    Input: s = "0110101"
//    Output: 4
//    Explanation:
//    After one second, s becomes "1011010".
//    After another second, s becomes "1101100".
//    After the third second, s becomes "1110100".
//    After the fourth second, s becomes "1111000".
//    No occurrence of "01" exists any longer, and the process needed 4 seconds to complete,
//    so we return 4.
//    Example 2:
//
//    Input: s = "11100"
//    Output: 0
//    Explanation:
//    No occurrence of "01" exists in s, and the processes needed 0 seconds to complete,
//    so we return 0.
//
//
//    Constraints:
//
//    1 <= s.length <= 1000
//    s[i] is either '0' or '1'.
//
//
//    Follow up:
//
//    Can you solve this problem in O(n) time complexity?