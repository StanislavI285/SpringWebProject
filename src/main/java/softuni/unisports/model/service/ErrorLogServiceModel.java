package softuni.unisports.model.service;

import java.time.LocalDateTime;

public class ErrorLogServiceModel {

    private LocalDateTime date;
    private String exception;
    private String reason;


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
}
