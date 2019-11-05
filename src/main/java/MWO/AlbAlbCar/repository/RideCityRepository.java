package MWO.AlbAlbCar.repository;

import MWO.AlbAlbCar.model.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

import MWO.AlbAlbCar.model.RideCity;

public interface RideCityRepository extends JpaRepository<RideCity, Integer > {
    void deleteByRide(Ride ride);
}


