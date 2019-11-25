package MWO.AlbAlbCar.controller;

import MWO.AlbAlbCar.model.Ride;
import MWO.AlbAlbCar.model.User;
import MWO.AlbAlbCar.service.RideService;
import MWO.AlbAlbCar.service.UserService;
import MWO.AlbAlbCar.util.JSONUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
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

    private Map<String, Object> checkIfAdmin(ObjectNode data) {
        Map<String, Object> result = new HashMap<>();
        JsonNode requestLogin = data.get("request_login");

        if (requestLogin == null) {
            result.put("result", false);
            result.put("message", "Param request_login is obligatory");

            return result;
        }

        if (userService.getUserRole(requestLogin.asText()) != 1) {
            result.put("result", false);
            result.put("message", "request_login is not an admin");

            return result;
        }

        return null;
    }

    @PostMapping("/admin/all-trips")
    public Map<String, Object> getCreatedTripsByUser(@RequestBody ObjectNode data) {
        List<Ride> rides;
        Map<String, Object> result = checkIfAdmin(data);
        if (result != null) {
            return result;
        }
        result = new HashMap<>();

        JsonNode login = data.get("searched_login");
        if (login != null) {
            rides = rideService.getAllRidesByUser(userService.getUserByLogin(login.asText()));
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

        result.put("result", true);
        result.put("rides", resultRides);

        return result;
    }

    @PostMapping("/admin/delete-trip")
    public Map<String, Object> deleteRide(@RequestBody ObjectNode data) {
        Map<String, Object> result = checkIfAdmin(data);
        if (result != null) {
            return result;
        }
        result = new HashMap<>();

        JsonNode rideIdNode = data.findValue("rideId");

        if (rideIdNode.isNull()) {
            result.put("success", false);
            result.put("message", "Param rideId is obligatory");

            return result;
        }
        int rideId = rideIdNode.asInt();
        Ride ride = rideService.getRideById(rideId);

        if(ride == null) {
            result.put("success", false);
            result.put("message", "Ride not found");

            return result;
        }

        rideService.removeRide(rideId);
        result.put("success", true);

        return result;
    }

    @PostMapping("/admin/users")
    public Map<String, Object> getUsers(@RequestBody ObjectNode data) {
        List<User> users;
        Map<String, Object> result = checkIfAdmin(data);
        if (result != null) {
            return result;
        }
        result = new HashMap<>();

        JsonNode login = data.get("searched_login");
        if (login != null) {
            users = userService.getUsersByLoginContaining(login.asText());
        } else {
            users = userService.getAllUsers();
        }

        List<Map<String, Object>> resultUsers = new ArrayList<>();
        for (User user : users) {
            Map<String, Object> jsonUser = new HashMap<>();
            jsonUser.put("userID", user.getUserId());
            jsonUser.put("login", user.getLogin());
            jsonUser.put("phone_number", user.getPhoneNumber());
            jsonUser.put("enabled", user.isEnabled());
            resultUsers.add(jsonUser);
        }

        result.put("users", resultUsers);

        return result;
    }

    @PostMapping("/admin/block-user")
    public Map<String, Object> blockUser(@RequestBody ObjectNode data) {
        Map<String, Object> result = checkIfAdmin(data);
        if (result != null) {
            return result;
        }
        result = new HashMap<>();

        JsonNode login = data.get("block_login");
        if (login == null) {
            result.put("success", false);
            result.put("message", "Param block_login is obligatory");

            return result;
        }

        int queryResult = userService.blockUser(login.asText());

        if (queryResult != 1) {
            result.put("success", false);
            result.put("message", "User with block_login does not exist");

            return result;
        }

        result.put("success", true);

        return result;
    }

    @PostMapping("/admin/unblock-user")
    public Map<String, Object> unblockUser(@RequestBody ObjectNode data) {
        Map<String, Object> result = checkIfAdmin(data);
        if (result != null) {
            return result;
        }
        result = new HashMap<>();

        JsonNode login = data.get("unblock_login");
        if (login == null) {
            result.put("success", false);
            result.put("message", "Param unblock_login is obligatory");

            return result;
        }

        int queryResult = userService.unblockUser(login.asText());

        if (queryResult != 1) {
            result.put("success", false);
            result.put("message", "User with unblock_login does not exist");

            return result;
        }

        result.put("success", true);

        return result;
    }
}
