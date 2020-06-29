package ma.tp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ma.tp.dao.EmpRepository;
import ma.tp.domaine.EmpConverter;
import ma.tp.domaine.EmpVo;
import ma.tp.model.Emp;

@Service("empService")
@Transactional
public class EmpServiceImpl implements IEmpService {

	@Autowired
	EmpRepository empRepository;

	@Override
	public List<EmpVo> getEmployees() {
		return EmpConverter.toListVo(empRepository.findAll());
	}

	@Override
	public void save(EmpVo emp) {
		System.out.println("hello this start save from empServiceImpl*******************************************");
		empRepository.save(EmpConverter.toBo(emp));
		System.out.println("hello this end save from empServiceImpl");
	}

	@Override
	public EmpVo getEmpById(Long id) {
		if (!empRepository.existsById(id))
			return null;
		return EmpConverter.toVo(empRepository.getOne(id));
	}

	@Override
	public void delete(Long id) {
		empRepository.delete(EmpConverter.toBo(getEmpById(id)));
	}

	@Override
	public List<EmpVo> findBySalary(Double salary) {

		return EmpConverter.toListVo(empRepository.findBySalary(salary));
	}

	@Override
	public List<EmpVo> findByFunction(String f) {
		return EmpConverter.toListVo(empRepository.findByFunction(f));
	}

	@Override
	public List<EmpVo> findBySalaryAndFunction(Double salary, String function) {
		return EmpConverter.toListVo(empRepository.findBySalaryAndFunction(salary, function));
	}

	@Override
	public EmpVo getEmpHavaingMaxSalary() {
		return EmpConverter.toVo(empRepository.getEmpHavaingMaxSalary());
	}

	@Override
	public List<EmpVo> findAll(int pageId, int size) {
		Page<Emp> result = empRepository.findAll(PageRequest.of(pageId, size, Direction.ASC, "name"));
		return EmpConverter.toListVo(result.getContent());
	}

	@Override
	public List<EmpVo> sortBy(String fieldName) {
		return EmpConverter.toListVo(empRepository.findAll(Sort.by(fieldName)));
	}

}
