package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BestTeamWithNoConflicts {
    class Solution {
        public int bestTeamScore(int[] scores, int[] ages) {
            if(scores == null || ages == null || scores.length == 0 || scores.length != ages.length) {
                return 0;
            }

            int n = scores.length;

            List<Player> players = new ArrayList<>();
            for(int i = 0; i < n; i++) {
                players.add(new Player(scores[i], ages[i]));
            }

            Collections.sort(players, (a, b) -> {
                if(a.age == b.age) {
                    return a.score - b.score;
                }

                return a.age - b.age;
            });

            int[] lis = new int[n];
            lis[0] = players.get(0).score;

            int maxTeamScore = lis[0];
            for(int i = 1; i < n; i++) {
                Player p1 = players.get(i);
                lis[i] = p1.score;

                for(int j = 0; j < i; j++) {
                    Player p2 = players.get(j);
                    if(p2.score <= p1.score
                            && lis[i] < lis[j] + p1.score) {
                        lis[i] = lis[j] + p1.score;
                    }
                }

                maxTeamScore = Math.max(maxTeamScore, lis[i]);
            }

            return maxTeamScore;
        }

        private class Player {
            int score;
            int age;

            public Player(int score, int age) {
                this.score = score;
                this.age = age;
            }
        }

        private void print(int[] arr) {
            for(int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }

    class Solution_Correct_1 {
        public int bestTeamScore(int[] scores, int[] ages) {
            if(scores == null || ages == null || scores.length == 0 || scores.length != ages.length) {
                return 0;
            }

            int n = scores.length;
            List<Player> players = new ArrayList<>();
            for(int i = 0; i < n; i++) {
                players.add(new Player(ages[i], scores[i]));
            }

            Collections.sort(players, ((p1, p2) -> {
                if(p1.age == p2.age) {
                    return p1.score - p2.score;
                }

                return p1.age - p2.age;
            }));

            int[] lis = new int[n];
            lis[0] = players.get(0).score;
            int maxScore = lis[0];
            for(int i = 1; i < n; i++) {
                Player a = players.get(i);
                lis[i] = a.score;
                for(int j = 0; j < i; j++) {
                    Player b = players.get(j);

                    if(!(a.age != b.age && b.score > a.score)) {
                        lis[i] = Math.max(lis[i], lis[j] + a.score);
                    }
                }

                maxScore = Math.max(maxScore, lis[i]);
            }

            return maxScore;
        }

        private class Player {
            int age;
            int score;

            public Player(int age, int score) {
                this.age = age;
                this.score = score;
            }

            @Override
            public String toString() {
                return "(" + this.age + ", " + this.score + ")";
            }
        }

        private void print(int[] arr) {
            for(int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }

    class Solution_Backtracking {
        public int bestTeamScore(int[] scores, int[] ages) {
            if(scores == null || ages == null || scores.length == 0 || scores.length != ages.length) {
                return 0;
            }

            List<Player> players = new ArrayList<>();
            int n = scores.length;
            for(int i = 0; i < n; i++) {
                players.add(new Player(ages[i], scores[i]));
            }

            // System.out.println(players);
            return bestTeamScore(players, 0, 0, new ArrayList<>()) ;
        }

        private int bestTeamScore(List<Player> players, int index, int score, List<Player> team) {
            if(index == players.size()) {
                // System.out.println(team + " : " + score);
                return score;
            }

            int maxScore = Integer.MIN_VALUE;

            Player player = players.get(index);
            if(canInclude(team, player)) {
                team.add(player);
                maxScore = bestTeamScore(players, index + 1, score + player.score, team);
                team.remove(team.size() - 1);
            }

            maxScore = Math.max(maxScore,
                    bestTeamScore(players, index + 1, score, team));

            return maxScore;
        }

        private boolean canInclude(List<Player> team, Player player) {
            for(Player teamMember : team) {
                if((teamMember.age > player.age && teamMember.score < player.score)
                        || (teamMember.age < player.age && teamMember.score > player.score)) {
                    return false;
                }
            }

            return true;
        }

        private class Player {
            int age;
            int score;

            public Player(int age, int score) {
                this.age = age;
                this.score = score;
            }

            @Override
            public String toString() {
                return "(" + this.age + ", " + this.score + ")";
            }
        }
    }

// [1,3,5,10,15]
// [1,2,3,4,5]

// [1,2,3,5]
// [8,9,10,1]

// [9,2,8,8,2]
// [4,1,3,3,5]

// [4,5,6,5]
// [2,1,2,1]
}

//    1626. Best Team With No Conflicts
//    Medium
//    You are the manager of a basketball team. For the upcoming tournament, you want to choose the team with the highest overall score. The score of the team is the sum of scores of all the players in the team.
//
//    However, the basketball team is not allowed to have conflicts. A conflict exists if a younger player has a strictly higher score than an older player. A conflict does not occur between players of the same age.
//
//    Given two lists, scores and ages, where each scores[i] and ages[i] represents the score and age of the ith player, respectively, return the highest overall score of all possible basketball teams.
//
//
//
//    Example 1:
//
//    Input: scores = [1,3,5,10,15], ages = [1,2,3,4,5]
//    Output: 34
//    Explanation: You can choose all the players.
//    Example 2:
//
//    Input: scores = [4,5,6,5], ages = [2,1,2,1]
//    Output: 16
//    Explanation: It is best to choose the last 3 players. Notice that you are allowed to choose multiple people of the same age.
//    Example 3:
//
//    Input: scores = [1,2,3,5], ages = [8,9,10,1]
//    Output: 6
//    Explanation: It is best to choose the first 3 players.
//
//
//    Constraints:
//
//    1 <= scores.length, ages.length <= 1000
//    scores.length == ages.length
//    1 <= scores[i] <= 106
//    1 <= ages[i] <= 1000