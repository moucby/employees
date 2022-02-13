package fr.boukouty.employee.service;

import fr.boukouty.employee.model.Employee;
import fr.boukouty.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImp implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     *
     * @return
     */
    @Override
    public List<Employee> getAllEmployees() {
        return this.employeeRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Employee::getId).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> getEmployeesWithKeyWord(String keyWord) {
        List<Employee> employeeList = this.employeeRepository.findAll();

        return employeeList
                .stream()
                .filter(employee -> employee.getFirstName().toLowerCase().contains(keyWord.toLowerCase())
                        || employee.getLastName().toLowerCase().contains(keyWord.toLowerCase()))
                .sorted(Comparator.comparing(Employee::getId).reversed())
                .collect(Collectors.toList());

    }

    @Override
    public void saveEmploye(final Employee employee) {
        this.employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(final long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee
                .orElseThrow(() -> new RuntimeException("Employee not found for id :: "+id));
    }

    @Override
    public void deleteEmployeeById(long id) {
        this.employeeRepository.deleteById(id);
    }
}
