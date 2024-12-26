/**
 * Represents a book with basic information.
 *
 * @param ISBN The ISBN of the book.
 * @param authors The authors of the book.
 * @param publicationYear The year the book was published.
 * @param originalTitle The original title of the book.
 * @param title The title of the book.
 * @param averageRating The average rating of the book.
 */
public record Book(String ISBN, String authors, int publicationYear,
                   String originalTitle, String title, double averageRating) {
 /**
         * @throws IllegalArgumentException if ISBN, authors, originalTitle, or title are null or empty, or if averageRating is negative.
 */
    public Book {
        if (ISBN == null || ISBN.isBlank()) {
            throw new IllegalArgumentException("ISBN cannot be null or empty");
        }

        if (authors == null || authors.isBlank()) {
            throw new IllegalArgumentException("Authors cannot be null or empty");
        }

        if (originalTitle == null) {
            throw new IllegalArgumentException("Original title cannot be null.");
        }

        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }

        if (averageRating < 0) {
            throw new IllegalArgumentException("Average rating cannot be negative");
        }
    }
}
