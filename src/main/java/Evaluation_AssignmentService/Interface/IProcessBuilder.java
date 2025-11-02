package Evaluation_AssignmentService.Interface;

import Evaluation_AssignmentService.Dto.ProcessDTO;
import Evaluation_AssignmentService.ProcessEntity.BaseProcess;

public interface IProcessBuilder<T extends BaseProcess, D extends ProcessDTO> {
    T buildFromDTO(D dto);
}