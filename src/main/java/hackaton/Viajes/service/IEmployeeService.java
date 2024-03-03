package hackaton.Viajes.service;

import hackaton.Viajes.model.Employee;

import java.util.List;

public interface IEmployeeService {

    public List<Employee>employees();

    public Employee findById(Integer idEmployee);

    public Employee save(Employee employee);

    public void deleteById(Integer idEmployee);

}
