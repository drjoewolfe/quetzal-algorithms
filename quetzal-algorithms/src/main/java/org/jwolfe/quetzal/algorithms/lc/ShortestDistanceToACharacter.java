package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class ShortestDistanceToACharacter {
    public int[] shortestToChar(String S, char C) {
        if(S == null || S.length() == 0) {
            return new int[0];
        }

        int n = S.length();
        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);

        int d = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            char l = S.charAt(i);
            if(l == C) {
                d = 0;
            } else if(d != Integer.MAX_VALUE) {
                d++;
            }

            distances[i] = Math.min(distances[i], d);
        }

        d = Integer.MAX_VALUE;
        for(int i = n - 1; i >= 0; i--) {
            char l = S.charAt(i);
            if(l == C) {
                d = 0;
            } else if(d != Integer.MAX_VALUE) {
                d++;
            }

            distances[i] = Math.min(distances[i], d);
        }

        return distances;
    }
}

//    821. Shortest Distance to a Character
//    Easy
//    Given a string s and a character c that occurs in s, return an array of integers answer where answer.length == s.length and answer[i] is the shortest distance from s[i] to the character c in s.
//
//
//
//    Example 1:
//
//    Input: s = "loveleetcode", c = "e"
//    Output: [3,2,1,0,1,0,0,1,2,2,1,0]
//    Example 2:
//
//    Input: s = "aaab", c = "b"
//    Output: [3,2,1,0]
//
//
//    Constraints:
//
//    1 <= s.length <= 104
//    s[i] and c are lowercase English letters.
//    c occurs at least once in s.