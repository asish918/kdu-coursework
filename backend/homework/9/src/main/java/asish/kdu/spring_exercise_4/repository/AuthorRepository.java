package asish.kdu.spring_exercise_4.repository;

import asish.kdu.spring_exercise_4.dtos.AuthorDTO;
import asish.kdu.spring_exercise_4.dtos.mappers.AuthorMapper;
import asish.kdu.spring_exercise_4.exceptions.AuthorException;
import asish.kdu.spring_exercise_4.logging.CustomLogger;
import asish.kdu.spring_exercise_4.models.Author;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class AuthorRepository {
    List<Author> authors;

    public AuthorRepository() {
        this.authors = new ArrayList<>();
    }

    public void addAuthorToLibrary(AuthorDTO authorDTO) throws AuthorException {
        Author author = AuthorMapper.dtoToEntity(authorDTO);
        for(Author a : authors) {
            if(Objects.equals(author.name(), a.name()))
                return;
        }

        authors.add(author);
        CustomLogger.printLogger("Author Added Successfully", CustomLogger.LoggerType.INFO);
    }
}
