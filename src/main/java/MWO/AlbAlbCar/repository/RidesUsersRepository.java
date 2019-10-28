package MWO.AlbAlbCar.repository;

import java.util.List;

import MWO.AlbAlbCar.model.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

import MWO.AlbAlbCar.model.RidesUsers;
import MWO.AlbAlbCar.model.User;

public interface RidesUsersRepository extends JpaRepository<RidesUsers, Integer> {
	
	List<RidesUsers> getByUser(User user);

	void deleteByRide(Ride ride);
}
