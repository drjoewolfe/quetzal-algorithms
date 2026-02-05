package org.jwolfe.quetzal.algorithms.lc;

import java.util.PriorityQueue;

public class MinimumPairRemovalToSortArrayII {
    public class Solution {

        public int minimumPairRemoval(int[] nums) {
            PriorityQueue<PQItem> pq = new PriorityQueue<>();
            boolean[] merged = new boolean[nums.length];

            int decreaseCount = 0;
            int count = 0;
            Node head = new Node(nums[0], 0);
            Node current = head;

            for (int i = 1; i < nums.length; i++) {
                Node newNode = new Node(nums[i], i);
                current.next = newNode;
                newNode.prev = current;
                pq.offer(
                        new PQItem(current, newNode, current.value + newNode.value)
                );
                if (nums[i - 1] > nums[i]) {
                    decreaseCount++;
                }
                current = newNode;
            }

            while (decreaseCount > 0) {
                PQItem item = pq.poll();
                Node first = item.first;
                Node second = item.second;
                long cost = item.cost;

                if (
                        merged[first.left] ||
                                merged[second.left] ||
                                first.value + second.value != cost
                ) {
                    continue;
                }
                count++;
                if (first.value > second.value) {
                    decreaseCount--;
                }

                Node prevNode = first.prev;
                Node nextNode = second.next;
                first.next = nextNode;
                if (nextNode != null) {
                    nextNode.prev = first;
                }

                if (prevNode != null) {
                    if (prevNode.value > first.value && prevNode.value <= cost) {
                        decreaseCount--;
                    } else if (
                            prevNode.value <= first.value && prevNode.value > cost
                    ) {
                        decreaseCount++;
                    }

                    pq.offer(new PQItem(prevNode, first, prevNode.value + cost));
                }

                if (nextNode != null) {
                    if (second.value > nextNode.value && cost <= nextNode.value) {
                        decreaseCount--;
                    } else if (
                            second.value <= nextNode.value && cost > nextNode.value
                    ) {
                        decreaseCount++;
                    }

                    pq.offer(new PQItem(first, nextNode, cost + nextNode.value));
                }

                first.value = cost;
                merged[second.left] = true;
            }

            return count;
        }

        class Node {

            long value;
            int left;
            Node prev;
            Node next;

            Node(int value, int left) {
                this.value = value;
                this.left = left;
            }
        }

        class PQItem implements Comparable<PQItem> {

            Node first;
            Node second;
            long cost;

            PQItem(Node first, Node second, long cost) {
                this.first = first;
                this.second = second;
                this.cost = cost;
            }

            @Override
            public int compareTo(PQItem other) {
                if (this.cost == other.cost) {
                    return this.first.left - other.first.left;
                }
                return this.cost < other.cost ? -1 : 1;
            }
        }
    }

    class Solution_Incorrect_2 {
        public int minimumPairRemoval(int[] nums) {
            if (nums == null || nums.length < 2) return 0;

            int n = nums.length;
            int idCounter = 0;

            // Doubly linked list
            Node head = new Node(nums[0], idCounter++);
            Node prev = head;

            PriorityQueue<Pair> heap = new PriorityQueue<>();
            int decreasing = 0;

            for (int i = 1; i < n; i++) {
                Node cur = new Node(nums[i], idCounter++);
                prev.next = cur;
                cur.prev = prev;

                heap.offer(new Pair(prev, cur));
                if (prev.val > cur.val) decreasing++;

                prev = cur;
            }

            int ops = 0;

            while (decreasing > 0) {
                Pair p = heap.poll();
                if (p == null) break;

                Node a = p.left;
                Node b = p.right;

                // stale heap entry
                if (a.next != b || b.prev != a) continue;

                ops++;

                // remove old inversions
                if (a.val > b.val) decreasing--;

                Node left = a.prev;
                Node right = b.next;

                if (left != null && left.val > a.val) decreasing--;
                if (right != null && b.val > right.val) decreasing--;

                // merge
                Node mid = new Node(a.val + b.val, idCounter++);

                mid.prev = left;
                mid.next = right;

                if (left != null) {
                    left.next = mid;
                    heap.offer(new Pair(left, mid));
                    if (left.val > mid.val) decreasing++;
                }

                if (right != null) {
                    right.prev = mid;
                    heap.offer(new Pair(mid, right));
                    if (mid.val > right.val) decreasing++;
                }
            }

            return ops;
        }

