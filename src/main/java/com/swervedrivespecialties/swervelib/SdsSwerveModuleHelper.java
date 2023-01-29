package com.swervedrivespecialties.swervelib;

import com.swervedrivespecialties.swervelib.ctre.*;
import com.swervedrivespecialties.swervelib.rev.NeoDriveControllerFactoryBuilder;
import com.swervedrivespecialties.swervelib.rev.NeoSteerConfiguration;
import com.swervedrivespecialties.swervelib.rev.NeoSteerControllerFactoryBuilder;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;

public final class SdsSwerveModuleHelper {
    private SdsSwerveModuleHelper() {
    }

    private static DriveControllerFactory<?, Integer> getFalcon500DriveFactory(SdsModulePowerConfiguration configuration) {
        return new Falcon500DriveControllerFactoryBuilder()
                .withVoltageCompensation(configuration.getNominalVoltage())
                .withCurrentLimit(configuration.getDriveCurrentLimit())
                .build();
    }

    private static SteerControllerFactory<?, Falcon500SteerConfiguration<CanCoderAbsoluteConfiguration>> getFalcon500SteerFactory(SdsModulePowerConfiguration configuration) {
        return new Falcon500SteerControllerFactoryBuilder()
                .withVoltageCompensation(configuration.getNominalVoltage())
                .withPidConstants(0.2, 0.0, 0.1)
                .withCurrentLimit(configuration.getSteerCurrentLimit())
                .build(new CanCoderFactoryBuilder()
                        .withReadingUpdatePeriod(100)
                        .build());
    }

    private static DriveControllerFactory<?, Integer> getNeoDriveFactory(SdsModulePowerConfiguration configuration) {
        return new NeoDriveControllerFactoryBuilder()
                .withVoltageCompensation(configuration.getNominalVoltage())
                .withCurrentLimit(configuration.getDriveCurrentLimit())
                .build();
    }

    private static SteerControllerFactory<?, NeoSteerConfiguration<CanCoderAbsoluteConfiguration>> getNeoSteerFactory(SdsModulePowerConfiguration configuration) {
        return new NeoSteerControllerFactoryBuilder()
                .withVoltageCompensation(configuration.getNominalVoltage())
                .withPidConstants(1.0, 0.0, 0.1)
                .withCurrentLimit(configuration.getSteerCurrentLimit())
                .build(new CanCoderFactoryBuilder()
                        .withReadingUpdatePeriod(100)
                        .build());
    }

    /**
     * Creates a Sds swerve module that uses Falcon 500s for driving and steering.
     * Module information is displayed in the specified ShuffleBoard container.
     *
     * @param container        The container to display module information in.
     * @param configuration    Module configuration parameters to use.
     * @param gearRatio        The gearing configuration the module is in.
     * @param driveMotorPort   The CAN ID of the drive Falcon 500.
     * @param steerMotorPort   The CAN ID of the steer Falcon 500.
     * @param steerEncoderPort The CAN ID of the steer CANCoder.
     * @param steerOffset      The offset of the CANCoder in radians.
     * @return The configured swerve module.
     */
    public static SwerveModule createFalcon500(
            ShuffleboardLayout container,
            SdsModulePowerConfiguration configuration,
            ModuleConfiguration gearRatio,
            int driveMotorPort,
            int steerMotorPort,
            int steerEncoderPort,
            double steerOffset
    ) {
        return new SwerveModuleFactory<>(
                gearRatio,
                getFalcon500DriveFactory(configuration),
                getFalcon500SteerFactory(configuration)
        ).create(
                container,
                driveMotorPort,
                new Falcon500SteerConfiguration<>(
                        steerMotorPort,
                        new CanCoderAbsoluteConfiguration(steerEncoderPort, steerOffset)
                )
        );
    }

