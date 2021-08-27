package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LongestUncommonSubsequenceII {
    class Solution {
        public int findLUSlength(String[] strs) {
            if(strs == null || strs.length == 0) {
                return -1;
            }

            Map<String, Integer> map = new HashMap<>();
            for(String s : strs) {
                map.put(s, map.getOrDefault(s, 0) + 1);
            }

            Arrays.sort(strs, (s1, s2) -> s2.length() - s1.length());

            int maxLength = -1;
            for(int i = 0; i < strs.length; i++) {
                String s = strs[i];
                if(map.get(s) > 1) {
                    continue;
                }

                boolean isSubsequence = false;
                for(int j = 0; j < i; j++) {
                    String ps = strs[j];
                    if(ps.length() == s.length()) {
                        break;
                    }

                    if(isSubsequenceOf(s, ps)) {
                        isSubsequence = true;
                        break;
                    }
                }

                if(isSubsequence) {
                    continue;
                }

                maxLength = Math.max(maxLength, s.length());
            }

            return maxLength;
        }

        private boolean isSubsequenceOf(String s, String ps) {
            int i = 0;

            for(int j = 0; j < ps.length(); j++) {
                if(s.charAt(i) == ps.charAt(j)) {
                    i++;

                    if(i == s.length()) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

// ["aba","cdc","eae"]
// ["aaa","aaa","aa"]
}

//    522. Longest Uncommon Subsequence II
//    Medium
//    Given an array of strings strs, return the length of the longest uncommon subsequence between them. If the longest uncommon subsequence does not exist, return -1.
//
//    An uncommon subsequence between an array of strings is a string that is a subsequence of one string but not the others.
//
//    A subsequence of a string s is a string that can be obtained after deleting any number of characters from s.
//
//    For example, "abc" is a subsequence of "aebdc" because you can delete the underlined characters in "aebdc" to get "abc". Other subsequences of "aebdc" include "aebdc", "aeb", and "" (empty string).
//
//
//    Example 1:
//
//    Input: strs = ["aba","cdc","eae"]
//    Output: 3
//    Example 2:
//
//    Input: strs = ["aaa","aaa","aa"]
//    Output: -1
//
//
//    Constraints:
//
//    1 <= strs.length <= 50
//    1 <= strs[i].length <= 10
//    strs[i] consists of lowercase English letters.
