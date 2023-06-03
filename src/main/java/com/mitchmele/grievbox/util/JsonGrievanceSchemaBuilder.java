package com.mitchmele.grievbox.util;

import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Slf4j
@Component
public class JsonGrievanceSchemaBuilder implements GenericJsonSchemaBuilder {

    @Override
    public JsonSchema build(String resourceName) {
        InputStream schemaInputStream = getClass().getResourceAsStream(resourceName);
        JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        return schemaFactory.getSchema(schemaInputStream);
    }
}
