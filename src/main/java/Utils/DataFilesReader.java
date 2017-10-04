package Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by yzosin on 03-Oct-17.
 */
public class DataFilesReader {
    static String STREETS = "src/main/resources/Streets.dat";
    static String FIRST_NAMES = "src/main/resources/First_Name.dat";
    static String LAST_NAMES = "src/main/resources/Last_Name.dat";

    String streetsDAta = STREETS;


    public String getStreets() {
        String streetsData = STREETS;
        String line = "";

        String randomStreet = "";
        List<String> lines = new ArrayList<String>();

        try {
            FileInputStream is = new FileInputStream(streetsData);
            BufferedReader breader = new BufferedReader(new InputStreamReader(is));
            while ((line = breader.readLine()) != null) {
                Random random = new Random();

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


}
