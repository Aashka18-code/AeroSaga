package com.aerosaga.workflow;

import java.time.Duration;

import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.failure.ActivityFailure;
import io.temporal.workflow.Workflow;

public class DroneWorkflowImpl implements DroneWorkflow {

    private final DroneActivities activities =
            Workflow.newActivityStub(
                    DroneActivities.class,
                    ActivityOptions.newBuilder()
                            .setStartToCloseTimeout(Duration.ofSeconds(30))
                            .setRetryOptions(
                                RetryOptions.newBuilder()
                                .setMaximumAttempts(1)
                                .build()
                            )
                            .build()
            );

    @Override
    public String takeoff(String droneId) {

        activities.takeoff(droneId);
        activities.navigate(droneId);

        try {
            activities.dropPackage(droneId);
        } catch (ActivityFailure e) {
            activities.returnToBase(droneId);
            return "Package drop failed. Drone returned to base.";
        }

        activities.returnToBase(droneId);

        return "Mission completed successfully.";
    }
}