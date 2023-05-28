package com.mitchmele.grievbox;

import com.mitchmele.grievbox.repository.GrievanceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class GrievboxApplicationTests {

	@MockBean
	private GrievanceRepository repository;

	@Test
	void contextLoads() {
	}

}
