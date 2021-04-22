package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class BrickWall {
    class Solution {
        public int leastBricks(List<List<Integer>> wall) {
            if(wall == null || wall.size() == 0) {
                return 0;
            }

            Map<Integer, Integer> borders = new HashMap<>();
            int width = wall.get(0).stream().mapToInt(Integer::intValue).sum();

            for(var row : wall) {
                int x = 0;
                for(var brick : row) {
                    x += brick;

                    if(x != width) {
                        borders.put(x, borders.getOrDefault(x, 0) + 1);
                    }
                }
            }

            if(borders.size() == 0) {
                return wall.size();
            }

            int minCrossings = Integer.MAX_VALUE;
            int height = wall.size();
            for(var entry : borders.entrySet()) {
                int count = entry.getValue();

                minCrossings = Math.min(minCrossings,
                        height - count);
            }

            return minCrossings;
        }
    }

    class Solution_Good {
        public int leastBricks(List<List<Integer>> wall) {
            if(wall == null || wall.size() == 0) {
                return 0;
            }

            List<List<Integer>> endPoints = new ArrayList<>();
            Set<Integer> uniquePoints = new HashSet<>();

            int width = wall.get(0).stream().mapToInt(Integer::intValue).sum();

            for(var row : wall) {
                List<Integer> brickEnds = new ArrayList<>();
                endPoints.add(brickEnds);

                int x = 0;
                for(var brick : row) {
                    x += brick;

                    if(x != width) {
                        brickEnds.add(x);
                        uniquePoints.add(x);
                    }
                }
            }

            if(uniquePoints.size() == 0) {
                return wall.size();
            }

            int minCrossings = Integer.MAX_VALUE;
            for(int point : uniquePoints) {
                minCrossings = Math.min(minCrossings,
                        getCrossingsCount(endPoints, point));
            }

            return minCrossings;
        }

        private int getCrossingsCount(List<List<Integer>> endPoints, int point) {
            int crossings = 0;
            for(var brickEnds : endPoints) {
                if(find(brickEnds, point) == -1) {
                    crossings++;
                }
            }

            return crossings;
        }

        private int find(List<Integer> brickEnds, int point) {
            int left = 0;
            int right = brickEnds.size() - 1;

            while(left <= right) {
                int mid = left + (right - left) / 2;
                int midPoint = brickEnds.get(mid);

                if(point == midPoint) {
                    return mid;
                } else if(point > midPoint) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            return -1;
        }

        private void print(List<List<Integer>> endPoints) {
            for(var brickEnds : endPoints) {
                for(var point : brickEnds) {
                    System.out.print(point + " ");
                }

                System.out.println();
            }
        }
    }

    class Solution_TLE {
        public int leastBricks(List<List<Integer>> wall) {
            if(wall == null || wall.size() == 0) {
                return 0;
            }

            Set<Integer> set = new HashSet<>();
            Queue<Integer> queue = new LinkedList<>();
            for(var row : wall) {
                int x = 0;
                for(int i = 0; i < row.size() - 1; i++) {
                    Integer brick = row.get(i);
                    x += brick;

                    if(!set.contains(x)) {
                        set.add(x);
                        queue.offer(x);
                    }
                }
            }

            if(queue.isEmpty()) {
                return wall.size();
            }

            int minWallCount = Integer.MAX_VALUE;
            while(!queue.isEmpty()) {
                int location = queue.poll();
                minWallCount = Math.min(minWallCount,
                        getCrossedWallCount(wall, location));
            }

            return minWallCount;
        }

        private int getCrossedWallCount(List<List<Integer>> wall, int position) {
            int count = 0;
            for(List<Integer> section : wall) {
                int x1 = 0;
                int x2 = 0;
                for(int width : section) {
                    x2 += width;
                    if(position > x1 && position < x2) {
                        count++;
                    }

                    if(x2 > position) {
                        break;
                    }

                    x1 = x2;
                }
            }

            return count;
        }
    }

    class Solution_Classic {
        public int leastBricks(List<List<Integer>> wall) {
            if(wall == null || wall.size() == 0) {
                return 0;
            }

            int width = wall.get(0).stream().mapToInt(Integer::intValue).sum();
            int minWallCount = Integer.MAX_VALUE;
            for(int i = 1; i < width; i++) {
                minWallCount = Math.min(minWallCount,
                        getCrossedWallCount(wall, i));
            }

            return minWallCount == Integer.MAX_VALUE ? wall.size() : minWallCount;
        }

        private int getCrossedWallCount(List<List<Integer>> wall, int position) {
            int count = 0;
            for(List<Integer> section : wall) {
                int x1 = 0;
                int x2 = 0;
                for(int width : section) {
                    x2 += width;
                    if(position > x1 && position < x2) {
                        count++;
                    }

                    if(x2 > position) {
                        break;
                    }

                    x1 = x2;
                }
            }

            return count;
        }
    }

// [[1,2,2,1],[3,1,2],[1,3,2],[2,4],[3,1,2],[1,3,1,1]]
// [[100000000],[100000000],[100000000]]
// [[1],[1],[1]]
// [[4, 5, 1], [2, 1, 2, 5], [1, 2, 1, 2, 4]]
}

//    554. Brick Wall
//    Medium
//    There is a brick wall in front of you. The wall is rectangular and has several rows of bricks. The bricks have the same height but different width. You want to draw a vertical line from the top to the bottom and cross the least bricks.
//
//    The brick wall is represented by a list of rows. Each row is a list of integers representing the width of each brick in this row from left to right.
//
//    If your line go through the edge of a brick, then the brick is not considered as crossed. You need to find out how to draw the line to cross the least bricks and return the number of crossed bricks.
//
//    You cannot draw a line just along one of the two vertical edges of the wall, in which case the line will obviously cross no bricks.
//
//
//
//    Example:
//
//    Input: [[1,2,2,1],
//    [3,1,2],
//    [1,3,2],
//    [2,4],
//    [3,1,2],
//    [1,3,1,1]]
//
//    Output: 2
//
//    Explanation:
//
//
//
//    Note:
//
//    The width sum of bricks in different rows are the same and won't exceed INT_MAX.
//    The number of bricks in each row is in range [1,10,000]. The height of wall is in range [1,10,000]. Total number of bricks of the wall won't exceed 20,000.