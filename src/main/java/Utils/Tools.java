package Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Tools {

    public static String getCurrentTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy_HH:mm");
        LocalDateTime dateTime = LocalDateTime.now();
        String formattedDateTime = dateTime.format(formatter);

        return formattedDateTime;
    }
}

