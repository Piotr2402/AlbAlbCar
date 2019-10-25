package MWO.AlbAlbCar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import MWO.AlbAlbCar.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	public User getUserByLogin(String login);
	
	@Query(value = "Select login from users where login = :searchedLogin and password = :searchedPass" , nativeQuery = true)
	public String checkPassword(@Param("searchedLogin") String searchedLogin, @Param("searchedPass") String searchedPass);
}
