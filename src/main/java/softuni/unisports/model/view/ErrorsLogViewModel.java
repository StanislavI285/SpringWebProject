package softuni.unisports.model.view;

import java.time.LocalDateTime;

public class ErrorsLogViewModel {

    private String id;
    private LocalDateTime date;
    private String exception;
    private String reason;

    public ErrorsLogViewModel() {
    }

    public String getId() {
        return id;
    }

    public ErrorsLogViewModel setId(String id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public ErrorsLogViewModel setDate(LocalDateTime date) {
        this.date = date;
        return this;
    }

    public String getException() {
        return exception;
    }

    public ErrorsLogViewModel setException(String exception) {
        this.exception = exception;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public ErrorsLogViewModel setReason(String reason) {
        this.reason = reason;
        return this;
    }
}
