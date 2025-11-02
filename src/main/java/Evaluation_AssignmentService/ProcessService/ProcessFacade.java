package Evaluation_AssignmentService.ProcessService;

import Evaluation_AssignmentService.Dto.EvaluateProcessDTO;
import Evaluation_AssignmentService.Dto.DraftDTO;
import Evaluation_AssignmentService.Dto.FormatADTO;
import Evaluation_AssignmentService.Enum.EnumProcessStatus;
import Evaluation_AssignmentService.ProcessEntity.Draft;
import Evaluation_AssignmentService.ProcessEntity.FormatA;
import Evaluation_AssignmentService.ProcessEntity.ProcessFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessFacade {
    @Autowired
    private ProcessFactory factory;
    @Autowired
    private DraftService draftService;
    @Autowired
    private FormatAService formatAService;

    public ProcessFacade() { }

    @Autowired
    public ProcessFacade(ProcessFactory factory, DraftService draftService, FormatAService formatAService) {
        this.factory = factory;
        this.draftService = draftService;
        this.formatAService = formatAService;
    }

    // Draft methods
    public Draft getDraftById(Long pId){ return draftService.extractByDegreeWorkId(pId);}
    public Draft saveDraft(DraftDTO pDto){
        return draftService.save((Draft)factory.createProcessFromDTO(pDto));
    }
    public List<Draft> getAllDrafts(){ return draftService.findAll(); }
    public Draft reUploadDraft(DraftDTO pDto){ return draftService.reUploadProcess((Draft)factory.createProcessFromDTO(pDto)); }
    public Draft evaluateDraft(Long pId, EvaluateProcessDTO pDto){ return draftService.evaluateProcess(pId, pDto.getComment(), pDto.getNewStatus()); }
    public List<Draft> getDraftsByStatus(EnumProcessStatus pStatus){ return draftService.findByStatus(pStatus);}

    // FormatA methods
    public FormatA getFormatAById(Long pId){ return formatAService.extractByDegreeWorkId(pId); }
    public FormatA saveFormatA(FormatADTO pDto){  return formatAService.save((FormatA) factory.createProcessFromDTO(pDto));}
    public List<FormatA> getAllFormatAs(){ return formatAService.findAll(); }
    public FormatA reUploadFormatA(FormatADTO pDto){
        return formatAService.reUploadProcess((FormatA) factory.createProcessFromDTO(pDto)); }
    public FormatA evaluateFormatA(Long pId, EvaluateProcessDTO pDto){ return formatAService.evaluateProcess(pId,pDto.getComment(),pDto.getNewStatus()); }
    public List<FormatA> getFormatsAByStatus(EnumProcessStatus pStatus) { return formatAService.findByStatus(pStatus); }
}


