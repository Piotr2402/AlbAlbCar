package MWO.AlbAlbCar.repository;

import MWO.AlbAlbCar.model.Ride;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import MWO.AlbAlbCar.model.RideCity;

public interface RideCityRepository extends JpaRepository<RideCity, Integer > {
    
	void deleteByRide(Ride ride);
    
	@Query(value = "select * from rides_cities where ride_id = :rideId and city_id = :cityId", nativeQuery = true)
	RideCity findyByRideIdAndCityId(@Param("rideId") int rideId, @Param("cityId") int cityId);
    
	@Query(value = "select * from rides_cities where ride_id = :rideId and"
    		+ " delay <= :destinationDelay and delay >= :assemblyDelay", nativeQuery = true)
    List<RideCity> getStopsBetweenDelays(@Param("rideId") int rideId, @Param("assemblyDelay") int assemblyDelay, @Param("destinationDelay") int destinationDelay);
}


