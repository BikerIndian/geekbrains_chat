package lesson06;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestArr {
    MainLesson06 lesson06;

    @Before
    public void beforeMethod(){
        lesson06 = new MainLesson06();
    }

    @Test
    public void testGetArr1(){

        int[] arrIn = {1,4,2,3,4,1,7};
        int[] result = lesson06.getArr(arrIn);

        int[] arr = {1,7};
        Assert.assertArrayEquals(arr,result);
    }

    @Test
    public void testGetArr2(){

        int[] arrIn = {1,4,2,3,5,1,7};
        int[] arr = {2,3,5,1,7};
        int[] result = lesson06.getArr(arrIn);

        Assert.assertArrayEquals(arr,result);
    }

    @Test(expected = RuntimeException.class)
    public void testGetArrException() throws RuntimeException{

        int[] arrIn = {1,2,3,5,1,7};
         lesson06.getArr(arrIn);

    }
}
