package Evaluation_AssignmentService.ProcessEntity;

import Evaluation_AssignmentService.Enum.EnumProcessStatus;
import Evaluation_AssignmentService.SecurityComponent.EnumTypeExceptions;
import Evaluation_AssignmentService.SecurityComponent.ProcessException;
import jakarta.persistence.*;

import java.util.Date;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BaseProcess {
    @Id
    @Column(name = "degreework_id")
    protected Long degreeworkId;

    @Temporal(TemporalType.TIMESTAMP)
    protected Date date;
    protected String url = "";
    protected EnumProcessStatus status = EnumProcessStatus.PENDING;
    protected String comments = "";

    protected BaseProcess() { this.date = new Date(); }

    public BaseProcess(Long degreeworkId) {
        this.degreeworkId = degreeworkId;
        this.date = new Date();
    }

    public BaseProcess(Long degreeworkId, String url) {
        this.degreeworkId = degreeworkId;
        this.url = url;
        this.date = new Date();
    }

    //TEMPLATE//
    public final void evaluate(EnumProcessStatus pNewStatus, String pComment) throws ProcessException {
        validateNewStatus(pNewStatus);

        setStatus(pNewStatus);
        validateRequirements();
        setComments(pComment);
        updateData();
    }
    protected void validateNewStatus(EnumProcessStatus pNewStatus){
        if(pNewStatus == null || pNewStatus.equals(EnumProcessStatus.PENDING))
            throw new ProcessException(EnumTypeExceptions.INVALID_NEW_STATUS);
    }
    public abstract void validateRequirements() throws ProcessException;
    protected abstract void updateData();

    //Getters y Setters
    public Long getDegreeworkId(){ return degreeworkId; }
    public void setDegreeworkId(Long degreeworkId){ this.degreeworkId = degreeworkId; }
    public Date getDate(){ return date; }
    public void setDate(Date date){ this.date = date; }
    public String getUrl(){ return url; }
    public void setUrl(String url){ this.url = url; }
    public EnumProcessStatus getStatus(){ return status; }
    private void setStatus(EnumProcessStatus status){ this.status = status; }
    public String getComments(){ return comments; }
    public void setComments(String comments){ this.comments = comments; }
}
