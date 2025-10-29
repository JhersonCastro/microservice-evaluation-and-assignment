package Evaluation_AssignmentService.ProcessEntity;

import Evaluation_AssignmentService.Enum.EnumProcessStatus;
import Evaluation_AssignmentService.SecurityComponent.ProcessException;

public class FormatA extends BaseProcess {
    private byte attempts = 0;
    private String mail;

    public FormatA(long pDegreeWorkId) { super(pDegreeWorkId); }

    @Override
    protected void validateRequirements() throws ProcessException {  }

    @Override
    protected void updateData() {
        if(this.status == EnumProcessStatus.REJECTED) attempts++;
    }

}
