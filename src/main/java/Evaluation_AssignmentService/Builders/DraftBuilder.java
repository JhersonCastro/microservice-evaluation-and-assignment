package Evaluation_AssignmentService.Builders;

import Evaluation_AssignmentService.Dto.DraftDTO;
import Evaluation_AssignmentService.Interface.IProcessBuilder;
import Evaluation_AssignmentService.ProcessEntity.Draft;
import org.springframework.stereotype.Component;

@Component
public class DraftBuilder implements IProcessBuilder<Draft,DraftDTO> {
    @Override
    public Draft buildFromDTO(DraftDTO pDTO) {
        return new Draft(pDTO.getDegreeWorkId(), pDTO.getUrl());
    }
}
