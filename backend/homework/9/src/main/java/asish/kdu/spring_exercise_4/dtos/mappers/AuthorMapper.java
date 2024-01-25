package asish.kdu.spring_exercise_4.dtos.mappers;

import asish.kdu.spring_exercise_4.dtos.AuthorDTO;
import asish.kdu.spring_exercise_4.exceptions.AuthorException;
import asish.kdu.spring_exercise_4.models.Author;

public class AuthorMapper {
    private AuthorMapper() {}
    public static Author dtoToEntity(AuthorDTO authorDTO) throws AuthorException {
        return new Author(authorDTO.getName());
    }

    public static AuthorDTO entityToDto(Author author) {
        return new AuthorDTO(author.name());
    }
}
