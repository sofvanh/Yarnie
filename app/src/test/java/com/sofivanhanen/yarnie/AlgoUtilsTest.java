package com.sofivanhanen.yarnie;

import org.junit.Test;

import java.util.ArrayList;

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
        assertEquals(new Pattern[0], AlgoUtils.patternKnapsackWeightOnly(new Pattern[0], 1000));
    }

    @Test
    public void patternKnapsackWeightOnly_zeroMaxYardage() throws Exception {
        Pattern pattern = new Pattern();
        Pattern[] array = new Pattern[1];
        array[0] = pattern;
        assertEquals(new Pattern[0], AlgoUtils.patternKnapsackWeightOnly(array, 0));
    }

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

        Pattern[] correctResult = new Pattern[2];
        correctResult[0] = one;
        correctResult[1] = three;

        Pattern[] result = AlgoUtils.patternKnapsackWeightOnly(array, maxYards);
        assertEquals(correctResult, result);
    }

}
