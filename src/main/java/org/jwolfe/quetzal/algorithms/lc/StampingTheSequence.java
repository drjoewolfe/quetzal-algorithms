package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class StampingTheSequence {
    class Solution {
        public int[] movesToStamp(String stamp, String target) {
            if(stamp == null || stamp.length() == 0 || target == null || target.length() < stamp.length()) {
                return new int[0];
            }

            int n = target.length();
            int m = stamp.length();

            Queue<Integer> queue = new LinkedList<>();
            List<Window> windows = new ArrayList<>();
            Stack<Window> stampedWindows = new Stack<>();
            boolean[] completedIndexes = new boolean[n];

            for(int i = 0; i < n - m + 1; i++) {
                Window window = new Window(i);
                windows.add(window);

                match(target, stamp, window);
                if(processWindowForCompleteMatch(window, queue, completedIndexes)) {
                    stampedWindows.push(window);
                }
            }

            while(!queue.isEmpty()) {
                int index = queue.poll();

                // For all windows thar overlap this index
                for(int j = Math.max(0, index - m + 1); j <= Math.min(n - m, index); j++) {
                    Window window = windows.get(j);
                    if(window.yetToMatchIndexes.contains(index)) {
                        window.yetToMatchIndexes.remove(index);
                        window.matchedIndexes.add(index);

                        if(processWindowForCompleteMatch(window, queue, completedIndexes)) {
                            stampedWindows.push(window);
                        }
                    }
                }
            }

            for(boolean v : completedIndexes) {
                if(!v) {
                    return new int[0];
                }
            }

            int size = stampedWindows.size();
            int[] result = new int[size];
            for(int i = 0; i < size; i++) {
                result[i] = stampedWindows.pop().startIndex;
            }

            return result;
        }

        private void match(String target, String stamp, Window window) {
            int n = target.length();
            int m = stamp.length();

            for(int j = 0; j < m; j++) {
                int i = window.startIndex + j;

                if(target.charAt(i) == stamp.charAt(j)) {
                    window.matchedIndexes.add(i);
                } else {
                    window.yetToMatchIndexes.add(i);
                }
            }
        }

        private boolean processWindowForCompleteMatch(Window window, Queue<Integer> queue, boolean[] completedIndexes) {
            if(window.yetToMatchIndexes.size() > 0) {
                return false;
            }

            boolean processed = false;
            for(int index : window.matchedIndexes) {
                if(!completedIndexes[index]) {
                    queue.offer(index);
                    completedIndexes[index] = true;
                    processed = true;
                }
            }

            return processed;
        }

        private class Window {
            int startIndex;

            Set<Integer> matchedIndexes;
            Set<Integer> yetToMatchIndexes;

            public Window(int startIndex) {
                this.startIndex = startIndex;
                matchedIndexes = new HashSet<>();
                yetToMatchIndexes = new HashSet<>();
            }
        }
    }
}

//    936. Stamping The Sequence
//    Hard
//    You want to form a target string of lowercase letters.
//
//    At the beginning, your sequence is target.length '?' marks.  You also have a stamp of lowercase letters.
//
//    On each turn, you may place the stamp over the sequence, and replace every letter in the sequence with the corresponding letter from the stamp.  You can make up to 10 * target.length turns.
//
//    For example, if the initial sequence is "?????", and your stamp is "abc",  then you may make "abc??", "?abc?", "??abc" in the first turn.  (Note that the stamp must be fully contained in the boundaries of the sequence in order to stamp.)
//
//    If the sequence is possible to stamp, then return an array of the index of the left-most letter being stamped at each turn.  If the sequence is not possible to stamp, return an empty array.
//
//    For example, if the sequence is "ababc", and the stamp is "abc", then we could return the answer [0, 2], corresponding to the moves "?????" -> "abc??" -> "ababc".
//
//    Also, if the sequence is possible to stamp, it is guaranteed it is possible to stamp within 10 * target.length moves.  Any answers specifying more than this number of moves will not be accepted.
//
//
//
//    Example 1:
//
//    Input: stamp = "abc", target = "ababc"
//    Output: [0,2]
//    ([1,0,2] would also be accepted as an answer, as well as some other answers.)
//    Example 2:
//
//    Input: stamp = "abca", target = "aabcaca"
//    Output: [3,0,1]
//
//
//    Note:
//
//    1 <= stamp.length <= target.length <= 1000
//    stamp and target only contain lowercase letters.