package lesson05;

import java.util.concurrent.CountDownLatch;

public class Start extends Stage{
    CountDownLatch startLatch;

    public Start(CountDownLatch startLatch) {
        this.startLatch = startLatch;
    }

    @Override
    public void go(Car c) {
        startLatch.countDown();

        try {
            startLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
