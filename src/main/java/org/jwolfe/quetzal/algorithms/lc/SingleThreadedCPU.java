package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class SingleThreadedCPU {
    class Solution {
        public int[] getOrder(int[][] tasks) {
            if(tasks == null || tasks.length == 0) {
                return new int[0];
            }

            int n = tasks.length;

            List<Task> allTasks = new ArrayList<>();
            for(int i = 0; i < n; i++) {
                allTasks.add(new Task(tasks[i][0], tasks[i][1], i));
            }

            Collections.sort(allTasks, (a, b) -> a.enqueueTime - b.enqueueTime);
            PriorityQueue<Task> heap = new PriorityQueue<>((a, b) -> {
                if(a.processingTime == b.processingTime) {
                    return a.index - b.index;
                }

                return a.processingTime - b.processingTime;
            });

            int[] order = new int[n];
            int index = 0;
            int taskIndex = 0;
            int time = 0;

            while(taskIndex < n || !heap.isEmpty()) {
                if(heap.isEmpty()
                        && time < allTasks.get(taskIndex).enqueueTime) {
                    time = allTasks.get(taskIndex).enqueueTime;
                }

                while(taskIndex < n && time >= allTasks.get(taskIndex).enqueueTime) {
                    heap.offer(allTasks.get(taskIndex++));
                }

                Task task = heap.poll();
                order[index++] = task.index;
                time += task.processingTime;
            }

            return order;
        }

        private class Task {
            int enqueueTime;
            int processingTime;
            int index;

            public Task(int enqueueTime, int processingTime, int index) {
                this.enqueueTime = enqueueTime;
                this.processingTime = processingTime;
                this.index = index;
            }
        }
    }

    class Solution_Correct_TLE {
        public int[] getOrder(int[][] tasks) {
            if(tasks == null || tasks.length == 0) {
                return new int[0];
            }

            int n = tasks.length;
            Set<Integer> unpickedTasks = new TreeSet<>();
            int time = Integer.MAX_VALUE;
            for(int i = 0; i < n; i++) {
                unpickedTasks.add(i);
                time = Math.min(time, tasks[i][0]);
            }

            Set<Integer> availableTasks = new TreeSet<>();
            int[] order = new int[n];
            for(int i = 0; i < n; i++) {
                int nextEarliestTaskEnqueueTime = Integer.MAX_VALUE;

                // Update available tasks
                for(int index : unpickedTasks) {
                    if(tasks[index][0] <= time) {
                        availableTasks.add(index);
                    }

                    if(nextEarliestTaskEnqueueTime > tasks[index][0]) {
                        nextEarliestTaskEnqueueTime = tasks[index][0];
                    }
                }

                // Update unpicked tasks
                // Choose from available tasks
                int minProcessingTime = Integer.MAX_VALUE;
                int taskWithMinProcessingTime = -1;
                for(int index : availableTasks) {
                    unpickedTasks.remove(index);

                    if(minProcessingTime > tasks[index][1]) {
                        minProcessingTime = tasks[index][1];
                        taskWithMinProcessingTime = index;
                    }
                }

                order[i] = taskWithMinProcessingTime;
                availableTasks.remove(taskWithMinProcessingTime);

                if(availableTasks.size() > 0) {
                    time += tasks[taskWithMinProcessingTime][1];
                } else {
                    time = Math.max(time + tasks[taskWithMinProcessingTime][1], nextEarliestTaskEnqueueTime);
                }
            }

            return order;
        }
    }

    class Solution_InCorrect {
        public int[] getOrder(int[][] tasks) {
            if(tasks == null || tasks.length == 0) {
                return new int[0];
            }

            PriorityQueue<Integer> heap = new PriorityQueue<>((i, j) -> {
                int enqueueTime1 = tasks[i][0];
                int processingTime1 = tasks[i][1];

                int enqueueTime2 = tasks[j][0];
                int processingTime2 = tasks[j][1];

                System.out.println(enqueueTime1 + ", " + processingTime1 + " - " + enqueueTime2 + ", " + processingTime2);
                if(enqueueTime1 != enqueueTime2) {
                    System.out.println("enqueue");
                    return enqueueTime1 - enqueueTime2;
                }

                if(processingTime1 != processingTime2) {
                    System.out.println("processing");
                    return processingTime1 - processingTime2;
                }

                System.out.println("index");
                return i - j;
            });

            int n = tasks.length;
            for(int i = 0; i < n; i++) {
                heap.offer(i);

                System.out.println(heap);
            }

            int[] results = new int[n];
            for(int i = 0; i < n; i++) {
                results[i] = heap.poll();
            }

            return results;
        }
    }

// [[1,2],[2,4],[3,2],[4,1]]
}

//    1834. Single-Threaded CPU
//    Medium
//    You are given n​​​​​​ tasks labeled from 0 to n - 1 represented by a 2D integer array tasks, where tasks[i] = [enqueueTimei, processingTimei] means that the i​​​​​​th​​​​ task will be available to process at enqueueTimei and will take processingTimei to finish processing.
//
//    You have a single-threaded CPU that can process at most one task at a time and will act in the following way:
//
//    If the CPU is idle and there are no available tasks to process, the CPU remains idle.
//    If the CPU is idle and there are available tasks, the CPU will choose the one with the shortest processing time. If multiple tasks have the same shortest processing time, it will choose the task with the smallest index.
//    Once a task is started, the CPU will process the entire task without stopping.
//    The CPU can finish a task then start a new one instantly.
//    Return the order in which the CPU will process the tasks.
//
//
//
//    Example 1:
//
//    Input: tasks = [[1,2],[2,4],[3,2],[4,1]]
//    Output: [0,2,3,1]
//    Explanation: The events go as follows:
//    - At time = 1, task 0 is available to process. Available tasks = {0}.
//    - Also at time = 1, the idle CPU starts processing task 0. Available tasks = {}.
//    - At time = 2, task 1 is available to process. Available tasks = {1}.
//    - At time = 3, task 2 is available to process. Available tasks = {1, 2}.
//    - Also at time = 3, the CPU finishes task 0 and starts processing task 2 as it is the shortest. Available tasks = {1}.
//    - At time = 4, task 3 is available to process. Available tasks = {1, 3}.
//    - At time = 5, the CPU finishes task 2 and starts processing task 3 as it is the shortest. Available tasks = {1}.
//    - At time = 6, the CPU finishes task 3 and starts processing task 1. Available tasks = {}.
//    - At time = 10, the CPU finishes task 1 and becomes idle.
//    Example 2:
//
//    Input: tasks = [[7,10],[7,12],[7,5],[7,4],[7,2]]
//    Output: [4,3,2,0,1]
//    Explanation: The events go as follows:
//    - At time = 7, all the tasks become available. Available tasks = {0,1,2,3,4}.
//    - Also at time = 7, the idle CPU starts processing task 4. Available tasks = {0,1,2,3}.
//    - At time = 9, the CPU finishes task 4 and starts processing task 3. Available tasks = {0,1,2}.
//    - At time = 13, the CPU finishes task 3 and starts processing task 2. Available tasks = {0,1}.
//    - At time = 18, the CPU finishes task 2 and starts processing task 0. Available tasks = {1}.
//    - At time = 28, the CPU finishes task 0 and starts processing task 1. Available tasks = {}.
//    - At time = 40, the CPU finishes task 1 and becomes idle.
//
//
//    Constraints:
//
//    tasks.length == n
//    1 <= n <= 105
//    1 <= enqueueTimei, processingTimei <= 109