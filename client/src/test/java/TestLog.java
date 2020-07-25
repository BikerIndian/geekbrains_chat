import client.Log;

public class TestLog {
    public static void main(String[] args) {
        //create();
        reader();
    }

    private static void reader() {
        Log log = new Log("history_ivan.txt");
        System.out.println(log.getMess(400));
    }

    private static void create() {
        Log log = new Log("history_ivan.txt");
        for (int i = 1; i <= 150; i++) {
            log.saveMess("ivan : "+i+"\n");
        }
        log.close();
    }
}
