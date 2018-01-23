package frc.team6223.robot.controllers;


import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.team6223.utils.drive.ControllerInput;
import frc.team6223.utils.drive.DriveController;
import frc.team6223.utils.drive.DriveControllerOutput;
import frc.team6223.utils.software.PIDFConstants;
import frc.team6223.utils.software.PIDFController;
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
        double motorVal = pidfController.runController(controllerInput.getLeftEncoderRate());
        // todo: separate left and right rates
        return new DriveControllerOutput(ControlMode.PercentOutput, motorVal, motorVal);
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
