import java.util.Scanner;

public class BookDeleter {
    private static final String csv_path = "books.csv";

    private String book_id;

    public void showPrompt() {
        String user_book_id = "";
        boolean id_is_correct = false;
        
        do {
            Scanner sc = new Scanner(System.in);
            BookItem bi = new BookItem();
            System.out.println();
            do {
                System.out.print("  Enter book ID: ");
                user_book_id = sc.nextLine();

                // Sanitize before processing.
                user_book_id = user_book_id.trim();
                user_book_id = user_book_id.toLowerCase();
            } while (!SchemaChecker.isValidInt(user_book_id));

            CSVDatabase csv_db = new CSVDatabase(csv_path);
            String[] book_data = csv_db.getLine(user_book_id);

            if (book_data.length == 0) {
                System.out.println(" No such book ID found in our database.");
                continue;
            }

            bi.populate(book_data);

            if (Integer.parseInt(bi.getBorrowedCopies()) > 0) {
                System.out.println(" Cannot delete book until all copies are returned.");
                continue;
            }

            System.out.println();
            System.out.println("  Book ID: " + bi.getBookID());
            System.out.println("  Book Title: " + bi.getBookTitle());
            System.out.println("  Book Author: " + bi.getBookAuthor());
            System.out.println("  Book Publisher: " + bi.getBookPublisher());
            System.out.println("  Year Published: " + bi.getYearPublished());

            String doreturn_answer = "";
            do {
                System.out.print(" Delete the following book? [y/N] ");
                doreturn_answer = sc.nextLine();
            } while (!doreturn_answer.equals("y") &&
                     !doreturn_answer.equals("n") &&
                     !doreturn_answer.isEmpty());

            if (doreturn_answer.equals("y"))
                id_is_correct = true;
        } while (!id_is_correct);

        book_id = user_book_id;
    }

    public void save() {
        CSVDatabase books_csv_db = new CSVDatabase(csv_path);
        books_csv_db.delete(book_id);
    }

    public String getBookID() {
        return book_id;
    }
}
