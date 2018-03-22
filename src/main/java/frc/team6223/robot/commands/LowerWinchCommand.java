package frc.team6223.robot.commands;


import edu.wpi.first.wpilibj.command.Command;
import frc.team6223.robot.subsystem.Claw;
import frc.team6223.utils.time.TimeKt;
import frc.team6223.utils.units.Time;
import frc.team6223.utils.units.TimeUnits;


public class LowerWinchCommand extends Command {

    private Claw clawSubsystem;
    private Time commandStart;
    private boolean passedFiveSeconds = false;

    public LowerWinchCommand(Claw clawSubsystem) {
        this.clawSubsystem = clawSubsystem;
    }

    @Override
    public synchronized void start() {
        super.start();
        commandStart = TimeKt.getCurrentTimeSec();
        this.clawSubsystem.lowerWinch();
    }

    @Override
    protected void execute() {
        super.execute();
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
