package ma.tp;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ma.tp.domaine.EmpVo;
import ma.tp.domaine.RoleVo;
import ma.tp.domaine.UserVo;
import ma.tp.service.IEmpService;
import ma.tp.service.IUserService;

@SpringBootApplication
public class LoginCRUDApplication implements CommandLineRunner {

	@Autowired
	private IUserService userService;

	@Autowired
	private IEmpService empService;

	public static void main(String[] args) {
		SpringApplication.run(LoginCRUDApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	} 



	@Override
	public void run(String... args) throws Exception {
		/*userService.cleanDataBase();
		userService.save(new RoleVo("ADMIN"));
		userService.save(new RoleVo("CLIENT")); 
		 RoleVo roleAdmin = userService.getRoleByName("ADMIN");
		RoleVo roleClient = userService.getRoleByName("CLIENT");
		UserVo admin1 = new UserVo("admin1", "admin1", Arrays.asList(roleAdmin));
		UserVo client1 = new UserVo("client1", "client1", Arrays.asList(roleClient));
		userService.save(admin1);
		userService.save(client1);
		empService.save(new EmpVo("emp1", 10000d, "Function1"));
		empService.save(new EmpVo("emp2", 20000d, "Function2"));
		empService.save(new EmpVo("emp3", 30000d, "Function3"));
		empService.save(new EmpVo("emp4", 40000d, "Function4"));
		empService.save(new EmpVo("emp5", 50000d, "Function5")); 

		RoleVo roleSuper=new RoleVo("SUPERADMIN");
		userService.save(roleSuper);
		UserVo userSuper=new UserVo("superadmin1","superadmin1",Arrays.asList(roleSuper));
		userService.save(userSuper); */
	}
}