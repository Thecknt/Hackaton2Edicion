package hackaton.Viajes.service;

import hackaton.Viajes.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEmployeeService {

    public List<Employee>employees();

    public Employee findById(Integer idEmployee);

    public void save(Employee employee);

    public void delete(Employee employee);

}
