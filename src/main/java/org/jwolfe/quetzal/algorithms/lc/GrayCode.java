package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;

public class GrayCode {
    class Solution {
        public List<Integer> grayCode(int n) {
            List<Integer> codes;
            if(n < 1) {
                return new ArrayList<>();
            }

            if(n == 1) {
                codes = new ArrayList<>();
                codes.add(0);
                codes.add(1);

                return codes;
            }

            codes = grayCode(n - 1);

            for(int i = codes.size() - 1; i >= 0; i--) {
                int code = codes.get(i);
                code = (1 << n - 1) | code;

                codes.add(code);
            }

            return codes;
        }
    }

    class Solution_Correct_2 {
        public List<Integer> grayCode(int n) {
            List<Integer> codes = new ArrayList<>();
            if(n < 1) {
                return codes;
            }

            int count = (int) Math.pow(2, n);
            for(int i = 1; i <= count; i++) {
                codes.add(0);
            }

            for(int j = n - 1; j >= 0; j--) {
                int reps = (int) Math.pow(2, j);

                boolean one = false;
                boolean flag = false;
                for(int i = 1; i <= count; i++) {
                    int val = codes.get(i - 1) * 2 + (one ? 1 : 0);
                    codes.set(i - 1, val);

                    if(i % reps == 0) {
                        if(!one & !flag) {
                            one = true;
                        } else if(one & !flag) {
                            one = true;
                            flag = true;
                        } else if(one & flag) {
                            one = false;
                        } else {
                            one = false;
                            flag = false;
                        }
                    }
                }
            }

            return codes;
        }
    }
}

//    89. Gray Code
//    Medium
//    An n-bit gray code sequence is a sequence of 2n integers where:
//
//    Every integer is in the inclusive range [0, 2n - 1],
//    The first integer is 0,
//    An integer appears no more than once in the sequence,
//    The binary representation of every pair of adjacent integers differs by exactly one bit, and
//    The binary representation of the first and last integers differs by exactly one bit.
//    Given an integer n, return any valid n-bit gray code sequence.
//
//
//
//    Example 1:
//
//    Input: n = 2
//    Output: [0,1,3,2]
//    Explanation:
//    The binary representation of [0,1,3,2] is [00,01,11,10].
//    - 00 and 01 differ by one bit
//    - 01 and 11 differ by one bit
//    - 11 and 10 differ by one bit
//    - 10 and 00 differ by one bit
//    [0,2,3,1] is also a valid gray code sequence, whose binary representation is [00,10,11,01].
//    - 00 and 10 differ by one bit
//    - 10 and 11 differ by one bit
//    - 11 and 01 differ by one bit
//    - 01 and 00 differ by one bit
//    Example 2:
//
//    Input: n = 1
//    Output: [0,1]
//
//
//    Constraints:
//
//    1 <= n <= 16