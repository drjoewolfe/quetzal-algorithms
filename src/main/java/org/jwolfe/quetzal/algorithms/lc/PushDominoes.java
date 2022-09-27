package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PushDominoes {
    class Solution {
        public String pushDominoes(String dominoes) {
            if(dominoes == null || dominoes.length() == 0) {
                return dominoes;
            }

            int n = dominoes.length();
            List<DominoOrientation> orientations = new ArrayList<>();
            orientations.add(new DominoOrientation(-1, 'L'));

            for(int i = 0; i < n; i++) {
                char c = dominoes.charAt(i);

                if(c != '.') {
                    orientations.add(new DominoOrientation(i, c));
                }
            }

            orientations.add(new DominoOrientation(n, 'R'));

            char[] results = dominoes.toCharArray();
            for(int i = 0; i < orientations.size() - 1; i++) {
                var leftOrientation = orientations.get(i);
                var rightOrientation = orientations.get(i + 1);

                if(leftOrientation.direction == rightOrientation.direction) {
                    for(int k = leftOrientation.index + 1; k < rightOrientation.index; k++) {
                        results[k] = leftOrientation.direction;
                    }
                } else {
                    if(leftOrientation.direction == 'R') {
                        int left = leftOrientation.index;
                        int right = rightOrientation.index;
                        for(int k = left + 1; k < right; k++) {
                            if(k - left == right - k) {
                                // mid point
                                results[k] = '.';
                            } else {
                                if(k - left > right - k) {
                                    results[k] = 'L';
                                } else {
                                    results[k] = 'R';
                                }
                            }
                        }
                    }

                    // LR does not have an impact;
                }
            }

            return String.valueOf(results);
        }

        private class DominoOrientation {
            int index;
            char direction;

            public DominoOrientation(int index, char direction) {
                this.index = index;
                this.direction = direction;
            }
        }
    }

    class Solution_Correct_1 {
        public String pushDominoes(String dominoes) {
            if(dominoes == null || dominoes.length() < 2) {
                return dominoes;
            }

            int n = dominoes.length();
            int[] forces = new int[n];

            int force = 0;
            for(int i = 0; i < n; i++) {
                char c = dominoes.charAt(i);

                if(c == 'R') {
                    force = n;
                } else if(c == 'L') {
                    force = 0;
                } else {
                    force = Math.max(force - 1, 0);
                }

                forces[i] += force;
            }

            force = 0;
            for(int i = n - 1; i >= 0; i--) {
                char c = dominoes.charAt(i);

                if(c == 'L') {
                    force = n;
                } else if(c == 'R') {
                    force = 0;
                } else {
                    force = Math.max(force - 1, 0);
                }

                forces[i] -= force;
            }

            StringBuilder builder = new StringBuilder();
            for(int i = 0; i < n; i++) {
                if(forces[i] > 0) {
                    builder.append('R');
                } else if(forces[i] < 0) {
                    builder.append('L');
                } else {
                    builder.append('.');
                }
            }

            return builder.toString();
        }
    }

    class Solution_Groups {
        public String pushDominoes(String dominoes) {
            if(dominoes == null || dominoes.length() == 0) {
                return dominoes;
            }

            int n = dominoes.length();

            // A.....B -> A = B, A != B (A = L/R, B = R/L) cases
            List<Integer> sentinals = new ArrayList<>();
            List<Character> symbols = new ArrayList<>();

            sentinals.add(-1);
            symbols.add('L');

            for(int i = 0; i < n; i++) {
                char c = dominoes.charAt(i);
                if(c != '.') {
                    sentinals.add(i);
                    symbols.add(c);
                }
            }

            sentinals.add(n);
            symbols.add('R');


            char[] tiles = dominoes.toCharArray();
            for(int index = 0; index < sentinals.size() - 1; index++) {
                int i = sentinals.get(index);
                int j = sentinals.get(index + 1);

                char c1 = symbols.get(index);
                char c2 = symbols.get(index + 1);

                if(c1 == c2) {
                    for(int k = i + 1; k < j; k++) {
                        tiles[k] = c1;
                    }
                } else if(c1 == 'R' && c2 == 'L') {
                    int len = j - i - 1;
                    int half = len / 2;
                    for(int k = i + 1; k <= i + half; k++) {
                        tiles[k] = 'R';
                    }

                    for(int k = j - 1; k >= j - half; k--) {
                        tiles[k] = 'L';
                    }
                }
            }

            return new String(tiles);
        }
    }


    class Solution_Incorrect {
        public String pushDominoes(String dominoes) {
            if(dominoes == null || dominoes.length() < 2) {
                return dominoes;
            }

            int n = dominoes.length();
            Queue<Integer> queue = new LinkedList<>();
            for(int i = 0; i < n; i++) {
                if(dominoes.charAt(i) != '.') {
                    queue.offer(i);
                }
            }

            StringBuilder builder = new StringBuilder(dominoes);
            while(!queue.isEmpty()) {
                int size = queue.size();
                for(int i = 0; i < size; i++) {
                    int index = queue.poll();
                    char c = builder.charAt(index);
                    if(c == 'L') {
                        if(index > 0) {
                            char lc = builder.charAt(index - 1);
                            if(lc == '.') {
                                builder.setCharAt(index - 1, 'L');
                                queue.offer(index - 1);
                            } else if(lc == 'R') {
                                builder.setCharAt(index - 1, '.');
                            }
                        }
                    } else if(c == 'R') {
                        if(index < n - 1) {
                            char rc = builder.charAt(index + 1);
                            if(rc == '.') {
                                builder.setCharAt(index + 1, 'R');
                                queue.offer(index + 1);
                            } else if(rc == 'L') {
                                builder.setCharAt(index + 1, '.');
                            }
                        }
                    }
                }

                System.out.println(builder.toString());
                System.out.println(queue);
            }

            return builder.toString();
        }
    }

// ".L.R...LR..L.."
}

//    838. Push Dominoes
//    Medium
//    There are n dominoes in a line, and we place each domino vertically upright. In the beginning, we simultaneously push some of the dominoes either to the left or to the right.
//
//    After each second, each domino that is falling to the left pushes the adjacent domino on the left. Similarly, the dominoes falling to the right push their adjacent dominoes standing on the right.
//
//    When a vertical domino has dominoes falling on it from both sides, it stays still due to the balance of the forces.
//
//    For the purposes of this question, we will consider that a falling domino expends no additional force to a falling or already fallen domino.
//
//    You are given a string dominoes representing the initial state where:
//
//    dominoes[i] = 'L', if the ith domino has been pushed to the left,
//    dominoes[i] = 'R', if the ith domino has been pushed to the right, and
//    dominoes[i] = '.', if the ith domino has not been pushed.
//    Return a string representing the final state.
//
//
//
//    Example 1:
//
//    Input: dominoes = "RR.L"
//    Output: "RR.L"
//    Explanation: The first domino expends no additional force on the second domino.
//    Example 2:
//
//
//    Input: dominoes = ".L.R...LR..L.."
//    Output: "LL.RR.LLRRLL.."
//
//
//    Constraints:
//
//    n == dominoes.length
//    1 <= n <= 105
//    dominoes[i] is either 'L', 'R', or '.'.