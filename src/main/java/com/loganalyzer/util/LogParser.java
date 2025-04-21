package com.loganalyzer.util;

import com.loganalyzer.model.LogEntry;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogParser {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");


    public List<LogEntry> parseLogFile(String filePath) throws Exception {
        List<LogEntry> entries = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            // Try to find the first space AFTER the timestamp
            int splitIndex = line.indexOf(" ", 20); // if log includes event after timestamp

            String timestampStr;
            String event;

            if (splitIndex == -1) {
                timestampStr = line;           // whole line is the timestamp
                event = "";                    // no event info
            } else {
                timestampStr = line.substring(0, splitIndex).trim();
                event = line.substring(splitIndex).trim();
            }

            Date timestamp = sdf.parse(timestampStr);
            entries.add(new LogEntry(timestamp, event));
        }

        reader.close();
        return entries;
    }

}
