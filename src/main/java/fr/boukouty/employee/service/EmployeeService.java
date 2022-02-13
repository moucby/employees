package fr.boukouty.employee.service;

import fr.boukouty.employee.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    List<Employee> getEmployeesWithKeyWord(final String keyWord);

    void saveEmploye(final Employee employee);

    Employee getEmployeeById(final long id);

    void deleteEmployeeById(final long id);
}
