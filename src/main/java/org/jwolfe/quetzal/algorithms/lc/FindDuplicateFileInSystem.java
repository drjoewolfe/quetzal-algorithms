package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindDuplicateFileInSystem {
    class Solution {
        public List<List<String>> findDuplicate(String[] paths) {
            List<List<String>> duplicates = new ArrayList<>();
            if(paths == null || paths.length == 0) {
                return duplicates;
            }

            Map<String, List<FileInfo>> map = new HashMap<>();

            for(String path : paths) {
                String[] components = path.split(" ");

                String directory = components[0];
                for(int i = 1; i < components.length; i++) {
                    String filePart = components[i];
                    String[] fileComponents = filePart.split("\\(");

                    String fileName = fileComponents[0];
                    String fileContents = fileComponents[1].substring(0, fileComponents[1].length() - 1);

                    FileInfo info = new FileInfo();
                    info.directory = directory;
                    info.fileName = fileName;
                    info.fileContents = fileContents;
                    info.filePart = filePart;

                    if(!map.containsKey(fileContents)) {
                        map.put(fileContents, new ArrayList<>());
                    }

                    map.get(fileContents).add(info);
                }
            }

            for(List<FileInfo> fileInfoList : map.values()) {
                if(fileInfoList.size() < 2) {
                    continue;
                }

                List<String> group = new ArrayList<>();
                duplicates.add(group);

                for(FileInfo info : fileInfoList) {
                    group.add(info.directory + "/" + info.fileName);
                }
            }

            return duplicates;
        }

        private class FileInfo {
            String directory;
            String fileName;
            String fileContents;
            String filePart;
        }
    }

    class Solution_Correct_1 {
        public List<List<String>> findDuplicate(String[] paths) {
            List<List<String>> duplicates = new ArrayList<>();
            if(paths == null || paths.length == 0) {
                return duplicates;
            }

            Map<String, List<String>> map = new HashMap<>();

            for(String pathComponent : paths) {
                String[] components = pathComponent.split(" ");
                if(components.length < 2) {
                    continue;
                }

                String path = components[0];
                for(int i = 1; i < components.length; i++) {
                    String fileComponent = components[i];
                    int fileStart = fileComponent.indexOf("(");
                    if(fileStart == -1 || fileComponent.charAt(fileComponent.length() - 1) != ')') {
                        continue;
                    }

                    String fileName = fileComponent.substring(0, fileStart);
                    String fileContents = fileComponent.substring(fileStart + 1, fileComponent.length() - 1);

                    String filePath = path + "/" + fileName;
                    if(!map.containsKey(fileContents)) {
                        map.put(fileContents, new ArrayList<>());
                    }

                    map.get(fileContents).add(filePath);
                }
            }

            for(var entry : map.entrySet()) {
                var list = entry.getValue();
                if(list.size() > 1) {
                    duplicates.add(list);
                }
            }

            return duplicates;
        }
    }

// ["root/a 1.txt(abcd) 2.txt(efgh)","root/c 3.txt(abcd)","root/c/d 4.txt(efgh)","root 4.txt(efgh)"]
// ["root/a 1.txt(abcd) 2.txt(efsfgh)","root/c 3.txt(abdfcd)","root/c/d 4.txt(efggdfh)"]
}

//    609. Find Duplicate File in System
//    Medium
//    Given a list paths of directory info, including the directory path, and all the files with contents in this directory, return all the duplicate files in the file system in terms of their paths. You may return the answer in any order.
//
//    A group of duplicate files consists of at least two files that have the same content.
//
//    A single directory info string in the input list has the following format:
//
//    "root/d1/d2/.../dm f1.txt(f1_content) f2.txt(f2_content) ... fn.txt(fn_content)"
//    It means there are n files (f1.txt, f2.txt ... fn.txt) with content (f1_content, f2_content ... fn_content) respectively in the directory "root/d1/d2/.../dm". Note that n >= 1 and m >= 0. If m = 0, it means the directory is just the root directory.
//
//    The output is a list of groups of duplicate file paths. For each group, it contains all the file paths of the files that have the same content. A file path is a string that has the following format:
//
//    "directory_path/file_name.txt"
//
//
//    Example 1:
//
//    Input: paths = ["root/a 1.txt(abcd) 2.txt(efgh)","root/c 3.txt(abcd)","root/c/d 4.txt(efgh)","root 4.txt(efgh)"]
//    Output: [["root/a/2.txt","root/c/d/4.txt","root/4.txt"],["root/a/1.txt","root/c/3.txt"]]
//    Example 2:
//
//    Input: paths = ["root/a 1.txt(abcd) 2.txt(efgh)","root/c 3.txt(abcd)","root/c/d 4.txt(efgh)"]
//    Output: [["root/a/2.txt","root/c/d/4.txt"],["root/a/1.txt","root/c/3.txt"]]
//
//
//    Constraints:
//
//    1 <= paths.length <= 2 * 104
//    1 <= paths[i].length <= 3000
//    1 <= sum(paths[i].length) <= 5 * 105
//    paths[i] consist of English letters, digits, '/', '.', '(', ')', and ' '.
//    You may assume no files or directories share the same name in the same directory.
//    You may assume each given directory info represents a unique directory. A single blank space separates the directory path and file info.
//
//
//    Follow up:
//
//    Imagine you are given a real file system, how will you search files? DFS or BFS?
//    If the file content is very large (GB level), how will you modify your solution?
//    If you can only read the file by 1kb each time, how will you modify your solution?
//    What is the time complexity of your modified solution? What is the most time-consuming part and memory-consuming part of it? How to optimize?
//    How to make sure the duplicated files you find are not false positive?