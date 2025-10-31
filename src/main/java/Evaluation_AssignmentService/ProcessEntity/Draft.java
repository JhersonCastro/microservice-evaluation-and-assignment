package Evaluation_AssignmentService.ProcessEntity;

import Evaluation_AssignmentService.Enum.EnumTypeProcess;
import Evaluation_AssignmentService.SecurityComponent.ProcessException;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
public class Draft extends BaseProcess {
    private int id_degree_work;
    private Date deadline;

    public Draft(long pDegreeWorkId) { super(pDegreeWorkId); }

    @Override
    protected void validateRequirements() throws ProcessException {  }

    @Override
    protected void updateData() { }
}
