package frc.team6223.robot.commands;


import edu.wpi.first.wpilibj.command.Command;
import frc.team6223.robot.controllers.VelocityController;
import frc.team6223.robot.subsystems.DriveSystem;


public class DriveTrainVelocity extends Command {
    private final double velocityTarget;
    private final DriveSystem driveSystem;

    private final VelocityController velocityController;

    public DriveTrainVelocity(double velocityTarget, DriveSystem driveSystem) {
        this.velocityTarget = velocityTarget;
        this.driveSystem = driveSystem;
        velocityController = new VelocityController(velocityTarget);
        requires(driveSystem);
    }

    public double getVelocityTarget() {
        return velocityTarget;
    }

    @Override
    protected void initialize() {
        super.initialize();
        this.driveSystem.setDriveMode(velocityController);
    }

    @Override
    protected void execute() {
        super.execute();
        this.driveSystem.driveMotors();
    }

    @Override
    protected boolean isFinished() {
        //todo: this command should get to target velocity then move for a certain amt of seconds
        return false;
    }
}
