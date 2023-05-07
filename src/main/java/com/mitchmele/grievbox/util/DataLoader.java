package com.mitchmele.grievbox.util;

import com.mitchmele.grievbox.model.Grievance;
import com.mitchmele.grievbox.repository.GrievanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader  {

    private final GrievanceRepository repository;

    @EventListener(ApplicationReadyEvent.class)
    public void loadData() {
        Grievance grievance = Grievance.builder().text("Super Pissed at Mexican Restaurant").rating(4).build();
        Grievance grievance2 = Grievance.builder().text("Horrible Service in Uber").rating(6).build();
        Grievance grievance3 = Grievance.builder().text("Got a speeding ticket FUCK!").rating(10).build();

        List<Grievance> grievances = List.of(grievance, grievance2, grievance3);

        log.info("Saving data");

        repository.saveAll(grievances);

        log.info("Data Successfully Saved");
    }

}
