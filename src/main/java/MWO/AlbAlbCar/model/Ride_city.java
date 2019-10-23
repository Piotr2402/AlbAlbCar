package MWO.AlbAlbCar.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rides_cities")
public class Ride_city {

	@Id
	private int id;
	
	@ManyToOne(targetEntity = City.class)
	@JoinColumn(name = "cityName", referencedColumnName = "cityName")
	private City city;
	
	@ManyToOne(targetEntity = Ride.class)
	@JoinColumn(name = "rideID", referencedColumnName = "rideID" )
    private Ride ride;
	
	private float price;
    private int stopNumer;
    private Date leavingTime;
    private int numberOfPlaces; 
    
    
    
    public Ride_city() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public Ride getRide() {
		return ride;
	}
	public void setRide(Ride ride) {
		this.ride = ride;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getStopNumer() {
		return stopNumer;
	}
	public void setStopNumer(int stopNumer) {
		this.stopNumer = stopNumer;
	}
	public Date getLeavingTime() {
		return leavingTime;
	}
	public void setLeavingTime(Date leavingTime) {
		this.leavingTime = leavingTime;
	}
	public int getNumberOfPlaces() {
		return numberOfPlaces;
	}
	public void setNumberOfPlaces(int numberOfPlaces) {
		this.numberOfPlaces = numberOfPlaces;
	}

}
