package a;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Time {
    public static String currentTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }

}
