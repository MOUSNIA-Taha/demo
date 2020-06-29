package ma.tp.domaine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpVo {
	private Long id;
	private String name;
	private double salary;
	private String function;

	public EmpVo(String name, double d, String function) {
		super();
		this.name = name;
		this.salary = d;
		this.function = function;
	}
}
