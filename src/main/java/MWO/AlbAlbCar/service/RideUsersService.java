package MWO.AlbAlbCar.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import MWO.AlbAlbCar.model.*;
import MWO.AlbAlbCar.repository.RidesUsersRepository;

@Service
public class RideUsersService {

	@Autowired
	RidesUsersRepository ridesUsersRepository;
	
	@Autowired
	CityService cityService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	RideService rideService;
	
	@Autowired
	RideCityService rideCityService;
	
	public List<Map<String, Object>> getUserRide(User user) {
		List<RidesUsers> rides = ridesUsersRepository.getByUser(user);
		List<Map<String, Object>> dataToSend = new ArrayList<Map<String, Object>>();
		for (RidesUsers r : rides) {
			Map<String, Object> oneRide = new HashMap<String, Object>();
			oneRide.put("id", r.getRide().getRideId());
			oneRide.put("date", getProperDepartureDate(r.getRide().getRideDate(),r.getFromCity(),r.getRide().getCities()));
			oneRide.put("road",getUserRoad(r.getRide(),r.getFromCity(),r.getToCity()));
			oneRide.put("driverLogin", r.getRide().getDriver().getLogin());
			oneRide.put("driverPhone", r.getRide().getDriver().getPhoneNumber());
			oneRide.put("canDelete",canDelete(r.getRide().getRideDate()));
			//To-Do Zwrócić prawidłowe.
			oneRide.put("price",20);
			dataToSend.add(oneRide);
		}
		return dataToSend;
	}
	
	public String getUserRoad(Ride ride, City from, City to) {
		boolean betweenFromAndTo = false;
		String road = "";
		List<RideCity> cities = ride.getCities();
		cities.sort(Comparator.comparing(RideCity::getDelay));
		for (RideCity r : cities) {
			if(r.getCity().equals(from)) {
				betweenFromAndTo = true;
			}
			if(betweenFromAndTo) {
				road += r.getCity().getCityName()+" - ";
			}
			if(r.getCity().equals(to)) {
				betweenFromAndTo = false;
			}
		}
		road = road.substring(0, road.length()-3);
		return road;
	}
	
	public String getProperDepartureDate(String date, City from, List<RideCity> cities) {
		int delay = 0;
		for (RideCity r : cities) {
			if(r.getCity().equals(from)) {
				delay = r.getDelay();
				break;
			}
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
		dateTime = dateTime.plusMinutes(delay);
		return formatter.format(dateTime);
	}
	
	public boolean canDelete(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
		dateTime = dateTime.minusHours(2);
		if(LocalDateTime.now().isBefore(dateTime)) {
			return true;
		}
		return false;
	}


	public boolean addRideUser(String login, int rideId, int assembly_place, int destination_place) {
		
		Ride ride = rideService.getRideById(rideId);
		User user = userService.getUserByLogin(login);
		
		if(rideCityService.isFreeSeatOnRideFromAToB(rideId, assembly_place, destination_place)) {
			RidesUsers rideUser = new RidesUsers();
			rideUser.setRide(ride);
			rideUser.setUser(user);
			rideUser.setFromCity(cityService.getCityById(assembly_place));
			rideUser.setToCity(cityService.getCityById(destination_place));
			ridesUsersRepository.save(rideUser);
			
			List<RideCity> stopsList = rideCityService.stopsBetweenAAndB(rideId, assembly_place, destination_place);
			stopsList.forEach(value -> {
				value.incrementPeopleInCar();
				rideCityService.save(value);
			});	
			return true;
		} else {
			return false;
		}
	}
	
}
