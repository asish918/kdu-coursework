package asish.kdu.spring_exercise_4.services;

import asish.kdu.spring_exercise_4.dtos.AuthorDTO;
import asish.kdu.spring_exercise_4.exceptions.AuthorException;
import asish.kdu.spring_exercise_4.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    private AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void parseAuthor(AuthorDTO authorDTO) throws AuthorException {
        authorRepository.addAuthorToLibrary(authorDTO);
    }
}
