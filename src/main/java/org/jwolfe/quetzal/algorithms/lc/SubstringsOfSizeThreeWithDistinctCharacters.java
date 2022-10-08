package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class SubstringsOfSizeThreeWithDistinctCharacters {
    class Solution {
        public int countGoodSubstrings(String s) {
            if(s == null || s.length() < 3) {
                return 0;
            }

            int count = 0;

            for(int i = 0; i < s.length() - 2; i++) {
                char a = s.charAt(i);
                char b = s.charAt(i + 1);
                char c = s.charAt(i + 2);

                if(a != b && b != c && a != c) {
                    count++;
                }
            }

            return count;
        }
    }

    class Solution_Map {
        public int countGoodSubstrings(String s) {
            if(s == null || s.length() < 3) {
                return 0;
            }

            int count = 0;

            Map<Character, Integer> map = new HashMap<>();
            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                map.put(c, map.getOrDefault(c, 0) + 1);

                count += (map.size() == 3 ? 1 : 0);

                if(i >= 2) {
                    char pc = s.charAt(i - 2);
                    if(map.get(pc) == 1) {
                        map.remove(pc);
                    } else {
                        map.put(pc, map.get(pc) - 1);
                    }
                }
            }

            return count;
        }
    }

// "xyzzaz"
}

//    1876. Substrings of Size Three with Distinct Characters
//    Easy
//    A string is good if there are no repeated characters.
//
//    Given a string s​​​​​, return the number of good substrings of length three in s​​​​​​.
//
//    Note that if there are multiple occurrences of the same substring, every occurrence should be counted.
//
//    A substring is a contiguous sequence of characters in a string.
//
//
//
//    Example 1:
//
//    Input: s = "xyzzaz"
//    Output: 1
//    Explanation: There are 4 substrings of size 3: "xyz", "yzz", "zza", and "zaz".
//    The only good substring of length 3 is "xyz".
//    Example 2:
//
//    Input: s = "aababcabc"
//    Output: 4
//    Explanation: There are 7 substrings of size 3: "aab", "aba", "bab", "abc", "bca", "cab", and "abc".
//    The good substrings are "abc", "bca", "cab", and "abc".
//
//
//    Constraints:
//
//    1 <= s.length <= 100
//    s​​​​​​ consists of lowercase English letters.
