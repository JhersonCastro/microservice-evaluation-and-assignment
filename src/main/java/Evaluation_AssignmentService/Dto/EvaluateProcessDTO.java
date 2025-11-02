package Evaluation_AssignmentService.Dto;

import Evaluation_AssignmentService.Enum.EnumProcessStatus;

public class EvaluateProcessDTO {
    private Long pId;
    private String pComment;
    private EnumProcessStatus pNewStatus;

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public String getpComment() {
        return pComment;
    }

    public void setpComment(String pComment) {
        this.pComment = pComment;
    }

    public EnumProcessStatus getpNewStatus() {
        return pNewStatus;
    }

    public void setpNewStatus(EnumProcessStatus pNewStatus) {
        this.pNewStatus = pNewStatus;
    }
}
