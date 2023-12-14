package net.deaprtment.exception;

import java.time.LocalDateTime;

public class ErrorDetails {
    private LocalDateTime timeStamp;
    private String Description;
    private String path;
    private String AppStatusCode;

    public ErrorDetails(LocalDateTime timeStamp, String Description, String path, String AppStatusCode) {
        this.timeStamp = timeStamp;
        this.Description = Description;
        this.path = path;
        this.AppStatusCode = AppStatusCode;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAppStatusCode() {
        return AppStatusCode;
    }

    public void setAppStatusCode(String appStatusCode) {
        AppStatusCode = appStatusCode;
    }
}
