package MWO.AlbAlbCar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import MWO.AlbAlbCar.model.User;

public interface UserRepository extends JpaRepository<User, Integer > {
	
	public User getUserByLogin(String login);
}
