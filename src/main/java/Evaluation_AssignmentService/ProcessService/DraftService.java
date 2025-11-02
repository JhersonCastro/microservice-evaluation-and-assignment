package Evaluation_AssignmentService.ProcessService;

import Evaluation_AssignmentService.Dto.DraftDTO;
import Evaluation_AssignmentService.Enum.EnumProcessStatus;
import Evaluation_AssignmentService.ProcessEntity.Draft;
import Evaluation_AssignmentService.ProcessEntity.FormatA;
import Evaluation_AssignmentService.ProcessEntity.ProcessFactory;
import Evaluation_AssignmentService.ProcessRepository.DraftRepository;
import Evaluation_AssignmentService.SecurityComponent.EnumTypeExceptions;
import Evaluation_AssignmentService.SecurityComponent.ProcessException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

@Service
public class DraftService extends ProcessService<Draft, DraftDTO>{

    @PersistenceContext
    private EntityManager entityManager;
    private final DraftRepository draftRepository;
    private final FormatAService formatAService;

    public DraftService(DraftRepository repository, ProcessFactory processFactory,  FormatAService formatAService) {
        super(repository,processFactory);
        this.draftRepository = repository;
        this.formatAService = formatAService;
    }

    @Override
    protected void validateRequirements(Draft pCurrentProcess) {
        if(pCurrentProcess.getStatus() == null || pCurrentProcess.getStatus().equals(EnumProcessStatus.REJECTED))
            throw new ProcessException(EnumTypeExceptions.INVALID_NEW_STATUS);
        if(pCurrentProcess.getDaysPassed() == 60) throw new ProcessException(EnumTypeExceptions.EXPIRED_TIME);
    }

    @Override
    protected void updateInternalData(Draft pUpdateProcess) {
        pUpdateProcess.updateDaysPassed();
    }

    @Override
    protected void validateBeforeCreate(Draft pNewProcess) {
        FormatA formatA = formatAService.extractByDegreeWorkId(pNewProcess.getDegreeworkId());
        if (formatA == null)
            throw new ProcessException(EnumTypeExceptions.PREVIOUS_PROCESS_NOT_SUMBITTED);
        if(!formatA.getStatus().equals(EnumProcessStatus.APPROVED))
            throw new ProcessException(EnumTypeExceptions.PREVIOUS_PROCESS_NOT_APPROVED);
        entityManager.clear();
    }
}
