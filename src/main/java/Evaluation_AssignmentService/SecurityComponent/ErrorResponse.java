package Evaluation_AssignmentService.SecurityComponent;

import java.util.Date;

public class ErrorResponse {
    private EnumTypeExceptions typeException;
    private String message;
    private Date date;

    public ErrorResponse(EnumTypeExceptions typeException, Date date, String message) {
        this.typeException = typeException;
        this.date = date;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public EnumTypeExceptions getTypeException() {
        return typeException;
    }

    public void setTypeException(EnumTypeExceptions typeException) {
        this.typeException = typeException;
    }
}
