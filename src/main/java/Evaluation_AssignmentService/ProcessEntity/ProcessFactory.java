package Evaluation_AssignmentService.ProcessEntity;

import Evaluation_AssignmentService.Builders.DraftBuilder;
import Evaluation_AssignmentService.Builders.FormatABuilder;
import Evaluation_AssignmentService.Dto.DraftDTO;
import Evaluation_AssignmentService.Dto.FormatADTO;
import Evaluation_AssignmentService.Dto.ProcessDTO;
import Evaluation_AssignmentService.Interface.IProcessBuilder;
import Evaluation_AssignmentService.SecurityComponent.EnumTypeExceptions;
import Evaluation_AssignmentService.Enum.EnumTypeProcess;
import Evaluation_AssignmentService.Interface.IProcessFactory;
import Evaluation_AssignmentService.SecurityComponent.ProcessException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class ProcessFactory implements IProcessFactory {
    private final Map<Class<? extends ProcessDTO>, IProcessBuilder<?, ?>> buildersByDTOType;
    private final Map<EnumTypeProcess, Function<Long, BaseProcess>> simpleCreators;

    public ProcessFactory(FormatABuilder formatABuilder, DraftBuilder draftBuilder) {
        buildersByDTOType = new HashMap<>();
        buildersByDTOType.put(FormatADTO.class, formatABuilder);
        buildersByDTOType.put(DraftDTO.class, draftBuilder);

        simpleCreators = new HashMap<>();
        simpleCreators.put(EnumTypeProcess.FORMAT_A, FormatA::new);
        simpleCreators.put(EnumTypeProcess.DRAFT, Draft::new);
    }

    @Override
    public BaseProcess createProcessFromDTO(ProcessDTO dto) {
        IProcessBuilder builder = buildersByDTOType.get(dto.getClass());
        if (builder == null) {
            throw new ProcessException(EnumTypeExceptions.PROCESS_NOT_SUPPORTED);
        }
        return buildProcessFromDTO(builder, dto);
    }

    private <T extends BaseProcess, D extends ProcessDTO> T buildProcessFromDTO(
            IProcessBuilder<T, D> builder, ProcessDTO dto) {
        return builder.buildFromDTO((D) dto);
    }

    @Override
    public BaseProcess createProcess(EnumTypeProcess pTypeProcess, Long pDegreeWorkId) {
        Function<Long, BaseProcess> vCreator = simpleCreators.get(pTypeProcess);
        if (vCreator == null) {
            throw new ProcessException(EnumTypeExceptions.PROCESS_NOT_SUPPORTED);
        }
        return vCreator.apply(pDegreeWorkId);
    }
}

