package MWO.AlbAlbCar.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import MWO.AlbAlbCar.util.DateUtil;
import MWO.AlbAlbCar.util.JSONUtil;
import MWO.AlbAlbCar.util.RideUtil;
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
			oneRide.put("date", DateUtil.getProperDepartureDate(r.getRide().getRideDate(),r.getFromCity(),r.getRide().getCities()));
			oneRide.put("road", JSONUtil.getUserRoad(r.getRide(),r.getFromCity(),r.getToCity()));
			oneRide.put("driverLogin", r.getRide().getDriver().getLogin());
			oneRide.put("driverPhone", r.getRide().getDriver().getPhoneNumber());
			oneRide.put("canDelete", DateUtil.canDelete(r.getRide().getRideDate()));
			oneRide.put("price", RideUtil.computePrice(r.getRide(), r.getFromCity().getId(), r.getToCity().getId()));
			dataToSend.add(oneRide);
		}
		return dataToSend;
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
	
	public boolean resignFromTrip(User user, Ride ride) {
		List<RidesUsers> ridesUser = ridesUsersRepository.getRideUser(user.getUserId(), ride.getRideId());
		RidesUsers rideUser = null;
		if(ridesUser.size() == 0) {
			return false;
		} else {
			rideUser = ridesUser.get(0);
		}
		List<RideCity> stopsList = rideCityService.stopsBetweenAAndB(ride.getRideId(), rideUser.getFromCity().getId(), rideUser.getToCity().getId());
		stopsList.forEach(value -> {
			value.decrementPeopleInCar();
			rideCityService.save(value);
		});
		ridesUsersRepository.delete(rideUser);
		return true;
	}
	
	public boolean hasUserBookedYet(User user, Ride ride) {
		List<RidesUsers> ridesUser = ridesUsersRepository.getRideUser(user.getUserId(), ride.getRideId());
		if(ridesUser.size() == 0) {
			return false;
		} else {
			return true;
		}
	}
	
}
