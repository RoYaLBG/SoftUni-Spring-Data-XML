package com.example.objectmapping;

import com.example.objectmapping.models.User;
import com.example.objectmapping.models.dto.SoldProductCollectionDto;
import com.example.objectmapping.models.dto.SoldProductDto;
import com.example.objectmapping.models.dto.UserCollectionDto;
import com.example.objectmapping.models.dto.UserWithSoldProductDto;
import com.example.objectmapping.repositories.UserRepository;
import com.example.objectmapping.services.util.FormatConverterFactory;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class Main implements CommandLineRunner {


    private final FormatConverterFactory factory;


    private final ModelMapper modelMapper;

    private final UserRepository userRepository;

    public Main(FormatConverterFactory factory, ModelMapper modelMapper, UserRepository userRepository) {
        this.factory = factory;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        Set<User> allWithSoldProducts = this.userRepository.findAllWithSoldProducts();

        var root = new UserCollectionDto();
        root.setUserCount(allWithSoldProducts.size());
        root.setUsers(allWithSoldProducts.stream().map(
                entity -> {
                    var userDto = this.modelMapper.map(entity, UserWithSoldProductDto.class);
                    var productCollection = new SoldProductCollectionDto();
                    productCollection.setProducts(
                            entity.getSoldProducts().stream().map(product -> this.modelMapper.map(product, SoldProductDto.class)).collect(Collectors.toList())
                    );

                    productCollection.setCount(entity.getSoldProducts().size());
                    userDto.setSoldProducts(productCollection);

                    return userDto;
                }
        ).collect(Collectors.toList()));


        var conv = this.factory.create("json");
        conv.setPrettyPrint();;

        System.out.println(conv.serialize(root));

//        var root = new UserCollectionDto();
//        root.setUserCount((long)allWithSoldProducts.size());
//
//        root.setUsers(allWithSoldProducts.stream().map(
//                entity -> this.modelMapper.map(entity, UserWithSoldProductDto.class)
//        ).collect(Collectors.toList()));
//
//        Stream.of(1,2,3,4,6)
//                .map(el -> el * 2)
//                .peek(el -> System.out.println(el * 2))
//                .collect(Collectors.toList());



//        var source = new DateTimeSource();
//        source.setDate("2020-11-11 12:34:56");
//
//        DateTimeTarget target = this.modelMapper.map(
//                source,
//                DateTimeTarget.class
//        );

//
//        var source = new FirstLastNameSource();
//        source.setFirstName("pesho");
//        source.setLastName("petrov");
//
//        var target = this.modelMapper.map(
//                source,
//                FullNameTarget.class
//        );

    }
}
