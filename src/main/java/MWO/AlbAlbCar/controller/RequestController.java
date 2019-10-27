package MWO.AlbAlbCar.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import MWO.AlbAlbCar.model.City;
import MWO.AlbAlbCar.model.Ride;
import MWO.AlbAlbCar.model.User;
import MWO.AlbAlbCar.repository.CityRepository;
import MWO.AlbAlbCar.repository.UserRepository;
import MWO.AlbAlbCar.service.RideCityService;
import MWO.AlbAlbCar.service.RideService;
import MWO.AlbAlbCar.service.RideUsersService;
import MWO.AlbAlbCar.service.UserService;

@RestController
public class RequestController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	RideUsersService rideUsersService;
	
	@Autowired
	RideService rideService;
	
	@Autowired
	RideCityService rideCityService;
	
	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping(value = "/cities")
	public List<City> getAllCities() {
		List<City> cities = cityRepository.findAll();
		return cities;
	}
	
	@PostMapping(value = "/search-trip")
	public List<Map<String, Object>> searchTrip(@RequestBody ObjectNode tripData) {
		
		int assembly_place = tripData.findValue("assembly_place").asInt();
		int destination_place = tripData.findValue("destination_place").asInt();
		String departure_datetime = tripData.findValue("departure_datetime").asText();
		
		List<Map<String, Object>> json_rides = rideService.searchTrip(assembly_place, destination_place, departure_datetime);
		
		return json_rides;
	}
	
	@PostMapping(value = "/created-trips")
	public List<Map<String, Object>> getCreatedTripsByMe(@RequestBody ObjectNode loginData) {
		String login = loginData.get("login").asText();
		List<Map<String, Object>> rides = rideService.getCreatedTripsByUser(userRepository.getUserByLogin(login));
		return rides;
	}
	
	@PostMapping(value = "/sign-up")
	public Map<String, String> signUp(@RequestBody ObjectNode userData) {
		String login = userData.findValue("login").asText();
		String phoneNumber = userData.findValue("phoneNumber").asText();
		String password = userData.findValue("password").asText();
		String password2 = userData.findValue("password2").asText();
		
		return userService.signUp(login, password, password2, phoneNumber);
	}
	
	@PostMapping(value = "/sign-in")
	public Map<String, String> signIn(@RequestBody ObjectNode userData) {
		String login = userData.findValue("login").asText();
		String password = userData.findValue("password").asText();
		
		return userService.signIn(login,password);
	}
	
	@PostMapping(value = "/participated-trips")
	public List<Map<String, Object>> participatedTrips(@RequestBody ObjectNode userData) {
		String login = userData.findValue("login").asText();
		List<Map<String, Object>> json = rideUsersService.getUserRide(userRepository.getUserByLogin(login));
		
		return json;
	}
	
	@PostMapping(value = "/add-new-ride")
	public Map<String, String> addRide(@RequestBody ObjectNode rideData) {
		
		String login = rideData.findValue("login").asText();
		int assembly_place = rideData.findValue("assembly_place").asInt();
		int destination_place = rideData.findValue("destination_place").asInt();
		String departure_datetime = rideData.findValue("departure_datetime").asText();
		int price = rideData.findValue("price").asInt();
		int seats = rideData.findValue("seats").asInt();
		Iterator<JsonNode> stops = rideData.get("stops").elements();
		
		User driver = userService.getUserByLogin(login);
		Ride ride = rideService.addRide(driver,seats,price,departure_datetime);
		
		return rideCityService.addStops(ride,stops,price,assembly_place,destination_place);	
	}	
}
