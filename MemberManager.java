import java.util.Scanner;

public class MemberManager {
    private static final String csv_path = "members.csv";

    private CSVDatabase csv_db;

    public void registerMember() {
        // Print the member registration banner.
        System.out.println("  _____________________");
        System.out.println(" /                     \\");
        System.out.println(" | MEMBER REGISTRATION |");
        System.out.println(" \\                     /");
        System.out.println("  ---------------------");
        
        Scanner sc = new Scanner(System.in);

        // Confirm decision before entering new members.
        String continue_answer = "";
        do {
            System.out.print(" Please fill in the details of the new member. Continue? [Y/n] ");
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

            System.out.println();

            MemberEntry me = new MemberEntry();

            me.promptUSN();
            String usn = me.getUSN();

            me.promptFirstName();
            String first_name = me.getFirstName();

            me.promptMiddleName();
            String middle_name = me.getMiddleName();

            me.promptLastName();
            String last_name = me.getLastName();

            me.promptBirthday();
            String birthday = me.getBirthday();

            me.promptContactNumber();
            String contact_num = me.getContactNumber();

            me.promptAddress();
            String address = me.getAddress();

            String borrowed_books = "0";

            csv_db = new CSVDatabase(csv_path);
            csv_db.insert(usn + "," +
                          first_name + "," + middle_name + "," + last_name + "," +
                          birthday + "," + contact_num + "," + address + "," +
                          borrowed_books);
            
            String addanother_answer = "";
            do {
                System.out.print(" Member registered. Add another member? [y/N] ");
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

    public void unregisterMember() {
        // Print the book registration banner.
        System.out.println();
        System.out.println(" /!! !!!!!!!!!!!!!! !!\\");
        System.out.println(" !!!     MEMBER     !!!");
        System.out.println(" !!! UNREGISTRATION !!!");
        System.out.println(" \\!! !!!!!!!!!!!!!! !!/");
        System.out.println();

        Scanner sc = new Scanner(System.in);

        // Confirm decision before unregistering members.
        String continue_answer = "";
        do {
            System.out.print(" Please fill in the details for the member to be unregistered. Continue? [Y/n] ");
            continue_answer = sc.nextLine();

            // Sanitize before processing.
            continue_answer = continue_answer.trim();
            continue_answer = continue_answer.toLowerCase();
        } while (!continue_answer.equals("y") && 
                 !continue_answer.equals("n") &&
                 !continue_answer.isEmpty());

        if (continue_answer.equals("n"))
            return; // Cancel returning if answer is no.
        
        // Do unregistration only once, by default.
        boolean keep_running = false;
        do {
            // Set to false again, in case this was changed in
            // the previous iteration.
            keep_running = false;

            MemberUnregister mu = new MemberUnregister();
            mu.showPrompt();
            mu.save();

            System.out.println();
            
            String addanother_answer = "";
            do {
                System.out.print(" Member unregistered. Unregister another member? [y/N] ");
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
