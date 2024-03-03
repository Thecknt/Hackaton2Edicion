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
        return this.employeeRepository.findAll();
    }

    @Override
    public Employee findById(Integer idEmployee) {
        Employee employee = this.employeeRepository.findById(idEmployee).orElse(null);
        return employee;
    }

    @Override
    public Employee save(Employee employee) {
      return this.employeeRepository.save(employee);
    }

    @Override
    public void deleteById(Integer idEmployee) {
        this.employeeRepository.deleteById(idEmployee);
    }

}
