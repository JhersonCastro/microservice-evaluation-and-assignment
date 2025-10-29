package Evaluation_AssignmentService.ProcessRepository;

import Evaluation_AssignmentService.ProcessEntity.BaseProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessRepository<T extends BaseProcess> extends JpaRepository<T, Long> { }
