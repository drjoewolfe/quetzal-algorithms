package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class OpenTheLock {
    class Solution {
        public int openLock(String[] deadends, String target) {
            if (target == null || target.length() != 4 || target.equals("0000")) {
                return 0;
            }

            Set<String> deadendSet = new HashSet<>();
            for (String combination : deadends) {
                deadendSet.add(combination);
            }

            String start = "0000";

            if (deadendSet.contains(start) || deadendSet.contains(target)) {
                return -1;
            }

            Queue<String> queue = new LinkedList<>();
            Set<String> visited = new HashSet<>();

            queue.offer(start);
            visited.add(start);

            int turns = 0;
            while (!queue.isEmpty()) {
                turns++;
                int size = queue.size();
                while (size > 0) {
                    String currentCombination = queue.poll();

                    for (int i = 0; i < 4; i++) {
                        // Up
                        String upCombination = getUp(currentCombination, i);
                        if (!deadendSet.contains(upCombination) && !visited.contains(upCombination)) {
                            if (upCombination.equals(target)) {
                                return turns;
                            }

                            queue.offer(upCombination);
                            visited.add(upCombination);
                        }

                        // Down
                        String downCombination = getDown(currentCombination, i);
                        if (!deadendSet.contains(downCombination) && !visited.contains(downCombination)) {
                            if (downCombination.equals(target)) {
                                return turns;
                            }

                            queue.offer(downCombination);
                            visited.add(downCombination);
                        }
                    }

                    size--;
                }
            }

            return -1;
        }

        private String getUp(String combination, int index) {
            char[] arr = combination.toCharArray();
            char c = arr[index];

            if (c == '9') {
                arr[index] = '0';
            } else {
                arr[index] = (char) (c + 1);
            }

            return new String(arr);
        }

        private String getDown(String combination, int index) {
            char[] arr = combination.toCharArray();
            char c = arr[index];

            if (c == '0') {
                arr[index] = '9';
            } else {
                arr[index] = (char) (c - 1);
            }

            return new String(arr);
        }
    }

    class Solution_Correct_1 {
        public int openLock(String[] deadends, String target) {
            if (target == null || target.length() < 4 || target.equals("0000")) {
                return 0;
            }

            Set<String> deadEndSet = new HashSet<>();
            for (String de : deadends) {
                deadEndSet.add(de);
            }

            String start = "0000";
            if (deadEndSet.contains(start)) {
                return -1;
            }

            Queue<String> queue = new LinkedList<>();
            Set<String> visited = new HashSet<>();

            queue.offer(start);
            visited.add(start);

            int turns = 0;
            while (!queue.isEmpty()) {
                turns++;
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    String comb = queue.poll();

                    char[] combArray = comb.toCharArray();
                    for (int j = 0; j < 4; j++) {
                        char c = combArray[j];
                        // Up
                        if (c == '9') {
                            combArray[j] = '0';
                        } else {
                            combArray[j] = (char) (c + 1);
                        }

                        String upComb = new String(combArray);
                        if (!deadEndSet.contains(upComb) && !visited.contains(upComb)) {
                            if (upComb.equals(target)) {
                                return turns;
                            }

                            queue.offer(upComb);
                            visited.add(upComb);
                        }

                        // Down
                        if (c == '0') {
                            combArray[j] = '9';
                        } else {
                            combArray[j] = (char) (c - 1);
                        }

                        String downComb = new String(combArray);
                        if (!deadEndSet.contains(downComb) && !visited.contains(downComb)) {
                            if (downComb.equals(target)) {
                                return turns;
                            }

                            queue.offer(downComb);
                            visited.add(downComb);
                        }

                        combArray[j] = c;
                    }

                }
            }

            return -1;
        }
    }

// ["0201","0101","0102","1212","2002"]
// "0202"

// ["8887","8889","8878","8898","8788","8988","7888","9888"]
// "8888"

// ["0000"]
// "8888"
}

//    752. Open the Lock
//    Medium
//
//    You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.
//
//    The lock initially starts at '0000', a string representing the state of the 4 wheels.
//
//    You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the lock will stop turning and you will be unable to open it.
//
//    Given a target representing the value of the wheels that will unlock the lock, return the minimum total number of turns required to open the lock, or -1 if it is impossible.
//
//
//
//    Example 1:
//
//    Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
//    Output: 6
//    Explanation:
//    A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
//    Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
//    because the wheels of the lock become stuck after the display becomes the dead end "0102".
//    Example 2:
//
//    Input: deadends = ["8888"], target = "0009"
//    Output: 1
//    Explanation:
//    We can turn the last wheel in reverse to move from "0000" -> "0009".
//    Example 3:
//
//    Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
//    Output: -1
//    Explanation:
//    We can't reach the target without getting stuck.
//    Example 4:
//
//    Input: deadends = ["0000"], target = "8888"
//    Output: -1
//
//
//    Constraints:
//
//    1 <= deadends.length <= 500
//    deadends[i].length == 4
//    target.length == 4
//    target will not be in the list deadends.
//    target and deadends[i] consist of digits only.