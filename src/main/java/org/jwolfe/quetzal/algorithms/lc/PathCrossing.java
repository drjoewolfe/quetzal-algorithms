package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashSet;
import java.util.Set;

public class PathCrossing {
    class Solution {
        public boolean isPathCrossing(String path) {
            if(path == null || path.length() == 0) {
                return false;
            }

            int x = 0;
            int y = 0;

            Set<Point> set = new HashSet<>();
            set.add(new Point(x, y));
            for(int i = 0; i < path.length(); i++) {
                char c = path.charAt(i);

                if(c == 'N') {
                    y++;
                } else if(c == 'S') {
                    y--;
                } else if(c == 'E') {
                    x++;
                } else if(c == 'W') {
                    x--;
                }

                Point point = new Point(x, y);
                if(set.contains(point)) {
                    return true;
                }

                set.add(point);
            }

            return false;
        }

        private class Point {
            int x;
            int y;

            public Point(int x, int y) {
                this.x = x;
                this.y = y;
            }

            @Override
            public boolean equals(Object other) {
                if(other instanceof Point) {
                    Point point = (Point) other;
                    return (this.x == point.x)
                            && (this.y == point.y);
                }

                return false;
            }

            @Override
            public int hashCode() {
                int hash = 31;

                hash *= 7;
                hash += this.x;

                hash *= 7;
                hash += this.y;

                return hash;
            }
        }
    }

    class Solution_Correct_1 {
        public boolean isPathCrossing(String path) {
            if(path == null || path.length() < 2) {
                return false;
            }

            Set<Point> set = new HashSet<>();

            int x = 0;
            int y = 0;

            set.add(new Point(x, y));

            for(int i = 0; i < path.length(); i++) {
                char c = path.charAt(i);

                switch(c) {
                    case 'N': x++;
                        break;
                    case 'S': x--;
                        break;
                    case 'E': y++;
                        break;
                    case 'W': y--;
                        break;
                }

                Point p = new Point(x, y);
                if(set.contains(p)) {
                    return true;
                }

                set.add(p);
            }

            return false;
        }

        class Point {
            int x;
            int y;

            public Point(int x, int y) {
                this.x = x;
                this.y = y;
            }

            @Override
            public boolean equals(Object other) {
                if(other instanceof Point) {
                    Point o = (Point) other;
                    return this.x == o.x && this.y == o.y;
                }

                return false;
            }

            @Override
            public int hashCode() {
                int hash = 7;
                hash = 31 * hash + x;
                hash = 31 * hash + y;

                return hash;
            }
        }
    }

// "NES"
// "NNSWWEWSSESSWENNW"
}

//    1496. Path Crossing
//    Easy
//    Given a string path, where path[i] = 'N', 'S', 'E' or 'W', each representing moving one unit north, south, east, or west, respectively. You start at the origin (0, 0) on a 2D plane and walk on the path specified by path.
//
//    Return true if the path crosses itself at any point, that is, if at any time you are on a location you have previously visited. Return false otherwise.
//
//
//
//    Example 1:
//
//
//    Input: path = "NES"
//    Output: false
//    Explanation: Notice that the path doesn't cross any point more than once.
//    Example 2:
//
//
//    Input: path = "NESWW"
//    Output: true
//    Explanation: Notice that the path visits the origin twice.
//
//
//    Constraints:
//
//    1 <= path.length <= 104
//    path[i] is either 'N', 'S', 'E', or 'W'.