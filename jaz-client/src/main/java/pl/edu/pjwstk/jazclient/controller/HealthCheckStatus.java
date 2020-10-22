package pl.edu.pjwstk.jazclient.controller;

public class HealthCheckStatus {
    private String environment;
    private String health;

    public HealthCheckStatus(String environment, String health) {
        this.environment = environment;
        this.health = health;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }
}
