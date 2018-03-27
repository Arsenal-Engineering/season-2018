package frc.team6223.robot.commands;


import edu.wpi.first.wpilibj.command.Command;
import frc.team6223.robot.subsystem.Climber;


public class StageTwoMove extends Command {

    private Climber climber;
    private StageTwoDirection direction;
    private boolean moveWinch = true;

    public StageTwoMove(Climber climber, StageTwoDirection direction, boolean moveWinch) {
        requires(climber);
        this.climber = climber;
        this.direction = direction;
        this.moveWinch = moveWinch;
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
                if (moveWinch) {
                    climber.raiseStageTwoWinch(1.0);
                } else {
                    climber.raiseStageTwo(0.3);
                }
                break;
            case DOWN:
                if (moveWinch) {
                    climber.lowerStageTwoWinch(0.3);
                } else {
                    climber.lowerStageTwo(0.3);
                }
                break;
        }
    }

    @Override
    protected void end() {
        super.end();
        climber.stopStageTwo();
    }

    @Override
    protected void interrupted() {
        super.interrupted();
        climber.stopStageTwo();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
