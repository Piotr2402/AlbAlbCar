package MWO.AlbAlbCar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import MWO.AlbAlbCar.model.RidesUsers;
import MWO.AlbAlbCar.model.User;

public interface RidesUsersRepository extends JpaRepository<RidesUsers, Integer> {
	
	public List<RidesUsers> getByUser(User user);
}
