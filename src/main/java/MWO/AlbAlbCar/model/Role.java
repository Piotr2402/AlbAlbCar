package MWO.AlbAlbCar.model;

import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "roles")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private int RoleID;
	@Column(name = "user_role")
	private String userRole;
	
	@ManyToMany(mappedBy = "roles")
	private Set<User> users;

	public Role() {
	}
	public Role(int RoleID, String userRole) {
		this.RoleID = RoleID;
		this.userRole = userRole;
	}
	
	public int getRoleID() {
		return RoleID;
	}

	public void setRoleID(int roleID) {
		RoleID = roleID;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
}
