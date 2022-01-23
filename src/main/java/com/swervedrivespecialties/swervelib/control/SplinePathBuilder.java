package com.swervedrivespecialties.swervelib.control;


import java.util.*;

import com.swervedrivespecialties.swervelib.math.Rotation2;
import com.swervedrivespecialties.swervelib.math.Vector2;
import com.swervedrivespecialties.swervelib.math.spline.CubicBezierSpline;
import com.swervedrivespecialties.swervelib.math.spline.CubicHermiteSpline;
import com.swervedrivespecialties.swervelib.math.spline.Spline;
import com.swervedrivespecialties.swervelib.util.SplinePathSegment;

public final class SplinePathBuilder {
    private List<PathSegment> segmentList = new ArrayList<>();
    private Map<Double, Rotation2> rotationMap = new TreeMap<>();
    private double length = 0.0;

    private PathSegment.State lastState;

    public SplinePathBuilder(Vector2 initialPosition, Rotation2 initialHeading, Rotation2 initialRotation) {
        lastState = new PathSegment.State(initialPosition, initialHeading, 0.0);
        rotationMap.put(0.0, initialRotation);
    }

    private void addSpline(Spline spline) {
        SplinePathSegment segment = new SplinePathSegment(spline);
        segmentList.add(segment);
        lastState = segment.getEnd();
        length += segment.getLength();
    }

    public Path build() {
        return new Path(segmentList.toArray(new PathSegment[0]), rotationMap);
    }

    public SplinePathBuilder bezier(Vector2 controlPoint1, Vector2 controlPoint2, Vector2 end) {
        addSpline(new CubicBezierSpline(
                lastState.getPosition(),
                controlPoint1,
                controlPoint2,
                end
        ));
        return this;
    }

    public SplinePathBuilder bezier(Vector2 controlPoint1, Vector2 controlPoint2, Vector2 end, Rotation2 rotation) {
        bezier(controlPoint1, controlPoint2, end);
        rotationMap.put(length, rotation);
        return this;
    }

    public SplinePathBuilder hermite(Vector2 position, Rotation2 heading) {
        addSpline(new CubicHermiteSpline(
                lastState.getPosition(), lastState.getHeading(),
                position, heading
        ));
        return this;
    }

    public SplinePathBuilder hermite(Vector2 position, Rotation2 heading, Rotation2 rotation) {
        hermite(position, heading);
        rotationMap.put(length, rotation);
        return this;
    }
}
