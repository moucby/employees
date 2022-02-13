package fr.boukouty.employee.resource;

import fr.boukouty.employee.model.Employee;
import fr.boukouty.employee.model.Search;
import fr.boukouty.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class EmployeeResource {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public String viewHomePage(Model model){
        model.addAttribute("listEmployees", employeeService.getAllEmployees());
        model.addAttribute("search", new Search());
        return "index";
    }

    @PostMapping("search")
    public String resultSearchListEmployee(@ModelAttribute("search") Search search, Model model){
        List<Employee> employeeList = new ArrayList<>();
        if(!search.getLibelle().equals("")){
            employeeList = this.employeeService.getEmployeesWithKeyWord(search.getLibelle());
        }else{
            employeeList = this.employeeService.getAllEmployees();
        }

        model.addAttribute("listEmployees", employeeList);
        return "index";
    }

    @GetMapping("add-employee")
    public String newEmployee(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "newEmployee";
    }

    @PostMapping("saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        this.employeeService.saveEmploye(employee);
        return "redirect:/";
    }

    @GetMapping("update-employee/{id}")
    public String updateEmployee(@PathVariable(value = "id") long id, Model model){
        Employee employee = this.employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "updateEmployee";

    }

    @GetMapping("delete-employee/{id}")
    public String deleteEmployee(@PathVariable long id){
        this.employeeService.deleteEmployeeById(id);
        return "redirect:/";
    }


}
