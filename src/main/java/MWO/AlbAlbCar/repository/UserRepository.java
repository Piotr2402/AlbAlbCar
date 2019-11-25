package MWO.AlbAlbCar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import MWO.AlbAlbCar.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	User getUserByLogin(String login);
	
	@Query(value = "Select login from users where login = :searchedLogin and password = :searchedPass" , nativeQuery = true)
	String checkPassword(@Param("searchedLogin") String searchedLogin, @Param("searchedPass") String searchedPass);

	@Query(value = "select role_id from users_roles ur join users u on ur.user_id = u.id where u.login = :login" , nativeQuery = true)
	int getUserRole(@Param("login") String login);

	List<User> findAllByLoginContaining(String login);

	@Modifying(clearAutomatically = true)
	@Query(value = "update users set enabled = false where login = :login", nativeQuery = true)
	int blockUserByLogin(@Param("login") String login);

	@Modifying(clearAutomatically = true)
	@Query(value = "update users set enabled = true where login = :login", nativeQuery = true)
	int unblockUserByLogin(@Param("login") String login);
}
