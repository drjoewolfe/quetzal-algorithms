package org.jwolfe.quetzal.algorithms.lc;

public class BuddyStrings {
    class Solution {
        public boolean buddyStrings(String A, String B) {
            if(A == null || B == null || A.length() == 0 || A.length() != B.length()) {
                return false;
            }

            if(A.equals(B)) {
                int[] counts = new int[26];
                for(int i = 0; i < A.length(); i++) {
                    counts[A.charAt(i) - 'a']++;
                }

                for(int i = 0; i < 26; i++) {
                    if(counts[i] > 1) {
                        return true;
                    }
                }

                return false;
            }


            int firstIndex = -1;
            int secondIndex = -1;
            for(int i = 0; i < A.length(); i++) {
                char a = A.charAt(i);
                char b = B.charAt(i);

                if(a == b) {
                    continue;
                }

                if(firstIndex == -1) {
                    firstIndex = i;
                } else if(secondIndex == -1) {
                    secondIndex = i;
                } else {
                    return false;
                }
            }

            if(secondIndex == -1) {
                return false;
            }

            return (A.charAt(firstIndex) == B.charAt(secondIndex))
                    && (A.charAt(secondIndex) == B.charAt(firstIndex));
        }
    }

    class Solution_Correct_1 {
        public boolean buddyStrings(String A, String B) {
            if(A == null || A.length() == 0 || B == null || A.length() != B.length()) {
                return false;
            }

            if(A.equals(B)) {
                int[] counts = new int[26];
                for(int i = 0; i < A.length(); i++) {
                    counts[A.charAt(i) - 'a']++;
                }

                for(int c : counts) {
                    if(c > 1) {
                        return true;
                    }
                }

                return false;
            }

            int firstIndex = -1;
            int secondIndex = -1;
            for(int i = 0; i < A.length(); i++) {
                char a = A.charAt(i);
                char b = B.charAt(i);

                if(a == b) {
                    continue;
                }

                if(firstIndex == -1) {
                    firstIndex = i;
                } else if(secondIndex == -1) {
                    secondIndex = i;
                } else {
                    return false;
                }
            }

            if(secondIndex == -1) {
                return false;
            }

            return A.charAt(firstIndex) == B.charAt(secondIndex) && A.charAt(secondIndex) == B.charAt(firstIndex);
        }
    }

// "ab"
// "ba"

// "abac"
// "abad"
}

//    859. Buddy Strings
//    Easy
//    Given two strings s and goal, return true if you can swap two letters in s so the result is equal to goal, otherwise, return false.
//
//    Swapping letters is defined as taking two indices i and j (0-indexed) such that i != j and swapping the characters at s[i] and s[j].
//
//    For example, swapping at indices 0 and 2 in "abcd" results in "cbad".
//
//
//    Example 1:
//
//    Input: s = "ab", goal = "ba"
//    Output: true
//    Explanation: You can swap s[0] = 'a' and s[1] = 'b' to get "ba", which is equal to goal.
//    Example 2:
//
//    Input: s = "ab", goal = "ab"
//    Output: false
//    Explanation: The only letters you can swap are s[0] = 'a' and s[1] = 'b', which results in "ba" != goal.
//    Example 3:
//
//    Input: s = "aa", goal = "aa"
//    Output: true
//    Explanation: You can swap s[0] = 'a' and s[1] = 'a' to get "aa", which is equal to goal.
//
//
//    Constraints:
//
//    1 <= s.length, goal.length <= 2 * 104
//    s and goal consist of lowercase letters.