    /**
     * Creates a Sds swerve module that uses Falcon 500s for driving and steering.
     * Module information is displayed in the specified ShuffleBoard container.
     *
     * @param container        The container to display module information in.
     * @param configuration    Module configuration parameters to use.
     * @param gearRatio        The gearing configuration the module is in.
     * @param driveMotorPort   The CAN ID of the drive Falcon 500.
     * @param steerMotorPort   The CAN ID of the steer Falcon 500.
     * @param steerEncoderPort The CAN ID of the steer CANCoder.
     * @param steerOffset      The offset of the CANCoder in radians.
     * @return The configured swerve module.
     */
    public static SwerveModule createFalcon500(
            ShuffleboardLayout container,
            SdsModulePowerConfiguration configuration,
            ModuleConfiguration gearRatio,
            int driveMotorPort,
            String driveMotorCanbus,
            int steerMotorPort,
            String steerMotorCanbus,
            int steerEncoderPort,
            String steerEncoderCanbus,
            double steerOffset
    ) {
        return new SwerveModuleFactory<>(
                gearRatio,
                getFalcon500DriveFactory(configuration),
                getFalcon500SteerFactory(configuration)
        ).create(
                container,
                driveMotorPort,
                driveMotorCanbus,
                new Falcon500SteerConfiguration<>(
                        steerMotorPort,
                        steerEncoderCanbus,
                        new CanCoderAbsoluteConfiguration(steerEncoderPort,steerEncoderCanbus, steerOffset)
                )
        );
    }

    
    /**
     * Creates a Sds swerve module that uses Falcon 500s for driving and steering.
     * Module information is displayed in the specified ShuffleBoard container.
     *
     * @param container        The container to display module information in.
     * @param gearRatio        The gearing configuration the module is in.
     * @param driveMotorPort   The CAN ID of the drive Falcon 500.
     * @param steerMotorPort   The CAN ID of the steer Falcon 500.
     * @param steerEncoderPort The CAN ID of the steer CANCoder.
     * @param steerOffset      The offset of the CANCoder in radians.
     * @return The configured swerve module.
     */
    public static SwerveModule createFalcon500(
            ShuffleboardLayout container,
            ModuleConfiguration gearRatio,
            int driveMotorPort,
            int steerMotorPort,
            int steerEncoderPort,
            double steerOffset
    ) {
        return createFalcon500(container, new SdsModulePowerConfiguration(), gearRatio, driveMotorPort, steerMotorPort, steerEncoderPort, steerOffset);
    }

    /**
     * Creates a Sds swerve module that uses Falcon 500s for driving and steering.
     * Module information is displayed in the specified ShuffleBoard container.
     *
     * @param container        The container to display module information in.
     * @param gearRatio        The gearing configuration the module is in.
     * @param driveMotorPort   The CAN ID of the drive Falcon 500.
     * @param steerMotorPort   The CAN ID of the steer Falcon 500.
     * @param steerEncoderPort The CAN ID of the steer CANCoder.
     * @param steerOffset      The offset of the CANCoder in radians.
     * @return The configured swerve module.
     */
    public static SwerveModule createFalcon500(
            ShuffleboardLayout container,
            ModuleConfiguration gearRatio,
            int driveMotorPort,
            String driveMotorCanbus,
            int steerMotorPort,
            String steerMotorCanbus,
            int steerEncoderPort,
            String steerEncoderCanbus,
            double steerOffset
    ) {
        return createFalcon500(container, new SdsModulePowerConfiguration(), gearRatio, driveMotorPort, driveMotorCanbus, steerMotorPort, steerMotorCanbus, steerEncoderPort, steerEncoderCanbus, steerOffset);
    }

    /**
     * Creates a Sds swerve module that uses Falcon 500s for driving and steering.
     *
     * @param configuration    Module configuration parameters to use.
     * @param gearRatio        The gearing configuration the module is in.
     * @param driveMotorPort   The CAN ID of the drive Falcon 500.
     * @param steerMotorPort   The CAN ID of the steer Falcon 500.
     * @param steerEncoderPort The CAN ID of the steer CANCoder.
     * @param steerOffset      The offset of the CANCoder in radians.
     * @return The configured swerve module.
     */
    public static SwerveModule createFalcon500(
            SdsModulePowerConfiguration configuration,
            ModuleConfiguration gearRatio,
            int driveMotorPort,
            int steerMotorPort,
            int steerEncoderPort,
            double steerOffset
    ) {
        return new SwerveModuleFactory<>(
                gearRatio,
                getFalcon500DriveFactory(configuration),
                getFalcon500SteerFactory(configuration)
        ).create(
                driveMotorPort,
                new Falcon500SteerConfiguration<>(
                        steerMotorPort,
                        new CanCoderAbsoluteConfiguration(steerEncoderPort, steerOffset)
                )
        );
    }

