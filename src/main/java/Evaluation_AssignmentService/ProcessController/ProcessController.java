package Evaluation_AssignmentService.ProcessController;

import Evaluation_AssignmentService.Dto.EvaluateProcessDTO;
import Evaluation_AssignmentService.Dto.DraftDTO;
import Evaluation_AssignmentService.Dto.FormatADTO;
import Evaluation_AssignmentService.Enum.EnumProcessStatus;
import Evaluation_AssignmentService.ProcessEntity.Draft;
import Evaluation_AssignmentService.ProcessEntity.FormatA;
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
    public ProcessController(ProcessFacade processFacade) {
        this.processFacade = processFacade;
    }

    //Dragt
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
        return ResponseEntity.ok(processFacade.saveDraft(pDraft));
    }
    @PutMapping("/draft/update/{id}")
    public ResponseEntity<Draft> reUploadDraft(@PathVariable Long id, @RequestBody DraftDTO pUpdatedDraft) {
        return ResponseEntity.ok(processFacade.reUploadDraft(id, pUpdatedDraft));
    }
    @PutMapping("/draft/evaluate/{id}")//retornamos objeto completo o solo mensaje?
    public ResponseEntity<Draft> evaluateDraft(@PathVariable Long id, @RequestBody EvaluateProcessDTO request) {
        Draft vEvaluatedDraft = processFacade.evaluateDraft(id, request);
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
        return ResponseEntity.ok(processFacade.saveFormatA(pFormatA));
    }
    @PutMapping("/formatA/update/{id}")
    public ResponseEntity<FormatA> reUploadFormatA(@PathVariable Long id, @RequestBody FormatADTO pUpdatedFormatA) {
        return ResponseEntity.ok(processFacade.reUploadFormatA(id, pUpdatedFormatA));
    }
    @PutMapping("/formatA/evaluate/{id}")
    public ResponseEntity<FormatA> evaluateFormatA(@PathVariable Long id, @RequestBody EvaluateProcessDTO request) {
        FormatA vEvaluatedDraft = processFacade.evaluateFormatA(id, request);
        System.out.println("Evaluado");
        if(vEvaluatedDraft.getAttempts() > 3)
            vEvaluatedDraft.getAttempts();//LLAMADO A RABBITMQ
        return ResponseEntity.ok(vEvaluatedDraft);
    }
    @GetMapping("/formatA/pending")
    public ResponseEntity<List<FormatA>> getPendingFormatsA() {
        List<FormatA> formatsA = processFacade.getFormatsAByStatus(EnumProcessStatus.PENDING);
        return ResponseEntity.ok(formatsA);
    }

}

