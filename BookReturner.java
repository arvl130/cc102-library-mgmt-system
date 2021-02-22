import java.util.Scanner;

public class BookReturner {
    private static final String books_csv_path = "books.csv";
    private static final String members_csv_path = "members.csv";

    private String book_id;
    private String book_info;
    private String member_usn;
    private String member_info;

    public void showPrompt() {
        String user_book_id = "";
        String user_member_usn = "";
        boolean id_is_correct = false;
        boolean usn_is_correct = false;
        Scanner sc = new Scanner(System.in);
        MemberEntry me = new MemberEntry();
        BookItem bi = new BookItem();

        do {
            System.out.println();
            do {
                System.out.print("  Enter member USN: ");
                user_member_usn = sc.nextLine();

                // Sanitize before processing.
                user_member_usn = user_member_usn.trim();
                user_member_usn = user_member_usn.toLowerCase();
            } while (!SchemaChecker.isValidUSN(user_member_usn));

            CSVDatabase csv_db = new CSVDatabase(members_csv_path);
            String[] member_data = csv_db.getLine(user_member_usn);

            if (member_data.length == 0) {
                System.out.println(" No such user found in our database.");
                continue;
            }

            me.populate(member_data);

            if (Integer.parseInt(me.getBorrowedBooks()) == 0) {
                System.out.println(" This user has not borrowed any books.");
                continue;
            }

            usn_is_correct = true;
        } while (!usn_is_correct);
        
        do {
            System.out.println();
            do {
                System.out.print("  Enter book ID: ");
                user_book_id = sc.nextLine();

                // Sanitize before processing.
                user_book_id = user_book_id.trim();
                user_book_id = user_book_id.toLowerCase();
            } while (!SchemaChecker.isValidInt(user_book_id));

            CSVDatabase csv_db = new CSVDatabase(books_csv_path);
            String[] book_data = csv_db.getLine(user_book_id);

            if (book_data.length == 0) {
                System.out.println(" No such book ID found in our database.");
                continue;
            }

            bi.populate(book_data);

            if (Integer.parseInt(bi.getBorrowedCopies()) == 0) {
                System.out.println(" No copies of given book have been borrowed.");
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
                System.out.print(" Return the following book? [y/N] ");
                doreturn_answer = sc.nextLine();
            } while (!doreturn_answer.equals("y") &&
                     !doreturn_answer.equals("n") &&
                     !doreturn_answer.isEmpty());

            if (doreturn_answer.equals("y")) {
                id_is_correct = true;
            }
        } while (!id_is_correct);

        // Set the correct book ID, book info line, member USN, and member info line.
        member_usn = user_member_usn;
        book_id = user_book_id;

        int member_borrowed = Integer.parseInt(me.getBorrowedBooks()) - 1;
        String new_member_borrowed = Integer.toString(member_borrowed);
        member_info = me.getUSN() + "," + me.getFirstName() + "," +
                      me.getMiddleName() + "," + me.getLastName() + "," +
                      me.getBirthday() + "," + me.getContactNumber() + "," +
                      me.getAddress() + "," + new_member_borrowed;

        int book_copies = Integer.parseInt(bi.getBorrowedCopies()) - 1;
        String new_book_copies = Integer.toString(book_copies);
        book_info = bi.getBookID() + "," + bi.getBookTitle() + "," +
                    bi.getBookAuthor() + "," + bi.getBookPublisher() + "," +
                    bi.getYearPublished() + "," + bi.getCopies() + "," +  new_book_copies;
    }

    public void save() {
        CSVDatabase members_csv_db = new CSVDatabase(members_csv_path);
        members_csv_db.update(member_usn, member_info);

        CSVDatabase books_csv_db = new CSVDatabase(books_csv_path);
        books_csv_db.update(book_id, book_info);
    }

    public String getBookID() {
        return book_id;
    }

    public String getBookInfo() {
        return book_info;
    }

    public String getUSN() {
        return member_usn;
    }

    public String getMemberInfo() {
        return member_info;
    }
}
