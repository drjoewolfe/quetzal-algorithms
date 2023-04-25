package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

public class  SmallestNumberInInfiniteSet {
    class SmallestInfiniteSet {
        PriorityQueue<Integer> heap;
        Set<Integer> set;
        int nextSmallest;

        public SmallestInfiniteSet() {
            nextSmallest = 1;
            heap = new PriorityQueue<>();
            set = new HashSet<>();
        }

        public int popSmallest() {
            if(!heap.isEmpty()) {
                var val = heap.poll();
                set.remove(val);
                return val;
            }

            return nextSmallest++;
        }

        public void addBack(int num) {
            if(num < nextSmallest
                    && !set.contains(num)) {
                heap.offer(num);
                set.add(num);
            }
        }
    }

    class SmallestInfiniteSet_Correct_1 {

        int nextSmallestNumber;
        TreeSet<Integer> addedNumbers;

        public SmallestInfiniteSet_Correct_1() {
            nextSmallestNumber = 1;
            addedNumbers = new TreeSet<>();
        }

        public int popSmallest() {
            if(addedNumbers.size() != 0) {
                int smallest = addedNumbers.first();
                addedNumbers.remove(smallest);

                return smallest;
            }

            return nextSmallestNumber++;
        }

        public void addBack(int num) {
            if(num >= nextSmallestNumber) {
                return;
            }

            addedNumbers.add(num);
        }
    }

/**
 * Your SmallestInfiniteSet object will be instantiated and called as such:
 * SmallestInfiniteSet obj = new SmallestInfiniteSet();
 * int param_1 = obj.popSmallest();
 * obj.addBack(num);
 */

// ["SmallestInfiniteSet","addBack","popSmallest","popSmallest","popSmallest","addBack","popSmallest","popSmallest","popSmallest"]
// [[],[2],[],[],[],[1],[],[],[]]
}

//    2336. Smallest Number in Infinite Set
//    Medium
//    You have a set which contains all positive integers [1, 2, 3, 4, 5, ...].
//
//    Implement the SmallestInfiniteSet class:
//
//    SmallestInfiniteSet() Initializes the SmallestInfiniteSet object to contain all positive integers.
//    int popSmallest() Removes and returns the smallest integer contained in the infinite set.
//    void addBack(int num) Adds a positive integer num back into the infinite set, if it is not already in the infinite set.
//
//
//    Example 1:
//
//    Input
//    ["SmallestInfiniteSet", "addBack", "popSmallest", "popSmallest", "popSmallest", "addBack", "popSmallest", "popSmallest", "popSmallest"]
//    [[], [2], [], [], [], [1], [], [], []]
//    Output
//    [null, null, 1, 2, 3, null, 1, 4, 5]
//
//    Explanation
//    SmallestInfiniteSet smallestInfiniteSet = new SmallestInfiniteSet();
//    smallestInfiniteSet.addBack(2);    // 2 is already in the set, so no change is made.
//    smallestInfiniteSet.popSmallest(); // return 1, since 1 is the smallest number, and remove it from the set.
//    smallestInfiniteSet.popSmallest(); // return 2, and remove it from the set.
//    smallestInfiniteSet.popSmallest(); // return 3, and remove it from the set.
//    smallestInfiniteSet.addBack(1);    // 1 is added back to the set.
//    smallestInfiniteSet.popSmallest(); // return 1, since 1 was added back to the set and
//    // is the smallest number, and remove it from the set.
//    smallestInfiniteSet.popSmallest(); // return 4, and remove it from the set.
//    smallestInfiniteSet.popSmallest(); // return 5, and remove it from the set.
//
//
//    Constraints:
//
//    1 <= num <= 1000
//    At most 1000 calls will be made in total to popSmallest and addBack.