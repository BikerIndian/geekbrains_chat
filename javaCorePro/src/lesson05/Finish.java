package lesson05;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public class Finish extends Stage {
    static Object mon = new Object();
    private static AtomicBoolean fin = new AtomicBoolean(false);
    CountDownLatch end;
    public Finish(CountDownLatch end) {
        this.end = end;
    }

    @Override
    public void go(Car c) {

        printWin(c.getName());


        end.countDown();

    }

    private static void printWin(String name) {
        synchronized (mon) {
            if (!fin.get()){
                System.out.println(name+" - WIN");
                fin.set(true);
            }
        }
    }

}
