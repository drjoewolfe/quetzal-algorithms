package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashSet;
import java.util.Set;

public class LetterTilePossibilities {
    class Solution {
        public int numTilePossibilities(String tiles) {
            if (tiles == null || tiles.length() == 0) {
                return 0;
            }

            Set<String> sequences = new HashSet<>();
            int n = tiles.length();
            boolean[] used = new boolean[n];

            generateSequences(tiles, new StringBuilder(), used, sequences);
            return sequences.size() - 1;
        }

        private void generateSequences(String tiles, StringBuilder builder, boolean[] used, Set<String> sequences) {
            String current = builder.toString();
            sequences.add(current);

            for (int i = 0; i < tiles.length(); i++) {
                if (used[i]) {
                    continue;
                }

                char c = tiles.charAt(i);

                used[i] = true;
                builder.append(c);
                generateSequences(tiles, builder, used, sequences);
                used[i] = false;
                builder.deleteCharAt(builder.length() - 1);
            }
        }
    }
}

//    1079. Letter Tile Possibilities
//    Medium
//    You have n  tiles, where each tile has one letter tiles[i] printed on it.
//
//    Return the number of possible non-empty sequences of letters you can make using the letters printed on those tiles.
//
//
//
//    Example 1:
//
//    Input: tiles = "AAB"
//    Output: 8
//    Explanation: The possible sequences are "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA".
//    Example 2:
//
//    Input: tiles = "AAABBC"
//    Output: 188
//    Example 3:
//
//    Input: tiles = "V"
//    Output: 1
//
//
//    Constraints:
//
//    1 <= tiles.length <= 7
//    tiles consists of uppercase English letters.