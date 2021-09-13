package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class MaximumNumberOfBalloons {
    class Solution {
        public int maxNumberOfBalloons(String text) {
            if(text == null || text.length() < 6) {
                return 0;
            }

            Map<Character, Integer> frequencies = new HashMap<>();
            for(int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
            }

            int count = 0;
            String balloon = "balloon";
            while(true) {
                for(int i = 0; i < balloon.length(); i++) {
                    char c = balloon.charAt(i);
                    if(!frequencies.containsKey(c)
                            || frequencies.get(c) == 0) {
                        return count;
                    }

                    frequencies.put(c, frequencies.get(c) - 1);
                }

                count++;
            }
        }
    }

    class Solution_Correct_2 {
        public int maxNumberOfBalloons(String text) {
            if(text == null || text.length() < 7) {
                return 0;
            }

            int[] frequencies = new int[26];
            for(int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                frequencies[c - 'a']++;
            }

            return min(frequencies['a' - 'a'],
                    frequencies['b' - 'a'],
                    frequencies['l' - 'a'] / 2,
                    frequencies['o' - 'a'] / 2,
                    frequencies['n' - 'a']
            );
        }

        private int min(int... nums) {
            int min = Integer.MAX_VALUE;
            for(int a : nums) {
                min = Math.min(min, a);
            }

            return min;
        }
    }
}

//    1189. Maximum Number of Balloons
//    Easy
//    Given a string text, you want to use the characters of text to form as many instances of the word "balloon" as possible.
//
//    You can use each character in text at most once. Return the maximum number of instances that can be formed.
//
//
//
//    Example 1:
//
//
//
//    Input: text = "nlaebolko"
//    Output: 1
//    Example 2:
//
//
//
//    Input: text = "loonbalxballpoon"
//    Output: 2
//    Example 3:
//
//    Input: text = "leetcode"
//    Output: 0
//
//
//    Constraints:
//
//    1 <= text.length <= 104
//    text consists of lower case English letters only.
