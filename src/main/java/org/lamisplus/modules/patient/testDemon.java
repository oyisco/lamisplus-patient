package org.lamisplus.modules.patient;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lamisplus.modules.patient.domain.model.Patient;
import org.lamisplus.modules.patient.resources.PatientResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest(PatientResources.class)
public class testDemon {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private PatientResources patientResources;

    public testDemon() {
    }

    private JacksonTester<Patient> jsonSuperHero;

    @Before
    public void setup() {
        jsonSuperHero.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.standaloneSetup(patientResources)
                .build();
    }

    @Test
    public void testForClient() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                post("/api/patient/search?searchContent=idris")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }
}
