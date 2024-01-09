package asish.kdu.q3;

public class Book {
    private String title;
    private String authorName;
    private int publicationYear;
    private double averageRating;
    private int ratingsCount;
    private String imageUrl;

    // Constructors, getters, setters...

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public int getRatingsCount() {
        return ratingsCount;
    }

    public void setRatingsCount(int ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                "\n, authorName='" + authorName + '\'' +
                "\n, publicationYear=" + publicationYear +
                "\n, averageRating=" + averageRating +
                "\n, ratingsCount=" + ratingsCount +
                "\n, imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
