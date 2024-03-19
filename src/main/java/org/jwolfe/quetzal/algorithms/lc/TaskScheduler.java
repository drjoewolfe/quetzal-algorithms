package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class TaskScheduler {
    class Solution {
        public int leastInterval(char[] tasks, int n) {
            if(tasks == null || tasks.length == 0) {
                return 0;
            }

            Map<Character, Integer> frequencies = new HashMap<>();
            for(char t : tasks) {
                frequencies.put(t, frequencies.getOrDefault(t, 0) + 1);
            }

            PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b - a);
            heap.addAll(frequencies.values());

            int slots = 0;
            List<Integer> scheduledTasks = new ArrayList<>();
            while(!heap.isEmpty()) {
                scheduledTasks.clear();

                for(int i = 0; i < n + 1 && !heap.isEmpty(); i++) {
                    scheduledTasks.add(heap.poll());
                }

                for(int taskCount : scheduledTasks) {
                    if(--taskCount > 0) {
                        heap.offer(taskCount);
                    }
                }

                slots += heap.isEmpty() ? scheduledTasks.size() : n + 1;
            }

            return slots;
        }
    }

    class Solution_Correct_1 {
        public int leastInterval(char[] tasks, int n) {
            if(tasks == null || tasks.length == 0) {
                return 0;
            }

            Map<Character, Integer> frequencies = new HashMap<>();
            for(char t : tasks) {
                frequencies.put(t, frequencies.getOrDefault(t, 0) + 1);
            }

            PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
            maxHeap.addAll(frequencies.values());

            int totalCycles = 0;
            List<Integer> scheduledTasks = new ArrayList<>();
            while(!maxHeap.isEmpty()) {
                scheduledTasks.clear();
                for(int i = 0; i < n + 1; i++) {
                    if(!maxHeap.isEmpty()) {
                        scheduledTasks.add(maxHeap.remove());
                    }
                }

                for(int a : scheduledTasks) {
                    if(--a > 0) {
                        maxHeap.add(a);
                    }
                }

                totalCycles += maxHeap.isEmpty() ? scheduledTasks.size() : n + 1;
            }

            return totalCycles;
        }
    }

// ["A","A","A","B","B","B"]
// 2
}

//    621. Task Scheduler
//    Medium
//    You are given an array of CPU tasks, each represented by letters A to Z, and a cooling time, n. Each cycle or interval allows the completion of one task. Tasks can be completed in any order, but there's a constraint: identical tasks must be separated by at least n intervals due to cooling time.
//
//    ​Return the minimum number of intervals required to complete all tasks.
//
//
//
//    Example 1:
//
//    Input: tasks = ["A","A","A","B","B","B"], n = 2
//
//    Output: 8
//
//    Explanation: A possible sequence is: A -> B -> idle -> A -> B -> idle -> A -> B.
//
//    After completing task A, you must wait two cycles before doing A again. The same applies to task B. In the 3rd interval, neither A nor B can be done, so you idle. By the 4th cycle, you can do A again as 2 intervals have passed.
//
//    Example 2:
//
//    Input: tasks = ["A","C","A","B","D","B"], n = 1
//
//    Output: 6
//
//    Explanation: A possible sequence is: A -> B -> C -> D -> A -> B.
//
//    With a cooling interval of 1, you can repeat a task after just one other task.
//
//    Example 3:
//
//    Input: tasks = ["A","A","A", "B","B","B"], n = 3
//
//    Output: 10
//
//    Explanation: A possible sequence is: A -> B -> idle -> idle -> A -> B -> idle -> idle -> A -> B.
//
//    There are only two types of tasks, A and B, which need to be separated by 3 intervals. This leads to idling twice between repetitions of these tasks.
//
//
//
//    Constraints:
//
//    1 <= tasks.length <= 104
//    tasks[i] is an uppercase English letter.
//    0 <= n <= 100