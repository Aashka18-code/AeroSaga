package com.aerosaga.workflow;

public class DroneActivitiesImpl implements DroneActivities {

@Override
public String takeoff(String droneId) {
    System.out.println("Drone " + droneId + " is taking off!");

    return "Drone " + droneId + " takeoff completed successfully.";
}

}