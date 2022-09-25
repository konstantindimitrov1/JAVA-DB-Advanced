package com.example.objectmapping.service;

import com.example.objectmapping.dto.EmployeeDto;
import com.example.objectmapping.dto.ManagerDto;
import com.example.objectmapping.entity.Employee;
import com.example.objectmapping.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final ModelMapper modelMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ManagerDto findOne(Long id) throws Exception {

        return modelMapper.map(this.employeeRepository.findById(id).orElseThrow(Exception::new), ManagerDto.class);

//        manual mapping
//        Employee oneEmployee = this.employeeRepository.findById(id).orElseThrow(Exception::new);
//
//        EmployeeDto dto = new EmployeeDto();
//
//        dto.setFirstName(oneEmployee.getFirstName());
//        dto.setLastName(oneEmployee.getLastName());
//        dto.setSalary(oneEmployee.getSalary());
//
//        return dto;
    }

    @Override
    public List<ManagerDto> findAll() {

        return modelMapper.map(this.employeeRepository.findAll(), new TypeToken<List<ManagerDto>>() {}.getType());
    }
}
