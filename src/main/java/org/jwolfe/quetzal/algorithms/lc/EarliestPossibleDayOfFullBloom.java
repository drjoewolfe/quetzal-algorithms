package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EarliestPossibleDayOfFullBloom {
    class Solution {
        public int earliestFullBloom(int[] plantTime, int[] growTime) {
            if(plantTime == null || plantTime.length == 0 || growTime == null || plantTime.length != growTime.length) {
                return 0;
            }

            int n = plantTime.length;
            List<PlantInfo> plantInfos = new ArrayList<>();
            for(int i = 0; i < n; i++) {
                PlantInfo info = new PlantInfo(plantTime[i], growTime[i]);
                plantInfos.add(info);
            }

            Collections.sort(plantInfos, (a, b) -> b.growTime - a.growTime);

            int totalPlantTime = 0;
            int latestBloomTime = 0;
            for(int i = 0; i < n; i++) {
                PlantInfo info = plantInfos.get(i);

                totalPlantTime += info.plantTime;
                latestBloomTime = Math.max(latestBloomTime, totalPlantTime + info.growTime);
            }

            return latestBloomTime;
        }

        private class PlantInfo {
            int plantTime;
            int growTime;

            public PlantInfo(int plantTime, int growTime) {
                this.plantTime = plantTime;
                this.growTime = growTime;
            }
        }
    }

    class Solution_BackTrack_TLE {
        public int earliestFullBloom(int[] plantTime, int[] growTime) {
            if(plantTime == null || plantTime.length == 0 || growTime == null || plantTime.length != growTime.length) {
                return 0;
            }

            int n = plantTime.length;
            int[] plantFinishTimes = new int[n];
            Arrays.fill(plantFinishTimes, -1);

            return earliestFullBloom(plantTime, growTime, 0, plantFinishTimes);
        }

        private int earliestFullBloom(int[] plantTime, int[] growTime, int time, int[] plantFinishTimes) {
            if(allPlantsPlanted(plantFinishTimes)) {
                return fullBloomTime(plantFinishTimes, growTime);
            }

            int earliestBloomTime = Integer.MAX_VALUE;
            for(int i = 0; i < plantTime.length; i++) {
                if(plantFinishTimes[i] != -1) {
                    // already planted
                    continue;
                }

                // try with planting this plant
                plantTime[i] -= 1;
                if(plantTime[i] == 0) {
                    plantFinishTimes[i] = time;
                }

                int bloomTime = earliestFullBloom(plantTime, growTime, time + 1, plantFinishTimes);
                earliestBloomTime = Math.min(earliestBloomTime, bloomTime);

                plantTime[i] += 1;
                if(plantTime[i] == 1) {
                    plantFinishTimes[i] = -1;
                }
            }

            return earliestBloomTime;
        }

        private boolean allPlantsPlanted(int[] plantFinishTimes) {
            for(int t : plantFinishTimes) {
                if(t == -1) {
                    return false;
                }
            }

            return true;
        }

        private int fullBloomTime(int[] plantFinishTimes, int[] growTime) {
            int bloomTime = 0;
            for(int i = 0; i < plantFinishTimes.length; i++) {
                int pt = plantFinishTimes[i];
                int gt = growTime[i];

                int bt = pt + gt + 1;
                bloomTime = Math.max(bloomTime, bt);
            }

            return bloomTime;
        }
    }

// [1,4,3]
// [2,3,1]

// [3,11,29,4,4,26,26,12,13,10,30,19,27,2,10]
// [10,13,22,17,18,15,21,11,24,14,18,23,1,30,6]
}

//    2136. Earliest Possible Day of Full Bloom
//    Hard
//    You have n flower seeds. Every seed must be planted first before it can begin to grow, then bloom. Planting a seed takes time and so does the growth of a seed. You are given two 0-indexed integer arrays plantTime and growTime, of length n each:
//
//    plantTime[i] is the number of full days it takes you to plant the ith seed. Every day, you can work on planting exactly one seed. You do not have to work on planting the same seed on consecutive days, but the planting of a seed is not complete until you have worked plantTime[i] days on planting it in total.
//    growTime[i] is the number of full days it takes the ith seed to grow after being completely planted. After the last day of its growth, the flower blooms and stays bloomed forever.
//    From the beginning of day 0, you can plant the seeds in any order.
//
//    Return the earliest possible day where all seeds are blooming.
//
//
//
//    Example 1:
//
//
//    Input: plantTime = [1,4,3], growTime = [2,3,1]
//    Output: 9
//    Explanation: The grayed out pots represent planting days, colored pots represent growing days, and the flower represents the day it blooms.
//    One optimal way is:
//    On day 0, plant the 0th seed. The seed grows for 2 full days and blooms on day 3.
//    On days 1, 2, 3, and 4, plant the 1st seed. The seed grows for 3 full days and blooms on day 8.
//    On days 5, 6, and 7, plant the 2nd seed. The seed grows for 1 full day and blooms on day 9.
//    Thus, on day 9, all the seeds are blooming.
//    Example 2:
//
//
//    Input: plantTime = [1,2,3,2], growTime = [2,1,2,1]
//    Output: 9
//    Explanation: The grayed out pots represent planting days, colored pots represent growing days, and the flower represents the day it blooms.
//    One optimal way is:
//    On day 1, plant the 0th seed. The seed grows for 2 full days and blooms on day 4.
//    On days 0 and 3, plant the 1st seed. The seed grows for 1 full day and blooms on day 5.
//    On days 2, 4, and 5, plant the 2nd seed. The seed grows for 2 full days and blooms on day 8.
//    On days 6 and 7, plant the 3rd seed. The seed grows for 1 full day and blooms on day 9.
//    Thus, on day 9, all the seeds are blooming.
//    Example 3:
//
//    Input: plantTime = [1], growTime = [1]
//    Output: 2
//    Explanation: On day 0, plant the 0th seed. The seed grows for 1 full day and blooms on day 2.
//    Thus, on day 2, all the seeds are blooming.
//
//
//    Constraints:
//
//    n == plantTime.length == growTime.length
//    1 <= n <= 105
//    1 <= plantTime[i], growTime[i] <= 104