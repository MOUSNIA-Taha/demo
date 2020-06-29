package ma.tp.service;

import java.util.List;

import ma.tp.domaine.EmpVo;

public interface IEmpService {
	List<EmpVo> getEmployees();

	void save(EmpVo emp);

	EmpVo getEmpById(Long id);

	void delete(Long id);

	List<EmpVo> findBySalary(Double salary);

	List<EmpVo> findByFunction(String designation);

	List<EmpVo> findBySalaryAndFunction(Double salary, String fonction);

	EmpVo getEmpHavaingMaxSalary();

	// Pour la pagination
	List<EmpVo> findAll(int pageId, int size);

	// pour le tri
	List<EmpVo> sortBy(String fieldName);
}
