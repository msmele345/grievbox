package com.mitchmele.grievbox.service;

import com.mitchmele.grievbox.model.Grievance;
import com.mitchmele.grievbox.model.GrievancesResponse;
import com.mitchmele.grievbox.model.SaveGrievanceRequest;
import com.mitchmele.grievbox.repository.GrievanceRepository;
import com.mitchmele.grievbox.util.JsonSchemaValidationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GrievanceServiceTest {

    @Mock
    private GrievanceRepository repository;

    @Mock
    private JsonSchemaValidationService schemaValidationService;

    @Mock
    private JweService jweService;

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

        String decryptedPayload = "{\"id\":\"1\",\"text\":\"Super Pissed at Mexican Restaurant\",\"rating\":4}";

        when(jweService.decryptPayload(anyString())).thenReturn(decryptedPayload);
        when(schemaValidationService.validateJsonPayloadString(anyString())).thenReturn(true);
        doNothing().when(repository).save(any());

        Grievance expectedGrievanceToSave = Grievance.builder()
                .text("Super Pissed at Mexican Restaurant")
                .rating(4)
                .build();

        service.saveNewGrievance(saveGrievanceRequest);

        verify(jweService).decryptPayload(encryptedPayload);
//        verify(repository).save(expectedGrievanceToSave); //
        verify(schemaValidationService).validateJsonPayloadString(decryptedPayload);
    }
}