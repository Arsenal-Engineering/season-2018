package frc.team6223.robot.commands

import edu.wpi.first.wpilibj.command.Command
import frc.team6223.robot.driveSubsystem

class DriveTrainMovement() : Command() {

    override fun initialize() {
        super.initialize()
    }

    override fun execute() {
        super.execute()
        driveSubsystem.driveMotors()
    }

    override fun isFinished(): Boolean {
        return false
    }

    init {
        requires(driveSubsystem)
    }
}