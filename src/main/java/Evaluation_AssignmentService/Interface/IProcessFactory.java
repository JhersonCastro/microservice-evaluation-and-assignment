package Evaluation_AssignmentService.Interface;

import Evaluation_AssignmentService.Enum.EnumTypeProcess;
import Evaluation_AssignmentService.ProcessEntity.BaseProcess;

public interface IProcessFactory {
    BaseProcess createProcess(EnumTypeProcess pTypeProcess, Long degreeWorkId);
}
