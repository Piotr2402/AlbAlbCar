package MWO.AlbAlbCar.repository;

import MWO.AlbAlbCar.model.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

import MWO.AlbAlbCar.model.RideCity;

public interface RideCityRepository extends JpaRepository<RideCity, Integer > {
	
	// TO DO List<Ride> findConnections(String startCity, String finishCity);

    void deleteByRide(Ride ride);
}


