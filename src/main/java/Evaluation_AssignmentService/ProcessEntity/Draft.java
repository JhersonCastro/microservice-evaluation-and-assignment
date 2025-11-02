package Evaluation_AssignmentService.ProcessEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.util.Date;
import java.util.concurrent.TimeUnit;


@Entity
@Table(
        name = "draft",
        uniqueConstraints = @UniqueConstraint(columnNames = "degreework_id")
)
public class Draft extends BaseProcess {
    private long daysPassed = 0;

    public Draft() { super(); }

    public Draft(Long degreeworkId) {
        super(degreeworkId);
    }

    public Draft(Long pDegreeWork, String pUrl) {
        this.url = pUrl;
        this.degreeworkId = pDegreeWork;
    }

    private long calculateDaysPassed() {
        Date vCurrentDate = new Date();
        long vDiffMillis = vCurrentDate.getTime() - this.date.getTime();
        return TimeUnit.DAYS.convert(vDiffMillis, TimeUnit.MILLISECONDS);
    }
    public void updateDaysPassed(){ calculateDaysPassed(); }
    public long getDaysPassed() {
        daysPassed = calculateDaysPassed();
        return daysPassed;
    }

    public void setDaysPassed(long daysPassed) {
        calculateDaysPassed();
    }
}
