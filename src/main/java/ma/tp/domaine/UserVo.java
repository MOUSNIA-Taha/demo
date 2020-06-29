package ma.tp.domaine;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {
	private Long id;
	private String username;
	private String password;
	private List<RoleVo> roles = new ArrayList<RoleVo>();

	public UserVo(String username, String password, List<RoleVo> roles) {
		super();
		this.username = username;
		this.password = password;
		this.roles = roles;
	}
}
