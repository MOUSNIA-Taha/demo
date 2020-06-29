package ma.tp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ma.tp.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String userName);

	@Query(value = "select * from user where user.id in(select user_id from user_role where role_id in(select id from role where role='ADMIN' or role='CLIENT'));",nativeQuery = true)
	List<User> findUserHasRoleAdminAndClient();
}
