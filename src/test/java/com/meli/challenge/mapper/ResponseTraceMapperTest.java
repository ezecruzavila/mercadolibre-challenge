package com.meli.challenge.mapper;

import com.meli.challenge.dto.ResponseTraceDTO;
import com.meli.challenge.dto.external.CurrencyDTO;
import com.meli.challenge.dto.external.LanguageDTO;
import com.meli.challenge.dto.external.CountryDTO;
import com.meli.challenge.entity.TraceEntity;
import com.meli.challenge.utilities.FormatUtils;
import com.meli.challenge.utilities.TestValues;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

/**
 *
 * @author Ezequiel Cruz Avila <ezecruzavila@gmail.com>
 */
@RunWith(MockitoJUnitRunner.class)
public class ResponseTraceMapperTest {

    ResponseTraceMapperImpl mapper = new ResponseTraceMapperImpl();

    private final ResponseTraceDTO responseTraceDTO = new ResponseTraceDTO();
    private final CountryDTO restCountryDTO = new CountryDTO();


    public ResponseTraceMapperTest() {
    }

    @Before
    public void setup() {

        List<String> times = new ArrayList();
        times.add(FormatUtils.getTime(TestValues.TIMEZONE));
        List<String> languages = new ArrayList();
        List<String> currencies1 = new ArrayList<>();
        currencies1.add(TestValues.CURRENCY_CODE+ " (1" + TestValues.CURRENCY_CODE+  " = " + TestValues.USD_RATE + " EUR)");
        languages.add(TestValues.LANGUAGE_NAME + " (" + TestValues.LANGUAGE_ISO + ")");
        responseTraceDTO.setIp(TestValues.IP);
        responseTraceDTO.setCountryName(TestValues.COUNTRY_NAME);
        responseTraceDTO.setCountryCode(TestValues.COUNTRY_CODE_US);
        responseTraceDTO.setLanguages(languages);
        responseTraceDTO.setCurrencies(currencies1);
        responseTraceDTO.setTimes(times);
        responseTraceDTO.setEstimatedDistance(TestValues.ESTIMATED_DISTANCE_US_STR);

        List<String> timezones = new ArrayList();
        List<CurrencyDTO> currencies = new ArrayList<>();
        List<Double> latLong = new ArrayList<>();
        CurrencyDTO currencyDTO = new CurrencyDTO();
        List<LanguageDTO> languageDTOs = new ArrayList();
        LanguageDTO languageDTO = new LanguageDTO();
        languageDTO.setIso639_1(TestValues.LANGUAGE_ISO);
        languageDTO.setName(TestValues.LANGUAGE_NAME);
        currencyDTO.setCode(TestValues.CURRENCY_CODE);
        currencyDTO.setEuroRate(TestValues.EURO_RATE);
        latLong.add(TestValues.LATTITUDE);
        latLong.add(TestValues.LONGITUDE);
        timezones.add(TestValues.TIMEZONE);
        currencies.add(currencyDTO);
        languageDTOs.add(languageDTO);

        restCountryDTO.setName(TestValues.COUNTRY_NAME);
        restCountryDTO.setCurrencies(currencies);
        restCountryDTO.setLanguages(languageDTOs);
        restCountryDTO.setTimezones(timezones);
        restCountryDTO.setLatlng(latLong);
        restCountryDTO.setAlpha2Code(TestValues.COUNTRY_CODE_US);
    }

    /**
     * Test of format method, of class TraceMapper.
     */
    @Test
    public void testFormat() {
    }

    /**
     * Test of toCompleteDTO method, of class TraceMapper.
     */
    @Test
    public void testToCompleteDTO_DTOsNotNull() {

        ResponseTraceDTO result = mapper.toCompleteDTO(TestValues.IP, restCountryDTO);

        assertEquals(responseTraceDTO.getIp(), result.getIp());
        assertEquals(responseTraceDTO.getCountryName(), result.getCountryName());
        assertEquals(responseTraceDTO.getCountryCode(), result.getCountryCode());
        assertEquals(responseTraceDTO.getLanguages().get(0), result.getLanguages().get(0));
        assertEquals(responseTraceDTO.getCurrencies().get(0), result.getCurrencies().get(0));
        assertEquals(responseTraceDTO.getTimes().get(0), result.getTimes().get(0));
        assertEquals(responseTraceDTO.getEstimatedDistance(), result.getEstimatedDistance());

    }

    @Test
    public void testToCompleteDTO_IpIsNull() {

        ResponseTraceDTO result = mapper.toCompleteDTO(null, restCountryDTO);

        assertNull(result.getIp());
        assertEquals(responseTraceDTO.getCountryName(), result.getCountryName());
        assertEquals(responseTraceDTO.getCountryCode(), result.getCountryCode());
        assertEquals(responseTraceDTO.getLanguages().get(0), result.getLanguages().get(0));
        assertEquals(responseTraceDTO.getCurrencies().get(0), result.getCurrencies().get(0));
        assertEquals(responseTraceDTO.getTimes().get(0), result.getTimes().get(0));
        assertEquals(responseTraceDTO.getEstimatedDistance(), result.getEstimatedDistance());

    }

    @Test
    public void testToCompleteDTO_RestCountryDTOIsNull() {

        ResponseTraceDTO result = mapper.toCompleteDTO(TestValues.IP, null);

        assertEquals(responseTraceDTO.getIp(), result.getIp());
        assertNull(result.getCountryName());
        assertNull(result.getCountryCode());
        assertTrue(result.getLanguages().isEmpty());
        assertTrue(result.getCurrencies().isEmpty());
        assertTrue(result.getTimes().isEmpty());
        assertNull(result.getEstimatedDistance());

    }

    @Test
    public void testToCompleteDTO_DTOsNull() {
        assertNull(mapper.toCompleteDTO(null, null));
    }

    /**
     * Test of toReducedDTO method, of class TraceMapper.
     */
    @Test
    public void testToReducedDTO_TraceNotNull() {
        TraceEntity trace = new TraceEntity(TestValues.IP, TestValues.COUNTRY_CODE_US);
        ResponseTraceDTO dto = mapper.toReducedDTO(trace);
        assertEquals(trace.getIp(), dto.getIp());
        assertEquals(trace.getCountryCode(), dto.getCountryCode());
    }

    @Test
    public void testToReducedDTO_TraceNull() {
        assertNull(mapper.toReducedDTO(null));
    }

}
