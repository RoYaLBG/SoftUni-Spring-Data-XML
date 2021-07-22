package com.example.objectmapping.repositories;

import com.example.objectmapping.models.dto.EmployeeAverageSalaryDto;
import com.example.objectmapping.models.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT new com.example.objectmapping.models.dto.EmployeeAverageSalaryDto(AVG(e.salary), SUM(e.subordinates.size)) FROM Employee e GROUP BY e.id")
    List<EmployeeAverageSalaryDto> findAllWithAvgSalary();
}
