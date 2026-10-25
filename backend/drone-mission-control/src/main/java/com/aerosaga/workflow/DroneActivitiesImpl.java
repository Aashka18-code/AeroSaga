package com.aerosaga.workflow;

public class DroneActivitiesImpl implements DroneActivities {

    @Override
    public String takeoff(String droneId) {

        if (droneId == null || droneId.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Drone ID cannot be empty or null."
            );
        }

        System.out.println("Drone " + droneId + " is taking off!");

        return "Drone " + droneId + " takeoff completed successfully.";
    }
}