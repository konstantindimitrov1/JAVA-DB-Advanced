package com.example.objectmapping.service;

import com.example.objectmapping.dto.EmployeeDto;
import com.example.objectmapping.dto.ManagerDto;

import java.util.List;

public interface EmployeeService {

    ManagerDto findOne(Long id) throws Exception;

    List<ManagerDto> findAll();
}
