package com.meli.challenge.integration;

import com.meli.challenge.ChallengeApplication;
import com.meli.challenge.dto.RequestDTO;
import com.meli.challenge.dto.ResponseTraceDTO;
import com.meli.challenge.utilities.TestValues;
import org.json.JSONException;
import org.json.JSONObject;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Ezequiel Cruz Avila <ezecruzavila@gmail.com>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChallengeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TraceIntegrationTest {

    @LocalServerPort
    private int port;

    private final TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void postTrace_callExternalAPIs() throws JSONException {
        String url = "http://localhost:" + port + "/meli-tracer/api/trace";
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("ip", TestValues.IP);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(jSONObject.toString(), headers);
        ResponseTraceDTO responseTraceDTO = restTemplate.postForObject(url, request, ResponseTraceDTO.class);
        assertEquals(TestValues.IP, responseTraceDTO.getIp());
        assertEquals(TestValues.COUNTRY_CODE_US, responseTraceDTO.getCountryCode());
        assertEquals(TestValues.COUNTRY_NAME, responseTraceDTO.getCountryName());
        assertEquals(TestValues.ESTIMATED_DISTANCE_US_STR, responseTraceDTO.getEstimatedDistance());

    }
}
