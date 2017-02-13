package ua.epam.spring.core.loggers;

import org.apache.commons.io.FileUtils;
import ua.epam.spring.core.beans.Event;

import java.io.*;

public class FileEventLogger implements EventLogger {

    private String fileName;
    private File file;

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
        file = new File(fileName);
        // create file if does'n exist
        if (!file.exists()) {
            try {
                Writer writer = new FileWriter(new File(fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void init() throws IOException {
        if (!file.canWrite()) {
            throw new IOException("Can't write to file " + fileName);
        }
    }

    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(file, event.toString(), true);
            FileUtils.writeStringToFile(file, "\n", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logEvent_usingFileWriter(Event event) {
        try {
            Writer output = new BufferedWriter(new FileWriter(fileName));
            output.append(event.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
