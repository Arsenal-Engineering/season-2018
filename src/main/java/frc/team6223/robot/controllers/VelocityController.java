package frc.team6223.robot.controllers;


import frc.team6223.arsenalFramework.drive.ControllerInput;
import frc.team6223.arsenalFramework.drive.DriveController;
import frc.team6223.arsenalFramework.drive.DriveControllerOutput;
import frc.team6223.arsenalFramework.hardware.MotorControlMode;
import frc.team6223.arsenalFramework.software.PIDFConstants;
import frc.team6223.arsenalFramework.software.PIDFController;
import frc.team6223.arsenalFramework.software.units.DistanceUnits;
import frc.team6223.arsenalFramework.software.units.TimeUnits;
import org.jetbrains.annotations.NotNull;


public class VelocityController implements DriveController {

    private final double velocityTarget;
    private PIDFController pidfController;

    public VelocityController(double velocityTarget) {
        this.velocityTarget = velocityTarget;
        pidfController = new PIDFController(new PIDFConstants(1.0, 0.0, 1.0, 1.0), velocityTarget);
    }

    public double getVelocityTarget() {
        return velocityTarget;
    }

    public PIDFController getPidfController() {
        return pidfController;
    }

    @NotNull
    @Override
    public DriveControllerOutput calculateMotorOutput(@NotNull ControllerInput controllerInput) {
        double motorVal = pidfController.runController(controllerInput.getLeftEncoderRate().rescaleScalar(DistanceUnits.METERS, TimeUnits.SECONDS));
        // todo: separate left and right rates
        return new DriveControllerOutput(MotorControlMode.PIDVelocity, motorVal, motorVal);
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
