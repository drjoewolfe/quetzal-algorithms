package org.jwolfe.quetzal.algorithms.lc;

import org.jwolfe.quetzal.library.general.Pair;

import java.util.LinkedList;
import java.util.Queue;

public class MovePiecesToObtainAString {
    class Solution {
        public boolean canChange(String start, String target) {
            if ((start == null && target == null) || (start.length() == 0 && target.length() == 0)) {
                return true;
            }

            if (start == null || target == null || start.length() != target.length()) {
                return false;
            }

            Queue<Pair<Character, Integer>> startQueue = new LinkedList<>();
            Queue<Pair<Character, Integer>> targetQueue = new LinkedList<>();

            int n = start.length();
            for (int i = 0; i < n; i++) {
                char sc = start.charAt(i);
                char tc = target.charAt(i);

                if (sc != '_') {
                    startQueue.offer(new Pair<>(sc, i));
                }

                if (tc != '_') {
                    targetQueue.offer(new Pair<>(tc, i));
                }
            }

            if (startQueue.size() != targetQueue.size()) {
                return false;
            }

            while (!startQueue.isEmpty()) {
                var startElement = startQueue.poll();
                var targetElement = targetQueue.poll();

                char sc = startElement.getKey();
                char tc = targetElement.getKey();

                int si = startElement.getValue();
                int ti = targetElement.getValue();

                if (sc != tc
                        || (sc == 'L' && si < ti)
                        || (sc == 'R' && si > ti)) {
                    return false;
                }
            }

            return true;
        }
    }

    class Solution_Correct_1 {
        public boolean canChange(String start, String target) {
            if (start == null || target == null || start.length() != target.length()) {
                return false;
            }

            int n = start.length();
            int si = 0;
            int ti = 0;

            while (si < n || ti < n) {
                while (si < n && start.charAt(si) == '_') {
                    si++;
                }

                while (ti < n && target.charAt(ti) == '_') {
                    ti++;
                }

                if (si == n && ti == n) {
                    return true;
                }

                if (si == n || ti == n) {
                    return false;
                }

                char sc = start.charAt(si);
                char tc = target.charAt(ti);

                if (sc != tc) {
                    return false;
                }

                if (sc == 'L') {
                    if (si < ti) {
                        return false;
                    }
                } else {
                    if (si > ti) {
                        return false;
                    }
                }

                si++;
                ti++;
            }

            return si == n && ti == n;
        }
    }

// "_L__R__R_"
// "L______RR"

// "_L"
// "LL"
}

//    2337. Move Pieces to Obtain a String
//    Medium
//    You are given two strings start and target, both of length n. Each string consists only of the characters 'L', 'R', and '_' where:
//
//    The characters 'L' and 'R' represent pieces, where a piece 'L' can move to the left only if there is a blank space directly to its left, and a piece 'R' can move to the right only if there is a blank space directly to its right.
//    The character '_' represents a blank space that can be occupied by any of the 'L' or 'R' pieces.
//    Return true if it is possible to obtain the string target by moving the pieces of the string start any number of times. Otherwise, return false.
//
//
//
//    Example 1:
//
//    Input: start = "_L__R__R_", target = "L______RR"
//    Output: true
//    Explanation: We can obtain the string target from start by doing the following moves:
//    - Move the first piece one step to the left, start becomes equal to "L___R__R_".
//    - Move the last piece one step to the right, start becomes equal to "L___R___R".
//    - Move the second piece three steps to the right, start becomes equal to "L______RR".
//    Since it is possible to get the string target from start, we return true.
//    Example 2:
//
//    Input: start = "R_L_", target = "__LR"
//    Output: false
//    Explanation: The 'R' piece in the string start can move one step to the right to obtain "_RL_".
//    After that, no pieces can move anymore, so it is impossible to obtain the string target from start.
//    Example 3:
//
//    Input: start = "_R", target = "R_"
//    Output: false
//    Explanation: The piece in the string start can move only to the right, so it is impossible to obtain the string target from start.
//
//
//    Constraints:
//
//    n == start.length == target.length
//    1 <= n <= 105
//    start and target consist of the characters 'L', 'R', and '_'.