    /**
     * Creates a Sds swerve module that uses Falcon 500s for driving and steering.
     *
     * @param configuration    Module configuration parameters to use.
     * @param gearRatio        The gearing configuration the module is in.
     * @param driveMotorPort   The CAN ID of the drive Falcon 500.
     * @param steerMotorPort   The CAN ID of the steer Falcon 500.
     * @param steerEncoderPort The CAN ID of the steer CANCoder.
     * @param steerOffset      The offset of the CANCoder in radians.
     * @return The configured swerve module.
     */
    public static SwerveModule createFalcon500(
            SdsModulePowerConfiguration configuration,
            ModuleConfiguration gearRatio,
            int driveMotorPort,
            String driveMotorCanbus,
            int steerMotorPort,
            String steerMotorCanbus,
            int steerEncoderPort,
            String steerEncoderCanbus,
            double steerOffset
    ) {
        return new SwerveModuleFactory<>(
                gearRatio,
                getFalcon500DriveFactory(configuration),
                getFalcon500SteerFactory(configuration)
        ).create(
                driveMotorPort,
                driveMotorCanbus,
                new Falcon500SteerConfiguration<>(
                        steerMotorPort,
                        steerMotorCanbus,
                        new CanCoderAbsoluteConfiguration(steerEncoderPort,steerEncoderCanbus, steerOffset)
                )
        );
    }

    /**
     * Creates a Sds swerve module that uses Falcon 500s for driving and steering.
     *
     * @param gearRatio        The gearing configuration the module is in.
     * @param driveMotorPort   The CAN ID of the drive Falcon 500.
     * @param steerMotorPort   The CAN ID of the steer Falcon 500.
     * @param steerEncoderPort The CAN ID of the steer CANCoder.
     * @param steerOffset      The offset of the CANCoder in radians.
     * @return The configured swerve module.
     */
    public static SwerveModule createFalcon500(
            ModuleConfiguration gearRatio,
            int driveMotorPort,
            int steerMotorPort,
            int steerEncoderPort,
            double steerOffset
    ) {
        return createFalcon500(new SdsModulePowerConfiguration(), gearRatio, driveMotorPort, steerMotorPort, steerEncoderPort, steerOffset);
    }

    /**
     * Creates a Sds swerve module that uses Falcon 500s for driving and steering.
     *
     * @param gearRatio        The gearing configuration the module is in.
     * @param driveMotorPort   The CAN ID of the drive Falcon 500.
     * @param steerMotorPort   The CAN ID of the steer Falcon 500.
     * @param steerEncoderPort The CAN ID of the steer CANCoder.
     * @param steerOffset      The offset of the CANCoder in radians.
     * @return The configured swerve module.
     */
    public static SwerveModule createFalcon500(
            ModuleConfiguration gearRatio,
            int driveMotorPort,
            String driveMotorCanbus,
            int steerMotorPort,
            String steerMotorCanbus,
            int steerEncoderPort,
            String steerEncoderCanbus,
            double steerOffset
    ) {
        return createFalcon500(new SdsModulePowerConfiguration(), gearRatio, driveMotorPort,driveMotorCanbus, steerMotorPort,steerMotorCanbus, steerEncoderPort,steerEncoderCanbus, steerOffset);
    }
    
        
    /**
     * Creates a Sds swerve module that uses NEOs for driving and steering.
     * Module information is displayed in the specified ShuffleBoard container.
     *
     * @param container        The container to display module information in.
     * @param configuration    Module configuration parameters to use.
     * @param gearRatio        The gearing configuration the module is in.
     * @param driveMotorPort   The CAN ID of the drive NEO.
     * @param steerMotorPort   The CAN ID of the steer NEO.
     * @param steerEncoderPort The CAN ID of the steer CANCoder.
     * @param steerOffset      The offset of the CANCoder in radians.
     * @return The configured swerve module.
     */
    public static SwerveModule createNeo(
            ShuffleboardLayout container,
            SdsModulePowerConfiguration configuration,
            ModuleConfiguration gearRatio,
            int driveMotorPort,
            int steerMotorPort,
            int steerEncoderPort,
            double steerOffset
    ) {
        return new SwerveModuleFactory<>(
                gearRatio,
                getNeoDriveFactory(configuration),
                getNeoSteerFactory(configuration)
        ).create(
                container,
                driveMotorPort,
                new NeoSteerConfiguration<>(
                        steerMotorPort,
                        new CanCoderAbsoluteConfiguration(steerEncoderPort, steerOffset)
                )
        );
    }


