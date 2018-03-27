package frc.team6223.robot.commands;


import edu.wpi.first.wpilibj.command.Command;
import frc.team6223.robot.subsystem.Climber;


public class StageThreeMove extends Command {

    private Climber climber;
    private StageThreeDirection direction;

    public StageThreeMove(Climber climber, StageThreeDirection direction) {
        requires(climber);
        this.climber = climber;
        this.direction = direction;
    }

    public enum StageThreeDirection {
        UP,
        DOWN,
    }

    @Override
    protected void execute() {
        super.execute();
        switch(direction) {
            case UP:
                climber.raiseStageThree(0.1);
                break;
            case DOWN:
                climber.lowerStageThree(0.1);
                break;
        }
    }

    @Override
    protected void end() {
        super.end();
        //climber.holdStageThree(0.5);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
