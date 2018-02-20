package frc.team6223.robot.controllers;


import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team6223.arsenalFramework.drive.ControllerInput;
import frc.team6223.arsenalFramework.drive.DriveController;
import frc.team6223.arsenalFramework.drive.DriveControllerOutput;
import frc.team6223.arsenalFramework.hardware.MotorControlMode;
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
    public void start(@NotNull Distance leftInitial, @NotNull Distance rightInitial) {

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isFinished() {
        return pidfController.isFinished();
    }

    @Override
    public void dashboardPeriodic(NetworkTable table) {
        table.getEntry("CurrentController").setString("VelocityController");
        table.getEntry("Velocity Target").setNumber(velocityTarget);
        table.getEntry("Current Controller kP").setNumber(pidfConstants.component1());
        table.getEntry("Current Controller kI").setNumber(pidfConstants.component2());
        table.getEntry("Current Controller kD").setNumber(pidfConstants.component3());
        table.getEntry("Current Controller kF").setNumber(pidfConstants.component4());
    }
}
