package com.example.objectmapping.services.util;

import com.example.objectmapping.exception.UnableToConvertException;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

@Component("xml_format_converter")
public class XMLFormatConverter implements FormatConverter {

    private boolean prettyPrint = false;

    @Override
    public void setPrettyPrint() {
        this.prettyPrint = true;
    }

    @Override
    public String serialize(Object obj) throws UnableToConvertException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, this.prettyPrint);
            StringWriter sw = new StringWriter();

            marshaller.marshal(
                    obj,
                    sw
            );

            return sw.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new UnableToConvertException();
        }
    }

    @Override
    public void serialize(Object obj, String fileName) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, this.prettyPrint);

            FileWriter fw = new FileWriter(fileName);

            marshaller.marshal(
                    obj,
                    fw
            );
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T> T deserialize(String input, Class<T> toType) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(toType);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());

            return (T) unmarshaller.unmarshal(inputStream);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public <T> T deserializeFromFile(String fileName, Class<T> toType) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(toType);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();


            return (T) unmarshaller.unmarshal(new FileInputStream(fileName));
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
