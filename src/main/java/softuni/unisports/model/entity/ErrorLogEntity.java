package softuni.unisports.model.entity;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "errors_log")
public class ErrorLogEntity extends BaseEntity{

    private String date;
    private String exception;
    private String reason;


    @Column(name = "date")
    public String getDate() {
        return date;
    }

    public ErrorLogEntity setDate(String date) {
        this.date = date;
        return this;
    }


    @Column(name = "exception")
    public String getException() {
        return exception;
    }

    public ErrorLogEntity setException(String exception) {
        this.exception = exception;
        return this;
    }


    @Column(name = "reason")
    public String getReason() {
        return reason;
    }

    public ErrorLogEntity setReason(String reason) {
        this.reason = reason;
        return this;
    }
}
