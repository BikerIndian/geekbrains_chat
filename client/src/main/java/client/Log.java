package client;

import java.io.FileWriter;
import java.io.IOException;

public class Log {
    String fileName;
    FileWriter writer;

    public Log(String fileName)  {
        this.fileName = fileName;
        init();
    }

    private void  init() {
        try {
            writer = new FileWriter(fileName, true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void saveMess(String mess)  {
        try {
            writer.write(mess);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void  close()  {

        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
