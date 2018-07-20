package utils.dataHandlers;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by yzosin on 03-Oct-17.
 */
public class DatFilesReader {
    static String STREETS = "src/main/resources/Streets.dat";
    static String FIRST_NAMES = "src/main/resources/First_Name.dat";
    static String LAST_NAMES = "src/main/resources/Last_Name.dat";

    /**
     * Get random street, first or last name from a .dat file
     * @param randomValue    what kind of randomly generated value should be
     */

    public static String getRandomValue(String randomValue) {
        String line;
        List<String> lines = new ArrayList<>();
        try {
            if (randomValue.equalsIgnoreCase("randomLastName")) {
                BufferedReader breader = new BufferedReader(new FileReader(LAST_NAMES));
                while ((line = breader.readLine()) != null) {
                    lines = breader.lines().collect(Collectors.toList());
                    Random random = new Random();
                    String randomLastName = lines.get(random.nextInt(lines.size() - 1));
                    breader.close();
                    return randomLastName;
                }
            } else if(randomValue.equalsIgnoreCase("randomFirstName")){
                BufferedReader breader = new BufferedReader(new FileReader(FIRST_NAMES));
                while ((line = breader.readLine()) != null) {
                lines = breader.lines().collect(Collectors.toList());
                Random random = new Random();
                String randomFirstName = lines.get(random.nextInt(lines.size() - 1));
                breader.close();
                return randomFirstName; }
            } else {
                BufferedReader breader = new BufferedReader(new FileReader(STREETS));
                while ((line = breader.readLine()) != null) {
                    lines = breader.lines().collect(Collectors.toList());
                    Random random = new Random();
                    String randomStreet = lines.get(random.nextInt(lines.size() - 1));
                    breader.close();
                    return randomStreet; }
            }
        } catch (IOException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
