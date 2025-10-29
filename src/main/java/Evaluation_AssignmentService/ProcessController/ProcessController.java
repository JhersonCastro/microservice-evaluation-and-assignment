package Evaluation_AssignmentService.ProcessController;

import Evaluation_AssignmentService.ProcessEntity.Draft;
import Evaluation_AssignmentService.ProcessService.ProcessFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Process")
public class ProcessController {
    private ProcessFacade process;

    @GetMapping("/{pDegreeWorkId}")
    public Draft getDraftById(Long pDegreeWorkId){ return process.getDraftById(pDegreeWorkId);}

}
