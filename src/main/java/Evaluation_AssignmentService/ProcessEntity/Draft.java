package Evaluation_AssignmentService.ProcessEntity;

import Evaluation_AssignmentService.SecurityComponent.ProcessException;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "draft")
public class Draft extends BaseProcess {

    protected Draft() { super(); }
    public Draft(long pDegreeWorkId) { super(pDegreeWorkId); }

    @Override
    public void validateRequirements() throws ProcessException {
        System.out.println("validando draft");
    }

    @Override
    protected void updateData() {
        System.out.println("Actualizando draft");
    }
}
