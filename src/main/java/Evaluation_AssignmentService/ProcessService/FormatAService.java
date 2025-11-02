package Evaluation_AssignmentService.ProcessService;

import Evaluation_AssignmentService.Dto.FormatADTO;
import Evaluation_AssignmentService.ProcessEntity.Draft;
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
        FormatA existingformatA = repository.findById(pId).orElseThrow(() -> new ProcessException(EnumTypeExceptions.NOT_FOUND));
        existingformatA.setComments(pUpdatedProcess.getComments());
        existingformatA.setDate(pUpdatedProcess.getDate());
        existingformatA.setUrl(pUpdatedProcess.getUrl());
        existingformatA.setCompanyLetterPath(pUpdatedProcess.getCompanyLetterPath());
        return repository.save(existingformatA);
    }
}
