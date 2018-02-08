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

    public void raiseClaw() {

    }

    public void lowerClaw() {

    }

    public void moveClawLeft() {

    }

    public void moveClawRight() {

    }

    public void lowerWinch() {

    }

    @Override
    protected void initDefaultCommand() {

    }
}
