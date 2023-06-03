package com.mitchmele.grievbox.service;

import com.mitchmele.grievbox.model.Grievance;
import com.mitchmele.grievbox.model.GrievancesResponse;
import com.mitchmele.grievbox.model.SaveGrievanceRequest;
import com.mitchmele.grievbox.repository.GrievanceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GrievanceServiceTest {

    @Mock
    private GrievanceRepository repository;

    @Mock
    private EncryptionService encryptionService;

    @InjectMocks
    private GrievanceService service;

    @Test
    void getAllGrievances_shouldCallRepo() {
        Grievance grievance = Grievance.builder().text("Super Pissed at Mexican Restaurant").rating(4).build();
        Grievance grievance2 = Grievance.builder().text("Horrible Service in Uber").rating(6).build();
        Grievance grievance3 = Grievance.builder().text("Got a speeding ticket FUCK!").rating(10).build();

        List<Grievance> grievances = List.of(grievance, grievance2, grievance3);

        when(repository.findAll()).thenReturn(grievances);

        GrievancesResponse actual = service.getAllGrievances();

        GrievancesResponse expected = GrievancesResponse.builder().grievances(grievances).build();

        assertThat(actual).isEqualTo(expected);

        verify(repository).findAll();
    }

    @Test
    void saveNewGrievance_shouldCallEncryptionService_to_DecryptPayload() {
        String encryptedPayload = "eesdfgsd===VV";
        SaveGrievanceRequest saveGrievanceRequest = SaveGrievanceRequest.builder().jweTokenPayload(encryptedPayload).build();

        service.saveNewGrievance(saveGrievanceRequest);

        verify(encryptionService).decryptPayload(encryptedPayload);
    }
}