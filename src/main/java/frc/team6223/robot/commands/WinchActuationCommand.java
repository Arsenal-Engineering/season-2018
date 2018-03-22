package frc.team6223.robot.commands;


import edu.wpi.first.wpilibj.command.Command;
import frc.team6223.arsenalFramework.hardware.TimeKt;
import frc.team6223.arsenalFramework.software.units.Time;
import frc.team6223.arsenalFramework.software.units.TimeUnits;
import frc.team6223.robot.subsystem.Claw;


public class WinchActuationCommand extends Command {

    private Claw clawSubsystem;
    private Time commandStart;
    private WinchDirection winchDirection;
    private boolean passedFiveSeconds = false;

    public WinchActuationCommand(Claw clawSubsystem, WinchDirection direction) {
        this.clawSubsystem = clawSubsystem;
        this.winchDirection = direction;
    }

    public enum WinchDirection {
        UP,
        DOWN,
    }

    @Override
    public synchronized void start() {
        super.start();
        commandStart = TimeKt.getCurrentTimeSec();
    }

    @Override
    protected void execute() {
        super.execute();
        switch (winchDirection) {
            case UP:
                this.clawSubsystem.raiseWinch();
                break;
            case DOWN:
                this.clawSubsystem.lowerWinch();
                break;
        }
        if (commandStart.minus(TimeKt.getCurrentTimeSec()).numericValue(TimeUnits.SECONDS) >= 5.0) {
            passedFiveSeconds = true;
        }
    }

    @Override
    protected void end() {
        super.end();
        this.clawSubsystem.stopWinch();
    }

    @Override
    protected void interrupted() {
        super.interrupted();
        this.clawSubsystem.stopWinch();
    }

    @Override
    protected boolean isFinished() {
        return passedFiveSeconds;
    }
}
