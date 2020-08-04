package lesson06;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestArrOneFour {
    MainLesson06 lesson06;

    @Before
    public void beforeMethod(){
        lesson06 = new MainLesson06();
    }

    @Test
    public void testOneFour1(){
        int[] arrIn = {1,4,4};
        Assert.assertTrue(lesson06.isOneFour(arrIn));
    }

    @Test
    public void testOneFour2(){
        int[] arrIn = {1,4,5};
        Assert.assertFalse(lesson06.isOneFour(arrIn));
    }

    @Test
    public void testOneFour3(){
        int[] arrIn = {1,1,1};
        Assert.assertFalse(lesson06.isOneFour(arrIn));
    }


}
