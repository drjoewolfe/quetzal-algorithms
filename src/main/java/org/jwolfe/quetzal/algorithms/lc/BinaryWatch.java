package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;

public class BinaryWatch {
    class Solution {
        public List<String> readBinaryWatch(int turnedOn) {
            List<String> results = new ArrayList<>();

            for (int hour = 0; hour < 12; hour++) {
                for (int minute = 0; minute < 60; minute++) {
                    int bitCount = Integer.bitCount(hour) + Integer.bitCount(minute);
                    if (bitCount == turnedOn) {
                        String time = hour + ":" + (minute < 10 ? "0" : "") + minute;
                        results.add(time);
                    }
                }
            }

            return results;
        }
    }
}

//    401. Binary Watch
//    Easy
//    A binary watch has 4 LEDs on the top to represent the hours (0-11), and 6 LEDs on the bottom to represent the minutes (0-59). Each LED represents a zero or one, with the least significant bit on the right.
//
//    For example, the below binary watch reads "4:51".
//
//
//    Given an integer turnedOn which represents the number of LEDs that are currently on (ignoring the PM), return all possible times the watch could represent. You may return the answer in any order.
//
//    The hour must not contain a leading zero.
//
//    For example, "01:00" is not valid. It should be "1:00".
//    The minute must consist of two digits and may contain a leading zero.
//
//    For example, "10:2" is not valid. It should be "10:02".
//
//
//    Example 1:
//
//    Input: turnedOn = 1
//    Output: ["0:01","0:02","0:04","0:08","0:16","0:32","1:00","2:00","4:00","8:00"]
//    Example 2:
//
//    Input: turnedOn = 9
//    Output: []
//
//
//    Constraints:
//
//    0 <= turnedOn <= 10