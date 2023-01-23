package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FindTheTownJudge {
    class Solution {
        public int findJudge(int N, int[][] trust) {
            if(N == 1) {
                return 1;
            }

            int[] trustMeter = new int[N + 1];
            int judge = -1;

            for(int[] trustInfo : trust) {
                int u = trustInfo[0];
                int v = trustInfo[1];

                trustMeter[u]--;
                trustMeter[v]++;
            }

            for(int i = 1; i <= N; i++) {
                if(trustMeter[i] == N - 1) {
                    return i;
                }
            }

            return -1;
        }
    }

    class Solution_Correct_2 {
        public int findJudge(int N, int[][] trust) {
            if(N == 1) {
                return 1;
            }

            Set<Integer> trusters = new HashSet<>();
            Map<Integer, Integer> trustMeter = new HashMap<>();
            for(int[] pair : trust) {
                int u = pair[0];
                int v = pair[1];

                trusters.add(u);
                trustMeter.put(v, trustMeter.getOrDefault(v, 0) + 1);
            }

            for(var entry : trustMeter.entrySet()) {
                int v = entry.getKey();
                int t = entry.getValue();

                if(t == N - 1 && !trusters.contains(v)) {
                    return v;
                }
            }

            return -1;
        }
    }

    class Solution_Correct {
        public int findJudge(int N, int[][] trust) {
            int[] trustMeter = new int[N + 1];

            for(int[] t : trust) {
                trustMeter[t[0]]--;
                trustMeter[t[1]]++;
            }

            for(int i = 1; i < N + 1; i++) {
                if(trustMeter[i] == N - 1) {
                    return i;
                }
            }

            return -1;
        }
    }
}

//    997. Find the Town Judge
//    Easy
//    In a town, there are n people labeled from 1 to n. There is a rumor that one of these people is secretly the town judge.
//
//    If the town judge exists, then:
//
//    The town judge trusts nobody.
//    Everybody (except for the town judge) trusts the town judge.
//    There is exactly one person that satisfies properties 1 and 2.
//    You are given an array trust where trust[i] = [ai, bi] representing that the person labeled ai trusts the person labeled bi.
//
//    Return the label of the town judge if the town judge exists and can be identified, or return -1 otherwise.
//
//
//
//    Example 1:
//
//    Input: n = 2, trust = [[1,2]]
//    Output: 2
//    Example 2:
//
//    Input: n = 3, trust = [[1,3],[2,3]]
//    Output: 3
//    Example 3:
//
//    Input: n = 3, trust = [[1,3],[2,3],[3,1]]
//    Output: -1
//
//
//    Constraints:
//
//    1 <= n <= 1000
//    0 <= trust.length <= 104
//    trust[i].length == 2
//    All the pairs of trust are unique.
//    ai != bi
//    1 <= ai, bi <= n