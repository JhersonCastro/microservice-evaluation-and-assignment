package Evaluation_AssignmentService.ProcessService;

import Evaluation_AssignmentService.ProcessEntity.BaseProcess;
import Evaluation_AssignmentService.ProcessRepository.ProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ProcessService<T extends BaseProcess>{
    @Autowired
    protected final ProcessRepository<T> repository;

    public ProcessService(ProcessRepository<T> repository) {
        this.repository = repository;
    }
    public T findByDegreeWorkId(Long pId){ return repository.findById(pId).orElse(null);}
    public T save(T pNewProcess){ return repository.save(pNewProcess); }
}
