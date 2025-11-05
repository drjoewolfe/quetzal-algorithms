package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class FindXSumOfAllKLongSubarraysII {
    class Solution {
        public long[] findXSum(int[] nums, int k, int x) {
            int n = nums.length;
            if (n == 0 || k == 0 || x == 0 || k > n) {
                return new long[0];
            }

            long[] ans = new long[n - k + 1];
            Window window = new Window(x);

            for (int i = 0; i < n; i++) {
                window.add(nums[i]);
                if (i >= k) window.remove(nums[i - k]);
                if (i >= k - 1) ans[i - k + 1] = window.getXSum();
            }

            return ans;
        }

        private static class Window {
            private final int x;
            private long xsum = 0;
            private final Map<Integer, Integer> counts = new HashMap<>();

            // comparator: frequency desc, value desc
            private final Comparator<ValueFrequency> comp = Comparator.naturalOrder();

            // top-x (best first). yWindow uses the *same* comparator so its first() is the best outside top-x.
            private final TreeSet<ValueFrequency> xWindow = new TreeSet<>(comp);
            private final TreeSet<ValueFrequency> yWindow = new TreeSet<>(comp);

            public Window(int x) {
                this.x = x;
            }

            public long getXSum() {
                return xsum;
            }

            public void add(int val) {
                int oldFreq = counts.getOrDefault(val, 0);
                if (oldFreq > 0) removeVF(new ValueFrequency(val, oldFreq));

                int newFreq = oldFreq + 1;
                counts.put(val, newFreq);
                addVF(new ValueFrequency(val, newFreq));
            }

            public void remove(int val) {
                // note: remove is only called for elements that must exist in the window
                int oldFreq = counts.get(val);
                removeVF(new ValueFrequency(val, oldFreq));

                int newFreq = oldFreq - 1;
                if (newFreq == 0) counts.remove(val);
                else {
                    counts.put(val, newFreq);
                    addVF(new ValueFrequency(val, newFreq));
                }
            }

            private void addVF(ValueFrequency vf) {
                // Add to xWindow first (simpler) and rebalance
                xWindow.add(vf);
                xsum += 1L * vf.value * vf.frequency;

                if (xWindow.size() > x) {
                    // smallest (worst) within top-x is at xWindow.last()
                    ValueFrequency evict = xWindow.last();
                    xWindow.remove(evict);
                    xsum -= 1L * evict.value * evict.frequency;
                    yWindow.add(evict);
                }
            }

            private void removeVF(ValueFrequency vf) {
                // Try removing from xWindow first
                if (xWindow.remove(vf)) {
                    xsum -= 1L * vf.value * vf.frequency;
                    // promote best from yWindow (if any) into xWindow
                    if (!yWindow.isEmpty()) {
                        ValueFrequency promote = yWindow.pollFirst(); // best outside top-x
                        xWindow.add(promote);
                        xsum += 1L * promote.value * promote.frequency;
                    }
                } else {
                    // not in xWindow -> remove from yWindow (if present)
                    yWindow.remove(vf);
                }
            }
        }

        private static class ValueFrequency implements Comparable<ValueFrequency> {
            int value;
            int frequency;

            public ValueFrequency(int value, int frequency) {
                this.value = value;
                this.frequency = frequency;
            }

            @Override
            public int compareTo(ValueFrequency o) {
                // Descending by frequency, then descending by value
                if (this.frequency != o.frequency)
                    return Integer.compare(o.frequency, this.frequency);
                if (this.value != o.value)
                    return Integer.compare(o.value, this.value);
                return 0;
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (!(obj instanceof ValueFrequency)) return false;
                ValueFrequency vf = (ValueFrequency) obj;
                return this.value == vf.value && this.frequency == vf.frequency;
            }

            @Override
            public int hashCode() {
                return Objects.hash(value, frequency);
            }

            @Override
            public String toString() {
                return "(" + value + "," + frequency + ")";
            }
        }
    }

    class Solution_Runtime_Errors {
        public long[] findXSum(int[] nums, int k, int x) {
            if (nums == null || nums.length == 0 || x == 0 || k == 0) {
                return new long[0];
            }

            int n = nums.length;

            long[] ans = new long[n - k + 1];
            int index = 0;

            Window window = new Window(x);

            for (int i = 0; i < n; i++) {
                int val = nums[i];
                window.add(val);

                if (i >= k) {
                    int lval = nums[i - k];
                    window.remove(lval);
                }

                if (i >= k - 1) {
                    long xsum = window.getXSum();
                    ans[index++] = xsum;
                }
            }

            return ans;
        }

        private class Window {
            private int x;
            private long xsum;

            Map<Integer, Integer> counts;
            TreeSet<ValueFrequency> xWindow;
            TreeSet<ValueFrequency> yWindow;

            public Window(int x) {
                this.x = x;
                this.xsum = 0;
                this.counts = new HashMap<>();
                this.xWindow = new TreeSet<>();
                this.yWindow = new TreeSet<>();
            }

            public long getXSum() {
                return xsum;
            }

            public void add(int val) {
                System.out.println("+" + val);
                if (counts.containsKey(val)) {
                    var pvf = new ValueFrequency(val, counts.get(val));
                    internalRemove(pvf);
                }

                counts.put(val, counts.getOrDefault(val, 0) + 1);
                var nvf = new ValueFrequency(val, counts.get(val));
                internalAdd(nvf);
            }

            private void remove(int val) {
                System.out.println("-" + val);
                var pvf = new ValueFrequency(val, counts.get(val));
                internalRemove(pvf);
                counts.put(val, counts.get(val) - 1);
                if (counts.get(val) > 0) {
                    var nvf = new ValueFrequency(val, counts.get(val));
                    internalAdd(nvf);
                } else {
                    counts.remove(val);
                }
            }

            private void internalAdd(ValueFrequency vf) {
                if (xWindow.size() < x || vf.frequency > xWindow.first().frequency) {
                    xsum += 1L * vf.frequency * vf.value;
                    xWindow.add(vf);

                    if (xWindow.size() == x) {
                        var evictedVF = xWindow.first();
                        xsum -= 1L * evictedVF.frequency * evictedVF.value;
                        xWindow.remove(evictedVF);
                        yWindow.add(evictedVF);
                    }
                } else {
                    yWindow.add(vf);
                }
            }

            private void internalRemove(ValueFrequency vf) {
                if (vf.frequency >= xWindow.first().frequency) {
                    xsum -= 1L * vf.frequency * vf.value;
                    xWindow.remove(vf);

                    if (!yWindow.isEmpty()) {
                        var injectedVF = yWindow.last();
                        xsum += 1L * injectedVF.frequency * injectedVF.value;
                        xWindow.add(injectedVF);
                        yWindow.remove(injectedVF);
                    }
                } else {
                    yWindow.remove(vf);
                }
            }
        }

        private class ValueFrequency implements Comparable<ValueFrequency> {
            int value;
            int frequency;

            public ValueFrequency(int value, int frequency) {
                this.value = value;
                this.frequency = frequency;
            }

            @Override
            public int compareTo(ValueFrequency other) {
                if (this.frequency == other.frequency) {
                    return this.value - other.value;
                }

                return this.frequency - other.frequency;
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }

                if (obj == null || !(obj instanceof ValueFrequency)) {
                    return false;
                }

                var vf = (ValueFrequency) obj;
                return value == vf.value && frequency == vf.frequency;
            }

            @Override
            public int hashCode() {
                return Objects.hash(value, frequency);
            }
        }
    }

    class Solution_TLE {
        public long[] findXSum(int[] nums, int k, int x) {
            if (nums == null || nums.length == 0 || x == 0 || k == 0) {
                return new long[0];
            }

            int n = nums.length;
            long[] ans = new long[n - k + 1];

            for (int i = 0; i <= n - k; i++) {
                Map<Integer, Integer> counts = new HashMap<>();
                for (int j = i; j < i + k; j++) {
                    counts.put(nums[j], counts.getOrDefault(nums[j], 0) + 1);
                }

                List<int[]> frequencies = new ArrayList<>();
                for (var entry : counts.entrySet()) {
                    frequencies.add(new int[]{entry.getValue(), entry.getKey()});
                }

                frequencies.sort((a, b) -> (a[0] != b[0]) ? b[0] - a[0] : b[1] - a[1]);
                long xsum = 0;
                for (int j = 0; j < x && j < frequencies.size(); j++) {
                    xsum += 1L * frequencies.get(j)[0] * frequencies.get(j)[1];
                }

                ans[i] = xsum;
            }

            return ans;
        }
    }
}

