package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class MinimumDifficultyOfAJobSchedule {
    class Solution {
        public int minDifficulty(int[] jobDifficulty, int d) {
            if(jobDifficulty == null || d < 0 || jobDifficulty.length < d) {
                return -1;
            }

            int[][] memo = new int[jobDifficulty.length][d + 1];
            for(int[] row : memo) {
                Arrays.fill(row, -1);
            }

            return minDifficulty(jobDifficulty, 0, d, memo);
        }

        private int minDifficulty(int[] jobDifficulty, int index, int daysLeft, int[][] memo) {
            if(daysLeft == 0) {
                if(index != jobDifficulty.length) {
                    return Integer.MAX_VALUE;
                }

                return 0;
            }

            if(memo[index][daysLeft] != -1) {
                return memo[index][daysLeft];
            }

            int minScheduleDifficulty = Integer.MAX_VALUE;
            int dayDifficulty = Integer.MIN_VALUE;
            for(int i = index; i <= jobDifficulty.length - daysLeft; i++) {
                dayDifficulty = Math.max(dayDifficulty, jobDifficulty[i]);
                int subsequentScheduleDifficulty = minDifficulty(jobDifficulty, i + 1, daysLeft - 1, memo);

                if(subsequentScheduleDifficulty != Integer.MAX_VALUE) {
                    int scheduleDifficulty = dayDifficulty + subsequentScheduleDifficulty;
                    minScheduleDifficulty = Math.min(minScheduleDifficulty, scheduleDifficulty);
                }
            }

            memo[index][daysLeft] = minScheduleDifficulty;
            return minScheduleDifficulty;
        }
    }

    class Solution_Recursive_TLE {
        public int minDifficulty(int[] jobDifficulty, int d) {
            if(jobDifficulty == null || d < 0 || jobDifficulty.length < d) {
                return -1;
            }

            return minDifficulty(jobDifficulty, 0, d);
        }

        private int minDifficulty(int[] jobDifficulty, int index, int daysLeft) {
            if(daysLeft == 0) {
                if(index != jobDifficulty.length) {
                    return Integer.MAX_VALUE;
                }

                return 0;
            }

            int minScheduleDifficulty = Integer.MAX_VALUE;
            int dayDifficulty = Integer.MIN_VALUE;
            for(int i = index; i <= jobDifficulty.length - daysLeft; i++) {
                dayDifficulty = Math.max(dayDifficulty, jobDifficulty[i]);
                int subsequentScheduleDifficulty = minDifficulty(jobDifficulty, i + 1, daysLeft - 1);

                if(subsequentScheduleDifficulty != Integer.MAX_VALUE) {
                    int scheduleDifficulty = dayDifficulty + subsequentScheduleDifficulty;
                    minScheduleDifficulty = Math.min(minScheduleDifficulty, scheduleDifficulty);
                }
            }

            return minScheduleDifficulty;
        }
    }

    class Solution_Incorrect {
        public int minDifficulty(int[] jobDifficulty, int d) {
            if(jobDifficulty == null || d < 0 || jobDifficulty.length < d) {
                return -1;
            }

            Arrays.sort(jobDifficulty);
            int n = jobDifficulty.length;
            int scheduleDifficulty = jobDifficulty[n - 1];
            for(int i = 0; i < d - 1; i++) {
                scheduleDifficulty += jobDifficulty[i];
            }

            return scheduleDifficulty;
        }
    }

// [6,5,4,3,2,1]
// 2

// [380,302,102,681,863,676,243,671,651,612,162,561,394,856,601,30,6,257,921,405,716,126,158,476,889,699,668,930,139,164,641,801,480,756,797,915,275,709,161,358,461,938,914,557,121,964,315]
// 10
}

//    1335. Minimum Difficulty of a Job Schedule
//    Hard
//    You want to schedule a list of jobs in d days. Jobs are dependent (i.e To work on the ith job, you have to finish all the jobs j where 0 <= j < i).
//
//    You have to finish at least one task every day. The difficulty of a job schedule is the sum of difficulties of each day of the d days. The difficulty of a day is the maximum difficulty of a job done on that day.
//
//    You are given an integer array jobDifficulty and an integer d. The difficulty of the ith job is jobDifficulty[i].
//
//    Return the minimum difficulty of a job schedule. If you cannot find a schedule for the jobs return -1.
//
//
//
//    Example 1:
//
//
//    Input: jobDifficulty = [6,5,4,3,2,1], d = 2
//    Output: 7
//    Explanation: First day you can finish the first 5 jobs, total difficulty = 6.
//    Second day you can finish the last job, total difficulty = 1.
//    The difficulty of the schedule = 6 + 1 = 7
//    Example 2:
//
//    Input: jobDifficulty = [9,9,9], d = 4
//    Output: -1
//    Explanation: If you finish a job per day you will still have a free day. you cannot find a schedule for the given jobs.
//    Example 3:
//
//    Input: jobDifficulty = [1,1,1], d = 3
//    Output: 3
//    Explanation: The schedule is one job per day. total difficulty will be 3.
//
//
//    Constraints:
//
//    1 <= jobDifficulty.length <= 300
//    0 <= jobDifficulty[i] <= 1000
//    1 <= d <= 10
