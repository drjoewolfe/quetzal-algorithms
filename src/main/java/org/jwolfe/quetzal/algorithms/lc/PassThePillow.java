package org.jwolfe.quetzal.algorithms.lc;

public class PassThePillow {
    class Solution {
        public int passThePillow(int n, int time) {
            if (n < 0 || time < 0) {
                return -1;
            }

            int completedRounds = time / (n - 1);
            int lastLine = time % (n - 1);

            if (completedRounds % 2 == 0) {
                // Last line is left to right
                return lastLine + 1;
            } else {
                // Last line is right to left
                return n - lastLine;
            }
        }
    }

    class Solution_Correct_1 {
        public int passThePillow(int n, int time) {
            int rounds = time / (n - 1);
            int line = time % (n - 1);

            if (rounds % 2 == 0) {
                return line + 1;
            } else {
                return n - line;
            }
        }
    }

// 4
// 5

// 3
// 2

// 9
// 4
}

//    2582. Pass the Pillow
//    Easy
//    There are n people standing in a line labeled from 1 to n. The first person in the line is holding a pillow initially. Every second, the person holding the pillow passes it to the next person standing in the line. Once the pillow reaches the end of the line, the direction changes, and people continue passing the pillow in the opposite direction.
//
//    For example, once the pillow reaches the nth person they pass it to the n - 1th person, then to the n - 2th person and so on.
//    Given the two positive integers n and time, return the index of the person holding the pillow after time seconds.
//
//
//
//    Example 1:
//
//    Input: n = 4, time = 5
//    Output: 2
//    Explanation: People pass the pillow in the following way: 1 -> 2 -> 3 -> 4 -> 3 -> 2.
//    Afer five seconds, the pillow is given to the 2nd person.
//    Example 2:
//
//    Input: n = 3, time = 2
//    Output: 3
//    Explanation: People pass the pillow in the following way: 1 -> 2 -> 3.
//    Afer two seconds, the pillow is given to the 3rd person.
//
//
//    Constraints:
//
//    2 <= n <= 1000
//    1 <= time <= 1000