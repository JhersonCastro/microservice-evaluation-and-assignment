package Evaluation_AssignmentService.Dto;

import Evaluation_AssignmentService.Enum.EnumModality;

public class FormatADTO extends ProcessDTO {
    private EnumModality modality;
    private String companyLetterPath;

    public FormatADTO(){ }

    public String getCompanyLetterPath() { return companyLetterPath; }
    public void setCompanyLetterPath(String companyLetterPath) { this.companyLetterPath = companyLetterPath;}
    public EnumModality getModality() { return modality;}
    public void setModality(EnumModality modality) { this.modality = modality;}
}
