package Evaluation_AssignmentService.SecurityComponent;

public class ProcessException extends RuntimeException {
    private final EnumTypeExceptions type;

    public ProcessException(EnumTypeExceptions type) {
        super(type.getMessage());
        this.type = type;
    }
    public EnumTypeExceptions getType() {
        return type;
    }
}
