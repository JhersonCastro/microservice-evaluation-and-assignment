package Evaluation_AssignmentService.Dto;

public abstract class ProcessDTO {
    protected Long degreeWorkId;
    protected String url;

    public ProcessDTO() { }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public Long getDegreeWorkId() {
        return degreeWorkId;
    }

    public void setDegreeWorkId(Long degreeWorkId) {
        this.degreeWorkId = degreeWorkId;
    }
}
