package com.mitchmele.grievbox.controller;

import com.mitchmele.grievbox.model.GrievancesResponse;
import com.mitchmele.grievbox.service.GrievanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class GrievanceController {

    private final GrievanceService grievanceService;

    @CrossOrigin
    @GetMapping("/grievances")
    public ResponseEntity<GrievancesResponse> getGrievances() {
        return ResponseEntity.ok(grievanceService.getAllGrievances());
    }
}