    /**
     * Creates a Sds swerve module that uses NEOs for driving and steering.
     * Module information is displayed in the specified ShuffleBoard container.
     *
     * @param container        The container to display module information in.
     * @param gearRatio        The gearing configuration the module is in.
     * @param driveMotorPort   The CAN ID of the drive NEO.
     * @param steerMotorPort   The CAN ID of the steer NEO.
     * @param steerEncoderPort The CAN ID of the steer CANCoder.
     * @param steerOffset      The offset of the CANCoder in radians.
     * @return The configured swerve module.
     */
    public static SwerveModule createNeo(
            ShuffleboardLayout container,
            ModuleConfiguration gearRatio,
            int driveMotorPort,
            int steerMotorPort,
            int steerEncoderPort,
            double steerOffset
    ) {
        return createNeo(container, new SdsModulePowerConfiguration(), gearRatio, driveMotorPort, steerMotorPort, steerEncoderPort, steerOffset);
    }

    /**
     * Creates a Sds swerve module that uses NEOs for driving and steering.
     *
     * @param configuration    Module configuration parameters to use.
     * @param gearRatio        The gearing configuration the module is in.
     * @param driveMotorPort   The CAN ID of the drive NEO.
     * @param steerMotorPort   The CAN ID of the steer NEO.
     * @param steerEncoderPort The CAN ID of the steer CANCoder.
     * @param steerOffset      The offset of the CANCoder in radians.
     * @return The configured swerve module.
     */
    public static SwerveModule createNeo(
            SdsModulePowerConfiguration configuration,
            ModuleConfiguration gearRatio,
            int driveMotorPort,
            int steerMotorPort,
            int steerEncoderPort,
            double steerOffset
    ) {
        return new SwerveModuleFactory<>(
                gearRatio,
                getNeoDriveFactory(configuration),
                getNeoSteerFactory(configuration)
        ).create(
                driveMotorPort,
                new NeoSteerConfiguration<>(
                        steerMotorPort,
                        new CanCoderAbsoluteConfiguration(steerEncoderPort, steerOffset)
                )
        );
    }

    /**
     * Creates a Sds swerve module that uses NEOs for driving and steering.
     *
     * @param gearRatio        The gearing configuration the module is in.
     * @param driveMotorPort   The CAN ID of the drive NEO.
     * @param steerMotorPort   The CAN ID of the steer NEO.
     * @param steerEncoderPort The CAN ID of the steer CANCoder.
     * @param steerOffset      The offset of the CANCoder in radians.
     * @return The configured swerve module.
     */
    public static SwerveModule createNeo(
        ModuleConfiguration gearRatio,
            int driveMotorPort,
            int steerMotorPort,
            int steerEncoderPort,
            double steerOffset
    ) {
        return createNeo(new SdsModulePowerConfiguration(), gearRatio, driveMotorPort, steerMotorPort, steerEncoderPort, steerOffset);
    }

    /**
     * Creates a Sds swerve module that uses a Falcon 500 for driving and a NEO for steering.
     * Module information is displayed in the specified ShuffleBoard container.
     *
     * @param container        The container to display module information in.
     * @param configuration    Module configuration parameters to use.
     * @param gearRatio        The gearing configuration the module is in.
     * @param driveMotorPort   The CAN ID of the drive Falcon 500.
     * @param steerMotorPort   The CAN ID of the steer NEO.
     * @param steerEncoderPort The CAN ID of the steer CANCoder.
     * @param steerOffset      The offset of the CANCoder in radians.
     * @return The configured swerve module.
     */
    public static SwerveModule createFalcon500Neo(
            ShuffleboardLayout container,
            SdsModulePowerConfiguration configuration,
            ModuleConfiguration gearRatio,
            int driveMotorPort,
            int steerMotorPort,
            int steerEncoderPort,
            double steerOffset
    ) {
        return new SwerveModuleFactory<>(
                gearRatio,
                getFalcon500DriveFactory(configuration),
                getNeoSteerFactory(configuration)
        ).create(
                container,
                driveMotorPort,
                new NeoSteerConfiguration<>(
                        steerMotorPort,
                        new CanCoderAbsoluteConfiguration(steerEncoderPort, steerOffset)
                )
        );
    }

