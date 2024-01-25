package asish.kdu.spring_exercise_4.dtos.mappers;

import asish.kdu.spring_exercise_4.dtos.PatronDTO;
import asish.kdu.spring_exercise_4.exceptions.PatronException;
import asish.kdu.spring_exercise_4.models.Patron;

public class PatronMapper {
    private PatronMapper() {}
    public static Patron dtoToEntity(PatronDTO patronDTO) throws PatronException {
        return new Patron(patronDTO.getName());
    }

    public static PatronDTO entityToDto(Patron patron) {
        return new PatronDTO(patron.name());
    }
}
