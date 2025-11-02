package Evaluation_AssignmentService.ProcessEntity;

import Evaluation_AssignmentService.Enum.EnumProcessStatus;
import jakarta.persistence.*;


import java.util.Date;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BaseProcess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProcess;

    @Column(name = "degreework_id")
    protected Long degreeworkId;

    @Temporal(TemporalType.TIMESTAMP)
    protected Date date = new Date();
    protected String url = "";
    protected EnumProcessStatus status = EnumProcessStatus.PENDING;
    protected String comments = "";

    protected BaseProcess() { this.date = new Date(); }

    public BaseProcess(Long degreeworkId) {
        this.degreeworkId = degreeworkId;
    }

    public BaseProcess(Long degreeworkId, String url) {
        this.degreeworkId = degreeworkId;
        this.url = url;
    }

    //Getters y Setters
    public Long getDegreeworkId(){ return degreeworkId; }
    public void setDegreeworkId(Long degreeworkId){ this.degreeworkId = degreeworkId; }
    public Date getDate(){ return date; }
    public void setDate(Date date){ this.date = date; }
    public String getUrl(){ return url; }
    public void setUrl(String url){ this.url = url; }
    public EnumProcessStatus getStatus(){ return status; }
    public void setStatus(EnumProcessStatus status){ this.status = status; }
    public String getComments(){ return comments; }
    public void setComments(String comments){ this.comments = comments; }
}
