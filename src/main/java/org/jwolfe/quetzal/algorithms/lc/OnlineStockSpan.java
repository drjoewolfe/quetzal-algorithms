package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class OnlineStockSpan {
    class StockSpanner {
        Stack<StockSpan> stack;

        public StockSpanner() {
            stack = new Stack<>();
        }

        public int next(int price) {
            StockSpan stockSpan = new StockSpan(price, 1);

            while(!stack.isEmpty()
                    && stack.peek().price <= price) {
                stockSpan.span += stack.pop().span;
            }

            stack.push(stockSpan);
            return stockSpan.span;
        }

        private class StockSpan {
            int price;
            int span;

            public StockSpan(int price, int span) {
                this.price = price;
                this.span = span;
            }
        }
    }

    class StockSpanner_Correct_1 {

        class StockSpan {
            int price;
            int span;

            public StockSpan(int price, int span) {
                this.price = price;
                this.span = span;
            }
        }

        Stack<StockSpan> stockSpans;

        public StockSpanner_Correct_1() {
            stockSpans = new Stack<>();
        }

        public int next(int price) {
            int span = 1;
            while(!stockSpans.isEmpty()
                    && stockSpans.peek().price <= price) {
                StockSpan stockSpan = stockSpans.pop();
                span += stockSpan.span;
            }

            stockSpans.push(new StockSpan(price, span));
            return span;
        }
    }

    /**
     * Your StockSpanner object will be instantiated and called as such:
     * StockSpanner obj = new StockSpanner();
     * int param_1 = obj.next(price);
     */

    class StockSpanner_Brute {
        List<Integer> dailyQuotes;

        public StockSpanner_Brute() {
            dailyQuotes = new ArrayList<>();
        }

        public int next(int price) {
            dailyQuotes.add(price);

            int span = 0;
            for(int i = dailyQuotes.size() - 1; i >= 0; i--) {
                if(dailyQuotes.get(i) > price) {
                    break;
                }

                span++;
            }

            return span;
        }
    }
}

//    901. Online Stock Span
//    Medium
//    Design an algorithm that collects daily price quotes for some stock and returns the span of that stock's price for the current day.
//
//    The span of the stock's price today is defined as the maximum number of consecutive days (starting from today and going backward) for which the stock price was less than or equal to today's price.
//
//    For example, if the price of a stock over the next 7 days were [100,80,60,70,60,75,85], then the stock spans would be [1,1,1,2,1,4,6].
//    Implement the StockSpanner class:
//
//    StockSpanner() Initializes the object of the class.
//    int next(int price) Returns the span of the stock's price given that today's price is price.
//
//
//    Example 1:
//
//    Input
//    ["StockSpanner", "next", "next", "next", "next", "next", "next", "next"]
//    [[], [100], [80], [60], [70], [60], [75], [85]]
//    Output
//    [null, 1, 1, 1, 2, 1, 4, 6]
//
//    Explanation
//    StockSpanner stockSpanner = new StockSpanner();
//    stockSpanner.next(100); // return 1
//    stockSpanner.next(80);  // return 1
//    stockSpanner.next(60);  // return 1
//    stockSpanner.next(70);  // return 2
//    stockSpanner.next(60);  // return 1
//    stockSpanner.next(75);  // return 4, because the last 4 prices (including today's price of 75) were less than or equal to today's price.
//    stockSpanner.next(85);  // return 6
//
//
//    Constraints:
//
//    1 <= price <= 105
//    At most 104 calls will be made to next.