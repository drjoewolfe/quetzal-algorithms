package org.jwolfe.quetzal.algorithms.lc;

import java.util.Stack;

public class UsingARobotToPrintTheLexicographicallySmallestString {
    class Solution {
        public String robotWithString(String s) {
            if(s == null || s.length() < 2) {
                return s;
            }

            int n = s.length();
            int[] frequencies = new int[26];
            for(int i = 0; i < n; i++) {
                frequencies[s.charAt(i) - 'a']++;
            }

            StringBuilder builder = new StringBuilder();
            Stack<Character> stack = new Stack<>();
            for(int i = 0; i < n; i++) {
                char c = s.charAt(i);
                frequencies[c - 'a']--;

                stack.push(c);
                while(!stack.isEmpty() && stack.peek() <= getLowestChar(frequencies)) {
                    builder.append(stack.pop());
                }
            }

            while(!stack.isEmpty()) {
                builder.append(stack.pop());
            }

            return builder.toString();
        }

        private char getLowestChar(int[] frequencies) {
            for(int i = 0; i < 26; i++) {
                if(frequencies[i] > 0) {
                    return (char) ('a' + i);
                }
            }

            return 'a';
        }
    }

// "zza"
}

//    2434. Using a Robot to Print the Lexicographically Smallest String
//    Medium
//    You are given a string s and a robot that currently holds an empty string t. Apply one of the following operations until s and t are both empty:
//
//    Remove the first character of a string s and give it to the robot. The robot will append this character to the string t.
//    Remove the last character of a string t and give it to the robot. The robot will write this character on paper.
//    Return the lexicographically smallest string that can be written on the paper.
//
//
//
//    Example 1:
//
//    Input: s = "zza"
//    Output: "azz"
//    Explanation: Let p denote the written string.
//    Initially p="", s="zza", t="".
//    Perform first operation three times p="", s="", t="zza".
//    Perform second operation three times p="azz", s="", t="".
//    Example 2:
//
//    Input: s = "bac"
//    Output: "abc"
//    Explanation: Let p denote the written string.
//    Perform first operation twice p="", s="c", t="ba".
//    Perform second operation twice p="ab", s="c", t="".
//    Perform first operation p="ab", s="", t="c".
//    Perform second operation p="abc", s="", t="".
//    Example 3:
//
//    Input: s = "bdda"
//    Output: "addb"
//    Explanation: Let p denote the written string.
//    Initially p="", s="bdda", t="".
//    Perform first operation four times p="", s="", t="bdda".
//    Perform second operation four times p="addb", s="", t="".
//
//
//    Constraints:
//
//    1 <= s.length <= 105
//    s consists of only English lowercase letters.