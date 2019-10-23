package MWO.AlbAlbCar.controller;

import org.springframework.web.bind.annotation.RestController;

import MWO.AlbAlbCar.model.Role;
import MWO.AlbAlbCar.model.User;
import MWO.AlbAlbCar.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
public class RequestController {

	@Autowired
	UserRepository userRepository; 
	
	@PostMapping(value = "/signup")
	@ResponseStatus(HttpStatus.OK)
	public void addUser(@RequestParam String login,@RequestParam String password,
			@RequestParam String password2, @RequestParam String phoneNumber) {
		
		User user = new User(login,password, phoneNumber);
		Set<Role> set = new HashSet<Role>();
		set.add(new Role(2,"user"));
		user.setRoles(set);
		userRepository.save(user);
	}
	
    @GetMapping("/all")
    public List<User> getAll() {
        return userRepository.findAll();
    }
	 
}
