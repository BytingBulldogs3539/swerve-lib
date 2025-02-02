package com.swervedrivespecialties.swervelib.ctre;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.swervedrivespecialties.swervelib.DriveController;
import com.swervedrivespecialties.swervelib.DriveControllerFactory;
import com.swervedrivespecialties.swervelib.ModuleConfiguration;

public final class Falcon500DriveControllerFactoryBuilder {
    private static final double TICKS_PER_ROTATION = 2048.0;

    private static final int CAN_TIMEOUT_MS = 250;
    private static final int STATUS_FRAME_GENERAL_PERIOD_MS = 250;

    private double nominalVoltage = Double.NaN;
    private double currentLimit = Double.NaN;

    public Falcon500DriveControllerFactoryBuilder withVoltageCompensation(double nominalVoltage) {
        this.nominalVoltage = nominalVoltage;
        return this;
    }

    public boolean hasVoltageCompensation() {
        return Double.isFinite(nominalVoltage);
    }

    public DriveControllerFactory<ControllerImplementation, Integer> build() {
        return new FactoryImplementation();
    }

    public Falcon500DriveControllerFactoryBuilder withCurrentLimit(double currentLimit) {
        this.currentLimit = currentLimit;
        return this;
    }

    public boolean hasCurrentLimit() {
        return Double.isFinite(currentLimit);
    }

    private class FactoryImplementation implements DriveControllerFactory<ControllerImplementation,Integer> {
        @Override
        public ControllerImplementation create(Integer id, String canbus, ModuleConfiguration moduleConfiguration) {
            TalonFXConfiguration motorConfiguration = new TalonFXConfiguration();

            double sensorPositionCoefficient = Math.PI * moduleConfiguration.getWheelDiameter() * moduleConfiguration.getDriveReduction() / TICKS_PER_ROTATION;
            double sensorVelocityCoefficient = sensorPositionCoefficient * 10.0;

            if (hasVoltageCompensation()) {
                motorConfiguration.voltageCompSaturation = nominalVoltage;
            }

            if (hasCurrentLimit()) {
                motorConfiguration.supplyCurrLimit.currentLimit = currentLimit;
                motorConfiguration.supplyCurrLimit.enable = true;
            }

            TalonFX motor;
            if(canbus!=null)
            {
                motor = new TalonFX(id, canbus);
            }
            else
            {
                motor = new TalonFX(id);
            }
            motor.configAllSettings(motorConfiguration);

            if (hasVoltageCompensation()) {
                // Enable voltage compensation
                motor.enableVoltageCompensation(true);
            }

            motor.setNeutralMode(NeutralMode.Brake);

            motor.setInverted(moduleConfiguration.isDriveInverted() ? TalonFXInvertType.Clockwise : TalonFXInvertType.CounterClockwise);
            motor.setSensorPhase(true);

            // Reduce CAN status frame rates
            
            motor.setStatusFramePeriod(
                    StatusFrameEnhanced.Status_1_General,
                    STATUS_FRAME_GENERAL_PERIOD_MS,
                    CAN_TIMEOUT_MS
            );

            return new ControllerImplementation(motor, sensorVelocityCoefficient, sensorPositionCoefficient);
        }

        @Override
        public ControllerImplementation create(Integer id, ModuleConfiguration moduleConfiguration) {
            TalonFXConfiguration motorConfiguration = new TalonFXConfiguration();

            double sensorPositionCoefficient = Math.PI * moduleConfiguration.getWheelDiameter() * moduleConfiguration.getDriveReduction() / TICKS_PER_ROTATION;
            double sensorVelocityCoefficient = sensorPositionCoefficient * 10.0;

            if (hasVoltageCompensation()) {
                motorConfiguration.voltageCompSaturation = nominalVoltage;
            }

            if (hasCurrentLimit()) {
                motorConfiguration.supplyCurrLimit.currentLimit = currentLimit;
                motorConfiguration.supplyCurrLimit.enable = true;
            }

            TalonFX motor = new TalonFX(id);
            motor.configAllSettings(motorConfiguration);

            if (hasVoltageCompensation()) {
                // Enable voltage compensation
                motor.enableVoltageCompensation(true);
            }

            motor.setNeutralMode(NeutralMode.Brake);

            motor.setInverted(moduleConfiguration.isDriveInverted() ? TalonFXInvertType.Clockwise : TalonFXInvertType.CounterClockwise);
            motor.setSensorPhase(true);

            // Reduce CAN status frame rates
            
            motor.setStatusFramePeriod(
                    StatusFrameEnhanced.Status_1_General,
                    STATUS_FRAME_GENERAL_PERIOD_MS,
                    CAN_TIMEOUT_MS
            );

            return new ControllerImplementation(motor, sensorVelocityCoefficient, sensorPositionCoefficient);
        }
    }

    private class ControllerImplementation implements DriveController {
        private final TalonFX motor;
        private final double sensorVelocityCoefficient;
        private final double sensorPositionCoefficient;
        private final double nominalVoltage = hasVoltageCompensation() ? Falcon500DriveControllerFactoryBuilder.this.nominalVoltage : 12.0;

        private ControllerImplementation(TalonFX motor, double sensorVelocityCoefficient, double sensorPositionCoefficient) {
            this.motor = motor;
            this.sensorPositionCoefficient = sensorPositionCoefficient;
            this.sensorVelocityCoefficient = sensorVelocityCoefficient;
        }

        @Override
        public void setReferenceVoltage(double voltage) {
            motor.set(TalonFXControlMode.PercentOutput, voltage / nominalVoltage);
        }

        @Override
        public double getStateVelocity() {
            return motor.getSelectedSensorVelocity() * sensorVelocityCoefficient;
        }

        @Override
        public double getPositon() {
            return motor.getSelectedSensorPosition() * sensorPositionCoefficient;
        }
    }
}
