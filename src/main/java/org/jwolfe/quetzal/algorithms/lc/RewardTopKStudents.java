package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class RewardTopKStudents {
    class Solution {
        public List<Integer> topStudents(String[] positive_feedback, String[] negative_feedback, String[] report, int[] student_id, int k) {
            if(report == null || report.length == 0 || student_id == null || student_id.length != report.length || k < 1) {
                return null;
            }

            Set<String> positive = new HashSet<>();
            Set<String> negative = new HashSet<>();

            for(String word : positive_feedback) {
                positive.add(word);
            }

            for(String word : negative_feedback) {
                negative.add(word);
            }

            int n = report.length;
            int[] scores = new int[n];

            for(int i = 0; i < n; i++) {
                int id = student_id[i];
                int score = 0;
                String[] words = report[i].split(" ");

                for(String word : words) {
                    if(positive.contains(word)) {
                        score += 3;
                    } else if(negative.contains(word)) {
                        score -= 1;
                    }
                }

                scores[i] = score;
            }

            PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> {
                if(a[1] != b[1]) {
                    return b[1] - a[1];
                }

                return a[0] - b[0];
            });

            for(int i = 0; i < n; i++) {
                int[] pair = new int[] {student_id[i], scores[i]};
                maxHeap.offer(pair);
            }

            List<Integer> results = new ArrayList<>();
            for(int i = 0; i < k; i++) {
                results.add(maxHeap.poll()[0]);
            }

            return results;
        }
    }

// ["smart","brilliant","studious"]
// ["not"]
// ["this student is studious","the student is smart"]
// [1,2]
// 2
}

//    2512. Reward Top K Students
//    Medium
//    You are given two string arrays positive_feedback and negative_feedback, containing the words denoting positive and negative feedback, respectively. Note that no word is both positive and negative.
//
//    Initially every student has 0 points. Each positive word in a feedback report increases the points of a student by 3, whereas each negative word decreases the points by 1.
//
//    You are given n feedback reports, represented by a 0-indexed string array report and a 0-indexed integer array student_id, where student_id[i] represents the ID of the student who has received the feedback report report[i]. The ID of each student is unique.
//
//    Given an integer k, return the top k students after ranking them in non-increasing order by their points. In case more than one student has the same points, the one with the lower ID ranks higher.
//
//
//
//    Example 1:
//
//    Input: positive_feedback = ["smart","brilliant","studious"], negative_feedback = ["not"], report = ["this student is studious","the student is smart"], student_id = [1,2], k = 2
//    Output: [1,2]
//    Explanation:
//    Both the students have 1 positive feedback and 3 points but since student 1 has a lower ID he ranks higher.
//    Example 2:
//
//    Input: positive_feedback = ["smart","brilliant","studious"], negative_feedback = ["not"], report = ["this student is not studious","the student is smart"], student_id = [1,2], k = 2
//    Output: [2,1]
//    Explanation:
//    - The student with ID 1 has 1 positive feedback and 1 negative feedback, so he has 3-1=2 points.
//    - The student with ID 2 has 1 positive feedback, so he has 3 points.
//    Since student 2 has more points, [2,1] is returned.
//
//
//    Constraints:
//
//    1 <= positive_feedback.length, negative_feedback.length <= 104
//    1 <= positive_feedback[i].length, negative_feedback[j].length <= 100
//    Both positive_feedback[i] and negative_feedback[j] consists of lowercase English letters.
//    No word is present in both positive_feedback and negative_feedback.
//    n == report.length == student_id.length
//    1 <= n <= 104
//    report[i] consists of lowercase English letters and spaces ' '.
//    There is a single space between consecutive words of report[i].
//    1 <= report[i].length <= 100
//    1 <= student_id[i] <= 109
//    All the values of student_id[i] are unique.
//    1 <= k <= n