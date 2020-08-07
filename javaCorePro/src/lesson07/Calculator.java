package lesson07;

import lesson07.MyUnit.AfterSuite;
import lesson07.MyUnit.BeforeSuite;
import lesson07.MyUnit.Test;

public class Calculator {
    private  int c;

    @BeforeSuite
    public void start(int c){
        this.c = c;
        System.out.println("@BeforeSuite ");
    }

    @Test(priority = 3)
    public int add(int a, int b) {
        return a + b ;
    }

    @Test(priority = 1)
    public int mul(int a, int b) {
        return a * b;
    }

    @Test(priority = 1)
    public int div(int a, int b) {
        return a / b ;
    }

    @AfterSuite
    public void end(){

        System.out.println("@AfterSuite c = " +c);
    }
}
