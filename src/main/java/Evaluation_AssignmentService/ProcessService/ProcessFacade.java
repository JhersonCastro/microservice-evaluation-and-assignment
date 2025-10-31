package Evaluation_AssignmentService.ProcessService;

import Evaluation_AssignmentService.ProcessEntity.Draft;
import org.springframework.beans.factory.annotation.Autowired;

public class ProcessFacade {
    @Autowired
    private DraftService draftService;

    @Autowired
    private FormatAService formatAService;

    public ProcessFacade() { }
    public Draft getDraftById(Long pId){ return draftService.findByDegreeWorkId(pId); }
}
