package org.jwolfe.quetzal.algorithms.lc;

public class GreatestCommonDivisorOfStrings {
    class Solution {
        public String gcdOfStrings(String str1, String str2) {
            if(str1 == null || str2 == null || str1.length() == 0 || str2.length() == 0) {
                return "";
            }

            String str12 = str1 + str2;
            String str21 = str2 + str1;

            if(!str12.equals(str21)) {
                return "";
            }

            int m = str1.length();
            int n = str2.length();

            int l = gcd(m, n);
            return str1.substring(0, l);
        }

        public int gcd(int x, int y) {
            if(y == 0) {
                return x;
            }

            return gcd(y, x % y);
        }
    }

    class Solution_Correct_1 {
        public String gcdOfStrings(String str1, String str2) {
            if(str1 == null || str1.length() == 0 || str2 == null || str2.length() == 0) {
                return "";
            }

            int s1l = str1.length();
            int s2l = str2.length();

            for(int l = str1.length(); l > 0; l--) {
                if(s1l % l != 0 || s2l % l != 0) {
                    continue;
                }

                String candidate = str1.substring(0, l);

                if(isDivisor(str1, candidate) && isDivisor(str2, candidate)) {
                    return candidate;
                }
            }

            return "";
        }

        private boolean isDivisor(String str, String candidate) {
            int si = 0;
            int ci = 0;

            int sl = str.length();
            int cl = candidate.length();

            // if(sl < cl || sl % cl != 0) {
            //  return false;
            // }

            while(si < sl
                    && str.charAt(si) == candidate.charAt(ci)) {
                si++;
                ci++;

                if(ci == cl) {
                    ci = 0;
                }
            }

            if(si != sl || ci != 0) {
                return false;
            }

            return true;
        }
    }
}

//    1071. Greatest Common Divisor of Strings
//    Easy
//    For two strings s and t, we say "t divides s" if and only if s = t + ... + t (i.e., t is concatenated with itself one or more times).
//
//    Given two strings str1 and str2, return the largest string x such that x divides both str1 and str2.
//
//
//
//    Example 1:
//
//    Input: str1 = "ABCABC", str2 = "ABC"
//    Output: "ABC"
//    Example 2:
//
//    Input: str1 = "ABABAB", str2 = "ABAB"
//    Output: "AB"
//    Example 3:
//
//    Input: str1 = "LEET", str2 = "CODE"
//    Output: ""
//
//
//    Constraints:
//
//    1 <= str1.length, str2.length <= 1000
//    str1 and str2 consist of English uppercase letters.