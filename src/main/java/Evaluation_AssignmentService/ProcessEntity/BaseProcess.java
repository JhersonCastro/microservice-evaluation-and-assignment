package Evaluation_AssignmentService.ProcessEntity;

import Evaluation_AssignmentService.Enum.EnumProcessStatus;
import Evaluation_AssignmentService.Enum.EnumTypeExceptions;
import Evaluation_AssignmentService.SecurityComponent.ProcessException;

import java.util.Date;

public abstract class BaseProcess {
    protected final Long degreeworkId;
    protected final Date date;
    protected String url;
    protected EnumProcessStatus status = EnumProcessStatus.PENDING;

    public BaseProcess(Long pDegreeWorkId) {
        degreeworkId = pDegreeWorkId;
        this.date = new Date();
    }
    public final void evaluate(EnumProcessStatus pNewStatus) throws ProcessException {
        validateNewStatus(pNewStatus);
        validateRequirements();
        setNewStatus(pNewStatus);
        updateData();
    }
    protected void validateNewStatus(EnumProcessStatus pNewStatus){
        if(pNewStatus == null || pNewStatus.equals(EnumProcessStatus.PENDING))
            throw new ProcessException(EnumTypeExceptions.INVALID_STATUS.getMessage());
    }
    private void setNewStatus(EnumProcessStatus pNewStatus){ this.status = pNewStatus;}
    protected abstract void validateRequirements() throws ProcessException;
    protected abstract void updateData();
    //

    public Long getDegreeworkId() {
        return degreeworkId;
    }

    public Date getDate() {
        return date;
    }

    public String getUrl() {
        return url;
    }

    public EnumProcessStatus getStatus() {
        return status;
    }
}
