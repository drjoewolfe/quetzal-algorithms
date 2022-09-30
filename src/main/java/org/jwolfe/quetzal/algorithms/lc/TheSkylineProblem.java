package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class TheSkylineProblem {
    class Solution {
        public List<List<Integer>> getSkyline(int[][] buildings) {
            List<List<Integer>> skyline = new ArrayList<>();

            PriorityQueue<BuildingEdge> heap = new PriorityQueue<>();

            for(int[] building : buildings) {
                int left = building[0];
                int right = building[1];
                int height = building[2];

                heap.offer(new BuildingEdge(left, height, true));
                heap.offer(new BuildingEdge(right, height, false));
            }

            int currentHeight = 0;
            TreeMap<Integer, Integer> heightCounts = new TreeMap<>();
            heightCounts.put(0, 1);

            while(!heap.isEmpty()) {
                BuildingEdge edge = heap.poll();

                if(edge.isLeft) {
                    heightCounts.put(edge.height, heightCounts.getOrDefault(edge.height, 0) + 1);
                } else {
                    if(heightCounts.get(edge.height) == 1) {
                        heightCounts.remove(edge.height);
                    } else {
                        heightCounts.put(edge.height, heightCounts.get(edge.height) - 1);
                    }
                }

                int maxHeight = heightCounts.lastKey();
                if(maxHeight != currentHeight) {
                    currentHeight = maxHeight;

                    List<Integer> point = new ArrayList<>();
                    point.add(edge.coordinate);
                    point.add(currentHeight);
                    skyline.add(point);
                }
            }

            return skyline;
        }

        private class BuildingEdge implements Comparable<BuildingEdge> {
            int coordinate;
            int height;
            boolean isLeft;

            public BuildingEdge(int coordinate, int height, boolean isLeft) {
                this.coordinate = coordinate;
                this.height = height;
                this.isLeft = isLeft;
            }

            @Override
            public int compareTo(BuildingEdge other) {
                if(this.coordinate != other.coordinate) {
                    return this.coordinate - other.coordinate;
                }

                if(this.isLeft && other.isLeft) {
                    return other.height - this.height;
                } else if(!this.isLeft && other.isLeft) {
                    return 1;
                } else if(this.isLeft && !other.isLeft) {
                    return -1;
                } else {
                    return this.height - other.height;
                }
            }
        }
    }

    class Solution_Correct {
        public List<List<Integer>> getSkyline(int[][] buildings) {
            List<List<Integer>> skyline = new ArrayList<>();
            if(buildings == null || buildings.length == 0) {
                return skyline;
            }

            BuildingEdge[] allEdges = new BuildingEdge[buildings.length * 2];

            int index = 0;
            for(int[] building : buildings) {
                int l = building[0];
                int r = building[1];
                int h = building[2];

                BuildingEdge leftEdge = new BuildingEdge(l, h, true);
                BuildingEdge rightEdge = new BuildingEdge(r, h, false);

                allEdges[index++] = leftEdge;
                allEdges[index++] = rightEdge;
            }

            Arrays.sort(allEdges);

            int currentHeight = 0;
            List<Integer> point = null;

            TreeMap<Integer, Integer> map = new TreeMap<>();
            map.put(0, 1);

            for(BuildingEdge edge : allEdges) {
                if(edge.isStart) {
                    map.put(edge.height,
                            map.getOrDefault(edge.height, 0) + 1);
                } else {
                    if(map.containsKey(edge.height)) {
                        if(map.get(edge.height) > 1) {
                            map.put(edge.height,
                                    map.get(edge.height) - 1);
                        } else {
                            map.remove(edge.height);
                        }
                    }
                }

                int lastHeight = map.lastKey();
                if(currentHeight != lastHeight) {
                    point = new ArrayList<>();
                    point.add(edge.coordinate);
                    point.add(lastHeight);
                    skyline.add(point);

                    currentHeight = lastHeight;
                }
            }

            return skyline;
        }

        class BuildingEdge implements Comparable<BuildingEdge> {
            int coordinate;
            int height;
            boolean isStart;

            public BuildingEdge (int coordinate, int height, boolean isStart) {
                this.coordinate = coordinate;
                this.height = height;
                this.isStart = isStart;
            }

            @Override
            public int compareTo(BuildingEdge o) {
                if(this.coordinate != o.coordinate) {
                    return this.coordinate - o.coordinate;
                }

                if(this.isStart && o.isStart) {
                    return o.height - this.height;
                }

                if(this.isStart && !o.isStart) {
                    return -1;
                }

                if(!this.isStart && o.isStart) {
                    return 1;
                }

                return this.height - o.height;
            }
        }

        private void printSkyline(List<List<Integer>> skyline) {
            for(List<Integer> row : skyline) {
                for(Integer val : row) {
                    System.out.print(val + " ");
                }

                System.out.println();
            }

            System.out.println();
        }
    }

    class Solution_Incomplete {
        public List<List<Integer>> getSkyline(int[][] buildings) {
            List<List<Integer>> skyline = new ArrayList<>();
            if(buildings == null || buildings.length == 0) {
                return skyline;
            }

            PriorityQueue<Coordinate> queue = new PriorityQueue<>((p1, p2) -> {
                if(p1.x != p2.x) {
                    return p1.x - p2.x;
                }
                return p2.y - p2.y;
            });
            for(var building : buildings) {
                if(building.length != 3) {
                    // Invalid input
                    return skyline;
                }

                int l = building[0];
                int r = building[1];
                int h = building[2];

                Coordinate left = new Coordinate(l, h, false);
                Coordinate right = new Coordinate(r, h, true);

                queue.offer(left);
                queue.offer(right);
            }

            int currentHeight = 0;
            while(!queue.isEmpty()) {
                Coordinate p = queue.poll();

                if(p.end) {
                    if(p.y == currentHeight) {
                        // Current building has ended

                    }
                } else {
                    if(p.y > currentHeight) {
                        currentHeight = p.y;
                        skyline.add(p.getAsList());
                    }
                }
            }

            return skyline;
        }

        class Coordinate {
            int x;
            int y;
            boolean end;

            Coordinate(int x, int y, boolean end) {
                this.x = x;
                this.y = y;
                this.end = end;
            }

            private List<Integer> getAsList() {
                List<Integer> list = new ArrayList<>();
                list.add(x);
                list.add(y);

                return list;
            }
        }
    }
}

