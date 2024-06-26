package org.jwolfe.quetzal.algorithms.lc;

import java.util.PriorityQueue;

public class IPO {
    class Solution {
        public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
            if (k < 1 || profits == null || capital == null || profits.length != capital.length) {
                return w;
            }

            int maxCapital = w;
            int n = profits.length;

            PriorityQueue<int[]> projectsByCapital = new PriorityQueue<>((a, b) -> a[1] - b[1]);
            for (int i = 0; i < n; i++) {
                projectsByCapital.offer(new int[]{profits[i], capital[i]});
            }

            PriorityQueue<int[]> projectsByProfit = new PriorityQueue<>((a, b) -> b[0] - a[0]);
            for (int i = 1; i <= k; i++) {
                while (!projectsByCapital.isEmpty()
                        && projectsByCapital.peek()[1] <= maxCapital) {
                    projectsByProfit.offer(projectsByCapital.poll());
                }

                if (projectsByProfit.size() == 0) {
                    break;
                }

                maxCapital += projectsByProfit.poll()[0];
            }

            return maxCapital;
        }
    }

    class Solution_Correct_1 {
        public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
            if (k < 1) {
                return w;
            }

            if (profits == null || capital == null || profits.length != capital.length) {
                return w;
            }

            int n = profits.length;

            PriorityQueue<Project> projectsByCapital = new PriorityQueue<>((a, b) -> a.capital - b.capital);
            for (int i = 0; i < n; i++) {
                projectsByCapital.offer(new Project(profits[i], capital[i]));
            }

            PriorityQueue<Project> availableProjects = new PriorityQueue<>((a, b) -> b.profit - a.profit);

            while (k > 0) {

                while (!projectsByCapital.isEmpty()
                        && projectsByCapital.peek().capital <= w) {
                    availableProjects.offer(projectsByCapital.poll());
                }

                if (availableProjects.size() == 0) {
                    break;
                }

                Project selectedProject = availableProjects.poll();
                w += selectedProject.profit;

                k--;
            }

            return w;
        }

        class Project {
            int profit;
            int capital;
            int delta;

            public Project(int profit, int capital) {
                this.profit = profit;
                this.capital = capital;
                this.delta = profit - capital;
            }

            public String toString() {
                return "(" + profit + ", " + capital + ")";
            }
        }
    }

// 2
// 0
// [1,2,3]
// [0,1,1]

// 11
// 11
// [1,2,3]
// [11,12,13]
}

//    502. IPO
//    Hard
//    Suppose LeetCode will start its IPO soon. In order to sell a good price of its shares to Venture Capital, LeetCode would like to work on some projects to increase its capital before the IPO. Since it has limited resources, it can only finish at most k distinct projects before the IPO. Help LeetCode design the best way to maximize its total capital after finishing at most k distinct projects.
//
//    You are given n projects where the ith project has a pure profit profits[i] and a minimum capital of capital[i] is needed to start it.
//
//    Initially, you have w capital. When you finish a project, you will obtain its pure profit and the profit will be added to your total capital.
//
//    Pick a list of at most k distinct projects from given projects to maximize your final capital, and return the final maximized capital.
//
//    The answer is guaranteed to fit in a 32-bit signed integer.
//
//
//
//    Example 1:
//
//    Input: k = 2, w = 0, profits = [1,2,3], capital = [0,1,1]
//    Output: 4
//    Explanation: Since your initial capital is 0, you can only start the project indexed 0.
//    After finishing it you will obtain profit 1 and your capital becomes 1.
//    With capital 1, you can either start the project indexed 1 or the project indexed 2.
//    Since you can choose at most 2 projects, you need to finish the project indexed 2 to get the maximum capital.
//    Therefore, output the final maximized capital, which is 0 + 1 + 3 = 4.
//    Example 2:
//
//    Input: k = 3, w = 0, profits = [1,2,3], capital = [0,1,2]
//    Output: 6
//
//
//    Constraints:
//
//    1 <= k <= 105
//    0 <= w <= 109
//    n == profits.length
//    n == capital.length
//    1 <= n <= 105
//    0 <= profits[i] <= 104
//    0 <= capital[i] <= 109