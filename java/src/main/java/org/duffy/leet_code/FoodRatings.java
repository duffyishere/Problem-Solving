package org.duffy.leet_code;

import java.util.*;

public class FoodRatings {
    Map<String, TreeSet<String>> sortedFood = new HashMap<>();
    Map<String, Integer> foodRating = new HashMap<>();
    Map<String, String> foodCuisines = new HashMap<>();

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        int n = foods.length;

        for (int i = 0; i < n; i++) {
            foodRating.put(foods[i], ratings[i]);
            this.foodCuisines.put(foods[i], cuisines[i]);
            sortedFood
                    .computeIfAbsent(cuisines[i], k -> new TreeSet<>(new FoodComparator()))
                    .add(foods[i]);
        }
    }

    public void changeRating(String food, int newRating) {
        TreeSet<String> currentSet = sortedFood.get(foodCuisines.get(food));
        if (currentSet != null) {
            currentSet.remove(food);
            foodRating.put(food, newRating);
            currentSet.add(food);
        }
    }

    public String highestRated(String cuisine) {
        return sortedFood.get(cuisine).last();
    }

    private class FoodComparator implements Comparator<String> {
        public int compare(String o1, String o2) {
            if (foodRating.get(o1) < foodRating.get(o2)) {
                return -1;
            } else if (foodRating.get(o2) < foodRating.get(o1)) {
                return 1;
            } else {
                return -o1.compareTo(o2);
            }
        }
    }

    public static void main(String[] args) {
        FoodRatings obj = new FoodRatings(
                new String[] {"kimchi", "miso", "sushi", "moussaka", "ramen", "bulgogi"},
                new String[] {"korean", "japanese", "japanese", "greek", "japanese", "korean"},
                new int[] {9, 12, 8, 15, 14, 7});

        obj.changeRating("ramen", 16);
    }
}