//    218. The Skyline Problem
//    Hard
//    A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Given the locations and heights of all the buildings, return the skyline formed by these buildings collectively.
//
//    The geometric information of each building is given in the array buildings where buildings[i] = [lefti, righti, heighti]:
//
//    lefti is the x coordinate of the left edge of the ith building.
//    righti is the x coordinate of the right edge of the ith building.
//    heighti is the height of the ith building.
//    You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
//
//    The skyline should be represented as a list of "key points" sorted by their x-coordinate in the form [[x1,y1],[x2,y2],...]. Each key point is the left endpoint of some horizontal segment in the skyline except the last point in the list, which always has a y-coordinate 0 and is used to mark the skyline's termination where the rightmost building ends. Any ground between the leftmost and rightmost buildings should be part of the skyline's contour.
//
//    Note: There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...,[2 3],[4 5],[7 5],[11 5],[12 7],...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...,[2 3],[4 5],[12 7],...]
//
//
//
//    Example 1:
//
//
//    Input: buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
//    Output: [[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
//    Explanation:
//    Figure A shows the buildings of the input.
//    Figure B shows the skyline formed by those buildings. The red points in figure B represent the key points in the output list.
//    Example 2:
//
//    Input: buildings = [[0,2,3],[2,5,3]]
//    Output: [[0,3],[5,0]]
//
//
//    Constraints:
//
//    1 <= buildings.length <= 104
//    0 <= lefti < righti <= 231 - 1
//    1 <= heighti <= 231 - 1
//    buildings is sorted by lefti in non-decreasing order.