    /**
     * Creates a Sds swerve module that uses a Falcon 500 for driving and a NEO for steering.
     * Module information is displayed in the specified ShuffleBoard container.
     *
     * @param container        The container to display module information in.
     * @param configuration    Module configuration parameters to use.
     * @param gearRatio        The gearing configuration the module is in.
     * @param driveMotorPort   The CAN ID of the drive Falcon 500.
     * @param steerMotorPort   The CAN ID of the steer NEO.
     * @param steerEncoderPort The CAN ID of the steer CANCoder.
     * @param steerOffset      The offset of the CANCoder in radians.
     * @return The configured swerve module.
     */
    public static SwerveModule createFalcon500Neo(
            ShuffleboardLayout container,
            SdsModulePowerConfiguration configuration,
            ModuleConfiguration gearRatio,
            int driveMotorPort,
            String driveMotorCanbus,
            int steerMotorPort,
            int steerEncoderPort,
            String steerEncoderCanbus,
            double steerOffset
    ) {
        return new SwerveModuleFactory<>(
                gearRatio,
                getFalcon500DriveFactory(configuration),
                getNeoSteerFactory(configuration)
        ).create(
                container,
                driveMotorPort,
                driveMotorCanbus,
                new NeoSteerConfiguration<>(
                        steerMotorPort,
                        new CanCoderAbsoluteConfiguration(steerEncoderPort, steerEncoderCanbus, steerOffset)
                )
        );
    }

    /**
     * Creates a Sds swerve module that uses a Falcon 500 for driving and a NEO for steering.
     * Module information is displayed in the specified ShuffleBoard container.
     *
     * @param container        The container to display module information in.
     * @param gearRatio        The gearing configuration the module is in.
     * @param driveMotorPort   The CAN ID of the drive Falcon 500.
     * @param steerMotorPort   The CAN ID of the steer NEO.
     * @param steerEncoderPort The CAN ID of the steer CANCoder.
     * @param steerOffset      The offset of the CANCoder in radians.
     * @return The configured swerve module.
     */
    public static SwerveModule createFalcon500Neo(
            ShuffleboardLayout container,
            ModuleConfiguration gearRatio,
            int driveMotorPort,
            int steerMotorPort,
            int steerEncoderPort,
            double steerOffset
    ) {
        return createFalcon500Neo(container, new SdsModulePowerConfiguration(), gearRatio, driveMotorPort, steerMotorPort, steerEncoderPort, steerOffset);
    }

    /**
     * Creates a Sds swerve module that uses a Falcon 500 for driving and a NEO for steering.
     * Module information is displayed in the specified ShuffleBoard container.
     *
     * @param container        The container to display module information in.
     * @param gearRatio        The gearing configuration the module is in.
     * @param driveMotorPort   The CAN ID of the drive Falcon 500.
     * @param steerMotorPort   The CAN ID of the steer NEO.
     * @param steerEncoderPort The CAN ID of the steer CANCoder.
     * @param steerOffset      The offset of the CANCoder in radians.
     * @return The configured swerve module.
     */
    public static SwerveModule createFalcon500Neo(
            ShuffleboardLayout container,
            ModuleConfiguration gearRatio,
            int driveMotorPort,
            String driveMotorCanbus,
            int steerMotorPort,
            int steerEncoderPort,
            String steerEncoderCanbus,
            double steerOffset
    ) {
        return createFalcon500Neo(container, new SdsModulePowerConfiguration(), gearRatio, driveMotorPort, driveMotorCanbus, steerMotorPort, steerEncoderPort, steerEncoderCanbus, steerOffset);
    }


