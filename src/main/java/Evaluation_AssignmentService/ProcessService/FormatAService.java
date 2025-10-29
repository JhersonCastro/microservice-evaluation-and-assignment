package Evaluation_AssignmentService.ProcessService;

import Evaluation_AssignmentService.ProcessEntity.FormatA;
import Evaluation_AssignmentService.ProcessRepository.ProcessRepository;
import org.springframework.stereotype.Service;

@Service
public class FormatAService extends ProcessService<FormatA>{

    public FormatAService(ProcessRepository<FormatA> repository) {
        super(repository);
    }
}
