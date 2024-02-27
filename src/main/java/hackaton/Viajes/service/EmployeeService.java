package hackaton.Viajes.service;

import hackaton.Viajes.model.Employee;
import hackaton.Viajes.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService{

    @Autowired
    EmployeeRepository employeeRepository;
    @Override
    public List<Employee> employees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(Integer idEmployee) {
        Employee employee = employeeRepository.findById(idEmployee).orElse(null);
        return employee;
    }

    @Override
    public void save(Employee employee) {
      employeeRepository.save(employee);
    }

    @Override
    public void delete(Employee employee) {
     employeeRepository.delete(employee);
    }
}
