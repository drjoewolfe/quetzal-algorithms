package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MaximumLengthOfAConcatenatedStringWithUniqueCharacters {
    class Solution {
        public int maxLength(List<String> arr) {
            if(arr == null || arr.size() == 0) {
                return 0;
            }

            return maxLength(arr, 0, 0);
        }

        private int maxLength(List<String> arr, int index, int mask) {
            if(index == arr.size()) {
                return getLength(mask);
            }

            boolean canInclude = true;

            int mergedMask = mask;
            String str = arr.get(index);
            for(int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                int number = c - 'a';

                if((mergedMask & (1 << number)) > 0) {
                    canInclude = false;
                    break;
                }

                mergedMask |= (1 << number);
            }

            int length = 0;
            if(canInclude) {
                // Include
                length = maxLength(arr, index + 1, mergedMask);
            }

            // Exclude
            length = Math.max(length, maxLength(arr, index + 1, mask));

            return length;
        }

        private int getLength(int mask) {
            int length = 0;

            while(mask > 0) {
                length += mask & 1;
                mask >>= 1;
            }

            return length;
        }
    }

    class Solution_Correct_2 {
        public int maxLength(List<String> arr) {
            if(arr == null || arr.size() == 0) {
                return 0;
            }

            return maxLength(arr, 0, 0);
        }

        private int maxLength(List<String> arr, int index, int mask) {
            if(index == arr.size()) {
                return getLength(mask);
            }

            String str = arr.get(index);
            boolean canInclude = true;
            int mergedMask = mask;
            for(int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                int pos = c - 'a';

                if((mergedMask & (1 << pos)) > 0) {
                    // Character already included in mask
                    canInclude = false;
                    break;
                }

                mergedMask |= (1 << pos);
            }

            int len = 0;

            if(canInclude) {
                len = maxLength(arr, index + 1, mergedMask);
            }

            len = Math.max(len, maxLength(arr, index + 1, mask));
            return len;
        }

        private int getLength(int mask) {
            int len = 0;
            for(int i = 0; i < 26; i++) {
                if((mask & (1 << i)) > 0) {
                    len++;
                }
            }

            return len;
        }
    }

    class Solution_Correct_1 {
        // Backtracking

        int maxLength = 0;
        public int maxLength(List<String> arr) {
            if(arr == null || arr.size() == 0) {
                return 0;
            }

            int n = arr.size();
            int[] counts = new int[26];
            computeMaxUniqueSubsequenceLength(arr, counts, 0);

            return maxLength;
        }

        private void computeMaxUniqueSubsequenceLength(List<String> arr, int[] counts, int index) {
            int currentLength = Arrays.stream(counts).sum();
            maxLength = Math.max(maxLength, currentLength);

            if(index > arr.size()) {
                return;
            }

            for(int i = index; i < arr.size(); i++) {
                String word = arr.get(i);
                if(canBeConcatenated(counts, word)) {
                    concatenate(counts, word);
                    computeMaxUniqueSubsequenceLength(arr, counts, i + 1);
                    removeConcatenation(counts, word);
                }
            }
        }

        private boolean canBeConcatenated(int[] counts, String word) {
            Set<Character> set = new HashSet<>();
            for(int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if(set.contains(c) || counts[c - 'a'] > 0) {
                    return false;
                }

                set.add(c);
            }

            return true;
        }

        private void concatenate(int[] counts, String word) {
            for(int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                counts[c - 'a']++;
            }
        }

        private void removeConcatenation(int[] counts, String word) {
            for(int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                counts[c - 'a']--;
            }
        }
    }

// ["un","iq","ue"]
// ["cha","r","act","ers"]
// ["abcdefghijklmnopqrstuvwxyz"]
// ["aa","bb"]
}

//    1239. Maximum Length of a Concatenated String with Unique Characters
//    Medium
//    You are given an array of strings arr. A string s is formed by the concatenation of a subsequence of arr that has unique characters.
//
//    Return the maximum possible length of s.
//
//    A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.
//
//
//
//    Example 1:
//
//    Input: arr = ["un","iq","ue"]
//    Output: 4
//    Explanation: All the valid concatenations are:
//    - ""
//    - "un"
//    - "iq"
//    - "ue"
//    - "uniq" ("un" + "iq")
//    - "ique" ("iq" + "ue")
//    Maximum length is 4.
//    Example 2:
//
//    Input: arr = ["cha","r","act","ers"]
//    Output: 6
//    Explanation: Possible longest valid concatenations are "chaers" ("cha" + "ers") and "acters" ("act" + "ers").
//    Example 3:
//
//    Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
//    Output: 26
//    Explanation: The only string in arr has all 26 characters.
//
//
//    Constraints:
//
//    1 <= arr.length <= 16
//    1 <= arr[i].length <= 26
//    arr[i] contains only lowercase English letters.