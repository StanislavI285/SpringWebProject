package softuni.unisports.model.service;

import java.time.LocalDateTime;

public class ErrorLogServiceModel {

    private LocalDateTime date;
    private String exception;
    private String reason;
    private String stackTrace;


    public ErrorLogServiceModel() {
    }

    public LocalDateTime getDate() {
        return date;
    }

    public ErrorLogServiceModel setDate(LocalDateTime date) {
        this.date = date;
        return this;
    }

    public String getException() {
        return exception;
    }

    public ErrorLogServiceModel setException(String exception) {
        this.exception = exception;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public ErrorLogServiceModel setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public ErrorLogServiceModel setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
        return this;
    }
}
