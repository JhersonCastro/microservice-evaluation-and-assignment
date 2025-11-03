package Evaluation_AssignmentService.ProcessController;

import Evaluation_AssignmentService.Comunication.Publisher;
import Evaluation_AssignmentService.Dto.*;
import Evaluation_AssignmentService.Enum.EnumProcessStatus;
import Evaluation_AssignmentService.ProcessEntity.Draft;
import Evaluation_AssignmentService.ProcessEntity.FormatA;
import Evaluation_AssignmentService.ProcessEntity.Presentation;
import Evaluation_AssignmentService.ProcessService.PresentationService;
import Evaluation_AssignmentService.ProcessService.ProcessFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/process")
public class ProcessController {

    private final ProcessFacade processFacade;
    @Autowired
    private Publisher publisher;


    @Autowired
    public ProcessController(ProcessFacade processFacade) {
        this.processFacade = processFacade;
    }
    //Draft
    @GetMapping("/draft/{id}")
    public ResponseEntity<Draft> getDraftById(@PathVariable Long id) {
        Draft draft = processFacade.getDraftById(id);
        if(draft == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(draft);
    }
    @GetMapping("/draft/all")
    public ResponseEntity<List<Draft>> getAllDrafts() {
        return ResponseEntity.ok(processFacade.getAllDrafts());
    }
    @PostMapping("/draft")
    public ResponseEntity<Draft> saveDraft(@RequestBody DraftDTO pDraft) {
        Draft draft = processFacade.saveDraft(pDraft);
        PostComunQueue(new ComunDTO(draft.getDegreeworkId(), "upload_draft"));
        return ResponseEntity.ok(draft);
    }
    @PutMapping("/draft/update")
    public ResponseEntity<Draft> reUploadDraft(@RequestBody DraftDTO pUpdatedDraft) {
        return ResponseEntity.ok(processFacade.saveDraft(pUpdatedDraft));
    }
    @PutMapping("/draft/evaluate/{id}")//retornamos objeto completo o solo mensaje?
    public ResponseEntity<Draft> evaluateDraft(@PathVariable Long id, @RequestBody EvaluateProcessDTO request) {
        Draft vEvaluatedDraft = processFacade.evaluateDraft(id, request);
        if(vEvaluatedDraft.getStatus().equals(EnumProcessStatus.APPROVED)){
            PostComunQueue(new ComunDTO(vEvaluatedDraft.getDegreeworkId(), "aprove_draft"));
        }
        PostComunQueue(new ComunDTO(vEvaluatedDraft.getDegreeworkId(), "reject_draft"));
        return ResponseEntity.ok(vEvaluatedDraft);
    }
    @GetMapping("/draft/pending")
    public ResponseEntity<List<Draft>> getPendingDrafts() {
        List<Draft> drafts = processFacade.getDraftsByStatus(EnumProcessStatus.PENDING);
        return ResponseEntity.ok(drafts);
    }

    //FormatA
    @GetMapping("/formatA/{id}")
    public ResponseEntity<FormatA> getFormatAById(@PathVariable Long id) {
        FormatA formatA = processFacade.getFormatAById(id);
        if(formatA == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(formatA);
    }
    @GetMapping("/formatA/all")
    public ResponseEntity<List<FormatA>> getAllFormatAs() {
        return ResponseEntity.ok(processFacade.getAllFormatAs());
    }
    @PostMapping("/formatA")
    public ResponseEntity<FormatA> saveFormatA(@RequestBody FormatADTO pFormatA) {
        FormatA formatA = processFacade.saveFormatA(pFormatA);
        PostComunQueue(new ComunDTO(formatA.getDegreeworkId(), "upload_format_a"));
        return ResponseEntity.ok(formatA);
    }
    @PutMapping("/formatA/update")
    public ResponseEntity<FormatA> reUploadFormatA(@RequestBody FormatADTO pUpdatedFormatA) {
        return ResponseEntity.ok(processFacade.reUploadFormatA(pUpdatedFormatA));
    }
    @PutMapping("/formatA/evaluate/{id}")
    public ResponseEntity<FormatA> evaluateFormatA(@PathVariable Long id, @RequestBody EvaluateProcessDTO request) {
        FormatA formatA = processFacade.evaluateFormatA(id, request);
        if(formatA.getStatus().equals(EnumProcessStatus.APPROVED))
            PostComunQueue(new ComunDTO(id, "accept_format_a"));
        else
            PostComunQueue(new ComunDTO(id, "reject_format_a"));
        return ResponseEntity.ok(formatA);
    }
    @GetMapping("/formatA/pending")
    public ResponseEntity<List<FormatA>> getPendingFormatsA() {
        List<FormatA> formatsA = processFacade.getFormatsAByStatus(EnumProcessStatus.PENDING);
        return ResponseEntity.ok(formatsA);
    }

    //Presentation
    @GetMapping("/presentation/{id}")
    public ResponseEntity<Presentation> getPresentationById(@PathVariable Long id) {
        return ResponseEntity.ok(processFacade.PresentationFindById(id));
    }
    @PostMapping("/Presentation")
    public ResponseEntity<Presentation> savePresentation(@RequestBody PresentationDTO pPresentation) {
        return ResponseEntity.ok(processFacade.Presentationsave(new Presentation(pPresentation.getIdDegreeWork(),pPresentation.getIdjurys())));
    }
    @PutMapping("/Presentation/update/{id}")
    public ResponseEntity<Presentation> reUploadPresentation(@PathVariable Long id, @RequestBody PresentationDTO pPresentation) {
        return ResponseEntity.ok(processFacade.Presentationupdate(id,new Presentation(pPresentation.getIdDegreeWork(),pPresentation.getIdjurys())));
    }

    //communications
    @PostMapping("/postQueue")
    public ResponseEntity<String> PostComunQueue(@RequestBody ComunDTO message) {
        publisher.sendMessageComunQueue(message);
        return ResponseEntity.ok("Message sent");
    }
}