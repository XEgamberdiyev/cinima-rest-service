package uz.pdp.cinemarestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinemarestservice.model.Permission;

public interface PermissionRepo extends JpaRepository<Permission, Integer> {
}
