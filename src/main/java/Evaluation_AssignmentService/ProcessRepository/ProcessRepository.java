package Evaluation_AssignmentService.ProcessRepository;

import Evaluation_AssignmentService.Enum.EnumProcessStatus;
import Evaluation_AssignmentService.ProcessEntity.BaseProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessRepository<T extends BaseProcess> extends JpaRepository<T, Long> {
    List<T> findByStatus(EnumProcessStatus status);
}
