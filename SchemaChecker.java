public class SchemaChecker {
    public static boolean isValidStr(String str) {
        if (str.isEmpty()) {
            System.out.println(" Empty. Please enter a value!");
            return false;
        }

        return true;
    }

    public static boolean isValidInt(String str) {
        if (str.isEmpty()) {
            System.out.println(" Empty. Please enter a number!");
            return false;
        }

        if (!str.matches("^\\d+$")) {
            System.out.println(" Not a valid number. Please enter a number!");
            return false;
        }

        return true;
    }

    public static boolean isValidUSN(String str) {
        if (str.isEmpty()) {
            System.out.println(" Empty. Please enter USN!");
            return false;
        }

        if (!str.matches("^\\d\\d-\\d\\d\\d\\d$")) {
            System.out.println(" Not a valid USN. Please use proper formatting! (format: ##-#### e.g. 20-1693)");
            return false;
        }

        return true;
    }
}
