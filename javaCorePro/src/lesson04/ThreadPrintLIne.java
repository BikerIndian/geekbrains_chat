package lesson04;

public class ThreadPrintLIne {
    private Object mon = new Object();
    private volatile int currentNum = 1;
    private int sizeThread = 0;
    private int num = 5;
    Thread t1;

    public ThreadPrintLIne() {
        init();
    }

    private void init() {
        t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {

                }
            }
        });
    }


    public void add(String str) {

        int indexIn = ++this.sizeThread;

        new Thread(() -> {

            try {
                t1.join();
            } catch (InterruptedException e) {
            }

            try {
                for (int i = 0; i < num; i++) {
                    synchronized (mon) {
                        while (currentNum != indexIn) {
                            mon.wait();
                        }

                        currentNum++;

                        if (currentNum == this.sizeThread + 1) {
                            currentNum = 1;
                        }
                        System.out.print(str);
                        mon.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }

    public void print(int num) {
        this.num = num;
        t1.interrupt();

    }
}
