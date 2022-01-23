package com.swervedrivespecialties.swervelib.util;

public interface InverseInterpolable<T> {
    double inverseInterpolate(T upper, T query);
}
