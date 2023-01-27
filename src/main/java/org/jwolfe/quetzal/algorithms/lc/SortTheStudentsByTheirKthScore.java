package org.jwolfe.quetzal.algorithms.lc;

import java.util.PriorityQueue;

public class SortTheStudentsByTheirKthScore {
    class Solution {
        public int[][] sortTheStudents(int[][] score, int k) {
            if(score == null || score.length == 0 || k < 0 || k >= score[0].length) {
                return score;
            }

            int m = score.length;
            int n = score[0].length;

            PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[1] - a[1]);
            for(int i = 0; i < m; i++) {
                maxHeap.offer(new int[] {i, score[i][k]});
            }

            int[][] results = new int[m][n];
            int row = 0;
            while(!maxHeap.isEmpty()) {
                int[] element = maxHeap.poll();

                int i = element[0];
                for(int j = 0; j < n; j++) {
                    results[row][j] = score[i][j];
                }

                row++;
            }

            return results;
        }
    }

// [[10,6,9,1],[7,5,11,2],[4,8,3,15]]
// 2

// [[64766,11978,20502,21365,79611,36797,58431,89540,59373,25955],[51305,66104,88468,5333,17233,32521,14087,42738,46669,65662],[93306,92793,54009,9715,24354,24895,20069,93332,73836,64359],[23358,84599,4675,20979,76889,34630,64098,23468,71448,17848]]
// 5
}

//    2545. Sort the Students by Their Kth Score
//    Medium
//    There is a class with m students and n exams. You are given a 0-indexed m x n integer matrix score, where each row represents one student and score[i][j] denotes the score the ith student got in the jth exam. The matrix score contains distinct integers only.
//
//    You are also given an integer k. Sort the students (i.e., the rows of the matrix) by their scores in the kth (0-indexed) exam from the highest to the lowest.
//
//    Return the matrix after sorting it.
//
//
//
//    Example 1:
//
//
//    Input: score = [[10,6,9,1],[7,5,11,2],[4,8,3,15]], k = 2
//    Output: [[7,5,11,2],[10,6,9,1],[4,8,3,15]]
//    Explanation: In the above diagram, S denotes the student, while E denotes the exam.
//    - The student with index 1 scored 11 in exam 2, which is the highest score, so they got first place.
//    - The student with index 0 scored 9 in exam 2, which is the second highest score, so they got second place.
//    - The student with index 2 scored 3 in exam 2, which is the lowest score, so they got third place.
//    Example 2:
//
//
//    Input: score = [[3,4],[5,6]], k = 0
//    Output: [[5,6],[3,4]]
//    Explanation: In the above diagram, S denotes the student, while E denotes the exam.
//    - The student with index 1 scored 5 in exam 0, which is the highest score, so they got first place.
//    - The student with index 0 scored 3 in exam 0, which is the lowest score, so they got second place.
//
//
//    Constraints:
//
//    m == score.length
//    n == score[i].length
//    1 <= m, n <= 250
//    1 <= score[i][j] <= 105
//    score consists of distinct integers.
//    0 <= k < n