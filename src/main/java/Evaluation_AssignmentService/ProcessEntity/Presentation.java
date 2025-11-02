package Evaluation_AssignmentService.ProcessEntity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "presentation")
public class Presentation {
    @Id
    @Column(name = "degreework_id")
    private Long degreeworkId;
    private List<Long> idjurys = new ArrayList<>();

    public Presentation() {}
    public Presentation(Long degreeworkId, Long idJury1) {
        this.degreeworkId = degreeworkId;
        idjurys.add(idJury1);
    }
    public Presentation(Long degreeworkId, Long idJury1, Long idJury2) {
        this.degreeworkId = degreeworkId;
        idjurys.add(idJury1);
        idjurys.add(idJury2);
    }
    public Presentation(Long degreeworkId, List<Long> idjurys) {
        this.degreeworkId = degreeworkId;
        this.idjurys = idjurys;
    }

    public Long getDegreeworkId() {
        return degreeworkId;
    }
    public List<Long> getIdjurys() {
        return idjurys;
    }
    public Presentation addjury(Long idJury) {
        if (idjurys.contains(idJury)) {

        }
        if (idjurys.size() >1) {

        }
        idjurys.add(idJury);
        return this;
    }
    public Presentation removejury(Long idJury) {
        if (idjurys.contains(idJury)) {
            idjurys.remove(idJury);
        }
        return this;
    }
    public Presentation setJurys(List<Long> idJurys) {
        this.idjurys = idJurys;
        return this;
    }

}
