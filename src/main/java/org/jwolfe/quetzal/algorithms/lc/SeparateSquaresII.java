package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class SeparateSquaresII {
    class SegmentTree {

        private int[] count;
        private int[] covered;
        private int[] xs;
        private int n;

        public SegmentTree(int[] xs_) {
            xs = xs_;
            n = xs.length - 1;
            count = new int[4 * n];
            covered = new int[4 * n];
        }

        private void modify(
                int qleft,
                int qright,
                int qval,
                int left,
                int right,
                int pos
        ) {
            if (xs[right + 1] <= qleft || xs[left] >= qright) {
                return;
            }
            if (qleft <= xs[left] && xs[right + 1] <= qright) {
                count[pos] += qval;
            } else {
                int mid = (left + right) / 2;
                modify(qleft, qright, qval, left, mid, pos * 2 + 1);
                modify(qleft, qright, qval, mid + 1, right, pos * 2 + 2);
            }

            if (count[pos] > 0) {
                covered[pos] = xs[right + 1] - xs[left];
            } else {
                if (left == right) {
                    covered[pos] = 0;
                } else {
                    covered[pos] = covered[pos * 2 + 1] + covered[pos * 2 + 2];
                }
            }
        }

        public void update(int qleft, int qright, int qval) {
            modify(qleft, qright, qval, 0, n - 1, 0);
        }

        public int query() {
            return covered[0];
        }
    }

    class Solution {

        public double separateSquares(int[][] squares) {
            // save events: (y-coordinate, type, left boundary, right boundary)
            List<int[]> events = new ArrayList<>();
            Set<Integer> xsSet = new TreeSet<>();

            for (int[] sq : squares) {
                int x = sq[0];
                int y = sq[1];
                int l = sq[2];
                int xr = x + l;
                events.add(new int[]{y, 1, x, xr});
                events.add(new int[]{y + l, -1, x, xr});
                xsSet.add(x);
                xsSet.add(xr);
            }

            // sort events by y-coordinate
            events.sort((a, b) -> Integer.compare(a[0], b[0]));
            // discrete coordinates
            int[] xs = xsSet.stream().mapToInt(i -> i).toArray();
            // initialize the segment tree
            SegmentTree segTree = new SegmentTree(xs);

            List<Long> psum = new ArrayList<>();
            List<Integer> widths = new ArrayList<>();
            Long totalArea = 0L;
            int prev = events.get(0)[0];

            // scan: calculate total area and record intermediate states
            for (int[] event : events) {
                int y = event[0];
                int delta = event[1];
                int xl = event[2];
                int xr = event[3];
                int len = segTree.query();
                totalArea += (long) len * (y - prev);
                segTree.update(xl, xr, delta);
                // record prefix sums and widths
                psum.add(totalArea);
                widths.add(segTree.query());
                prev = y;
            }

            // calculate the target area (half rounded up)
            long target = (long) (totalArea + 1) / 2;
            // binary search
            int i = binarySearch(psum, target);
            double area = psum.get(i);
            // get the corresponding area, width, and height
            int width = widths.get(i);
            int height = events.get(i)[0];

            return height + (totalArea - area * 2) / (width * 2.0);
        }

        private int binarySearch(List<Long> list, long target) {
            int left = 0;
            int right = list.size() - 1;
            int result = 0;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (list.get(mid) < target) {
                    result = mid;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return result;
        }
    }

    class Solution_Incorrect_2 {

        public double separateSquares(int[][] squares) {
            if (squares == null || squares.length == 0) {
                return 0d;
            }

            List<int[]> events = new ArrayList<>();
            Set<Integer> xSet = new HashSet<>();

            for (int[] sq : squares) {
                int x1 = sq[0];
                int y1 = sq[1];
                int l = sq[2];

                int x2 = x1 + l;
                int y2 = y1 + l;

                events.add(new int[]{y1, 1, x1, x2});
                events.add(new int[]{y2, -1, x1, x2});

                xSet.add(x1);
                xSet.add(x2);
            }

            int[] xArr = new int[xSet.size()];
            int k = 0;
            for (int x : xSet) xArr[k++] = x;

            Arrays.sort(xArr);
            events.sort(Comparator.comparingInt(a -> a[0]));

            SegmentTree seg = new SegmentTree(xArr);

            List<Long> prefixArea = new ArrayList<>();
            List<Integer> widths = new ArrayList<>();
            List<Integer> yBase = new ArrayList<>();

            long totalArea = 0;
            int prevY = events.get(0)[0];

            for (int[] e : events) {
                int y = e[0];
                int type = e[1];
                int x1 = e[2];
                int x2 = e[3];

                int height = y - prevY;
                int width = seg.netCover();

                // Only record REAL vertical strips
                if (height > 0 && width > 0) {
                    totalArea += 1L * height * width;
                    prefixArea.add(totalArea);
                    widths.add(width);
                    yBase.add(prevY);
                }

                seg.update(x1, x2, type);
                prevY = y;
            }

            long target = (totalArea + 1) / 2;

            int idx = lowerBound(prefixArea, target);

            long areaBefore = idx == 0 ? 0 : prefixArea.get(idx - 1);
            long remaining = target - areaBefore;

            return yBase.get(idx) + remaining / (double) widths.get(idx);
        }

        private int lowerBound(List<Long> list, long target) {
            int l = 0, r = list.size() - 1;
            while (l < r) {
                int m = (l + r) / 2;
                if (list.get(m) < target) l = m + 1;
                else r = m;
            }
            return l;
        }

        private class SegmentTree {
            int[] x;
            int[] count;
            int[] cover;
            int n;

            SegmentTree(int[] x) {
                this.x = x;
                this.n = x.length - 1;
                count = new int[4 * n];
                cover = new int[4 * n];
            }

            int netCover() {
                return cover[0];
            }

            void update(int ql, int qr, int val) {
                modify(ql, qr, val, 0, n - 1, 0);
            }

            void modify(int ql, int qr, int val, int l, int r, int p) {
                if (x[r + 1] <= ql || x[l] >= qr) return;

                if (ql <= x[l] && x[r + 1] <= qr) {
                    count[p] += val;
                } else {
                    int m = (l + r) / 2;
                    modify(ql, qr, val, l, m, p * 2 + 1);
                    modify(ql, qr, val, m + 1, r, p * 2 + 2);
                }

                if (count[p] > 0) {
                    cover[p] = x[r + 1] - x[l];
                } else if (l == r) {
                    cover[p] = 0;
                } else {
                    cover[p] = cover[p * 2 + 1] + cover[p * 2 + 2];
                }
            }
        }
    }

    class Solution_Incorrect {
        public double separateSquares(int[][] squares) {
            if (squares == null || squares.length == 0) {
                return 0d;
            }

            List<int[]> events = new ArrayList<>();
            Set<Integer> xSet = new HashSet<>();

            for (int[] square : squares) {
                int x1 = square[0];
                int y1 = square[1];
                int l = square[2];

                int x2 = x1 + l;
                int y2 = y1 + l;

                int[] event1 = new int[]{y1, 1, x1, x2};
                int[] event2 = new int[]{y2, -1, x1, x2};

                events.add(event1);
                events.add(event2);

                xSet.add(x1);
                xSet.add(x2);
            }

            int[] xArr = new int[xSet.size()];
            int xi = 0;
            for (int x : xSet) {
                xArr[xi++] = x;
            }

            Collections.sort(events, (a, b) -> Integer.compare(a[0], b[0]));
            Arrays.sort(xArr);

            SegmentTree segmentTree = new SegmentTree(xArr);
            List<Long> pSum = new ArrayList<>();
            List<Integer> widths = new ArrayList<>();

            long totalArea = 0L;
            int py = events.get(0)[0];
            for (int[] event : events) {
                int cy = event[0];
                int t = event[1];
                int x1 = event[2];
                int x2 = event[3];

                int h = cy - py;
                int w = segmentTree.netCover();

                long area = 1L * h * w;
                totalArea += area;

                pSum.add(totalArea);
                widths.add(w);

                segmentTree.update(x1, x2, t);
                py = cy;
            }

            long target = (long) (totalArea + 2) / 2;

            int i = binarySearch(pSum, target);
            double area = pSum.get(i);

            int width = widths.get(i);
            int height = events.get(i)[0];

            return height + (totalArea - 2 * area) / (width * 2.0);
        }

        private int binarySearch(List<Long> list, long target) {
            int l = 0, r = list.size() - 1;
            while (l < r) {
                int m = (l + r) / 2;
                if (list.get(m) < target) l = m + 1;
                else r = m;
            }
            return l;
        }

        private class SegmentTree {
            int[] xArr;
            int[] count;
            int[] cover;
            int n;

            public SegmentTree(int[] xArr) {
                this.xArr = xArr;

                this.n = xArr.length - 1;
                this.count = new int[4 * this.n];
                this.cover = new int[4 * this.n];
            }

            public int netCover() {
                return cover[0];
            }

            public void update(int qLeft, int qRight, int qVal) {
                modify(qLeft, qRight, qVal, 0, n - 1, 0);
            }

            private void modify(int qLeft, int qRight, int qVal, int left, int right, int pos) {
                if (xArr[right + 1] <= qLeft || xArr[left] >= qRight) {
                    return;
                }

                if (qLeft <= xArr[left] && xArr[right + 1] <= qRight) {
                    count[pos] += qVal;
                } else {
                    int mid = (left + right) / 2;
                    modify(qLeft, qRight, qVal, left, mid, pos * 2 + 1);
                    modify(qLeft, qRight, qVal, mid + 1, right, pos * 2 + 2);
                }

                if (count[pos] > 0) {
                    cover[pos] = xArr[right + 1] - xArr[left];
                } else {
                    if (left == right) {
                        cover[pos] = 0;
                    } else {
                        cover[pos] = cover[pos * 2 + 1] + cover[pos * 2 + 2];
                    }
                }
            }
        }
    }
}

//    3454. Separate Squares II
//    Hard
//    You are given a 2D integer array squares. Each squares[i] = [xi, yi, li] represents the coordinates of the bottom-left point and the side length of a square parallel to the x-axis.
//
//    Find the minimum y-coordinate value of a horizontal line such that the total area covered by squares above the line equals the total area covered by squares below the line.
//
//    Answers within 10-5 of the actual answer will be accepted.
//
//    Note: Squares may overlap. Overlapping areas should be counted only once in this version.
//
//
//
//    Example 1:
//
//    Input: squares = [[0,0,1],[2,2,1]]
//
//    Output: 1.00000
//
//    Explanation:
//
//
//
//    Any horizontal line between y = 1 and y = 2 results in an equal split, with 1 square unit above and 1 square unit below. The minimum y-value is 1.
//
//    Example 2:
//
//    Input: squares = [[0,0,2],[1,1,1]]
//
//    Output: 1.00000
//
//    Explanation:
//
//
//
//    Since the blue square overlaps with the red square, it will not be counted again. Thus, the line y = 1 splits the squares into two equal parts.
//
//
//
//    Constraints:
//
//    1 <= squares.length <= 5 * 104
//    squares[i] = [xi, yi, li]
//    squares[i].length == 3
//    0 <= xi, yi <= 109
//    1 <= li <= 109
//    The total area of all the squares will not exceed 1015.