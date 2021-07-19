package com.example.objectmapping;

import com.example.objectmapping.models.dto.EmployeeCreateRequest;
import com.example.objectmapping.models.dto.EmployeeCreateResponse;
import com.example.objectmapping.models.dto.ManagerCollection;
import com.example.objectmapping.models.dto.ManagerDto;
import com.example.objectmapping.services.EmployeeService;
import com.google.gson.Gson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
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

        JAXBContext jaxbContext = JAXBContext.newInstance(ManagerDto.class, ManagerCollection.class, EmployeeCreateRequest.class, EmployeeCreateResponse.class);
        Marshaller managerMarshaller = jaxbContext.createMarshaller();
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        managerMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);


//        JAXBContext jaxbContextAllManagers = JAXBContext.newInstance(ManagerCollection.class);
//        Marshaller allManagerMarshaller = jaxbContextAllManagers.createMarshaller();
//        allManagerMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);


        var sc = new Scanner(System.in);

        var line = sc.nextLine();

        while (!line.equals("end")) {
            var cmdParts = line.split(" ", 2);
            switch (cmdParts[0]) {
                case "find":
                    Long id = Long.parseLong(cmdParts[1]);
                    ManagerDto managerById = this.employeeService.findOne(id);

                    System.out.println(this.gson.toJson(managerById));

                    managerMarshaller.marshal(managerById, System.out);
                    break;
                case "findAll":
                    List<ManagerDto> allManagers = this.employeeService.findAll();
                    System.out.println(this.gson.toJson(allManagers));

                    managerMarshaller.marshal(new ManagerCollection(allManagers), System.out);

                    break;
                case "save":
                    /*
                      <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                      <employee first_name="..." last_name="...">
                          <salary>2000</salary>
                          <address>Mladost 4</address>
                      </employee>
                     *
                     * <?xml version="1.0" encoding="UTF-8" standalone="yes"?><employee first_name="Ot" last_name="XML :)"><salary>2000</salary><address>Mladost 4</address></employee>
                     */
                    //<employee><firstName>...</firstName><lastName>....</lastName><salary>...</salary><address></address></employee>
                    String input = cmdParts[1];

                    EmployeeCreateRequest request
                            = (EmployeeCreateRequest) unmarshaller.unmarshal(new ByteArrayInputStream(input.getBytes()));
//                    EmployeeCreateRequest request = this.gson.fromJson(
//                            input, // {"firstName": "...", ... }
//                            EmployeeCreateRequest.class
//                    );
//
                    EmployeeCreateResponse response = this.employeeService.save(
                            request
                    );

                    System.out.println(this.gson.toJson(response));
                    managerMarshaller.marshal(response, System.out);
                    break;
                case "save-from-file":
//                    EmployeeCreateRequest fileRequest = this.gson.fromJson(
//                            new FileReader(
//                                    cmdParts[1]
//                            ),
//                            EmployeeCreateRequest.class
//                    );

                    EmployeeCreateRequest fileRequest = (EmployeeCreateRequest) unmarshaller.unmarshal(
                            new FileReader(cmdParts[1])
                    );

                    EmployeeCreateResponse fileResponse = this.employeeService.save(
                            fileRequest
                    );

                    System.out.println(this.gson.toJson(fileResponse));
                    managerMarshaller.marshal(fileResponse, System.out);
                    break;
                case "findAll-to":
                    try (FileWriter fw1 = new FileWriter(cmdParts[1] + ".json")) {
                        List<ManagerDto> managers = this.employeeService.findAll();
                        this.gson.toJson(
                                managers,
                                fw1
                        );

                        managerMarshaller.marshal(
                                new ManagerCollection(managers),
                                new File(cmdParts[1] + ".xml")
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
