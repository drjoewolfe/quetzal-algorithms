package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class SnapshotArray {
    class SnapshotArray_BinarySearch {
        TreeMap<Integer, Integer>[] records;
        int currentSnapId;

        public SnapshotArray_BinarySearch(int length) {
            currentSnapId = 0;
            records = new TreeMap[length];

            for(int i = 0; i < length; i++) {
                records[i] = new TreeMap<>();
                records[i].put(0, 0);
            }
        }

        public void set(int index, int val) {
            records[index].put(currentSnapId, val);
        }

        public int snap() {
            return currentSnapId++;
        }

        public int get(int index, int snap_id) {
            return records[index].floorEntry(snap_id).getValue();
        }
    }

    class SnapshotArray_Correct_1 {
        Map<Integer, Map<Integer, Integer>> store;
        int length;
        int defaultMap;
        int currentSnapshot;

        public SnapshotArray_Correct_1(int length) {
            store = new HashMap<>();
            this.length = length;
            defaultMap = -1;
            currentSnapshot = -1;

            store.put(currentSnapshot, new HashMap<>());
        }

        public void set(int index, int val) {
            if(index < 0 || index >= length) {
                throw new RuntimeException("Invalid index");
            }

            var map = store.get(defaultMap);
            map.put(index, val);
        }

        public int snap() {
            var map = new HashMap<>(store.get(defaultMap));
            store.put(++currentSnapshot, map);

            return currentSnapshot;
        }

        public int get(int index, int snap_id) {
            if(snap_id < 0 || snap_id > currentSnapshot) {
                throw new RuntimeException("Invalid snap id");
            }

            var map = store.get(snap_id);
            if(map.containsKey(index)) {
                return store.get(snap_id).get(index);
            }

            return 0;
        }
    }

/**
 * Your SnapshotArray object will be instantiated and called as such:
 * SnapshotArray obj = new SnapshotArray(length);
 * obj.set(index,val);
 * int param_2 = obj.snap();
 * int param_3 = obj.get(index,snap_id);
 */
}

//    1146. Snapshot Array
//    Medium
//    Implement a SnapshotArray that supports the following interface:
//
//    SnapshotArray(int length) initializes an array-like data structure with the given length. Initially, each element equals 0.
//    void set(index, val) sets the element at the given index to be equal to val.
//    int snap() takes a snapshot of the array and returns the snap_id: the total number of times we called snap() minus 1.
//    int get(index, snap_id) returns the value at the given index, at the time we took the snapshot with the given snap_id
//
//
//    Example 1:
//
//    Input: ["SnapshotArray","set","snap","set","get"]
//    [[3],[0,5],[],[0,6],[0,0]]
//    Output: [null,null,0,null,5]
//    Explanation:
//    SnapshotArray snapshotArr = new SnapshotArray(3); // set the length to be 3
//    snapshotArr.set(0,5);  // Set array[0] = 5
//    snapshotArr.snap();  // Take a snapshot, return snap_id = 0
//    snapshotArr.set(0,6);
//    snapshotArr.get(0,0);  // Get the value of array[0] with snap_id = 0, return 5
//
//
//    Constraints:
//
//    1 <= length <= 5 * 104
//    0 <= index < length
//    0 <= val <= 109
//    0 <= snap_id < (the total number of times we call snap())
//    At most 5 * 104 calls will be made to set, snap, and get.