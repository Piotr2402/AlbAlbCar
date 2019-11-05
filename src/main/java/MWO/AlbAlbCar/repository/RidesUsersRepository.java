package MWO.AlbAlbCar.repository;

import java.util.List;

import MWO.AlbAlbCar.model.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import MWO.AlbAlbCar.model.Ride;
import MWO.AlbAlbCar.model.RidesUsers;
import MWO.AlbAlbCar.model.User;

public interface RidesUsersRepository extends JpaRepository<RidesUsers, Integer> {
	

	List<RidesUsers> getByUser(User user);

	void deleteByRide(Ride ride);
	
	
	
	@Query(value = "to do", nativeQuery = true)
	public int numberOfUsersFromAToB(@Param("rideId") int rideId, @Param("cityA") int cityA, @Param("cityB") int cityB);

}
