package Evaluation_AssignmentService.Enum;

public enum EnumTypeExceptions {
    INVALID_STATUS("The status is null or pending"),
    PROCESS_NOT_SUPPORTED("That kind of process is not supported"),
    NULL_PARAMETER("Null parameter");
    private final String message;

    EnumTypeExceptions(String message) { this.message = message; }

    public String getMessage() { return message; }
}
