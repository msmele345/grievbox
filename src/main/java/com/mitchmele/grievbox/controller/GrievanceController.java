package com.mitchmele.grievbox.controller;

import com.mitchmele.grievbox.model.GrievancesResponse;
import com.mitchmele.grievbox.model.SaveGrievanceRequest;
import com.mitchmele.grievbox.model.SaveGrievanceResponse;
import com.mitchmele.grievbox.service.GrievanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/grievance")
    public ResponseEntity<SaveGrievanceResponse> saveGrievance(@RequestBody SaveGrievanceRequest request) {
        return ResponseEntity.ok(grievanceService.saveNewGrievance(request));
    }

}