//    3321. Find X-Sum of All K-Long Subarrays II
//    Hard
//    You are given an array nums of n integers and two integers k and x.
//
//    The x-sum of an array is calculated by the following procedure:
//
//    Count the occurrences of all elements in the array.
//    Keep only the occurrences of the top x most frequent elements. If two elements have the same number of occurrences, the element with the bigger value is considered more frequent.
//    Calculate the sum of the resulting array.
//    Note that if an array has less than x distinct elements, its x-sum is the sum of the array.
//
//    Return an integer array answer of length n - k + 1 where answer[i] is the x-sum of the subarray nums[i..i + k - 1].
//
//
//
//    Example 1:
//
//    Input: nums = [1,1,2,2,3,4,2,3], k = 6, x = 2
//
//    Output: [6,10,12]
//
//    Explanation:
//
//    For subarray [1, 1, 2, 2, 3, 4], only elements 1 and 2 will be kept in the resulting array. Hence, answer[0] = 1 + 1 + 2 + 2.
//    For subarray [1, 2, 2, 3, 4, 2], only elements 2 and 4 will be kept in the resulting array. Hence, answer[1] = 2 + 2 + 2 + 4. Note that 4 is kept in the array since it is bigger than 3 and 1 which occur the same number of times.
//    For subarray [2, 2, 3, 4, 2, 3], only elements 2 and 3 are kept in the resulting array. Hence, answer[2] = 2 + 2 + 2 + 3 + 3.
//    Example 2:
//
//    Input: nums = [3,8,7,8,7,5], k = 2, x = 2
//
//    Output: [11,15,15,15,12]
//
//    Explanation:
//
//    Since k == x, answer[i] is equal to the sum of the subarray nums[i..i + k - 1].
//
//
//
//    Constraints:
//
//    nums.length == n
//    1 <= n <= 105
//    1 <= nums[i] <= 109
//    1 <= x <= k <= nums.length