package ma.tp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="role")
public class Role {
	@Id
	@GeneratedValue
	private long id;
	private String role;
	
	public Role(String role) {
		super();
		this.role = role;
	}
	
	
}
