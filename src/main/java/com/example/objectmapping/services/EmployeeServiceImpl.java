package com.example.objectmapping.services;

import com.example.objectmapping.models.dto.EmployeeCreateRequest;
import com.example.objectmapping.models.dto.EmployeeCreateResponse;
import com.example.objectmapping.models.dto.ManagerDto;
import com.example.objectmapping.models.entities.Employee;
import com.example.objectmapping.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final ModelMapper mapper;

    public EmployeeServiceImpl(
            EmployeeRepository employeeRepository,
            ModelMapper mapper
    ) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    @Override
    public ManagerDto findOne(Long id) {
        return this.mapper.map(
                this.employeeRepository.findById(id).orElseThrow(),
                ManagerDto.class
        );
    }

    @Override
    public List<ManagerDto> findAll() {
        return this.mapper.map(
                this.employeeRepository.findAll(),
                new TypeToken<List<ManagerDto>>() {
                }.getType()
        );
    }

    @Override
    public EmployeeCreateResponse save(EmployeeCreateRequest createRequest) {
        return this.mapper.map(
                this.employeeRepository.save(
                        this.mapper.map(
                                createRequest,
                                Employee.class
                        )
                ),
                EmployeeCreateResponse.class
        );
    }

    @Transactional
    public void giveAway(long managerId) {
        var manager = this.employeeRepository.findById(managerId).orElseThrow();

        var halfSalary = manager.getSalary().divide(new BigDecimal(2), RoundingMode.CEILING);
        var forEachEmployee = halfSalary.divide(BigDecimal.valueOf(manager.getSubordinates().size()), RoundingMode.CEILING);

        manager.getSubordinates().forEach(employee -> {
            employee.setSalary(employee.getSalary().add(forEachEmployee));
            this.employeeRepository.save(employee);
        });

        manager.setSalary(manager.getSalary().subtract(halfSalary));

        this.employeeRepository.save(manager);
    }


}
