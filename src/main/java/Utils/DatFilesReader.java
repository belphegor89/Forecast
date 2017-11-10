package Utils;

import org.openqa.selenium.Cookie;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static Pages.BasePage.driver;

/**
 * Created by yzosin on 03-Oct-17.
 */
public class DatFilesReader {
    static String STREETS = "src/main/resources/Streets.dat";
    static String FIRST_NAMES = "src/main/resources/First_Name.dat";
    static String LAST_NAMES = "src/main/resources/Last_Name.dat";

    /**
     * Get random street from a .dat file
     */
    public static String getStreets() {

        String line;
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader breader = new BufferedReader(new FileReader(STREETS));
            while ((line = breader.readLine()) != null) {
                lines = breader.lines().collect(Collectors.toList());
                //List<String> lines = Files.readAllLines(Paths.get("src/main/resources/Streets.dat"), Charset.defaultCharset());
                Random random = new Random();
                String randomStreet = lines.get(random.nextInt(lines.size() - 1));
                breader.close();
                return randomStreet;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Get random first name from a .dat file
     */
    public static String getFirstName() {

        String line;
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader breader = new BufferedReader(new FileReader(FIRST_NAMES));
            while ((line = breader.readLine()) != null) {
                lines = breader.lines().collect(Collectors.toList());
                Random random = new Random();
                String randomFirstName = lines.get(random.nextInt(lines.size() - 1));
                breader.close();
                return randomFirstName;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Get random last name from a .dat file
     */
    public static String getLastName() {

        String line;
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader breader = new BufferedReader(new FileReader(LAST_NAMES));
            while ((line = breader.readLine()) != null) {
                lines = breader.lines().collect(Collectors.toList());
                Random random = new Random();
                String randomLastName = lines.get(random.nextInt(lines.size() - 1));
                breader.close();
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
