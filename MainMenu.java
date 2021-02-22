import java.util.Scanner;

public class MainMenu {
    private BooksManager books_manager;
    private MemberManager member_manager;
    private boolean keep_running;

    public MainMenu() {
        // Start the books management service.
        books_manager = new BooksManager();

        // Start the member management service.
        member_manager = new MemberManager();

        // Tell the instance that we should
        // loop our program.
        keep_running = true;
    }

    public void showPrompt() {
        while (keep_running) {
            Scanner sc = new Scanner(System.in);

            System.out.println();
            System.out.print("Command (? for help): ");

            String cmd = sc.nextLine();
            commandProcessor(cmd);
        }
    }

    private void commandProcessor(String cmd) {
        cmd = cmd.toLowerCase();
        switch(cmd) {
            case "a":
                books_manager.registerBook();
                break;
            case "d":
                books_manager.deleteBook();
                break;
            case "r":
                member_manager.registerMember();
                break;
            case "u":
                member_manager.unregisterMember();
                break;
            case "v":
                books_manager.viewAll();
                break;
            case "b":
                books_manager.loanBook();
                break;
            case "e":
                books_manager.returnBook();
                break;
            case "h":
            case "?":
                showHelp();
                break;
            case "q":
                quitProgram();
                break;
        }
    }

    private void showHelp() {
        System.out.println("  a    [A]dd books.");
        System.out.println("  d    [D]elete books.");
        System.out.println("  r    [R]egister members.");
        System.out.println("  u    [U]nregister members.");
        System.out.println("  v    List a[v]ailable books.");
        System.out.println("  b    Loan or [b]orrow a book.");
        System.out.println("  e    R[e]turn a book.");
        System.out.println("  h    Show [h]elp message.");
        System.out.println("  q    [Q]uit program.");
    }

    private void quitProgram() {
        keep_running = false;

        System.out.println();
        System.out.println("Goodbye!");
    }
}
