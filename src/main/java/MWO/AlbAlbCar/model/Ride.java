package MWO.AlbAlbCar.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "rides")
public class Ride {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ride_id")
	private int rideId;
	
	@ManyToOne
    @JoinColumn(name="driver", nullable=false)
	private User driver;
	
	@Column(name = "seats")
	private int seats;
	
	@Column(name = "price")
	private int price;
	
	@Column(name = "ride_date")
	private String rideDate;
	
	@OneToMany(mappedBy = "ride")
	private List<RideCity> cities = new ArrayList<RideCity>();
	
	@OneToMany(mappedBy = "ride")
	private List<RidesUsers> users = new ArrayList<RidesUsers>();

	public Ride() {
	}

	public int getRideId() {
		return rideId;
	}

	public void setRideId(int rideId) {
		this.rideId = rideId;
	}

	public User getDriver() {
		return driver;
	}

	public void setDriver(User driver) {
		this.driver = driver;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getRideDate() {
		return rideDate;
	}

	public void setRideDate(String rideDate) {
		this.rideDate = rideDate;
	}

	public List<RideCity> getCities() {
		return cities;
	}

	public void setCities(List<RideCity> cities) {
		this.cities = cities;
	}

	public List<RidesUsers> getUsers() {
		return users;
	}

	public void setUsers(List<RidesUsers> users) {
		this.users = users;
	}

	public String getCitiesString() {
		String road = "";
		for (RideCity c : cities) {
            road += c.getCity().getCityName()+" - ";
        }
		road = road.substring(0, road.length()-3);
		return road;
	}	
}
