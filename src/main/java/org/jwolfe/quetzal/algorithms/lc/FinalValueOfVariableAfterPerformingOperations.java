package org.jwolfe.quetzal.algorithms.lc;

public class FinalValueOfVariableAfterPerformingOperations {
    class Solution {
        public int finalValueAfterOperations(String[] operations) {
            if (operations == null || operations.length == 0) {
                return 0;
            }

            int value = 0;
            for (var operation : operations) {
                switch (operation) {
                    case "--X":
                        value--;
                        break;
                    case "X--":
                        value--;
                        break;
                    case "++X":
                        value++;
                        break;
                    case "X++":
                        value++;
                        break;
                    default:
                        break;
                }
            }

            return value;
        }
    }

    class Solution_Correct_1 {
        public int finalValueAfterOperations(String[] operations) {
            if (operations == null || operations.length == 0) {
                return 0;
            }

            int value = 0;
            for (String operation : operations) {
                if (operation.equals("X++") || operation.equals("++X")) {
                    value++;
                } else {
                    value--;
                }
            }

            return value;
        }
    }
}

//    2011. Final Value of Variable After Performing Operations
//    Easy
//    There is a programming language with only four operations and one variable X:
//
//    ++X and X++ increments the value of the variable X by 1.
//    --X and X-- decrements the value of the variable X by 1.
//    Initially, the value of X is 0.
//
//    Given an array of strings operations containing a list of operations, return the final value of X after performing all the operations.
//
//
//
//    Example 1:
//
//    Input: operations = ["--X","X++","X++"]
//    Output: 1
//    Explanation: The operations are performed as follows:
//    Initially, X = 0.
//    --X: X is decremented by 1, X =  0 - 1 = -1.
//    X++: X is incremented by 1, X = -1 + 1 =  0.
//    X++: X is incremented by 1, X =  0 + 1 =  1.
//    Example 2:
//
//    Input: operations = ["++X","++X","X++"]
//    Output: 3
//    Explanation: The operations are performed as follows:
//    Initially, X = 0.
//    ++X: X is incremented by 1, X = 0 + 1 = 1.
//    ++X: X is incremented by 1, X = 1 + 1 = 2.
//    X++: X is incremented by 1, X = 2 + 1 = 3.
//    Example 3:
//
//    Input: operations = ["X++","++X","--X","X--"]
//    Output: 0
//    Explanation: The operations are performed as follows:
//    Initially, X = 0.
//    X++: X is incremented by 1, X = 0 + 1 = 1.
//    ++X: X is incremented by 1, X = 1 + 1 = 2.
//    --X: X is decremented by 1, X = 2 - 1 = 1.
//    X--: X is decremented by 1, X = 1 - 1 = 0.
//
//
//    Constraints:
//
//    1 <= operations.length <= 100
//    operations[i] will be either "++X", "X++", "--X", or "X--".
