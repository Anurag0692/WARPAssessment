package com.loganalyzer.service;

import com.loganalyzer.model.LogEntry;
import java.util.*;
import java.util.concurrent.TimeUnit;
public class LogAnalyzerService {

    public List<List<String>> findEventSequences(List<LogEntry> logEntries, int windowSeconds) {
        logEntries.sort(Comparator.comparing(LogEntry::getTimestamp));
        List<List<String>> result = new ArrayList<>();
        Deque<LogEntry> window = new LinkedList<>();
        for (LogEntry current : logEntries) {
            while (!window.isEmpty() &&
                    TimeUnit.MILLISECONDS.toSeconds(current.getTimestamp().getTime() - window.peekFirst().getTimestamp().getTime()) > windowSeconds) {
                window.pollFirst();
            }
            window.addLast(current);
            if (window.size() > 1) {
                List<String> sequence = new ArrayList<>();
                for (LogEntry e : window) {
                    sequence.add(e.getEvent());
                }
                result.add(sequence);
            }
        }
        return result;
    }
}
