package Evaluation_AssignmentService.ProcessService;

import Evaluation_AssignmentService.Dto.FormatADTO;
import Evaluation_AssignmentService.ProcessEntity.FormatA;
import Evaluation_AssignmentService.ProcessEntity.ProcessFactory;
import Evaluation_AssignmentService.ProcessRepository.ProcessRepository;
import Evaluation_AssignmentService.SecurityComponent.EnumTypeExceptions;
import Evaluation_AssignmentService.SecurityComponent.ProcessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FormatAService extends ProcessService<FormatA, FormatADTO>{

    public FormatAService(ProcessRepository<FormatA> repository, ProcessFactory processFactory) {
        super(repository, processFactory);
    }

    @Override
    public FormatA reUploadProcess(Long pId, FormatA pUpdatedProcess){
        Optional<FormatA> vFormatA = repository.findById(pId);
        if(vFormatA == null) throw new ProcessException(EnumTypeExceptions.NOT_FOUND);
        pUpdatedProcess.setAttempts(vFormatA.get().getAttempts());
        pUpdatedProcess.setDate(vFormatA.get().getDate());
        pUpdatedProcess.setDegreeworkId(pId);
        pUpdatedProcess.validateRequirements();
        return repository.save(pUpdatedProcess);
    }
}
