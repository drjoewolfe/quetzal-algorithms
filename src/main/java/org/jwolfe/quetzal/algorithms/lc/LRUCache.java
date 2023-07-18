package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    class LRUCache_Correct_2 {
        class CacheItem {
            int key;
            int value;
            CacheItem prev;
            CacheItem next;

            public CacheItem(int key, int value) {
                this.key = key;
                this.value = value;
            }

            @Override
            public boolean equals(Object other) {
                if(other instanceof CacheItem) {
                    return ((CacheItem) other).key == this.key;
                }

                return false;
            }

            @Override
            public int hashCode() {
                int hash = 17;
                hash *= 31;
                hash += key;

                return hash;
            }

            @Override
            public String toString() {
                return "[" + key + " : " + value + "]";
            }
        }

        Map<Integer, CacheItem> cache;
        int capacity;

        CacheItem head;
        CacheItem tail;

        public LRUCache_Correct_2(int capacity) {
            this.capacity = capacity;
            this.cache = new HashMap<>();

            this.head = null;
            this.tail = null;
        }

        public int get(int key) {
            if(cache.containsKey(key)) {
                var item = cache.get(key);
                moveToFront(item);

                return item.value;
            }

            return -1;
        }

        public void put(int key, int value) {
            CacheItem item = null;
            if(cache.containsKey(key)) {
                item = cache.get(key);
                item.value = value;
            } else {
                item = new CacheItem(key, value);

            }

            if(cache.size() == capacity && !cache.containsKey(key)) {
                evictLast();
            }

            moveToFront(item);
            cache.put(key, item);
        }

        private void moveToFront(CacheItem item) {
            if(head == null) {
                head = item;
                tail = item;
            } else if(head == item) {
                return;
            } else if(capacity == 1) {
                head = item;
                head.next = null;
                head.prev = null;

                tail = item;
            } else {
                var prev = item.prev;
                var next = item.next;

                if(prev != null) {
                    prev.next = next;
                }

                if(next != null) {
                    next.prev = prev;
                }

                if(item == tail) {
                    if(prev != null) {
                        tail = prev;
                    } else if(next != null) {
                        tail = next;
                    }
                }

                item.prev = head;
                item.next = null;

                head.next = item;

                head = item;
            }
        }

        private void evictLast() {
            var lruItem = tail;
            cache.remove(lruItem.key);

            tail = tail.next;
            if(tail != null) {
                tail.prev = null;
            }
        }

        private void printCache() {
            for(Map.Entry entry : cache.entrySet()) {
                System.out.print(entry.toString() + " ");
            }

            System.out.print("; head: " + head + ", tail: " + tail);
            System.out.println();

            CacheItem current = head;
            while(current != null) {
                System.out.print(current + " ");
                current = current.next;
            }

            System.out.println();
        }
    }

    class LRUCache_Correct {

        class CacheItem {
            int key;
            int value;

            CacheItem next;
            CacheItem prev;

            public CacheItem(int key, int value) {
                this.key = key;
                this.value = value;
            }

            @Override
            public boolean equals(Object o) {
                if(o instanceof CacheItem) {
                    CacheItem item = (CacheItem) o;
                    return this.key == item.key;
                }

                return false;
            }

            @Override
            public int hashCode() {
                int hash = 17;
                hash *= 31;
                hash += key;

                return hash;
            }

            @Override
            public String toString() {
                return "[" + key + " : " + value + "]";
            }
        }

        private Map<Integer, CacheItem> cache = new HashMap<>();

        private CacheItem head;
        private CacheItem tail;

        private int capacity;

        public LRUCache_Correct(int capacity) {
            if(capacity <= 0) {
                capacity = 10;
            }

            this.capacity = capacity;
        }

        public int get(int key) {
            if(cache.containsKey(key)) {
                CacheItem item = cache.get(key);
                moveToHead(item);

                return item.value;
            }

            return -1;
        }

        public void put(int key, int value) {
            CacheItem item = null;
            if(cache.containsKey(key)) {
                item = cache.get(key);
                item.value = value;
            } else {
                item = new CacheItem(key, value);
            }

            if(capacity == cache.size() && ! cache.containsKey(key)) {
                evictLast();
            }

            moveToHead(item);
            cache.put(key, item);
        }

        private void moveToHead(CacheItem item) {
            if(head == null) {
                head = item;
                tail = item;
            } if(head == item) {
                return;
            } else if(capacity == 1) {
                head = item;
                head.next = null;
                head.prev = null;

                tail = item;
            } else {
                CacheItem prev = item.prev;
                CacheItem next = item.next;

                if(prev != null) {
                    prev.next = next;
                }

                if(next != null) {
                    next.prev = prev;
                }

                if(item == tail) {
                    if(next != null) {
                        tail = next;
                    } else if(prev != null) {
                        tail = prev;
                    }
                }

                item.next = head;
                item.prev = null;

                head.prev = item;

                head = item;
            }
        }

        private void evictLast() {
            CacheItem lruItem = tail;
            cache.remove(lruItem.key);

            tail = tail.prev;
            if(tail != null) {
                tail.next = null;
            }
        }

        private void printCache() {
            for(Map.Entry entry : cache.entrySet()) {
                System.out.print(entry.toString() + " ");
            }

            System.out.print("; head: " + head + ", tail: " + tail);
            System.out.println();

            CacheItem current = head;
            while(current != null) {
                System.out.print(current + " ");
                current = current.next;
            }

            System.out.println();
        }
    }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

// ["LRUCache","put","put","get","put","get","put","get","get","get"]
// [[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]

// ["LRUCache","get","put","get","put","put","get","get"]
// [[2],[2],[2,6],[1],[1,5],[1,2],[1],[2]]
}

//    146. LRU Cache
//    Medium
//    Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
//
//    Implement the LRUCache class:
//
//    LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
//    int get(int key) Return the value of the key if the key exists, otherwise return -1.
//    void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
//    The functions get and put must each run in O(1) average time complexity.
//
//
//
//    Example 1:
//
//    Input
//    ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
//    [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
//    Output
//    [null, null, null, 1, null, -1, null, -1, 3, 4]
//
//    Explanation
//    LRUCache lRUCache = new LRUCache(2);
//    lRUCache.put(1, 1); // cache is {1=1}
//    lRUCache.put(2, 2); // cache is {1=1, 2=2}
//    lRUCache.get(1);    // return 1
//    lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
//    lRUCache.get(2);    // returns -1 (not found)
//    lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
//    lRUCache.get(1);    // return -1 (not found)
//    lRUCache.get(3);    // return 3
//    lRUCache.get(4);    // return 4
//
//
//    Constraints:
//
//    1 <= capacity <= 3000
//    0 <= key <= 104
//    0 <= value <= 105
//    At most 2 * 105 calls will be made to get and put.