package com.swervedrivespecialties.swervelib.util;

public interface Interpolable<T> {
    T interpolate(T other, double t);
}