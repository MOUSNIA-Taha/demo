package ma.tp.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Data;
import ma.tp.dao.*;
import ma.tp.domaine.*;
import ma.tp.model.*;

@Service("userService")
@Transactional
@Data
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmpRepository empRepository;

    @Autowired
    JavaMailSender MailSender;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        boolean enabled = true;
        boolean accountNotExpired = true;
        boolean credentialsNotExpired = true;
        boolean accoutnNotLocked = true;
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), enabled,
                accountNotExpired, credentialsNotExpired, accoutnNotLocked, getAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(List<Role> roles) {
        List<GrantedAuthority> springSecurityAuthorities = new ArrayList<>();
        for (Role r : roles) {
            springSecurityAuthorities.add(new SimpleGrantedAuthority(r.getRole()));
        }
        return springSecurityAuthorities;
    }

    @Override
    public void update(UserVo userVo) {
        User user = UserConverter.toBo(userVo);
        User old = userRepository.getOne(userVo.getId());
        SimpleMailMessage message = new SimpleMailMessage();
        if (!(old.getPassword().equals(user.getPassword()))) {
            message.setText("votre nouveau nom d'utilisateur est :" + user.getUsername() + ",le mot de passe est :"
                    + user.getPassword());
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        } else
            message.setText("votre nouveau nom d'utilisateur est :" + user.getUsername());
        List<Role> rolesPersist = new ArrayList<Role>();
        for (Role role : user.getRoles()) {
            Role userRole = roleRepository.findByRole(role.getRole()).get(0);
            rolesPersist.add(userRole);
        }
        user.setRoles(rolesPersist);
        userRepository.save(user);
        message.setTo("t.mousnia@gmail.com");
        message.setSubject("Notification");
        MailSender.send(message);
    }

    @Override
    public void add(UserVo userVo) {
        User user = UserConverter.toBo(userVo);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        List<Role> rolesPersist = new ArrayList<>();
        for (Role role : user.getRoles()) {
            Role userRole = roleRepository.findByRole(role.getRole()).get(0);
            rolesPersist.add(userRole);
        }
        user.setRoles(rolesPersist);
        userRepository.save(user);
    }

    @Override
    public void save(RoleVo roleVo) {
        roleRepository.save(RoleConverter.toBo(roleVo));
    }

    @Override
    public List<UserVo> getAlluUsers() {
        return UserConverter.toVoList(userRepository.findAll());
    }

    @Override
    public List<RoleVo> getAllRoles() {
        List<RoleVo> roleList = RoleConverter.toVoList(roleRepository.findAll());
        for (int i = 0; i < roleList.size(); i++) {
            if (roleList.get(i).getRole().equals("SUPERADMIN"))
                roleList.remove(i);
        }
        return roleList;
    }

    @Override
    public RoleVo getRoleByName(String role) {
        return RoleConverter.toVo(roleRepository.findByRole(role).get(0));
    }

    @Override
    public void cleanDataBase() {
        userRepository.deleteAll();
        roleRepository.deleteAll();
        empRepository.deleteAll();
    }

    @Override
    public List<UserVo> findUserHasRoleAdminAndClient() {
        return UserConverter.toVoList(userRepository.findUserHasRoleAdminAndClient());
    }

    @Override
    public UserVo findUserById(Long id) {
        return UserConverter.toVo(userRepository.getOne(id));
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(userRepository.getOne(id));
    }

}