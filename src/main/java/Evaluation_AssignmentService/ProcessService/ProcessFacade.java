package Evaluation_AssignmentService.ProcessService;

import Evaluation_AssignmentService.ProcessEntity.Draft;

public class ProcessFacade {
    private DraftService draftService;
    private FormatAService formatAService;

    public ProcessFacade() { }
    public Draft getDraftById(Long pId){ return draftService.findByDegreeWorkId(pId); }
}
