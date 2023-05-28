package com.mitchmele.grievbox.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitchmele.grievbox.model.Grievance;
import com.mitchmele.grievbox.model.GrievancesResponse;
import com.mitchmele.grievbox.repository.GrievanceRepository;
import com.mitchmele.grievbox.service.GrievanceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



import java.util.List;

@ExtendWith(MockitoExtension.class)
class GrievanceControllerTest {

    @Mock
    private GrievanceService grievanceService;

    @InjectMocks
    private GrievanceController controller;

    private MockMvc mockMvc;
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getGrievances_shouldReturnListOfGrievances() throws Exception {
        Grievance grievance = Grievance.builder().text("Super Pissed at Mexican Restaurant").rating(4).build();
        Grievance grievance2 = Grievance.builder().text("Horrible Service in Uber").rating(6).build();
        Grievance grievance3 = Grievance.builder().text("Got a speeding ticket FUCK!").rating(10).build();

        List<Grievance> grievances = List.of(grievance, grievance2, grievance3);

        GrievancesResponse expected = GrievancesResponse.builder().grievances(grievances).build();

        when(grievanceService.getAllGrievances()).thenReturn(expected);

        mockMvc.perform(get("/grievances"))
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(expected)));

        verify(grievanceService).getAllGrievances();
    }
}