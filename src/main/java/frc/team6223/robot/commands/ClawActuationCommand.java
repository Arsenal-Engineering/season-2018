package frc.team6223.robot.commands;


import edu.wpi.first.wpilibj.command.Command;
import frc.team6223.robot.subsystem.Claw;
import frc.team6223.utils.units.Time;
import frc.team6223.utils.time.TimeKt;
import frc.team6223.utils.units.TimeUnits;


public class ClawActuationCommand extends Command {

    private Claw clawSubsystem;
    private ClawActuation actuation;
    private Time commandStart;
    private boolean passedFiveSeconds = false;

    public ClawActuationCommand(Claw clawSubsystem, ClawActuation actuation) {
        this.clawSubsystem = clawSubsystem;
        this.actuation = actuation;

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
        switch (actuation) {
            case OPEN:
                clawSubsystem.openClaw();
                break;
            case CLOSE:
                clawSubsystem.closeClaw();
                break;
        }
    }

    @Override
    protected boolean isFinished() {
        return passedFiveSeconds;
    }
}

enum ClawActuation {
    OPEN,
    CLOSE
}
