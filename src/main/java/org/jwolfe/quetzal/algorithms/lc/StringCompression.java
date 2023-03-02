package org.jwolfe.quetzal.algorithms.lc;

public class StringCompression {
    class Solution {
        public int compress(char[] chars) {
            if(chars == null || chars.length == 0) {
                return 0;
            }

            int n = chars.length;
            int position = 0;
            int index = 0;

            while(index < n) {
                char c = chars[index];
                int groupSize = 1;

                while(index + groupSize < n && chars[index + groupSize] == c) {
                    groupSize++;
                }

                chars[position++] = c;
                if(groupSize > 1) {
                    char[] countArray = Integer.toString(groupSize).toCharArray();
                    for(int i = 0; i < countArray.length; i++) {
                        chars[position++] = countArray[i];
                    }
                }

                index += groupSize;
            }

            return position;
        }
    }

    class Solution_Correct_1 {
        public int compress(char[] chars) {
            if(chars == null || chars.length == 0) {
                return 0;
            }

            int index = 0;
            char prev = chars[0];
            int count = 1;

            for(int i = 1; i < chars.length; i++) {
                char current = chars[i];

                if(prev == current) {
                    count++;
                } else {
                    chars[index++] = prev;
                    if(count > 1) {
                        char[] countArray = ("" + count).toCharArray();
                        for(int k = 0; k < countArray.length; k++) {
                            chars[index++] = countArray[k];
                        }
                    }

                    prev = current;
                    count = 1;
                }
            }

            chars[index++] = prev;
            if(count > 1) {
                char[] countArray = ("" + count).toCharArray();
                for(int k = 0; k < countArray.length; k++) {
                    chars[index++] = countArray[k];
                }
            }

            return index;
        }
    }
}

//    443. String Compression
//    Medium
//    Given an array of characters chars, compress it using the following algorithm:
//
//    Begin with an empty string s. For each group of consecutive repeating characters in chars:
//
//    If the group's length is 1, append the character to s.
//    Otherwise, append the character followed by the group's length.
//    The compressed string s should not be returned separately, but instead, be stored in the input character array chars. Note that group lengths that are 10 or longer will be split into multiple characters in chars.
//
//    After you are done modifying the input array, return the new length of the array.
//
//    You must write an algorithm that uses only constant extra space.
//
//
//
//    Example 1:
//
//    Input: chars = ["a","a","b","b","c","c","c"]
//    Output: Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
//    Explanation: The groups are "aa", "bb", and "ccc". This compresses to "a2b2c3".
//    Example 2:
//
//    Input: chars = ["a"]
//    Output: Return 1, and the first character of the input array should be: ["a"]
//    Explanation: The only group is "a", which remains uncompressed since it's a single character.
//    Example 3:
//
//    Input: chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
//    Output: Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].
//    Explanation: The groups are "a" and "bbbbbbbbbbbb". This compresses to "ab12".
//
//
//    Constraints:
//
//    1 <= chars.length <= 2000
//    chars[i] is a lowercase English letter, uppercase English letter, digit, or symbol.