package com.loganalyzer;

import com.loganalyzer.model.LogEntry;
import com.loganalyzer.service.LogAnalyzerService;
import com.loganalyzer.util.LogParser;
import java.util.List;

public class LogAnalyzerApp {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: java LogAnalyzerApp <logFilePath> <timeWindowInSeconds>");
            return;
        }
        String filePath = args[0];
        int windowSeconds = Integer.parseInt(args[1]);
        try {
            LogParser parser = new LogParser();
            List<LogEntry> entries = parser.parseLogFile(filePath);
            LogAnalyzerService analyzer = new LogAnalyzerService();
            List<List<String>> sequences = analyzer.findEventSequences(entries, windowSeconds);
            System.out.println("Detected Event Sequences:");
            for (List<String> seq : sequences) {
                System.out.println(seq);
            }

        } catch (Exception e) {
            System.err.println("Error during analysis: " + e.getMessage());
        }
    }
}
