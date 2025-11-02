package Evaluation_AssignmentService.ProcessEntity;

import Evaluation_AssignmentService.Enum.EnumModality;
import Evaluation_AssignmentService.Enum.EnumProcessStatus;
import Evaluation_AssignmentService.SecurityComponent.EnumTypeExceptions;
import Evaluation_AssignmentService.SecurityComponent.ProcessException;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "format_a")
public class FormatA extends BaseProcess {
    private EnumModality modality;
    private String companyLetterPath;
    private byte attempts = 0;

    protected FormatA() { super(); }
    public FormatA(long pDegreeWorkId) { super(pDegreeWorkId); }

    public FormatA(Long degreeworkId, String url, EnumModality modality, String companyLetterPath) {
        super(degreeworkId, url);
        this.modality = modality;
        this.companyLetterPath = companyLetterPath;
    }

    @Override
    public void validateRequirements() throws ProcessException {
        if(this.attempts > 2 ) throw new ProcessException(EnumTypeExceptions.LIMIT_ATTEMPT_EXCEEDED);
    }

    @Override
    protected void updateData() {
        if(this.status == EnumProcessStatus.REJECTED)
            attempts++;
    }

    public byte getAttempts(){ return this.attempts; }
    public String getCompanyLetterPath(){ return this.companyLetterPath; }
    public EnumModality getModality(){ return this.modality; }

    public void setModality(EnumModality pNewModality){ this.modality = pNewModality;}
    public void setCompanyLetterPath(String pNewCompanyLetterPath) { this.companyLetterPath = pNewCompanyLetterPath;}

    public void setAttempts(byte attempts) {
        this.attempts = attempts;
    }
}
