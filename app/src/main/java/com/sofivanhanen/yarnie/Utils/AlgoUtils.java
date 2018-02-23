package com.sofivanhanen.yarnie.Utils;

import com.sofivanhanen.yarnie.Data.Pattern;
import com.sofivanhanen.yarnie.Data.PatternList;

/**
 * Created by sofvanh on 31/01/18.
 */

public class AlgoUtils {

    // Helper method for finding bigger value
    public static int max(int a, int b) {
        if (b > a) return b;
        else return a;
    }

    // Helper method for finding the set with biggest yardage
    private static PatternList max(PatternList a, PatternList b) {
        int aYards = a.getTotalYards();
        int bYards = b.getTotalYards();
        if (aYards >= bYards) return a;
        else return b;
    }

    // First algorithm that works with Pattern objects
    // Uses the recursive knapsack algorithm in KKKK (Kisakoodarin käsikirja)
    public static PatternList patternKnapsackWeightOnly(Pattern[] patterns, int maxYardage) {
        if (patterns.length == 0 || maxYardage <= 0) return new PatternList();
        PatternList list = recursiveKnapsackWeightOnly(patterns, patterns.length-1, maxYardage);
        return list;
    }

    private static PatternList recursiveKnapsackWeightOnly(Pattern[] patterns, int maxIndex, int maxYards) {
        if (maxIndex == -1) {
            return new PatternList();
        }
        PatternList first = recursiveKnapsackWeightOnly(patterns, maxIndex - 1, maxYards);
        if (maxYards < patterns[maxIndex].getYardage()) return first;
        PatternList second = recursiveKnapsackWeightOnly(patterns, maxIndex - 1, maxYards - patterns[maxIndex].getYardage());
        second.add(patterns[maxIndex]);
        return max(first, second);
    }

    // TODO: Add an algorithm for calculating a pattern's value
    // Should be based on user's preferences, availability, and popularity

    // TODO: Add a kanpsack algorithm for patterns that have a value

}
