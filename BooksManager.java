import java.util.Scanner;

public class BooksManager {
    private static final String csv_path = "books.csv";

    private CSVDatabase csv_db;

    public BooksManager() {
        // Start the connection with database.
        csv_db = new CSVDatabase(csv_path);
    }

    public void registerBook() {
        // Print the book registration banner.
        System.out.println("  ___________________");
        System.out.println(" /                   \\");
        System.out.println(" | BOOK REGISTRATION |");
        System.out.println(" \\                   /");
        System.out.println("  -------------------");

        Scanner sc = new Scanner(System.in);

        // Confirm decision before entering new books.
        String continue_answer = "";
        do {
            System.out.print(" Please fill in the details of the new book. Continue? [Y/n] ");
            continue_answer = sc.nextLine();

            // Sanitize before processing.
            continue_answer = continue_answer.trim();
            continue_answer = continue_answer.toLowerCase();
        } while (!continue_answer.equals("y") && 
                 !continue_answer.equals("n") &&
                 !continue_answer.isEmpty());

        if (continue_answer.equals("n"))
            return; // Cancel registration if answer is no.

        // Do registration only once, by default.
        boolean keep_running = false;
        do {
            // Set to false again, in case this was changed in
            // the previous iteration.
            keep_running = false;
            
            BookItem bi = new BookItem();

            System.out.println();

            bi.promptBookID();
            String book_id = bi.getBookID();

            bi.promptBookTitle();
            String book_title = bi.getBookTitle();

            bi.promptBookAuthor();
            String book_author = bi.getBookAuthor();

            bi.promptBookPublisher();
            String book_publisher = bi.getBookPublisher();

            bi.promptYearPublished();
            String year_published = bi.getYearPublished();

            bi.promptCopies();
            String copies = bi.getCopies();

            String borrowed_copies = "0";

            System.out.println();

            csv_db.insert(book_id + "," + book_title + "," +
                          book_author + "," + book_publisher + "," + 
                          year_published + "," + copies + "," + borrowed_copies);
            
            String addanother_answer = "";
            do {
                System.out.print(" Book saved. Add another book? [y/N] ");
                addanother_answer = sc.nextLine();

                // Sanitize before processing.
                addanother_answer = addanother_answer.trim();
                addanother_answer = addanother_answer.toLowerCase();
            } while (!addanother_answer.equals("y") && 
                     !addanother_answer.equals("n") &&
                     !addanother_answer.isEmpty());

            if (addanother_answer.equals("y"))
                keep_running = true; // Repeat registration if answer is yes.

        } while (keep_running);
    }

    public void loanBook() {
        // Print the book registration banner.
        System.out.println("  __________________");
        System.out.println(" /                  \\");
        System.out.println(" |   BOOK LOANING   |");
        System.out.println(" \\                  /");
        System.out.println("  ------------------");

        Scanner sc = new Scanner(System.in);

        // Confirm decision before loan new books.
        String continue_answer = "";
        do {
            System.out.print(" Please fill in the details for the book to be loaned. Continue? [Y/n] ");
            continue_answer = sc.nextLine();

            // Sanitize before processing.
            continue_answer = continue_answer.trim();
            continue_answer = continue_answer.toLowerCase();
        } while (!continue_answer.equals("y") && 
                 !continue_answer.equals("n") &&
                 !continue_answer.isEmpty());

        if (continue_answer.equals("n"))
            return; // Cancel returning if answer is no.

        // Do returning only once, by default.
        boolean keep_running = false;
        do {
            // Set to false again, in case this was changed in
            // the previous iteration.
            keep_running = false;

            BookLoaner bl = new BookLoaner();
            bl.showPrompt();
            bl.save();

            System.out.println();
            
            String addanother_answer = "";
            do {
                System.out.print(" Book loaned. Loan another book? [y/N] ");
                addanother_answer = sc.nextLine();

                // Sanitize before processing.
                addanother_answer = addanother_answer.trim();
                addanother_answer = addanother_answer.toLowerCase();
            } while (!addanother_answer.equals("y") && 
                     !addanother_answer.equals("n") &&
                     !addanother_answer.isEmpty());

            if (addanother_answer.equals("y"))
                keep_running = true; // Repeat loaning if answer is yes.

        } while (keep_running);
    }

