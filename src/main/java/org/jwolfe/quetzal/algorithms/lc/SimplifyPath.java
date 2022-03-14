package org.jwolfe.quetzal.algorithms.lc;

import java.util.Deque;
import java.util.LinkedList;

public class SimplifyPath {
    class Solution {
        public String simplifyPath(String path) {
            if(path == null || path.length() == 0) {
                return null;
            }

            Deque<String> directories = new LinkedList<>();
            String[] parts = path.split("/");
            for(String part : parts) {
                part = part.trim();
                if(part.equals("") || part.equals(".")) {
                    continue;
                }

                if(part.equals("..")) {
                    if(directories.size() > 0) {
                        directories.removeLast();
                    }
                } else {
                    directories.addLast(part);
                }
            }

            StringBuilder builder = new StringBuilder();
            if(directories.size() == 0) {
                builder.append("/");
            } else {
                while(directories.size() > 0) {
                    builder.append("/" + directories.pollFirst());
                }
            }

            return builder.toString();
        }
    }

    class Solution_Correct_1 {
        public String simplifyPath(String path) {
            if(path == null || path.length() == 0) {
                return null;
            }

            Deque<String> deque = new LinkedList<>();
            String[] directories = path.split("/");
            for(String dir : directories) {
                dir = dir.trim();
                if(dir.equals("") || dir.equals(".")) {
                    continue;
                } else if(dir.equals("..")) {
                    if(deque.size() != 0) {
                        deque.removeLast();
                    }
                } else {
                    deque.addLast(dir);
                }
            }

            StringBuilder builder = new StringBuilder();
            if(deque.size() == 0) {
                builder.append("/");
            } else {
                while(deque.size() > 0) {
                    builder.append("/" + deque.pollFirst());
                }
            }

            return builder.toString();
        }
    }
}

//    71. Simplify Path
//    Medium
//    Given a string path, which is an absolute path (starting with a slash '/') to a file or directory in a Unix-style file system, convert it to the simplified canonical path.
//
//    In a Unix-style file system, a period '.' refers to the current directory, a double period '..' refers to the directory up a level, and any multiple consecutive slashes (i.e. '//') are treated as a single slash '/'. For this problem, any other format of periods such as '...' are treated as file/directory names.
//
//    The canonical path should have the following format:
//
//    The path starts with a single slash '/'.
//    Any two directories are separated by a single slash '/'.
//    The path does not end with a trailing '/'.
//    The path only contains the directories on the path from the root directory to the target file or directory (i.e., no period '.' or double period '..')
//    Return the simplified canonical path.
//
//
//
//    Example 1:
//
//    Input: path = "/home/"
//    Output: "/home"
//    Explanation: Note that there is no trailing slash after the last directory name.
//    Example 2:
//
//    Input: path = "/../"
//    Output: "/"
//    Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.
//    Example 3:
//
//    Input: path = "/home//foo/"
//    Output: "/home/foo"
//    Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.
//    Example 4:
//
//    Input: path = "/a/./b/../../c/"
//    Output: "/c"
//
//
//    Constraints:
//
//    1 <= path.length <= 3000
//    path consists of English letters, digits, period '.', slash '/' or '_'.
//    path is a valid absolute Unix path.