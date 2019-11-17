package MWO.AlbAlbCar.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import MWO.AlbAlbCar.repository.CityRepository;
import MWO.AlbAlbCar.repository.RideCityRepository;
import MWO.AlbAlbCar.repository.RidesUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import MWO.AlbAlbCar.model.Ride;
import MWO.AlbAlbCar.model.RidesUsers;
import MWO.AlbAlbCar.model.User;
import MWO.AlbAlbCar.repository.RideRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RideService {
	
	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	RideRepository rideRepository;

	@Autowired
	RideCityRepository rideCityRepository;

	@Autowired
	RidesUsersRepository ridesUsersRepository;

	@Autowired
	RideCityService rideCityService;
	
	@Autowired
	RideUsersService rideUsersService;

	public List<Map<String, Object>> getCreatedTripsByUser(User user) {
		List<Ride> rides = rideRepository.getByDriver(user);
		List<Map<String, Object>> json_rides = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < rides.size(); i++) {
			Map<String, Object> json = new HashMap<String, Object>();
			json.put("id", rides.get(i).getRideId());
			json.put("road", rideCityService.getCitiesWithTimeAndSeats(rides.get(i)));
			json.put("seats", rides.get(i).getSeats());

			List<RidesUsers> users = rides.get(i).getUsers();
			List<Map<String, Object>> json_users = new ArrayList<Map<String, Object>>();
			for (int j = 0; j < users.size(); j++) {
				Map<String, Object> json_user = new HashMap<String, Object>();
				json_user.put("login", users.get(j).getUser().getLogin());
				json_user.put("from", users.get(j).getFromCity().getCityName());
				json_user.put("to", users.get(j).getToCity().getCityName());
				json_user.put("phone", users.get(j).getUser().getPhoneNumber());
				json_user.put("price", rideCityService.computePrice(rides.get(i), users.get(j).getFromCity().getId(), users.get(j).getToCity().getId()));
				json_users.add(json_user);
			}
			json.put("clients", json_users);
			json_rides.add(json);
		}
		return json_rides;
	}

	public List<Map<String, Object>> searchTrip(int assembly_place, int destination_place, String departure_datetime) {
		List<Ride> rides = rideRepository.getRidesFromAToB(assembly_place, destination_place, departure_datetime);
		List<Map<String, Object>> json_rides = new ArrayList<Map<String, Object>>();

		for (int i = 0; i < rides.size(); i++) {
			
			int rideId = rides.get(i).getRideId();
			if (rideCityService.isFreeSeatOnRideFromAToB(rideId, assembly_place, destination_place)) {
				Map<String, Object> json = new HashMap<String, Object>();
				json.put("id", rideId);
				json.put("date", rideUsersService.getProperDepartureDate(rides.get(i).getRideDate(), cityRepository.getOne(assembly_place), rides.get(i).getCities()));
				json.put("driver", rides.get(i).getDriver().getLogin());
				json.put("road", rides.get(i).getCitiesString());
				json.put("seats", rideCityService.computeFreeSeats(rides.get(i), assembly_place, destination_place));
				json.put("price", rideCityService.computePrice(rides.get(i),assembly_place,destination_place));
				json_rides.add(json);
			}
		}
		
		return json_rides;
	}

	public Ride addRide(User driver, int seats, int price, String departure_datetime) {
		Ride ride = new Ride();
		ride.setDriver(driver);
		ride.setRideDate(departure_datetime);
		ride.setSeats(seats);
		ride.setPrice(price);
		return rideRepository.save(ride);
	}

	@Transactional
	public Map<String, Object> removeRide(int rideID, String login) {
		Map<String, Object> response = new HashMap<String, Object>();

		Optional<Ride> oRide = rideRepository.findById(rideID);

		if (oRide.isPresent()) {
			Ride ride = oRide.get();

			if (ride.getDriver().getLogin().equals(login)) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime rideDateTime = LocalDateTime.parse(ride.getRideDate(), formatter);
				rideDateTime = rideDateTime.minusHours(2);
				if (LocalDateTime.now().isBefore(rideDateTime)) {
					ridesUsersRepository.deleteByRide(ride);
					rideCityRepository.deleteByRide(ride);
					rideRepository.deleteById(rideID);
					response.put("result", "success");
				} else {
					response.put("result", "fail");
					response.put("message", "Too late to delete the ride");
				}
			} else {
				response.put("result", "fail");
				response.put("message", "Not a driver of the ride");
			}
		} else {
			response.put("result", "fail");
			response.put("message", "Ride not found");
		}

		return response;
	}

	public Ride getRideById(int id) {
		return rideRepository.findById(id).orElse(null);
	}

}