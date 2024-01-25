package asish.kdu.spring_exercise_4.dtos;

import lombok.*;


@Data
@AllArgsConstructor
public class BookDTO {
    @NonNull private @Getter(AccessLevel.PUBLIC) String name;
    @NonNull private @Getter(AccessLevel.PUBLIC) String isbn;
    @NonNull private @Getter(AccessLevel.PUBLIC) String genre;
    @NonNull private @Getter(AccessLevel.PUBLIC) String checkedOut;
    @NonNull private @Getter(AccessLevel.PUBLIC) String authorName;
    @NonNull private @Getter(AccessLevel.PUBLIC) String patronName;
}
