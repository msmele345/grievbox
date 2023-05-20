package com.mitchmele.grievbox.service;

import com.mitchmele.grievbox.model.Grievance;
import com.mitchmele.grievbox.model.GrievancesResponse;
import com.mitchmele.grievbox.repository.GrievanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GrievanceService {

    private final GrievanceRepository repository;

    public GrievancesResponse getAllGrievances() {
        List<Grievance> currentGrievances = repository.findAll();
        return GrievancesResponse.builder()
                .grievances(currentGrievances)
                .build();
    }
}
