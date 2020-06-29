package ma.tp.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import ma.tp.domaine.RoleVo;
import ma.tp.domaine.UserVo;

public interface IUserService extends UserDetailsService{
    void add(UserVo user);
    void update(UserVo user);
    void save(RoleVo role);
    void delete(Long id);
    List<UserVo> getAlluUsers();
    List<RoleVo> getAllRoles();
    RoleVo getRoleByName(String role);
    void cleanDataBase(); 
    List<UserVo> findUserHasRoleAdminAndClient();  
    UserVo findUserById(Long id); 
    
}