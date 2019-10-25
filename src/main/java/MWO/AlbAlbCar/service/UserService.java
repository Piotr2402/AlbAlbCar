package MWO.AlbAlbCar.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import MWO.AlbAlbCar.model.Role;
import MWO.AlbAlbCar.model.User;
import MWO.AlbAlbCar.repository.UserRepository;

@Service()
public class UserService {

	@Autowired
	UserRepository userRepository;

	public ArrayList<String> signIn(String login, String password) {
		ArrayList<String> result = new ArrayList<String>();
		User user = userRepository.getUserByLogin(login);

		if (user == null) {
			result.add("no such a user");
			return result;
		}

		if (!login.equals(userRepository.checkPassword(login, password))) {
			result.add("wrong password");
			return result;
		}

		Set<Role> roles = user.getRoles();
		for (Role role : roles)
			result.add(role.getUserRole());
		return result;
	}

	public Map<String, String> signUp(String login, String password, String password2, String phoneNumber) {

		Map<String, String> json = new HashMap<String, String>();

		if (!password.equals(password2)) {
			json.put("result", "fail");
			json.put("reason", "Passwords should be same");
			return json;
		}

		User user = new User(login, password, phoneNumber);
		Set<Role> set = new HashSet<Role>();
		set.add(new Role(2, "user"));
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
