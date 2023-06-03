package com.mitchmele.grievbox.service;

import com.mitchmele.grievbox.model.Grievance;
import com.mitchmele.grievbox.model.GrievancesResponse;
import com.mitchmele.grievbox.model.SaveGrievanceRequest;
import com.mitchmele.grievbox.model.SaveGrievanceResponse;
import com.mitchmele.grievbox.repository.GrievanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GrievanceService {

    private final GrievanceRepository repository;
    private final EncryptionService encryptionService;

    public GrievancesResponse getAllGrievances() {
        List<Grievance> currentGrievances = repository.findAll();
        return GrievancesResponse.builder()
                .grievances(currentGrievances)
                .build();
    }

    public SaveGrievanceResponse saveNewGrievance(SaveGrievanceRequest encryptedRequest) {
        encryptionService.decryptPayload(encryptedRequest.getJweTokenPayload());
        return null;
    }
}
