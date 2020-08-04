package server;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.*;

public class Log {
    private static final Logger logger = Logger.getLogger("");

    public Log() {
        init();
    }

    private void init() {
        try {
            logger.setUseParentHandlers(false);

            Handler fileHandler = new FileHandler("log_%g.log",10*1024,5, true);

            fileHandler.setFormatter(new Formatter() {
                @Override
                public String format(LogRecord record) {

                    SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss dd.MM.yyyy");
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(record.getMillis());

                    return String.format("%s  %s \n",  formatter.format(calendar.getTime()), record.getMessage());
                }
            });
            fileHandler.setLevel(Level.ALL);
            logger.addHandler(fileHandler);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void mess(String mes){
        logger.severe(mes);
    }
}
