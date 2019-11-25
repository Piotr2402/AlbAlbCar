package MWO.AlbAlbCar.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import MWO.AlbAlbCar.model.Role;
import MWO.AlbAlbCar.model.User;
import MWO.AlbAlbCar.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public Map<String, String> signIn(String login, String password) {
		Map<String, String> result = new HashMap<String, String>();
		User user = userRepository.getUserByLogin(login);

		if (user == null) {
			result.put("result","no such a user");
			return result;
		}

		if (!login.equals(userRepository.checkPassword(login, password))) {
			result.put("result","wrong password");
			return result;
		}

		if (!user.isEnabled()) {
			result.put("result", "user is blocked");
			return result;
		}

		Set<Role> roles = user.getRoles();
		for (Role role : roles)
			result.put("result", role.getUserRole());
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

	public User getUserByLogin(String login) {
		return userRepository.getUserByLogin(login);
	}

	public int getUserRole(String login) {
		return userRepository.getUserRole(login);
	}

	public List<User> getUsersByLoginContaining(String login) {
		return userRepository.findAllByLoginContaining(login);
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public int blockUser(String login) {
		return userRepository.blockUserByLogin(login);
	}

	public int unblockUser(String login) {
		return userRepository.unblockUserByLogin(login);
	}
}
