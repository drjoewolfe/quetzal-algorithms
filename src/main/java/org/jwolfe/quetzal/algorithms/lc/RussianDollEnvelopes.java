package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RussianDollEnvelopes {
    class Solution {
        public int maxEnvelopes(int[][] envelopes) {
            if(envelopes == null || envelopes.length == 0) {
                return 0;
            }

            Arrays.sort(envelopes, (a, b) -> {
                if(a[0] == b[0]) {
                    return a[1] - b[1];
                }

                return a[0] - b[0];
            });

            int n = envelopes.length;
            int maxDolls = 1;
            int[] lis = new int[n];
            Arrays.fill(lis, 1);
            for(int i = 1; i < n; i++) {
                for(int j = 0; j < i; j++) {
                    int[] envIn = envelopes[j];
                    int[] envOut = envelopes[i];
                    if(canStack(envIn, envOut)
                            && lis[j] + 1 > lis[i]) {
                        lis[i] = lis[j] + 1;

                        maxDolls = Math.max(maxDolls, lis[i]);
                    }
                }
            }

            return maxDolls;
        }

        private boolean canStack(int[] envelopeIn, int[] envelopeOut) {
            return envelopeIn[0] < envelopeOut[0] && envelopeIn[1] < envelopeOut[1];
        }
    }

    class Solution_Classic {
        private int maxDolls;

        public int maxEnvelopes(int[][] envelopes) {
            if(envelopes == null || envelopes.length == 0) {
                return 0;
            }

            Arrays.sort(envelopes, (a, b) -> {
                if(a[0] == b[0]) {
                    return a[1] - b[1];
                }

                return a[0] - b[0];
            });

            maxDolls = 0;
            maxEnvelopes(envelopes, 0, new ArrayList<>());
            return maxDolls;
        }

        private void maxEnvelopes(int[][] envelopes, int index, List<int[]> envelopeList) {
            if(index == envelopes.length) {
                return;
            }

            for(int i = index; i < envelopes.length; i++) {
                int[] envelope = envelopes[i];
                if(envelopeList.size() > 0
                        && !canStack(envelopeList.get(envelopeList.size() - 1), envelope)) {
                    continue;
                }

                envelopeList.add(envelope);
                maxDolls = Math.max(maxDolls, envelopeList.size());

                maxEnvelopes(envelopes, i + 1, envelopeList);
                envelopeList.remove(envelopeList.size() - 1);
            }
        }

        private boolean canStack(int[] envelopeIn, int[] envelopeOut) {
            return envelopeIn[0] < envelopeOut[0] && envelopeIn[1] < envelopeOut[1];
        }
    }

// [[5,4],[6,4],[6,7],[2,3]]
}

//    354. Russian Doll Envelopes
//    Hard
//    You are given a 2D array of integers envelopes where envelopes[i] = [wi, hi] represents the width and the height of an envelope.
//
//    One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.
//
//    Return the maximum number of envelopes can you Russian doll (i.e., put one inside the other).
//
//    Note: You cannot rotate an envelope.
//
//
//
//    Example 1:
//
//    Input: envelopes = [[5,4],[6,4],[6,7],[2,3]]
//    Output: 3
//    Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
//    Example 2:
//
//    Input: envelopes = [[1,1],[1,1],[1,1]]
//    Output: 1
//
//
//    Constraints:
//
//    1 <= envelopes.length <= 5000
//    envelopes[i].length == 2
//    1 <= wi, hi <= 104