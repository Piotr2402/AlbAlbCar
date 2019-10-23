package MWO.AlbAlbCar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import MWO.AlbAlbCar.model.Ride;
import MWO.AlbAlbCar.model.Ride_city;
import MWO.AlbAlbCar.model.Role;

public interface Ride_cityReposiotory extends JpaRepository<Ride_city, Integer > {
	
	// TO DO List<Ride> findConnections(String startCity, String finishCity);
}


