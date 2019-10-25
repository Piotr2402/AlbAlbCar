package MWO.AlbAlbCar.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mysql.cj.jdbc.exceptions.SQLError;

import MWO.AlbAlbCar.model.City;
import MWO.AlbAlbCar.model.Ride;
import MWO.AlbAlbCar.model.RidesUsers;
import MWO.AlbAlbCar.model.Role;
import MWO.AlbAlbCar.model.User;
import MWO.AlbAlbCar.repository.CityRepository;
import MWO.AlbAlbCar.repository.RideRepository;
import MWO.AlbAlbCar.repository.UserRepository;

@RestController
public class RequestController {
	
	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	RideRepository rideRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping(value = "/cities")
	@ResponseBody
	public List<City> getAllCities() {
		List<City> cities = cityRepository.findAll();
		return cities;
	}
	
	@PostMapping(value = "/search-trip")
	public List<Map<String, Object>> searchTrip(@RequestBody ObjectNode tripData) {
		
		int assembly_place = tripData.findValue("assembly_place").asInt();
		int destination_place = tripData.findValue("destination_place").asInt();
		String departure_datetime = tripData.findValue("departure_datetime").asText();
		
		List<Ride> rides = rideRepository.getRidesFromAToB(assembly_place, destination_place, departure_datetime);
		List<Map<String, Object>> json_rides = new ArrayList<Map<String, Object>>();
		for(int i = 0; i < rides.size(); i++) {
			Map<String, Object> json = new HashMap<String, Object>();
			json.put("date", rides.get(i).getRideDate());
			json.put("driver", rides.get(i).getDriver().getLogin());
			json.put("road", rides.get(i).getCitiesString());
			json.put("seats", rides.get(i).getSeats());
			json.put("price", rides.get(i).getCities().get(0).getPrice());
			json_rides.add(json);
		}
		return json_rides;
	}
	
	@PostMapping(value = "/created-trips")
	public List<Map<String, Object>> getCreatedTripsByMe(@RequestBody ObjectNode loginData) {
		
		String login = loginData.get("login").asText();
		
		List<Ride> rides = rideRepository.getByDriver(userRepository.getUserByLogin(login));
		System.out.println(rides.size());
		List<Map<String, Object>> json_rides = new ArrayList<Map<String, Object>>();
		for(int i = 0; i < rides.size(); i++) {
			Map<String, Object> json = new HashMap<String, Object>();
			json.put("id", rides.get(i).getRideId());
			json.put("road", rides.get(i).getCitiesWithTimeString());
			json.put("seats", rides.get(i).getSeats());
			
			List<RidesUsers> users = rides.get(i).getUsers();
			List<Map<String, Object>> json_users = new ArrayList<Map<String, Object>>();
			for(int j = 0; j < users.size(); j++) {
				Map<String, Object> json_user = new HashMap<String, Object>();
				json_user.put("login",users.get(j).getUser().getLogin());
				json_user.put("from",users.get(j).getFromCity().getCityName());
				json_user.put("to",users.get(j).getToCity().getCityName());
				json_user.put("phone",users.get(j).getUser().getPhoneNumber());
				json_user.put("price",20);
				json_users.add(json_user);
			}
			json.put("clients", json_users);
			json_rides.add(json);
		}
		
		
		return json_rides;
	}
	
	@PostMapping(value = "/signup")
	public Map<String, String> signup(@RequestBody ObjectNode userData) {
		
		String login = userData.findValue("login").asText();
		String phoneNumber = userData.findValue("phoneNumber").asText();
		String password = userData.findValue("password").asText();
		String password2 = userData.findValue("password2").asText();
		Map<String, String> json = new HashMap<String, String>();
		
		if(!password.equals(password2)) {
			json.put("result", "fail");
			json.put("reason", "Passwords should be same");
			return json;
		}
		
		User user = new User(login,password, phoneNumber);
		Set<Role> set = new HashSet<Role>();
		set.add(new Role(2,"user"));
		user.setRoles(set);
		try {
			userRepository.save(user);
		} catch (DataIntegrityViolationException ex) {
			json.put("result", "fail");
			json.put("reason", "login is already in use");
			return json;
		}

		json.put("result", "success");
		return json;
		
	}
}
