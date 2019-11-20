package MWO.AlbAlbCar.util;

import MWO.AlbAlbCar.model.Ride;
import MWO.AlbAlbCar.model.RideCity;

import java.util.List;

public class RideUtil {
    public static double computePrice(Ride ride, int assembly_place, int destination_place) {
        List<RideCity> cities = ride.getCities();
        double priceFrom = 0;
        double priceTo = 0;
        for(RideCity rideCity: cities) {
            if(rideCity.getCity().getId() == assembly_place) {
                priceFrom = rideCity.getPrice();
            } else if(rideCity.getCity().getId() == destination_place) {
                priceTo = rideCity.getPrice();
            }
        }
        return priceTo-priceFrom;
    }
}
