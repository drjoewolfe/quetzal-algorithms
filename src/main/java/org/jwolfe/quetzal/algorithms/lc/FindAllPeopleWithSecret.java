package org.jwolfe.quetzal.algorithms.lc;

import org.jwolfe.quetzal.library.general.Pair;

import java.util.*;

public class FindAllPeopleWithSecret {
    class Solution {
        public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
            List<Integer> results = new ArrayList<>();

            if (n < 1) {
                return results;
            }

            Arrays.sort(meetings, (a, b) -> a[2] - b[2]);

            TreeMap<Integer, List<int[]>> meetingsByTime = new TreeMap<>();
            for (int[] meeting : meetings) {
                int a = meeting[0];
                int b = meeting[1];
                int t = meeting[2];

                if (!meetingsByTime.containsKey(t)) {
                    meetingsByTime.put(t, new ArrayList<>());
                }

                meetingsByTime.get(t).add(new int[]{a, b});
            }

            DisjointSet ds = new DisjointSet(n);
            ds.union(0, firstPerson);

            for (int t : meetingsByTime.keySet()) {
                for (int[] meeting : meetingsByTime.get(t)) {
                    int a = meeting[0];
                    int b = meeting[1];

                    ds.union(a, b);
                }

                for (int[] meeting : meetingsByTime.get(t)) {
                    int a = meeting[0];
                    int b = meeting[1];

                    if (!ds.isConnected(a, 0)) {
                        ds.reset(a);
                        ds.reset(b);
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                if (ds.isConnected(i, 0)) {
                    results.add(i);
                }
            }

            return results;
        }

        private class DisjointSet {
            int[] parent;
            int[] rank;

            public DisjointSet(int n) {
                parent = new int[n];
                rank = new int[n];

                for (int x = 0; x < n; x++) {
                    parent[x] = x;
                }
            }

            public void union(int x, int y) {
                int px = find(x);
                int py = find(y);

                if (px != py) {
                    if (rank[px] > rank[py]) {
                        parent[py] = px;
                    } else if (rank[px] < rank[py]) {
                        parent[px] = py;
                    } else {
                        parent[py] = px;
                        rank[px]++;
                    }
                }
            }

            public int find(int x) {
                if (parent[x] != x) {
                    parent[x] = find(parent[x]);
                }

                return parent[x];
            }

            public boolean isConnected(int x, int y) {
                return find(x) == find(y);
            }

            public void reset(int x) {
                parent[x] = x;
                rank[x] = 0;
            }
        }
    }

    class Solution_Correct_1 {
        public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
            Map<Integer, List<Pair<Integer, Integer>>> graph = new HashMap<>();
            for (int[] meeting : meetings) {
                int x = meeting[0];
                int y = meeting[1];
                int t = meeting[2];

                if (!graph.containsKey(x)) {
                    graph.put(x, new ArrayList<>());
                }

                if (!graph.containsKey(y)) {
                    graph.put(y, new ArrayList<>());
                }

                graph.get(x).add(new Pair<>(y, t));
                graph.get(y).add(new Pair<>(x, t));
            }

            int[] earliest = new int[n];
            Arrays.fill(earliest, Integer.MAX_VALUE);

            earliest[0] = 0;
            earliest[firstPerson] = 0;

            Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
            queue.offer(new Pair<>(0, 0));
            queue.offer(new Pair<>(firstPerson, 0));

            while (!queue.isEmpty()) {
                var node = queue.poll();

                int u = node.getKey();
                int t = node.getValue();

                if (!graph.containsKey(u)) {
                    continue;
                }

                for (var childNode : graph.get(u)) {
                    int v = childNode.getKey();
                    int vt = childNode.getValue();

                    if (vt >= t && earliest[v] > vt) {
                        earliest[v] = vt;
                        queue.offer(new Pair<>(v, vt));
                    }
                }
            }

            List<Integer> peopleWithSecret = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (earliest[i] != Integer.MAX_VALUE) {
                    peopleWithSecret.add(i);
                }
            }

