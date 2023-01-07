package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NumberOfPeopleAwareOfASecret {
    class Solution {
        private int MOD = 1_000_000_007;

        public int peopleAwareOfSecret(int n, int delay, int forget) {
            if(n <= 0) {
                return 0;
            }

            // dp[i][j] => # people who knows the secret for exactly j + 1 days, on day i
            long[][] dp = new long[n + 1][n + 1];
            dp[1][0] = 1;
            for(int i = 2; i <= n; i++) {
                for(int j = 1; j < forget; j++) {
                    dp[i][j] = dp[i - 1][j - 1];
                }

                for(int j = delay; j < forget; j++) {
                    dp[i][0] += dp[i][j];
                    dp[i][0] %= MOD;
                }
            }

            // print(dp);

            int people = 0;
            for(int j = 0; j < forget; j++) {
                people += dp[n][j];
                people %= MOD;
            }

            return people;
        }

        private void print(int[][] arr) {
            for(int[] row : arr) {
                for(int cell : row) {
                    System.out.print(cell + " ");
                }

                System.out.println();
            }

            System.out.println();
        }
    }

    class Solution_Correct_TLE {
        private int MOD = 1_000_000_007;

        public int peopleAwareOfSecret(int n, int delay, int forget) {
            if(n <= 0) {
                return 0;
            }

            Queue<Integer> queue = new LinkedList<>();
            queue.offer(1);

            int day = 2;
            while(!queue.isEmpty() && day <= n) {
                int size = queue.size();
                for(int i = 0; i < size; i++) {
                    int d = queue.poll();
                    if(d < delay) {
                        queue.offer(d + 1);
                    } else if(d < forget) {
                        queue.offer(d + 1);
                        queue.offer(1);
                    }
                }

                day++;
            }

            return queue.size();
        }
    }

    class Solution_Incorrect {
        private int MOD = 1_000_000_007;

        public int peopleAwareOfSecret(int n, int delay, int forget) {
            if(n < 0) {
                return 0;
            }

            List<Integer> list = new ArrayList<>();
            List<Integer> tempList = new ArrayList<>();
            list.add(1);

            for(int day = 1; day <= n; day++) {
                tempList.clear();

                for(Integer personDay : list) {
                    if((day - personDay + 1) > forget) {
                        // time to forget
                        continue;

                    } else if((day - personDay + 1) > delay) {
                        // spread secret - add new person
                        tempList.add(day);
                    }

                    tempList.add(personDay);
                }

                list.clear();
                list.addAll(tempList);
            }

            return tempList.size();
        }

        private void print(List<Integer> arr) {
            for(int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }

    class Solution_TLE {
        private int MOD = 1_000_000_007;

        public int peopleAwareOfSecret(int n, int delay, int forget) {
            if(n < 0) {
                return 0;
            }

            List<Integer> list = new ArrayList<>();
            List<Integer> tempList = new ArrayList<>();
            list.add(1);

            for(int day = 1; day <= n; day++) {
                tempList.clear();

                for(Integer personDay : list) {
                    if((day - personDay + 1) > forget) {
                        // time to forget
                        continue;

                    } else if((day - personDay + 1) > delay) {
                        // spread secret - add new person
                        tempList.add(day);
                    }

                    tempList.add(personDay);
                }

                list.clear();
                list.addAll(tempList);
            }

            return tempList.size();
        }

        private void print(List<Integer> arr) {
            for(int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }

// 6
// 2
// 4

// 684
// 18
// 496

// 425
// 81
// 118
}

//    2327. Number of People Aware of a Secret
//    Medium
//    On day 1, one person discovers a secret.
//
//    You are given an integer delay, which means that each person will share the secret with a new person every day, starting from delay days after discovering the secret. You are also given an integer forget, which means that each person will forget the secret forget days after discovering it. A person cannot share the secret on the same day they forgot it, or on any day afterwards.
//
//    Given an integer n, return the number of people who know the secret at the end of day n. Since the answer may be very large, return it modulo 109 + 7.
//
//
//
//    Example 1:
//
//    Input: n = 6, delay = 2, forget = 4
//    Output: 5
//    Explanation:
//    Day 1: Suppose the first person is named A. (1 person)
//    Day 2: A is the only person who knows the secret. (1 person)
//    Day 3: A shares the secret with a new person, B. (2 people)
//    Day 4: A shares the secret with a new person, C. (3 people)
//    Day 5: A forgets the secret, and B shares the secret with a new person, D. (3 people)
//    Day 6: B shares the secret with E, and C shares the secret with F. (5 people)
//    Example 2:
//
//    Input: n = 4, delay = 1, forget = 3
//    Output: 6
//    Explanation:
//    Day 1: The first person is named A. (1 person)
//    Day 2: A shares the secret with B. (2 people)
//    Day 3: A and B share the secret with 2 new people, C and D. (4 people)
//    Day 4: A forgets the secret. B, C, and D share the secret with 3 new people. (6 people)
//
//
//    Constraints:
//
//    2 <= n <= 1000
//    1 <= delay < forget <= n