package frc.team6223.robot.subsystem;


import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team6223.utils.srx.MotorControlMode;
import frc.team6223.utils.srx.TalonMotor;


public class Claw extends Subsystem {

    private TalonMotor leftRightMotor;
    private TalonMotor upDownMotor;
    private TalonMotor winchMotor;
    private TalonMotor openCloseMotor;

    public Claw(TalonMotor leftRightMotor, TalonMotor upDownMotor, TalonMotor winchMotor, TalonMotor openCloseMotor) {
        this.leftRightMotor = leftRightMotor;
        this.upDownMotor = upDownMotor;
        this.winchMotor = winchMotor;
        this.openCloseMotor = openCloseMotor;
    }

    public void openClaw() {
        // Assume positive and half max output
        openCloseMotor.set(MotorControlMode.VoltagePercentOut, 0.5);
    }

    public void closeClaw() {
        openCloseMotor.set(MotorControlMode.VoltagePercentOut, -0.5);
    }

    public void stopOpenCloseClaw() {
        openCloseMotor.set(MotorControlMode.VoltagePercentOut, 0.0);
    }

    public void raiseClaw() {
        upDownMotor.set(MotorControlMode.VoltagePercentOut, 0.5);
    }

    public void lowerClaw() {
        upDownMotor.set(MotorControlMode.VoltagePercentOut, -0.5);
    }

    public void stopRaiseLowerClaw() {
        upDownMotor.set(MotorControlMode.VoltagePercentOut, 0.0);
    }

    public void moveClawLeft() {
        leftRightMotor.set(MotorControlMode.VoltagePercentOut, 0.5);
    }

    public void moveClawRight() {
        leftRightMotor.set(MotorControlMode.VoltagePercentOut, -0.5);
    }

    public void stopLeftRightClaw() {
        leftRightMotor.set(MotorControlMode.VoltagePercentOut, 0.0);
    }

    public void lowerWinch() {
        winchMotor.set(MotorControlMode.VoltagePercentOut, 0.5);
    }

    public void stopWinch() {
        winchMotor.set(MotorControlMode.VoltagePercentOut, 0.0);
    }

    @Override
    protected void initDefaultCommand() {

    }
}
