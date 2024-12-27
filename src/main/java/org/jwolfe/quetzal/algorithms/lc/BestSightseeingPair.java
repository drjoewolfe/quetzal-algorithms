package org.jwolfe.quetzal.algorithms.lc;

public class BestSightseeingPair {
    class Solution {
        public int maxScoreSightseeingPair(int[] A) {
            if(A == null || A.length < 2) {
                return 0;
            }

            int n = A.length;
            int maxScore = 0;

            int maxValueSoFar = A[0];
            int maxValueIndex = 0;

            for(int i = 1; i < n; i++) {
                int score = maxValueSoFar + maxValueIndex + A[i] - i;
                maxScore = Math.max(maxScore, score);

                if(A[i] + i > maxValueSoFar + maxValueIndex) {
                    maxValueSoFar = A[i];
                    maxValueIndex = i;
                }
            }

            return maxScore;
        }
    }

    class Solution_Correct_1 {
        public int maxScoreSightseeingPair(int[] A) {
            if(A == null || A.length < 2) {
                return 0;
            }

            int n = A.length;
            int maxScore = 0;

            int maxValueSoFar = A[0];
            int maxValueIndex = 0;
            for(int i = 1; i < n; i++) {
                int score = maxValueSoFar + A[i] + maxValueIndex - i;
                maxScore = Math.max(score, maxScore);

                // System.out.println(maxValueSoFar + ", " + A[i] + " : " + maxValueIndex + ", " + i + " = " + score + " , " + maxScore);
                if(maxValueSoFar + maxValueIndex <= A[i] + i) {
                    maxValueSoFar = A[i];
                    maxValueIndex = i;
                }
            }

            return maxScore;
        }
    }

    class Solution_Brute {
        public int maxScoreSightseeingPair(int[] A) {
            if(A == null || A.length < 2) {
                return 0;
            }

            int n = A.length;
            int maxScore = 0;
            for(int i = 0; i < n - 1; i++) {
                int a = A[i];
                for(int j = i + 1; j < n; j++) {
                    int b = A[j];
                    int score = a + b + i - j;

                    maxScore = Math.max(score, maxScore);
                }
            }

            return maxScore;
        }
    }
}

//    1014. Best Sightseeing Pair
//    Medium
//    You are given an integer array values where values[i] represents the value of the ith sightseeing spot. Two sightseeing spots i and j have a distance j - i between them.
//
//    The score of a pair (i < j) of sightseeing spots is values[i] + values[j] + i - j: the sum of the values of the sightseeing spots, minus the distance between them.
//
//    Return the maximum score of a pair of sightseeing spots.
//
//
//
//    Example 1:
//
//    Input: values = [8,1,5,2,6]
//    Output: 11
//    Explanation: i = 0, j = 2, values[i] + values[j] + i - j = 8 + 5 + 0 - 2 = 11
//    Example 2:
//
//    Input: values = [1,2]
//    Output: 2
//
//
//    Constraints:
//
//    2 <= values.length <= 5 * 104
//    1 <= values[i] <= 1000