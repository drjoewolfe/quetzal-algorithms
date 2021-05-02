package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CourseScheduleIII {
    class Solution {
        public int scheduleCourse(int[][] courses) {
            if(courses == null || courses.length == 0) {
                return 0;
            }

            int n = courses.length;
            Arrays.sort(courses, (a, b) -> a[1] - b[1]);

            int currentTime = 0;
            int courseCount = 0;
            for(int i = 0; i < n; i++) {
                int[] course = courses[i];
                int duration = course[0];
                int maxEnd = course[1];

                if(currentTime + duration <= maxEnd) {
                    currentTime += duration;
                    courseCount++;
                } else {
                    int maxPreviousCourseIndex = i;
                    for(int j = 0; j < i; j++) {
                        int[] previousCourse = courses[j];
                        int previousDuration = previousCourse[0];

                        if(previousDuration > courses[maxPreviousCourseIndex][0])  {
                            maxPreviousCourseIndex = j;
                        }
                    }

                    if(maxPreviousCourseIndex != i) {
                        currentTime += duration;
                        currentTime -= courses[maxPreviousCourseIndex][0];
                    }

                    courses[maxPreviousCourseIndex][0] = -1;
                }
            }

            return courseCount;
        }
    }

    class Solution_Memoized {
        public int scheduleCourse(int[][] courses) {
            if(courses == null || courses.length == 0) {
                return 0;
            }

            int n = courses.length;

            Map<Memento, Integer> memo = new HashMap<>();
            Arrays.sort(courses, (a, b) -> a[1] - b[1]);
            return scheduleCourses(courses, 0, 0, memo);
        }

        private int scheduleCourses(int[][] courses, int index, int currentTime, Map<Memento, Integer> memo) {
            if(courses.length == index) {
                return 0;
            }

            Memento memento = new Memento(index, currentTime);
            if(memo.containsKey(memento)) {
                return memo.get(memento);
            }

            int[] course = courses[index];
            int duration = course[0];
            int maxEnd = course[1];

            int maxCourses = 0;
            if(currentTime + duration > maxEnd) {
                // Cannot accomodate current course with the current run
                maxCourses = scheduleCourses(courses, index + 1, currentTime, memo);
            } else {
                // 2 choices - take the course, or skip the course
                int countOnTake = 1 + scheduleCourses(courses, index + 1, currentTime + duration, memo);
                int countOnSkip = scheduleCourses(courses, index + 1, currentTime, memo);
                maxCourses = Math.max(countOnTake, countOnSkip);
            }

            memo.put(memento, maxCourses);
            return maxCourses;
        }
    }

    static class Memento {
        int index;
        int time;

        public Memento(int index, int time) {
            this.index = index;
            this.time = time;
        }

        @Override
        public boolean equals(Object other) {
            if(other instanceof Memento) {
                Memento m = (Memento) other;
                return this.index == m.index && this.time == m.time;
            }

            return false;
        }

        @Override
        public int hashCode() {
            int hash = 7;

            hash *= 31;
            hash += index;

            hash *= 31;
            hash += time;

            return hash;
        }
    }

    class Solution_Recursive {
        public int scheduleCourse(int[][] courses) {
            if(courses == null || courses.length == 0) {
                return 0;
            }

            Arrays.sort(courses, (a, b) -> a[1] - b[1]);
            return scheduleCourses(courses, 0, 0);
        }

        private int scheduleCourses(int[][] courses, int index, int currentTime) {
            if(courses.length == index) {
                return 0;
            }

            int[] course = courses[index];
            int duration = course[0];
            int maxEnd = course[1];

            if(currentTime + duration > maxEnd) {
                // Cannot accomodate current course with the current run
                return scheduleCourses(courses, index + 1, currentTime);
            }

            // 2 choices - take the course, or skip the course
            int countOnTake = 1 + scheduleCourses(courses, index + 1, currentTime + duration);
            int countOnSkip = scheduleCourses(courses, index + 1, currentTime);

            return Math.max(countOnTake, countOnSkip);
        }
    }

// [[100,200],[200,1300],[1000,1250],[2000,3200]]
// [[7,16],[2,3],[3,12],[3,14],[10,19],[10,16],[6,8],[6,11],[3,13],[6,16]]
}

//    630. Course Schedule III
//    Hard
//    There are n different online courses numbered from 1 to n. You are given an array courses where courses[i] = [durationi, lastDayi] indicate that the ith course should be taken continuously for durationi days and must be finished before or on lastDayi.
//
//    You will start on the 1st day and you cannot take two or more courses simultaneously.
//
//    Return the maximum number of courses that you can take.
//
//
//
//    Example 1:
//
//    Input: courses = [[100,200],[200,1300],[1000,1250],[2000,3200]]
//    Output: 3
//    Explanation:
//    There are totally 4 courses, but you can take 3 courses at most:
//    First, take the 1st course, it costs 100 days so you will finish it on the 100th day, and ready to take the next course on the 101st day.
//    Second, take the 3rd course, it costs 1000 days so you will finish it on the 1100th day, and ready to take the next course on the 1101st day.
//    Third, take the 2nd course, it costs 200 days so you will finish it on the 1300th day.
//    The 4th course cannot be taken now, since you will finish it on the 3300th day, which exceeds the closed date.
//    Example 2:
//
//    Input: courses = [[1,2]]
//    Output: 1
//    Example 3:
//
//    Input: courses = [[3,2],[4,3]]
//    Output: 0
//
//
//    Constraints:
//
//    1 <= courses.length <= 104
//    1 <= durationi, lastDayi <= 104
