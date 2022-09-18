package org.jwolfe.quetzal.algorithms.lc;

public class CountDaysSpentTogether {
    class Solution {
        public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
            if(arriveAlice == null || leaveAlice == null || arriveBob == null || leaveBob == null || arriveAlice.length() != 5 || leaveAlice.length() != 5 || arriveBob.length() != 5 || leaveBob.length() != 5) {
                return -1;
            }

            int[] arriveAliceDate = getDate(arriveAlice);
            int[] leaveAliceDate = getDate(leaveAlice);
            int[] arriveBobDate = getDate(arriveBob);
            int[] leaveBobDate = getDate(leaveBob);

            int[] latestArrival = getMax(arriveAliceDate, arriveBobDate);
            int[] earliestDeparture = getMin(leaveAliceDate, leaveBobDate);

            if((latestArrival[0] > earliestDeparture[0])
                    || ((latestArrival[0] == earliestDeparture[0]) && (latestArrival[1] > earliestDeparture[1]))) {
                return 0;
            }

            return getDiffDays(latestArrival, earliestDeparture);
        }

        private int[] getDate(String dateString) {
            String[] parts = dateString.split("-");
            int month = Integer.parseInt(parts[0]);
            int day = Integer.parseInt(parts[1]);

            return new int[] {month, day};
        }

        private int[] getMax(int[] date1, int[] date2) {
            if(date1[0] > date2[0]) {
                return date1;
            } else if(date1[0] < date2[0]) {
                return date2;
            }

            if(date1[1] > date2[1]) {
                return date1;
            }

            return date2;
        }

        private int[] getMin(int[] date1, int[] date2) {
            if(date1[0] < date2[0]) {
                return date1;
            } else if(date1[0] > date2[0]) {
                return date2;
            }

            if(date1[1] < date2[1]) {
                return date1;
            }

            return date2;
        }

        private int getDiffDays(int[] date1, int[] date2) {
            if(date1[0] == date2[0]) {
                return date2[1] - date1[1] + 1;
            }

            int[] daysInMonth = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

            int totalDays = daysInMonth[date1[0] - 1] - date1[1] + 1;

            // Months
            for(int i = date1[0] + 1; i < date2[0]; i++) {
                totalDays += daysInMonth[i - 1];
            }

            totalDays += date2[1];

            return totalDays;
        }
    }

// "08-15"
// "08-18"
// "08-16"
// "08-19"

// "10-01"
// "10-31"
// "11-01"
// "12-31"
}

//    2409. Count Days Spent Together
//    Easy
//    Alice and Bob are traveling to Rome for separate business meetings.
//
//    You are given 4 strings arriveAlice, leaveAlice, arriveBob, and leaveBob. Alice will be in the city from the dates arriveAlice to leaveAlice (inclusive), while Bob will be in the city from the dates arriveBob to leaveBob (inclusive). Each will be a 5-character string in the format "MM-DD", corresponding to the month and day of the date.
//
//    Return the total number of days that Alice and Bob are in Rome together.
//
//    You can assume that all dates occur in the same calendar year, which is not a leap year. Note that the number of days per month can be represented as: [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31].
//
//
//
//    Example 1:
//
//    Input: arriveAlice = "08-15", leaveAlice = "08-18", arriveBob = "08-16", leaveBob = "08-19"
//    Output: 3
//    Explanation: Alice will be in Rome from August 15 to August 18. Bob will be in Rome from August 16 to August 19. They are both in Rome together on August 16th, 17th, and 18th, so the answer is 3.
//    Example 2:
//
//    Input: arriveAlice = "10-01", leaveAlice = "10-31", arriveBob = "11-01", leaveBob = "12-31"
//    Output: 0
//    Explanation: There is no day when Alice and Bob are in Rome together, so we return 0.
//
//
//    Constraints:
//
//    All dates are provided in the format "MM-DD".
//    Alice and Bob's arrival dates are earlier than or equal to their leaving dates.
//    The given dates are valid dates of a non-leap year.