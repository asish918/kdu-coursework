package asish.kdu.q3;

import asish.kdu.logging.CustomLogger;

import java.util.Objects;

public class APIResponseParser {
    public static void main(String[] args) {
        String xmlData =
                "<work>\n<id type=\"integer\">2361393</id><books_count type=\"integer\">813</books_count>\n<ratings_count type=\"integer\">1,16,315</ratings_count>\n<text_reviews_count type=\"integer\">3439</text_reviews_count><original_publication_year type=\"integer\">1854</original_publication_year><original_publication_month type=\"integer\" nil=\"true\"/><original_publication_day type=\"integer\" nil=\"true\"/><average_rating>5.0</average_rating>\n<best_book type=\"Book\">\n<id type=\"integer\">16902</id> <title>Avengers</title> <author>\n<id type=\"integer\">10264</id>\n <name>Tony Stark</name> </author>\n <image_url>http://images.gr-assets.com/books/1465675526m/16902.jpg</image_url>\n <small_image_url>http://images.gr-assets.com/books/1465675526s/16902.jpg</small\n </small_image_url>\n </best_book>\n </work>";

        Book book = parseXmlToBook(xmlData);
        CustomLogger.printLogger(book.toString());
    }

    private static Book parseXmlToBook(String xmlData) {
        Book book = new Book();

        book.setTitle(parse(xmlData, "title"));
        book.setAuthorName(parse(xmlData, new String[]{"author", "name"}));
        book.setPublicationYear(Integer.parseInt(parse(xmlData, "original_publication_year")));
        book.setAverageRating(Double.parseDouble(parse(xmlData, "average_rating")));
        book.setRatingsCount(Integer.parseInt(Objects.requireNonNull(parse(xmlData, "ratings_count")).replaceAll(",", "")));
        book.setImageUrl(parse(xmlData, "image_url"));

        return book;
    }

    private static String parse(String xml, String tagName) {
        String startTag = "<" + tagName;
        String endTag = "</" + tagName + ">";

        int startIndex = xml.indexOf(startTag);
        int extraAttributeLength = xml.indexOf(">", startIndex);

        int endIndex = xml.indexOf(endTag, startIndex);
        return (startIndex != -1 && endIndex != -1) ? xml.substring(extraAttributeLength + 1, endIndex) : null;
    }

    private static String parse(String xml, String[] classList) {
        String parentTag = classList[0];
        String childTagName = classList[1];

        String startTag = "<" + parentTag;
        String endTag = "</" + parentTag + ">";
        int startIndex = xml.indexOf(startTag);
        int extraAttributeLength = xml.indexOf(">", startIndex);
        int endIndex = xml.indexOf(endTag, startIndex);
        String parentContent = (startIndex != -1 && endIndex != -1) ? xml.substring(extraAttributeLength + 1, endIndex) : null;

        if (parentContent != null) {
            String childStartTag = "<" + childTagName;
            String childEndTag = "</" + childTagName + ">";
            int childStartIndex = parentContent.indexOf(childStartTag);
            int extraChildAttributeLength = xml.indexOf(">", childStartIndex);
            int childEndIndex = parentContent.indexOf(childEndTag, childStartIndex);
            return (childStartIndex != -1 && childEndIndex != -1) ? parentContent.substring(extraChildAttributeLength, childEndIndex) : null;
        }

        return null;
    }
}
