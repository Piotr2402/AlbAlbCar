package MWO.AlbAlbCar.util;

import MWO.AlbAlbCar.model.*;

import java.util.*;

public class JSONUtil {

    public static String getUserRoad(Ride ride, City from, City to) {
        boolean betweenFromAndTo = false;
        StringBuilder road = new StringBuilder();
        List<RideCity> cities = ride.getCities();
        cities.sort(Comparator.comparing(RideCity::getDelay));
        for (RideCity r : cities) {
            if (r.getCity().equals(from)) {
                betweenFromAndTo = true;
            }
            if (betweenFromAndTo) {
                road.append(r.getCity().getCityName()).append(" - ");
            }
            if (r.getCity().equals(to)) {
                betweenFromAndTo = false;
            }
        }

        return road.substring(0, road.length() - 3);
    }

    public static String getCitiesWithTimeAndSeats(Ride ride) {
        StringBuilder road = new StringBuilder();

        for(int i = 0; i < ride.getCities().size(); i++) {
            RideCity c = ride.getCities().get(i);
            if( i < ride.getCities().size() - 1 ) {
                road.append(c.getCity().getCityName()).append("(").append(DateUtil.getProperDepartureDate(ride.getRideDate(),
                        c.getCity(), ride.getCities())).append(" Zajętych miejsc: ").append(c.getPeopleInCar()).append(") ");
            } else {
                road.append(c.getCity().getCityName());
            }
        }

        return road.toString();
    }

    public static List<Map<String, Object>> getRideUsers(Ride ride) {
        List<RidesUsers> users = ride.getUsers();
        List<Map<String, Object>> json_users = new ArrayList<>();

        for (RidesUsers user : users) {
            Map<String, Object> json_user = new HashMap<String, Object>();
            json_user.put("login", user.getUser().getLogin());
            json_user.put("from", user.getFromCity().getCityName());
            json_user.put("to", user.getToCity().getCityName());
            json_user.put("phone", user.getUser().getPhoneNumber());
            json_user.put("price", RideUtil.computePrice(ride, user.getFromCity().getId(), user.getToCity().getId()));
            json_users.add(json_user);
        }

        return json_users;
    }
}
