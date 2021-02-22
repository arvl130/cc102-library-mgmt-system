import java.util.Scanner;

public class MemberUnregister {
    private static final String csv_path = "members.csv";

    private String member_usn;

    public void showPrompt() {
        String user_member_usn = "";
        boolean usn_is_correct = false;
        
        do {
            Scanner sc = new Scanner(System.in);
            MemberEntry me = new MemberEntry();

            System.out.println();

            do {
                System.out.print("  Enter member USN: ");
                user_member_usn = sc.nextLine();

                // Sanitize before processing.
                user_member_usn = user_member_usn.trim();
                user_member_usn = user_member_usn.toLowerCase();
            } while (!SchemaChecker.isValidUSN(user_member_usn));

            CSVDatabase csv_db = new CSVDatabase(csv_path);
            String[] member_data = csv_db.getLine(user_member_usn);

            if (member_data.length == 0) {
                System.out.println(" No such member found in our database.");
                continue;
            }

            me.populate(member_data);

            if (Integer.parseInt(me.getBorrowedBooks()) > 0) {
                System.out.println(" Cannot unregister member until all books are returned.");
                continue;
            }

            System.out.println();
            System.out.println("  USN: " + me.getUSN());
            System.out.println("  First name: " + me.getFirstName());
            System.out.println("  Middle name: " + me.getMiddleName());
            System.out.println("  Last name: " + me.getLastName());
            System.out.println("  Birthday: " + me.getBirthday());
            System.out.println("  Contact number: " + me.getContactNumber());
            System.out.println("  Address: " + me.getAddress());
            System.out.println("  Borrowed books: " + me.getBorrowedBooks());

            String doreturn_answer = "";
            do {
                System.out.print(" Unregister the following user? [y/N] ");
                doreturn_answer = sc.nextLine();
            } while (!doreturn_answer.equals("y") &&
                     !doreturn_answer.equals("n") &&
                     !doreturn_answer.isEmpty());

            if (doreturn_answer.equals("y"))
                usn_is_correct = true;
        } while (!usn_is_correct);

        member_usn = user_member_usn;
    }

    public void save() {
        CSVDatabase csv_db = new CSVDatabase(csv_path);
        csv_db.delete(member_usn);
    }

    public String getUSN() {
        return member_usn;
    }
}
