import java.util.Scanner;

public class BookItem {
    private static final String csv_path = "books.csv";

    private String book_id;
    private String book_title;
    private String book_author;
    private String book_publisher;
    private String year_published;
    private String copies;
    private String borrowed_copies;

    public String getBookID() {
        return book_id;
    }

    public String getBookTitle() {
        return book_title;
    }

    public String getBookAuthor() {
        return book_author;
    }

    public String getBookPublisher() {
        return book_publisher;
    }

    public String getYearPublished() {
        return year_published;
    }

    public String getCopies() {
        return copies;
    }

    public String getBorrowedCopies() {
        return borrowed_copies;
    }

    public void promptBookID() {
        boolean book_id_is_valid = false;
        do {
            do {
                System.out.print("  Enter book ID: ");
                Scanner sc = new Scanner(System.in);
                book_id = sc.nextLine();

                // Sanitation.
                book_id = book_id.replace(",", " ");
                book_id = book_id.trim();
            } while (!SchemaChecker.isValidInt(book_id));

            book_id = Integer.toString(Integer.parseInt(book_id));

            CSVDatabase csv_db = new CSVDatabase(csv_path);
            String[] book_data = csv_db.getLine(book_id);

            if (book_data.length != 0) {
                System.out.println(" Book ID already exists in the database.");
                continue;
            }
            
            book_id_is_valid = true;
        } while (!book_id_is_valid);
    }

    public void promptBookTitle() {
        do {
            System.out.print("  Enter book title: ");
            Scanner sc = new Scanner(System.in);
            book_title = sc.nextLine();

            // Sanitation.
            book_title = book_title.replace(",", " ");
            book_title = book_title.trim();
        } while (!SchemaChecker.isValidStr(book_title));
    }

    public void promptBookAuthor() {
        do {
            System.out.print("  Enter book author: ");
            Scanner sc = new Scanner(System.in);
            book_author = sc.nextLine();

            // Sanitation.
            book_author = book_author.replace(",", " ");
            book_author = book_author.trim();
        } while (!SchemaChecker.isValidStr(book_author));
    }

    public void promptBookPublisher() {
        do {
            System.out.print("  Enter book publisher: ");
            Scanner sc = new Scanner(System.in);
            book_publisher = sc.nextLine();

            // Sanitation.
            book_publisher = book_publisher.replace(",", " ");
            book_publisher = book_publisher.trim();
        } while (!SchemaChecker.isValidStr(book_publisher));
    }

    public void promptYearPublished() {
        do {
            System.out.print("  Enter year published: ");
            Scanner sc = new Scanner(System.in);
            year_published = sc.nextLine();

            // Sanitation.
            year_published = year_published.replace(",", " ");
            year_published = year_published.trim();
        } while (!SchemaChecker.isValidInt(year_published));

        year_published = Integer.toString(Integer.parseInt(year_published));
    }

    public void promptCopies() {
        do {
            System.out.print("  Enter number of copies: ");
            Scanner sc = new Scanner(System.in);
            copies = sc.nextLine();

            // Sanitation.
            copies = copies.replace(",", " ");
            copies = copies.trim();
        } while (!SchemaChecker.isValidInt(copies));

        copies = Integer.toString(Integer.parseInt(copies));
    }

    public void populate(String[] book_data) {
        book_id = book_data[0];
        book_title = book_data[1];
        book_author = book_data[2];
        book_publisher = book_data[3];
        year_published = book_data[4];
        copies = book_data[5];
        borrowed_copies = book_data[6];
    }
}
