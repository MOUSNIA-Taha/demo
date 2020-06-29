package ma.tp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.tp.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	List<Role> findByRole(String role);
	List<Role> findAll();
}
