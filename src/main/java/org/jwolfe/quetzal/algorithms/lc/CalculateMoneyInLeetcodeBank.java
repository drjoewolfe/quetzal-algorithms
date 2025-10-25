package org.jwolfe.quetzal.algorithms.lc;

public class CalculateMoneyInLeetcodeBank {
    class Solution {
        public int totalMoney(int n) {
            int weeks = n / 7;
            int days = n % 7;

            int weeksMoney = 28 * weeks + 7 * (weeks * (weeks - 1) / 2);

            int daysStart = weeks + 1;
            int daysEnd = weeks + days;

            int daysMoney = (daysEnd * (daysEnd + 1)) / 2 - ((daysStart - 1) * daysStart) / 2;

            int total = weeksMoney + daysMoney;

            return total;
        }
    }

    class Solution_Correct_2 {
        public int totalMoney(int n) {
            if (n < 1) {
                return 0;
            }

            int wholeWeeks = n / 7;
            int remainingDays = n % 7;

            int totalAmount = 0;
            if (wholeWeeks > 0) {
                totalAmount += wholeWeeks * 28 + (int) ((1d * wholeWeeks / 2 * (wholeWeeks - 1) * 7));
            }

            if (remainingDays > 0) {
                totalAmount += ((remainingDays) * (remainingDays - 1) / 2) + (wholeWeeks + 1) * remainingDays;
            }

            return totalAmount;
        }
    }

    class Solution_Correct_1 {
        public int totalMoney(int n) {
            if (n < 1) {
                return 0;
            }

            int totalAmount = 0;

            // In 1 week, 1+2+3+4+5+6+7 = 28
            int wholeWeeks = n / 7;
            for (int w = 1; w <= wholeWeeks; w++) {
                totalAmount += (28 + (w - 1) * 7);
            }

            int remainingDays = n % 7;
            for (int d = 1; d <= remainingDays; d++) {
                totalAmount += (d + wholeWeeks);
            }

            return totalAmount;
        }
    }


    class Solution_Approach_1 {
        public int totalMoney(int n) {
            if (n < 1) {
                return 0;
            }

            int totalAmount = 0;
            int w = 1;
            while (n > 0) {
                int amount = w;
                for (int d = 1; d <= 7 && n > 0; d++) {
                    totalAmount += amount;
                    amount++;
                    n--;
                }

                w++;
            }

            return totalAmount;
        }
    }

// 4
}

//    1716. Calculate Money in Leetcode Bank
//    Easy
//    Hercy wants to save money for his first car. He puts money in the Leetcode bank every day.
//
//    He starts by putting in $1 on Monday, the first day. Every day from Tuesday to Sunday, he will put in $1 more than the day before. On every subsequent Monday, he will put in $1 more than the previous Monday.
//    Given n, return the total amount of money he will have in the Leetcode bank at the end of the nth day.
//
//
//
//    Example 1:
//
//    Input: n = 4
//    Output: 10
//    Explanation: After the 4th day, the total is 1 + 2 + 3 + 4 = 10.
//    Example 2:
//
//    Input: n = 10
//    Output: 37
//    Explanation: After the 10th day, the total is (1 + 2 + 3 + 4 + 5 + 6 + 7) + (2 + 3 + 4) = 37. Notice that on the 2nd Monday, Hercy only puts in $2.
//    Example 3:
//
//    Input: n = 20
//    Output: 96
//    Explanation: After the 20th day, the total is (1 + 2 + 3 + 4 + 5 + 6 + 7) + (2 + 3 + 4 + 5 + 6 + 7 + 8) + (3 + 4 + 5 + 6 + 7 + 8) = 96.
//
//
//    Constraints:
//
//    1 <= n <= 1000