            return peopleWithSecret;
        }
    }

    class Solution_Incorrect_2 {
        public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
            Set<Integer> peopleWithSecret = new HashSet<>();
            if (n < 1) {
                return new ArrayList<>(peopleWithSecret);
            }

            peopleWithSecret.add(0);
            if (meetings == null || meetings.length == 0 || firstPerson < 0 || firstPerson >= n) {
                return new ArrayList<>(peopleWithSecret);
            }

            peopleWithSecret.add(firstPerson);

            TreeMap<Integer, DisjointSets> meetingsByTime = new TreeMap<>();
            for (int[] meeting : meetings) {
                int time = meeting[2];

                if (!meetingsByTime.containsKey(time)) {
                    meetingsByTime.put(time, new DisjointSets());
                }

                int x = meeting[0];
                int y = meeting[1];

                var disjointSets = meetingsByTime.get(time);
                disjointSets.union(x, y);
            }

            for (var daySchedule : meetingsByTime.entrySet()) {
                System.out.println(daySchedule.getKey());
                var sets = daySchedule.getValue().getSets();
                for (var set : sets) {
                    System.out.println("\t" + set);
                    for (var person : set) {
                        if (person.equals(5)) {
                            System.out.println("DEBUG: " + set + " , " + peopleWithSecret);
                        }
                        if (peopleWithSecret.contains(person)) {
                            peopleWithSecret.addAll(set);
                            break;
                        }
                    }
                }
            }

            return new ArrayList<>(peopleWithSecret);
        }

        class DisjointSets {
            Map<Integer, Integer> parent;

            public DisjointSets() {
                parent = new HashMap<>();
            }

            public void add(int element) {
                if (!parent.containsKey(element)) {
                    parent.put(element, -1);
                }
            }

            public void union(int element1, int element2) {
                add(element1);
                add(element2);

                element2 = find(element2);
                parent.put(element1, element2);
            }

            public int find(int element) {
                if (parent.get(element).equals(-1)) {
                    return element;
                }

                return find(parent.get(element));
            }

            public List<Set> getSets() {
                Map<Integer, Set<Integer>> setMap = new HashMap<>();
                for (var entry : parent.entrySet()) {
                    int e = entry.getKey();
                    int p = entry.getValue();

                    if (p == -1) {
                        setMap.put(e, new HashSet<>());
                        setMap.get(e).add(e);
                    }
                }

                for (var entry : parent.entrySet()) {
                    int e = entry.getKey();
                    int p = entry.getValue();

                    if (p != -1) {
                        p = find(e);
                        setMap.get(p).add(e);
                    }
                }

                List<Set> sets = new ArrayList<>();
                for (var entry : setMap.entrySet()) {
                    sets.add(entry.getValue());
                }

                return sets;
            }
        }
    }

    class Solution_Incorrect {
        public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
            Set<Integer> peopleWithSecret = new HashSet<>();
            if (n < 1) {
                return new ArrayList<>(peopleWithSecret);
            }

            peopleWithSecret.add(0);
            if (meetings == null || meetings.length == 0 || firstPerson < 0 || firstPerson >= n) {
                return new ArrayList<>(peopleWithSecret);
            }

            peopleWithSecret.add(firstPerson);

            Map<Integer, Set<Integer>> meetingsByTime = new TreeMap<>();
            for (int[] meeting : meetings) {
                int time = meeting[2];

                if (!meetingsByTime.containsKey(time)) {
                    meetingsByTime.put(time, new HashSet<>());
                }

                int x = meeting[0];
                int y = meeting[1];

                meetingsByTime.get(time).add(x);
                meetingsByTime.get(time).add(y);
            }

            for (var daySchedule : meetingsByTime.entrySet()) {
                var participants = daySchedule.getValue();
                for (var person : participants) {
                    if (peopleWithSecret.contains(person)) {
                        peopleWithSecret.addAll(participants);
                        break;
                    }
                }
            }

            return new ArrayList<>(peopleWithSecret);
        }
    }

// 6
// [[1,2,5],[2,3,8],[1,5,10]]
// 1

// 6
// [[0,2,1],[1,3,1],[4,5,1]]
// 1

// 4
// [[3,1,3],[1,2,2],[0,3,3]]
// 3

// 5
// [[3,4,2],[1,2,1],[2,3,1]]
// 1
}

//    2092. Find All People With Secret
//    Hard
//    You are given an integer n indicating there are n people numbered from 0 to n - 1. You are also given a 0-indexed 2D integer array meetings where meetings[i] = [xi, yi, timei] indicates that person xi and person yi have a meeting at timei. A person may attend multiple meetings at the same time. Finally, you are given an integer firstPerson.
//
//    Person 0 has a secret and initially shares the secret with a person firstPerson at time 0. This secret is then shared every time a meeting takes place with a person that has the secret. More formally, for every meeting, if a person xi has the secret at timei, then they will share the secret with person yi, and vice versa.
//
//    The secrets are shared instantaneously. That is, a person may receive the secret and share it with people in other meetings within the same time frame.
//
//    Return a list of all the people that have the secret after all the meetings have taken place. You may return the answer in any order.
//
//
//
//    Example 1:
//
//    Input: n = 6, meetings = [[1,2,5],[2,3,8],[1,5,10]], firstPerson = 1
//    Output: [0,1,2,3,5]
//    Explanation:
//    At time 0, person 0 shares the secret with person 1.
//    At time 5, person 1 shares the secret with person 2.
//    At time 8, person 2 shares the secret with person 3.
//    At time 10, person 1 shares the secret with person 5.​​​​
//    Thus, people 0, 1, 2, 3, and 5 know the secret after all the meetings.
//    Example 2:
//
//    Input: n = 4, meetings = [[3,1,3],[1,2,2],[0,3,3]], firstPerson = 3
//    Output: [0,1,3]
//    Explanation:
//    At time 0, person 0 shares the secret with person 3.
//    At time 2, neither person 1 nor person 2 know the secret.
//    At time 3, person 3 shares the secret with person 0 and person 1.
//    Thus, people 0, 1, and 3 know the secret after all the meetings.
//    Example 3:
//
//    Input: n = 5, meetings = [[3,4,2],[1,2,1],[2,3,1]], firstPerson = 1
//    Output: [0,1,2,3,4]
//    Explanation:
//    At time 0, person 0 shares the secret with person 1.
//    At time 1, person 1 shares the secret with person 2, and person 2 shares the secret with person 3.
//    Note that person 2 can share the secret at the same time as receiving it.
//    At time 2, person 3 shares the secret with person 4.
//    Thus, people 0, 1, 2, 3, and 4 know the secret after all the meetings.
//
//
//    Constraints:
//
//    2 <= n <= 105
//    1 <= meetings.length <= 105
//    meetings[i].length == 3
//    0 <= xi, yi <= n - 1
//    xi != yi
//    1 <= timei <= 105
//    1 <= firstPerson <= n - 1