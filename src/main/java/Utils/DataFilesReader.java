package Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by yzosin on 03-Oct-17.
 */
public class DataFilesReader {
    static String STREETS = "src/main/resources/Streets.dat";
    static String FIRST_NAMES = "src/main/resources/First_Name.dat";
    static String LAST_NAMES = "src/main/resources/Last_Name.dat";

    public static String getStreets() {

        String line;
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader breader = new BufferedReader(new FileReader(STREETS));
            while ((line = breader.readLine())!= null) {
                lines = breader.lines().collect(Collectors.toList());
                //List<String> lines = Files.readAllLines(Paths.get("src/main/resources/Streets.dat"), Charset.defaultCharset());
                Random random = new Random();
                String randomStreet = lines.get(random.nextInt(lines.size()- 1));
                return randomStreet;
            }
        }
          catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getFirstName() {

        String line;
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader breader = new BufferedReader(new FileReader(FIRST_NAMES));
            while ((line = breader.readLine())!= null) {
                lines = breader.lines().collect(Collectors.toList());
                Random random = new Random();
                String randomFirstName = lines.get(random.nextInt(lines.size()- 1));
                return randomFirstName;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getLastName() {

        String line;
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader breader = new BufferedReader(new FileReader(LAST_NAMES));
            while ((line = breader.readLine())!= null) {
                lines = breader.lines().collect(Collectors.toList());
                Random random = new Random();
                String randomLastName = lines.get(random.nextInt(lines.size()- 1));
                return randomLastName;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
