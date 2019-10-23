package MWO.AlbAlbCar.controller;

import java.util.HashSet;
import java.util.List;
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
import MWO.AlbAlbCar.model.Role;
import MWO.AlbAlbCar.model.User;
import MWO.AlbAlbCar.repository.CityRepository;
import MWO.AlbAlbCar.repository.UserRepository;

@Controller
public class MainController {
	
	@Autowired
	UserRepository userRepository; 
	
	@Autowired
	CityRepository cityRepository; 
	
	@GetMapping(value = "/") 
	public String index() {
		return "index";
	} 
	
	@GetMapping(value = "/drive") 
	public String drive(Model model) {
		List<City> cities = cityRepository.findAll();
		model.addAttribute("cities", cities);
		return "drive";
	} 
	
	@GetMapping(value = "/new-ride") 
	public String newRide() {
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
	public String searchTrip(@RequestParam String assembly_place,@RequestParam String destination_place,
			@RequestParam String departure_datetime, Model model) {	
		System.out.println(assembly_place+" "+destination_place+" "+departure_datetime);
		return "redirect:/drive";
	}
	
}
