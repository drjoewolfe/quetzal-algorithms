package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class DesignAFoodRatingSystem {
    class FoodRatings {
        Map<String, FoodInfo> foodMap;
        Map<String, TreeSet<FoodInfo>> cuisineMap;

        public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
            foodMap = new HashMap<>();
            cuisineMap = new HashMap<>();

            for (int i = 0; i < foods.length; i++) {
                String food = foods[i];
                String cuisine = cuisines[i];
                int rating = ratings[i];

                FoodInfo foodInfo = new FoodInfo(food, cuisine, rating);
                foodMap.put(food, foodInfo);

                if (!cuisineMap.containsKey(cuisine)) {
                    cuisineMap.put(cuisine, new TreeSet<>((a, b) -> {
                        if (a.rating == b.rating) {
                            return a.food.compareTo(b.food);
                        }

                        return b.rating - a.rating;
                    }
                    ));
                }

                cuisineMap.get(cuisine).add(foodInfo);
            }
        }

        public void changeRating(String food, int newRating) {
            var foodInfo = foodMap.get(food);

            var cuisineFoods = cuisineMap.get(foodInfo.cuisine);
            cuisineFoods.remove(foodInfo);

            foodInfo.rating = newRating;
            cuisineFoods.add(foodInfo);
        }

        public String highestRated(String cuisine) {
            var cuisineFoods = cuisineMap.get(cuisine);
            return cuisineFoods.first().food;
        }

        class FoodInfo {
            String food;
            String cuisine;
            int rating;

            public FoodInfo(String food, String cuisine, int rating) {
                this.food = food;
                this.cuisine = cuisine;
                this.rating = rating;
            }
        }
    }

    class FoodRatings_Correct_2 {
        Map<String, FoodRating> foodRatingMap;
        Map<String, String> foodCuisineMap;
        Map<String, TreeSet<FoodRating>> cuisineFoodRatingMap;

        public FoodRatings_Correct_2(String[] foods, String[] cuisines, int[] ratings) {
            int n = foods.length;

            foodRatingMap = new HashMap<>();
            foodCuisineMap = new HashMap<>();
            cuisineFoodRatingMap = new HashMap<>();

            for (int i = 0; i < n; i++) {
                String food = foods[i];
                String cuisine = cuisines[i];
                int rating = ratings[i];

                FoodRating foodRating = new FoodRating(food, rating);

                foodRatingMap.put(food, foodRating);
                foodCuisineMap.put(food, cuisine);
                if (!cuisineFoodRatingMap.containsKey(cuisine)) {
                    cuisineFoodRatingMap.put(cuisine, new TreeSet<>());
                }

                cuisineFoodRatingMap.get(cuisine).add(foodRating);
            }
        }

        public void changeRating(String food, int newRating) {
            String cuisine = foodCuisineMap.get(food);
            var foodRating = foodRatingMap.get(food);
            var set = cuisineFoodRatingMap.get(cuisine);

            set.remove(foodRating);
            foodRating.rating = newRating;
            set.add(foodRating);
        }

        public String highestRated(String cuisine) {
            return cuisineFoodRatingMap.get(cuisine).last().food;
        }

        private class FoodRating implements Comparable<FoodRating> {
            String food;
            int rating;

            public FoodRating(String food, int rating) {
                this.food = food;
                this.rating = rating;
            }

            @Override
            public int compareTo(FoodRating other) {
                if (this.rating == other.rating) {
                    return other.food.compareTo(this.food);
                }

                return this.rating - other.rating;
            }

            @Override
            public String toString() {
                return "(" + this.food + ", " + this.rating + ")";
            }
        }
    }

    class FoodRatings_Correct_1 {
        Map<String, TreeSet<FoodRating>> cuisineMap;
        Map<String, String> foodCuisineMap;
        Map<String, FoodRating> foodRatingMap;

        public FoodRatings_Correct_1(String[] foods, String[] cuisines, int[] ratings) {
            cuisineMap = new HashMap<>();
            foodCuisineMap = new HashMap<>();
            foodRatingMap = new HashMap<>();

            for (int i = 0; i < foods.length; i++) {
                String food = foods[i];
                String cuisine = cuisines[i];
                int rating = ratings[i];

                var foodRating = new FoodRating(food, rating);
                foodCuisineMap.put(food, cuisine);
                foodRatingMap.put(food, foodRating);
                if (!cuisineMap.containsKey(cuisine)) {
                    cuisineMap.put(cuisine, new TreeSet<>());
                }

                cuisineMap.get(cuisine).add(foodRating);
            }
        }

        public void changeRating(String food, int newRating) {
            String cuisine = foodCuisineMap.get(food);
            var set = cuisineMap.get(cuisine);
            var foodRating = foodRatingMap.get(food);

            set.remove(foodRating);
            foodRating.rating = newRating;
            set.add(foodRating);
        }

        public String highestRated(String cuisine) {
            return cuisineMap.get(cuisine).last().food;
        }

        private class FoodRating implements Comparable<FoodRating> {
            String food;
            int rating;

            public FoodRating(String food, int rating) {
                this.food = food;
                this.rating = rating;
            }

            @Override
            public int compareTo(FoodRating other) {
                if (this.rating == other.rating) {
                    return other.food.compareTo(this.food);
                }

                return this.rating - other.rating;
            }
        }
    }

    class FoodRatings_TLE {

        Map<String, TreeSet<FoodRating>> cuisineMap;
        Map<String, String> foodCuisineMap;

        public FoodRatings_TLE(String[] foods, String[] cuisines, int[] ratings) {
            cuisineMap = new HashMap<>();
            foodCuisineMap = new HashMap<>();

            for (int i = 0; i < foods.length; i++) {
                String food = foods[i];
                String cuisine = cuisines[i];
                int rating = ratings[i];

                var foodRating = new FoodRating(food, rating);
                foodCuisineMap.put(food, cuisine);
                if (!cuisineMap.containsKey(cuisine)) {
                    cuisineMap.put(cuisine, new TreeSet<>());
                }

                cuisineMap.get(cuisine).add(foodRating);
            }
        }

        public void changeRating(String food, int newRating) {
            String cuisine = foodCuisineMap.get(food);
            var set = cuisineMap.get(cuisine);
            FoodRating foodRating = null;
            for (var entry : set) {
                if (entry.food.equals(food)) {
                    foodRating = entry;
                    break;
                }
            }

            set.remove(foodRating);
            foodRating.rating = newRating;
            set.add(foodRating);
        }

        public String highestRated(String cuisine) {
            return cuisineMap.get(cuisine).last().food;
        }

        private class FoodRating implements Comparable<FoodRating> {
            String food;
            int rating;

            public FoodRating(String food, int rating) {
                this.food = food;
                this.rating = rating;
            }

            @Override
            public int compareTo(FoodRating other) {
                if (this.rating == other.rating) {
                    return other.food.compareTo(this.food);
                }

                return this.rating - other.rating;
            }
        }
    }

