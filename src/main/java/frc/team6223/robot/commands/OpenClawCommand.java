package frc.team6223.robot.commands;


import edu.wpi.first.wpilibj.command.Command;
import frc.team6223.robot.subsystem.Claw;
import frc.team6223.utils.units.Time;
import frc.team6223.utils.time.TimeKt;
import frc.team6223.utils.units.TimeUnits;


public class OpenClawCommand extends Command {

    private Claw clawSubsystem;
    private Time commandStart;
    private boolean passedFiveSeconds = false;

    public OpenClawCommand(Claw clawSubsystem) {
        this.clawSubsystem = clawSubsystem;

        this.requires(clawSubsystem);
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
        clawSubsystem.stopOpenCloseClaw();
    }

    @Override
    protected void interrupted() {
        super.interrupted();
        clawSubsystem.stopOpenCloseClaw();
    }

    @Override
    public synchronized void start() {
        super.start();
        commandStart = TimeKt.getCurrentTimeSec();
        clawSubsystem.openClaw();
    }

    @Override
    protected boolean isFinished() {
        return passedFiveSeconds;
    }
}
