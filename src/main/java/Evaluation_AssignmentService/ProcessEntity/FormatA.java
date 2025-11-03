package Evaluation_AssignmentService.ProcessEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
        name = "format_a",
        uniqueConstraints = @UniqueConstraint(columnNames = "degreework_id")
)
public class FormatA extends BaseProcess {
    private String companyLetterPath;
    private byte attempts = 0;

    protected FormatA() { super(); }
    public FormatA(long pDegreeWorkId) { super(pDegreeWorkId); }

    public FormatA(Long degreeworkId, String url) {
        super(degreeworkId, url);
    }

    public FormatA(Long degreeworkId, String url, String companyLetterPath) {
        super(degreeworkId, url);
        this.companyLetterPath = companyLetterPath;
    }

    public byte getAttempts(){ return this.attempts; }
    public String getCompanyLetterPath(){ return this.companyLetterPath; }

    public void setCompanyLetterPath(String pNewCompanyLetterPath) { this.companyLetterPath = pNewCompanyLetterPath;}

    public void setAttempts(byte attempts) {
        this.attempts = attempts;
    }
    public void increaseAttempts(){ this.attempts++;}
}