        /* ---------- Helper Classes ---------- */

        class Node {
            long val;
            int id;
            Node prev, next;

            Node(long v, int i) {
                val = v;
                id = i;
            }
        }

        class Pair implements Comparable<Pair> {
            Node left, right;
            long sum;

            Pair(Node l, Node r) {
                left = l;
                right = r;
                sum = l.val + r.val;
            }

            @Override
            public int compareTo(Pair o) {
                if (this.sum != o.sum) {
                    return Long.compare(this.sum, o.sum);
                }
                return this.left.id - o.left.id;
            }
        }
    }

    class Solution_Incorrect {
        public int minimumPairRemoval(int[] nums) {
            if (nums == null || nums.length < 2) {
                return 0;
            }

            int n = nums.length;

            PriorityQueue<PQItem> heap = new PriorityQueue<>();
            boolean[] merged = new boolean[n];

            int decreasingCount = 0;

            Node head = new Node(nums[0], 0);
            Node prev = head;
            for (int i = 1; i < n; i++) {
                Node curr = new Node(nums[i], i);
                prev.next = curr;
                curr.prev = prev;

                heap.offer(new PQItem(prev, curr, prev.value + curr.value));
                if (prev.value > curr.value) {
                    decreasingCount++;
                }

                prev = curr;
            }

            int minOperations = 0;
            int nodeIndex = n;
            while (decreasingCount > 0) {
                System.out.println(decreasingCount + ", " + heap.size());
                PQItem item = heap.poll();
                Node first = item.first;
                Node second = item.second;
                long sum = item.sum;

                if (merged[first.index]
                        || merged[second.index]
                        || first.value + second.value != sum) {
                    continue;
                }

                minOperations++;
                if (first.value > second.value) {
                    decreasingCount--;
                }

                prev = first.prev;
                Node next = second.next;

                Node mid = new Node(sum, nodeIndex++);
                mid.prev = prev;
                mid.next = next;

                if (prev != null) {
                    if (prev.value > first.value && prev.value < sum) {
                        decreasingCount--;
                    } else if (prev.value <= first.value && prev.value > sum) {
                        decreasingCount++;
                    }

                    prev.next = mid;
                    heap.offer(new PQItem(prev, mid, prev.value + mid.value));
                }

                if (next != null) {
                    if (second.value > next.value && sum <= next.value) {
                        decreasingCount--;
                    } else if (second.value <= next.value && sum >= next.value) {
                        decreasingCount++;
                    }

                    next.prev = mid;
                    heap.offer(new PQItem(mid, next, mid.value + next.value));
                }

                merged[first.index] = true;
                merged[second.index] = true;
            }

            return minOperations;
        }

        private class PQItem implements Comparable<PQItem> {
            Node first;
            Node second;
            long sum;

            public PQItem(Node first, Node second, long sum) {
                this.first = first;
                this.second = second;
                this.sum = sum;
            }

            @Override
            public int compareTo(PQItem other) {
                if (this.sum == other.sum) {
                    return this.first.index - other.first.index;
                }

                return this.sum < other.sum ? -1 : 1;
            }
        }

        private class Node {
            long value;
            int index;
            Node prev;
            Node next;

            public Node(long value, int index) {
                this.value = value;
                this.index = index;
            }
        }
    }
}

//    3510. Minimum Pair Removal to Sort Array II
//    Hard
//    Given an array nums, you can perform the following operation any number of times:
//
//    Select the adjacent pair with the minimum sum in nums. If multiple such pairs exist, choose the leftmost one.
//    Replace the pair with their sum.
//    Return the minimum number of operations needed to make the array non-decreasing.
//
//    An array is said to be non-decreasing if each element is greater than or equal to its previous element (if it exists).
//
//
//
//    Example 1:
//
//    Input: nums = [5,2,3,1]
//
//    Output: 2
//
//    Explanation:
//
//    The pair (3,1) has the minimum sum of 4. After replacement, nums = [5,2,4].
//    The pair (2,4) has the minimum sum of 6. After replacement, nums = [5,6].
//    The array nums became non-decreasing in two operations.
//
//    Example 2:
//
//    Input: nums = [1,2,2]
//
//    Output: 0
//
//    Explanation:
//
//    The array nums is already sorted.
//
//
//
//    Constraints:
//
//    1 <= nums.length <= 105
//    -109 <= nums[i] <= 109