package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class MaximumProfitFromTradingStocksWithDiscounts {
    class Solution {

        private int[][][] memo;   // memo[u][discounted][b]
        private boolean[][] seen;
        private int budget;

        public int maxProfit(int n, int[] present, int[] future, int[][] hierarchy, int budget) {
            this.budget = budget;

            Map<Integer, Set<Integer>> graph = new HashMap<>();
            for (int u = 1; u <= n; u++) {
                graph.put(u, new HashSet<>());
            }

            for (int[] pair : hierarchy) {
                graph.get(pair[0]).add(pair[1]);
            }

            memo = new int[n + 1][2][budget + 1];
            seen = new boolean[n + 1][2];

            int[] profitsForBudget = dfs(graph, 1, false, present, future);

            int ans = 0;
            for (int p : profitsForBudget) ans = Math.max(ans, p);
            return ans;
        }

        private int[] dfs(
                Map<Integer, Set<Integer>> graph,
                int u,
                boolean discounted,
                int[] present,
                int[] future
        ) {
            int d = discounted ? 1 : 0;

            // ✅ Memo hit
            if (seen[u][d]) {
                return memo[u][d].clone();
            }

            // ---------- Option 1: u does NOT buy ----------
            int[] option1 = new int[budget + 1];

            for (int v : graph.get(u)) {
                int[] child = dfs(graph, v, false, present, future);
                int[] tmp = new int[budget + 1];

                for (int i = 0; i <= budget; i++) {
                    for (int k = 0; k <= i; k++) {
                        tmp[i] = Math.max(tmp[i], option1[k] + child[i - k]);
                    }
                }
                option1 = tmp;
            }

            // ---------- Option 2: u buys ----------
            int cost = discounted ? present[u - 1] / 2 : present[u - 1];
            int profit = future[u - 1] - cost;

            int[] option2 = new int[budget + 1];
            Arrays.fill(option2, Integer.MIN_VALUE);

            if (cost <= budget) {
                int[] acc = new int[budget + 1];

                for (int v : graph.get(u)) {
                    int[] child = dfs(graph, v, true, present, future);
                    int[] tmp = new int[budget + 1];

                    for (int i = 0; i <= budget; i++) {
                        for (int k = 0; k <= i; k++) {
                            tmp[i] = Math.max(tmp[i], acc[k] + child[i - k]);
                        }
                    }
                    acc = tmp;
                }

                for (int b = cost; b <= budget; b++) {
                    option2[b] = profit + acc[b - cost];
                }
            }

            // ---------- Combine ----------
            int[] res = new int[budget + 1];
            for (int b = 0; b <= budget; b++) {
                res[b] = Math.max(option1[b], Math.max(option2[b], 0));
            }

            // ✅ Store memo
            seen[u][d] = true;
            memo[u][d] = res.clone();

            return res;
        }
    }

    class Solution_TLE {
        public int maxProfit(int n, int[] present, int[] future, int[][] hierarchy, int budget) {
            Map<Integer, Set<Integer>> graph = new HashMap<>();
            for (int u = 1; u <= n; u++) {
                graph.put(u, new HashSet<>());
            }

            for (int[] pair : hierarchy) {
                int u = pair[0];
                int v = pair[1];

                graph.get(u).add(v);
            }

            int[] profitsForBudget = dfs(graph, 1, false, present, future, budget);
            int maxProfitForBudget = 0;
            for (int profit : profitsForBudget) {
                maxProfitForBudget = Math.max(maxProfitForBudget, profit);
            }

            return maxProfitForBudget;
        }

        private int[] dfs(Map<Integer, Set<Integer>> graph, int u, boolean discounted, int[] present, int[] future, int budget) {
            // Option 1: "u" doesn't buy stock
            int[] option1ProfitForBudget = new int[budget + 1];
            for (int v : graph.get(u)) {
                int[] childProfitForBudget = dfs(graph, v, false, present, future, budget);

                int[] tempProfitForBudget = new int[budget + 1];
                for (int i = 0; i <= budget; i++) {
                    for (int allocatedBudget = 0; allocatedBudget <= i; allocatedBudget++) {
                        tempProfitForBudget[i] = Math.max(tempProfitForBudget[i], option1ProfitForBudget[allocatedBudget] + childProfitForBudget[i - allocatedBudget]);
                    }
                }

                option1ProfitForBudget = tempProfitForBudget;
            }

            // Option 2: "u" buys stock
            int cost = discounted ? present[u - 1] / 2 : present[u - 1];
            int profit = future[u - 1] - cost;

            int[] option2ProfitForBudget = new int[budget + 1];
            Arrays.fill(option2ProfitForBudget, Integer.MIN_VALUE);

            if (cost >= 0 && cost <= budget) {
                int[] option2TempProfitForBudget = new int[budget + 1];

                for (int v : graph.get(u)) {
                    int[] childProfitForBudget = dfs(graph, v, true, present, future, budget);
                    int[] tempProfitForBudget = new int[budget + 1];
                    for (int i = 0; i <= budget; i++) {
                        for (int allocatedBudget = 0; allocatedBudget <= i; allocatedBudget++) {
                            tempProfitForBudget[i] = Math.max(tempProfitForBudget[i], option2TempProfitForBudget[allocatedBudget] + childProfitForBudget[i - allocatedBudget]);
                        }
                    }

                    option2TempProfitForBudget = tempProfitForBudget;
                }

                for (int b = cost; b <= budget; b++) {
                    option2ProfitForBudget[b] = profit + option2TempProfitForBudget[b - cost];
                }
            }

            int[] maxProfitForBudget = new int[budget + 1];
            for (int b = 0; b <= budget; b++) {
                maxProfitForBudget[b] = Math.max(option1ProfitForBudget[b], Math.max(option2ProfitForBudget[b], 0));
            }

            return maxProfitForBudget;
        }
    }
}

