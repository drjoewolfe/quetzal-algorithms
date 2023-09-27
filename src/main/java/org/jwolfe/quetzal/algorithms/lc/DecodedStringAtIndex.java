package org.jwolfe.quetzal.algorithms.lc;

public class DecodedStringAtIndex {
    class Solution {
        public String decodeAtIndex(String S, int K) {
            if(S == null || S.length() == 0 || K < 1) {
                return null;
            }

            long size = 0;
            int n = S.length();
            for(int i = 0; i < n; i++) {
                char c = S.charAt(i);
                if(Character.isDigit(c)) {
                    int d = c - '0';
                    size *= d;

                } else {
                    size++;
                }
            }

            for(int i = n - 1; i >= 0; i--) {
                char c = S.charAt(i);

                K %= size;
                if(K == 0 && Character.isLetter(c)) {
                    return Character.toString(c);
                }

                if(Character.isDigit(c)) {
                    int d = c - '0';
                    size /= d;
                } else {
                    size--;
                }
            }

            return null;
        }
    }

    class Solution_Correct_1 {
        public String decodeAtIndex(String S, int K) {
            if(S == null || S.length() < 2 || S.length() > 100 || K < 1) {
                return null;
            }

            int n = S.length();
            long size = 0;
            for(int i = 0; i < n; i++) {
                char c = S.charAt(i);
                if(Character.isDigit(c)) {
                    size *= (c - '0');
                } else {
                    size++;
                }
            }

            for(int i = n - 1; i >= 0; i--) {
                char c = S.charAt(i);

                K %= size;
                if(K == 0 && Character.isLetter(c)) {
                    return Character.toString(c);
                }

                if(Character.isDigit(c)) {
                    size /= (c - '0');
                } else {
                    size--;
                }
            }

            return null;
        }
    }

    class Solution_Better {
        public String decodeAtIndex(String S, int K) {
            if(S == null || S.length() < 2 || S.length() > 100 || K < 1) {
                return null;
            }

            StringBuilder tape = new StringBuilder();
            int index = -1;

            for(int i = 0; i < S.length(); i++) {
                Character c = S.charAt(i);
                if(Character.isDigit(c)) {
                    String tapeString = tape.toString();
                    int count = (int) (c - '0') - 1;
                    while(count > 0) {
                        tape.append(tapeString);
                        index += tapeString.length();
                        count--;
                    }
                } else {
                    tape.append(c);
                    index++;
                }

                if(index == K - 1) {
                    return ((Character) tape.charAt(K - 1)).toString();
                }
            }

            return tape.substring(K - 1, K);
        }
    }


    class Solution_Brute {
        public String decodeAtIndex(String S, int K) {
            if(S == null || S.length() < 2 || S.length() > 100 || K < 1) {
                return null;
            }

            StringBuilder tape = new StringBuilder();
            for(int i = 0; i < S.length(); i++) {
                Character c = S.charAt(i);
                if(Character.isDigit(c)) {
                    String tapeString = tape.toString();
                    int count = (int) (c - '0') - 1;
                    while(count > 0) {
                        tape.append(tapeString);
                        count--;
                    }
                } else {
                    tape.append(c);
                }
            }

            return tape.substring(K - 1, K);
        }
    }

// "leet2code3"
// 10

// "a2345678999999999999999"
// 1
}

//    880. Decoded String at Index
//    Medium
//    You are given an encoded string s. To decode the string to a tape, the encoded string is read one character at a time and the following steps are taken:
//
//    If the character read is a letter, that letter is written onto the tape.
//    If the character read is a digit d, the entire current tape is repeatedly written d - 1 more times in total.
//    Given an integer k, return the kth letter (1-indexed) in the decoded string.
//
//
//
//    Example 1:
//
//    Input: s = "leet2code3", k = 10
//    Output: "o"
//    Explanation: The decoded string is "leetleetcodeleetleetcodeleetleetcode".
//    The 10th letter in the string is "o".
//    Example 2:
//
//    Input: s = "ha22", k = 5
//    Output: "h"
//    Explanation: The decoded string is "hahahaha".
//    The 5th letter is "h".
//    Example 3:
//
//    Input: s = "a2345678999999999999999", k = 1
//    Output: "a"
//    Explanation: The decoded string is "a" repeated 8301530446056247680 times.
//    The 1st letter is "a".
//
//
//    Constraints:
//
//    2 <= s.length <= 100
//    s consists of lowercase English letters and digits 2 through 9.
//    s starts with a letter.
//    1 <= k <= 109
//    It is guaranteed that k is less than or equal to the length of the decoded string.
//    The decoded string is guaranteed to have less than 263 letters.