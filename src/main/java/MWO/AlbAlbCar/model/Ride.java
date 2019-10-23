package MWO.AlbAlbCar.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "rides")
public class Ride {

	@Id
	private int rideID;
	
	@OneToMany(targetEntity= Ride_city.class, mappedBy = "ride")
	private Set<Ride_city> rides;

	public Ride() {
	}
	
	public Set<Ride_city> getRideID() {
		return rides;
	}

	public void setRideID(Set<Ride_city> ride) {
		this.rides = ride;
	}
}
