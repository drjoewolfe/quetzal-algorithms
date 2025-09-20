package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class ImplementRouter {
    class Router {
        int memoryLimit;
        Queue<Long> queue;
        Map<Long, int[]> packets;
        Map<Integer, List<Integer>> destinationTimestamps;

        public Router(int memoryLimit) {
            this.memoryLimit = memoryLimit;
            this.queue = new LinkedList<>();
            this.packets = new HashMap<>();
            this.destinationTimestamps = new HashMap<>();
        }

        public boolean addPacket(int source, int destination, int timestamp) {
            long key = getKey(source, destination, timestamp);
            if (packets.containsKey(key)) {
                return false;
            }

            if (queue.size() >= memoryLimit) {
                forwardPacket();
            }

            queue.offer(key);
            packets.put(key, new int[]{source, destination, timestamp});

            if (!destinationTimestamps.containsKey(destination)) {
                destinationTimestamps.put(destination, new ArrayList<>());
            }

            destinationTimestamps.get(destination).add(timestamp);
            return true;
        }

        public int[] forwardPacket() {
            if (queue.isEmpty()) {
                return new int[]{};
            }

            long key = queue.poll();
            int[] packet = packets.get(key);

            packets.remove(key);

            int destination = packet[1];
            List<Integer> timestamps = destinationTimestamps.get(destination);
            timestamps.remove(0);
            if (timestamps.size() == 0) {
                destinationTimestamps.remove(destination);
            }

            return packet;
        }

        public int getCount(int destination, int startTime, int endTime) {
            if (!destinationTimestamps.containsKey(destination)) {
                return 0;
            }

            List<Integer> timestamps = destinationTimestamps.get(destination);
            int lower = lowerBound(timestamps, startTime);
            int upper = upperBound(timestamps, endTime);

            return upper - lower;
        }

        private int lowerBound(List<Integer> timestamps, int time) {
            int left = 0;
            int right = timestamps.size();

            while (left < right) {
                int mid = left + (right - left) / 2;
                int stamp = timestamps.get(mid);

                if (stamp < time) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            return left;
        }

        private int upperBound(List<Integer> timestamps, int time) {
            int left = 0;
            int right = timestamps.size();

            while (left < right) {
                int mid = left + (right - left) / 2;
                int stamp = timestamps.get(mid);

                if (stamp <= time) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            return left;
        }

        private long getKey(int source, int destination, int timestamp) {
            return ((long) source << 40) | ((long) destination << 20) | timestamp;
        }
    }

    class Router_TLE {
        int memoryLimit;
        Deque<String> queue;
        Map<String, int[]> packets;
        Map<Integer, List<Integer>> destinationTimestamps;

        public Router_TLE(int memoryLimit) {
            this.memoryLimit = memoryLimit;
            this.queue = new ArrayDeque<>();
            this.packets = new HashMap<>();
            this.destinationTimestamps = new HashMap<>();
        }

        public boolean addPacket(int source, int destination, int timestamp) {
            String key = Integer.toString(source) + "-" + Integer.toString(destination) + "-" + Integer.toString(timestamp);
            if (packets.containsKey(key)) {
                return false;
            }

            if (queue.size() == memoryLimit) {
                String firstKey = queue.pollFirst();
                int[] firstPacket = packets.get(firstKey);
                packets.remove(key);
                destinationTimestamps.get(firstPacket[1]).remove(0);
            }

            queue.offerLast(key);
            packets.put(key, new int[]{source, destination, timestamp});

            if (!destinationTimestamps.containsKey(destination)) {
                destinationTimestamps.put(destination, new LinkedList<>());
            }

            destinationTimestamps.get(destination).add(timestamp);
            return true;
        }

        public int[] forwardPacket() {
            if (queue.isEmpty()) {
                return new int[]{};
            }

            String key = queue.pollFirst();
            int[] packet = packets.get(key);

            packets.remove(key);

            int destination = packet[1];
            List<Integer> timestamps = destinationTimestamps.get(destination);
            timestamps.remove(0);
            if (timestamps.size() == 0) {
                destinationTimestamps.remove(destination);
            }

            return packet;
        }

        public int getCount(int destination, int startTime, int endTime) {
            if (!destinationTimestamps.containsKey(destination)) {
                return 0;
            }

            List<Integer> timestamps = destinationTimestamps.get(destination);
            int lower = lowerBound(timestamps, startTime);
            int upper = upperBound(timestamps, endTime);

            return upper - lower;
        }

        private int lowerBound(List<Integer> timestamps, int time) {
            int left = 0;
            int right = timestamps.size();

            while (left < right) {
                int mid = left + (right - left) / 2;
                int stamp = timestamps.get(mid);

                if (stamp < time) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            return left;
        }

        private int upperBound(List<Integer> timestamps, int time) {
            int left = 0;
            int right = timestamps.size();

            while (left < right) {
                int mid = left + (right - left) / 2;
                int stamp = timestamps.get(mid);

                if (stamp <= time) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            return left;
        }
    }

/**
 * Your Router object will be instantiated and called as such:
 * Router obj = new Router(memoryLimit);
 * boolean param_1 = obj.addPacket(source,destination,timestamp);
 * int[] param_2 = obj.forwardPacket();
 * int param_3 = obj.getCount(destination,startTime,endTime);
 */
}

//    3508. Implement Router
//    Medium
//    Design a data structure that can efficiently manage data packets in a network router. Each data packet consists of the following attributes:
//
//    source: A unique identifier for the machine that generated the packet.
//    destination: A unique identifier for the target machine.
//    timestamp: The time at which the packet arrived at the router.
//    Implement the Router class:
//
//    Router(int memoryLimit): Initializes the Router object with a fixed memory limit.
//
//    memoryLimit is the maximum number of packets the router can store at any given time.
//    If adding a new packet would exceed this limit, the oldest packet must be removed to free up space.
//    bool addPacket(int source, int destination, int timestamp): Adds a packet with the given attributes to the router.
//
//    A packet is considered a duplicate if another packet with the same source, destination, and timestamp already exists in the router.
//    Return true if the packet is successfully added (i.e., it is not a duplicate); otherwise return false.
//    int[] forwardPacket(): Forwards the next packet in FIFO (First In First Out) order.
//
//    Remove the packet from storage.
//    Return the packet as an array [source, destination, timestamp].
//    If there are no packets to forward, return an empty array.
//    int getCount(int destination, int startTime, int endTime):
//
//    Returns the number of packets currently stored in the router (i.e., not yet forwarded) that have the specified destination and have timestamps in the inclusive range [startTime, endTime].
//    Note that queries for addPacket will be made in increasing order of timestamp.
//
//
//
//    Example 1:
//
//    Input:
//    ["Router", "addPacket", "addPacket", "addPacket", "addPacket", "addPacket", "forwardPacket", "addPacket", "getCount"]
//    [[3], [1, 4, 90], [2, 5, 90], [1, 4, 90], [3, 5, 95], [4, 5, 105], [], [5, 2, 110], [5, 100, 110]]
//
//    Output:
//    [null, true, true, false, true, true, [2, 5, 90], true, 1]
//
//    Explanation
//
//    Router router = new Router(3); // Initialize Router with memoryLimit of 3.
//    router.addPacket(1, 4, 90); // Packet is added. Return True.
//    router.addPacket(2, 5, 90); // Packet is added. Return True.
//    router.addPacket(1, 4, 90); // This is a duplicate packet. Return False.
//    router.addPacket(3, 5, 95); // Packet is added. Return True
//    router.addPacket(4, 5, 105); // Packet is added, [1, 4, 90] is removed as number of packets exceeds memoryLimit. Return True.
//    router.forwardPacket(); // Return [2, 5, 90] and remove it from router.
//    router.addPacket(5, 2, 110); // Packet is added. Return True.
//    router.getCount(5, 100, 110); // The only packet with destination 5 and timestamp in the inclusive range [100, 110] is [4, 5, 105]. Return 1.
//    Example 2:
//
//    Input:
//    ["Router", "addPacket", "forwardPacket", "forwardPacket"]
//    [[2], [7, 4, 90], [], []]
//
//    Output:
//    [null, true, [7, 4, 90], []]
//
//    Explanation
//
//    Router router = new Router(2); // Initialize Router with memoryLimit of 2.
//    router.addPacket(7, 4, 90); // Return True.
//    router.forwardPacket(); // Return [7, 4, 90].
//    router.forwardPacket(); // There are no packets left, return [].
//
//
//    Constraints:
//
//    2 <= memoryLimit <= 105
//    1 <= source, destination <= 2 * 105
//    1 <= timestamp <= 109
//    1 <= startTime <= endTime <= 109
//    At most 105 calls will be made to addPacket, forwardPacket, and getCount methods altogether.
//    queries for addPacket will be made in increasing order of timestamp.
