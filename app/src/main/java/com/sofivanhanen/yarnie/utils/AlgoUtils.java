package com.sofivanhanen.yarnie.utils;


import com.sofivanhanen.yarnie.data.Pattern;
import com.sofivanhanen.yarnie.data.PatternList;

/**
 * Created by sofvanh on 31/01/18.
 */

public class AlgoUtils {

    // Helper method for finding the set with biggest yardage
    private static PatternList maxYardage(PatternList a, PatternList b) {
        int aYards = a.getTotalYards();
        int bYards = b.getTotalYards();
        if (aYards >= bYards) return a;
        else return b;
    }

    // Helper method for finding the set with biggest value
    private static PatternList maxValue(PatternList a, PatternList b) {
        int aValue = a.getTotalValue();
        int bValue = b.getTotalValue();
        if (aValue >= bValue) return a;
        else return b;
    }

    public static int calculatePatternValue(Pattern pattern) {
        if (pattern.getPublished() == null || pattern.getProjects_count() < 1) {
            // Pattern not correctly instantiated
            return -1;
        }

        // Final value calculation:
        // yardage * ((5 / 5+(years since published)) * (if free, 1; if not, 0.75) + (number of projects / 200 000))

        int yardage = pattern.getYardage();
        int yearsSincePublish;
        if (pattern.getPublished() != null) {
            long millisecondsInYear = 31556952000l;
            yearsSincePublish = (int) ((System.currentTimeMillis() - pattern.getPublished().getTime()) / millisecondsInYear);
        } else {
            yearsSincePublish = 3; // We don't know actual publishing year
        }
        double free;
        if (pattern.getFree()) free = 1;
            else free = 0.75;
        int numberOfProjects = pattern.getProjects_count();

        double value = ((double)yardage * ((5.0/(5+yearsSincePublish))*(free)+(numberOfProjects/200000.0)));

        return (int) value;

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
        return maxYardage(first, second);
    }

    // For when user wants to use meters instead of yards. Rounds down.
    private static final double ONE_METER_IN_YARDS = 1.0936133;
    public static int metersToYards(int meters) {
        return (int)(meters * ONE_METER_IN_YARDS);
    }

    // Same as above, except now we use pattern's value as value
    public static PatternList patternKnapsack(Pattern[] patterns, int maxYardage) {
        if (patterns.length == 0 || maxYardage <= 0) return new PatternList();
        PatternList list = recursiveKnapsack(patterns, patterns.length-1, maxYardage);
        return list;
    }

    private static PatternList recursiveKnapsack(Pattern[] patterns, int maxIndex, int maxYards) {
        if (maxIndex == -1) {
            return new PatternList();
        }
        PatternList first = recursiveKnapsackWeightOnly(patterns, maxIndex - 1, maxYards);
        if (maxYards < patterns[maxIndex].getYardage()) return first;
        PatternList second = recursiveKnapsackWeightOnly(patterns, maxIndex - 1, maxYards - patterns[maxIndex].getYardage());
        second.add(patterns[maxIndex]);
        return maxValue(first, second);
    }

}
