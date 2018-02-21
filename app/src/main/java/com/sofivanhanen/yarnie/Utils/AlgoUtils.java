package com.sofivanhanen.yarnie.Utils;

import com.sofivanhanen.yarnie.Data.Pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sofvanh on 31/01/18.
 */

public class AlgoUtils {

    // TODO: Get rid of the ArrayLists here

    // Helper method for finding bigger value
    public static int max(int a, int b) {
        if (b > a) return b;
        else return a;
    }

    // Helper method for finding the set with biggest yardage
    // TODO: Since we need to replace the ArrayLists anyway, should make a Pattern-specific
    // collection class that can calculate the yardage on it's own
    private static List<Pattern> max(List<Pattern> a, List<Pattern> b) {
        int aYards = getTotalYards(a);
        int bYards = getTotalYards(b);
        if (aYards >= bYards) return a;
        else return b;
    }

    public static int getTotalYards(List<Pattern> patterns) {
        int yards = 0;
        for (Pattern pattern : patterns) {
            yards += pattern.getYardage();
        }
        return yards;
    }

    // First algorithm that works with Pattern objects
    // Uses the recursive knapsack algorithm in KKKK (Kisakoodarin käsikirja)
    public static List<Pattern> patternKnapsackWeightOnly(Pattern[] patterns, int maxYardage) {
        if (patterns.length == 0 || maxYardage <= 0) return new ArrayList<>();
        List<Pattern> list = recursiveKnapsackWeightOnly(patterns, patterns.length-1, maxYardage);
        return list;
    }

    private static List<Pattern> recursiveKnapsackWeightOnly(Pattern[] patterns, int maxIndex, int maxYards) {
        if (maxIndex == -1) {
            return new ArrayList<Pattern>();
        }
        List<Pattern> first = recursiveKnapsackWeightOnly(patterns, maxIndex - 1, maxYards);
        if (maxYards < patterns[maxIndex].getYardage()) return first;
        List<Pattern> second = recursiveKnapsackWeightOnly(patterns, maxIndex - 1, maxYards - patterns[maxIndex].getYardage());
        second.add(patterns[maxIndex]);
        return max(first, second);
    }

    // TODO: Add an algorithm for calculating a pattern's value
    // Should be based on user's preferences, availability, and popularity

    // TODO: Add a kanpsack algorithm for patterns that have a value

}
