package Evaluation_AssignmentService.ProcessService;

import Evaluation_AssignmentService.Dto.DraftDTO;
import Evaluation_AssignmentService.ProcessEntity.Draft;
import Evaluation_AssignmentService.ProcessEntity.ProcessFactory;
import Evaluation_AssignmentService.ProcessRepository.DraftRepository;
import Evaluation_AssignmentService.SecurityComponent.EnumTypeExceptions;
import Evaluation_AssignmentService.SecurityComponent.ProcessException;
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
        Draft existingDraft = draftRepository.findById(pId).orElseThrow(() -> new ProcessException(EnumTypeExceptions.NOT_FOUND));
        existingDraft.setComments(pReUploadProcess.getComments());
        existingDraft.setDate(pReUploadProcess.getDate());
        existingDraft.setUrl(pReUploadProcess.getUrl());
        return draftRepository.save(existingDraft);
    }
}
