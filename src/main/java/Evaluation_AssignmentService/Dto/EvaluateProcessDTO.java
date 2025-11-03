package Evaluation_AssignmentService.Dto;

import Evaluation_AssignmentService.Enum.EnumProcessStatus;

public class EvaluateProcessDTO {
    private String Comment;
    private EnumProcessStatus NewStatus;

    public EvaluateProcessDTO() { }

    public EvaluateProcessDTO(String comment, EnumProcessStatus newStatus) {
        Comment = comment;
        NewStatus = newStatus;
    }
    public String getComment() { return Comment;}
    public void setComment(String comment) {
        this.Comment = comment;
    }
    public EnumProcessStatus getNewStatus() {
        return NewStatus;
    }
    public void setNewStatus(EnumProcessStatus newStatus) {
        this.NewStatus = newStatus;
    }
}
