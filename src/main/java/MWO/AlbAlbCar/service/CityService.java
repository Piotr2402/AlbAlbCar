package MWO.AlbAlbCar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import MWO.AlbAlbCar.model.City;
import MWO.AlbAlbCar.repository.CityRepository;

@Service
public class CityService {

	@Autowired
	CityRepository cityRepository;
	
	public City getCityById(int id) {
		return cityRepository.findById(id).orElse(null);
	}
}
