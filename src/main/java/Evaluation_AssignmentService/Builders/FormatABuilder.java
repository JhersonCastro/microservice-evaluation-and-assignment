package Evaluation_AssignmentService.Builders;

import Evaluation_AssignmentService.Dto.FormatADTO;
import Evaluation_AssignmentService.Interface.IProcessBuilder;
import Evaluation_AssignmentService.ProcessEntity.FormatA;
import Evaluation_AssignmentService.SecurityComponent.EnumTypeExceptions;
import Evaluation_AssignmentService.SecurityComponent.ProcessException;
import org.springframework.stereotype.Component;

@Component
public class FormatABuilder implements IProcessBuilder<FormatA, FormatADTO> {

    @Override
    public FormatA buildFromDTO(FormatADTO dto) {
        if (dto.getDegreeWorkId() == null) {
            throw new ProcessException(EnumTypeExceptions.NULL_PARAMETER);
        }
        return new FormatA(dto.getDegreeWorkId(), dto.getUrl(),dto.getCompanyLetterPath());
    }
}
