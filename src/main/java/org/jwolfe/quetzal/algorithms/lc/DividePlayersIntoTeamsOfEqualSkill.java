package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class DividePlayersIntoTeamsOfEqualSkill {
    class Solution {
        public long dividePlayers(int[] skill) {
            if (skill == null || skill.length == 0 || skill.length % 2 != 0) {
                return -1;
            }

            int n = skill.length;
            int totalSkill = 0;

            int[] skillFrequencies = new int[1001];

            for (int playerSkill : skill) {
                totalSkill += playerSkill;
                skillFrequencies[playerSkill]++;
            }

            int teamCount = n / 2;
            if (totalSkill % teamCount != 0) {
                return -1;
            }

            long currency = 0;
            int teamSkill = totalSkill / teamCount;
            for (int playerSkill : skill) {
                int partnerSkill = teamSkill - playerSkill;

                if (skillFrequencies[partnerSkill] == 0) {
                    return -1;
                }

                skillFrequencies[partnerSkill]--;
                currency += (playerSkill * partnerSkill);
            }

            return currency / 2;
        }
    }

    class Solution_Correct_1 {
        public long dividePlayers(int[] skill) {
            if (skill == null || skill.length < 2 || skill.length % 2 != 0) {
                return -1;
            }

            Arrays.sort(skill);

            int n = skill.length;

            int teamSkill = skill[0] + skill[n - 1];
            long chemistry = (skill[0] * skill[n - 1]);

            int left = 1;
            int right = n - 2;

            while (left < right) {
                int p1 = skill[left++];
                int p2 = skill[right--];

                if (p1 + p2 != teamSkill) {
                    return -1;
                }

                chemistry += (p1 * p2);
            }

            return chemistry;
        }
    }

// [3,2,5,1,3,4]
}

//    2491. Divide Players Into Teams of Equal Skill
//    Medium
//    You are given a positive integer array skill of even length n where skill[i] denotes the skill of the ith player. Divide the players into n / 2 teams of size 2 such that the total skill of each team is equal.
//
//    The chemistry of a team is equal to the product of the skills of the players on that team.
//
//    Return the sum of the chemistry of all the teams, or return -1 if there is no way to divide the players into teams such that the total skill of each team is equal.
//
//
//
//    Example 1:
//
//    Input: skill = [3,2,5,1,3,4]
//    Output: 22
//    Explanation:
//    Divide the players into the following teams: (1, 5), (2, 4), (3, 3), where each team has a total skill of 6.
//    The sum of the chemistry of all the teams is: 1 * 5 + 2 * 4 + 3 * 3 = 5 + 8 + 9 = 22.
//    Example 2:
//
//    Input: skill = [3,4]
//    Output: 12
//    Explanation:
//    The two players form a team with a total skill of 7.
//    The chemistry of the team is 3 * 4 = 12.
//    Example 3:
//
//    Input: skill = [1,1,2,3]
//    Output: -1
//    Explanation:
//    There is no way to divide the players into teams such that the total skill of each team is equal.
//
//
//    Constraints:
//
//    2 <= skill.length <= 105
//    skill.length is even.
//    1 <= skill[i] <= 1000