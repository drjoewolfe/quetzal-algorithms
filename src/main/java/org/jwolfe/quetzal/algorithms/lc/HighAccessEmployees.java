package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class HighAccessEmployees {
    class Solution {
        public List<String> findHighAccessEmployees(List<List<String>> access_times) {
            List<String> highAccessEmployees = new ArrayList<>();
            if(access_times == null || access_times.size() < 3) {
                return highAccessEmployees;
            }

            Map<String, List<Integer>> map = new HashMap<>();
            for(var accessInfo : access_times) {
                String name = accessInfo.get(0);
                String accessTimeString = accessInfo.get(1);
                int accessTime = getAccessTime(accessTimeString);

                if(!map.containsKey(name)) {
                    map.put(name, new ArrayList<>());
                }

                map.get(name).add(accessTime);
            }

            for(var entry : map.entrySet()) {
                String name = entry.getKey();
                var accessTimes = entry.getValue();

                Collections.sort(accessTimes);
                for(int i = 0; i < accessTimes.size() - 2; i++) {
                    int accessTime = accessTimes.get(i);
                    int windowEnd = accessTime + 59;

                    if(accessTimes.get(i + 1) <= windowEnd
                            && accessTimes.get(i + 2) <= windowEnd) {
                        highAccessEmployees.add(name);
                        break;
                    }
                }
            }

            return highAccessEmployees;
        }

        private int getAccessTime(String timeString) {
            char h1 = timeString.charAt(0);
            char h2 = timeString.charAt(1);
            char m1 = timeString.charAt(2);
            char m2 = timeString.charAt(3);

            int time = ((h1 * 10) + h2) * 60 + ((m1 * 10) + m2);
            return time;
        }

        private void print(List<Integer> list) {
            for(int a : list) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }

// [["a","0549"],["b","0457"],["a","0532"],["a","0621"],["b","0540"]]

// [["cd","1025"],["ab","1025"],["cd","1046"],["cd","1055"],["ab","1124"],["ab","1120"]]

// [["dz","0719"],["dz","0726"],["dz","0716"],["dz","0716"]]
}

//    2933. High-Access Employees
//    Medium
//    You are given a 2D 0-indexed array of strings, access_times, with size n. For each i where 0 <= i <= n - 1, access_times[i][0] represents the name of an employee, and access_times[i][1] represents the access time of that employee. All entries in access_times are within the same day.
//
//    The access time is represented as four digits using a 24-hour time format, for example, "0800" or "2250".
//
//    An employee is said to be high-access if he has accessed the system three or more times within a one-hour period.
//
//    Times with exactly one hour of difference are not considered part of the same one-hour period. For example, "0815" and "0915" are not part of the same one-hour period.
//
//    Access times at the start and end of the day are not counted within the same one-hour period. For example, "0005" and "2350" are not part of the same one-hour period.
//
//    Return a list that contains the names of high-access employees with any order you want.
//
//
//
//    Example 1:
//
//    Input: access_times = [["a","0549"],["b","0457"],["a","0532"],["a","0621"],["b","0540"]]
//    Output: ["a"]
//    Explanation: "a" has three access times in the one-hour period of [05:32, 06:31] which are 05:32, 05:49, and 06:21.
//    But "b" does not have more than two access times at all.
//    So the answer is ["a"].
//    Example 2:
//
//    Input: access_times = [["d","0002"],["c","0808"],["c","0829"],["e","0215"],["d","1508"],["d","1444"],["d","1410"],["c","0809"]]
//    Output: ["c","d"]
//    Explanation: "c" has three access times in the one-hour period of [08:08, 09:07] which are 08:08, 08:09, and 08:29.
//    "d" has also three access times in the one-hour period of [14:10, 15:09] which are 14:10, 14:44, and 15:08.
//    However, "e" has just one access time, so it can not be in the answer and the final answer is ["c","d"].
//    Example 3:
//
//    Input: access_times = [["cd","1025"],["ab","1025"],["cd","1046"],["cd","1055"],["ab","1124"],["ab","1120"]]
//    Output: ["ab","cd"]
//    Explanation: "ab" has three access times in the one-hour period of [10:25, 11:24] which are 10:25, 11:20, and 11:24.
//    "cd" has also three access times in the one-hour period of [10:25, 11:24] which are 10:25, 10:46, and 10:55.
//    So the answer is ["ab","cd"].
//
//
//    Constraints:
//
//    1 <= access_times.length <= 100
//    access_times[i].length == 2
//    1 <= access_times[i][0].length <= 10
//    access_times[i][0] consists only of English small letters.
//    access_times[i][1].length == 4
//    access_times[i][1] is in 24-hour time format.
//    access_times[i][1] consists only of '0' to '9'.