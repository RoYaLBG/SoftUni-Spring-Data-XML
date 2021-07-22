package com.example.objectmapping.configs;

import org.modelmapper.ModelMapper;

public class ModelMapperSingleton {

    private static ModelMapper mapper = null;

    private static int inicializacii = 0;

    public static ModelMapper getInstance() {
        if (mapper == null) {
            System.out.println("INITICIALIZIRAME MAPPERA " + inicializacii++);
            mapper = new ModelMapper();
//            mapper.addMappings(new PropertyMap<Employee, EmployeeDto>() {
//                @Override
//                protected void configure() {
//                    map().setIncome(source.getSalary());
//                }
//            });
        }

        System.out.println("WRYSHTAME INICIALIZIRAN MAPPER " + inicializacii++);
        return mapper;
    }

}
