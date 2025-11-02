package Evaluation_AssignmentService.ProcessService;

import Evaluation_AssignmentService.Dto.DraftDTO;
import Evaluation_AssignmentService.ProcessEntity.Draft;
import Evaluation_AssignmentService.ProcessEntity.ProcessFactory;
import Evaluation_AssignmentService.ProcessRepository.DraftRepository;
import org.springframework.stereotype.Service;

@Service
public class DraftService extends ProcessService<Draft, DraftDTO>{

    private final DraftRepository draftRepository;

    public DraftService(DraftRepository repository, ProcessFactory processFactory) {
        super(repository,processFactory);
        this.draftRepository = repository;
    }

    @Override
    public Draft reUploadProcess(Long pId, Draft pReUploadProcess) {
        return null;
    }
}
