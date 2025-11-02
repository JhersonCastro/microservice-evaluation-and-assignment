package Evaluation_AssignmentService.SecurityComponent;

public enum EnumTypeExceptions {
    INVALID_NEW_STATUS("The new status is null or pending"),
    INVALID_CURRENT_STATUS("The process is not rejected"),
    PROCESS_NOT_SUPPORTED("That kind of process is not supported"),
    EXISTING_ID("That ID already exists "),
    NOT_FOUND("Process not found."),
    LIMIT_ATTEMPT_EXCEEDED("The limit of attempts has been exceeded"),
    PREREQUISITE_NOT_MET("A prerequisite was not met, the process cannot continue."),
    NULL_PARAMETER("Null parameter");

    private final String message;

    EnumTypeExceptions(String message) { this.message = message; }

    public String getMessage() { return message; }
}
