package Evaluation_AssignmentService.ProcessEntity;

import Evaluation_AssignmentService.Enum.EnumTypeExceptions;
import Evaluation_AssignmentService.Enum.EnumTypeProcess;
import Evaluation_AssignmentService.Interface.IProcessFactory;
import Evaluation_AssignmentService.SecurityComponent.ProcessException;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ProcessFactory implements IProcessFactory {
    private final Map<EnumTypeProcess, Function<Long, BaseProcess>> creators;

    public ProcessFactory() {
        creators = new HashMap<>();
        creators.put(EnumTypeProcess.FORMAT_A, FormatA::new);
        creators.put(EnumTypeProcess.DRAFT, Draft::new);
    }

    @Override
    public BaseProcess createProcess(EnumTypeProcess pTypeProcess, Long pDegreeWorkId) {
        Function<Long, BaseProcess> creator = creators.get(pTypeProcess);
        if (creator == null) {
            throw new ProcessException(EnumTypeExceptions.PROCESS_NOT_SUPPORTED.getMessage());
        }
        return creator.apply(pDegreeWorkId);
    }
}
