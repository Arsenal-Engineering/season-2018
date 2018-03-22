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

    private final PIDFConstants raiseConstants = new PIDFConstants(1.0, 0.0, 0.0, 1.0);
    private final PIDFConstants lowerConstants = new PIDFConstants(.5, 0.0, 0.0, 1.0);

    public void raiseStageTwo(double voltageOut) {
        stageTwoUpwardTalon.set(MotorControlMode.VoltagePercentOut, voltageOut);
        stageTwoDownwardTalon.set(MotorControlMode.VoltagePercentOut, -voltageOut);
    }

    public void lowerStageTwo(double voltageOut) {
        stageTwoUpwardTalon.set(MotorControlMode.VoltagePercentOut, -voltageOut);
        stageTwoDownwardTalon.set(MotorControlMode.VoltagePercentOut, voltageOut);
    }

    public void holdStageTwo(double voltageOut) {
        stageTwoUpwardTalon.set(MotorControlMode.VoltagePercentOut, voltageOut);
        stageTwoDownwardTalon.set(MotorControlMode.VoltagePercentOut, voltageOut);
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
