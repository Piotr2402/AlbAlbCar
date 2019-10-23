package MWO.AlbAlbCar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import MWO.AlbAlbCar.model.Role;
import MWO.AlbAlbCar.model.User;

public interface RoleRepository extends JpaRepository<Role, Integer > {}
