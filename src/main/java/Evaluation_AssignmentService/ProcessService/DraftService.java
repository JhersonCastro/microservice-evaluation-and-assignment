package Evaluation_AssignmentService.ProcessService;

import Evaluation_AssignmentService.ProcessEntity.Draft;
import Evaluation_AssignmentService.ProcessRepository.DraftRepository;
import Evaluation_AssignmentService.ProcessRepository.ProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DraftService extends ProcessService<Draft>{

    @Autowired
    DraftRepository draftRepository;

    public DraftService(ProcessRepository<Draft> repository) {
        super(repository);
    }


}
