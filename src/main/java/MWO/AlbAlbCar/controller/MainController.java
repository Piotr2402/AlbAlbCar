package MWO.AlbAlbCar.controller;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import MWO.AlbAlbCar.model.City;
import MWO.AlbAlbCar.model.Ride;
import MWO.AlbAlbCar.model.Role;
import MWO.AlbAlbCar.model.User;
import MWO.AlbAlbCar.repository.CityRepository;
import MWO.AlbAlbCar.repository.RideRepository;
import MWO.AlbAlbCar.repository.UserRepository;

@Controller
public class MainController {
	
	@Autowired
	UserRepository userRepository; 
	
	@Autowired
	CityRepository cityRepository; 
	
	@Autowired
	RideRepository rideRepository; 
	
	@GetMapping(value = "/") 
	public String index() {
		return "index";
	} 
	
	@GetMapping(value = "/drive") 
	public String drive(Model model) {
		List<City> cities = cityRepository.findAll();
		model.addAttribute("cities", cities);
		model.addAttribute("selected1", "1");
		model.addAttribute("selected2", "1");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").withLocale(Locale.getDefault())
	            .withZone(ZoneId.systemDefault());;
		Instant now = Instant.now();
        String formatted = formatter.format(now);
		model.addAttribute("selectedDate", formatted);
		return "drive";
	} 
	
	@GetMapping(value = "/new-ride") 
	public String newRide(Model model) {
		List<City> cities = cityRepository.findAll();
		model.addAttribute("cities", cities);
		return "new-ride";
	} 
	
	@GetMapping(value = "/your-rides") 
	public String yourRides() {
		return "your-rides";
	} 
	
	@GetMapping(value = "/admin-rides") 
	public String adminRides() {
		return "admin-rides";
	} 
	
	@GetMapping(value = "/admin-users") 
	public String adminUsers() {
		return "admin-users";
	} 
	
	@PostMapping(value = "/signup")
	public String addUser(@RequestParam String login,@RequestParam String password,
			@RequestParam String password2, @RequestParam String phone) {
		
		/*User user = new User(login,password, phone);
		Set<Role> set = new HashSet<Role>();
		set.add(new Role(2,"user"));
		user.setRoles(set);
		userRepository.save(user);*/
		
		System.out.println(login+" "+password+" "+password2+" "+phone);
		return "redirect:/";
	}
	
	@PostMapping(value = "/search-trip")
	public String searchTrip(@RequestParam int assembly_place,@RequestParam int destination_place,
			@RequestParam String departure_datetime, Model model) {	
		List<Ride> rides = rideRepository.getRidesFromAToB(assembly_place, destination_place, departure_datetime);
		model.addAttribute("rides", rides);
		List<City> cities = cityRepository.findAll();
		model.addAttribute("cities", cities);
		model.addAttribute("freeSeats", "3");
		model.addAttribute("price", "24 zl");
		model.addAttribute("selected1", assembly_place);
		model.addAttribute("selected2", destination_place);
		model.addAttribute("selectedDate", departure_datetime);
		return "drive";
	}
	
	@PostMapping(value = "/reserve-trip")
	public String reserveTrip(@RequestParam int assembly_place,@RequestParam int destination_place) {
		System.out.println(assembly_place+" "+destination_place);
		return "your-rides";
	}
	
}
