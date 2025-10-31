package Evaluation_AssignmentService.ProcessService;

import Evaluation_AssignmentService.ProcessEntity.FormatA;
import Evaluation_AssignmentService.ProcessRepository.FormatARepository;
import Evaluation_AssignmentService.ProcessRepository.ProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormatAService extends ProcessService<FormatA>{

    @Autowired
    FormatARepository formatARepository;

    public FormatAService(ProcessRepository<FormatA> repository) {
        super(repository);
    }
}
