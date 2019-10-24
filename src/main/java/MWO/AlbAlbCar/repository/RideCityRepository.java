package MWO.AlbAlbCar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import MWO.AlbAlbCar.model.RideCity;

public interface RideCityRepository extends JpaRepository<RideCity, Integer > {
	
	// TO DO List<Ride> findConnections(String startCity, String finishCity);
}


