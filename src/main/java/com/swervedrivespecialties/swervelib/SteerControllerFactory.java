package com.swervedrivespecialties.swervelib;

import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardContainer;

@FunctionalInterface
public interface SteerControllerFactory<Controller extends SteerController, SteerConfiguration> {
    default void addDashboardEntries(
            ShuffleboardContainer container,
            Controller controller
    ) {
        container.addNumber("Current Angle", () -> controller.getStateAngle().getDegrees());
        container.addNumber("Target Angle", () -> controller.getReferenceAngle().getDegrees());
    }

    default Controller create(
            ShuffleboardContainer dashboardContainer,
            SteerConfiguration steerConfiguration,
            ModuleConfiguration moduleConfiguration
    ) {
        var controller = create(steerConfiguration, moduleConfiguration);
        addDashboardEntries(dashboardContainer, controller);

        return controller;
    }

    Controller create(SteerConfiguration steerConfiguration, ModuleConfiguration moduleConfiguration);
}
