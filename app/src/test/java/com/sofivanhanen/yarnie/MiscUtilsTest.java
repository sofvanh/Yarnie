package com.sofivanhanen.yarnie;

import com.sofivanhanen.yarnie.Data.Pattern;

import org.junit.Test;

import java.util.ArrayList;

import static com.sofivanhanen.yarnie.Utils.MiscUtils.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by sofvanh on 16/02/18.
 */

public class MiscUtilsTest {

    @Test
    public void arrayToList_test() {
        Pattern p1 = new Pattern();
        p1.setYardage(18);
        Pattern p2 = new Pattern();
        p2.setYardage(19);

        Pattern[] patterns = new Pattern[2];
        patterns[0] = p1;
        patterns[1] = p2;

        ArrayList<Pattern> correctResult = new ArrayList<>();
        correctResult.add(p1);
        correctResult.add(p2);

        assertEquals(correctResult, arrayToList(patterns));
    }

    @Test
    public void arrayToList_notSame() {
        Pattern p1 = new Pattern();
        p1.setYardage(18);
        Pattern p2 = new Pattern();
        p2.setYardage(19);
        Pattern p3 = new Pattern();
        p3.setYardage(20);

        Pattern[] patterns = new Pattern[2];
        patterns[0] = p1;
        patterns[1] = p2;

        ArrayList<Pattern> incorrectResult = new ArrayList<>();
        incorrectResult.add(p1);
        incorrectResult.add(p3);

        assertNotEquals(incorrectResult, arrayToList(patterns));
    }

    @Test
    public void listToArray_test() {
        Pattern p1 = new Pattern();
        p1.setYardage(18);
        Pattern p2 = new Pattern();
        p2.setYardage(19);

        ArrayList<Pattern> patterns = new ArrayList<>();
        patterns.add(p1);
        patterns.add(p2);

        Pattern[] correctResult = new Pattern[2];
        correctResult[0] = p1;
        correctResult[1] = p2;

        assertEquals(correctResult, listToArray(patterns));
    }

    @Test
    public void listToArray_notSame() {
        Pattern p1 = new Pattern();
        p1.setYardage(18);
        Pattern p2 = new Pattern();
        p2.setYardage(19);
        Pattern p3 = new Pattern();
        p3.setYardage(20);

        ArrayList<Pattern> patterns = new ArrayList<>();
        patterns.add(p1);
        patterns.add(p2);

        Pattern[] incorrectResult = new Pattern[2];
        incorrectResult[0] = p1;
        incorrectResult[1] = p3;

        assertNotEquals(incorrectResult, listToArray(patterns));
    }

}
