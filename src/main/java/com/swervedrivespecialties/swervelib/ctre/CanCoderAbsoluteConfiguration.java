package com.swervedrivespecialties.swervelib.ctre;

public class CanCoderAbsoluteConfiguration {
    private final int id;
    private final double offset;
    private final String canbus;

    public CanCoderAbsoluteConfiguration(int id, String canbus, double offset) {
        this.id = id;
        this.offset = offset;
        this.canbus = canbus;
    }
    public CanCoderAbsoluteConfiguration(int id, double offset) {
        this.id = id;
        this.offset = offset;
        this.canbus = null;
    }

    public String getCanbus()
    {
        return canbus;
    }

    public int getId() {
        return id;
    }

    public double getOffset() {
        return offset;
    }
}
