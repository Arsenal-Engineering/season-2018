package frc.team6223.robot.commands;


import edu.wpi.first.wpilibj.command.Command;
import frc.team6223.robot.subsystem.Climber;


public class StageTwoMove extends Command {

    private Climber climber;
    private StageTwoDirection direction;

    public StageTwoMove(Climber climber, StageTwoDirection direction) {
        requires(climber);
        this.climber = climber;
        this.direction = direction;
    }

    public enum StageTwoDirection {
        UP,
        DOWN,
    }

    @Override
    protected void execute() {
        super.execute();
        switch (this.direction) {
            case UP:
                climber.raiseStageTwo(0.5);
                break;
            case DOWN:
                climber.lowerStageTwo(0.5);
                break;
        }
    }

    @Override
    protected void end() {
        super.end();
        climber.holdStageTwo(0.5);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
