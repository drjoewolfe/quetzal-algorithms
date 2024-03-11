package org.jwolfe.quetzal.algorithms.lc;

public class CustomSortString {
    class Solution {
        public String customSortString(String order, String str) {
            if(order == null || order.length() == 0 || str == null || str.length() == 0) {
                return str;
            }

            int[] frequencies = new int[26];
            for(int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                frequencies[c - 'a']++;
            }

            StringBuilder builder = new StringBuilder();
            for(int i = 0; i < order.length(); i++) {
                char c = order.charAt(i);
                int index = c - 'a';

                while(frequencies[index] > 0) {
                    builder.append(c);
                    frequencies[index]--;
                }
            }

            for(int i = 0; i < 26; i++) {
                if(frequencies[i] == 0) {
                    continue;
                }

                char c = (char) ('a' + i);
                while(frequencies[i] > 0) {
                    builder.append(c);
                    frequencies[i]--;
                }
            }

            return builder.toString();
        }
    }

    class Solution_Correct_1 {
        public String customSortString(String order, String str) {
            if(order == null || order.length() == 0) {
                return str;
            }

            int[] frequency = new int[26];
            for(int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                frequency[c - 'a']++;
            }

            StringBuilder builder = new StringBuilder();
            for(int i = 0; i < order.length(); i++) {
                char c = order.charAt(i);

                int index = c - 'a';
                while(frequency[index] > 0) {
                    builder.append(c);
                    frequency[index]--;
                }
            }

            for(int i = 0; i < 26; i++) {
                char c = (char) ('a' + i);
                while(frequency[i] > 0) {
                    builder.append(c);
                    frequency[i]--;
                }
            }

            return builder.toString();
        }
    }
}

//    791. Custom Sort String
//    Medium
//
//    1272
//
//    238
//
//    Add to List
//
//    Share
//    order and str are strings composed of lowercase letters. In order, no letter occurs more than once.
//
//    order was sorted in some custom order previously. We want to permute the characters of str so that they match the order that order was sorted. More specifically, if x occurs before y in order, then x should occur before y in the returned string.
//
//    Return any permutation of str (as a string) that satisfies this property.
//
//    Example:
//    Input:
//    order = "cba"
//    str = "abcd"
//    Output: "cbad"
//    Explanation:
//    "a", "b", "c" appear in order, so the order of "a", "b", "c" should be "c", "b", and "a".
//    Since "d" does not appear in order, it can be at any position in the returned string. "dcba", "cdba", "cbda" are also valid outputs.
//
//
//    Note:
//
//    order has length at most 26, and no character is repeated in order.
//    str has length at most 200.
//    order and str consist of lowercase letters only.