    /**
     * Creates a Sds swerve module that uses a Falcon 500 for driving and a NEO for steering.
     *
     * @param configuration    Module configuration parameters to use.
     * @param gearRatio        The gearing configuration the module is in.
     * @param driveMotorPort   The CAN ID of the drive Falcon 500.
     * @param steerMotorPort   The CAN ID of the steer NEO.
     * @param steerEncoderPort The CAN ID of the steer CANCoder.
     * @param steerOffset      The offset of the CANCoder in radians.
     * @return The configured swerve module.
     */
    public static SwerveModule createFalcon500Neo(
            SdsModulePowerConfiguration configuration,
            ModuleConfiguration gearRatio,
            int driveMotorPort,
            int steerMotorPort,
            int steerEncoderPort,
            double steerOffset
    ) {
        return new SwerveModuleFactory<>(
                gearRatio,
                getFalcon500DriveFactory(configuration),
                getNeoSteerFactory(configuration)
        ).create(
                driveMotorPort,
                new NeoSteerConfiguration<>(
                        steerMotorPort,
                        new CanCoderAbsoluteConfiguration(steerEncoderPort, steerOffset)
                )
        );
    }
    /**
     * Creates a Sds swerve module that uses a Falcon 500 for driving and a NEO for steering.
     *
     * @param configuration    Module configuration parameters to use.
     * @param gearRatio        The gearing configuration the module is in.
     * @param driveMotorPort   The CAN ID of the drive Falcon 500.
     * @param steerMotorPort   The CAN ID of the steer NEO.
     * @param steerEncoderPort The CAN ID of the steer CANCoder.
     * @param steerOffset      The offset of the CANCoder in radians.
     * @return The configured swerve module.
     */
    public static SwerveModule createFalcon500Neo(
            SdsModulePowerConfiguration configuration,
            ModuleConfiguration gearRatio,
            int driveMotorPort,
            String driveMotorCanbus,
            int steerMotorPort,
            int steerEncoderPort,
            String steerEncoderCanbus,
            double steerOffset
    ) {
        return new SwerveModuleFactory<>(
                gearRatio,
                getFalcon500DriveFactory(configuration),
                getNeoSteerFactory(configuration)
        ).create(
                driveMotorPort,
                driveMotorCanbus,
                new NeoSteerConfiguration<>(
                        steerMotorPort,
                        new CanCoderAbsoluteConfiguration(steerEncoderPort, steerEncoderCanbus, steerOffset)
                )
        );
    }

    

    /**
     * Creates a Sds swerve module that uses a Falcon 500 for driving and a NEO for steering.
     *
     * @param gearRatio        The gearing configuration the module is in.
     * @param driveMotorPort   The CAN ID of the drive Falcon 500.
     * @param steerMotorPort   The CAN ID of the steer NEO.
     * @param steerEncoderPort The CAN ID of the steer CANCoder.
     * @param steerOffset      The offset of the CANCoder in radians.
     * @return The configured swerve module.
     */
    public static SwerveModule createFalcon500Neo(
            ModuleConfiguration gearRatio,
            int driveMotorPort,
            int steerMotorPort,
            int steerEncoderPort,
            double steerOffset
    ) {
        return createFalcon500Neo(new SdsModulePowerConfiguration(), gearRatio, driveMotorPort, steerMotorPort, steerEncoderPort, steerOffset);
    }

    /**
     * Creates a Sds swerve module that uses a Falcon 500 for driving and a NEO for steering.
     *
     * @param gearRatio        The gearing configuration the module is in.
     * @param driveMotorPort   The CAN ID of the drive Falcon 500.
     * @param steerMotorPort   The CAN ID of the steer NEO.
     * @param steerEncoderPort The CAN ID of the steer CANCoder.
     * @param steerOffset      The offset of the CANCoder in radians.
     * @return The configured swerve module.
     */
    public static SwerveModule createFalcon500Neo(
            ModuleConfiguration gearRatio,
            int driveMotorPort,
            String driveMotorCanbus,
            int steerMotorPort,
            int steerEncoderPort,
            String steerEncoderCanbus,
            double steerOffset
    ) {
        return createFalcon500Neo(new SdsModulePowerConfiguration(), gearRatio, driveMotorPort, driveMotorCanbus, steerMotorPort, steerEncoderPort, steerEncoderCanbus,steerOffset);
    }

