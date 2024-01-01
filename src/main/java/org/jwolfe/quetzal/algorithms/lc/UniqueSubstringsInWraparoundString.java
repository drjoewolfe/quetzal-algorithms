package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashSet;
import java.util.Set;

public class UniqueSubstringsInWraparoundString {
    class Solution {
        public int findSubstringInWraproundString(String p) {
            if(p == null || p.length() == 0) {
                return 0;
            }

            int n = p.length();
            int maxCurrentLength = 0;

            int[] counts = new int[26];
            for(int i = 0; i < n; i++) {
                char c = p.charAt(i);

                if(i > 0
                        && ((c == p.charAt(i - 1) + 1) || (c == 'a' && p.charAt(i - 1) == 'z'))) {
                    maxCurrentLength++;
                } else {
                    maxCurrentLength = 1;
                }

                int index = c - 'a';
                counts[index] = Math.max(counts[index], maxCurrentLength);
            }

            int totalCount = 0;
            for(int a : counts) {
                totalCount += a;
            }

            return totalCount;
        }
    }

    class SolutionTLE {
        public int findSubstringInWraproundString(String p) {
            if(p == null || p.length() == 0) {
                return 0;
            }

            int n = p.length();
            int count = 0;
            Set<String> seen = new HashSet<>();
            for(int i = 0; i < n; i++) {
                char prev = p.charAt(i);
                String prevStr = String.valueOf(prev);
                if(!seen.contains(prevStr)) {
                    count++;
                    seen.add(prevStr);
                }

                for(int j = i + 1; j < n; j++) {
                    char curr = p.charAt(j);

                    if(!((curr == prev + 1)
                            || (curr == 'a' && prev == 'z'))) {
                        break;
                    }

                    prev = curr;

                    String str = p.substring(i, j + 1);
                    if(seen.contains(str)) {
                        continue;
                    }

                    seen.add(str);
                    count++;

                }
            }

            return count;
        }
    }

// "a"
// "cac"
// "zab"
// "cdefghefghijklmnopqrstuvwxmnijklmnopqrstuvbcdefghijklmnopqrstuvwabcddefghijklfghijklmabcdefghijklmnopqrstuvwxymnopqrstuvwxyz"
// "cdefghefghijklmnopqrstuvwxmnijklmnopqrstuvbc"
// "cdefghefghijklmnopqrstuvwxm"
// "cdefghefghij"
// "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz"
}

//    467. Unique Substrings in Wraparound String
//    Medium
//    We define the string base to be the infinite wraparound string of "abcdefghijklmnopqrstuvwxyz", so base will look like this:
//
//    "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".
//    Given a string s, return the number of unique non-empty substrings of s are present in base.
//
//
//
//    Example 1:
//
//    Input: s = "a"
//    Output: 1
//    Explanation: Only the substring "a" of s is in base.
//    Example 2:
//
//    Input: s = "cac"
//    Output: 2
//    Explanation: There are two substrings ("a", "c") of s in base.
//    Example 3:
//
//    Input: s = "zab"
//    Output: 6
//    Explanation: There are six substrings ("z", "a", "b", "za", "ab", and "zab") of s in base.
//
//
//    Constraints:
//
//    1 <= s.length <= 105
//    s consists of lowercase English letters.