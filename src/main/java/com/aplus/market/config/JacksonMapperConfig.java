package com.aplus.market.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * @Author: ldh
 * @Date: 2018/12/26 22:50
 */
public class JacksonMapperConfig extends ObjectMapper {

    public JacksonMapperConfig() {
        super();

        this.configure(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN, true);

        this.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);
        this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        SimpleModule simpleModule = new SimpleModule();

        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);

        registerModule(simpleModule);
    }
}
