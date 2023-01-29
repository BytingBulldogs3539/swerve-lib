package com.swervedrivespecialties.swervelib;

import edu.wpi.first.math.kinematics.SwerveModulePosition;

public interface SwerveModule {
    SwerveModulePosition getPosition();

    void set(double driveVoltage, double steerAngle);
}
