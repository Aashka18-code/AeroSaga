package com.aerosaga.workflow;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;

public class DroneClient {

    public static void main(String[] args) {

        WorkflowServiceStubs service =
                WorkflowServiceStubs.newLocalServiceStubs();

        WorkflowClient client =
                WorkflowClient.newInstance(service);

        DroneWorkflow workflow = client.newWorkflowStub(
                DroneWorkflow.class,
                WorkflowOptions.newBuilder()
                        .setTaskQueue("DRONE_TASK_QUEUE")
                        .setWorkflowId("drone-takeoff-001")
                        .build()
        );

        String result = workflow.takeoff("DRONE-001");

        System.out.println("Workflow Result: " + result);

        service.shutdown();
    }
}