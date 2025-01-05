package org.jwolfe.quetzal.algorithms.lc;

public class ShiftingLettersII {
    class Solution {
        public String shiftingLetters(String s, int[][] shifts) {
            if (s == null || s.length() == 0 || shifts == null || shifts.length == 0) {
                return s;
            }

            int n = s.length();
            int[] diffs = new int[n + 1];
            for (int[] shift : shifts) {
                int from = shift[0];
                int to = shift[1];
                int direction = shift[2];

                if (direction == 1) {
                    diffs[from]++;
                    diffs[to + 1]--;
                } else {
                    diffs[from]--;
                    diffs[to + 1]++;
                }
            }

            for (int i = 1; i < n; i++) {
                diffs[i] += diffs[i - 1];
            }

            StringBuilder result = new StringBuilder();
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);

                int diff = diffs[i] % 26;
                if (diff == 0) {
                    result.append(c);
                } else {
                    if (diff < 0) {
                        diff += 26;
                    }

                    char nc = (char) ('a' + (c - 'a' + diff) % 26);
                    result.append(nc);
                }
            }

            return result.toString();
        }
    }

    class Solution_Correct_1 {
        public String shiftingLetters(String s, int[][] shifts) {
            if (s == null || s.length() == 0 || shifts == null || shifts.length == 0) {
                return s;
            }

            int n = s.length();
            int[] line = new int[n + 1];
            for (int[] shift : shifts) {
                int start = shift[0];
                int end = shift[1];
                int direction = shift[2];

                if (direction == 1) {
                    line[start]++;
                    line[end + 1]--;
                } else {
                    line[start]--;
                    line[end + 1]++;
                }
            }

            for (int i = 1; i < n; i++) {
                line[i] += line[i - 1];
            }

            StringBuilder builder = new StringBuilder(s);
            for (int i = 0; i < n; i++) {
                int shiftCount = line[i];
                char c = s.charAt(i);

                if (shiftCount == 0) {
                    continue;
                }

                int nci = ((c - 'a') + shiftCount) % 26;
                if (nci < 0) {
                    nci += 26;
                }

                char nc = (char) ('a' + nci);
                builder.setCharAt(i, nc);
            }

            return builder.toString();
        }
    }

    class Solution_TLE {
        public String shiftingLetters(String s, int[][] shifts) {
            if (s == null || s.length() == 0 || shifts == null || shifts.length == 0) {
                return s;
            }

            int n = s.length();
            int[] cumulativeShifts = new int[n];
            for (int[] shift : shifts) {
                int start = shift[0];
                int end = shift[1];
                int direction = shift[2];

                for (int i = start; i <= end; i++) {
                    if (direction == 1) {
                        cumulativeShifts[i]++;
                    } else {
                        cumulativeShifts[i]--;
                    }
                }
            }

            StringBuilder builder = new StringBuilder(s);
            for (int i = 0; i < n; i++) {
                int shiftCount = cumulativeShifts[i];
                char c = s.charAt(i);

                if (shiftCount == 0) {
                    continue;
                }

                int nci = ((c - 'a') + shiftCount) % 26;
                if (nci < 0) {
                    nci += 26;
                }

                char nc = (char) ('a' + nci);
                builder.setCharAt(i, nc);
            }

            return builder.toString();
        }

        private void print(int[] arr) {
            for (int c : arr) {
                System.out.print(c + " ");
            }

            System.out.println();
        }
    }


// "abc"
// [[0,1,0],[1,2,1],[0,2,1]]

// "dztz"
// [[0,0,0],[1,1,1]]
}

//    2381. Shifting Letters II
//    Medium
//    You are given a string s of lowercase English letters and a 2D integer array shifts where shifts[i] = [starti, endi, directioni]. For every i, shift the characters in s from the index starti to the index endi (inclusive) forward if directioni = 1, or shift the characters backward if directioni = 0.
//
//    Shifting a character forward means replacing it with the next letter in the alphabet (wrapping around so that 'z' becomes 'a'). Similarly, shifting a character backward means replacing it with the previous letter in the alphabet (wrapping around so that 'a' becomes 'z').
//
//    Return the final string after all such shifts to s are applied.
//
//
//
//    Example 1:
//
//    Input: s = "abc", shifts = [[0,1,0],[1,2,1],[0,2,1]]
//    Output: "ace"
//    Explanation: Firstly, shift the characters from index 0 to index 1 backward. Now s = "zac".
//    Secondly, shift the characters from index 1 to index 2 forward. Now s = "zbd".
//    Finally, shift the characters from index 0 to index 2 forward. Now s = "ace".
//    Example 2:
//
//    Input: s = "dztz", shifts = [[0,0,0],[1,1,1]]
//    Output: "catz"
//    Explanation: Firstly, shift the characters from index 0 to index 0 backward. Now s = "cztz".
//    Finally, shift the characters from index 1 to index 1 forward. Now s = "catz".
//
//
//    Constraints:
//
//    1 <= s.length, shifts.length <= 5 * 104
//    shifts[i].length == 3
//    0 <= starti <= endi < s.length
//    0 <= directioni <= 1
//    s consists of lowercase English letters.