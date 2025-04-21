package com.loganalyzer.model;

import java.util.Date;

public class LogEntry {
    private Date timestamp;
    private String event;
    public LogEntry(Date timestamp, String event) {
        this.timestamp = timestamp;
        this.event = event;
    }
    public Date getTimestamp() {
        return timestamp;
    }
    public String getEvent() {
        return event;
    }
}
