package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class PairsOfSongsWithTotalDurationsDivisibleBy60 {
    class Solution {
        public int numPairsDivisibleBy60(int[] time) {
            if(time == null || time.length == 0) {
                return 0;
            }

            int[] normalized = new int[60];
            for(int i = 0; i < time.length; i++) {
                int a = time[i];
                int na = a % 60;

                normalized[na]++;
            }

            int count = 0;
            for(int i = 0; i <= 30; i++) {
                int c1 = normalized[i];
                if(i == 0 || i == 30) {
                    count += ((c1 - 1) * (c1) / 2);
                } else {
                    int c2 = normalized[60 - i];
                    count += (c1 * c2);
                }
            }

            return count;
        }

        private void print(int[] arr) {
            for(int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }

    class Solution_Correct_1 {
        public int numPairsDivisibleBy60(int[] time) {
            if(time == null || time.length < 2) {
                return 0;
            }

            int count = 0;
            int n = time.length;
            int[] remainders = new int[60];

            for(int t : time) {
                int mod = t % 60;
                if(mod == 0) {
                    count += remainders[0];
                } else {
                    count += remainders[60-mod];
                }

                remainders[mod]++;
            }

            return count;
        }
    }

    class Solution_Incorrect {
        public int numPairsDivisibleBy60(int[] time) {
            if(time == null || time.length < 2) {
                return 0;
            }

            int n = time.length;
            Map<Integer, Integer> map = new HashMap<>();

            for(int i = 0; i < time.length; i++) {
                int a = time[i] % 60;
                map.put(a, map.getOrDefault(a, 0) + 1);
            }

            int count = 0;
            for(var n1 : map.keySet()) {
                int n1Count = map.get(n1);

                int n2 = 60 - n1;
                if(n1 == n2 || n1 == 0 || n1 == 60) {
                    count += selectionsOf2(n1Count);
                }
                else if(map.containsKey(n2)) {
                    int n2Count = map.get(n2);
                    count += (n1Count * n2Count);

                    map.put(n2, 0);
                }

                map.put(n1, 0);
            }

            return count;
        }

        private int selectionsOf2(int n) {
            return factorial(n) / (factorial(n - 2) * 2);
        }

        private int factorial(int n) {
            int result = 1;
            while(n > 0) {
                result *= (n--);
            }

            return result;
        }
    }

    class Solution_Brute {
        public int numPairsDivisibleBy60(int[] time) {
            if(time == null || time.length < 2) {
                return 0;
            }

            int count = 0;
            int n = time.length;
            for(int i = 0; i < n - 1; i++) {
                for(int j = i + 1; j < n; j++) {
                    if((time[i] + time[j]) % 60 == 0) {
                        count++;
                    }
                }
            }

            return count;
        }
    }

// [30,20,150,100,40]
// [60,60,60]
// [30,20,150,100,40]
}

//    1010. Pairs of Songs With Total Durations Divisible by 60
//    Medium
//    You are given a list of songs where the ith song has a duration of time[i] seconds.
//
//    Return the number of pairs of songs for which their total duration in seconds is divisible by 60. Formally, we want the number of indices i, j such that i < j with (time[i] + time[j]) % 60 == 0.
//
//
//
//    Example 1:
//
//    Input: time = [30,20,150,100,40]
//    Output: 3
//    Explanation: Three pairs have a total duration divisible by 60:
//    (time[0] = 30, time[2] = 150): total duration 180
//    (time[1] = 20, time[3] = 100): total duration 120
//    (time[1] = 20, time[4] = 40): total duration 60
//    Example 2:
//
//    Input: time = [60,60,60]
//    Output: 3
//    Explanation: All three pairs have a total duration of 120, which is divisible by 60.
//
//
//    Constraints:
//
//    1 <= time.length <= 6 * 104
//    1 <= time[i] <= 500