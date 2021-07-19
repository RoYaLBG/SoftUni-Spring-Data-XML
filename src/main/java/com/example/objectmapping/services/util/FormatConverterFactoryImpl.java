package com.example.objectmapping.services.util;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;


@Component
public class FormatConverterFactoryImpl implements FormatConverterFactory {

    private final ApplicationContext ctx;

    public FormatConverterFactoryImpl(ApplicationContext ctx) {
        this.ctx = ctx;
    }


    @Override
    public FormatConverter create(String formatType) {
//        "com.example.objectmapping.services.util." + "XML" + "FormatConverter"
        String className = formatType.toUpperCase() + FormatConverter.class.getSimpleName();
        try {
            return (FormatConverter)this.ctx.getBean(Class.forName(FormatConverter.class.getPackageName() + "." + className));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
