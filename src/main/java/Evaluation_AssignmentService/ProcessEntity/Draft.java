package Evaluation_AssignmentService.ProcessEntity;

import Evaluation_AssignmentService.Enum.EnumTypeProcess;
import Evaluation_AssignmentService.SecurityComponent.ProcessException;

public class Draft extends BaseProcess {

    public Draft(long pDegreeWorkId) { super(pDegreeWorkId); }

    @Override
    protected void validateRequirements() throws ProcessException {  }

    @Override
    protected void updateData() { }
}
