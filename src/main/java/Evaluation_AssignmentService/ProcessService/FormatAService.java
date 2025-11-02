package Evaluation_AssignmentService.ProcessService;

import Evaluation_AssignmentService.Dto.FormatADTO;
import Evaluation_AssignmentService.Enum.EnumProcessStatus;
import Evaluation_AssignmentService.ProcessEntity.FormatA;
import Evaluation_AssignmentService.ProcessEntity.ProcessFactory;
import Evaluation_AssignmentService.ProcessRepository.FormatARepository;
import Evaluation_AssignmentService.SecurityComponent.EnumTypeExceptions;
import Evaluation_AssignmentService.SecurityComponent.ProcessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormatAService extends ProcessService<FormatA, FormatADTO>{
    private final FormatARepository formatARepository;

    @Autowired
    public FormatAService(FormatARepository repository, ProcessFactory processFactory) {
        super(repository, processFactory);
        this.formatARepository = repository;
    }

    @Override
    protected void validateRequirements(FormatA pCurrentProcess) {
        if(pCurrentProcess.getAttempts() > 3) throw new ProcessException(EnumTypeExceptions.LIMIT_ATTEMPT_EXCEEDED);
    }

    @Override
    protected void validateBeforeCreate(FormatA pNewProcess) {
        if(pNewProcess == null) throw new ProcessException(EnumTypeExceptions.NOT_FOUND);//Simbolico
    }

    @Override
    protected void updateInternalData(FormatA pUpdateProcess) {
        if(pUpdateProcess.getStatus().equals(EnumProcessStatus.REJECTED))
            pUpdateProcess.increaseAttempts();
    }
    @Override
    protected void SynchronizeData(FormatA pCurrentProcess, FormatA pUpdateProcess){
        super.SynchronizeData(pCurrentProcess,pUpdateProcess);
        pCurrentProcess.setCompanyLetterPath(pCurrentProcess.getCompanyLetterPath());
    }

}
