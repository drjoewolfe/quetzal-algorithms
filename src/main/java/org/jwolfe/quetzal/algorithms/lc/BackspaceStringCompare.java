package org.jwolfe.quetzal.algorithms.lc;

import java.util.Stack;

public class BackspaceStringCompare {
    class Solution {
        public boolean backspaceCompare(String S, String T) {
            if(S == null && T == null) {
                return true;
            }

            if(S == null || T == null) {
                return false;
            }

            Stack<Character> SStack = getStack(S);
            Stack<Character> TStack = getStack(T);

            if(SStack.size() != TStack.size()) {
                return false;
            }

            while(!SStack.isEmpty()) {
                if(!SStack.pop().equals(TStack.pop())) {
                    return false;
                }
            }

            return true;
        }

        private Stack<Character> getStack(String s) {
            Stack<Character> stack = new Stack<>();
            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                if(c == '#') {
                    if(!stack.isEmpty()) {
                        stack.pop();
                    }
                } else {
                    stack.push(c);
                }
            }

            return stack;
        }
    }

    class Solution_Correct_1 {
        public boolean backspaceCompare(String S, String T) {
            if(S == null && T == null) {
                return true;
            }

            int i = S.length() - 1;
            int j = T.length() - 1;

            int skipS = 0;
            int skipT = 0;

            while(i >= 0 || j >= 0) {

                while(i >= 0) {
                    char sc = S.charAt(i);
                    if(sc == '#') {
                        skipS++;
                        i--;
                    } else if(skipS > 0) {
                        skipS--;
                        i--;
                    } else {
                        break;
                    }
                }

                while(j >= 0) {
                    char tc = T.charAt(j);
                    if(tc == '#') {
                        skipT++;
                        j--;
                    } else if(skipT > 0) {
                        skipT--;
                        j--;
                    } else {
                        break;
                    }
                }

                if(i >= 0 && j >= 0 && S.charAt(i) != T.charAt(j)) {
                    return false;
                }

                if((i >= 0) != (j >= 0)) {
                    return false;
                }

                i--;
                j--;
            }

            return true;
        }
    }

    class Solution_BuildStrings {
        public boolean backspaceCompare(String S, String T) {
            if(S == null && T == null) {
                return true;
            }

            if(S == null || T == null) {
                return false;
            }

            String parsedS = parseString(S);
            String parsedT = parseString(T);

            if(parsedS.equals(parsedT)) {
                return true;
            }

            return false;
        }

        private String parseString(String S) {
            StringBuilder builder = new StringBuilder();

            for(int i = 0; i < S.length(); i++) {
                char c = S.charAt(i);

                if(c == '#') {
                    if(builder.length() > 0) {
                        int l = builder.length() - 1;
                        builder.delete(l, l + 1);
                    }
                } else {
                    builder.append(c);
                }
            }

            return builder.toString();
        }
    }
}

//    844. Backspace String Compare
//    Easy
//    Given two strings s and t, return true if they are equal when both are typed into empty text editors. '#' means a backspace character.
//
//    Note that after backspacing an empty text, the text will continue empty.
//
//
//
//    Example 1:
//
//    Input: s = "ab#c", t = "ad#c"
//    Output: true
//    Explanation: Both s and t become "ac".
//    Example 2:
//
//    Input: s = "ab##", t = "c#d#"
//    Output: true
//    Explanation: Both s and t become "".
//    Example 3:
//
//    Input: s = "a#c", t = "b"
//    Output: false
//    Explanation: s becomes "c" while t becomes "b".
//
//
//    Constraints:
//
//    1 <= s.length, t.length <= 200
//    s and t only contain lowercase letters and '#' characters.
//
//
//    Follow up: Can you solve it in O(n) time and O(1) space?