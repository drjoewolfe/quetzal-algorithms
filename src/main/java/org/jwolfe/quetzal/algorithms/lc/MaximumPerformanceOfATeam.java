package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class MaximumPerformanceOfATeam {
    class Solution {
        private int MOD = 1_000_000_007;
        public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
            if(n == 0 || k == 0 || k > n || speed == null || efficiency == null || speed.length != n || efficiency.length != n) {
                return 0;
            }

            List<Player> players = new ArrayList<>();
            for(int i = 0; i < n; i++) {
                Player player = new Player(i, speed[i], efficiency[i]);
                players.add(player);
            }

            Collections.sort(players, (p1, p2) -> p2.efficiency - p1.efficiency);

            long maxTeamPerformance = 0;
            long currentSpeedSum = 0;
            long currentMinEfficiency = 0;

            PriorityQueue<Player> minHeap = new PriorityQueue<>((p1, p2) -> p1.speed - p2.speed);

            for(int i = 0; i < n; i++) {
                Player player = players.get(i);
                currentSpeedSum += player.speed;
                currentMinEfficiency = player.efficiency;

                long performance = currentSpeedSum * currentMinEfficiency;
                maxTeamPerformance = Math.max(maxTeamPerformance, performance);

                minHeap.offer(player);
                if(minHeap.size() == k) {
                    Player prevPlayer = minHeap.poll();
                    currentSpeedSum -= prevPlayer.speed;
                }
            }

            return (int) (maxTeamPerformance % MOD);
        }
    }

    class Solution_PQ_TLE {
        private int MOD = 1_000_000_007;
        public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
            if(n == 0 || k == 0 || k > n || speed == null || efficiency == null || speed.length != n || efficiency.length != n) {
                return 0;
            }

            List<Player> players = new ArrayList<>();
            for(int i = 0; i < n; i++) {
                Player player = new Player(i, speed[i], efficiency[i]);
                players.add(player);
            }

            Collections.sort(players, (p1, p2) -> p2.efficiency - p1.efficiency);

            long maxTeamPerformance = 0;
            PriorityQueue<Player> minHeap = new PriorityQueue<>((p1, p2) -> p1.speed - p2.speed);
            for(int i = 0; i < n; i++) {
                Set<Player> team = new HashSet<>();
                team.add(players.get(i));
                while(!minHeap.isEmpty()) {
                    team.add(minHeap.poll());
                }

                long performance = getPerformance(team);
                maxTeamPerformance = Math.max(maxTeamPerformance, performance);

                for(Player player : team) {
                    minHeap.offer(player);
                }

                while(!minHeap.isEmpty() && minHeap.size() >= k) {
                    minHeap.poll();
                }
            }

            return (int) (maxTeamPerformance % MOD);
        }

        private long getPerformance(Set<Player> team) {
            long speedComponent = 0;
            long efficiencyComponent = Integer.MAX_VALUE;
            for(Player player : team) {
                speedComponent += player.speed;
                efficiencyComponent = Math.min(efficiencyComponent, player.efficiency);
            }

            long performance = speedComponent * efficiencyComponent;
            return performance;
        }
    }

    class Solution_Recursive_TLE {
        int maxTeamPerformance;

        public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
            if(n == 0 || k == 0 || k > n || speed == null || efficiency == null || speed.length != n || efficiency.length != n) {
                return 0;
            }

            maxTeamPerformance = 0;
            maxPerformance(n, speed, efficiency, k, 0, new HashSet<>());

            return maxTeamPerformance;
        }

        private void maxPerformance(int n, int[] speed, int[] efficiency, int k, int index, Set<Integer> team) {
            if(team.size() == k || index == n) {
                int speedComponent = 0;
                int efficiencyComponent = Integer.MAX_VALUE;
                for(int i : team) {
                    speedComponent += speed[i];
                    efficiencyComponent = Math.min(efficiencyComponent, efficiency[i]);
                }

                int performance = speedComponent * efficiencyComponent;
                maxTeamPerformance = Math.max(maxTeamPerformance, performance);
                return;
            }

            // Without this member
            maxPerformance(n, speed, efficiency, k, index + 1, team);

            // With this member
            team.add(index);
            maxPerformance(n, speed, efficiency, k, index + 1, team);
            team.remove(index);
        }
    }

    static class Player {
        int index;
        int speed;
        int efficiency;

        public Player(int index, int speed, int efficiency) {
            this.index = index;
            this.speed = speed;
            this.efficiency = efficiency;
        }

        @Override
        public boolean equals(Object o) {
            if(o instanceof Player) {
                Player p = (Player) o;
                return this.index == p.index;
            }

            return false;
        }

        @Override
        public int hashCode() {
            return index;
        }

        @Override
        public String toString() {
            return "(" + speed + "," + efficiency + ")";
        }
    }

// 6
// [2,10,3,1,5,8]
// [5,4,3,9,7,2]
// 2

// 3
// [2,8,2]
// [2,7,1]
// 2

// 7
// [1,4,1,9,4,4,4]
// [8,2,1,7,1,8,4]
// 6
}

//    1383. Maximum Performance of a Team
//    Hard
//    You are given two integers n and k and two integer arrays speed and efficiency both of length n. There are n engineers numbered from 1 to n. speed[i] and efficiency[i] represent the speed and efficiency of the ith engineer respectively.
//
//    Choose at most k different engineers out of the n engineers to form a team with the maximum performance.
//
//    The performance of a team is the sum of their engineers' speeds multiplied by the minimum efficiency among their engineers.
//
//    Return the maximum performance of this team. Since the answer can be a huge number, return it modulo 109 + 7.
//
//
//
//    Example 1:
//
//    Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 2
//    Output: 60
//    Explanation:
//    We have the maximum performance of the team by selecting engineer 2 (with speed=10 and efficiency=4) and engineer 5 (with speed=5 and efficiency=7). That is, performance = (10 + 5) * min(4, 7) = 60.
//    Example 2:
//
//    Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 3
//    Output: 68
//    Explanation:
//    This is the same example as the first but k = 3. We can select engineer 1, engineer 2 and engineer 5 to get the maximum performance of the team. That is, performance = (2 + 10 + 5) * min(5, 4, 7) = 68.
//    Example 3:
//
//    Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 4
//    Output: 72
//
//
//    Constraints:
//
//    1 <= <= k <= n <= 105
//    speed.length == n
//    efficiency.length == n
//    1 <= speed[i] <= 105
//    1 <= efficiency[i] <= 108