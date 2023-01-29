package com.swervedrivespecialties.swervelib;

import edu.wpi.first.math.geometry.Rotation2d;

public interface SteerController {
    Rotation2d getReferenceAngle();

    void setReferenceAngle(double referenceAngleRadians);

    Rotation2d getStateAngle();
}
