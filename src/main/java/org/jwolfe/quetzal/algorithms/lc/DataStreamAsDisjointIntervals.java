package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

public class DataStreamAsDisjointIntervals {
    class SummaryRanges {
        TreeMap<Integer, Integer> stream;

        public SummaryRanges() {
            stream = new TreeMap<>();
        }

        public void addNum(int value) {
            int start = value;
            int end = value;

            var lowerKey = stream.floorKey(value);
            if(lowerKey != null) {
                int end2 = stream.get(lowerKey);
                if(end2 >= value) {
                    return;
                }

                if(value - end2 == 1) {
                    start = lowerKey;
                }
            }

            if(stream.containsKey(value + 1)) {
                end = stream.get(value + 1);
                stream.remove(value + 1);
            }

            stream.put(start, end);
        }

        public int[][] getIntervals() {
            int[][] results = new int[stream.size()][2];
            int index = 0;
            for(var entry : stream.entrySet()) {
                results[index][0] = entry.getKey();
                results[index][1] = entry.getValue();

                index++;
            }

            return results;
        }
    }

    class SummaryRanges_Correct_2 {
        TreeMap<Integer, Integer> stream;

        public SummaryRanges_Correct_2() {
            stream = new TreeMap<>();
        }

        public void addNum(int value) {
            var start1 = stream.floorKey(value);

            if(start1 == null) {
                if(stream.containsKey(value + 1)) {
                    // merge intervals
                    int end2 = stream.get(value + 1);

                    stream.remove(value + 1);
                    stream.put(value, end2);
                } else {
                    // new interval
                    stream.put(value, value);
                }
            } else {
                int end1 = stream.get(start1);

                if(end1 >= value) {
                    // already in interval
                    return;
                } else if(value - end1 == 1) {
                    if(stream.containsKey(value + 1)) {
                        // merge intervals
                        int end2 = stream.get(value + 1);

                        stream.remove(start1);
                        stream.remove(value + 1);

                        stream.put(start1, end2);
                    } else {
                        // extend interval
                        stream.put(start1, value);
                    }
                } else {
                    if(stream.containsKey(value + 1)) {
                        // merge intervals
                        int end2 = stream.get(value + 1);

                        stream.remove(value + 1);
                        stream.put(value, end2);
                    } else {
                        // new interval
                        stream.put(value, value);
                    }
                }
            }
        }

        public int[][] getIntervals() {
            int[][] results = new int[stream.size()][2];
            int index = 0;
            for(var entry : stream.entrySet()) {
                results[index][0] = entry.getKey();
                results[index][1] = entry.getValue();

                index++;
            }

            return results;
        }
    }

    class SummaryRanges_Correct_1 {
        TreeSet<Integer> stream;

        public SummaryRanges_Correct_1() {
            stream = new TreeSet<>();
        }

        public void addNum(int value) {
            stream.add(value);
        }

        public int[][] getIntervals() {
            List<int[]> intervals = new ArrayList<>();

            int start = -1;
            int end = -1;

            for(var element : stream) {
                if(start == -1) {
                    start = element;
                    end = element;
                } else if(element - end == 1) {
                    end = element;
                } else {
                    intervals.add(new int[] {start, end});
                    start = element;
                    end = element;
                }
            }

            intervals.add(new int[] {start, end});

            int[][] results = new int[intervals.size()][2];
            for(int i = 0; i < intervals.size(); i++) {
                results[i] = intervals.get(i);
            }

            return results;
        }
    }

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(value);
 * int[][] param_2 = obj.getIntervals();
 */

// ["SummaryRanges","addNum","getIntervals","addNum","getIntervals","addNum","getIntervals","addNum","getIntervals","addNum","getIntervals"]
// [[],[1],[],[3],[],[7],[],[2],[],[6],[]]

// ["SummaryRanges","addNum","getIntervals","addNum","getIntervals","addNum","getIntervals"]
// [[],[1],[],[9],[],[2],[]]
}

//    352. Data Stream as Disjoint Intervals
//    Hard
//    Given a data stream input of non-negative integers a1, a2, ..., an, summarize the numbers seen so far as a list of disjoint intervals.
//
//    Implement the SummaryRanges class:
//
//    SummaryRanges() Initializes the object with an empty stream.
//    void addNum(int value) Adds the integer value to the stream.
//    int[][] getIntervals() Returns a summary of the integers in the stream currently as a list of disjoint intervals [starti, endi]. The answer should be sorted by starti.
//
//
//    Example 1:
//
//    Input
//    ["SummaryRanges", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals"]
//    [[], [1], [], [3], [], [7], [], [2], [], [6], []]
//    Output
//    [null, null, [[1, 1]], null, [[1, 1], [3, 3]], null, [[1, 1], [3, 3], [7, 7]], null, [[1, 3], [7, 7]], null, [[1, 3], [6, 7]]]
//
//    Explanation
//    SummaryRanges summaryRanges = new SummaryRanges();
//    summaryRanges.addNum(1);      // arr = [1]
//    summaryRanges.getIntervals(); // return [[1, 1]]
//    summaryRanges.addNum(3);      // arr = [1, 3]
//    summaryRanges.getIntervals(); // return [[1, 1], [3, 3]]
//    summaryRanges.addNum(7);      // arr = [1, 3, 7]
//    summaryRanges.getIntervals(); // return [[1, 1], [3, 3], [7, 7]]
//    summaryRanges.addNum(2);      // arr = [1, 2, 3, 7]
//    summaryRanges.getIntervals(); // return [[1, 3], [7, 7]]
//    summaryRanges.addNum(6);      // arr = [1, 2, 3, 6, 7]
//    summaryRanges.getIntervals(); // return [[1, 3], [6, 7]]
//
//
//    Constraints:
//
//    0 <= value <= 104
//    At most 3 * 104 calls will be made to addNum and getIntervals.
//
//
//    Follow up: What if there are lots of merges and the number of disjoint intervals is small compared to the size of the data stream?