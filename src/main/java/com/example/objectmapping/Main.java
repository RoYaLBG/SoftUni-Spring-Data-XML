package com.example.objectmapping;

import com.example.objectmapping.models.dto.EmployeeCreateRequest;
import com.example.objectmapping.models.dto.EmployeeCreateResponse;
import com.example.objectmapping.models.dto.ManagerDto;
import com.example.objectmapping.services.EmployeeService;
import com.google.gson.Gson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

@Component
public class Main implements CommandLineRunner {

    private final EmployeeService employeeService;

    private final Gson gson;

    public Main(EmployeeService employeeService, Gson gson) {
        this.employeeService = employeeService;
        this.gson = gson;
    }

    @Override
    public void run(String... args) throws Exception {
        var sc = new Scanner(System.in);

        var line = sc.nextLine();

        while (!line.equals("end")) {
            var cmdParts = line.split(" ", 2);
            switch (cmdParts[0]) {
                case "find":
                    Long id = Long.parseLong(cmdParts[1]);
                    ManagerDto managerById = this.employeeService.findOne(id);
                    System.out.println(this.gson.toJson(managerById));
                    break;
                case "findAll":
                    List<ManagerDto> allManagers = this.employeeService.findAll();
                    System.out.println(this.gson.toJson(allManagers));
                    break;
                case "save":
                    String json = cmdParts[1];
                    EmployeeCreateRequest request = this.gson.fromJson(
                            json, // {"firstName": "...", ... }
                            EmployeeCreateRequest.class
                    );

                    EmployeeCreateResponse response = this.employeeService.save(
                            request
                    );

                    System.out.println(this.gson.toJson(response));
                    break;
                case "save-from-file":
                    EmployeeCreateRequest fileRequest = this.gson.fromJson(
                            new FileReader(
                                    cmdParts[1]
                            ),
                            EmployeeCreateRequest.class
                    );

                    EmployeeCreateResponse fileResponse = this.employeeService.save(
                            fileRequest
                    );

                    System.out.println(this.gson.toJson(fileResponse));
                    break;
                case "findAll-to":
                    try (FileWriter fw1 = new FileWriter(cmdParts[1])) {
                        List<ManagerDto> managers = this.employeeService.findAll();
                        this.gson.toJson(
                                managers,
                                fw1
                        );
                        System.out.println("Written to file " + cmdParts[1]);
                    }

                    break;

            }

            line = sc.nextLine();
        }



//
//        List<ManagerDto> managers = this.employeeService.findAll();
//        managers.forEach(managerDto -> {
//            if (managerDto.getSubordinates().isEmpty()) {
//                return;
//            }
//            System.out.println(managerDto.getFirstName() + " " + managerDto.getLastName() + " (" + managerDto.getSubordinates().size() + "):");
//            managerDto.getSubordinates().forEach(employeeDto -> {
//                System.out.println("\t" + employeeDto.getFirstName() + " " + employeeDto.getLastName() + " : " + employeeDto.getIncome());
//            });
//        });
    }
}
