package org.jwolfe.quetzal.algorithms.lc;

public class TakingMaximumEnergyFromTheMysticDungeon {
    class Solution {
        public int maximumEnergy(int[] energy, int k) {
            if (energy == null || k < 0) {
                return 0;
            }

            int n = energy.length;
            int maxEnergy = Integer.MIN_VALUE;

            int[] sumArr = new int[n];
            for (int i = n - 1; i >= 0; i--) {
                if (i + k < n) {
                    sumArr[i] = sumArr[i + k];
                }

                sumArr[i] += energy[i];
                maxEnergy = Math.max(maxEnergy, sumArr[i]);
            }

            return maxEnergy;
        }
    }

    class Solution_Brute {
        public int maximumEnergy(int[] energy, int k) {
            if (energy == null || k < 0) {
                return 0;
            }

            int n = energy.length;
            int maxEnergy = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                // i is the wizard we start with

                int roundEnergy = 0;
                int j = i;
                while (j < n) {
                    roundEnergy += energy[j];
                    j += k;
                }

                maxEnergy = Math.max(maxEnergy, roundEnergy);
            }

            return maxEnergy;
        }
    }
}

//    3147. Taking Maximum Energy From the Mystic Dungeon
//    Medium
//    In a mystic dungeon, n magicians are standing in a line. Each magician has an attribute that gives you energy. Some magicians can give you negative energy, which means taking energy from you.
//
//    You have been cursed in such a way that after absorbing energy from magician i, you will be instantly transported to magician (i + k). This process will be repeated until you reach the magician where (i + k) does not exist.
//
//    In other words, you will choose a starting point and then teleport with k jumps until you reach the end of the magicians' sequence, absorbing all the energy during the journey.
//
//    You are given an array energy and an integer k. Return the maximum possible energy you can gain.
//
//    Note that when you are reach a magician, you must take energy from them, whether it is negative or positive energy.
//
//
//
//    Example 1:
//
//    Input: energy = [5,2,-10,-5,1], k = 3
//
//    Output: 3
//
//    Explanation: We can gain a total energy of 3 by starting from magician 1 absorbing 2 + 1 = 3.
//
//    Example 2:
//
//    Input: energy = [-2,-3,-1], k = 2
//
//    Output: -1
//
//    Explanation: We can gain a total energy of -1 by starting from magician 2.
//
//
//
//    Constraints:
//
//    1 <= energy.length <= 105
//    -1000 <= energy[i] <= 1000
//    1 <= k <= energy.length - 1
