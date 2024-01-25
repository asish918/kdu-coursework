package asish.kdu.spring_exercise_4.dtos;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class AuthorDTO {
    private @Getter(AccessLevel.PUBLIC) String name;
}
