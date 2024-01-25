package asish.kdu.spring_exercise_4.repository;

import asish.kdu.spring_exercise_4.dtos.PatronDTO;
import asish.kdu.spring_exercise_4.dtos.mappers.PatronMapper;
import asish.kdu.spring_exercise_4.exceptions.PatronException;
import asish.kdu.spring_exercise_4.logging.CustomLogger;
import asish.kdu.spring_exercise_4.models.Patron;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class PatronRepository {
    private List<Patron> patrons;

    public PatronRepository() {
        this.patrons = new ArrayList<>();
    }
    public void addPatronToLibrary(PatronDTO patronDTO) throws PatronException {
        Patron patron = PatronMapper.dtoToEntity(patronDTO);
        for(Patron p : patrons) {
            if(Objects.equals(patron.name(), p.name()))
                return;
        }

        patrons.add(patron);
        CustomLogger.printLogger("Patron Added Successfully", CustomLogger.LoggerType.INFO);
    }
}
