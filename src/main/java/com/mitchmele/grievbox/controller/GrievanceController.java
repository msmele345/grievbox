package com.mitchmele.grievbox.controller;

import com.mitchmele.grievbox.model.Grievance;
import com.mitchmele.grievbox.model.GrievancesResponse;
import com.mitchmele.grievbox.repository.GrievanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class GrievanceController {

    private final GrievanceRepository repository;

    @GetMapping("/grievances")
    public ResponseEntity<GrievancesResponse> getGrievances() {
        List<Grievance> grievances = repository.findAll();
        return ResponseEntity.ok(GrievancesResponse.builder().grievances(grievances).build());
    }
}
