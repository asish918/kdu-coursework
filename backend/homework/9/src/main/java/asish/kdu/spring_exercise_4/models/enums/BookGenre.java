package asish.kdu.spring_exercise_4.models.enums;

public enum BookGenre {
    FICTION,
    MYSTERY,
    SCIENCE_FICTION,
    ROMANCE,
    HORROR,
    FANTASY,
    NON_FICTION,
    THRILLER,
    HISTORICAL_FICTION,
    CHILDRENS,
    POETRY,
    DRAMA,
    COMEDY,
    BIOGRAPHY,
    SELF_HELP,
    CRIME,
    ADVENTURE,
    SCIENCE,
    PHILOSOPHY,
    TRAVEL;

    public static BookGenre fromString(String text) {
        for (BookGenre genre : BookGenre.values()) {
            if (genre.name().equalsIgnoreCase(text)) {
                return genre;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found in BookGenre enum");
    }
}
