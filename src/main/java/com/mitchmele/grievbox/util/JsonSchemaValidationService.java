package com.mitchmele.grievbox.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.ValidationMessage;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class JsonSchemaValidationService {

    private final ObjectMapper objectMapper;
    private final JsonGrievanceSchemaBuilder jsonGrievanceSchemaBuilder;

    private static final String VALID_GRIEVANCE_JSON = "/schemas/Grievance.json";

    public boolean validateJsonPayloadString(String payloadString) {
        String jsonToValidate = "{\"id\":\"1\",\"text\":\"Super Pissed at Mexican Restaurant\",\"rating\":4}";
        try {
            JsonSchema schema = jsonGrievanceSchemaBuilder.build(VALID_GRIEVANCE_JSON);

            JsonNode jsonNode = objectMapper.readTree(jsonToValidate);

            Set<ValidationMessage> validationMessages = schema.validate(jsonNode);

            log.info("JSON SCHEMA VALIDATION ERRORS: {}", objectMapper.writeValueAsString(validationMessages));

            return validationMessages.isEmpty();
        } catch (Exception e) {
            log.error("EXCEPTION DURING SCHEMA VALIDATION. MESSAGE: {}", e.getLocalizedMessage());
        }
        return false;
    }

    @PostConstruct
    public void validateSchema() {
        log.info("**VALIDATING**");
        String jsonToValidate = "{\"id\":\"1\",\"text\":\"Super Pissed at Mexican Restaurant\",\"rating\":4}";
        validateJsonPayloadString(jsonToValidate);
    }
}

//    public boolean validateJsonPayloadString(String payloadString) {
//        String jsonToValidate = "{\"id\":\"1\",\"text\":\"Super Pissed at Mexican Restaurant\",\"rating\":4}";
//        try {
////            InputStream schemaInputStream = getClass().getResourceAsStream("/schemas/Grievance.json");
////            JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
////
////            JsonSchema schema = schemaFactory.getSchema(schemaInputStream);
//
//            JsonNode jsonNode = objectMapper.readTree(jsonToValidate);
//
//            Set<ValidationMessage> validationMessages = schema.validate(jsonNode);
//
//            log.info("JSON SCHEMA ERRORS: {}", objectMapper.writeValueAsString(validationMessages));
//
//            return validationMessages.isEmpty();
//        } catch (Exception e) {
//            log.error("EXCEPTION DURING SCHEMA VALIDATION. MESSAGE: {}", e.getLocalizedMessage());
//        }
//        return false;
//    }