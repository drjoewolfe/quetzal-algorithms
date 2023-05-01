package org.jwolfe.quetzal.algorithms.lc;

public class AverageSalaryExcludingTheMinimumAndMaximumSalary {
    class Solution {
        public double average(int[] salary) {
            if(salary == null || salary.length < 3) {
                return 0d;
            }

            int n = salary.length;
            int sum = salary[0];
            int min = sum;
            int max = sum;

            for(int i = 1; i < n; i++) {
                int val = salary[i];

                sum += val;
                min = Math.min(min, val);
                max = Math.max(max, val);
            }

            return (sum - (min + max)) / (n - 2.0);
        }
    }

    class Solution_Correct_1 {
        public double average(int[] salary) {
            if(salary == null || salary.length < 2) {
                return 0;
            }

            int min = salary[0];
            int max = salary[0];
            int sum = salary[0];

            for(int i = 1; i < salary.length; i++) {
                sum += salary[i];
                min = Math.min(min, salary[i]);
                max = Math.max(max, salary[i]);
            }

            return (sum - min - max) / (salary.length - 2.0);
        }
    }
}

//    1491. Average Salary Excluding the Minimum and Maximum Salary
//    Easy
//    You are given an array of unique integers salary where salary[i] is the salary of the ith employee.
//
//    Return the average salary of employees excluding the minimum and maximum salary. Answers within 10-5 of the actual answer will be accepted.
//
//
//
//    Example 1:
//
//    Input: salary = [4000,3000,1000,2000]
//    Output: 2500.00000
//    Explanation: Minimum salary and maximum salary are 1000 and 4000 respectively.
//    Average salary excluding minimum and maximum salary is (2000+3000) / 2 = 2500
//    Example 2:
//
//    Input: salary = [1000,2000,3000]
//    Output: 2000.00000
//    Explanation: Minimum salary and maximum salary are 1000 and 3000 respectively.
//    Average salary excluding minimum and maximum salary is (2000) / 1 = 2000
//
//
//    Constraints:
//
//    3 <= salary.length <= 100
//    1000 <= salary[i] <= 106
//    All the integers of salary are unique.