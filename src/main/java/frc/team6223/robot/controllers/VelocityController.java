package frc.team6223.robot.controllers;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team6223.arsenalFramework.drive.ControllerInput;
import frc.team6223.arsenalFramework.drive.DriveController;
import frc.team6223.arsenalFramework.drive.DriveControllerOutput;
import frc.team6223.arsenalFramework.hardware.motor.MotorControlMode;
import frc.team6223.arsenalFramework.software.PIDFConstants;
import frc.team6223.arsenalFramework.software.PIDFController;
import frc.team6223.arsenalFramework.software.units.Distance;
import frc.team6223.arsenalFramework.software.units.DistanceUnits;
import frc.team6223.arsenalFramework.software.units.TimeUnits;
import org.jetbrains.annotations.NotNull;


public class VelocityController implements DriveController {

    private final double velocityTarget;
    private final PIDFConstants pidfConstants;
    private final PIDFController pidfController;

    public VelocityController(double velocityTarget) {
        this.velocityTarget = velocityTarget;
        pidfConstants = new PIDFConstants(1.0, 0.0, 1.0, 1.0);
        pidfController = new PIDFController(pidfConstants, velocityTarget);
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
    public void start(@NotNull int leftInitial, @NotNull int rightInitial) {

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isFinished() {
        return pidfController.isFinished();
    }

    @Override
    public void dashboardPeriodic() {
        SmartDashboard.putString("Current Controller", "VelocityController");
        SmartDashboard.putNumber("Velocity Target", velocityTarget);
        SmartDashboard.putNumber("Current Controller kP", pidfConstants.component1());
        SmartDashboard.putNumber("Current Controller kI", pidfConstants.component2());
        SmartDashboard.putNumber("Current Controller kD", pidfConstants.component3());
        SmartDashboard.putNumber("Current Controller kF", pidfConstants.component4());
    }
}
