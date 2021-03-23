package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DesignHashMap {
    public static class MyHashMap {

        private List<LinkedList<Entry>> map;

        private int capacity = 1000;

        /** Initialize your data structure here. */
        public MyHashMap() {
            map = new ArrayList<>();
            for(int i = 0; i < capacity; i++) {
                map.add(new LinkedList<>());
            }
        }

        /** value will always be non-negative. */
        public void put(int key, int value) {
            int hash = key % capacity;
            LinkedList<Entry> list = map.get(hash);
            for(Entry listEntry : list) {
                if(listEntry.key == key) {
                    listEntry.value = value;
                    return;
                }
            }

            Entry entry = new Entry(key, value);
            list.add(entry);
        }

        /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
        public int get(int key) {
            int hash = key % capacity;
            LinkedList<Entry> list = map.get(hash);
            for(Entry listEntry : list) {
                if(listEntry.key == key) {
                    return listEntry.value;
                }
            }

            return -1;
        }

        /** Removes the mapping of the specified value key if this map contains a mapping for the key */
        public void remove(int key) {
            int hash = key % capacity;
            LinkedList<Entry> list = map.get(hash);
            int index = -1;
            for(int i = 0; i < list.size(); i++) {
                Entry listEntry = list.get(i);
                if(listEntry.key == key) {
                    index = i;
                    break;
                }
            }

            if(index != -1) {
                list.remove(index);
            }
        }

        private class Entry {
            int key;
            int value;

            public Entry(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }
    }

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
}

//    706. Design HashMap
//    Easy
//    Design a HashMap without using any built-in hash table libraries.
//
//    To be specific, your design should include these functions:
//
//    put(key, value) : Insert a (key, value) pair into the HashMap. If the value already exists in the HashMap, update the value.
//    get(key): Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
//    remove(key) : Remove the mapping for the value key if this map contains the mapping for the key.
//
//    Example:
//
//    MyHashMap hashMap = new MyHashMap();
//    hashMap.put(1, 1);
//    hashMap.put(2, 2);
//    hashMap.get(1);            // returns 1
//    hashMap.get(3);            // returns -1 (not found)
//    hashMap.put(2, 1);          // update the existing value
//    hashMap.get(2);            // returns 1
//    hashMap.remove(2);          // remove the mapping for 2
//    hashMap.get(2);            // returns -1 (not found)
//
//    Note:
//
//    All keys and values will be in the range of [0, 1000000].
//    The number of operations will be in the range of [1, 10000].
//    Please do not use the built-in HashMap library.