    /**
     * Creates a Sds swerve module that uses a NEO for driving and a Falcon 500 for steering.
     * Module information is displayed in the specified ShuffleBoard container.
     *
     * @param container        The container to display module information in.
     * @param configuration    Module configuration parameters to use.
     * @param gearRatio        The gearing configuration the module is in.
     * @param driveMotorPort   The CAN ID of the drive NEO.
     * @param steerMotorPort   The CAN ID of the steer Falcon 500.
     * @param steerEncoderPort The CAN ID of the steer CANCoder.
     * @param steerOffset      The offset of the CANCoder in radians.
     * @return The configured swerve module.
     */
    public static SwerveModule createNeoFalcon500(
            ShuffleboardLayout container,
            SdsModulePowerConfiguration configuration,
            ModuleConfiguration gearRatio,
            int driveMotorPort,
            int steerMotorPort,
            int steerEncoderPort,
            double steerOffset
    ) {
        return new SwerveModuleFactory<>(
                gearRatio,
                getNeoDriveFactory(configuration),
                getFalcon500SteerFactory(configuration)
        ).create(
                container,
                driveMotorPort,
                new Falcon500SteerConfiguration<>(
                        steerMotorPort,
                        new CanCoderAbsoluteConfiguration(steerEncoderPort, steerOffset)
                )
        );
    }

    /**
     * Creates a Sds swerve module that uses a NEO for driving and a Falcon 500 for steering.
     * Module information is displayed in the specified ShuffleBoard container.
     *
     * @param container        The container to display module information in.
     * @param configuration    Module configuration parameters to use.
     * @param gearRatio        The gearing configuration the module is in.
     * @param driveMotorPort   The CAN ID of the drive NEO.
     * @param steerMotorPort   The CAN ID of the steer Falcon 500.
     * @param steerEncoderPort The CAN ID of the steer CANCoder.
     * @param steerOffset      The offset of the CANCoder in radians.
     * @return The configured swerve module.
     */
    public static SwerveModule createNeoFalcon500(
            ShuffleboardLayout container,
            SdsModulePowerConfiguration configuration,
            ModuleConfiguration gearRatio,
            int driveMotorPort,
            int steerMotorPort,
            String steerMotorCanbus,
            int steerEncoderPort,
            String steerEncoderCanbus,
            double steerOffset
    ) {
        return new SwerveModuleFactory<>(
                gearRatio,
                getNeoDriveFactory(configuration),
                getFalcon500SteerFactory(configuration)
        ).create(
                container,
                driveMotorPort,
                new Falcon500SteerConfiguration<>(
                        steerMotorPort,
                        steerMotorCanbus,
                        new CanCoderAbsoluteConfiguration(steerEncoderPort, steerEncoderCanbus, steerOffset)
                )
        );
    }

    /**
     * Creates a Sds swerve module that uses a NEO for driving and a Falcon 500 for steering.
     * Module information is displayed in the specified ShuffleBoard container.
     *
     * @param container        The container to display module information in.
     * @param gearRatio        The gearing configuration the module is in.
     * @param driveMotorPort   The CAN ID of the drive NEO.
     * @param steerMotorPort   The CAN ID of the steer Falcon 500.
     * @param steerEncoderPort The CAN ID of the steer CANCoder.
     * @param steerOffset      The offset of the CANCoder in radians.
     * @return The configured swerve module.
     */
    public static SwerveModule createNeoFalcon500(
            ShuffleboardLayout container,
            ModuleConfiguration gearRatio,
            int driveMotorPort,
            int steerMotorPort,
            int steerEncoderPort,
            double steerOffset
    ) {
        return createNeoFalcon500(container, new SdsModulePowerConfiguration(), gearRatio, driveMotorPort, steerMotorPort, steerEncoderPort, steerOffset);
    }

        /**
     * Creates a Sds swerve module that uses a NEO for driving and a Falcon 500 for steering.
     * Module information is displayed in the specified ShuffleBoard container.
     *
     * @param container        The container to display module information in.
     * @param gearRatio        The gearing configuration the module is in.
     * @param driveMotorPort   The CAN ID of the drive NEO.
     * @param steerMotorPort   The CAN ID of the steer Falcon 500.
     * @param steerEncoderPort The CAN ID of the steer CANCoder.
     * @param steerOffset      The offset of the CANCoder in radians.
     * @return The configured swerve module.
     */
    public static SwerveModule createNeoFalcon500(
            ShuffleboardLayout container,
            ModuleConfiguration gearRatio,
            int driveMotorPort,
            int steerMotorPort,
            String steerMotorCanbus,
            int steerEncoderPort,
            String steerEncoderCanbus,
            double steerOffset
    ) {
        return createNeoFalcon500(container, new SdsModulePowerConfiguration(), gearRatio, driveMotorPort, steerMotorPort, steerMotorCanbus, steerEncoderPort, steerEncoderCanbus, steerOffset);
    }

    
     /**
     * Creates a Sds swerve module that uses a NEO for driving and a Falcon 500 for steering.
     *
     * @param configuration    Module configuration parameters to use.
     * @param gearRatio        The gearing configuration the module is in.
     * @param driveMotorPort   The CAN ID of the drive NEO.
     * @param steerMotorPort   The CAN ID of the steer Falcon 500.
     * @param steerEncoderPort The CAN ID of the steer CANCoder.
     * @param steerOffset      The offset of the CANCoder in radians.
     * @return The configured swerve module.
     */
    public static SwerveModule createNeoFalcon500(
            SdsModulePowerConfiguration configuration,
            ModuleConfiguration gearRatio,
            int driveMotorPort,
            int steerMotorPort,
            int steerEncoderPort,
            double steerOffset
    ) {
        return new SwerveModuleFactory<>(
                gearRatio,
                getNeoDriveFactory(configuration),
                getFalcon500SteerFactory(configuration)
        ).create(
                driveMotorPort,
                new Falcon500SteerConfiguration<>(
                        steerMotorPort,
                        new CanCoderAbsoluteConfiguration(steerEncoderPort, steerOffset)
                )
        );
    }

