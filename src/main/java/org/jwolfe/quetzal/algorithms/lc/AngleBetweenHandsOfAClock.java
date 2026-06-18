package org.jwolfe.quetzal.algorithms.lc;

public class AngleBetweenHandsOfAClock {
    class Solution {
        public double angleClock(int hour, int minutes) {
            // For minute hand
            //      1 minute = 6 degrees
            //      y minutes = 6y degrees
            // For hour hand
            //      base: 1 hour = 30 degrees
            //      incr: 1 min = 0.5 degrees
            //      x hours y min = (30x) + (0.5y) degrees

            double minuteAngle = 6 * minutes;
            double hourAngle = (30 * hour) + (0.5 * minutes);

            double maxAngle = Math.max(hourAngle, minuteAngle);
            double minAngle = Math.min(hourAngle, minuteAngle);

            double ans = maxAngle - minAngle;
            if (ans > 180) {
                ans = 360 - ans;
            }

            return ans;
        }
    }
}

//    1344. Angle Between Hands of a Clock
//    Medium
//    Given two numbers, hour and minutes, return the smaller angle (in degrees) formed between the hour and the minute hand.
//
//    Answers within 10-5 of the actual value will be accepted as correct.
//
//
//
//    Example 1:
//
//
//    Input: hour = 12, minutes = 30
//    Output: 165
//    Example 2:
//
//
//    Input: hour = 3, minutes = 30
//    Output: 75
//    Example 3:
//
//
//    Input: hour = 3, minutes = 15
//    Output: 7.5
//
//
//    Constraints:
//
//    1 <= hour <= 12
//    0 <= minutes <= 59