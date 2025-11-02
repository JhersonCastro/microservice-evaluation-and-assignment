package Evaluation_AssignmentService.Dto;

import java.util.List;

public class PresentationDTO {
    private Long idDegreeWork;
    private List<Long> idjurys;

    public void setIdDegreeWork(Long idDegreeWork) {
        this.idDegreeWork = idDegreeWork;
    }

    public void setIdjurys(List<Long> idjurys) {
        this.idjurys = idjurys;
    }
    public Long getIdDegreeWork() {
        return idDegreeWork;
    }
    public List<Long> getIdjurys() {
        return idjurys;
    }
}
