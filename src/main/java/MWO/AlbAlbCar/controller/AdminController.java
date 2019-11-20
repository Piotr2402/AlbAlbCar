package MWO.AlbAlbCar.controller;

import MWO.AlbAlbCar.model.Ride;
import MWO.AlbAlbCar.service.RideService;
import MWO.AlbAlbCar.service.UserService;
import MWO.AlbAlbCar.util.JSONUtil;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class AdminController {

    @Autowired
    RideService rideService;

    @Autowired
    UserService userService;

    @PostMapping("/admin/all-trips")
    public List<Map<String, Object>> getCreatedTripsByUser(@RequestBody @Nullable ObjectNode loginData) {
        List<Ride> rides;

        if (loginData != null) {
            String login = loginData.get("login").asText();
            rides = rideService.getAllRidesByUser(userService.getUserByLogin(login));
        } else {
            rides = rideService.getAllRides();
        }

        List<Map<String, Object>> resultRides = new ArrayList<>();
        for (Ride ride : rides) {
            Map<String, Object> jsonRide = new HashMap<>();
            jsonRide.put("id", ride.getRideId());
            jsonRide.put("road", JSONUtil.getCitiesWithTimeAndSeats(ride));
            Map<String, Object> driver = new HashMap<>();
            driver.put("login", ride.getDriver().getLogin());
            driver.put("phone", ride.getDriver().getPhoneNumber());
            jsonRide.put("driver", driver);
            jsonRide.put("clients", JSONUtil.getRideUsers(ride));

            resultRides.add(jsonRide);
        }

        return resultRides;
    }
}
