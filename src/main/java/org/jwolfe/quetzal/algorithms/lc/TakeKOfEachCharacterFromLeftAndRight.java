package org.jwolfe.quetzal.algorithms.lc;

public class TakeKOfEachCharacterFromLeftAndRight {
    class Solution {
        public int takeCharacters(String s, int k) {
            if(s == null || k < 0) {
                return -1;
            }

            if(k == 0) {
                return 0;
            }

            int a = 0;
            int b = 0;
            int c = 0;
            int n = s.length();

            for(int i = 0; i < n; i++) {
                char ch = s.charAt(i);
                if(ch == 'a') {
                    a++;
                } else if(ch == 'b') {
                    b++;
                } else {
                    c++;
                }
            }

            if(a < k || b < k || c < k) {
                // Not possible
                return -1;
            }

            int i = n - 1;
            int j = n - 1;
            int minMinutes = Integer.MAX_VALUE;

            while(i >= 0) {
                char ch = s.charAt(i);
                if(ch == 'a') {
                    a--;
                } else if(ch == 'b') {
                    b--;
                } else {
                    c--;
                }

                i--;

                while(a < k || b < k || c < k) {
                    ch = s.charAt(j);
                    if(ch == 'a') {
                        a++;
                    } else if(ch == 'b') {
                        b++;
                    } else {
                        c++;
                    }

                    j--;
                }

                int minutes = i + (n - 1 - j) + 1;
                minMinutes = Math.min(minMinutes, minutes);
            }

            return minMinutes;
        }
    }

    class Solution_Correct_TLE {
        public int takeCharacters(String s, int k) {
            if(s == null || k < 0) {
                return -1;
            }

            if(k == 0) {
                return 0;
            }

            int a = 0;
            int b = 0;
            int c = 0;
            int n = s.length();

            for(int i = 0; i < n; i++) {
                char ch = s.charAt(i);
                if(ch == 'a') {
                    a++;
                } else if(ch == 'b') {
                    b++;
                } else {
                    c++;
                }
            }

            if(a < k || b < k || c < k) {
                // Not possible
                return -1;
            }

            int minMinutes = Integer.MAX_VALUE;
            for(int left = -1; left < n; left++) {
                a = 0;
                b = 0;
                c = 0;

                int minutes = 0;
                for(int i = 0; i <= left; i++) {
                    minutes++;
                    char ch = s.charAt(i);
                    if(ch == 'a') {
                        a++;
                    } else if(ch == 'b') {
                        b++;
                    } else {
                        c++;
                    }
                }

                if(a >= k && b >= k && c >= k) {
                    minMinutes = Math.min(minMinutes, minutes);
                    break;
                }

                for(int j = n - 1; j > left; j--) {
                    minutes++;
                    char ch = s.charAt(j);
                    if(ch == 'a') {
                        a++;
                    } else if(ch == 'b') {
                        b++;
                    } else {
                        c++;
                    }

                    if(a >= k && b >= k && c >= k) {
                        minMinutes = Math.min(minMinutes, minutes);
                        break;
                    }
                }
            }

            return minMinutes;
        }
    }

// "aabaaaacaabc"
// 2

// "a"
// 0

// "acba"
// 1

// "cbbac"
// 1
}

//    2516. Take K of Each Character From Left and Right
//    Medium
//    You are given a string s consisting of the characters 'a', 'b', and 'c' and a non-negative integer k. Each minute, you may take either the leftmost character of s, or the rightmost character of s.
//
//    Return the minimum number of minutes needed for you to take at least k of each character, or return -1 if it is not possible to take k of each character.
//
//
//
//    Example 1:
//
//    Input: s = "aabaaaacaabc", k = 2
//    Output: 8
//    Explanation:
//    Take three characters from the left of s. You now have two 'a' characters, and one 'b' character.
//    Take five characters from the right of s. You now have four 'a' characters, two 'b' characters, and two 'c' characters.
//    A total of 3 + 5 = 8 minutes is needed.
//    It can be proven that 8 is the minimum number of minutes needed.
//    Example 2:
//
//    Input: s = "a", k = 1
//    Output: -1
//    Explanation: It is not possible to take one 'b' or 'c' so return -1.
//
//
//    Constraints:
//
//    1 <= s.length <= 105
//    s consists of only the letters 'a', 'b', and 'c'.
//    0 <= k <= s.length