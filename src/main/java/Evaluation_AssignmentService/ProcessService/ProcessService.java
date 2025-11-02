package Evaluation_AssignmentService.ProcessService;

import Evaluation_AssignmentService.Dto.ProcessDTO;
import Evaluation_AssignmentService.Enum.EnumProcessStatus;
import Evaluation_AssignmentService.ProcessEntity.ProcessFactory;
import Evaluation_AssignmentService.SecurityComponent.EnumTypeExceptions;
import Evaluation_AssignmentService.ProcessEntity.BaseProcess;
import Evaluation_AssignmentService.ProcessRepository.ProcessRepository;
import Evaluation_AssignmentService.SecurityComponent.ProcessException;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public abstract class ProcessService<T extends BaseProcess, D extends ProcessDTO> {
    protected final ProcessRepository<T> repository;
    protected final ProcessFactory processFactory;

    public ProcessService(ProcessRepository<T> repository, ProcessFactory processFactory) {
        this.repository = repository;
        this.processFactory = processFactory;
    }
    public T findByDegreeWorkId(Long pId) {
        return repository.findById(pId).orElse(null);
    }

    public T save(T pNewProcess) {
        if (repository.existsById(pNewProcess.getDegreeworkId()))
            throw new ProcessException(EnumTypeExceptions.EXISTING_ID);
        else return repository.save(pNewProcess);
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public T update(Long pId, T pUpdatedProcess) {
        Optional<T> vProcess = repository.findById(pId);
        if(vProcess == null) throw new ProcessException(EnumTypeExceptions.NOT_FOUND);
        pUpdatedProcess.validateRequirements();
        pUpdatedProcess.setDegreeworkId(pId);
        return repository.save(pUpdatedProcess);
    }
    public abstract T reUploadProcess(Long pId, T pReUploadProcess);

    public T evaluateProcess(Long pId, String pComment, EnumProcessStatus pNewStatus) {
        //no deberia retornar el resultado de la evaluacion? (fallo al evaluar, evaluo correctamente, se nego etc..)
        T vCurrentProcess = this.findByDegreeWorkId(pId);
        if (vCurrentProcess == null)
            throw new ProcessException(EnumTypeExceptions.NOT_FOUND);
        vCurrentProcess.evaluate(pNewStatus,pComment);
        return repository.save(vCurrentProcess);
    }


    public List<T> findByStatus(EnumProcessStatus pStatus) {
        return repository.findByStatus(pStatus);
    }
}

