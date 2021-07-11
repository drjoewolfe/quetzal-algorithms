package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class FindMedianFromDataStream {
    class MedianFinder {

        PriorityQueue<Integer> maxHeap;
        PriorityQueue<Integer> minHeap;

        /** initialize your data structure here. */
        public MedianFinder() {
            maxHeap = new PriorityQueue<>((a, b) -> b - a);
            minHeap = new PriorityQueue<>();
        }

        public void addNum(int num) {
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());

            int m = maxHeap.size();
            int n = minHeap.size();

            if(n > m) {
                maxHeap.offer(minHeap.poll());
            }
        }

        public double findMedian() {
            int m = maxHeap.size();
            int n = minHeap.size();

            int l = m + n;
            // System.out.println(m + ", " + n + ", " + l);

            if(l % 2 == 0) {
                return (maxHeap.peek() + minHeap.peek()) / 2.0;
            } else {
                return maxHeap.peek();
            }
        }
    }

    /**
     * Your MedianFinder object will be instantiated and called as such:
     * MedianFinder obj = new MedianFinder();
     * obj.addNum(num);
     * double param_2 = obj.findMedian();
     */

    class MedianFinder_InsertionSort {
        List<Integer> list;

        /** initialize your data structure here. */
        public MedianFinder_InsertionSort() {
            list = new ArrayList<>();
        }

        public void addNum(int num) {
            list.add(num);

            int i = list.size() - 1;
            for(int j = i - 1; j >= 0; j--) {
                if(list.get(j) > list.get(i)) {
                    swap(i, j);
                    i = j;
                }
            }
        }

        public double findMedian() {
            int n = list.size();
            if(n % 2 == 0) {
                return (list.get(n/2 - 1) + list.get(n/2)) / 2.0;
            } else {
                return list.get(n/2);
            }
        }

        private void swap(int i, int j) {
            int temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }
    }

    class MedianFinder_CollectionSort {

        List<Integer> list;

        /** initialize your data structure here. */
        public MedianFinder_CollectionSort() {
            list = new ArrayList<>();
        }

        public void addNum(int num) {
            list.add(num);
            Collections.sort(list);
        }

        public double findMedian() {
            int n = list.size();
            if(n % 2 == 0) {
                return (list.get(n/2 - 1) + list.get(n/2)) / 2.0;
            } else {
                return list.get(n/2);
            }
        }
    }

    class MedianFinder_Correct_2 {
        PriorityQueue<Integer> maxHeap;
        PriorityQueue<Integer> minHeap;

        /** initialize your data structure here. */
        public MedianFinder_Correct_2() {
            maxHeap = new PriorityQueue<>((a, b) -> b - a);
            minHeap = new PriorityQueue<>();
        }

        public void addNum(int num) {
            if(!minHeap.isEmpty() && minHeap.peek() < num) {
                minHeap.offer(num);
            } else {
                maxHeap.offer(num);
            }

            // Balance the heaps
            int m = maxHeap.size();
            int n = minHeap.size();

            if(m == n + 2) {
                minHeap.offer(maxHeap.poll());
            } else if(m < n) {
                maxHeap.offer(minHeap.poll());
            }
        }

        public double findMedian() {
            int m = maxHeap.size();
            int n = minHeap.size();

            int l = m + n;
            if(l % 2 == 0) {
                return (maxHeap.peek() + minHeap.peek()) / 2.0;
            } else {
                return maxHeap.peek();
            }
        }
    }

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
}

//    295. Find Median from Data Stream
//    Hard
//    The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value and the median is the mean of the two middle values.
//
//    For example, for arr = [2,3,4], the median is 3.
//    For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
//    Implement the MedianFinder class:
//
//    MedianFinder() initializes the MedianFinder object.
//    void addNum(int num) adds the integer num from the data stream to the data structure.
//    double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.
//
//
//    Example 1:
//
//    Input
//    ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
//    [[], [1], [2], [], [3], []]
//    Output
//    [null, null, null, 1.5, null, 2.0]
//
//    Explanation
//    MedianFinder medianFinder = new MedianFinder();
//    medianFinder.addNum(1);    // arr = [1]
//    medianFinder.addNum(2);    // arr = [1, 2]
//    medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
//    medianFinder.addNum(3);    // arr[1, 2, 3]
//    medianFinder.findMedian(); // return 2.0
//
//
//    Constraints:
//
//    -105 <= num <= 105
//    There will be at least one element in the data structure before calling findMedian.
//    At most 5 * 104 calls will be made to addNum and findMedian.
//
//
//    Follow up:
//
//    If all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
//    If 99% of all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?