package com.mitchmele.grievbox.service;

import com.mitchmele.grievbox.model.*;
import com.mitchmele.grievbox.repository.GrievanceRepository;
import com.mitchmele.grievbox.util.JsonSchemaValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GrievanceService {

    private final GrievanceRepository repository;
    private final EncryptionService encryptionService;
    private final JsonSchemaValidationService schemaValidationService;

    public GrievancesResponse getAllGrievances() {
        List<Grievance> currentGrievances = repository.findAll();
        return GrievancesResponse.builder()
                .grievances(currentGrievances)
                .build();
    }

    public SaveGrievanceResponse saveNewGrievance(SaveGrievanceRequest encryptedRequest) {
        String decryptedPayload = encryptionService.decryptPayload(encryptedRequest.getJweTokenPayload());

        boolean isValidPayload = schemaValidationService.validateJsonPayloadString(decryptedPayload);
        //need mapper for decryptedPayload > Grievance obj
        //need interceptor in jwe-api
        //add @request header on Grievance post endpoint to validate interceptor
        //add jwe code in encryptionService
        //keys?
        return SaveGrievanceResponse.builder()
                .status("200")
                .message(ResponseMessage.SUCCESS)
                .build();
        //send back encryptedResponse later
    }
}
