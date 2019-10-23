package MWO.AlbAlbCar.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class User {

	@Id
	private String login;
	private String password1;
	private String phoneNumber;
	
	@JsonIgnore
	@ManyToMany (targetEntity = Role.class)
	@JoinTable(name = "users_roles",
		joinColumns = @JoinColumn(name = "login"),
		inverseJoinColumns=@JoinColumn(name = "RoleID"))
	private Set<Role> roles = new HashSet<Role>();
	
	public User( ) {
	}
	
	public User(String login, String password1, String phoneNumber) {
		this.login = login;
		this.password1 = password1;
		this.phoneNumber = phoneNumber;
	}

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password1;
	}
	public void setPassword(String password1) {
		this.password1 = password1;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Set<Role> getRoles() {
	    return roles;
	}

	public void setRoles(Set<Role> roles) {
	    this.roles = roles;
	}
	
}