//    3562. Maximum Profit from Trading Stocks with Discounts
//    Hard
//    You are given an integer n, representing the number of employees in a company. Each employee is assigned a unique ID from 1 to n, and employee 1 is the CEO. You are given two 1-based integer arrays, present and future, each of length n, where:
//
//    present[i] represents the current price at which the ith employee can buy a stock today.
//    future[i] represents the expected price at which the ith employee can sell the stock tomorrow.
//    The company's hierarchy is represented by a 2D integer array hierarchy, where hierarchy[i] = [ui, vi] means that employee ui is the direct boss of employee vi.
//
//    Additionally, you have an integer budget representing the total funds available for investment.
//
//    However, the company has a discount policy: if an employee's direct boss purchases their own stock, then the employee can buy their stock at half the original price (floor(present[v] / 2)).
//
//    Return the maximum profit that can be achieved without exceeding the given budget.
//
//    Note:
//
//    You may buy each stock at most once.
//    You cannot use any profit earned from future stock prices to fund additional investments and must buy only from budget.
//
//
//    Example 1:
//
//    Input: n = 2, present = [1,2], future = [4,3], hierarchy = [[1,2]], budget = 3
//
//    Output: 5
//
//    Explanation:
//
//
//
//    Employee 1 buys the stock at price 1 and earns a profit of 4 - 1 = 3.
//    Since Employee 1 is the direct boss of Employee 2, Employee 2 gets a discounted price of floor(2 / 2) = 1.
//    Employee 2 buys the stock at price 1 and earns a profit of 3 - 1 = 2.
//    The total buying cost is 1 + 1 = 2 <= budget. Thus, the maximum total profit achieved is 3 + 2 = 5.
//    Example 2:
//
//    Input: n = 2, present = [3,4], future = [5,8], hierarchy = [[1,2]], budget = 4
//
//    Output: 4
//
//    Explanation:
//
//
//
//    Employee 2 buys the stock at price 4 and earns a profit of 8 - 4 = 4.
//    Since both employees cannot buy together, the maximum profit is 4.
//    Example 3:
//
//    Input: n = 3, present = [4,6,8], future = [7,9,11], hierarchy = [[1,2],[1,3]], budget = 10
//
//    Output: 10
//
//    Explanation:
//
//
//
//    Employee 1 buys the stock at price 4 and earns a profit of 7 - 4 = 3.
//    Employee 3 would get a discounted price of floor(8 / 2) = 4 and earns a profit of 11 - 4 = 7.
//    Employee 1 and Employee 3 buy their stocks at a total cost of 4 + 4 = 8 <= budget. Thus, the maximum total profit achieved is 3 + 7 = 10.
//    Example 4:
//
//    Input: n = 3, present = [5,2,3], future = [8,5,6], hierarchy = [[1,2],[2,3]], budget = 7
//
//    Output: 12
//
//    Explanation:
//
//
//
//    Employee 1 buys the stock at price 5 and earns a profit of 8 - 5 = 3.
//    Employee 2 would get a discounted price of floor(2 / 2) = 1 and earns a profit of 5 - 1 = 4.
//    Employee 3 would get a discounted price of floor(3 / 2) = 1 and earns a profit of 6 - 1 = 5.
//    The total cost becomes 5 + 1 + 1 = 7 <= budget. Thus, the maximum total profit achieved is 3 + 4 + 5 = 12.
//
//
//    Constraints:
//
//    1 <= n <= 160
//    present.length, future.length == n
//    1 <= present[i], future[i] <= 50
//    hierarchy.length == n - 1
//    hierarchy[i] == [ui, vi]
//    1 <= ui, vi <= n
//    ui != vi
//    1 <= budget <= 160
//    There are no duplicate edges.
//    Employee 1 is the direct or indirect boss of every employee.
//    The input graph hierarchy is guaranteed to have no cycles.