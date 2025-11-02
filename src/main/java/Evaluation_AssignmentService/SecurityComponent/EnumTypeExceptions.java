package Evaluation_AssignmentService.SecurityComponent;

public enum EnumTypeExceptions {
    INVALID_NEW_STATUS("The new status is null or pending"),
    INVALID_CURRENT_STATUS("The current process is not rejected; you cannot update it."),
    PROCESS_NOT_SUPPORTED("That kind of process is not supported"),
    EXISTING_ID("That ID already exists "),
    NOT_FOUND("Process not found."),
    LIMIT_ATTEMPT_EXCEEDED("The limit of attempts has been exceeded"),
    PREVIOUS_PROCESS_NOT_APPROVED("The previous process has not been approved and cannot continue until it is approved."),
    PREVIOUS_PROCESS_NOT_SUMBITTED("The previous process has not been uploaded; you cannot continue until it is uploaded and approved."),
    PROCESS_APPROVED("The current process is approved, you cannot change it."),
    EXPIRED_TIME("The available time ended"),
    NON_MODIFICABLE_PROCESS("The current process can only be resubmitted when it is rejected."),
    PROCESS_NOT_PENDING("The current process is not pending and cannot be evaluated"),
    NULL_PARAMETER("Null parameter");

    private final String message;

    EnumTypeExceptions(String message) { this.message = message; }

    public String getMessage() { return message; }
}
