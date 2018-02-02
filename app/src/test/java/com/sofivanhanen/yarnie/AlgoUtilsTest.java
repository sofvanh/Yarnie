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

}
