package Evaluation_AssignmentService.Dto;

public class ComunDTO {
    private Long id;
    private String action;

    public ComunDTO() {}
    public ComunDTO(Long id, String action) {
        this.id = id;
        this.action = action;
    }

    public Long getId() {
        return id;
    }

    public String getAction() {
        return action;
    }
}