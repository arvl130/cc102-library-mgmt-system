import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;

public class CSVDatabase {
    private String csv_file;

    public CSVDatabase(String csv_path) {
        csv_file = csv_path;
    }

    public void insert(String insert_ln) {
        try {
            FileWriter fw = new FileWriter(csv_file, true);
            fw.write(insert_ln);
            fw.write("\n");
            fw.close();
        }
        catch (IOException e) {
            System.out.println("DB Error: " + e.getMessage());
        }
    }

    public void update(String id, String update_ln) {
        try {
            Path csv_path = Paths.get(csv_file);
            List<String> all_lines = Files.readAllLines(csv_path, StandardCharsets.UTF_8);

            for (int i = 0; i < all_lines.size(); i++) {
                String curr_line = all_lines.get(i);
                String[] curr_line_arr = curr_line.split(",");
                String curr_line_id = curr_line_arr[0];

                if (curr_line_id.equals(id)) {
                    all_lines.set(i, update_ln);
                    break;
                }
            }

            Files.write(csv_path, all_lines, StandardCharsets.UTF_8);
        }
        catch (IOException e) {
            System.out.println("DB Error: " + e.getMessage());
        }
    }

    public void delete(String id) {
        try {
            Path csv_path = Paths.get(csv_file);
            List<String> all_lines = Files.readAllLines(csv_path, StandardCharsets.UTF_8);

            for (int i = 0; i < all_lines.size(); i++) {
                String curr_line = all_lines.get(i);
                String[] curr_line_arr = curr_line.split(",");
                String curr_line_id = curr_line_arr[0];

                if (curr_line_id.equals(id)) {
                    all_lines.remove(i);
                    break;
                }
            }

            Files.write(csv_path, all_lines, StandardCharsets.UTF_8);
        }
        catch (IOException e) {
            System.out.println("DB Error: " + e.getMessage());
        }
    }

    public String[] getLine(String id) {
        String[] matched_line_arr = {};

        try {
            File f = new File(csv_file);
            f.createNewFile();

            Scanner sc = new Scanner(f);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                String[] line_arr = line.split(",");
                if (line_arr[0].equals(id))
                    matched_line_arr = line_arr;
            }

            sc.close();
        }
        catch (IOException e) {
            System.out.println("DB Error: " + e.getMessage());
        }

        return matched_line_arr;
    }

    public String[][] getAllLines() {
        ArrayList<String[]> all_lines_arrlist = new ArrayList<String[]>();

        try {
            File f = new File(csv_file);
            f.createNewFile();

            Scanner sc = new Scanner(f);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                String[] line_arr = line.split(",");
                all_lines_arrlist.add(line_arr);
            }

            sc.close();
        }
        catch (IOException e) {
            System.out.println("DB Error: " + e.getMessage());
        }

        String[][] all_lines_2darr = new String[all_lines_arrlist.size()][];
        for (int i = 0; i < all_lines_arrlist.size(); i++) {
            all_lines_2darr[i] = all_lines_arrlist.get(i);
        }

        return all_lines_2darr;
    }
}
