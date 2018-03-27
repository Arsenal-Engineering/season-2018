package frc.team6223.robot.subsystem;


import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team6223.arsenalFramework.hardware.motor.ArsenalTalon;
import frc.team6223.arsenalFramework.hardware.motor.MotorControlMode;
import frc.team6223.arsenalFramework.software.PIDFConstants;


public class Climber extends Subsystem {

    private ArsenalTalon stageTwoUpwardTalon;
    private ArsenalTalon stageThreeUpwardTalon;

    private ArsenalTalon stageTwoDownwardTalon;

    public Climber(ArsenalTalon stageTwoUpwardTalon, ArsenalTalon stageThreeUpwardTalon,
      ArsenalTalon stageTwoDownwardTalon)
    {
        this.stageTwoUpwardTalon = stageTwoUpwardTalon;
        this.stageThreeUpwardTalon = stageThreeUpwardTalon;
        this.stageTwoDownwardTalon = stageTwoDownwardTalon;
    }

    public void raiseStageTwo(double voltageOut) {
        stageTwoUpwardTalon.set(MotorControlMode.VoltagePercentOut, voltageOut);
    }

    public void lowerStageTwo(double voltageOut) {
        stageTwoUpwardTalon.set(MotorControlMode.VoltagePercentOut, -voltageOut);
    }

    public void raiseStageTwoWinch(double voltageOut) {
        //stageTwoUpwardTalon.set(MotorControlMode.VoltagePercentOut, voltageOut);
        stageTwoDownwardTalon.set(MotorControlMode.VoltagePercentOut, -voltageOut);
    }

    public void lowerStageTwoWinch(double voltageOut) {
        //stageTwoUpwardTalon.set(MotorControlMode.VoltagePercentOut, -voltageOut);
        stageTwoDownwardTalon.set(MotorControlMode.VoltagePercentOut, voltageOut);
    }

    public void stopStageTwo() {
        stageTwoUpwardTalon.set(MotorControlMode.VoltagePercentOut, 0);
        stageTwoDownwardTalon.set(MotorControlMode.VoltagePercentOut, 0);
    }


    public void raiseStageThree(double voltageOut) {
        stageThreeUpwardTalon.set(MotorControlMode.VoltagePercentOut, voltageOut);
    }

    public void lowerStageThree(double voltageOut) {
        stageThreeUpwardTalon.set(MotorControlMode.VoltagePercentOut, -voltageOut);
    }

    public void holdStageThree(double voltageOut) {
        stageThreeUpwardTalon.set(MotorControlMode.VoltagePercentOut, voltageOut);
    }

    @Override
    protected void initDefaultCommand() {

    }
}
