package com.meli.challenge;

import com.meli.challenge.entity.DistanceEntity;
import com.meli.challenge.entity.TraceEntity;
import com.meli.challenge.service.DistanceServiceImpl;
import com.meli.challenge.service.TraceServiceImpl;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ChallengeApplication {

        @Bean
        ApplicationRunner applicationRunner(TraceServiceImpl svc, DistanceServiceImpl distSvc){
            return args -> {
                //United States       
                svc.save( new TraceEntity("34.4.103.255","US"));
                distSvc.save(new DistanceEntity("US", 8701L));
                //Germany
                svc.save(new TraceEntity("34.4.103.255","GE"));
                distSvc.save(new DistanceEntity("GE", 10500L));
                //Spain
                svc.save(new TraceEntity("5.34.159.255","ES"));
                distSvc.save(new DistanceEntity("ES", 9000L));
                
                
            };
        }
        
	public static void main(String[] args) {
		SpringApplication.run(ChallengeApplication.class, args);
	}

}
