package com.example.objectmapping.models.dto;

import com.example.objectmapping.configs.LocalDateTimeAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

@XmlRootElement(name = "test")
@XmlAccessorType(XmlAccessType.FIELD)
public class TestDto {

    @XmlElement(name = "some-date")
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime date;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
