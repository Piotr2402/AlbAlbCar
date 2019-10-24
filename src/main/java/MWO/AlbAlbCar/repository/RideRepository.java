package MWO.AlbAlbCar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import MWO.AlbAlbCar.model.Ride;
import MWO.AlbAlbCar.model.User;

public interface RideRepository extends JpaRepository<Ride, Integer > {
	
	@Query(value = "select * from rides where ride_id = (select a.ride_id from rides_cities a join rides_cities b on "
			+ "a.ride_id=b.ride_id where a.city_id = :cityA and b.city_id = :cityB and a.delay < b.delay) and "
			+ "ride_date between :date and date_add( :date , interval 48 hour);", nativeQuery = true)
	public List<Ride> getRidesFromAToB(@Param("cityA") int cityA, @Param("cityB") int cityB, @Param("date") String date);
	
	public List<Ride> getByDriver(User user);
}
