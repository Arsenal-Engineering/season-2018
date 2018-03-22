package frc.team6223.robot.commands;


import edu.wpi.first.wpilibj.command.Command;
import frc.team6223.robot.subsystem.Claw;
import frc.team6223.utils.time.TimeKt;
import frc.team6223.utils.units.Time;
import frc.team6223.utils.units.TimeUnits;


public class ClawLRMovementCommand extends Command {

    private Claw clawSubsystem;
    private Time commandStart;
    private ClawLRMovement movement;
    private boolean passedFiveSeconds = false;

    public ClawLRMovementCommand(Claw clawSubsystem, ClawLRMovement movement) {
        this.clawSubsystem = clawSubsystem;
        this.movement = movement;
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
        clawSubsystem.stopLeftRightClaw();
    }

    @Override
    protected void interrupted() {
        super.interrupted();
        clawSubsystem.stopLeftRightClaw();
    }

    @Override
    public synchronized void start() {
        super.start();
        commandStart = TimeKt.getCurrentTimeSec();
        switch (movement) {
            case LEFT:
                clawSubsystem.moveClawLeft();
                break;
            case RIGHT:
                clawSubsystem.moveClawRight();
                break;
        }

    }

    @Override
    protected boolean isFinished() {
        return passedFiveSeconds;
    }
}

enum ClawLRMovement {
    LEFT,
    RIGHT
}