package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupThePeopleGivenTheGroupSizeTheyBelongTo {
    class Solution {
        public List<List<Integer>> groupThePeople(int[] groupSizes) {
            List<List<Integer>> results = new ArrayList<>();
            if(groupSizes == null || groupSizes.length == 0) {
                return results;
            }

            int n = groupSizes.length;
            Map<Integer, List<List<Integer>>> groupMappings = new HashMap<>();
            for(int i = 0; i < n; i++) {
                int size = groupSizes[i];

                if(!groupMappings.containsKey(size)) {
                    groupMappings.put(size, new ArrayList<>());
                }

                List<List<Integer>> mapping = groupMappings.get(size);
                if(mapping.size() == 0 || mapping.get(mapping.size() - 1).size() == size) {
                    mapping.add(new ArrayList<>());
                }

                mapping.get(mapping.size() - 1).add(i);
            }

            for(var entry : groupMappings.entrySet()) {
                for(var list : entry.getValue()) {
                    results.add(list);
                }
            }

            return results;
        }
    }

    class Solution_Correct_1 {
        public List<List<Integer>> groupThePeople(int[] groupSizes) {
            List<List<Integer>> result = new ArrayList<>();
            if(groupSizes == null || groupSizes.length == 0) {
                return result;
            }

            Map<Integer, List<List<Integer>>> groupMappings = new HashMap<>();
            for(int i = 0; i < groupSizes.length; i++) {
                int size = groupSizes[i];

                if(!groupMappings.containsKey(size)) {
                    groupMappings.put(size, new ArrayList<>());
                }

                var groupList = groupMappings.get(size);
                if(groupList.size() == 0 || groupList.get(groupList.size() - 1).size() == size) {
                    List<Integer> group = new ArrayList<>();
                    groupList.add(group);
                }

                groupList.get(groupList.size() - 1).add(i);
            }

            for(var entry : groupMappings.entrySet()) {
                for(var group : entry.getValue()) {
                    result.add(group);
                }
            }

            return result;
        }
    }
}

//    1282. Group the People Given the Group Size They Belong To
//    Medium
//    There are n people that are split into some unknown number of groups. Each person is labeled with a unique ID from 0 to n - 1.
//
//    You are given an integer array groupSizes, where groupSizes[i] is the size of the group that person i is in. For example, if groupSizes[1] = 3, then person 1 must be in a group of size 3.
//
//    Return a list of groups such that each person i is in a group of size groupSizes[i].
//
//    Each person should appear in exactly one group, and every person must be in a group. If there are multiple answers, return any of them. It is guaranteed that there will be at least one valid solution for the given input.
//
//
//
//    Example 1:
//
//    Input: groupSizes = [3,3,3,3,3,1,3]
//    Output: [[5],[0,1,2],[3,4,6]]
//    Explanation:
//    The first group is [5]. The size is 1, and groupSizes[5] = 1.
//    The second group is [0,1,2]. The size is 3, and groupSizes[0] = groupSizes[1] = groupSizes[2] = 3.
//    The third group is [3,4,6]. The size is 3, and groupSizes[3] = groupSizes[4] = groupSizes[6] = 3.
//    Other possible solutions are [[2,1,6],[5],[0,4,3]] and [[5],[0,6,2],[4,3,1]].
//    Example 2:
//
//    Input: groupSizes = [2,1,3,3,3,2]
//    Output: [[1],[0,5],[2,3,4]]
//
//
//    Constraints:
//
//    groupSizes.length == n
//    1 <= n <= 500
//    1 <= groupSizes[i] <= n