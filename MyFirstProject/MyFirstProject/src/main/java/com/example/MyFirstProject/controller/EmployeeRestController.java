package com.example.MyFirstProject.controller;
import com.example.MyFirstProject.model.Employee;
import com.example.MyFirstProject.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class EmployeeRestController {
    @Autowired
    private EmployeeRepository employeeRepository;


    @GetMapping("/employee/{id}")
    public ResponseEntity<Optional<Employee>> getEmployeeById(@PathVariable Integer id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return ResponseEntity.ok().body(employee);
    }

    @GetMapping("/employee/name/{name}")
    public ResponseEntity<Employee> getEmployeeByName(@PathVariable String name) {
        Employee employee = employeeRepository.findByName(name);
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    @GetMapping("/employee/number/{number}")
    public ResponseEntity<Employee> getEmployeeByEmail(@PathVariable long number) {
        Employee employee = employeeRepository.findByNumber(number);
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    @GetMapping("/employee/email/{email}")
    public ResponseEntity<Employee> getEmployeeByEmail(@PathVariable String email) {
        Employee employee = employeeRepository.findByEmail(email);
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    @GetMapping("/listEmployees")
    public List< Employee > getAllEmployees() {
        return employeeRepository.findAll();
    }

    @PostMapping("/addEmployee")
    public Employee createEmployee( @RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @DeleteMapping("/deleteEmployee/{name}")
    public Map< String, Boolean > deleteEmployee(@PathVariable String name) {
        Employee employee = employeeRepository.findByName(name);
        employeeRepository.delete(employee);
        Map < String, Boolean > response = new HashMap< >();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PutMapping("/employee/update/{number}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long number,
                                                 @RequestBody Employee employeeDetails) {
      Employee employee = employeeRepository.findByNumber(number);

        employee.setEmail(employeeDetails.getEmail());
        employee.setName(employeeDetails.getName());
        employee.setNumber(employeeDetails.getNumber());
        final Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }
}
