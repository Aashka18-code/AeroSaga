package com.aerosaga.workflow;

import java.time.Duration;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;

public class DroneWorkflowImpl implements DroneWorkflow {

private final DroneActivities activities =
        Workflow.newActivityStub(
                DroneActivities.class,
                ActivityOptions.newBuilder()
                        .setStartToCloseTimeout(Duration.ofSeconds(30))
                        .build()
        );

@Override
public String takeoff(String droneId) {
    return activities.takeoff(droneId);
}

}