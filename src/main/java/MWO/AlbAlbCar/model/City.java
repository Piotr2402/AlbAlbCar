package MWO.AlbAlbCar.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cities")
public class City {

	
	@Id
	private String cityName;
	
	@OneToMany(targetEntity= Ride_city.class, mappedBy = "city")
	private Set<Ride_city> rides;
	
	City() {	
	}

	public Set<Ride_city> getCityName() {
		return rides;
	}
	public void setCityName(Set<Ride_city> rides) {
		this.rides = rides;
	}
	
	
}
