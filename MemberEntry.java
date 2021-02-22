import java.util.Scanner;

public class MemberEntry {
    private static final String csv_path = "members.csv";

    private String usn;
    private String first_name;
    private String middle_name;
    private String last_name;
    private String birthday;
    private String contact_num;
    private String address;
    private String borrowed_books;

    public String getUSN() {
        return usn;
    }

    public String getFirstName() {
        return first_name;
    }

    public String getMiddleName() {
        return middle_name;
    }

    public String getLastName() {
        return last_name;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getContactNumber() {
        return contact_num;
    }

    public String getAddress() {
        return address;
    }

    public String getBorrowedBooks() {
        return borrowed_books;
    }

    public void promptUSN() {
        boolean usn_is_valid = false;
        do {
            do {
                System.out.print("  Enter USN: ");
                Scanner sc = new Scanner(System.in);
                usn = sc.nextLine();

                // Sanitation.
                usn = usn.replace(",", " ");
                usn = usn.trim();
            } while (!SchemaChecker.isValidUSN(usn));

            CSVDatabase csv_db = new CSVDatabase(csv_path);
            String[] member_data = csv_db.getLine(usn);

            if (member_data.length != 0) {
                System.out.println(" Member already exists in the database.");
                continue;
            }

            usn_is_valid = true;
        } while (!usn_is_valid);
    }

    public void promptFirstName() {
        do {
            System.out.print("  Enter First name: ");
            Scanner sc = new Scanner(System.in);
            first_name = sc.nextLine();

            // Sanitation.
            first_name = first_name.replace(",", " ");
            first_name = first_name.trim();
        } while (!SchemaChecker.isValidStr(first_name));
    }

    public void promptMiddleName() {
        do {
            System.out.print("  Enter Middle name: ");
            Scanner sc = new Scanner(System.in);
            middle_name = sc.nextLine();

            // Sanitation.
            middle_name = middle_name.replace(",", " ");
            middle_name = middle_name.trim();
        } while (!SchemaChecker.isValidStr(middle_name));
    }

    public void promptLastName() {
        do {
            System.out.print("  Enter Last name: ");
            Scanner sc = new Scanner(System.in);
            last_name = sc.nextLine();

            // Sanitation.
            last_name = last_name.replace(",", " ");
            last_name = last_name.trim();
        } while (!SchemaChecker.isValidStr(last_name));
    }

    public void promptBirthday() {
        do {
            System.out.print("  Enter birthday: ");
            Scanner sc = new Scanner(System.in);
            birthday = sc.nextLine();

            // Sanitation.
            birthday = birthday.replace(",", " ");
            birthday = birthday.trim();
        } while (!SchemaChecker.isValidStr(birthday));
    }

    public void promptContactNumber() {
        do {
            System.out.print("  Enter Contact number: ");
            Scanner sc = new Scanner(System.in);
            contact_num = sc.nextLine();

            // Sanitation.
            contact_num = contact_num.replace(",", " ");
            contact_num = contact_num.trim();
        } while (!SchemaChecker.isValidStr(contact_num));
    }

    public void promptAddress() {
        do {
            System.out.print("  Enter Address: ");
            Scanner sc = new Scanner(System.in);
            address = sc.nextLine();

            // Sanitation.
            address = address.replace(",", " ");
            address = address.trim();
        } while (!SchemaChecker.isValidStr(address));
    }

    public void populate(String[] member_data) {
        usn = member_data[0];
        first_name = member_data[1];
        middle_name = member_data[2];
        last_name = member_data[3];
        birthday = member_data[4];
        contact_num = member_data[5];
        address = member_data[6];
        borrowed_books = member_data[7];
    }
}