    /**
     * Creates a Sds swerve module that uses a NEO for driving and a Falcon 500 for steering.
     *
     * @param configuration    Module configuration parameters to use.
     * @param gearRatio        The gearing configuration the module is in.
     * @param driveMotorPort   The CAN ID of the drive NEO.
     * @param steerMotorPort   The CAN ID of the steer Falcon 500.
     * @param steerEncoderPort The CAN ID of the steer CANCoder.
     * @param steerOffset      The offset of the CANCoder in radians.
     * @return The configured swerve module.
     */
    public static SwerveModule createNeoFalcon500(
            SdsModulePowerConfiguration configuration,
            ModuleConfiguration gearRatio,
            int driveMotorPort,
            int steerMotorPort,
            String steerMotorCanbus,
            int steerEncoderPort,
            String steerEncoderCanbus,
            double steerOffset
    ) {
        return new SwerveModuleFactory<>(
                gearRatio,
                getNeoDriveFactory(configuration),
                getFalcon500SteerFactory(configuration)
        ).create(
                driveMotorPort,
                new Falcon500SteerConfiguration<>(
                        steerMotorPort,
                        steerMotorCanbus,
                        new CanCoderAbsoluteConfiguration(steerEncoderPort, steerEncoderCanbus, steerOffset)
                )
        );
    }


    

    /**
     * Creates a Sds swerve module that uses a NEO for driving and a Falcon 500 for steering.
     *
     * @param gearRatio        The gearing configuration the module is in.
     * @param driveMotorPort   The CAN ID of the drive NEO.
     * @param steerMotorPort   The CAN ID of the steer Falcon 500.
     * @param steerEncoderPort The CAN ID of the steer CANCoder.
     * @param steerOffset      The offset of the CANCoder in radians.
     * @return The configured swerve module.
     */
    public static SwerveModule createNeoFalcon500(
        ModuleConfiguration gearRatio,
            int driveMotorPort,
            int steerMotorPort,
            int steerEncoderPort,
            double steerOffset
    ) {
        return createNeoFalcon500(new SdsModulePowerConfiguration(), gearRatio, driveMotorPort, steerMotorPort, steerEncoderPort, steerOffset);
    }

    /**
     * Creates a Sds swerve module that uses a NEO for driving and a Falcon 500 for steering.
     *
     * @param gearRatio        The gearing configuration the module is in.
     * @param driveMotorPort   The CAN ID of the drive NEO.
     * @param steerMotorPort   The CAN ID of the steer Falcon 500.
     * @param steerEncoderPort The CAN ID of the steer CANCoder.
     * @param steerOffset      The offset of the CANCoder in radians.
     * @return The configured swerve module.
     */
    public static SwerveModule createNeoFalcon500(
        ModuleConfiguration gearRatio,
        int driveMotorPort,
        int steerMotorPort,
        String steerMotorCanbus,
        int steerEncoderPort,
        String steerEncoderCanbus,
        double steerOffset
    ) {
        return createNeoFalcon500(new SdsModulePowerConfiguration(), gearRatio, driveMotorPort, steerMotorPort, steerMotorCanbus, steerEncoderPort, steerEncoderCanbus, steerOffset);
    }
}
