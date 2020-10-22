package pl.edu.pjwstk.jazclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    public final String HEALTH_OK = "OK";
    public final String HEALTH_NOT_OK = "NOT_OK";

    public String currentHealthStatus = HEALTH_NOT_OK;

    @Value("${envName}")
    private String env;

    @GetMapping("/health")
    public HealthCheckStatus getHealthStatus() {
        return new HealthCheckStatus(env, currentHealthStatus);
    }

    @PostMapping("/health")
    public String changeHealthStatus() {
        if(currentHealthStatus.equals(HEALTH_NOT_OK)){
            currentHealthStatus = HEALTH_OK;
        } else {
            currentHealthStatus = HEALTH_NOT_OK;
        }

        return "ACCEPTED";
    }
}