    public void returnBook() {
        // Print the book registration banner.
        System.out.println("  ____________________");
        System.out.println(" /                    \\");
        System.out.println(" |   BOOK RETURNING   |");
        System.out.println(" \\                    /");
        System.out.println("  --------------------");

        Scanner sc = new Scanner(System.in);

        // Confirm decision before returning new books.
        String continue_answer = "";
        do {
            System.out.print(" Please fill in the details for the book to be returned. Continue? [Y/n] ");
            continue_answer = sc.nextLine();

            // Sanitize before processing.
            continue_answer = continue_answer.trim();
            continue_answer = continue_answer.toLowerCase();
        } while (!continue_answer.equals("y") && 
                 !continue_answer.equals("n") &&
                 !continue_answer.isEmpty());

        if (continue_answer.equals("n"))
            return; // Cancel returning if answer is no.

        // Do returning only once, by default.
        boolean keep_running = false;
        do {
            // Set to false again, in case this was changed in
            // the previous iteration.
            keep_running = false;

            BookReturner br = new BookReturner();
            br.showPrompt();
            br.save();

            System.out.println();
            
            String addanother_answer = "";
            do {
                System.out.print(" Book returned. Return another book? [y/N] ");
                addanother_answer = sc.nextLine();

                // Sanitize before processing.
                addanother_answer = addanother_answer.trim();
                addanother_answer = addanother_answer.toLowerCase();
            } while (!addanother_answer.equals("y") && 
                     !addanother_answer.equals("n") &&
                     !addanother_answer.isEmpty());

            if (addanother_answer.equals("y"))
                keep_running = true; // Repeat returning if answer is yes.

        } while (keep_running);
    }

    public void viewAll() {
        String[][] books_arr = csv_db.getAllLines();

        System.out.println();
        System.out.println("  + - - - ");
        for (String row[] : books_arr) {
            System.out.println("  | Book ID: " + row[0]);
            System.out.println("  | Book Title: " + row[1]);
            System.out.println("  | Book Author: " + row[2]);
            System.out.println("  | Book Publisher: " + row[3]);
            System.out.println("  | Book Year published: " + row[4]);
            System.out.println("  | Book Copies: " + row[5]);
            System.out.println("  | Borrowed Copies: " + row[6]);
            System.out.println("  + - - - ");
        }
    }

    public void deleteBook() {
        // Print the book registration banner.
        System.out.println();
        System.out.println(" /!! !!!!!!!!!!!!! !!\\");
        System.out.println(" !!! BOOK DELETION !!!");
        System.out.println(" \\!! !!!!!!!!!!!!! !!/");
        System.out.println();

        Scanner sc = new Scanner(System.in);

        // Confirm decision before returning new books.
        String continue_answer = "";
        do {
            System.out.print(" Please fill in the details for the book to be deleted. Continue? [Y/n] ");
            continue_answer = sc.nextLine();

            // Sanitize before processing.
            continue_answer = continue_answer.trim();
            continue_answer = continue_answer.toLowerCase();
        } while (!continue_answer.equals("y") && 
                 !continue_answer.equals("n") &&
                 !continue_answer.isEmpty());

        if (continue_answer.equals("n"))
            return; // Cancel returning if answer is no.
        
        // Do deletion only once, by default.
        boolean keep_running = false;
        do {
            // Set to false again, in case this was changed in
            // the previous iteration.
            keep_running = false;

            System.out.println();

            BookDeleter bd = new BookDeleter();
            bd.showPrompt();
            bd.save();

            System.out.println();
            
            String addanother_answer = "";
            do {
                System.out.print(" Book deleted. Delete another book? [y/N] ");
                addanother_answer = sc.nextLine();

                // Sanitize before processing.
                addanother_answer = addanother_answer.trim();
                addanother_answer = addanother_answer.toLowerCase();
            } while (!addanother_answer.equals("y") && 
                     !addanother_answer.equals("n") &&
                     !addanother_answer.isEmpty());

            if (addanother_answer.equals("y"))
                keep_running = true; // Repeat returning if answer is yes.

        } while (keep_running);
    }
}
