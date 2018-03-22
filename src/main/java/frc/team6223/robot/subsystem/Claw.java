package frc.team6223.robot.subsystem;


import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team6223.arsenalFramework.hardware.motor.ArsenalTalon;
import frc.team6223.arsenalFramework.hardware.motor.MotorControlMode;


public class Claw extends Subsystem {

    public static final Double timeToOpenClaw = 1.0;
    public static final Double timeToMoveClawFurthestLR = 1.0;
    public static final Double timeToLowerWinch = 1.0;

    //private TalonMotor leftRightMotor;
    //private TalonMotor upDownMotor;
    //private TalonMotor winchMotor;
    private ArsenalTalon openCloseMotor;

    public Claw(/*TalonMotor leftRightMotor, TalonMotor upDownMotor, TalonMotor winchMotor,*/ ArsenalTalon
    openCloseMotor) {
        //this.leftRightMotor = leftRightMotor;
        //this.upDownMotor = upDownMotor;
        //this.winchMotor = winchMotor;
        this.openCloseMotor = openCloseMotor;
    }

    public void openClaw() {
        // Assume positive and half max output
        openCloseMotor.set(MotorControlMode.VoltagePercentOut, 1.0);
    }

    public void closeClaw() {
        openCloseMotor.set(MotorControlMode.VoltagePercentOut, -1.0);
    }

    public void stopOpenCloseClaw() {
        openCloseMotor.set(MotorControlMode.VoltagePercentOut, 0.0);
    }

    public void raiseClaw() {
        //upDownMotor.set(MotorControlMode.VoltagePercentOut, 0.5);
    }

    public void lowerClaw() {
        //upDownMotor.set(MotorControlMode.VoltagePercentOut, -0.5);
    }

    public void stopRaiseLowerClaw() {
        //upDownMotor.set(MotorControlMode.VoltagePercentOut, 0.0);
    }

    public void moveClawLeft() {
        //leftRightMotor.set(MotorControlMode.VoltagePercentOut, 0.5);
    }

    public void moveClawRight() {
        //leftRightMotor.set(MotorControlMode.VoltagePercentOut, -0.5);
    }

    public void stopLeftRightClaw() {
        //leftRightMotor.set(MotorControlMode.VoltagePercentOut, 0.0);
    }

    public void lowerWinch() {
        //winchMotor.set(MotorControlMode.VoltagePercentOut, 0.5);
    }

    public void stopWinch() {
        //winchMotor.set(MotorControlMode.VoltagePercentOut, 0.0);
    }

    @Override
    protected void initDefaultCommand() {

    }
}
