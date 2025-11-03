package Evaluation_AssignmentService.Enum;

/**
 * Enumeration of process statuses.
 * Represents the current state of a process.
 */
public enum EnumProcessStatus {
    /** Process was approved. */
    APPROVED,

    /** Process was rejected. */
    REJECTED,

    /** Process is still pending review. */
    PENDING,

    /** Process failed. */
    FAILED
}
