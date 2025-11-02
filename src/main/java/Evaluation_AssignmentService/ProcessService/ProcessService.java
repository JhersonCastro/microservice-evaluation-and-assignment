package Evaluation_AssignmentService.ProcessService;

import Evaluation_AssignmentService.Dto.ProcessDTO;
import Evaluation_AssignmentService.Enum.EnumProcessStatus;
import Evaluation_AssignmentService.ProcessEntity.ProcessFactory;
import Evaluation_AssignmentService.SecurityComponent.EnumTypeExceptions;
import Evaluation_AssignmentService.ProcessEntity.BaseProcess;
import Evaluation_AssignmentService.ProcessRepository.ProcessRepository;
import Evaluation_AssignmentService.SecurityComponent.ProcessException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Transactional
public abstract class ProcessService<T extends BaseProcess, D extends ProcessDTO> {
    protected final ProcessRepository<T> repository;
    protected final ProcessFactory processFactory;

    @Autowired
    public ProcessService(ProcessRepository<T> repository, ProcessFactory processFactory) {
        this.repository = repository;
        this.processFactory = processFactory;
    }

    protected T findById(Long pId){ return repository.findById(pId).orElse(null); }
    public List<T> findByStatus(EnumProcessStatus pStatus) {
        return repository.findByStatus(pStatus);
    }
    public List<T> findAll() {
        return repository.findAll();
    }

    //TEMPLATE
    public T save(T pNewProcess) {
        if(extractByDegreeWorkId(pNewProcess.getDegreeworkId()) != null) throw new ProcessException(EnumTypeExceptions.EXISTING_ID);
        else validateBeforeCreate(pNewProcess);
        return repository.save(pNewProcess);
    }
    public T update(Long pId, T pUpdatedProcess) {
        T vProcess = this.findByDegreeWorkId(pId);
        pUpdatedProcess.setDegreeworkId(pId);
        return repository.save(pUpdatedProcess);
    }
    //TEMPLATE
    //FINAL
    public T reUploadProcess(T pReUploadProcess){
        T vCurrentProcess = this.findByDegreeWorkId(pReUploadProcess.getDegreeworkId());
        validateCurrentStatus(vCurrentProcess);
        if(!vCurrentProcess.getStatus().equals(EnumProcessStatus.REJECTED))
            throw new ProcessException(EnumTypeExceptions.NON_MODIFICABLE_PROCESS);
        validateCurrentStatus(vCurrentProcess);
        SynchronizeData(vCurrentProcess, pReUploadProcess);
        vCurrentProcess.setStatus(EnumProcessStatus.PENDING);
        return repository.save(vCurrentProcess);
    }

    //TEMPLATE
    //FINAL
    public T evaluateProcess(Long pId, String pComment, EnumProcessStatus pNewStatus) {
        T vCurrentProcess = this.findByDegreeWorkId(pId);
        validateCurrentStatus(vCurrentProcess);
        if(!vCurrentProcess.getStatus().equals(EnumProcessStatus.PENDING))
            throw new ProcessException(EnumTypeExceptions.PROCESS_NOT_PENDING);
        validateNewStatus(pNewStatus);
        vCurrentProcess.setComments(pComment);
        vCurrentProcess.setStatus(pNewStatus);
        updateInternalData(vCurrentProcess);
        return repository.save(vCurrentProcess);
    }
    private void validateCurrentStatus(T pCurrentProcess){
        if(pCurrentProcess.getStatus().equals(EnumProcessStatus.APPROVED))
            throw new ProcessException(EnumTypeExceptions.PROCESS_APPROVED);
        validateRequirements(pCurrentProcess);
    }
    private void validateNewStatus(EnumProcessStatus pNewStatus){
        if(pNewStatus == null || pNewStatus.equals(EnumProcessStatus.PENDING))
            throw new ProcessException(EnumTypeExceptions.INVALID_NEW_STATUS);
    }
    //OVERRIDE
    protected void SynchronizeData(T pCurrentProcess, T pUpdateProcess){
        pCurrentProcess.setUrl(pUpdateProcess.getUrl());
    }

    public T findByDegreeWorkId(Long pDegreeworkId) {
        return repository.findByDegreeworkId(pDegreeworkId)
                .orElseThrow(() -> new ProcessException(EnumTypeExceptions.NOT_FOUND));
    }
    public T extractByDegreeWorkId(Long pDegreeWorkId){
        return repository.findByDegreeworkId(pDegreeWorkId).orElse(null);
    }

    protected abstract void updateInternalData(T pUpdateProcess);
    protected abstract void validateBeforeCreate(T pNewProcess);
    protected abstract void validateRequirements(T pCurrentProcess);
}