/**
 * Your FoodRatings object will be instantiated and called as such:
 * FoodRatings obj = new FoodRatings(foods, cuisines, ratings);
 * obj.changeRating(food,newRating);
 * String param_2 = obj.highestRated(cuisine);
 */

// ["FoodRatings","highestRated","highestRated","changeRating","highestRated","changeRating","highestRated"]
// [[["kimchi","miso","sushi","moussaka","ramen","bulgogi"],["korean","japanese","japanese","greek","japanese","korean"],[9,12,8,15,14,7]],["korean"],["japanese"],["sushi",16],["japanese"],["ramen",16],["japanese"]]

// ["FoodRatings","changeRating","highestRated","changeRating","changeRating","highestRated"]
// [[["czopaaeyl","lxoozsbh","kbaxapl"],["dmnuqeatj","dmnuqeatj","dmnuqeatj"],[11,2,15]],["czopaaeyl",12],["dmnuqeatj"],["kbaxapl",8],["lxoozsbh",5],["dmnuqeatj"]]
}

//    2353. Design a Food Rating System
//    Medium
//    Design a food rating system that can do the following:
//
//    Modify the rating of a food item listed in the system.
//    Return the highest-rated food item for a type of cuisine in the system.
//    Implement the FoodRatings class:
//
//    FoodRatings(String[] foods, String[] cuisines, int[] ratings) Initializes the system. The food items are described by foods, cuisines and ratings, all of which have a length of n.
//    foods[i] is the name of the ith food,
//    cuisines[i] is the type of cuisine of the ith food, and
//    ratings[i] is the initial rating of the ith food.
//    void changeRating(String food, int newRating) Changes the rating of the food item with the name food.
//    String highestRated(String cuisine) Returns the name of the food item that has the highest rating for the given type of cuisine. If there is a tie, return the item with the lexicographically smaller name.
//    Note that a string x is lexicographically smaller than string y if x comes before y in dictionary order, that is, either x is a prefix of y, or if i is the first position such that x[i] != y[i], then x[i] comes before y[i] in alphabetic order.
//
//
//
//    Example 1:
//
//    Input
//    ["FoodRatings", "highestRated", "highestRated", "changeRating", "highestRated", "changeRating", "highestRated"]
//    [[["kimchi", "miso", "sushi", "moussaka", "ramen", "bulgogi"], ["korean", "japanese", "japanese", "greek", "japanese", "korean"], [9, 12, 8, 15, 14, 7]], ["korean"], ["japanese"], ["sushi", 16], ["japanese"], ["ramen", 16], ["japanese"]]
//    Output
//    [null, "kimchi", "ramen", null, "sushi", null, "ramen"]
//
//    Explanation
//    FoodRatings foodRatings = new FoodRatings(["kimchi", "miso", "sushi", "moussaka", "ramen", "bulgogi"], ["korean", "japanese", "japanese", "greek", "japanese", "korean"], [9, 12, 8, 15, 14, 7]);
//    foodRatings.highestRated("korean"); // return "kimchi"
//    // "kimchi" is the highest rated korean food with a rating of 9.
//    foodRatings.highestRated("japanese"); // return "ramen"
//    // "ramen" is the highest rated japanese food with a rating of 14.
//    foodRatings.changeRating("sushi", 16); // "sushi" now has a rating of 16.
//    foodRatings.highestRated("japanese"); // return "sushi"
//    // "sushi" is the highest rated japanese food with a rating of 16.
//    foodRatings.changeRating("ramen", 16); // "ramen" now has a rating of 16.
//    foodRatings.highestRated("japanese"); // return "ramen"
//    // Both "sushi" and "ramen" have a rating of 16.
//    // However, "ramen" is lexicographically smaller than "sushi".
//
//
//    Constraints:
//
//    1 <= n <= 2 * 104
//    n == foods.length == cuisines.length == ratings.length
//    1 <= foods[i].length, cuisines[i].length <= 10
//    foods[i], cuisines[i] consist of lowercase English letters.
//    1 <= ratings[i] <= 108
//    All the strings in foods are distinct.
//    food will be the name of a food item in the system across all calls to changeRating.
//    cuisine will be a type of cuisine of at least one food item in the system across all calls to highestRated.
//    At most 2 * 104 calls in total will be made to changeRating and highestRated.