package Evaluation_AssignmentService.ProcessService;

import Evaluation_AssignmentService.ProcessEntity.Presentation;
import Evaluation_AssignmentService.ProcessRepository.PresentationRepository;
import Evaluation_AssignmentService.SecurityComponent.EnumTypeExceptions;
import Evaluation_AssignmentService.SecurityComponent.ProcessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PresentationService {

    private final PresentationRepository presentationRepository;


    public PresentationService(PresentationRepository presentationRepository) {
        this.presentationRepository = presentationRepository;
    }
    public Presentation findById(Long id) {
        return presentationRepository.findById(id).orElse(null);
    }
    public Presentation save(Presentation presentation) {
        verifyPresentation(presentation);
        if(presentationRepository.existsById(presentation.getDegreeworkId())){
            throw new ProcessException(EnumTypeExceptions.EXISTING_ID);
        }
        return presentationRepository.save(presentation);
    }
    public Presentation update(Long pId, Presentation presentation) {
        Presentation existPresentation = presentationRepository.findById(pId).orElseThrow(() -> new ProcessException(EnumTypeExceptions.NOT_FOUND));
        verifyPresentation(presentation);
        existPresentation.setJurys(presentation.getIdjurys());
        return presentationRepository.save(presentation);
    }

    private void verifyPresentation(Presentation presentation) {
        if (presentation == null) {
            throw new ProcessException(EnumTypeExceptions.NULL_PARAMETER);
        }
        if (presentation.getDegreeworkId() == null) {
            throw new ProcessException(EnumTypeExceptions.NULL_PARAMETER);
        }
        List<Long> idJurys =presentation.getIdjurys();
        if(idJurys.isEmpty()){
            throw new ProcessException(EnumTypeExceptions.NULL_PARAMETER);
        }
        if(idJurys.size()>2){
            throw new ProcessException(EnumTypeExceptions.PROCESS_NOT_SUPPORTED);
        }
        if (idJurys.get(1)==null){
            return;
        }
        if (idJurys.get(0)==idJurys.get(1)) {
            throw new ProcessException(EnumTypeExceptions.PROCESS_NOT_SUPPORTED);
        }
    }
}
