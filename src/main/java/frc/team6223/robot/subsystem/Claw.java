package frc.team6223.robot.subsystem;


import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team6223.arsenalFramework.hardware.motor.ArsenalTalon;
import frc.team6223.arsenalFramework.hardware.motor.MotorControlMode;


public class Claw extends Subsystem {

    public static final Double timeToOpenClaw = 3.0;

    private ArsenalTalon winchMotor;
    private ArsenalTalon openCloseMotor;

    public Claw(ArsenalTalon winchMotor, ArsenalTalon openCloseMotor) {
        this.winchMotor = winchMotor;
        this.openCloseMotor = openCloseMotor;
    }

    public void openClaw() {
        // Assume positive and half max output
        openCloseMotor.set(MotorControlMode.VoltagePercentOut, -1.0);
    }

    public void closeClaw() {
        openCloseMotor.set(MotorControlMode.VoltagePercentOut, 1.0);
    }

    public void stopOpenCloseClaw() {
        openCloseMotor.set(MotorControlMode.VoltagePercentOut, 0.0);
    }

    public void raiseWinch() {
        winchMotor.set(MotorControlMode.VoltagePercentOut, -0.5);
    }

    public void lowerWinch() {
        winchMotor.set(MotorControlMode.VoltagePercentOut, 0.5);
    }

    public void stopWinch() {
        winchMotor.set(MotorControlMode.VoltagePercentOut, 0.0);
    }
	
	public void holdClaw() {
		openCloseMotor.set(MotorControlMode.VoltagePercentOut, 1.0);
	}
	
	public double getOpenCurrent() {
		return openCloseMotor.getOutputCurrent();
	}

    @Override
    protected void initDefaultCommand() {

    }
}
