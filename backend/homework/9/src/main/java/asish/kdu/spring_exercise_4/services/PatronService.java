package asish.kdu.spring_exercise_4.services;

import asish.kdu.spring_exercise_4.dtos.PatronDTO;
import asish.kdu.spring_exercise_4.exceptions.PatronException;
import asish.kdu.spring_exercise_4.repository.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatronService {
    private PatronRepository patronRepository;

    @Autowired
    public PatronService(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }
    public void parsePatron(PatronDTO patronDTO) throws PatronException {
        patronRepository.addPatronToLibrary(patronDTO);
    }
}
