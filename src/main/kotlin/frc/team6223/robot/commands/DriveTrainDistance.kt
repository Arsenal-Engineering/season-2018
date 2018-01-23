package frc.team6223.robot.commands

import edu.wpi.first.wpilibj.command.Command
import frc.team6223.robot.controllers.PIDDistanceController
import frc.team6223.utils.drive.DriveSystem

/**
 * A [Command] to move a [DriveSystem] a certain distance
 *
 * This command uses the [PIDDistanceController] to attempt to move the robot a certain distance in feet.
 * @property dist The distance in feet to move
 * @property driveSubsystem The [DriveSystem] to move
 */
class DriveTrainDistance(private val dist: Double, private val driveSubsystem: DriveSystem): Command() {

    private val distController = PIDDistanceController(this.dist)

    override fun initialize() {
        super.initialize()
        driveSubsystem.driveMode = distController
    }

    override fun execute() {
        super.execute()
        driveSubsystem.driveMotors()
    }

    override fun isFinished(): Boolean {
        return distController.pidController.isFinished
    }

    init {
        requires(driveSubsystem)
    }
}