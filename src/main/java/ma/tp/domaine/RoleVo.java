package ma.tp.domaine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleVo {
	
	private long id;
	private String role;

	public RoleVo(String role) {
		this.role = role;
	}
}
