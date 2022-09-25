package com.example.objectmapping;

import com.example.objectmapping.dto.ManagerDto;
import com.example.objectmapping.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Main implements CommandLineRunner {

    private final EmployeeService employeeService;

    @Autowired
    public Main(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void run(String... args) throws Exception {
        List<ManagerDto> managers = employeeService.findAll();
        managers.forEach(managerDto -> {
            System.out.println(managerDto.getFirstName() + " "
        + managerDto.getLastName() + " :");
        managerDto.getSubordinates().forEach(employeeDto -> System.out.println("\t" + employeeDto.getFirstName() + " " + employeeDto.getLastName() + " : " + employeeDto.getIncome()));
        });


//        ManagerDto dto = employeeService.findOne(1L);
//        System.out.println(dto.getFirstName() + " "
//        + dto.getLastName() + " :");
//        dto.getSubordinates().forEach(employeeDto -> System.out.println("\t" + employeeDto.getFirstName() + " " + employeeDto.getLastName() + " : " + employeeDto.getIncome()));
    }
}
