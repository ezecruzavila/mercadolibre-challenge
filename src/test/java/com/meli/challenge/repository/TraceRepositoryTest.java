package com.meli.challenge.repository;

import com.meli.challenge.entity.TraceEntity;
import com.meli.challenge.service.DistanceServiceImpl;
import com.meli.challenge.service.TraceServiceImpl;
import com.meli.challenge.utilities.TestValues;
import java.util.HashMap;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Ezequiel Cruz Avila <ezecruzavila@gmail.com>
 */
@DataJpaTest
@RunWith(SpringRunner.class)
public class TraceRepositoryTest {

    @Autowired
    TraceRepository dao;

    @Autowired
    TestEntityManager em;

    @MockBean
    TraceServiceImpl traceServiceImpl;

    @MockBean
    DistanceServiceImpl distanceServiceImpl;

    @Test
    public void shouldSaveTrace() {
        TraceEntity trace = new TraceEntity(TestValues.IP, TestValues.COUNTRY_CODE_US);
        em.persistAndFlush(trace);
        TraceEntity daoTrace = ((List<TraceEntity>) dao.findAll()).get(0);
        assertEquals(trace.getCountryCode(), daoTrace.getCountryCode());
        assertEquals(trace.getIp(), daoTrace.getIp());
    }

    @Test
    public void testCountTraceByCountryCode() {
        int usReps = 3;
        int geReps = 4;
        for (int i = 0; i < usReps; i++) {
            TraceEntity trace = new TraceEntity(TestValues.IP, TestValues.COUNTRY_CODE_US);
            em.persistAndFlush(trace);
        }
        for (int i = 0; i < geReps; i++) {
            TraceEntity trace = new TraceEntity(TestValues.IP, TestValues.COUNTRY_CODE_GE);
            em.persistAndFlush(trace);
        }
        List<Object[]> response = dao.countCountryCodeGroupByCountryCode();
        HashMap<String, Long> traceCount = new HashMap();
        response.forEach(objects -> {
            traceCount.put((String) objects[0], (Long) objects[1]);
        });
        ;
        assertEquals(new Long(usReps), traceCount.get(TestValues.COUNTRY_CODE_US));
        assertEquals(new Long(geReps), traceCount.get(TestValues.COUNTRY_CODE_GE));
    }

}
