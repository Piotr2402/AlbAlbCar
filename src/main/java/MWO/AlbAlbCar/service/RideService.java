package MWO.AlbAlbCar.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import MWO.AlbAlbCar.model.Ride;
import MWO.AlbAlbCar.model.RidesUsers;
import MWO.AlbAlbCar.model.User;
import MWO.AlbAlbCar.repository.RideRepository;

@Service
public class RideService {
	
	@Autowired
	RideRepository rideRepository;
	
	public List<Map<String, Object>> getCreatedTripsByUser(User user) {
		List<Ride> rides = rideRepository.getByDriver(user);
		List<Map<String, Object>> json_rides = new ArrayList<Map<String, Object>>();
		for(int i = 0; i < rides.size(); i++) {
			Map<String, Object> json = new HashMap<String, Object>();
			json.put("id", rides.get(i).getRideId());
			//To-Do Obliczać prawidłowo datę.
			json.put("road", rides.get(i).getCitiesWithTimeString());
			//To-Do Obliczać prawidłowo.
			json.put("seats", rides.get(i).getSeats());
			
			List<RidesUsers> users = rides.get(i).getUsers();
			List<Map<String, Object>> json_users = new ArrayList<Map<String, Object>>();
			for(int j = 0; j < users.size(); j++) {
				Map<String, Object> json_user = new HashMap<String, Object>();
				json_user.put("login",users.get(j).getUser().getLogin());
				json_user.put("from",users.get(j).getFromCity().getCityName());
				json_user.put("to",users.get(j).getToCity().getCityName());
				json_user.put("phone",users.get(j).getUser().getPhoneNumber());
				//To-Do Obliczać prawidłowo.
				json_user.put("price",20);
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
		for(int i = 0; i < rides.size(); i++) {
			Map<String, Object> json = new HashMap<String, Object>();
			//To-Do Obliczać prawidłowo.
			json.put("date", rides.get(i).getRideDate());
			json.put("driver", rides.get(i).getDriver().getLogin());
			json.put("road", rides.get(i).getCitiesString());
			//To-Do Obliczać prawidłowo.
			json.put("seats", rides.get(i).getSeats());
			//To-Do Obliczać prawidłowo.
			json.put("price", rides.get(i).getCities().get(0).getPrice());
			json_rides.add(json);
		}
		return json_rides;
	}
	
}
