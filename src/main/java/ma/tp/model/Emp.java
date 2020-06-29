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
@Table(name="emplyee")
public class Emp {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private double salary;
	private String function;
	
	public Emp(String name, Double salary, String function) {
		super();
		this.name = name;
		this.salary = salary;
		this.function = function;
	}
	
}
