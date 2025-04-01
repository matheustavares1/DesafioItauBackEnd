package com.DesafioItau.DesafioBackEndItau.exceptions;

import java.time.Instant;

public class StandardError {

    private Integer status;
    private String message;
    private Instant timestamp;
    private String path;

    public StandardError() {}

    public StandardError(Integer status, String message, Instant timestamp, String path) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
        this.path = path;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
