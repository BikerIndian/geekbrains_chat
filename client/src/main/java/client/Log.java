package client;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

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

    /**
     * Чтение последних n строк
     * @param n
     * @return
     */
    public String getMess(int n) {

        File file = new File(fileName);
        if (!file.isFile()){
            return "";
        }
        StringBuffer buf = new StringBuffer();

        int readLines = 0;
        StringBuilder builder = new StringBuilder();
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r")) {
            long fileLength = file.length() - 1;
            if (fileLength<0){
                return "";
            }
            randomAccessFile.seek(fileLength);

            for (long pointer = fileLength; pointer >= 0; pointer--) {
                randomAccessFile.seek(pointer);
                char c;

                c = (char) randomAccessFile.read();

                if (c == '\n') {
                    readLines++;
                    if (readLines == n+1)
                        break;
                }
                builder.append(c);
                fileLength = fileLength - pointer;
            }

            builder.reverse();
            buf.append(builder.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }


        return buf.toString();
    }
}
