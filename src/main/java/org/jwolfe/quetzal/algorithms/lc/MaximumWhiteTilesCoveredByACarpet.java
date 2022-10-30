package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class MaximumWhiteTilesCoveredByACarpet {
    class Solution {
        public int maximumWhiteTiles(int[][] tiles, int carpetLen) {
            if(tiles == null || tiles.length == 0 || carpetLen == 0) {
                return 0;
            }

            Arrays.sort(tiles, (a, b) -> a[0] - b[0]);

            int n = tiles.length;
            long[] prefixSums = new long[n + 1];
            for(int i = 0; i < n; i++) {
                int[] tile = tiles[i];
                int left = tile[0];
                int right = tile[1];

                prefixSums[i + 1] = prefixSums[i] + (right - left + 1);
            }

            long maxTiles = 0;
            for(int i = 0; i < n; i++) {
                int[] tile = tiles[i];
                int left = tile[0];
                int right = tile[1];

                int end = left + carpetLen - 1;
                int j = binarySearchForGreater(tiles, end);

                long covered = prefixSums[j] - prefixSums[i];
                if(j != n && end >= tiles[j][0]) {
                    covered += (end - tiles[j][0] + 1);
                }

                maxTiles = Math.max(maxTiles, covered);
            }

            return (int) maxTiles;
        }

        private int binarySearchForGreater(int[][] tiles, int position) {
            int left = 0;
            int right = tiles.length - 1;

            int index = tiles.length;
            while(left <= right) {
                int mid = left + (right - left) / 2;

                int pos = tiles[mid][1];
                if(pos >= position) {
                    index = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            return index;
        }
    }

// [[1,5],[10,11],[12,18],[20,25],[30,32]]
// 10

// [[10,11],[1,1]]
// 2

// [[8051,8057],[8074,8089],[7994,7995],[7969,7987],[8013,8020],[8123,8139],[7930,7950],[8096,8104],[7917,7925],[8027,8035],[8003,8011]]
// 9854
}

//    2271. Maximum White Tiles Covered by a Carpet
//    Medium
//    You are given a 2D integer array tiles where tiles[i] = [li, ri] represents that every tile j in the range li <= j <= ri is colored white.
//
//    You are also given an integer carpetLen, the length of a single carpet that can be placed anywhere.
//
//    Return the maximum number of white tiles that can be covered by the carpet.
//
//
//
//    Example 1:
//
//
//    Input: tiles = [[1,5],[10,11],[12,18],[20,25],[30,32]], carpetLen = 10
//    Output: 9
//    Explanation: Place the carpet starting on tile 10.
//    It covers 9 white tiles, so we return 9.
//    Note that there may be other places where the carpet covers 9 white tiles.
//    It can be shown that the carpet cannot cover more than 9 white tiles.
//    Example 2:
//
//
//    Input: tiles = [[10,11],[1,1]], carpetLen = 2
//    Output: 2
//    Explanation: Place the carpet starting on tile 10.
//    It covers 2 white tiles, so we return 2.
//
//
//    Constraints:
//
//    1 <= tiles.length <= 5 * 104
//    tiles[i].length == 2
//    1 <= li <= ri <= 109
//    1 <= carpetLen <= 109
//    The tiles are non-overlapping.