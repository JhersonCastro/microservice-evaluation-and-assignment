package Evaluation_AssignmentService.ProcessService;

import Evaluation_AssignmentService.ProcessEntity.BaseProcess;
import Evaluation_AssignmentService.ProcessRepository.ProcessRepository;

public abstract class ProcessService<T extends BaseProcess>{
    protected final ProcessRepository<T> repository;

    public ProcessService(ProcessRepository<T> repository) {
        this.repository = repository;
    }
    public T findByDegreeWorkId(Long pId){ return repository.findById(pId).orElse(null);}
    public T save(T pNewProcess){ return repository.save(pNewProcess); }
}
