package org.jwolfe.quetzal.algorithms.lc;

import org.jwolfe.quetzal.library.general.Pair;

import java.util.*;

public class ShortestPathToGetAllKeys {
    class Solution {
        public int shortestPathAllKeys(String[] grid) {
            if(grid == null || grid.length == 0 || grid[0].length() == 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length();

            Set<Character> keySet = new HashSet<>();
            Set<Character> lockSet = new HashSet<>();

            int startRow = -1;
            int startColumn = -1;
            int allKeys = 0;

            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    char c = grid[i].charAt(j);

                    if(c == '@') {
                        startRow = i;
                        startColumn = j;
                    } else if(c == '.' || c == '#') {
                        continue;
                    } else if(c >= 'a' && c <= 'f') {
                        int key = c - 'a';
                        allKeys |= (1 << key);
                        keySet.add(c);
                    } else if(c >= 'A' && c <= 'F') {
                        lockSet.add(c);
                    }
                }
            }

            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[] {startRow, startColumn, 0, 0});

            int[][] directions = new int[][] {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

            Map<Integer, Set<Pair<Integer, Integer>>> visited = new HashMap<>();
            visited.put(0, new HashSet<>());
            visited.get(0).add(new Pair<>(startRow, startColumn));

            while(!queue.isEmpty()) {
                int[] entry = queue.poll();
                int x = entry[0];
                int y = entry[1];
                int d = entry[2];
                int currentKeys = entry[3];

                for(int[] direction : directions) {
                    int nx = x + direction[0];
                    int ny = y + direction[1];

                    if(nx < 0 || nx >= m || ny < 0 || ny >= n) {
                        continue;
                    }

                    char c = grid[nx].charAt(ny);
                    if(c == '#') {
                        continue;
                    }

                    // System.out.println("\t\t" + c);
                    if(keySet.contains(c) && ((currentKeys & (1 << c - 'a')) == 0)) {
                        int key = c - 'a';

                        // Pickup the key
                        int newKeys = currentKeys | (1 << key);
                        if(allKeys == newKeys) {
                            return d + 1;
                        }

                        if(!visited.containsKey(newKeys)) {
                            visited.put(newKeys, new HashSet<>());
                        }

                        visited.get(newKeys).add(new Pair<>(nx, ny));
                        queue.offer(new int[] {nx, ny, d + 1, newKeys});

                    } else if(lockSet.contains(c)) {
                        // Do we have the key for this lock ?
                        int key = c - 'A';
                        if((currentKeys & (1 << key)) == 0) {
                            continue;
                        }

                        // Have the key. Visit the cell if not already visited
                        if(!visited.get(currentKeys).contains(new Pair<>(nx, ny))) {
                            visited.get(currentKeys).add(new Pair<>(nx, ny));
                            queue.offer(new int[] {nx, ny, d + 1, currentKeys});
                        }

                    } else {
                        // Visit the cell if not already visited
                        if(!visited.get(currentKeys).contains(new Pair<>(nx, ny))) {
                            visited.get(currentKeys).add(new Pair<>(nx, ny));
                            queue.offer(new int[] {nx, ny, d + 1, currentKeys});
                        }
                    }
                }
            }

            return -1;
        }
    }

// ["@.a..","###.#","b.A.B"]
// ["Dd#b@",".fE.e","##.B.","#.cA.","aF.#C"]
}

//    864. Shortest Path to Get All Keys
//    Hard
//    You are given an m x n grid grid where:
//
//    '.' is an empty cell.
//    '#' is a wall.
//    '@' is the starting point.
//    Lowercase letters represent keys.
//    Uppercase letters represent locks.
//    You start at the starting point and one move consists of walking one space in one of the four cardinal directions. You cannot walk outside the grid, or walk into a wall.
//
//    If you walk over a key, you can pick it up and you cannot walk over a lock unless you have its corresponding key.
//
//    For some 1 <= k <= 6, there is exactly one lowercase and one uppercase letter of the first k letters of the English alphabet in the grid. This means that there is exactly one key for each lock, and one lock for each key; and also that the letters used to represent the keys and locks were chosen in the same order as the English alphabet.
//
//    Return the lowest number of moves to acquire all keys. If it is impossible, return -1.
//
//
//
//    Example 1:
//
//
//    Input: grid = ["@.a..","###.#","b.A.B"]
//    Output: 8
//    Explanation: Note that the goal is to obtain all the keys not to open all the locks.
//    Example 2:
//
//
//    Input: grid = ["@..aA","..B#.","....b"]
//    Output: 6
//    Example 3:
//
//
//    Input: grid = ["@Aa"]
//    Output: -1
//
//
//    Constraints:
//
//    m == grid.length
//    n == grid[i].length
//    1 <= m, n <= 30
//    grid[i][j] is either an English letter, '.', '#', or '@'.
//    The number of keys in the grid is in the range [1, 6].
//    Each key in the grid is unique.
//    Each key in the grid has a matching lock.