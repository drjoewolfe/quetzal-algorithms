package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class DesignCircularDeque {
    class MyCircularDeque {

        int[] elements;
        int front;
        int rear;
        int count;
        int capacity;

        public MyCircularDeque(int k) {
            elements = new int[k];
            front = 0;
            rear = k - 1;
            count = 0;
            capacity = k;
        }

        public boolean insertFront(int value) {
            if (isFull()) {
                return false;
            }

            front = (front - 1 + capacity) % capacity;

            elements[front] = value;
            count++;

            print();
            return true;
        }

        public boolean insertLast(int value) {
            if (isFull()) {
                return false;
            }

            rear = (rear + 1) % capacity;

            elements[rear] = value;
            count++;

            print();
            return true;
        }

        public boolean deleteFront() {
            if (isEmpty()) {
                return false;
            }

            front = (front + 1) % capacity;
            count--;

            print();
            return true;
        }

        public boolean deleteLast() {
            if (isEmpty()) {
                return false;
            }

            rear = (rear - 1 + capacity) % capacity;
            count--;

            print();
            return true;
        }

        public int getFront() {
            if (isEmpty()) {
                return -1;
            }

            return elements[front];
        }

        public int getRear() {
            if (isEmpty()) {
                return -1;
            }

            return elements[rear];
        }

        public boolean isEmpty() {
            return count == 0;
        }

        public boolean isFull() {
            return count == capacity;
        }

        private void print() {
            // print(elements);
            // System.out.println("\t Front: " + front + ", Rear: " + rear + ", Count: " + count);
        }

        private void print(int[] arr) {
            for (int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }

    class MyCircularDeque_Incorrect {

        int[] elements;
        int front;
        int rear;
        int count;
        int capacity;

        public MyCircularDeque_Incorrect(int k) {
            elements = new int[k];
            Arrays.fill(elements, -1);

            front = 0;
            rear = 0;
            count = 0;
            capacity = k;
        }

        public boolean insertFront(int value) {
            if (isFull()) {
                return false;
            }

            if (!isEmpty()) {
                front = (front + 1) % capacity;
            }

            elements[front] = value;
            count++;

            print();
            return true;
        }

        public boolean insertLast(int value) {
            if (isFull()) {
                return false;
            }

            if (!isEmpty()) {
                if (rear == 0) {
                    rear = capacity - 1;
                } else {
                    rear--;
                }
            }

            elements[rear] = value;
            count++;

            print();
            return true;
        }

        public boolean deleteFront() {
            if (isEmpty()) {
                return false;
            }

            elements[front] = -1;
            if (front > 0) {
                front--;
            } else {
                front = capacity - 1;
            }

            count--;

            print();
            return true;
        }

        public boolean deleteLast() {
            if (isEmpty()) {
                return false;
            }

            elements[rear] = -1;
            rear = (rear + 1) % capacity;

            count--;

            print();
            return true;
        }

        public int getFront() {
            return elements[front];
        }

        public int getRear() {
            return elements[rear];
        }

        public boolean isEmpty() {
            return count == 0;
        }

        public boolean isFull() {
            return count == capacity;
        }

        private void print() {
            print(elements);
            System.out.println("\t Front: " + front + ", Rear: " + rear + ", Count: " + count);
        }

        private void print(int[] arr) {
            for (int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */

// ["MyCircularDeque","insertLast","insertLast","insertFront","insertFront","getRear","isFull","deleteLast","insertFront","getFront"]
// [[3],[1],[2],[3],[4],[],[],[],[4],[]]

// ["MyCircularDeque","insertFront","deleteLast","getRear","getFront","getFront","deleteFront","insertFront","insertLast","insertFront","getFront","insertFront"]
// [[4],[9],[],[],[],[],[],[6],[5],[9],[],[6]]

// ["MyCircularDeque","insertFront","deleteLast","getFront","insertLast","insertFront","getFront","getRear","getFront","getFront","getRear","insertLast"]
// [[2],[7],[],[],[5],[0],[],[],[],[],[],[0]]
}

//    641. Design Circular Deque
//    Medium
//    Design your implementation of the circular double-ended queue (deque).
//
//    Implement the MyCircularDeque class:
//
//    MyCircularDeque(int k) Initializes the deque with a maximum size of k.
//    boolean insertFront() Adds an item at the front of Deque. Returns true if the operation is successful, or false otherwise.
//    boolean insertLast() Adds an item at the rear of Deque. Returns true if the operation is successful, or false otherwise.
//    boolean deleteFront() Deletes an item from the front of Deque. Returns true if the operation is successful, or false otherwise.
//    boolean deleteLast() Deletes an item from the rear of Deque. Returns true if the operation is successful, or false otherwise.
//    int getFront() Returns the front item from the Deque. Returns -1 if the deque is empty.
//    int getRear() Returns the last item from Deque. Returns -1 if the deque is empty.
//    boolean isEmpty() Returns true if the deque is empty, or false otherwise.
//    boolean isFull() Returns true if the deque is full, or false otherwise.
//
//
//    Example 1:
//
//    Input
//    ["MyCircularDeque", "insertLast", "insertLast", "insertFront", "insertFront", "getRear", "isFull", "deleteLast", "insertFront", "getFront"]
//    [[3], [1], [2], [3], [4], [], [], [], [4], []]
//    Output
//    [null, true, true, true, false, 2, true, true, true, 4]
//
//    Explanation
//    MyCircularDeque myCircularDeque = new MyCircularDeque(3);
//    myCircularDeque.insertLast(1);  // return True
//    myCircularDeque.insertLast(2);  // return True
//    myCircularDeque.insertFront(3); // return True
//    myCircularDeque.insertFront(4); // return False, the queue is full.
//    myCircularDeque.getRear();      // return 2
//    myCircularDeque.isFull();       // return True
//    myCircularDeque.deleteLast();   // return True
//    myCircularDeque.insertFront(4); // return True
//    myCircularDeque.getFront();     // return 4
//
//
//    Constraints:
//
//    1 <= k <= 1000
//    0 <= value <= 1000
//    At most 2000 calls will be made to insertFront, insertLast, deleteFront, deleteLast, getFront, getRear, isEmpty, isFull.