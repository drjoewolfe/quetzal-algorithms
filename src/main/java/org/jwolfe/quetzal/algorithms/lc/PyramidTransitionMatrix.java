package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PyramidTransitionMatrix {
    class Solution {
        public boolean pyramidTransition(String bottom, List<String> allowed) {
            if (bottom == null || bottom.length() < 2) {
                return true;
            }

            if (allowed == null || allowed.size() == 0) {
                return false;
            }

            Map<String, List<Character>> allowedTransitionsMap = new HashMap<>();
            for (String str : allowed) {
                String blockPattern = str.substring(0, 2);
                char nextBlock = str.charAt(2);
                if (!allowedTransitionsMap.containsKey(blockPattern)) {
                    allowedTransitionsMap.put(blockPattern, new ArrayList<>());
                }

                allowedTransitionsMap.get(blockPattern).add(nextBlock);
            }

            return pyramidTransition(bottom, 0, allowedTransitionsMap, new StringBuilder());
        }

        private boolean pyramidTransition(String curr, int index, Map<String, List<Character>> allowedTransitionsMap, StringBuilder nextBuilder) {
            if (curr.length() == 1) {
                return true;
            }

            if (index == curr.length() - 1) {
                // In last column of current row. Move to next.
                String next = nextBuilder.toString();
                return pyramidTransition(next, 0, allowedTransitionsMap, new StringBuilder());
            }

            String blockPattern = curr.substring(index, index + 2);
            if (!allowedTransitionsMap.containsKey(blockPattern)) {
                return false;
            }

            List<Character> allowedTransitions = allowedTransitionsMap.get(blockPattern);
            for (char nextBlock : allowedTransitions) {
                nextBuilder.append(nextBlock);
                if (pyramidTransition(curr, index + 1, allowedTransitionsMap, nextBuilder)) {
                    return true;
                }

                nextBuilder.deleteCharAt(nextBuilder.length() - 1);
            }

            return false;
        }
    }
}

//    756. Pyramid Transition Matrix
//    Medium
//    You are stacking blocks to form a pyramid. Each block has a color, which is represented by a single letter. Each row of blocks contains one less block than the row beneath it and is centered on top.
//
//    To make the pyramid aesthetically pleasing, there are only specific triangular patterns that are allowed. A triangular pattern consists of a single block stacked on top of two blocks. The patterns are given as a list of three-letter strings allowed, where the first two characters of a pattern represent the left and right bottom blocks respectively, and the third character is the top block.
//
//    For example, "ABC" represents a triangular pattern with a 'C' block stacked on top of an 'A' (left) and 'B' (right) block. Note that this is different from "BAC" where 'B' is on the left bottom and 'A' is on the right bottom.
//    You start with a bottom row of blocks bottom, given as a single string, that you must use as the base of the pyramid.
//
//    Given bottom and allowed, return true if you can build the pyramid all the way to the top such that every triangular pattern in the pyramid is in allowed, or false otherwise.
//
//
//
//    Example 1:
//
//
//    Input: bottom = "BCD", allowed = ["BCC","CDE","CEA","FFF"]
//    Output: true
//    Explanation: The allowed triangular patterns are shown on the right.
//    Starting from the bottom (level 3), we can build "CE" on level 2 and then build "A" on level 1.
//    There are three triangular patterns in the pyramid, which are "BCC", "CDE", and "CEA". All are allowed.
//    Example 2:
//
//
//    Input: bottom = "AAAA", allowed = ["AAB","AAC","BCD","BBE","DEF"]
//    Output: false
//    Explanation: The allowed triangular patterns are shown on the right.
//    Starting from the bottom (level 4), there are multiple ways to build level 3, but trying all the possibilites, you will get always stuck before building level 1.
//
//
//    Constraints:
//
//    2 <= bottom.length <= 6
//    0 <= allowed.length <= 216
//    allowed[i].length == 3
//    The letters in all input strings are from the set {'A', 'B', 'C', 'D', 'E', 'F'}.
//    All the values of allowed are unique.