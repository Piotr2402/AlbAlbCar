package MWO.AlbAlbCar.util;

import MWO.AlbAlbCar.model.City;
import MWO.AlbAlbCar.model.RideCity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DateUtil {

    private static final int HOURS_BEFORE = 2;

    public static String getProperDepartureDate(String date, City from, List<RideCity> cities) {
        int delay = 0;
        for (RideCity r : cities) {
            if(r.getCity().equals(from)) {
                delay = r.getDelay();
                break;
            }
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        dateTime = dateTime.plusMinutes(delay);
        return formatter.format(dateTime);
    }

    public static boolean canDelete(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        dateTime = dateTime.minusHours(HOURS_BEFORE);
        if(LocalDateTime.now().isBefore(dateTime)) {
            return true;
        }
        return false;
    }
}
