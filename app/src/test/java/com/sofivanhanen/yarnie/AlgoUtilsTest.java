package com.sofivanhanen.yarnie;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by sofvanh on 02/02/18.
 */

public class AlgoUtilsTest {

    // Making a simple test here for starters
    // This will have to be changed when the algorithm's functionality is changed.
    @Test
    public void knapsackWeightOnly_empty() throws Exception {
        assertEquals(AlgoUtils.knapsackWeightOnly(new int[0], 0), new ArrayList<>());
    }

    @Test
    public void patternKnapsackWeightOnly_emptyValues() throws Exception {
        assertEquals(new ArrayList<Pattern>(), AlgoUtils.patternKnapsackWeightOnly(new Pattern[0], 1000));
    }

    @Test
    public void patternKnapsackWeightOnly_zeroMaxYardage() throws Exception {
        Pattern pattern = new Pattern();
        Pattern[] array = new Pattern[1];
        array[0] = pattern;
        assertEquals(new ArrayList<Pattern>(), AlgoUtils.patternKnapsackWeightOnly(array, 0));
    }

    // Testing the simplest algorithm - n = 3, x = 10
    @Test
    public void patternKnapsackWeightOnly_easy() throws Exception {
        int maxYards = 10;

        Pattern one = new Pattern();
        one.setYardage(4);
        Pattern two = new Pattern();
        two.setYardage(7);
        Pattern three = new Pattern();
        three.setYardage(5);

        Pattern[] array = new Pattern[3];
        array[0] = one;
        array[1] = two;
        array[2] = three;

        ArrayList<Pattern> correctResult = new ArrayList<>();
        correctResult.add(one);
        correctResult.add(three);

        List<Pattern> result = AlgoUtils.patternKnapsackWeightOnly(array, maxYards);
        assertEquals(correctResult, result);
    }

    // Testing the simplest algorithm - n = 10, x = 100
    @Test
    public void patternKnapsackWeightOnly_medium() throws Exception {
        int maxYards = 100;
        ArrayList<Pattern> correctResult = new ArrayList<Pattern>();
        Pattern[] array = new Pattern[10];
        int[] yardage = {
                40, // index 0 - part of result
                39,
                15, // index 2 - part of result
                15, // index 3 - part of result
                14,
                20,
                0,
                30, // index 7 - part of result
                89,
                101
        };

        for (int i = 0; i < array.length; i++) {
            array[i] = new Pattern();
            array[i].setYardage(yardage[i]);
            if (i == 0||i == 2||i == 3||i == 7) {
                correctResult.add(array[i]);
            }
        }

        List<Pattern> result = AlgoUtils.patternKnapsackWeightOnly(array, maxYards);
        assertEquals(correctResult, result);
    }

}
