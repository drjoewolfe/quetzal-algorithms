package org.jwolfe.quetzal.algorithms.lc;

public class DetermineColorOfAChessboardSquare {
    class Solution {
        public boolean squareIsWhite(String coordinates) {
            if(coordinates == null || coordinates.length() != 2) {
                return false;
            }

            char col = coordinates.charAt(0);
            int row = coordinates.charAt(1) - '0';

            if(col == 'a' || col == 'c' || col == 'e' || col == 'g') {
                return (row % 2) == 0;
            } else {
                return (row % 2) != 0;
            }
        }
    }

    class Solution_Approach_1 {
        public boolean squareIsWhite(String coordinates) {
            if(coordinates == null || coordinates.length() != 2) {
                return false;
            }

            char col = coordinates.charAt(0);
            int row = coordinates.charAt(1) - '0';

            // a0 is black
            boolean white = false;

            for(int j = 'b'; j <= col; j++) {
                white = !white;
            }

            for(int i = 1; i < row; i++) {
                white = ! white;
            }

            return white;
        }
    }
}

//    1812. Determine Color of a Chessboard Square
//    Easy
//    You are given coordinates, a string that represents the coordinates of a square of the chessboard. Below is a chessboard for your reference.
//
//
//
//    Return true if the square is white, and false if the square is black.
//
//    The coordinate will always represent a valid chessboard square. The coordinate will always have the letter first, and the number second.
//
//
//
//    Example 1:
//
//    Input: coordinates = "a1"
//    Output: false
//    Explanation: From the chessboard above, the square with coordinates "a1" is black, so return false.
//    Example 2:
//
//    Input: coordinates = "h3"
//    Output: true
//    Explanation: From the chessboard above, the square with coordinates "h3" is white, so return true.
//    Example 3:
//
//    Input: coordinates = "c7"
//    Output: false
//
//
//    Constraints:
//
//    coordinates.length == 2
//    'a' <= coordinates[0] <= 'h'
//    '1' <= coordinates[1] <= '8'
