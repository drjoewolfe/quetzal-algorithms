package org.jwolfe.quetzal.algorithms.lc;

public class DeleteColumnsToMakeSorted {
    class Solution {
        public int minDeletionSize(String[] A) {
            if(A == null || A.length < 2) {
                return 0;
            }

            int m = A.length;
            int n = A[0].length();
            int unsortedColumns = 0;

            for(int i = 0; i < n; i++) {
                for(int j = 1; j < m; j++) {
                    if(A[j].charAt(i) < A[j - 1].charAt(i)) {
                        unsortedColumns++;
                        break;
                    }
                }
            }

            return unsortedColumns;
        }
    }

    class Solution_Correct_1 {
        public int minDeletionSize(String[] A) {
            if(A == null || A.length < 2) {
                return 0;
            }

            int n = A.length;
            int l = A[0].length();

            for(String word : A) {
                if(word.length() != l) {
                    return 0;
                }
            }

            int deletionCount = 0;
            for(int i = 0; i < l; i++) {
                boolean increasing = true;
                for(int j = 1; j < n; j++) {
                    if(A[j].charAt(i) < A[j - 1].charAt(i)) {
                        increasing = false;
                        break;
                    }
                }

                if(!increasing) {
                    deletionCount++;
                }
            }

            return deletionCount;
        }
    }
}

//    944. Delete Columns to Make Sorted
//    Easy
//    You are given an array of n strings strs, all of the same length.
//
//    The strings can be arranged such that there is one on each line, making a grid. For example, strs = ["abc", "bce", "cae"] can be arranged as:
//
//    abc
//    bce
//    cae
//    You want to delete the columns that are not sorted lexicographically. In the above example (0-indexed), columns 0 ('a', 'b', 'c') and 2 ('c', 'e', 'e') are sorted while column 1 ('b', 'c', 'a') is not, so you would delete column 1.
//
//    Return the number of columns that you will delete.
//
//
//
//    Example 1:
//
//    Input: strs = ["cba","daf","ghi"]
//    Output: 1
//    Explanation: The grid looks as follows:
//    cba
//    daf
//    ghi
//    Columns 0 and 2 are sorted, but column 1 is not, so you only need to delete 1 column.
//    Example 2:
//
//    Input: strs = ["a","b"]
//    Output: 0
//    Explanation: The grid looks as follows:
//    a
//    b
//    Column 0 is the only column and is sorted, so you will not delete any columns.
//    Example 3:
//
//    Input: strs = ["zyx","wvu","tsr"]
//    Output: 3
//    Explanation: The grid looks as follows:
//    zyx
//    wvu
//    tsr
//    All 3 columns are not sorted, so you will delete all 3.
//
//
//    Constraints:
//
//    n == strs.length
//    1 <= n <= 100
//    1 <= strs[i].length <= 1000
//    strs[i] consists of lowercase English letters.